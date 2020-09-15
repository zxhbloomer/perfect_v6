package com.perfect.core.serviceimpl.sys.pages.function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.perfect.bean.entity.sys.pages.function.SFunctionEntity;
import com.perfect.bean.pojo.result.CheckResult;
import com.perfect.bean.pojo.result.DeleteResult;
import com.perfect.bean.pojo.result.InsertResult;
import com.perfect.bean.pojo.result.UpdateResult;
import com.perfect.bean.result.utils.v1.CheckResultUtil;
import com.perfect.bean.result.utils.v1.DeleteResultUtil;
import com.perfect.bean.result.utils.v1.InsertResultUtil;
import com.perfect.bean.result.utils.v1.UpdateResultUtil;
import com.perfect.bean.vo.sys.pages.function.SFunctionVo;
import com.perfect.common.exception.BusinessException;
import com.perfect.common.exception.UpdateErrorException;
import com.perfect.common.utils.bean.BeanUtilsSupport;
import com.perfect.core.mapper.sys.pages.function.SFunctionMapper;
import com.perfect.core.service.sys.pages.function.ISFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 按钮表 服务实现类
 * </p>
 *
 * @author zxh
 * @since 2020-06-16
 */
@Service
public class SFunctionServiceImpl extends ServiceImpl<SFunctionMapper, SFunctionEntity> implements ISFunctionService {

    @Autowired
    private SFunctionMapper mapper;

    /**
     * 获取列表，页面查询
     *
     * @param searchCondition
     * @return
     */
    @Override
    public List<SFunctionVo> selectPage(SFunctionVo searchCondition) {

        return mapper.selectPage(searchCondition);
    }

    /**
     * 获取列表，查询所有数据
     *
     * @param searchCondition
     * @return
     */
    @Override
    public List<SFunctionVo> select(SFunctionVo searchCondition) {
        // 查询 数据
        List<SFunctionVo> list = mapper.select(searchCondition);
        return list;
    }

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param vo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public InsertResult<Integer> insert(SFunctionVo vo) {
        // 插入前check
        CheckResult cr = checkLogic(vo, CheckResult.INSERT_CHECK_TYPE);
        if (cr.isSuccess() == false) {
            throw new BusinessException(cr.getMessage());
        }

        // 设置：字典键值和字典排序
        SFunctionVo data = mapper.getSortNum();
        if (null == data) {
            vo.setSort(0);
        } else {
            vo.setSort(data.getSort()+1);
        }

        // 插入逻辑保存
        SFunctionEntity sfe = (SFunctionEntity) BeanUtilsSupport.copyProperties(vo, SFunctionEntity.class);
        int rtn = mapper.insert(sfe);
        vo.setId(sfe.getId());
        return InsertResultUtil.OK(rtn);
    }

    /**
     * 更新一条记录（选择字段，策略更新）
     *
     * @param vo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UpdateResult<Integer> update(SFunctionVo vo) {
        // 更新前check
        CheckResult cr = checkLogic(vo, CheckResult.UPDATE_CHECK_TYPE);
        if (cr.isSuccess() == false) {
            throw new BusinessException(cr.getMessage());
        }
        // 更新逻辑保存
        vo.setC_id(null);
        vo.setC_time(null);
        SFunctionEntity sfe = (SFunctionEntity) BeanUtilsSupport.copyProperties(vo, SFunctionEntity.class);
        return UpdateResultUtil.OK(mapper.updateById(sfe));
    }

    /**
     * 获取列表，查询所有数据
     *
     * @return
     */
    public int selectByName(SFunctionVo entity, String moduleType) {
        return mapper.selectCount(new QueryWrapper<SFunctionEntity>()
            .eq("name", entity.getName())
            .ne(CheckResult.UPDATE_CHECK_TYPE.equals(moduleType) ? true:false, "id", entity.getId())
        );
    }

    /**
     * 获取列表，查询所有数据
     *
     * @return
     */
    public int selectByCode(SFunctionVo entity, String moduleType) {
        return mapper.selectCount(new QueryWrapper<SFunctionEntity>()
            .eq("code", entity.getCode())
            .ne(CheckResult.UPDATE_CHECK_TYPE.equals(moduleType) ? true:false, "id", entity.getId())
        );
    }

    /**
     * check逻辑
     *
     * @return
     */
    public CheckResult checkLogic(SFunctionVo vo, String moduleType) {

        switch (moduleType) {
            case CheckResult.INSERT_CHECK_TYPE:
                // 新增场合，不能重复
                if (selectByCode(vo, moduleType) >= 1) {
                    return CheckResultUtil.NG("新增保存出错：按钮编号【"+ vo.getCode() +"】出现重复!", vo.getCode());
                }
                if (selectByName(vo, moduleType) >= 1) {
                    return CheckResultUtil.NG("新增保存出错：按钮名称【"+ vo.getName() +"】出现重复!", vo.getName());
                }

                break;
            case CheckResult.UPDATE_CHECK_TYPE:
                // 更新场合，不能重复设置
                if (selectByCode(vo, moduleType) >= 1) {
                    return CheckResultUtil.NG("更新保存出错：按钮编号【"+ vo.getCode() +"】出现重复!", vo.getCode());
                }
                if (selectByName(vo, moduleType) >= 1) {
                    return CheckResultUtil.NG("更新保存出错：按钮名称【"+ vo.getName() +"】出现重复!", vo.getName());
                }
                break;
            default:
        }
        return CheckResultUtil.OK();
    }

    /**
     * 查询by id，返回结果
     *
     * @param id
     * @return
     */
    @Override
    public SFunctionVo selectByid(Long id) {
        // 查询 数据
        return mapper.selectId(id);
    }

    /**
     * 批量删除
     *
     * @param searchCondition
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public DeleteResult<Integer> realDeleteByIdsIn(List<SFunctionVo> searchCondition) {
        List<Long> idList = new ArrayList<>();
        searchCondition.forEach(bean -> {
            idList.add(bean.getId());
        });
        int result=mapper.deleteBatchIds(idList);
        return DeleteResultUtil.OK(result);
    }

    /**
     * sort保存
     *
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UpdateResult<List<SFunctionVo>> saveSort(List<SFunctionVo> data) {
        List<SFunctionVo> resultList = new ArrayList<>();
        // 乐观锁 dbversion
        for(SFunctionVo bean:data){
            UpdateResult updateResult = this.update(bean);
            if(updateResult.isSuccess()){
                SFunctionVo searchData = this.selectByid(bean.getId());
                resultList.add(searchData);
            } else {
                throw new UpdateErrorException("保存的数据已经被修改，请查询后重新编辑更新。");
            }
        }
        return UpdateResultUtil.OK(resultList);
    }
}

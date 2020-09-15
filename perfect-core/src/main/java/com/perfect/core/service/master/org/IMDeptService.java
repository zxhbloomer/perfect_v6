package com.perfect.core.service.master.org;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.perfect.bean.entity.master.org.MDeptEntity;
import com.perfect.bean.pojo.result.InsertResult;
import com.perfect.bean.pojo.result.UpdateResult;
import com.perfect.bean.vo.master.org.MDeptVo;

import java.util.List;

/**
 * <p>
 * 部门主表 服务类 接口
 * </p>
 *
 * @author zxh
 * @since 2019-08-23
 */
public interface IMDeptService extends IService<MDeptEntity> {
    /**
     * 获取列表，页面查询
     */
    IPage<MDeptVo> selectPage(MDeptVo searchCondition) ;

    /**
     * 获取所有数据
     */
    List<MDeptVo> select(MDeptVo searchCondition) ;

    /**
     * 获取所选id的数据
     */
    List<MDeptEntity> selectIdsIn(List<MDeptVo> searchCondition) ;

    /**
     * 获取所选id的数据，导出用
     */
    List<MDeptVo> selectIdsInForExport(List<MDeptVo> searchCondition) ;

    /**
     * 批量删除复原
     * @param searchCondition
     * @return
     */
    void deleteByIdsIn(List<MDeptVo> searchCondition);

    /**
     * 插入一条记录（选择字段，策略插入）
     * @param entity 实体对象
     * @return
     */
    InsertResult<Integer> insert(MDeptEntity entity);

    /**
     * 更新一条记录（选择字段，策略更新）
     * @param entity 实体对象
     * @return
     */
    UpdateResult<Integer> update(MDeptEntity entity);

    /**
     * 获取数据byid
     * @param id
     * @return
     */
    MDeptVo selectByid(Long id);
}

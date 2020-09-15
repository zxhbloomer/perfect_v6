package com.perfect.core.service.common;

import com.baomidou.mybatisplus.extension.service.IService;
import com.perfect.bean.vo.common.component.DictConditionVo;
import com.perfect.bean.vo.common.component.DictGroupVo;
import com.perfect.bean.vo.common.component.NameAndValueVo;
import com.perfect.bean.vo.common.component.PerfectComponentVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zxh
 * @since 2019-09-24
 */
public interface ICommonComponentService extends IService<NameAndValueVo> {

    /**
     * 获取所有的下拉选项的数据bean
     * @return
     */
    PerfectComponentVo getAllSelectComponentBean();

    /**
     * 下拉选项卡：删除类型字典
     * @return
     */
    List<NameAndValueVo> selectComponentDeleteMapNormal();

    /**
     * 下拉选项卡：删除类型字典，不包含删除
     * @return
     */
    List<NameAndValueVo> selectComponentDeleteMapOnlyUsedData();

    /**
     * 下拉选项卡：按参数查询
     * @return
     */
    List<NameAndValueVo> selectComponent(DictConditionVo condition);

    /**
     * 下拉选项卡，按组：按参数查询
     * @return
     */
    List<DictGroupVo> selectGroupComponent(DictConditionVo condition);

    /**
     * 下拉选项卡：按参数查询，含有filter
     * @return
     */
    List<NameAndValueVo> selectComponentFilter(DictConditionVo condition);

    /**
     * 根据字典类型，字典编码，获取字典值
     * @return
     */
    String getDictName(String code, String dict_value);
}

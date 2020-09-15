package com.perfect.bean.vo.common.component;

import com.perfect.bean.config.base.v1.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zxh
 * @date 2019/9/24
 */
@Data
@NoArgsConstructor
@ApiModel(value = "下拉选项的数据", description = "下拉选项的数据")
@EqualsAndHashCode(callSuper=false)
public class DictConditionVo extends BaseVo implements Serializable {
    private static final long serialVersionUID = 6697222826158984527L;

    /**
     * 显示的数据
     */
    private String name;

    /**
     * 对应的id
     */
    private String value;

    private String dict_type_code;
    private Long dict_data_id;

    /**
     * 查询参数
     */
    private String para;

    private String [] filter_para;
}

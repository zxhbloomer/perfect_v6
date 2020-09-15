package com.perfect.bean.vo.master.org;

import com.perfect.bean.config.base.v1.BaseVo;
import com.perfect.bean.vo.common.condition.PageCondition;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 岗位主表
 * </p>
 *
 * @author zxh
 * @since 2019-11-12
 */
@Data
@NoArgsConstructor
@ApiModel(value = "岗位主表", description = "岗位主表")
@EqualsAndHashCode(callSuper=false)
public class MPositionVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 3554363110752008984L;

    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 全称
     */
    private String name;

    /**
     * 简称
     */
    private String simple_name;

    /**
     * 说明
     */
    private String descr;

    /**
     * 是否删除
     */
    private Boolean is_del;
    private String is_del_name;

    /**
     * 租户id
     */
    private Long tenant_id;

    private Long c_id;
    private String c_name;

    private LocalDateTime c_time;

    private Long u_id;
    private String u_name;

    private LocalDateTime u_time;

    /**
     * 数据版本，乐观锁使用
     */
    private Integer dbversion;

    /**
     * 关联单号
     */
    private Long parent_serial_id;

    /**
     * 所属数据
     */
    private String group_full_name;
    private String group_full_simple_name;
    private String company_name;
    private String company_simple_name;
    private String dept_full_name;
    private String dept_full_simple_name;

    /**
     * 岗位下员工id
     */
    private Long staff_id;

    /**
     * 是否已经设置该岗位
     */
    private Boolean settled;

    /**
     * 该岗位向下，员工数量
     */
    private int staff_count;

    /**
     * 弹出框模式：空：普通模式；10：组织使用，需要排除已经选择的数据；
     */
    private String dataModel;

    /**
     * 换页条件
     */
    private PageCondition pageCondition;
}

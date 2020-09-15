package com.perfect.core.mapper.master.org;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perfect.bean.entity.master.org.MGroupEntity;
import com.perfect.bean.vo.master.org.MGroupVo;
import com.perfect.common.constant.PerfectDictConstant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 集团主表 Mapper 接口
 * </p>
 *
 * @author zxh
 * @since 2019-08-23
 */
@Repository
public interface MGroupMapper extends BaseMapper<MGroupEntity> {
    /**
     * 页面查询列表
     * @param page
     * @param searchCondition
     * @return
     */
    @Select("    "
        + " select t.*,                                                                                              "
        + "        c_staff.name as c_name,                                                                           "
        + "        u_staff.name as u_name,                                                                           "
        + "        vor.parent_group_name,                                                                            "
        + "        vor.parent_group_simple_name                                                                      "
        + "   from m_group t                                                                                         "
        + "  LEFT JOIN m_staff c_staff ON t.c_id = c_staff.id                                                        "
        + "  LEFT JOIN m_staff u_staff ON t.u_id = u_staff.id                                                        "
        + "  LEFT JOIN v_org_route_tenant_group_info vor on t.id = vor.current_id                                    "
        + "  where true                                                                                              "
        + "    and (t.code like CONCAT ('%',#{p1.code,jdbcType=VARCHAR},'%') or #{p1.code,jdbcType=VARCHAR} is null) "
        + "    and (t.name like CONCAT ('%',#{p1.name,jdbcType=VARCHAR},'%') or #{p1.name,jdbcType=VARCHAR} is null) "
        + "    and (t.is_del =#{p1.is_del,jdbcType=VARCHAR} or #{p1.is_del,jdbcType=VARCHAR} is null)                "
        + "    and (t.tenant_id =#{p1.tenant_id,jdbcType=BIGINT} or #{p1.tenant_id,jdbcType=BIGINT} is null)         "
        + "    and (t.id =#{p1.id,jdbcType=BIGINT} or #{p1.id,jdbcType=BIGINT} is null)                              "
        + "    and (                                                                                                 "
        + "       case when #{p1.dataModel,jdbcType=VARCHAR} = '"+ PerfectDictConstant.DICT_ORG_USED_TYPE_SHOW_UNUSED +"' then   "
        + "           not exists(                                                                                    "
        + "                     select 1                                                                             "
        + "                       from m_org subt1                                                                   "
        + "                      where subt1.serial_type = '"+ PerfectDictConstant.DICT_SYS_CODE_TYPE_M_GROUP +"'    "
        + "                        and t.id = subt1.serial_id                                                        "
        + "           )                                                                                              "
        + "       else true                                                                                          "
        + "       end                                                                                                "
        + "        )                                                                                                 "
        + "                                                                                                          ")
    IPage<MGroupVo> selectPage(Page page, @Param("p1") MGroupVo searchCondition);

    /**
     * 按条件获取所有数据，没有分页
     * @param searchCondition
     * @return
     */
    @Select("    "
        + "      select t.*,                                                                                                "
        + "             c_staff.name as c_name,                                                                             "
        + "             u_staff.name as u_name,                                                                             "
        + "             t2.label as is_del_name                                                                             "
        + "        from m_group t                                                                                           "
        + "   LEFT JOIN m_staff c_staff ON t.c_id = c_staff.id                                                              "
        + "   LEFT JOIN m_staff u_staff ON t.u_id = u_staff.id                                                              "
        + "   LEFT JOIN v_dict_info AS t2 ON t2.code = '" + PerfectDictConstant.DICT_SYS_DELETE_MAP + "' and t2.dict_value = cast(t.is_del as char(1))      "
        + "       where true                                                                                                "
        + "         and (t.code like CONCAT ('%',#{p1.code,jdbcType=VARCHAR},'%') or #{p1.code,jdbcType=VARCHAR} is null)   "
        + "         and (t.name like CONCAT ('%',#{p1.name,jdbcType=VARCHAR},'%') or #{p1.name,jdbcType=VARCHAR} is null)   "
        + "         and (t.is_del =#{p1.is_del,jdbcType=VARCHAR} or #{p1.is_del,jdbcType=VARCHAR} is null)                  "
        + "         and (t.tenant_id =#{p1.tenant_id,jdbcType=BIGINT} or #{p1.tenant_id,jdbcType=BIGINT} is null)           "
        + "         and (t.id =#{p1.id,jdbcType=BIGINT} or #{p1.id,jdbcType=BIGINT} is null)                                "
        + "      ")
    List<MGroupVo> select(@Param("p1") MGroupVo searchCondition);

    /**
     * 没有分页，按id筛选条件，导出
     * @param searchCondition
     * @return
     */
    @Select("   <script>   "
        + "     select t.*,                                                                                              "
        + "            c_staff.name as c_name,                                                                           "
        + "            u_staff.name as u_name,                                                                           "
        + "            t2.label as is_del_name                                                                           "
        + "       from m_group t                                                                                         "
        + "  LEFT JOIN m_staff c_staff ON t.c_id = c_staff.id                                                            "
        + "  LEFT JOIN m_staff u_staff ON t.u_id = u_staff.id                                                            "
        + "  LEFT JOIN v_dict_info AS t2 ON t2.code = '" + PerfectDictConstant.DICT_SYS_DELETE_MAP + "' and t2.dict_value = cast(t.is_del as char(1))      "
        + "      where true                                                                                              "
        + "        and (t.tenant_id = #{p2} or #{p2} is null  )                                                          "
        + "        and t.id in                                                                                           "
        + "        <foreach collection='p1' item='item' index='index' open='(' separator=',' close=')'>                  "
        + "             #{item.id}                                                                                       "
        + "        </foreach>                                                                                            "
        + "  </script>    ")
    List<MGroupVo> selectIdsInForExport(@Param("p1") List<MGroupVo> searchCondition, @Param("p2")Long tenant_id);

    /**
     * 没有分页，按id筛选条件
     * @param searchCondition
     * @return
     */
    @Select("   <script>   "
        + " select t.*                                                                                               "
        + "   from m_group t                                                                                         "
         + "  where true                                                                                              "
        + "    and (t.tenant_id = #{p2} or #{p2} is null  )                                                          "
        + "    and t.id in                                                                                           "
        + "        <foreach collection='p1' item='item' index='index' open='(' separator=',' close=')'>              "
        + "         #{item.id}                                                                                       "
        + "        </foreach>                                                                                        "
        + "  </script>    ")
    List<MGroupEntity> selectIdsIn(@Param("p1") List<MGroupVo> searchCondition, @Param("p2")Long tenant_id);

    /**
     * 按条件获取所有数据，没有分页
     * @param code
     * @return
     */
    @Select("    "
        + " select t.* "
        + "   from m_group t "
        + "  where true "
        + "    and t.code =  #{p1}   "
        + "    and (t.id  =  #{p2} or #{p2} is null)   "
        + "    and (t.id  <> #{p3} or #{p3} is null)   "
        + "    and (t.tenant_id  = #{p4} or #{p4} is null)   "
        + "    and t.is_del =  0   "
        + "      ")
    List<MGroupEntity> selectByCode(@Param("p1") String code, @Param("p2") Long equal_id, @Param("p3") Long not_equal_id, @Param("p4")Long tenant_id);

    /**
     * 按条件获取所有数据，没有分页
     * @param name
     * @return
     */
    @Select("    "
        + " select t.* "
        + "   from m_group t "
        + "  where true "
        + "    and t.name =  #{p1}   "
        + "    and (t.id  =  #{p2} or #{p2} is null)   "
        + "    and (t.id  <> #{p3} or #{p3} is null)   "
        + "    and (t.tenant_id  = #{p4} or #{p4} is null)   "
        + "    and t.is_del =  0   "
        + "      ")
    List<MGroupEntity> selectByName(@Param("p1") String name, @Param("p2") Long equal_id, @Param("p3") Long not_equal_id, @Param("p4")Long tenant_id);

    /**
     * 按条件获取所有数据，没有分页
     * @param name
     * @return
     */
    @Select("                                                                                                   "
        + "    select t.*                                                                                       "
        + "      from m_group t                                                                                 "
        + "     where true                                                                                      "
        + "       and t.simple_name =  #{p1}                                                                    "
        + "       and (t.id  =  #{p2} or #{p2} is null)                                                         "
        + "       and (t.id  <> #{p3} or #{p3} is null)                                                         "
        + "       and (t.tenant_id  = #{p4} or #{p4} is null)                                                   "
        + "       and t.is_del =  0                                                                             "
        + "                                                                                                     ")
    List<MGroupEntity> selectBySimpleName(@Param("p1") String name, @Param("p2") Long equal_id, @Param("p3") Long not_equal_id, @Param("p4")Long tenant_id);

    /**
     * 查询在组织架构中是否存在有被使用的数据
     * @param searchCondition
     * @return
     */
    @Select("                                                                                                   "
        + " select count(1)                                                                                     "
        + "   from m_org t                                                                                      "
        + "  where true                                                                                         "
        + "    and t.serial_type = '" + PerfectDictConstant.DICT_ORG_SETTING_TYPE_GROUP_SERIAL_TYPE + "'        "
        + "    and t.serial_id = #{p1.id,jdbcType=BIGINT}                                                       "
        + "                                                                                                     ")
    int isExistsInOrg(@Param("p1") MGroupEntity searchCondition);

    /**
     *
     * 根据id获取数据
     *
     * @param id
     * @return
     */
    @Select("    "
        + " select t.*,                                                                                              "
        + "        c_staff.name as c_name,                                                                           "
        + "        u_staff.name as u_name                                                                            "
        + "   from m_group t                                                                                         "
        + "  LEFT JOIN m_staff c_staff ON t.c_id = c_staff.id                                                        "
        + "  LEFT JOIN m_staff u_staff ON t.u_id = u_staff.id                                                        "
        + "  where true                                                                                              "
        + "    and (t.id =#{p1} )                                                                                    "
        + "      ")
    MGroupVo selectId(@Param("p1") Long id);
}

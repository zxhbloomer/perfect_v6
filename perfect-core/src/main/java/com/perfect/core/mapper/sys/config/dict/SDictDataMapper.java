package com.perfect.core.mapper.sys.config.dict;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perfect.bean.entity.sys.config.dict.SDictDataEntity;
import com.perfect.bean.vo.sys.config.dict.SDictDataVo;
import com.perfect.common.constant.PerfectDictConstant;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author zxh
 * @since 2019-08-23
 */
@Repository
public interface SDictDataMapper extends BaseMapper<SDictDataEntity> {

    String common_select = "  "
        + "  SELECT                                                                 "
        + "       t1.id,                                                            "
        + "       t1.dict_type_id,                                                  "
        + "       t1.sort,                                                          "
        + "       t1.label,                                                         "
        + "       t1.dict_value ,                                                   "
        + "       t1.descr,                                                         "
        + "       t1.is_del,                                                        "
        + "       t1.c_id,                                                          "
        + "       t1.c_time,                                                        "
        + "       t1.u_id,                                                          "
        + "       t1.u_time,                                                        "
        + "       t1.extra1,                                                        "
        + "       t1.extra2,                                                        "
        + "       t1.extra3,                                                        "
        + "       t1.extra4,                                                        "
        + "       t1.dbversion,                                                     "
        + "       t2.name  dictTypeName ,                                           "
        + "       t2.code  dictTypeCode,                                            "
        + "       t2.descr dict_type_descr,                                         "
        + "       t2.is_del dictTypeIsdel,                                          "
        + "       t3.max_sort,                                                      "
        + "       t3.min_sort,                                                      "
        + "       t1.dict_value as table_name,                                      "
        + "       t1.label as table_comment,                                        "
        + "       t1.extra1 as column_name,                                         "
        + "       t1.extra2 as column_comment                                       "
        + "  FROM                                                                   "
        + "  	s_dict_data AS t1                                                   "
        + "  	LEFT JOIN s_dict_type AS t2 ON t1.dict_type_id = t2.id              "
        + "  	INNER JOIN (                                                        "
        + "  		SELECT                                                          "
        + "  			count(1) - 1 AS max_sort,                                   "
        + "  			0 AS min_sort,                                              "
        + "  			subt1.dict_type_id                                          "
        + "  		FROM                                                            "
        + "  			s_dict_data subt1                                           "
        + "  		GROUP BY                                                        "
        + "  			subt1.dict_type_id                                          "
        + "  	) t3 ON t1.dict_type_id = t3.dict_type_id                           "
        + "                                                                         "
        ;

    /**
     * 页面查询列表
     * @param page
     * @param searchCondition
     * @return
     */
    @Select("    "
        + common_select
        + "  where true "
        + "    and (t2.code like CONCAT ('%',#{p1.dictTypeCode,jdbcType=VARCHAR},'%') or #{p1.dictTypeCode,jdbcType=VARCHAR} is null) "
        + "    and (t2.name like CONCAT ('%',#{p1.dictTypeName,jdbcType=VARCHAR},'%') or #{p1.dictTypeName,jdbcType=VARCHAR} is null) "
        + "    and (t1.label like CONCAT ('%',#{p1.label,jdbcType=VARCHAR},'%') or #{p1.label,jdbcType=VARCHAR} is null) "
        + "    and (t2.is_del =#{p1.is_del,jdbcType=VARCHAR} or #{p1.is_del,jdbcType=VARCHAR} is null) "
        + "      ")
    IPage<SDictDataVo> selectPage(Page page, @Param("p1") SDictDataVo searchCondition );

    /**
     * 按条件获取所有数据，没有分页
     * @param searchCondition
     * @return
     */
    @Select("    "
        + common_select
        + "  where true "
        + "    and (t2.code like CONCAT ('%',#{p1.dictTypeCode,jdbcType=VARCHAR},'%') or #{p1.dictTypeCode,jdbcType=VARCHAR} is null) "
        + "    and (t2.name like CONCAT ('%',#{p1.dictTypeName,jdbcType=VARCHAR},'%') or #{p1.dictTypeName,jdbcType=VARCHAR} is null) "
        + "    and (t1.label like CONCAT ('%',#{p1.label,jdbcType=VARCHAR},'%') or #{p1.label,jdbcType=VARCHAR} is null) "
        + "    and (t2.is_del =#{p1.is_del,jdbcType=VARCHAR} or #{p1.is_del,jdbcType=VARCHAR} is null) "
        + "      ")
    List<SDictDataVo> select(@Param("p1") SDictDataVo searchCondition );

    /**
     * 没有分页，按id筛选条件
     * @param searchCondition
     * @return
     */
    @Select("    <script>    "
        + common_select
        + "  where t.id in "
        + "        <foreach collection='p1' item='item' index='index' open='(' separator=',' close=')'>    "
        + "         #{item.id}  "
        + "        </foreach>    "
        + "  </script>    ")
    List<SDictDataVo> selectIdsIn(@Param("p1") List<SDictDataVo> searchCondition );

    /**
     * 按条件获取所有数据，没有分页
     * @param dict_value
     * @return
     */
    @Select("    "
        + " select t.* "
        + "   from s_dict_data t "
        + "  where true "
        + "    and t.dict_value =  #{p1}  "
        + "    and t.dict_type_id =  #{p2}  "
        + "    and (t.id  =  #{p3} or #{p2} is null)   "
        + "    and (t.id  <> #{p4} or #{p3} is null)   "
        + "    and t.is_del =  0   "
        + "      ")
    List<SDictDataEntity> selectByDictValue(@Param("p1") String dict_value, @Param("p2") Long dict_type_id, @Param("p3") Long equal_id, @Param("p4") Long not_equal_id);

    /**
     * 按条件获取所有数据，没有分页
     * @param label
     * @return
     */
    @Select("    "
        + " select t.* "
        + "   from s_dict_data t "
        + "  where true "
        + "    and t.label =  #{p1}                    "
        + "    and t.dict_type_id =  #{p2}             "
        + "    and (t.id  =  #{p3} or #{p2} is null)   "
        + "    and (t.id  <> #{p4} or #{p3} is null)   "
        + "    and t.is_del =  0   "
        + "      ")
    List<SDictDataEntity> selectByLabel(@Param("p1") String label, @Param("p2") Long dict_type_id, @Param("p3") Long equal_id, @Param("p4") Long not_equal_id);

    /**
     * 按条件获取所有数据，没有分页
     * @param id
     * @return
     */
    @Select("    "
        + common_select
        + "  where t1.id =  #{p1}"
        + "      ")
    SDictDataVo selectId(@Param("p1") Long id);

    /**
     * 获取排序最大序号
     */
    @Select("    "
        + "   SELECT  "
        + "     (MAX(IFNULL(t.sort, 0)) + 1) AS sort "
        + "     FROM s_dict_data t "
        + "    WHERE t.dict_type_id =  #{p1}"
        + " GROUP BY t.dict_type_id"
        + "      ")
    SDictDataEntity getSortNum(@Param("p1") Long dict_type_id);

    /**
     * 页面查询列表
     * @param searchCondition
     * @return
     */
    @Select("    "
            + common_select
            + "  where true "
            + "    and (t2.code like CONCAT ('%',#{p1.dictTypeCode,jdbcType=VARCHAR},'%') or #{p1.dictTypeCode,jdbcType=VARCHAR} is null) "
            + "    and (t2.name like CONCAT ('%',#{p1.dictTypeName,jdbcType=VARCHAR},'%') or #{p1.dictTypeName,jdbcType=VARCHAR} is null) "
            + "    and (t1.dict_value = #{p1.table_name,jdbcType=VARCHAR} or #{p1.table_name,jdbcType=VARCHAR} is null) "
            + "    and (t2.is_del =#{p1.is_del,jdbcType=VARCHAR} or #{p1.is_del,jdbcType=VARCHAR} is null) "
            + "    and (t2.code = '" + PerfectDictConstant.DICT_SYS_TABLE_TYPE + "') "
            + "   order by t1.sort             ")
    List<SDictDataVo> selectColumnComment(@Param("p1") SDictDataVo searchCondition );
}

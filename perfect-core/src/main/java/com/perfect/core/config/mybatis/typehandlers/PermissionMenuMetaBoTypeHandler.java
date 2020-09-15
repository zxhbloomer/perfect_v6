package com.perfect.core.config.mybatis.typehandlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.perfect.bean.bo.session.user.rbac.PermissionMenuMetaBo;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: JsonHandler
 * @Description: mybatis处理JSON array类型
 * @Author: zxh
 * @date: 2020/4/13
 * @Version: 1.0
 */
@MappedTypes(value = {PermissionMenuMetaBo.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR}, includeNullJdbcType = true)
public class PermissionMenuMetaBoTypeHandler <T> extends BaseTypeHandler<T> {

    private Class<T> clazz;

    public PermissionMenuMetaBoTypeHandler(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.clazz = clazz;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, T parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,
            JSON.toJSONString(parameter,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.QuoteFieldNames,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.WriteClassName)
        );
    }

    @Override
    public T getNullableResult(ResultSet resultSet, String s) throws SQLException {
        try {
            return getObjcetByJson(resultSet.getString(s));
        } catch (IllegalAccessException e) {
            throw new SQLException(e);
        } catch (InstantiationException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public T getNullableResult(ResultSet resultSet, int i) throws SQLException {
        try {
            return getObjcetByJson(resultSet.getString(i));
        } catch (IllegalAccessException e) {
            throw new SQLException(e);
        } catch (InstantiationException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public T getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        try {
            return getObjcetByJson(callableStatement.getString(i));
        } catch (IllegalAccessException e) {
            throw new SQLException(e);
        } catch (InstantiationException e) {
            throw new SQLException(e);
        }
    }

    private T getObjcetByJson(String content) throws IllegalAccessException, InstantiationException {
        T rtn = JSON.parseObject(content,clazz);
        return rtn;
    }
}

package net.jeeshop.biz.member.typehandler;

import net.jeeshop.biz.member.enums.GenderType;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author dylan
 * @date 2016-04-04 21-36
 */
public class GenderTypeTypeHandler extends BaseTypeHandler<GenderType> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, GenderType genderType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(genderType.getValue()));
    }

    @Override
    public GenderType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return parseType(resultSet.getString(s));
    }

    private GenderType parseType(String s) {

        if (StringUtils.isBlank(s)) {
            return null;
        }
        int value = Integer.parseInt(s);
        return GenderType.parseValue(value);
    }

    @Override
    public GenderType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return parseType(resultSet.getString(i));
    }

    @Override
    public GenderType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseType(callableStatement.getString(i));
    }
}

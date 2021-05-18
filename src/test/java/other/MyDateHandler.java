package org.jeecg.modules.system.mapper;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author zhangbin
 * @version 1.0.0
 * @ClassName MyDateHandler.java
 * @Description TODO
 * @createTime 2020-09-22 20:01:10
 */
public class MyDateHandler implements TypeHandler<Long> {

	/*
	 * <result property="activateTime" column="N_ACTIVATETIME"  typeHandler="MyDateHandler"/>
	 * */
	@Override
	public void setParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
		// not support
		throw new UnsupportedOperationException("unSupport set value");
	}

	@Override
	public Long getResult(ResultSet rs, String columnName) throws SQLException {
		String string = rs.getString(columnName);
		return this.parse2Long(string);

	}

	private long parse2Long(String string) throws SQLException {
		if (StringUtils.isNumeric(string)) {
			return Long.valueOf(string);
		}
		try {
			Date date = DateUtils.parseDate(string, "yyyy-MM-dd HH:mm:ss");
			return date.getTime();
		} catch (ParseException e) {
			throw new SQLException(e);
		}
	}

	@Override
	public Long getResult(ResultSet rs, int columnIndex) throws SQLException {
		String string = rs.getString(columnIndex);
		return this.parse2Long(string);
	}

	@Override
	public Long getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String string = cs.getString(columnIndex);
		return this.parse2Long(string);
	}
}

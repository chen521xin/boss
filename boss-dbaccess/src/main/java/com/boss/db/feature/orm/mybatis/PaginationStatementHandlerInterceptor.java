/*###################################################################
 *#                                                                 #       
 *#                          Copyright(c) 2017 by                   #
 *#              Shanghai stock Exchange(SSE),shanghai,China        #
 *#                          All rights reserved.                   #
 *#                                                                 #
 *###################################################################
 */
package com.boss.db.feature.orm.mybatis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boss.db.feature.orm.dialect.AbstractDialect;
import com.boss.db.feature.orm.dialect.DialectFactory;

/**
 * @description
 * @data 2017年10月24日下午7:07:29
 * @author Administrator
 * @version v1.0
 * @since v1.0
 *
 **/
@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PaginationStatementHandlerInterceptor implements Interceptor {
	private final static Logger logger = LoggerFactory.getLogger(PaginationStatementHandlerInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		ParameterHandler parameterHandler = statementHandler.getParameterHandler();
		BoundSql boundSql = statementHandler.getBoundSql();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
		AbstractDialect dialect = DialectFactory.buildDialect(configuration);
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		Page<?> page = (Page<?>) rowBounds;
		String countSql = dialect.getCountString(originalSql);
		Connection connection = (Connection) invocation.getArgs()[0];
		int total = getTotal(parameterHandler, connection, countSql);
		page.setTotalCount(total);
		metaStatementHandler.setValue("delegate.boundSql.sql",
				dialect.getLimitSring(originalSql, page.getOffset(), page.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
		if (logger.isDebugEnabled()) {
			logger.debug("分页SQL：" + boundSql.getSql());
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	public int getTotal(ParameterHandler parameterHandler, Connection conn, String countSql) throws SQLException {
		PreparedStatement pre = conn.prepareStatement(countSql);
		parameterHandler.setParameters(pre);
		ResultSet rs = pre.executeQuery();
		int count = 0;
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pre.close();
		return count;
	}

}

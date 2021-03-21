package com.devcortes.reservation_service.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.beans.PropertyVetoException;

@Configuration
@MapperScan("com.mybookingpal.dataaccesslayer.mappers")
public class BookingpalDataSourceConfiguration {

	@Value("${mbp.bp.db.driverClass}")
	private String driver;

	@Value("${mbp.bp.db.url}")
	private String url;

	@Value("${mbp.bp.db.user}")
	private String user;

	@Value("${mbp.bp.db.password}")
	private String password;

	@Value("${mbp.bp.db.maxPoolSize}")
	private String maxPoolSize;

	@Value("${mbp.bp.db.preferredTestQuery}")
	private String preferredTestQuery;

	@Value("${mbp.bp.db.testConnectionOnCheckin}")
	private String testConnectionOnCheckin;

	@Value("${mbp.bp.db.idleConnectionTestPeriod}")
	private String idleConnectionTestPeriod;

	@Value("${mbp.bp.db.debugUnreturnedConnectionStackTraces}")
	private String debugUnreturnedConnectionStackTraces;

	@Value("${mbp.bp.db.unreturnedConnectionTimeout}")
	private String unreturnedConnectionTimeout;

	@Bean(name = "mbpDataSource")
	public ComboPooledDataSource mbpDataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(driver);
		dataSource.setJdbcUrl(url);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setMaxPoolSize(NumberUtils.createInteger(maxPoolSize));
		dataSource.setPreferredTestQuery(preferredTestQuery);
		dataSource.setTestConnectionOnCheckin(BooleanUtils.toBooleanObject(testConnectionOnCheckin));
		dataSource.setIdleConnectionTestPeriod(NumberUtils.createInteger(idleConnectionTestPeriod));
		dataSource.setDebugUnreturnedConnectionStackTraces(BooleanUtils.toBooleanObject(debugUnreturnedConnectionStackTraces));
		dataSource.setUnreturnedConnectionTimeout(NumberUtils.createInteger(unreturnedConnectionTimeout));
		return dataSource;
	}

	@Bean(name = "mbpTransactionManager")
	public DataSourceTransactionManager mbpTransactionManager(@Qualifier("mbpDataSource") final ComboPooledDataSource dataSource) throws PropertyVetoException {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "mbpSqlSessionFactory")
	public SqlSessionFactoryBean mbpSqlSessionFactory(@Qualifier("mbpDataSource") final ComboPooledDataSource dataSource) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setCacheEnabled(true);
		configuration.setLazyLoadingEnabled(false);
		configuration.setMultipleResultSetsEnabled(true);
		configuration.setUseColumnLabel(true);
		configuration.setUseGeneratedKeys(true);
		configuration.setDefaultExecutorType(ExecutorType.REUSE);
		configuration.setDefaultStatementTimeout(600);
		sessionFactory.setConfiguration(configuration);
		sessionFactory.setTypeAliasesPackage("com/mybookingpal/dataaccesslayer/entity");
		sessionFactory.setTypeHandlersPackage("com/mybookingpal/dataaccesslayer/handlers");
		return sessionFactory;
	}
}


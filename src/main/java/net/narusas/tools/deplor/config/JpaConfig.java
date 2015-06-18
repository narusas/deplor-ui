package net.narusas.tools.deplor.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "net.narusas.tools.deplor")
public class JpaConfig {
	@Autowired
	DataSource	dataSource;

	@Autowired
	Properties	jpaProperties;

	@Bean
	public JpaVendorAdapter jpaVendorAdopter() {
		EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
		// vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setShowSql(true);
		return vendorAdapter;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdopter());
		factory.setJpaProperties(jpaProperties);
		factory.setPackagesToScan("net.narusas.tools.deplor");
		// factory.setMappingResources("META-INF/orm.xml");
		factory.setDataSource(dataSource);
		factory.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public SharedEntityManagerBean entityManager() {
		SharedEntityManagerBean em = new SharedEntityManagerBean();
		em.setEntityManagerFactory(entityManagerFactory());
		return em;
	}

	@Bean
	public PersistenceExceptionTranslator persistenceExceptionTranslator() {
		return new EclipseLinkJpaDialect();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public Properties jpaCommonProperties() {
		Properties props = new Properties();
		// eclipselink 가 slf4j 를 사용하게 설정함
		props.setProperty("eclipselink.logging.logger", "framework.persistent.jpa.eclipselink.SLF4JLogger");

		// eclipselink 2nd cache 활성화를 설정함
		props.setProperty("eclipselink.cache.shared.default", "false");

		// props.setProperty("eclipselink.allow-zero-id", "true");
		return props;
	}

	@Bean(destroyMethod = "close")
	public javax.sql.DataSource dataSource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:8889/deplor?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull");
		ds.setUsername("deplor");
		ds.setPassword("deplor");
		ds.setInitSQL("select 1");
		ds.setMaxActive(400);
		ds.setValidationQuery("select 1");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(true);
		ds.setJmxEnabled(true);
		return (javax.sql.DataSource) ds;
	}

	@Bean
	public Properties jpaProperties(Properties jpaCommonProperties) {
		// Eclipselink loadtime weaving 을 disable 시킨다. 기본값은 true 이다.
		jpaCommonProperties.setProperty("eclipselink.weaving", "false");
		return jpaCommonProperties;
	}

}

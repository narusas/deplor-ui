package net.narusas.tools.deplor.config;

import lombok.extern.slf4j.Slf4j;
import net.narusas.tools.deplor.crowler.SvnCrowler;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

//@formatter:off
@Configuration
@EnableTransactionManagement

@Import({
		// 데이터 접근 관련
		JpaConfig.class
})
@ComponentScan(basePackages = { "net.narusas.tools.deplor" })

@Slf4j
@Repository
//@formatter:on
public class ApplicationConfig {
	public ApplicationConfig() {

		// Optionally remove existing handlers attached to j.u.l root logger
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // (since SLF4J 1.6.5)

		// add SLF4JBridgeHandler to j.u.l's root logger, should be done once
		// during
		// the initialization phase of your application
		SLF4JBridgeHandler.install();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public LocalValidatorFactoryBean localValidator() {
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		return factoryBean;
	}
}

package de.andrena.et14.spring.persistenz.springconfig;

import static java.util.Collections.singletonMap;
import static org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO;

import org.h2.Driver;
import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import de.andrena.et14.spring.persistenz.CustomClasspathScanningPersistenceUnitPostProcessor;

@Configuration
@EnableTransactionManagement
public class PersistenzSpringConfig {

	@Bean
	public JpaTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setPersistenceUnitManager(persistenceUnitManager());
		entityManagerFactoryBean.setDataSource(dataSource());
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabasePlatform(H2Dialect.class.getName());
		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactoryBean.setJpaPropertyMap(singletonMap(HBM2DDL_AUTO, "create"));
		return entityManagerFactoryBean;
	}

	@Bean
	public DefaultPersistenceUnitManager persistenceUnitManager() {
		DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
		persistenceUnitManager.setPackagesToScan();
		persistenceUnitManager.setDefaultDataSource(dataSource());
		CustomClasspathScanningPersistenceUnitPostProcessor persistenceUnitPostProcessor = new CustomClasspathScanningPersistenceUnitPostProcessor();
		persistenceUnitPostProcessor.setBasePackage("de.andrena.et14.spring");
		persistenceUnitManager.setPersistenceUnitPostProcessors(persistenceUnitPostProcessor);
		return persistenceUnitManager;
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Driver.class.getName());
		dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}

	@Bean
	public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
		return new PersistenceAnnotationBeanPostProcessor();
	}
}

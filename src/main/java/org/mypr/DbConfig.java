package org.mypr;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.mypr.model.Person;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableJpaRepositories(basePackages = {"org.mypr.dao"})
@EntityScan(basePackages = {"org.mypr.model"})
public class DbConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource
				.setUrl("jdbc:postgresql://localhost:5432/Subjektid?autoReconnect=true");
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect",
				"org.hibernate.dialect.PostgreSQLDialect");
		dataSource.setConnectionProperties(properties);
		dataSource.setUsername("postgres");
		dataSource.setPassword("TereHommikust");
		return dataSource;
	}

	@Bean
	public Person DataService() {
		return new Person();
	}
}

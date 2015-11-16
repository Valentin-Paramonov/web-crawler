package paramonov.valentine.web_crawler.site_checker;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "paramonov.valentine.web_crawler")
class PersistenceConfig {
    @Configuration
    @PropertySource("classpath:persistence.properties")
    static class PersistenceProps {
    }

    @Bean(destroyMethod = "close")
    @Autowired
    DataSource dataSource(Environment env) {
        return new HikariDataSource(dataSourceConfig(env));
    }

    private HikariConfig dataSourceConfig(Environment env) {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getRequiredProperty("db.driver"));
        dataSourceConfig.setJdbcUrl(env.getRequiredProperty("db.url"));
        dataSourceConfig.setUsername(env.getRequiredProperty("db.userName"));
        dataSourceConfig.setPassword(env.getRequiredProperty("db.password"));
        return dataSourceConfig;
    }

    @Bean
    @Autowired
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("paramonov.valentine.web_crawler");
        entityManagerFactoryBean.setJpaProperties(jpaProps(env));
        return entityManagerFactoryBean;
    }

    private Properties jpaProps(Environment env) {
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        return jpaProperties;
    }

    @Bean
    @Autowired
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

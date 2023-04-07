package ru.hh.school.checkupextension.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.PostgreSQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateConfig {

    @Value("${database_url}")
    private String databaseUrl;

    @Value("${database_username}")
    private String databaseUsername;

    @Value("${database_password}")
    private String databasePassword;

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(databaseUrl, databaseUsername, databasePassword);
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.hh.school.checkupextension.test");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public SessionFactory sessionFactory(LocalSessionFactoryBean localSessionFactoryBean) {
        return localSessionFactoryBean.getObject();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        hibernateProperties.put(Environment.DIALECT, PostgreSQLDialect.class.getName());
        hibernateProperties.put(Environment.HBM2DDL_AUTO, "update");

        return hibernateProperties;
    }
}

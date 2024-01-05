// package com.example.sistlabsolos.config;

// import org.hibernate.dialect.PostgreSQL10Dialect;
// import org.hibernate.dialect.PostgreSQLDialect;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.springframework.jdbc.datasource.DriverManagerDataSource;
// import org.springframework.orm.jpa.JpaTransactionManager;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;

// import jakarta.activation.DataSource;
// import jakarta.persistence.EntityManagerFactory;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;


// @Configuration
// @EnableTransactionManagement
// public class PersistenceJpaConfig {

//     @Autowired
//     private DataSource dataSource;
//     @Bean
//     public DataSourceTransactionManager transactionManager(DataSource dataSource) {
//       return new DataSourceTransactionManager();
//     }
    
// }

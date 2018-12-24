package com.foobar;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "barEntityManagerFactory",
    transactionManagerRef = "barTransactionManager", basePackages = {"com.foobar.*"})

public class BarDbConfig {

  @Bean(name = "barDataSource")
  @ConfigurationProperties(prefix = "bar.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().driverClassName("oracle.jdbc.driver.OracleDriver").url("jdbc:oracle:thin:@10.40.0.240:1521/surenadb").build();
  }

  @Bean(name = "barEntityManagerFactory")
  public LocalContainerEntityManagerFactoryBean barEntityManagerFactory(
      EntityManagerFactoryBuilder builder, @Qualifier("barDataSource") DataSource dataSource) {
    return builder.dataSource(dataSource).packages("com.foobar.bar.domain").persistenceUnit("bar")
        .build();
  }

  @Bean(name = "barTransactionManager")
  public PlatformTransactionManager barTransactionManager(
      @Qualifier("barEntityManagerFactory") EntityManagerFactory barEntityManagerFactory) {
    JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
    jpaTransactionManager.setJpaProperties(jpaHibernateProperties());
    return jpaTransactionManager;
  }
  private Properties jpaHibernateProperties() {

    Properties properties = new Properties();

    properties.put("spring.jpa.hibernate.ddl-auto", "update");
    properties.put("spring.jpa.show-sql", "true");


    return properties;
  }
}

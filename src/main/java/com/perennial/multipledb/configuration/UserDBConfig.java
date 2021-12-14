package com.perennial.multipledb.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryBean",
        basePackages = "com.perennial.multipledb.user.repo",
        transactionManagerRef = "mySqltransactionManager")
@Profile("dev")
public class UserDBConfig {
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "db1.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder){
      /*
        return  builder.dataSource(dataSource).packages("com.perennial.multipledb.model.user")
                .persistenceUnit("User").build();
*/
        HashMap<String,Object> properties=new HashMap<>();
        return builder.dataSource(dataSource())
                .packages("com.perennial.multipledb.model.user")
                .properties(properties).build();
            }
    @Primary
    @Bean
    public PlatformTransactionManager mySqltransactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
            return new JpaTransactionManager(entityManagerFactory);
    }
}

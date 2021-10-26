
package com.sabitov.conf;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@ComponentScan("com.sabitov")
@PropertySource("classpath:application.properties")
public class ApplicationConfig {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setPassword(environment.getProperty("db.password"));
        config.setUsername(environment.getProperty("db.user"));
        config.setDriverClassName(environment.getProperty("db.driver"));
        config.setJdbcUrl(environment.getProperty("db.url"));
        config.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.hikariPoolSize")));
        DataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}


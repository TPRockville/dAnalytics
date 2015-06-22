package org.jderive;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Durga on 6/20/2015.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.jderive"})
@EntityScan(basePackages = "org.jderive.domain")
public class JDeriveApplication {

    @Autowired
    @Getter
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory sessionFactory() {
        if (this.entityManagerFactory != null) {
            return this.entityManagerFactory.unwrap(SessionFactory.class);
        } else {
            throw new IllegalStateException("DB Connectivity failed. check the configuration.");
        }
    }

    public static void main(String... args) {
        SpringApplication.run(JDeriveApplication.class, args);
    }
}

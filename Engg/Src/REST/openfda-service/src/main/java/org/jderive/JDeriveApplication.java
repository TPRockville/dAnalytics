package org.jderive;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.jderive.domain.DrugOnlyMonthSummaryDomain;
import org.jderive.filter.CorsFilter;
import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
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
@EnableJSONDoc
@EnableCaching
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

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(DrugOnlyMonthSummaryDomain.EVENT_COUNT_CACHE);
    }

    /*@Bean
    public CacheManager drugCacheManager() {
        return new ConcurrentMapCacheManager(DrugOnlyMonthSummaryDomain.ALL_DRUG_CACHE);
    }*/


    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    public static void main(String... args) {
        SpringApplication.run(JDeriveApplication.class, args);
    }
}

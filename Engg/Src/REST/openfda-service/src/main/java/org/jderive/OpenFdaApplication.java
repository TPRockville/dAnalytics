package org.jderive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Durga on 6/20/2015.
 */

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"org.jderive"})
@EntityScan(basePackages = "org.jderive.domain")
public class OpenFdaApplication {

    public static void main(String ... args) {
        SpringApplication.run(OpenFdaApplication.class, args);
    }

    public void dummy() {
        //dummy method.
    }
}

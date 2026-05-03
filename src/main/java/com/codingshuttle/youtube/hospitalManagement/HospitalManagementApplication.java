package com.codingshuttle.youtube.hospitalManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class HospitalManagementApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(HospitalManagementApplication.class, args);
        Environment environment = configurableApplicationContext.getEnvironment();
    }

}

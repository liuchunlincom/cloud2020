package com.cennavi.cloud.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Rain
 * @Date 2020/10/22 10:56
 **/

@EnableDiscoveryClient
@SpringBootApplication
public class SeataAccountMain {
    public static void main(String[] args) {
        SpringApplication.run(SeataAccountMain.class, args);
    }
}


package com.cennavi.cloud.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Rain
 * @Date 2020/10/22 12:23
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class StorageMain {
    public static void main(String[] args) {
        SpringApplication.run(StorageMain.class, args);
    }
}

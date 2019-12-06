package io.github.tlideacreator.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试应用
 * @author lqh
 */

@SpringBootApplication
@ComponentScan(basePackages = {"io.github.tlideacreator"})
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}

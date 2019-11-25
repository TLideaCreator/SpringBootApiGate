package org.idea.creator.demo;

import org.idea.creator.api.gate.annotation.GateScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试应用
 * @author lqh
 */

@SpringBootApplication
@GateScan(path={"org.idea.creator.demo.gates"})
@ComponentScan({"org.idea.creator.api.gate","org.idea.creator.demo"})
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}

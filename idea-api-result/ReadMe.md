# api 返回值
## 使用方法
### 添加拦截器
```
package com.example.demo;

import org.idea.creator.api.gate.interceptor.GateInterceptor;
import org.idea.creator.api.result.interceptor.ResultInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GateInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new ResultInterceptor()).addPathPatterns("/**");
    }
}

```

### 添加包扫描
```$java
package org.idea.creator.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 测试应用
 * @author lqh
 */

@SpringBootApplication
@ComponentScan(basePackages = {"org.idea.creator"})
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}

```

### controller添加注解
```
package com.example.demo;


import org.idea.creator.api.gate.annotation.GateKeeper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@GateKeeper(gates = {"auth"}) //注册全部controller都要通过的验证
public class TestCtrl {
    @GetMapping("/")   
    //相应结果返回"auth"
    public String auth() {
        return "auth";
    }


    @GetMapping("/test")
    @GateKeeper(gates = {"test"})
    //需要单独使用的gate可以注册在单独接口上,但是test返回false此接口报错
    public String test() {
        return "test";
    }
}

```
# api 门卫
## 使用方法
### 主程序入口
```
package org.idea.creator.demo;

import org.idea.creator.api.gate.annotation.GateScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 测试应用
 * @author lqh
 */

@SpringBootApplication
@GateScan(path={"org.idea.creator.demo.gates"})  //过滤方法所在目录
public class DemoApp {
    public static void main(String[] args) {
        SpringApplication.run(DemoApp.class, args);
    }
}

```

### 添加拦截器

```
package com.example.demo;

import org.idea.creator.api.gate.interceptor.GateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GateInterceptor()).addPathPatterns("/**");
    }
}

```


### 配置文件中

```
context:
  listener:
    classes: org.idea.creator.api.gate.GateRunner
```


### 创建gate

```
package com.example.demo.gates;


import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Gate(value = "auth")
public class AuthGate implements IGateInterface {
    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }
}
```
```
package com.example.demo.gates;


import org.idea.creator.api.gate.IGateInterface;
import org.idea.creator.api.gate.annotation.Gate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Gate(value = "test")
public class TestGate implements IGateInterface {
    @Override
    public boolean handler(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }
}

```

### 在controller中使用

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

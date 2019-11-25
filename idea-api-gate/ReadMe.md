# api 门卫
## 使用方法

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
package org.idea.creator.demo;

import org.idea.creator.api.gate.annotation.GateKeeper;
import org.idea.creator.api.result.annotation.ResultResponse;
import org.idea.creator.api.result.result.ResultEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 * @author lqh
 */
@RestController
@GateKeeper(gates={"auth"})
@ResultResponse
public class TestCtrl {
    @GetMapping("/test")
    @GateKeeper(gates={"test"})
    public String test(){
      return "test";
    }

    @GetMapping("/hello")
    public ResultEnum hello(){
        return ResultEnum.STRING.setData("hello");
    }

}


```

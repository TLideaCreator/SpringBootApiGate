package io.github.tlideacreator.demo;

import io.github.tlideacreator.api.gate.annotation.GateKeeper;
import io.github.tlideacreator.api.result.annotation.ResultResponse;
import io.github.tlideacreator.api.result.result.ResultEnum;
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

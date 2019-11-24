package org.idea.creator.demo;

import org.idea.creator.api.gate.annotation.GateKeeper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@GateKeeper(gates={"auth"})
public class TestCtrl {
    @GetMapping("/test")
    @GateKeeper(gates={"test"})
    public String test(){
      return "test";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}

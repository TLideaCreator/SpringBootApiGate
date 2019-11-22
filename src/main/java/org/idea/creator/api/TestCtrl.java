package org.idea.creator.api;

import org.idea.creator.api.gate.annotation.GateKeeper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lqh
 */
@RestController
@GateKeeper(gates = {"Auth"})
public class TestCtrl {
    @GateKeeper(gates = {"test"})
    @GetMapping("/test")
    public String test(){
        return "test";
    }


    @GetMapping("/hello")
    public String hello(){
        return "hello gate";
    }
}

package io.github.tlideacreator.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author lqh
 */
@Service
public class TestService {
    public boolean randStatus(){
        return Math.random()*10 % 4 > 1;
    }
}

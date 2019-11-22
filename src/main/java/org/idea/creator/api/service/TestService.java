package org.idea.creator.api.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author lqh
 */
@Service
public class TestService {
    public boolean check(){
        Random r = new Random();
        r.nextInt();
        return r.nextInt() % 3 == 0;
    }
}

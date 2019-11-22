package org.idea.creator.api;

import org.idea.creator.api.gate.annotation.GateScan;
import org.idea.creator.api.gate.config.GateUnit;
import org.idea.creator.api.gate.exception.GateException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author lqh
 */
@SpringBootApplication
@GateScan(path = {"org.idea.creator.api.gates"})
public class IdeaApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdeaApiDemoApplication.class, args);
	}

}

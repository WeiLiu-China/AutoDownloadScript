package context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright 2004-2020 by xdja.com  All rights reserved.
 *
 * @author xxx
 * @version 1.0.0
 * @ClassName BeanConfig.java
 * @Description TODO
 * @createTime 2020/10/27 08:58:56
 */
@Configuration
public class BeanConfig {
	@Bean(name="testDemo")
	public Demo generateDemo() {
		Demo demo = new Demo();
		demo.setId(12345);
		demo.setName("test");
		return demo;
	}
}
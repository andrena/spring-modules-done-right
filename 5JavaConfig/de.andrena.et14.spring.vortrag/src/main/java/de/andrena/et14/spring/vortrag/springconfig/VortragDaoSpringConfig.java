package de.andrena.et14.spring.vortrag.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.andrena.et14.spring.vortrag.VortragDao;

@Configuration
public class VortragDaoSpringConfig {

	@Bean
	public VortragDao vortragDao() {
		return new VortragDao();
	}
}

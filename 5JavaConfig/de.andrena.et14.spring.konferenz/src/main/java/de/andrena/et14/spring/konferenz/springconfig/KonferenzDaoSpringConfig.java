package de.andrena.et14.spring.konferenz.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.andrena.et14.spring.konferenz.KonferenzDao;

@Configuration
public class KonferenzDaoSpringConfig {

	@Bean
	public KonferenzDao konferenzDao() {
		return new KonferenzDao();
	}
}

package de.andrena.et14.spring.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("de.andrena.et14.spring.*.springconfig")
public class WebAppConfiguration {

	@Bean
	public HttpInvokerExporterPostProcessor httpInvokerExporterPostProcessor() {
		return new HttpInvokerExporterPostProcessor();
	}
}

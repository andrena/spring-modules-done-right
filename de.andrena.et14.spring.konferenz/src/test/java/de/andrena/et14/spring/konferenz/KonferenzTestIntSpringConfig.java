package de.andrena.et14.spring.konferenz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
public class KonferenzTestIntSpringConfig {

	@Bean
	public HttpInvokerProxyFactoryBean konferenzClient() {
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		httpInvokerProxyFactoryBean
				.setServiceUrl("http://localhost:8080/remoting/IKonferenzService-httpinvoker");
		httpInvokerProxyFactoryBean.setServiceInterface(IKonferenzService.class);
		return httpInvokerProxyFactoryBean;
	}
}

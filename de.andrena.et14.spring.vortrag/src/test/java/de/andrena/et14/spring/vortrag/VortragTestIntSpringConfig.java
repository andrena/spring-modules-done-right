package de.andrena.et14.spring.vortrag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

import de.andrena.et14.spring.konferenz.IKonferenzService;

@Configuration
public class VortragTestIntSpringConfig {

	@Bean
	public HttpInvokerProxyFactoryBean konferenzClient() {
		return createHttpInvokerClient(IKonferenzService.class,
				"http://localhost:8080/remoting/IKonferenzService-httpinvoker");
	}

	@Bean
	public HttpInvokerProxyFactoryBean vortragClient() {
		return createHttpInvokerClient(IVortragService.class,
				"http://localhost:8080/remoting/IVortragService-httpinvoker");
	}

	private HttpInvokerProxyFactoryBean createHttpInvokerClient(Class<?> serviceInterface,
			String serviceUrl) {
		HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
		httpInvokerProxyFactoryBean.setServiceUrl(serviceUrl);
		httpInvokerProxyFactoryBean.setServiceInterface(serviceInterface);
		return httpInvokerProxyFactoryBean;
	}
}

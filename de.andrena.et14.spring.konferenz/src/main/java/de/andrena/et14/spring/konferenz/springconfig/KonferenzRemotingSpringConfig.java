package de.andrena.et14.spring.konferenz.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import de.andrena.et14.spring.konferenz.IKonferenzService;

@Configuration
public class KonferenzRemotingSpringConfig {

	@Autowired
	private KonferenzServiceSpringConfig konferenzServiceSpringConfig;

	@Bean(name = "/KonferenzService")
	public HttpInvokerServiceExporter konferenzServiceExporter() {
		HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
		serviceExporter.setServiceInterface(IKonferenzService.class);
		serviceExporter.setService(konferenzServiceSpringConfig.konferenzService());
		return serviceExporter;
	}
}

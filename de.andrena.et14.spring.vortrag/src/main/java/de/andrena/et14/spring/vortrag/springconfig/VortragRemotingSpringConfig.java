package de.andrena.et14.spring.vortrag.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import de.andrena.et14.spring.vortrag.IVortragService;

@Configuration
public class VortragRemotingSpringConfig {

	@Autowired
	private VortragServiceSpringConfig vortragServiceSpringConfig;

	@Bean(name = "/VortragService")
	public HttpInvokerServiceExporter konferenzServiceExporter() {
		HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
		serviceExporter.setServiceInterface(IVortragService.class);
		serviceExporter.setService(vortragServiceSpringConfig.vortragService());
		return serviceExporter;
	}
}

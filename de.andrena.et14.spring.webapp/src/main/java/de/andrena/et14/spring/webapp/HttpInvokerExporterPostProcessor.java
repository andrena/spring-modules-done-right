package de.andrena.et14.spring.webapp;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import de.andrena.et14.spring.common.HttpInvoker;

public class HttpInvokerExporterPostProcessor implements InitializingBean, ApplicationContextAware {

	private static final Log LOG = LogFactory.getLog(HttpInvokerExporterPostProcessor.class);

	private ConfigurableApplicationContext applicationContext;

	@Override
	public void afterPropertiesSet() throws Exception {
		Collection<HttpInvoker> httpInvokerBeans = applicationContext.getBeansOfType(
				HttpInvoker.class).values();
		for (HttpInvoker fassade : httpInvokerBeans) {
			Class<?> fassadeInterface = findeFassadenInterface(fassade.getClass());

			String httpUrl = toHttpUrl(fassadeInterface);
			LOG.info("Registering HTTP-Invoker for " + fassade + " at " + httpUrl);

			HttpInvokerServiceExporter exporter = createRemoteExporter(fassade, fassadeInterface);
			applicationContext.getBeanFactory().registerSingleton(httpUrl, exporter);
		}
	}

	private String toHttpUrl(Class<?> facadeInterface) {
		return "/" + facadeInterface.getSimpleName() + "-httpinvoker";
	}

	private HttpInvokerServiceExporter createRemoteExporter(Object service,
			Class<?> serviceInterface) {
		try {
			HttpInvokerServiceExporter remoteExporter = new HttpInvokerServiceExporter();
			remoteExporter.setService(service);
			remoteExporter.setServiceInterface(serviceInterface);
			remoteExporter.afterPropertiesSet();
			return remoteExporter;
		} catch (Exception e) {
			throw new RuntimeException("exception while creating remote exporter", e);
		}
	}

	private static Class<?> findeFassadenInterface(Class<?> fassadenKlasse) {
		for (Class<?> interfaceClass : fassadenKlasse.getInterfaces()) {
			if (Arrays.asList(interfaceClass.getInterfaces()).contains(HttpInvoker.class)) {
				return interfaceClass;
			}
		}

		throw new RuntimeException("Es konnte keine Facade für " + fassadenKlasse.getName()
				+ " erstellt werden. Die ServiceFacade muss genau ein Interface implementieren.");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = (ConfigurableApplicationContext) applicationContext;
	}
}

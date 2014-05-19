package de.andrena.et14.spring.persistenz;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

public class CustomClasspathScanningPersistenceUnitPostProcessor implements
		PersistenceUnitPostProcessor {

	private String basePackage;

	@Override
	public void postProcessPersistenceUnitInfo(MutablePersistenceUnitInfo pui) {
		for (PersistentClassesProvider module : findPersistentClassesProviders()) {
			for (Class<?> persistentClass : module.getPersistentClasses()) {
				pui.addManagedClassName(persistentClass.getName());
			}
		}
	}

	private Iterable<PersistentClassesProvider> findPersistentClassesProviders() {
		ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
				false);
		scanner.addIncludeFilter(new AssignableTypeFilter(PersistentClassesProvider.class));

		Set<BeanDefinition> providerDefinitions = scanner.findCandidateComponents(basePackage);
		return instantiateProviders(providerDefinitions);
	}

	private Iterable<PersistentClassesProvider> instantiateProviders(
			Set<BeanDefinition> providerDefinitions) {
		Set<PersistentClassesProvider> result = new HashSet<>();
		for (BeanDefinition beanDefinition : providerDefinitions) {
			try {
				Class<?> providerClass = Class.forName(beanDefinition.getBeanClassName());
				result.add((PersistentClassesProvider) providerClass.newInstance());
			} catch (Exception e) {
				throw new BeanInitializationException("Persistence Unit could not be built: "
						+ beanDefinition.getBeanClassName(), e);
			}
		}
		return result;
	}

	@Required
	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}
}

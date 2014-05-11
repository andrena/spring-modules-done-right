package de.andrena.et14.spring.konferenz;

import de.andrena.et14.spring.persistenz.PersistentClassesProvider;

public class KonferenzPersistentClassesProvider implements
		PersistentClassesProvider {

	@Override
	public Class<?>[] getPersistentClasses() {
		return new Class<?>[] { KonferenzEntity.class };
	}

}

package de.andrena.et14.spring.vortraege;

import de.andrena.persistenz.PersistentClassesProvider;

public class VortragPersistentClassesProvider implements
		PersistentClassesProvider {

	@Override
	public Class<?>[] getPersistentClasses() {
		return new Class<?>[] { VortragEntity.class };
	}

}

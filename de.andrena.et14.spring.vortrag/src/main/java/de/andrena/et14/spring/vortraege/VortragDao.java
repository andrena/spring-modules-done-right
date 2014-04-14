package de.andrena.et14.spring.vortraege;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class VortragDao {
	@PersistenceContext
	private EntityManager entityManager;

	public VortragEntity findById(long id) {
		return entityManager.find(VortragEntity.class, id);
	}

	public void persist(VortragEntity konferenzEntity) {
		entityManager.persist(konferenzEntity);
	}
}

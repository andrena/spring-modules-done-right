package de.andrena.et14.spring.konferenz;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class KonferenzDao {
	@PersistenceContext
	private EntityManager entityManager;

	public KonferenzEntity findById(long id) {
		return entityManager.find(KonferenzEntity.class, id);
	}

	public void persist(KonferenzEntity konferenzEntity) {
		entityManager.persist(konferenzEntity);
	}
}

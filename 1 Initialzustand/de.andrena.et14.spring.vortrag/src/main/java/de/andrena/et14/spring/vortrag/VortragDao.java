package de.andrena.et14.spring.vortrag;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class VortragDao {
	@PersistenceContext
	private EntityManager entityManager;

	public VortragEntity findById(long id) {
		return entityManager.find(VortragEntity.class, id);
	}

	public void persist(VortragEntity konferenzEntity) {
		entityManager.persist(konferenzEntity);
	}

	public List<VortragEntity> findAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<VortragEntity> query = builder
				.createQuery(VortragEntity.class);
		query.select(query.from(VortragEntity.class));

		return entityManager.createQuery(query).getResultList();
	}
}

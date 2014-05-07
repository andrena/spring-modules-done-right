package de.andrena.et14.spring.konferenz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class KonferenzDao {

	@PersistenceContext
	private EntityManager entityManager;

	public KonferenzEntity findById(long id) {
		return entityManager.find(KonferenzEntity.class, id);
	}

	public void persist(KonferenzEntity konferenzEntity) {
		entityManager.persist(konferenzEntity);
	}

	public List<KonferenzEntity> loadAll() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<KonferenzEntity> query = builder.createQuery(KonferenzEntity.class);
		query.select(query.from(KonferenzEntity.class));

		return entityManager.createQuery(query).getResultList();
	}
}

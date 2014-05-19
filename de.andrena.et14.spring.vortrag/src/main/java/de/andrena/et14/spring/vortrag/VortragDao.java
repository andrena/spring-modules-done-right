package de.andrena.et14.spring.vortrag;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		CriteriaQuery<VortragEntity> query = createQuery();
		query.select(query.from(VortragEntity.class));

		return entityManager.createQuery(query).getResultList();
	}

	public List<VortragEntity> findAllForKonferenzId(long konferenzId) {
		CriteriaQuery<VortragEntity> query = createQuery();
		Root<VortragEntity> vortragRoot = query.from(VortragEntity.class);
		query.select(vortragRoot);
		query.where(konferenzIdEquals(vortragRoot, konferenzId));
		return entityManager.createQuery(query).getResultList();
	}

	private Predicate konferenzIdEquals(Root<VortragEntity> vortragRoot, long konferenzId) {
		return criteriaBuilder().equal(vortragRoot.get("konferenz").get("id"), konferenzId);
	}

	private CriteriaQuery<VortragEntity> createQuery() {
		return criteriaBuilder().createQuery(VortragEntity.class);
	}

	private CriteriaBuilder criteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
}

/**
 * 
 */
package org.appdot.repository;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.appdot.model.IdEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

/**
 * @author Lian
 *
 */
public class BaseJpaRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
		BaseJpaRepository<T, ID> {

	private final EntityManager em;

	private final JpaEntityInformation<T, ?> entityInformation;

	public BaseJpaRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
		this(JpaEntityInformationSupport.getMetadata(domainClass, entityManager), entityManager);
	}

	public BaseJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
		this.entityInformation = entityInformation;
	}

	protected <X, Y> void translateFetchJoinToJoin(From<X, Y> from) {
		Set<Fetch<Y, ?>> fetcher = from.getFetches();
		while (fetcher.size() != 0) {
			//替换fetch  成 join
			translateFetchJoinToJoin(fetcher.iterator().next(), from);
		}
		Set<Join<Y, ?>> joins = from.getJoins();
		for (Join<Y, ?> join : joins) {
			//递归替换
			translateFetchJoinToJoin(join);
		}
	}

	@SuppressWarnings("rawtypes")
	protected void translateFetchJoinToJoin(Fetch fetch, From parent) {
		//先从parent里面移除 当前fetch
		parent.getFetches().remove(fetch);
		String fetchName = fetch.getAttribute().getName();
		String joinType = fetch.getJoinType().name();
		Join join = parent.join(fetchName, JoinType.valueOf(joinType));
		Set fetcher = fetch.getFetches();
		for (Object f : fetcher) {
			translateFetchJoinToJoin((Fetch) f, join);
		}
		fetch.getFetches().clear();
	}

	@Override
	public <S extends T> S save(S entity) {
		if (entity instanceof IdEntity) {
			Date current = new Date();
			IdEntity idEntity = (IdEntity) entity;
			if (entityInformation.isNew(entity)) {
				idEntity.setCreatedAt(current);
				idEntity.setUpdatedAt(current);
			} else {
				idEntity.setUpdatedAt(current);
			}
		}
		return super.save(entity);
	}

	@Override
	protected TypedQuery<Long> getCountQuery(Specification<T> spec) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		//count语句不应该存在排序
		query.orderBy(Collections.EMPTY_LIST);
		Root<T> root = applySpecificationToCriteria(spec, query);
		if (query.isDistinct()) {
			query.select(builder.countDistinct(root));
		} else {
			query.select(builder.count(root));
		}

		translateFetchJoinToJoin(root);

		return em.createQuery(query);
	}

	/**
	 * (Override) Applies the given {@link Specification} to the given {@link CriteriaQuery}.
	 * 
	 * @param spec can be {@literal null}.
	 * @param query must not be {@literal null}.
	 * @return
	 */
	private <S> Root<T> applySpecificationToCriteria(Specification<T> spec, CriteriaQuery<S> query) {

		Assert.notNull(query);
		Root<T> root = query.from(getDomainClass());

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}
}

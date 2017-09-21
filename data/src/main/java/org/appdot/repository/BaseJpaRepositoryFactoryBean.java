/**
 * 
 */
package org.appdot.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.appdot.model.IdEntity;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;


/**
 * @author Lian
 *
 */
public class BaseJpaRepositoryFactoryBean<R extends BaseJpaRepository<T, ID>, T extends IdEntity, ID extends Serializable>
		extends JpaRepositoryFactoryBean<R, T, ID> {

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseJpaRepositoryFactory<T, ID>(entityManager);
	}

	private static class BaseJpaRepositoryFactory<T extends IdEntity, I extends Serializable> extends
			JpaRepositoryFactory {

		private EntityManager entityManager;

		public BaseJpaRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@SuppressWarnings({ "unchecked" })
		@Override
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new BaseJpaRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			// The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
			//to check for QueryDslJpaRepository's which is out of scope.
			return BaseJpaRepositoryImpl.class;
		}
	}
}

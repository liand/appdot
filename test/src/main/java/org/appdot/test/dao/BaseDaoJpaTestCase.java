package org.appdot.test.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.appdot.test.spring.BaseSpringTransactionalTestCase;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.beans.BeansException;

/**
 * Base class for running DAO tests.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @author jgarcia (updated: migrate to hibernate 4; moved from compass-search to hibernate-search
 * @author liand (updated: use commons MapUtils to convert ResourceBundle to Map; modify spring context file locations)
 */
public abstract class BaseDaoJpaTestCase extends BaseSpringTransactionalTestCase {

	protected EntityManager entityManager;

	/**
	 * ResourceBundle loaded from src/test/resources/${package.name}/ClassName.properties (if exists)
	 */
	protected ResourceBundle rb;

	/**
	 * Default constructor - populates "rb" variable if properties file exists for the class in
	 * src/test/resources.
	 */
	public BaseDaoJpaTestCase() {
		// Since a ResourceBundle is not required for each class, just
		// do a simple check to see if one exists
		String className = this.getClass().getName();

		try {
			rb = ResourceBundle.getBundle(className);
		} catch (MissingResourceException mre) {
			logger.trace("No resource bundle found for: " + className);
		}
	}

	/**
	 * Utility method to populate a javabean-style object with values
	 * from a Properties file
	 *
	 * @param obj the model object to populate
	 * @return Object populated object
	 * @throws Exception if BeanUtils fails to copy properly
	 */
	protected Object populate(final Object obj) throws Exception {
		// loop through all the beans methods and set its properties from its .properties file
		Map<String, String> map = new HashMap<String, String>();

		map = MapUtils.toMap(rb);

		BeanUtils.copyProperties(obj, map);

		return obj;
	}

	/**
	 * Create a HibernateTemplate from the SessionFactory and call flush() and clear() on it.
	 * Designed to be used after "save" methods in tests: http://issues.appfuse.org/browse/APF-178.
	 *
	 * @throws org.springframework.beans.BeansException
	 *          when can't find 'sessionFactory' bean
	 */
	protected void flush() throws BeansException {
		entityManager.flush();
	}

	/**
	 * Flush search indexes, to be done after a reindex() or reindexAll() operation
	 */
	/**
	 * Flush search indexes, to be done after a reindex() or reindexAll() operation
	 */
	public void flushSearchIndexes() {
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) applicationContext
				.getBean("entityManagerFactory");
		FullTextEntityManager fullTextEntityMgr = Search.getFullTextEntityManager(entityManagerFactory
				.createEntityManager());
		fullTextEntityMgr.flushToIndexes();
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}

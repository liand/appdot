/**
 * @(#) IdEntity.java
 */

package org.appdot.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity简单基类，不包括auditable属性.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略.
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author liand
 */
//JPA 基类标识
@MappedSuperclass
public abstract class SimpleIdEntity {

	protected Long id;

	public SimpleIdEntity() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

}

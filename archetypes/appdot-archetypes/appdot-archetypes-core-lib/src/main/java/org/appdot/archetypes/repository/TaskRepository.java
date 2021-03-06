package org.appdot.archetypes.repository;

import org.appdot.archetypes.entity.account.Task;
import org.appdot.repository.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface TaskRepository extends BaseJpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
	
	Page<Task> findByUserId(Long id, Pageable pageRequest);

	@Modifying
	@Query("delete from Task task where task.user.id=?1")
	void deleteByUserId(Long id);
}

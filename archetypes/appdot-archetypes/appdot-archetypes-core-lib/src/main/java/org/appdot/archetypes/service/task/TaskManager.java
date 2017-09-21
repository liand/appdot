/**
 * 
 */
package org.appdot.archetypes.service.task;

import java.util.Map;

import org.appdot.archetypes.entity.account.Task;
import org.appdot.service.GenericManager;
import org.springframework.data.domain.Page;

/**
 * @author Lian
 *
 */
public interface TaskManager extends GenericManager<Task, Long> {

	public abstract Page<Task> getUserTask(Long userId, Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType);

}

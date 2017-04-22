package ua.com.gfalcon.gesem.dao.cms;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.gfalcon.gesem.domain.cms.BuildObject;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
public interface BuildObjectDAO extends PagingAndSortingRepository<BuildObject, Long> {
}

package ua.com.gfalcon.gesem.dao.cms.specification;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.gfalcon.gesem.domain.cms.specification.Stage;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
public interface StageDAO extends PagingAndSortingRepository<Stage, Long> {
}

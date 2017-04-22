package ua.com.gfalcon.gesem.dao.norms;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.gfalcon.gesem.domain.norms.WorksPrice;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
public interface WorksPriceDAO extends PagingAndSortingRepository<WorksPrice, Long> {
}

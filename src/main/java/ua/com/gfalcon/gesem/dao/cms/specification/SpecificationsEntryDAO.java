package ua.com.gfalcon.gesem.dao.cms.specification;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.com.gfalcon.gesem.domain.cms.specification.SpecificationsEntry;


public interface SpecificationsEntryDAO extends PagingAndSortingRepository<SpecificationsEntry, Long> {
}

package ua.com.gfalcon.gesem.dao.cms.specification;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.cms.specification.SpecificationsEntry;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class SpecificationsEntryDAOImpl extends GenericEntityDao<SpecificationsEntry>
        implements SpecificationsEntryDAO {
    protected SpecificationsEntryDAOImpl() {
        super(SpecificationsEntry.class);
    }
}

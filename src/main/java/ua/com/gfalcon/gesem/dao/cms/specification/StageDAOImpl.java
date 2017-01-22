package ua.com.gfalcon.gesem.dao.cms.specification;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.cms.specification.StageImpl;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class StageDAOImpl extends GenericEntityDao<StageImpl> implements StageDAO {
    protected StageDAOImpl() {
        super(StageImpl.class);
    }
}

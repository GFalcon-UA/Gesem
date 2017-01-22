package ua.com.gfalcon.gesem.dao.cms.specification;

import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.cms.specification.StagesWork;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
public class StagesWorkDAOImpl extends GenericEntityDao<StagesWork> implements StagesWorkDAO {
    protected StagesWorkDAOImpl() {
        super(StagesWork.class);
    }
}

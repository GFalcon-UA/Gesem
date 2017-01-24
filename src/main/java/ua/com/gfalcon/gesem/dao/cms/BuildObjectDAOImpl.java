package ua.com.gfalcon.gesem.dao.cms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.cms.BuildObject;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class BuildObjectDAOImpl extends GenericEntityDao<BuildObject> implements BuildObjectDAO {
    protected BuildObjectDAOImpl() {
        super(BuildObject.class);
    }
}

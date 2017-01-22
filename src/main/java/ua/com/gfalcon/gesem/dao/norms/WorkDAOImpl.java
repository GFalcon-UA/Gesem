package ua.com.gfalcon.gesem.dao.norms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.norms.Work;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class WorkDAOImpl extends GenericEntityDao<Work> implements WorkDAO {
    protected WorkDAOImpl() {
        super(Work.class);
    }
}

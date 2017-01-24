package ua.com.gfalcon.gesem.dao.norms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.norms.WorksPrice;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class WorksPriceDAOImpl extends GenericEntityDao<WorksPrice> implements WorksPriceDAO {
    protected WorksPriceDAOImpl() {
        super(WorksPrice.class);
    }
}

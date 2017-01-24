package ua.com.gfalcon.gesem.dao.norms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.norms.MeasureUnit;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class MeasureUnitDAOImpl extends GenericEntityDao<MeasureUnit> implements MeasureUnitDAO{
    protected MeasureUnitDAOImpl() {
        super(MeasureUnit.class);
    }
}

package ua.com.gfalcon.gesem.dao.norms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.norms.MaterialsPrice;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class MaterialsPriceDAOImpl extends GenericEntityDao<MaterialsPrice> implements MaterialsPriceDAO {
    protected MaterialsPriceDAOImpl() {
        super(MaterialsPrice.class);
    }
}

package ua.com.gfalcon.gesem.dao.norms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.norms.Material;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class MaterialDAOImpl extends GenericEntityDao<Material> implements MaterialDAO {
    protected MaterialDAOImpl() {
        super(Material.class);
    }
}

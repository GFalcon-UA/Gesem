package ua.com.gfalcon.gesem.dao.cms;

import org.springframework.stereotype.Repository;
import ua.com.gfalcon.entitydao.GenericEntityDao;
import ua.com.gfalcon.gesem.domain.cms.Partner;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Repository
public class PartnerDAOImpl extends GenericEntityDao<Partner> implements PartnerDAO {
    protected PartnerDAOImpl() {
        super(Partner.class);
    }
}

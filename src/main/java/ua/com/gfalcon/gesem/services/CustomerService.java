package ua.com.gfalcon.gesem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gfalcon.gesem.dao.cms.BuildObjectDAO;
import ua.com.gfalcon.gesem.dao.cms.PartnerDAO;
import ua.com.gfalcon.gesem.dao.cms.specification.SpecificationsEntryDAO;
import ua.com.gfalcon.gesem.dao.cms.specification.StageDAO;
import ua.com.gfalcon.gesem.dao.cms.specification.StagesWorkDAO;
import ua.com.gfalcon.gesem.domain.cms.BuildObject;
import ua.com.gfalcon.gesem.domain.cms.Partner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class CustomerService {
    @Autowired
    private PartnerDAO partnerDAO;
    @Autowired
    private BuildObjectDAO buildObjectDAO;
    @Autowired
    private SpecificationsEntryDAO specificationsEntryDAO;
    @Autowired
    private StageDAO stageDAO;
    @Autowired
    private StagesWorkDAO stagesWorkDAO;

    @Transactional(readOnly = true)
    public List<Partner> getCustomersList() {
        List<Partner> partners;
        partners = (List<Partner>) partnerDAO.findAll();
        return partners;
    }

    public void createPartner(String name, String phones, String contactPersons, String codeUSREOU) {
        Partner partner = new Partner(name);
        partner.setCodeUSREOU(codeUSREOU);
        partner.setPhones(phones);
        partner.setContactPersons(contactPersons);
        partnerDAO.save(partner);
    }

    public void updatePartnerById(Long id, String name, String phones, String contactPersons, String codeUSREOU)
            throws NoSuchFieldException {
        Partner partner = getCustomerById(id);
        if (name != null && !name.equals("")) {
            partner.setName(name);
        }
        if (codeUSREOU != null) {
            partner.setCodeUSREOU(codeUSREOU);
        }
        if (phones != null) {
            partner.setPhones(phones);
        }
        if (contactPersons != null) {
            partner.setContactPersons(contactPersons);
        }
        partnerDAO.save(partner);
    }

    public Partner updatePartner(Partner partner) {
        return partnerDAO.save(partner);
    }

    public void deletePartner(Long id) {
        partnerDAO.delete(id);
    }

    public void deletePartner(Partner partner) {
        partnerDAO.delete(partner);
    }

    @Transactional(readOnly = true)
    public Partner getCustomerById(Long id) throws NoSuchFieldException {
        Partner result;
        if (partnerDAO.exists(id)) {
            result = partnerDAO.findOne(id);
        } else {
            throw new NoSuchFieldException(String.format("Partner [id = %s] not found", id));
        }
        return result;
    }

    public void createBuidObject(Long customerId, String name, String address, BigDecimal overheadCosts)
            throws NoSuchFieldException {
        Partner partner = getCustomerById(customerId);
        BuildObject buildObject = new BuildObject(name);
        if (address != null) {
            buildObject.setAddress(address);
        }
        if (overheadCosts != null) {
            buildObject.setOverheadCosts(overheadCosts);
        }

        partner.addObject(buildObject);
        Partner updated = updatePartner(partner);

        buildObject.setOwner(updated);
        updateBuidObject(buildObject);
    }

    public BuildObject updateBuidObject(BuildObject buildObject) {
        return buildObjectDAO.save(buildObject);
    }

    @Transactional(readOnly = true)
    public BuildObject getBuilObjectById(Long id) throws NoSuchFieldException {
        BuildObject buildObject;
        if (buildObjectDAO.exists(id)) {
            buildObject = buildObjectDAO.findOne(id);
        } else {
            throw new NoSuchFieldException(String.format("BuildObject [id = %s] not found", id));
        }
        return buildObject;
    }

    /*@Transactional(readOnly = true)
    public List<BuildObject> getBuildObjectsByOwnerId(Long id) throws NoSuchFieldException {
        Partner owner = getCustomerById(id);
        List<BuildObject> buildObjectList = buildObjectDAO.findAllBy("owner", owner);
        return buildObjectList;
    }*/

    public void deleteBuidObject(Long id) {
        buildObjectDAO.delete(id);
    }

    public void deleteBuidObject(BuildObject buildObject) {
        buildObjectDAO.delete(buildObject);
    }

}

package ua.com.gfalcon.gesem.services;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gfalcon.gesem.dao.cms.specification.SpecificationsEntryDAO;
import ua.com.gfalcon.gesem.dao.norms.*;
import ua.com.gfalcon.gesem.domain.norms.*;
import ua.com.gfalcon.gesem.exeptions.HasChildrenEntityException;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class CommonDefinitionService {
    @Autowired
    private MaterialDAO materialDAO;
    @Autowired
    private MaterialsPriceDAO materialsPriceDAO;
    @Autowired
    private MeasureUnitDAO measureUnitDAO;
    @Autowired
    private WorkDAO workDAO;
    @Autowired
    private WorksPriceDAO worksPriceDAO;
    @Autowired
    private WorksTypeDAO worksTypeDAO;

    @Autowired
    private SpecificationsEntryDAO specificationsEntryDAO;

    public MeasureUnit createMeasureUnit(@NotNull String name, String smallName) {
        MeasureUnit measureUnit = new MeasureUnit(name);
        if (smallName != null) {
            measureUnit.setSmallName(smallName);
        }
        measureUnitDAO.save(measureUnit);
        return measureUnit;
    }

    @Transactional(readOnly = true)
    public MeasureUnit getMeasureUnitById(Long id) throws NoSuchFieldException {
        MeasureUnit result;
        if (measureUnitDAO.exists(id)) {
            result = measureUnitDAO.findOne(id);
        } else {
            throw new NoSuchFieldException(String.format("MeasureUnit [id = %s] not found", id));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public void updateMeasureUnit(MeasureUnit measureUnit) {
        measureUnitDAO.save(measureUnit);
    }

    @Transactional(readOnly = true)
    public List<MeasureUnit> getMeasureUnits() {
        List<MeasureUnit> measureUnitList = (List<MeasureUnit>) measureUnitDAO.findAll();
        return measureUnitList;
    }

    public void deleteMeasureUnitById(Long id) {
        measureUnitDAO.delete(id);
    }

    public Material createMaterial(@NotNull String name, MeasureUnit measureUnit) {
        Material material = new Material(name);
        if (measureUnit != null) {
            measureUnit.addMaterial(material);
            material.setMeasureUnit(measureUnit);
            updateMeasureUnit(measureUnit);
        }
        materialDAO.save(material);
        return material;
    }

    @Transactional(readOnly = true)
    public Material getMaterialById(Long id) throws NoSuchFieldException {
        Material result;
        if (materialDAO.exists(id)) {
            result = materialDAO.findOne(id);
        } else {
            throw new NoSuchFieldException(String.format("Material [id = %s] not found", id));
        }
        return result;
    }

    public void updateMaterial(Material material) {
        Material oldMaterial = null;
        try {
            oldMaterial = getMaterialById(material.getId());
        } catch (NoSuchFieldException e) {
            materialDAO.save(material);
            return;
        }

        if (oldMaterial != null) {
            MeasureUnit oldMU = oldMaterial.getMeasureUnit();
            MeasureUnit newMU = material.getMeasureUnit();
            if (newMU == null && oldMU != null) {
                removeMaterialFromMeasureUnit(oldMaterial, oldMU);
            } else if (newMU != null && !newMU.equals(oldMU)) {
                if (oldMU != null) {
                    removeMaterialFromMeasureUnit(oldMaterial, oldMU);
                }
                newMU.addMaterial(material);
                updateMeasureUnit(newMU);
            }
        }

        materialDAO.save(material);
    }

    private void removeMaterialFromMeasureUnit(@NotNull Material material, @NotNull MeasureUnit oldMU) {
        oldMU.removeMaterial(material);
        updateMeasureUnit(oldMU);
    }

    /*
        public void deleteMaterial(Material material) throws HasChildrenEntityException {
            List<SpecificationsEntry> entries = specificationsEntryDAO.findAllBy("material", material);
            if (entries.size() > 0) {
                throw new HasChildrenEntityException(String.format(
                        "Can't delete Material [id = %s]. It is included in specifications (include count = %s)",
                        material.getId(), entries.size()));
            }

            Map<Material, BigDecimal> basicBOM = new HashMap<>();
            List<WorksType> worksTypes = getWorksTypes();
            for (WorksType type : worksTypes) {
                basicBOM.putAll(type.getBasicBOM());
            }
            List<Work> works = getWorks();
            for (Work work : works) {
                basicBOM.putAll(work.getBasicBomCoeficients());
                basicBOM.putAll(work.getSpecificBOM());
            }

            if (basicBOM.containsKey(material)) {
                throw new HasChildrenEntityException(
                        String.format("Can't delete Material [id = %s]. It is included in base specifications",
                                material.getId()));
            }

            MeasureUnit mu = material.getMeasureUnit();
            if (mu != null) {
                removeMaterialFromMeasureUnit(material, mu);
            }
            materialDAO.delete(material);
        }
    */
    @Transactional(readOnly = true)
    public List<Material> getMaterials() {
        List<Material> list = (List<Material>) materialDAO.findAll();
        return list;
    }

    public void createWorksType(@NotNull String name, Set<Work> works, BigDecimal price,
            Map<Material, BigDecimal> basicBOM) {
        WorksType worksType = new WorksType(name);
        if (works != null) {
            worksType.setWorks(works);
            for (Work work : works) {
                work.setWorksType(worksType);
                updateWork(work);
            }
        }
        if (price != null) {
            WorksPrice worksPrice = new WorksPrice(worksType, price, new DateTime(2000, 1, 1, 0, 0));
            worksType.addCost(worksPrice);
            worksPriceDAO.save(worksPrice);
        }
        if (basicBOM != null) {
            worksType.setBasicBOM(basicBOM);
        }
        worksTypeDAO.save(worksType);
    }

    public void setWorksPrice(@NotNull Long worksTypeId, @NotNull BigDecimal price, DateTime startDate)
            throws NoSuchFieldException {
        WorksType worksType = getWorksTypeById(worksTypeId);
    }

    /**
     * Задать прайсавую цену
     *
     * @param worksType - вид работ
     * @param price     - цена
     * @param startDate - дата начала действия
     * @throws IllegalArgumentException при попытке задания отрицательной цены
     */
    /*public void setPlanWorksPrice(@NotNull WorksType worksType, @NotNull BigDecimal price, DateTime startDate)
            throws IllegalArgumentException {
        if (price.intValue() < 0) {
            throw new IllegalArgumentException(
                    String.format("Can't send negative price for WorksType [id = %s]", worksType.getId()));
        }
        List<WorksPrice> worksPriceList = worksPriceDAO.findAllBy("worksType", worksType);
        WorksPrice worksPrice = null;
        if (!worksPriceList.isEmpty()) {
            for (WorksPrice wp : worksPriceList) {
                if (startDate.equals(wp.getStartDateTime())) {
                    worksPrice = wp;
                    break;
                }
            }
        }
        if (worksPrice == null) {
            if (startDate == null) {
                worksPrice = new WorksPrice(worksType, price);
            } else {
                worksPrice = new WorksPrice(worksType, price, startDate);
            }
            worksType.addCost(worksPrice);
            updateWorksType(worksType);
        } else {
            worksPrice.setCost(price);
        }
        worksPriceDAO.saveOrUpdate(worksPrice);
    }*/

    /*public void setPlanMaterialPrice(@NotNull Material material, @NotNull BigDecimal price, DateTime startDate)
            throws IllegalArgumentException {
        if (price.intValue() < 0) {
            throw new IllegalArgumentException(
                    String.format("Can't send negative price for Material [id = %s]", material.getId()));
        }
        List<MaterialsPrice> priceList = materialsPriceDAO.findAllBy("material", material);
        MaterialsPrice materialPrice = null;
        if (!priceList.isEmpty()) {
            for (MaterialsPrice wp : priceList) {
                if (startDate.equals(wp.getStartDateTime())) {
                    materialPrice = wp;
                    break;
                }
            }
        }
        if (materialPrice == null) {
            if (startDate == null) {
                materialPrice = new MaterialsPrice(material, price);
            } else {
                materialPrice = new MaterialsPrice(material, price, startDate);
            }
            material.addCost(materialPrice);
            updateMaterial(material);
        } else {
            materialPrice.setCost(price);
        }
        materialsPriceDAO.saveOrUpdate(materialPrice);
    }*/

    /**
     * Удалить прайсовую цену
     *
     * @param worksType
     * @param date
     */
    public void removePlanWorkPrice(@NotNull WorksType worksType, DateTime date) {
        if (worksType.getCost().size() == 1) {
            WorksPrice[] worksPrices = (WorksPrice[]) worksType.getCost().toArray();
            if (date == null) {
                worksPriceDAO.delete(worksPrices[0]);
                worksType.setCost(null);
            } else {
                if (date.equals(worksPrices[0].getStartDateTime())) {
                    worksType.setCost(null);
                    worksPriceDAO.delete(worksPrices[0]);
                } else {
                    throw new IllegalArgumentException(String.format(
                            "Can't remove price for WorksType [id = %s]. The Date in request is not equals",
                            worksType.getId()));
                }
            }
        } else {
            WorksPrice worksPrice = null;
            for (WorksPrice wp : worksType.getCost()) {
                if (date.equals(wp.getStartDateTime())) {
                    worksPrice = wp;
                    break;
                }
            }
            if (worksPrice != null) {
                worksType.removeCost(worksPrice);
                worksPriceDAO.delete(worksPrice);
            } else {
                throw new IllegalArgumentException(
                        String.format("Can't remove price for WorksType [id = %s]. The Date in request is not equals",
                                worksType.getId()));
            }
        }
        updateWorksType(worksType);
    }

    public void removePlanMaterialPrice(@NotNull Material material, DateTime date) {
        if (material.getCosts().size() == 1) {
            MaterialsPrice[] materialsPrices = (MaterialsPrice[]) material.getCosts().toArray();
            if (date == null) {
                materialsPriceDAO.delete(materialsPrices[0]);
                material.setCosts(null);
            } else {
                if (date.equals(materialsPrices[0].getStartDateTime())) {
                    material.setCosts(null);
                    materialsPriceDAO.delete(materialsPrices[0]);
                } else {
                    throw new IllegalArgumentException(String.format(
                            "Can't remove price for Material [id = %s]. The Date in request is not equals",
                            material.getId()));
                }
            }
        } else {
            MaterialsPrice materialsPrice = null;
            for (MaterialsPrice price : material.getCosts()) {
                if (date.equals(price.getStartDateTime())) {
                    materialsPrice = price;
                    break;
                }
            }
            if (materialsPrice != null) {
                material.removeCost(materialsPrice);
                materialsPriceDAO.delete(materialsPrice);
            } else {
                throw new IllegalArgumentException(
                        String.format("Can't remove price for Material [id = %s]. The Date in request is not equals",
                                material.getId()));
            }
        }
        updateMaterial(material);
    }

    @Transactional(readOnly = true)
    public WorksType getWorksTypeById(Long id) throws NoSuchFieldException {
        WorksType result;
        if (worksTypeDAO.exists(id)) {
            result = worksTypeDAO.findOne(id);
        } else {
            throw new NoSuchFieldException(String.format("WorksType [id = %s] not found", id));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<WorksType> getWorksTypes() {
        List<WorksType> list = (List<WorksType>) worksTypeDAO.findAll();
        return list;
    }

    public void updateWorksType(WorksType worksType) {
        /*
        try {
            WorksType oldWorksType = getWorksTypeById(worksType.getId());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Set<Work> newWorkList = worksType.getWorks();
        */
        //todo
        worksTypeDAO.save(worksType);
    }

    public void deleteWorksType(WorksType worksType) throws HasChildrenEntityException {
        Set<Work> workSet = worksType.getWorks();
        if (workSet != null && !workSet.isEmpty()) {
            throw new HasChildrenEntityException(
                    String.format("Can't delete WorkType [id = %s]. It has not empty Set of Work (size = %s)",
                            worksType.getId(), workSet.size()));
        }
        worksTypeDAO.delete(worksType);
    }

    public void createWork(@NotNull String name, WorksType worksType, Map<Material, BigDecimal> basicBomCoefficients,
            Map<Material, BigDecimal> specificBOM) {
        Work work = new Work(name);
        if (worksType != null) {
            worksType.removeWork(work);
            work.setWorksType(worksType);
            updateWorksType(worksType);
        }
        if (basicBomCoefficients != null) {
            work.setBasicBomCoeficients(basicBomCoefficients);
        }
        if (specificBOM != null) {
            work.setSpecificBOM(specificBOM);
        }
        workDAO.save(work);
    }

    @Transactional(readOnly = true)
    public Work getWorkById(Long id) throws NoSuchFieldException {
        Work result;
        if (workDAO.exists(id)) {
            result = workDAO.findOne(id);
        } else {
            throw new NoSuchFieldException(String.format("Work [id = %s] not found", id));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<Work> getWorks() {
        List<Work> works = (List<Work>) workDAO.findAll();
        return works;
    }

    public void updateWork(Work work) {
        //todo
        workDAO.save(work);
    }

/*    public void deleteWorkById(Work work) throws HasChildrenEntityException {
        int specIncludedCount = specificationsEntryDAO.findAllBy("work", work).size();
        if (specIncludedCount > 0) {
            throw new HasChildrenEntityException(
                    String.format("Can't delete Work [id = %s]. It is included in specifications (include count = %s)",
                            work.getId(), specIncludedCount));
        }
        WorksType worksType = work.getWorksType();
        if (worksType != null) {
            worksType.removeWork(work);
            updateWorksType(worksType);
        }
        workDAO.delete(work);
    }
*/
}

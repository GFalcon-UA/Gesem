package ua.com.gfalcon.gesem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.gfalcon.gesem.dao.cms.BuildObjectDAO;
import ua.com.gfalcon.gesem.dao.cms.specification.StageDAO;
import ua.com.gfalcon.gesem.domain.cms.BuildObject;
import ua.com.gfalcon.gesem.domain.cms.specification.ParentStage;
import ua.com.gfalcon.gesem.domain.cms.specification.Stage;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional
public class ProjectService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StageDAO stageDAO;

    @Transactional(readOnly = true)
    public Stage getStageById(Long id) throws NoSuchFieldException {
        Stage stage;
        if (stageDAO.findById(id).isPresent()) {
            stage = stageDAO.findById(id).get();
        } else {
            throw new NoSuchFieldException(String.format("Stage [id = %s] not found", id));
        }
        return stage;
    }

    @Transactional(readOnly = true)
    public Set<Stage> getStagesListByParentStageId(Long id) throws NoSuchFieldException {
        Set<Stage> stageList;
        Stage parentStage = getStageById(id);
        stageList = parentStage.getStages();
        return stageList;
    }

    /**
     * @param stageName   имя стадии
     * @param parentStage стадия верхнего уровня
     * @param seq         порядковый номер для сортировки
     * @throws InstantiationException
     */
    public void createStage(String stageName, @NotNull ParentStage parentStage, Integer seq)
            throws InstantiationException {
        Stage newStage = new Stage(stageName);

        if (parentStage instanceof BuildObject) {
            BuildObject buildObject = (BuildObject) parentStage;
            newStage.setParentStage(buildObject);
            customerService.updateBuidObject(buildObject);
        } else if (parentStage instanceof Stage) {
            Stage mainStage = (Stage) parentStage;
            newStage.setParentStage(mainStage);
            updateStage(mainStage);
        } else {
            throw new InstantiationException(
                    String.format("Can't create Stage [name = %s]. Parent stage isn't instance class", stageName));
        }

        if (seq == null || seq < 1) {
            seq = 1;
        }

        newStage.setSequence(seq);

        updateStage(newStage);
    }

    public void updateStage(Stage stage) {
        stageDAO.saveOrUpdate(stage);
    }

    public void deleteStageById(Long id) throws NoSuchFieldException {
        Stage stage = getStageById(id);
        ParentStage parentStage = stage.getParentStage();
        parentStage.deleteChildStage(stage);
        stageDAO.delete(stage);
    }

}

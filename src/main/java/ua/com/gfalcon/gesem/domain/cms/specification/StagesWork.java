package ua.com.gfalcon.gesem.domain.cms.specification;

import ua.com.gfalcon.gesem.domain.norms.Work;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Entity(name = "StagesWork")
@Table(name = "STAGES_WORKS")
@PrimaryKeyJoinColumn(name = "work_id")
public class StagesWork extends Work {

    @ManyToOne
    private Stage parentStage;

    @OneToMany(mappedBy = "work", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SpecificationsEntry> entrySet = new HashSet<>();

    protected StagesWork() {

    }

    public StagesWork(String name, Stage parentStage) {
        setName(name);
        setParentStage(parentStage);
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public Set<SpecificationsEntry> getEntrySet() {
        return entrySet;
    }

    public void setEntrySet(Set<SpecificationsEntry> entrySet) {
        this.entrySet = entrySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        StagesWork that = (StagesWork) o;

        if (!parentStage.equals(that.parentStage))
            return false;
        return entrySet != null ? entrySet.equals(that.entrySet) : that.entrySet == null;
    }

    @Override
    public int hashCode() {
        int result = parentStage.hashCode();
        result = 31 * result + (entrySet != null ? entrySet.hashCode() : 0);
        return result;
    }
}

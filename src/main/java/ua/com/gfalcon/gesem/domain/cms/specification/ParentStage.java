package ua.com.gfalcon.gesem.domain.cms.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Entity(name = "ParentStage")
@Table(name = "PARENT_STAGES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ParentStage extends AbstractEntity {

    @JsonProperty(value = "aStages")
    @OneToMany(mappedBy = "parentStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stage> stages = new HashSet<>();

    protected ParentStage() {

    }

    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public void addChildStage(Stage stage) {
        stages.add(stage);
    }

    public void addChildStages(Set<Stage> stages) {
        this.stages.addAll(stages);
    }

    public void deleteChildStage(Stage stage) {
        stages.remove(stage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof ParentStage))
            return false;

        ParentStage that = (ParentStage) o;

        return new EqualsBuilder()
                .append(stages, that.stages)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(stages)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ParentStage " + super.toString();
    }
}

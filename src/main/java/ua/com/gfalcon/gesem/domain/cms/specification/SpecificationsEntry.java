package ua.com.gfalcon.gesem.domain.cms.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gfalcon.entitydao.AbstractEntity;
import ua.com.gfalcon.gesem.domain.norms.Material;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Table(name = "SP_ENTRIES")
@Entity(name = "SpecificationsEntry")
public class SpecificationsEntry extends AbstractEntity {

    @JsonProperty(value = "oMaterial")
    @ManyToOne
    private Material material;

    @JsonProperty(value = "oWork")
    @ManyToOne
    private StagesWork work;

    @JsonProperty(value = "nCoefficient")
    private BigDecimal coef = new BigDecimal(1); // коэффициент для расчета материалов

    public SpecificationsEntry() {

    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public StagesWork getWork() {
        return work;
    }

    public void setWork(StagesWork work) {
        this.work = work;
    }

    public BigDecimal getCoef() {
        return coef;
    }

    public void setCoef(BigDecimal coef) {
        this.coef = coef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SpecificationsEntry that = (SpecificationsEntry) o;

        if (!material.equals(that.material))
            return false;
        if (!work.equals(that.work))
            return false;
        return coef.equals(that.coef);
    }

    @Override
    public int hashCode() {
        int result = material.hashCode();
        result = 31 * result + work.hashCode();
        result = 31 * result + coef.hashCode();
        return result;
    }

}

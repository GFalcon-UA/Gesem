package ua.com.gfalcon.gesem.domain.norms;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@Entity(name = "MaterialsPrice")
@Table(name = "MATERIALS_PRICELIST")
public class MaterialsPrice extends Price {

    @JsonProperty(value = "oMaterial")
    @ManyToOne
    private Material material;

    protected MaterialsPrice() {

    }

    public MaterialsPrice(Material material, BigDecimal price) {
        this(material, price, DateTime.now());
    }

    public MaterialsPrice(Material material, BigDecimal price, DateTime startDate) {
        setMaterial(material);
        setCost(price);
        setStartDate(startDate);
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MaterialsPrice))
            return false;
        if (!super.equals(o))
            return false;

        MaterialsPrice that = (MaterialsPrice) o;

        return material.equals(that.material);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + material.hashCode();
        return result;
    }
}

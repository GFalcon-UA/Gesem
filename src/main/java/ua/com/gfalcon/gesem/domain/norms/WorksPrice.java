package ua.com.gfalcon.gesem.domain.norms;

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
@Table(name = "WORKS_PRICELIST")
@Entity(name = "WorksPrice")
public class WorksPrice extends Price {

    @ManyToOne
    private WorksType worksType;

    protected WorksPrice() {

    }

    public WorksPrice(WorksType worksType, BigDecimal price) {
        this(worksType, price, DateTime.now());
    }

    public WorksPrice(WorksType worksType, BigDecimal price, DateTime startDate) {
        setWorksType(worksType);
        setCost(price);
        setStartDate(startDate);
    }

    public WorksType getWorksType() {
        return worksType;
    }

    public void setWorksType(WorksType worksType) {
        this.worksType = worksType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WorksPrice))
            return false;
        if (!super.equals(o))
            return false;

        WorksPrice that = (WorksPrice) o;

        return worksType.equals(that.worksType);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + worksType.hashCode();
        return result;
    }
}

package ua.com.gfalcon.gesem.domain.norms;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.Date;
import java.util.Map;

/**
 * @author Oleksii Khalikov
 * @version 1.0
 * @since 1.0
 */
@MappedSuperclass
public class Price extends AbstractEntity {

    @JsonProperty(value = "dStartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @JsonProperty(value = "nCost")
    private BigDecimal cost;

    protected Price() {

    }

    public Date getStartDate() {
        return startDate;
    }

    public DateTime getStartDateTime() {
        return new DateTime(startDate);
    }

    public void setStartDate(DateTime date) {
        setStartDate(date.toDate());
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCost(BigDecimal cost, Date startDate) {
        setCost(cost);
        setStartDate(startDate);
    }

    public void setCost(BigDecimal cost, DateTime date) {
        setCost(cost, date.toDate());
    }

    public Map.Entry<Date, BigDecimal> getCostAsEntry() {
        return new AbstractMap.SimpleEntry<>(getStartDate(), getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Price))
            return false;

        Price price = (Price) o;

        if (startDate != null ? !startDate.equals(price.startDate) : price.startDate != null)
            return false;
        return cost.equals(price.cost);
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + cost.hashCode();
        return result;
    }
}

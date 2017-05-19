/*
 * Copyright (c) 2016-2017 by Oleksii KHALIKOV.
 * ========================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ua.com.gfalcon.gesem.domain.norms;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since 1.0
 */
@Entity(name = "Work")
@Table(name = "WORKS")
@Inheritance(strategy = InheritanceType.JOINED)
public class Work extends AbstractEntity {

    @JsonProperty(value = "sName")
    @Column(name = "NAME", unique = true)
    private String name;

    @JsonProperty(value = "oWorkType")
    @ManyToOne
    private WorksType worksType;

    /**
     * Коэффициенты для расчета расхода базовых материалов
     */
    @JsonProperty(value = "oBasicBomCoefficients")
    @ElementCollection
    @CollectionTable(name = "basic_bom_coefficients")
    @Column(name = "coefficient")
    @MapKeyJoinColumn(name = "material_id", referencedColumnName = "id")
    private Map<Material, BigDecimal> basicBomCoefficients = new HashMap<>();

    /**
     * Нормы расхода специфических материалов
     */
    @JsonProperty(value = "oSpecificBOM")
    @ElementCollection
    @CollectionTable(name = "specific_bom")
    @Column(name = "norm")
    @MapKeyJoinColumn(name = "material_id", referencedColumnName = "id")
    private Map<Material, BigDecimal> specificBOM = new HashMap<>();

    protected Work() {

    }

    public Work(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorksType getWorksType() {
        return worksType;
    }

    public void setWorksType(WorksType worksType) {
        this.worksType = worksType;
    }

    public Map<Material, BigDecimal> getBasicBomCoeficients() {
        return basicBomCoefficients;
    }

    public void setBasicBomCoeficients(Map<Material, BigDecimal> basicBomCoeficients) {
        this.basicBomCoefficients = basicBomCoeficients;
    }

    public Map<Material, BigDecimal> getSpecificBOM() {
        return specificBOM;
    }

    public void setSpecificBOM(Map<Material, BigDecimal> specificBOM) {
        this.specificBOM = specificBOM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Work))
            return false;

        Work work = (Work) o;

        if (!name.equals(work.name))
            return false;
        if (!worksType.equals(work.worksType))
            return false;
        if (basicBomCoefficients != null ?
                !basicBomCoefficients.equals(work.basicBomCoefficients) :
                work.basicBomCoefficients != null)
            return false;
        return specificBOM != null ? specificBOM.equals(work.specificBOM) : work.specificBOM == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + worksType.hashCode();
        result = 31 * result + (basicBomCoefficients != null ? basicBomCoefficients.hashCode() : 0);
        result = 31 * result + (specificBOM != null ? specificBOM.hashCode() : 0);
        return result;
    }
}

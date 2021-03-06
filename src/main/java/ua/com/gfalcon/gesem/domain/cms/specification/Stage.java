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

package ua.com.gfalcon.gesem.domain.cms.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since 1.0
 */
@Entity(name = "Stage")
@Table(name = "STAGES")
public class Stage extends ParentStage {

    @JsonProperty(value = "sName")
    @Column(name = "NAME", unique = true)
    private String name;

    @JsonProperty(value = "oParentStage")
    @ManyToOne
    private ParentStage parentStage;

    @JsonProperty(value = "oWorks")
    @ElementCollection
    @CollectionTable(name = "amount_works")
    @Column(name = "amount")
    @MapKeyJoinColumn(name = "work_id", referencedColumnName = "id")
    private Map<StagesWork, BigDecimal> works = new HashMap<>(); // работы и их объемы

    /**
     * номер по порядку для сортировки
     */
    @JsonProperty(value = "nSequence")
    @Column(name = "SEQUENCE")
    private Integer sequence;

    protected Stage() {

    }

    public Stage(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ParentStage getParentStage() {
        return parentStage;
    }

    public void setParentStage(ParentStage parentStage) {
        this.parentStage = parentStage;
    }

    public Map<StagesWork, BigDecimal> getWorks() {
        return works;
    }

    public void setWorks(Map<StagesWork, BigDecimal> works) {
        this.works = works;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Stage))
            return false;

        Stage stage = (Stage) o;

        return new EqualsBuilder()
                .append(name, stage.name)
                .append(parentStage, stage.parentStage)
                .append(works, stage.works)
                .append(sequence, stage.sequence)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(parentStage)
                .append(works)
                .append(sequence)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Stage{" +
                "name='" + name + '\'' +
                ", parentStage=" + parentStage +
                "} " + super.toString();
    }
}

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

import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Группировка работ по типам
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since on 04.01.2017
 */
@Entity(name = "WorksType")
@Table(name = "WORKS_TYPES")
public class WorksType extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "worksType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Work> works = new HashSet<>();

    /**
     * Стоимость и конечная дата действия стоимости
     */
    @OneToMany(mappedBy = "worksType", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<WorksPrice> cost = new HashSet<>();

    /**
     * Нормы расхода базовых материалов
     */
    @ElementCollection
    @CollectionTable(name = "basic_bom")
    @Column(name = "norm")
    @MapKeyJoinColumn(name = "material_id", referencedColumnName = "id")
    private Map<Material, BigDecimal> basicBOM = new HashMap<>();

    protected WorksType() {

    }

    public WorksType(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Work> getWorks() {
        return works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }

    public void addWork(Work work) {
        this.works.add(work);
    }

    public void removeWork(Work work) {
        this.works.remove(work);
    }

    public Set<WorksPrice> getCost() {
        return cost;
    }

    public void setCost(Set<WorksPrice> cost) {
        this.cost = cost;
    }

    public void addCost(WorksPrice cost) {
        this.cost.add(cost);
    }

    public void removeCost(WorksPrice cost) {
        this.cost.remove(cost);
    }

    public Map<Material, BigDecimal> getBasicBOM() {
        return basicBOM;
    }

    public void setBasicBOM(Map<Material, BigDecimal> basicBOM) {
        this.basicBOM = basicBOM;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof WorksType))
            return false;

        WorksType worksType = (WorksType) o;

        if (!name.equals(worksType.name))
            return false;
        if (works != null ? !works.equals(worksType.works) : worksType.works != null)
            return false;
        if (cost != null ? !cost.equals(worksType.cost) : worksType.cost != null)
            return false;
        return basicBOM != null ? basicBOM.equals(worksType.basicBOM) : worksType.basicBOM == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (works != null ? works.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (basicBOM != null ? basicBOM.hashCode() : 0);
        return result;
    }
}

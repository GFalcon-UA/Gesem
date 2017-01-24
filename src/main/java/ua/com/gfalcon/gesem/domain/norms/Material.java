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
import ua.com.gfalcon.gesem.domain.cms.specification.SpecificationsEntry;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since on 29.12.2016
 */
@Embeddable
@Entity(name = "Material")
@Table(name = "MATERIALS")
public class Material extends AbstractEntity{

    @Column(unique = true)
    private String name;

    @ManyToOne
    private MeasureUnit measureUnit;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<MaterialsPrice> costs = new HashSet<>();

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<SpecificationsEntry> specificationsEntries = new HashSet<>();

    protected Material() {

    }

    public Material(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Set<MaterialsPrice> getCosts() {
        return costs;
    }

    public void setCosts(Set<MaterialsPrice> costs) {
        this.costs = costs;
    }

    public Set<SpecificationsEntry> getSpecificationsEntries() {
        return specificationsEntries;
    }

    public void setSpecificationsEntries(
            Set<SpecificationsEntry> specificationsEntries) {
        this.specificationsEntries = specificationsEntries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Material material = (Material) o;

        if (!name.equals(material.name))
            return false;
        if (measureUnit != null ? !measureUnit.equals(material.measureUnit) : material.measureUnit != null)
            return false;
        if (costs != null ? !costs.equals(material.costs) : material.costs != null)
            return false;
        return specificationsEntries != null ?
                specificationsEntries.equals(material.specificationsEntries) :
                material.specificationsEntries == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (measureUnit != null ? measureUnit.hashCode() : 0);
        result = 31 * result + (costs != null ? costs.hashCode() : 0);
        result = 31 * result + (specificationsEntries != null ? specificationsEntries.hashCode() : 0);
        return result;
    }
}

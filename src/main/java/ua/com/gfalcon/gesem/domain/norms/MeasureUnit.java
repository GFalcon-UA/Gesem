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
import java.util.HashSet;
import java.util.Set;

/**
 * Единицы измерения
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since on 31.12.2016
 */
@Entity(name = "MeasureUnit")
@Table(name = "MEASURE_UNITS")
public class MeasureUnit extends AbstractEntity {

    @JsonProperty(value = "sName")
    @Column(name = "NAME", unique = true)
    private String name;

    @JsonProperty(value = "sSmallName")
    @Column(name = "SMALL_NAME")
    private String smallName;

    @JsonProperty(value = "aMaterials")
    @OneToMany(mappedBy = "measureUnit", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Material> materialSet = new HashSet<>();

    protected MeasureUnit() {

    }

    public MeasureUnit(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public Set<Material> getMaterialSet() {
        return materialSet;
    }

    public void addMaterial(Material material) {
        materialSet.add(material);
    }

    public void removeMaterial(Material material) {
        if (materialSet.contains(material)) {
            materialSet.remove(material);
        }
    }

    public void setMaterialSet(Set<Material> materialSet) {
        this.materialSet = materialSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MeasureUnit))
            return false;

        MeasureUnit that = (MeasureUnit) o;

        if (!name.equals(that.name))
            return false;
        if (smallName != null ? !smallName.equals(that.smallName) : that.smallName != null)
            return false;
        return materialSet != null ? materialSet.equals(that.materialSet) : that.materialSet == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (smallName != null ? smallName.hashCode() : 0);
        result = 31 * result + (materialSet != null ? materialSet.hashCode() : 0);
        return result;
    }
}

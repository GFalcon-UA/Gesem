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

package ua.com.gfalcon.gesem.domain.cms;

import ua.com.gfalcon.entitydao.AbstractEntity;
import ua.com.gfalcon.gesem.domain.cms.specification.Stage;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since 1.0
 */
@Entity(name = "BuidObject")
@Table(name = "BUILD_OBJECTS")
public class BuildObject extends AbstractEntity {

    private String name;

    @ManyToOne
    private Partner owner;

    @OneToMany(mappedBy = "parentStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stage> stages = new HashSet<>();

    private String address;

    private BigDecimal overheadCosts = new BigDecimal(0);

    protected BuildObject() {

    }

    public BuildObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Partner getOwner() {
        return owner;
    }

    public void setOwner(Partner owner) {
        this.owner = owner;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getOverheadCosts() {
        return overheadCosts;
    }

    public void setOverheadCosts(BigDecimal overheadCosts) {
        this.overheadCosts = overheadCosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BuildObject that = (BuildObject) o;

        if (!name.equals(that.name))
            return false;
        if (!owner.equals(that.owner))
            return false;
        if (stages != null ? !stages.equals(that.stages) : that.stages != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null)
            return false;
        return overheadCosts != null ? overheadCosts.equals(that.overheadCosts) : that.overheadCosts == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + owner.hashCode();
        result = 31 * result + (stages != null ? stages.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (overheadCosts != null ? overheadCosts.hashCode() : 0);
        return result;
    }
}

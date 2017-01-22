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
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Partner owner;

    @OneToMany(mappedBy = "parentStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stage> stages;

    @Column(name = "ADDRESS")
    private String address;

    @Column()
    private BigDecimal overheadCosts;

    public BuildObject(String name) {
        setName(name);
        setOverheadCosts(0);
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

    public void setOverheadCosts(double percent){
        setOverheadCosts(new BigDecimal(percent));
    }

    public void setOverheadCosts(int percent){
        setOverheadCosts(new BigDecimal(percent));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof BuildObject))
            return false;

        BuildObject that = (BuildObject) o;

        if (!getName().equals(that.getName()))
            return false;
        if (!getOwner().equals(that.getOwner()))
            return false;
        if (getStages() != null ? !getStages().equals(that.getStages()) : that.getStages() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null)
            return false;
        return getOverheadCosts() != null ?
                getOverheadCosts().equals(that.getOverheadCosts()) :
                that.getOverheadCosts() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getOwner().hashCode();
        result = 31 * result + (getStages() != null ? getStages().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getOverheadCosts() != null ? getOverheadCosts().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BuildObject{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                '}';
    }
}

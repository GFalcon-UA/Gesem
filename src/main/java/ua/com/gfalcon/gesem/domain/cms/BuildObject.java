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

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.gfalcon.gesem.domain.cms.specification.ParentStage;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since 1.0
 */
@Entity(name = "BuidObject")
@Table(name = "BUILD_OBJECTS")
public class BuildObject extends ParentStage {

    @JsonProperty(value = "sName")
    private String name;

    @JsonProperty(value = "oOwner")
    @ManyToOne
    private Partner owner;

    @JsonProperty(value = "sAddress")
    private String address;

    @JsonProperty(value = "nOverheadCosts")
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

        if (!(o instanceof BuildObject))
            return false;

        BuildObject that = (BuildObject) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .append(owner, that.owner)
                .append(address, that.address)
                .append(overheadCosts, that.overheadCosts)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(owner)
                .append(address)
                .append(overheadCosts)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BuildObject{" +
                "name='" + name + '\'' +
                ", owner=" + owner +
                ", address='" + address + '\'' +
                ", overheadCosts=" + overheadCosts +
                "} " + super.toString();
    }
}

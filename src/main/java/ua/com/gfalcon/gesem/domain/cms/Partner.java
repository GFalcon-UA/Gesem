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
import ua.com.gfalcon.entitydao.AbstractEntity;

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
@Entity(name = "Partner")
@Table(name = "PARTNERS")
public class Partner extends AbstractEntity {

    @JsonProperty(value = "sName")
    @Column(name = "NAME", unique = true)
    private String name;

    @JsonProperty(value = "aObjects")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<BuildObject> objects = new HashSet<>();

    @JsonProperty(value = "sPhones")
    @Column(name = "PHONES")
    private String phones;

    @JsonProperty(value = "sContactPersons")
    @Column(name = "CONTACT")
    private String contactPersons;

    @JsonProperty(value = "sCodeUSREOU")
    @Column(name = "CODE_USREOU")
    private String codeUSREOU;

    protected Partner() {

    }

    public Partner(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BuildObject> getObjects() {
        return objects;
    }

    public void setObjects(Set<BuildObject> objects) {
        this.objects = objects;
    }

    public void addObject(BuildObject object) {
        this.objects.add(object);
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getContactPersons() {
        return contactPersons;
    }

    public void setContactPersons(String contactPersons) {
        this.contactPersons = contactPersons;
    }

    public String getCodeUSREOU() {
        return codeUSREOU;
    }

    public void setCodeUSREOU(String codeUSREOU) {
        this.codeUSREOU = codeUSREOU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Partner partner = (Partner) o;

        if (!name.equals(partner.name))
            return false;
        if (objects != null ? !objects.equals(partner.objects) : partner.objects != null)
            return false;
        if (phones != null ? !phones.equals(partner.phones) : partner.phones != null)
            return false;
        if (contactPersons != null ? !contactPersons.equals(partner.contactPersons) : partner.contactPersons != null)
            return false;
        return codeUSREOU != null ? codeUSREOU.equals(partner.codeUSREOU) : partner.codeUSREOU == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (objects != null ? objects.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        result = 31 * result + (contactPersons != null ? contactPersons.hashCode() : 0);
        result = 31 * result + (codeUSREOU != null ? codeUSREOU.hashCode() : 0);
        return result;
    }
}

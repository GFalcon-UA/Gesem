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

import javax.persistence.*;
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

    @Column(name = "NAME", unique = true)
    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<BuildObject> objects;

    @Column(name = "PHONES")
    private String phones;

    @Column(name = "CONTACT")
    private String contactPersons;

    @Column(name = "USREOU")
    private String codeUSREOU;

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

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    /**
     *
     * @return телефоны компании
     */
    public String getContactPersons() {
        return contactPersons;
    }

    /**
     *
     * @param contactPersons Контактные лица
     */
    public void setContactPersons(String contactPersons) {
        this.contactPersons = contactPersons;
    }

    /**
     * получить код ЕДРПОУ
     * @return код ЕДРПОУ
     */
    public String getCodeUSREOU() {
        return codeUSREOU;
    }

    /**
     *
     * @param codeUSREOU код ЕДРПОУ
     */
    public void setCodeUSREOU(String codeUSREOU) {
        this.codeUSREOU = codeUSREOU;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Partner))
            return false;

        Partner partner = (Partner) o;

        if (!getName().equals(partner.getName()))
            return false;
        if (getObjects() != null ? !getObjects().equals(partner.getObjects()) : partner.getObjects() != null)
            return false;
        if (getPhones() != null ? !getPhones().equals(partner.getPhones()) : partner.getPhones() != null)
            return false;
        if (getContactPersons() != null ?
                !getContactPersons().equals(partner.getContactPersons()) :
                partner.getContactPersons() != null)
            return false;
        return getCodeUSREOU() != null ?
                getCodeUSREOU().equals(partner.getCodeUSREOU()) :
                partner.getCodeUSREOU() == null;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + (getObjects() != null ? getObjects().hashCode() : 0);
        result = 31 * result + (getPhones() != null ? getPhones().hashCode() : 0);
        result = 31 * result + (getContactPersons() != null ? getContactPersons().hashCode() : 0);
        result = 31 * result + (getCodeUSREOU() != null ? getCodeUSREOU().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "name='" + name + '\'' +
                ", codeUSREOU='" + codeUSREOU + '\'' +
                '}';
    }
}

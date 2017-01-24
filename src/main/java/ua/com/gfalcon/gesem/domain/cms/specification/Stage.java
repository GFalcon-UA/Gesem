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

import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since 1.0
 */
@Entity(name = "Stage")
@Table(name = "STAGES")
public class Stage extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @ManyToOne
    private Stage parentStage;

    @OneToMany(mappedBy = "parentStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stage> stages = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "amount_works")
    @Column(name = "amount")
    @MapKeyJoinColumn(name = "work_id", referencedColumnName = "id")
    private Map<StagesWork, BigDecimal> works = new HashMap<>(); // работы и их объемы

    protected Stage() {

    }

    public Stage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stage getParentStage() {
        return parentStage;
    }

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
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
        if (o == null || getClass() != o.getClass())
            return false;

        Stage stage = (Stage) o;

        if (!name.equals(stage.name))
            return false;
        if (parentStage != null ? !parentStage.equals(stage.parentStage) : stage.parentStage != null)
            return false;
        if (stages != null ? !stages.equals(stage.stages) : stage.stages != null)
            return false;
        return works != null ? works.equals(stage.works) : stage.works == null;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (parentStage != null ? parentStage.hashCode() : 0);
        result = 31 * result + (stages != null ? stages.hashCode() : 0);
        result = 31 * result + (works != null ? works.hashCode() : 0);
        return result;
    }
}

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
import ua.com.gfalcon.gesem.domain.norms.Work;

import javax.persistence.*;
import java.math.BigDecimal;
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
public class StageImpl extends AbstractEntity implements Stage {
    @Column(name = "NAME")
    private String name;

    @ManyToOne
    private Stage parentStage;

    @OneToMany(mappedBy = "parentStage", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Stage> stages;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "type")
    @MapKeyEnumerated
    private Map<Work, BigDecimal> works;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setParentStage(Stage parentStage) {

    }

    @Override
    public Set<Stage> getSubStages() {
        return stages;
    }

    @Override
    public void addWork(Work work, BigDecimal value) {
        works.put(work, value);
    }

    @Override
    public void removeWork(Work work) {
        works.remove(work);
    }

    @Override
    public void addWorks(Work work, BigDecimal quantity) {

    }

    @Override
    public Map<Work, BigDecimal> getWorks() {
        return null;
    }

}

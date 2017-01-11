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

import java.util.Date;
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
public class WorksType extends AbstractEntity {
    private String name;
    private Set<Work> works;

    /**
     * Стоимость и конечная дата действия стоимости
     */
    private Map<Date, Integer> cost;

    /**
     * Нормы расхода базовых материалов
     */
    private Map<Material, Integer> basicBOM;

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

    public Map<Date, Integer> getCost() {
        return cost;
    }

    public void setCost(Map<Date, Integer> cost) {
        this.cost = cost;
    }

    public Map<Material, Integer> getBasicBOM() {
        return basicBOM;
    }

    public void setBasicBOM(Map<Material, Integer> basicBOM) {
        this.basicBOM = basicBOM;
    }
}

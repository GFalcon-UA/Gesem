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

import com.fasterxml.jackson.databind.MappingJsonFactory;
import ua.com.gfalcon.entitydao.AbstractEntity;
import ua.com.gfalcon.gesem.domain.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since on 29.12.2016
 */
public class Work /*extends AbstractEntity */{
    private String name;
    private WorksType worksType;

    /**
     * Коэффициенты для расчета расхода базовых материалов
     */
    private Map<Material, Integer> basicBomCoeficients;

    /**
     * Нормы расхода специфических материалов
     */
    private Map<Material, Integer> specificBOM;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorksType getWorksType() {
        return worksType;
    }

    public void setWorksType(WorksType worksType) {
        this.worksType = worksType;
    }

    public Map<Material, Integer> getBasicBomCoeficients() {
        return basicBomCoeficients;
    }

    public void setBasicBomCoeficients(Map<Material, Integer> basicBomCoeficients) {
        this.basicBomCoeficients = basicBomCoeficients;
    }

    public Map<Material, Integer> getSpecificBOM() {
        return specificBOM;
    }

    public void setSpecificBOM(Map<Material, Integer> specificBOM) {
        this.specificBOM = specificBOM;
    }

    public Map<Material, Integer> getBillOfMaterial() {
        byte decimalRate = Config.getNumberDecimalRate();
        HashMap<Material, Integer> basicBOM = (HashMap<Material, Integer>) getWorksType().getBasicBOM();
        HashMap<Material, Integer> billOfMaterial = new HashMap<>();
        //todo добавить расчет норм расхода материалов
        return billOfMaterial;
    }

    public void setBillOfMaterial(Map<Material, Integer> bom) {

    }
}

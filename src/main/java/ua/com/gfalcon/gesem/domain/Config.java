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

package ua.com.gfalcon.gesem.domain;

import org.springframework.stereotype.Component;

/**
 * Created by Gesem
 *
 * @author Oleksii Khalikov
 * @version v-1.0
 * @since on 04.01.2017
 */
@Component
public class Config {
    private static byte currencyDecimalRate;
    private static byte numberDecimalRate;
    /**
     * Количество недель от даты активации пользователя, до даты истечения активации
     */
    public static final Integer WEEKS_YO_EXPIRE_DATE_ACTIVE_USER = 52;
    /**
     * Максимальное допустимое количество ошибочного ввода пароля
     */
    public static final Integer MAX_PASSWORD_FAILED = 10;

    public Config(){
        setCurrencyDecimalRate((byte) 2);
        setNumberDecimalRate((byte) 3);
    }

    public static byte getCurrencyDecimalRate() {
        return currencyDecimalRate;
    }

    public static void setCurrencyDecimalRate(byte currencyDecimalRate) {
        Config.currencyDecimalRate = currencyDecimalRate;
    }

    public static byte getNumberDecimalRate() {
        return numberDecimalRate;
    }

    public static void setNumberDecimalRate(byte numberDecimalRate) {
        Config.numberDecimalRate = numberDecimalRate;
    }
}

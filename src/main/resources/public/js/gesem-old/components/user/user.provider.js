/*
 * Copyright (c) 2016-2017 by Oleksii KHALIKOV.
 * ========================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';

    angular
        .module('gesem')
        .factory('$userProvider', function () {

            var rolesEnum = {
                Admin: 0,
                User: 1
            };

            var setUser = function (u) {
                if (u) {
                    if (u.bAdministrator) {
                        u.Roles = {
                            Admin: 0,
                            User: 1
                        };
                    } else {
                        u.Roles = {
                            User: 1
                        };
                    }
                    if (!u.bActivated) {
                        u.Roles = {};
                    }
                }
                this.user = u;
            };

            var getUser = function () {
                return this.user;
            };

            return {
                getUser: getUser,
                setUser: setUser,
                rolesEnum: rolesEnum
            }
        });
})();
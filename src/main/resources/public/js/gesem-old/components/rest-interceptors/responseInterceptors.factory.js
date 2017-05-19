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
    angular.module('gesem')
        .factory('responseInterceptor', ['$q', '$rootScope', '$injector', '$sce',
            function ($q, $rootScope, $injector, $sce) {

                var httpInterceptors = {

                    'response': function (response) {
                        /*var Modal= $injector.get('Modal');
                         if(response.status < 400 || (response.data && response.data.statusCode < 400)) {
                         return response || $q.when(response);
                         }

                         Modal.inform.warning()(response.data.message || response.data.serverMessage || angular.toJson(response));*/
                        return response || $q.when(response);
                    },

                    'responseError': function (rejection) {

                        var Modal = $injector.get('Modal');
                        var $http = $injector.get('$http');

                        /**
                         * Потеряна связь с сервером NodeJS
                         * либо отключился интернет
                         */
                        if (rejection.status == -1) {
                            alert("Виникла помилка при спробі звернення до сервера");
                            return rejection;
                        }

                        debugger;

                        Modal.inform.error()(rejection.data.message || rejection.data.serverMessage || (rejection.statusText === '' ? 'Виникла помилка: ' + rejection.status : rejection.statusText));

                        return $q.reject(rejection);
                    }
                };

                return httpInterceptors;
            }])
})();
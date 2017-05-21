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
        .config(['$routeProvider', '$httpProvider', '$locationProvider', 'csrfProvider',
            function ($routeProvider, $httpProvider, $locationProvider, csrfProvider) {

                $locationProvider.html5Mode(true);

                $routeProvider.when('/', {
                    templateUrl: 'js/home/home.html',
                    controller: 'home',
                    controllerAs: 'controller'
                }).when('/message', {
                    templateUrl: 'js/message/message.html',
                    controller: 'message',
                    controllerAs: 'controller'
                }).when('/login', {
                    templateUrl: 'js/navigation/login.html',
                    controller: 'navigation',
                    controllerAs: 'controller'
                }).otherwise('/');

                $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';


                // options (Object) - Options to customize the CSRF interceptor behavior.
                var csrfOption = {};
                // options.url (String) - The URL to which the initial CSRF request has to be made to get the CSRF token. Default: /.
                csrfOption.url = '/';
                // options.csrfHttpType (String) - The HTTP method type which should be used while requesting the CSRF token call. Default: head.
                csrfOption.csrfHttpType = 'head';
                // options.maxRetries (Number) - The number of retries allowed for CSRF token call in-case of 403 Forbidden response errors. Default: 5.
                csrfOption.maxRetries = 5;
                // options.csrfTokenHeader (String) - Set this option to add the CSRF headers only to some HTTP requests. Default: ['GET', 'HEAD', 'PUT', 'POST', 'DELETE'].
                csrfOption.csrfTokenHeader = 'X-CSRF-TOKEN';
                // options.httpTypes (Array) - Customize the name of the CSRF header on the requests. Default: X-CSRF-TOKEN.
                csrfOption.httpTypes = ['GET', 'HEAD', 'PUT', 'POST', 'DELETE'];
                csrfProvider.config(csrfOption);

            }]);

})();
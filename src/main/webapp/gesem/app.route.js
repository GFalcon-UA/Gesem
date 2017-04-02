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

/**
 * Created by GFalcon on 09.01.2017.
 */
(function () {
  'use strict';

  angular
    .module('gesem')
    .constant('baseUrl', window.location.href)
    .config(routeConfig);

  routeConfig.$inject = ['$routeProvider', '$locationProvider', 'baseUrl'];
  function routeConfig($routeProvider, $locationProvider, baseUrl) {

    $routeProvider
      .when('/', {
          redirectTo: 'login'
      });

    $routeProvider
        .when('/login', {
            templateUrl: "gesem/components/authorization/login.html",
            controller: 'LoginPageCtrl',
            controllerAs: 'vm'
        })
        .when('/users', {
            templateUrl: "gesem/app/users/users.html",
            controller: 'UsersCtrl',
            controllerAs: 'vm'
        });

    $routeProvider.otherwise({
      redirectTo: "/goToBack"
    });

      //$locationProvider.html5Mode(true);

  }

})();

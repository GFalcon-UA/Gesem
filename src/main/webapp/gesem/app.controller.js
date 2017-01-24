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
  angular.module('gesem').controller('MainCtrl', MainCtrl);

  MainCtrl.$inject = ['$route', '$routeParams', '$location', '$rootScope'];
  function MainCtrl($route, $routeParams, $location, $rootScope) {
    this.$route = $route;
    this.$location = $location;
    this.$routeParams = $routeParams;

    var previousSuccwssRoute = '';

    $rootScope.$on('$locationChangeStart', function (angularEvent, newUrl, oldUrl) {
      debugger;

    });

    $rootScope.$on('$routeChangeSuccess', function (angularEvent, current) {
      previousSuccwssRoute = current.$$route.originalPath;
    });

    $rootScope.$on('$routeChangeError', function (angularEvent, current) {
      debugger;
    })
  }

})();
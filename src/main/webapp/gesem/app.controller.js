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

    MainCtrl.$inject = [
        '$route', '$routeParams', '$location', '$rootScope', '$pagesSecurityService', '$userProvider'
    ];
    function MainCtrl($route, $routeParams, $location, $rootScope, $pagesSecurityService, $userProvider) {
        var vm = this;
        vm.$route = $route;
        vm.$location = $location;
        vm.$routeParams = $routeParams;

        vm.init = initMainController;

        var previousSuccessRoute;

        initMainController();

        function initMainController() {
            previousSuccessRoute = '';
        }

        $scope.goTo = function (path) {
            $location.path(path);
        };

        angular.extend($scope, $userProvider, true);

        $scope.$on('$locationChangeStart', function (event, nextUrl, prevUrl) {
            if (nextUrl.indexOf("/goToBack") >= 0) {
                if (previousSuccessRoute) {
                    $location.path(previousSuccessRoute);
                } else {
                    $location.path('/login');
                }
                return;
            }

            if ($location.path() !== '/login' || nextUrl.indexOf('login') == -1) {
                if (!$pagesSecurityService.checkAuthorize($location.path())) {
                    alert('Access denied!');
                    $location.path(prevUrl.split('#')[1]);
                }
            }
        });

        $rootScope.$on('$routeChangeSuccess', function (angularEvent, current) {
            previousSuccessRoute = current.$$route.originalPath;
        });

    }

})();
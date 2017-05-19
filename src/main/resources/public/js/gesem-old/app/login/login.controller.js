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
 * Created by GFalcon on 09.01.2017.
 */
(function () {
    'use strict';

    angular
        .module('gesem')
        .controller('LoginPageCtrl', LoginPageCtrl);

    LoginPageCtrl.$inject = ['UserService', '$userProvider', '$location', '$rootScope'];
    function LoginPageCtrl(UserService, $userProvider, $location, $rootScope) {
        var vm = this;

        vm.init = initController;
        vm.isRegisterPage = false;
        vm.oUser = {};
        vm.isProcessing = false;
        vm.auth = auth;
        vm.register = register;
        vm.changeView = changeView;

        // todo
        var sMainViewPath = '/users';

        initController();

        function initController() {
            vm.isRegisterPage = false;
            vm.oUser = {
                sLogin: '',
                sPassword: '',
                sConfirmPassword: ''
            };
        }

        function goToMainView() {
            $location.path(sMainViewPath);
        }

        function auth(oUser) {
            debugger;
            vm.isProcessing = true;
            UserService.auth(oUser).then(function (data) {
                $userProvider.setUser(data);
                debugger;
                goToMainView();
            }, function (err) {
                debugger;
            }).finally(function () {
                vm.isProcessing = false;
            })
        }

        function register(oUser) {
            vm.isProcessing = true;
            UserService.registration(oUser).then(function (data) {
                debugger;
            }, function (err) {
                debugger;
            }).finally(function () {
                vm.isProcessing = false;
            })
        }


        function changeView() {
            vm.isRegisterPage = !vm.isRegisterPage;
        }
    }
})();

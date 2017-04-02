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
      .module('authorizationModule')
    .controller('LoginPageCtrl', LoginPageCtrl);

  LoginPageCtrl.$inject = ['UserService'];
  function LoginPageCtrl(UserService) {
    var vm = this;

    vm.init = initController;
    vm.isRegisterPage = false;
    vm.oUser = {};
    vm.auth = auth;
    vm.register = register;
    vm.changeView = changeView;

    initController();

    function initController() {
        vm.isRegisterPage = false;
      vm.oUser = {
        sLogin: '',
        sPassword: '',
        sConfirmPassword: ''
      };
    }

    function auth(oUser) {
        UserService.auth(oUser).success(function (data) {
            debugger;
        }).error(function (err) {
            debugger;
        })
    }

    function register(oUser) {
      debugger;
      UserService.registration(oUser).success(function (data) {
        debugger;
      }).error(function (err) {
        debugger;
      })
    }

    function changeView() {
      vm.isRegisterPage = !vm.isRegisterPage;
    }
  }
})();
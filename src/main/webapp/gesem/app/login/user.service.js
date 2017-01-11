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

  angular.module('gesem').service('UserService', UserService);

  UserService.$inject = ['gfREST'];
  function UserService(gfREST) {

    var oUserService = {
      auth: authUser,
      registration: registerUser,
      checkLogin: checkLogin
    };

    return oUserService;

    function authUser(oUser) {
      var user = {
        login: oUser.sLogin,
        password: oUser.sPassword
      };

      gfREST.post('auth/authenticate', user).
      then(function (data) {
        debugger;
      }, function (err) {
        debugger;
      });
    }

    function registerUser(oUser) {
      var user = {
        login: oUser.sLogin,
        password: oUser.sPassword
      };

      gfREST.post('auth/register?login', user).
      then(function (data) {
        debugger;
      }, function (err) {
        debugger;
      });
    }

    function checkLogin(sLogin) {
      var req = {
        login: sLogin
      };

      gfREST.get('auth/checkLogin', req).
      then(function (data) {
        debugger;
      }, function (err) {
        debugger;
      });
    }

  }
})();

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

    angular.module('authorizationModule').service('UserService', UserService);

    UserService.$inject = ['$http', '$q', '$userProvider'];
    function UserService($http, $q, $userProvider) {

        var oUserService = {
            auth: authUser,
            registration: registerUser,
            checkLogin: checkLogin
    };

    function sendRestRequest(req, callback) {
      var cb = callback || angular.noop;
      var deferred = $q.defer();

        req.url = 'auth/' + req.url;

      $http(req).then(
        function (response) {
          deferred.resolve(response.data);
          return cb();
        },
        function (response) {
          deferred.reject(response);
          return cb(response);
        }.bind(this));
      return deferred.promise;
    }

        return oUserService;

        function authUser(oUser, callback) {
      var req = {
          method: 'POST',
          url: 'authenticate',
          params: {
              login: oUser.sLogin,
              password: oUser.sPassword
          }
      };
      return sendRestRequest(req, callback);
    }

        function registerUser(oUser, callback) {
      var req = {
        method: 'POST',
          url: 'register',
          params: {
              login: oUser.sLogin,
              password: oUser.sPassword
          }
      };
      return sendRestRequest(req, callback);
    }

        function checkLogin(sLogin, callback) {
      var req = {
          method: 'GET',
          url: 'checkLogin',
          params: {
              login: sLogin
          }
      };
      return sendRestRequest(req, callback);
    }

  }
})();

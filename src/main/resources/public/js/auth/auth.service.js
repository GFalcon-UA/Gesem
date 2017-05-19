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

  angular.module('auth')
    .factory('AuthService', ['$http', '$q', function ($http, $q) {

      var service = {};

      service.login = login;
      service.logout = logout;

      init();

      return service;

      function init() {
        console.debug('AuthService is initialized');
      }

      function sendRestRequest(req, callback) {
        var cb = callback || angular.noop;
        var deferred = $q.defer();

        req.url = req.url + "";

        $http(req).then(
          function (response) {
            var data;
            try {
              data = angular.fromJson(response.data);
            } catch (e) {
              data = response.data;
            }
            deferred.resolve(data);
            return cb();
          },
          function (response) {
            deferred.reject(response);
            return cb(response);
          }.bind(this));
        return deferred.promise;
      }

      function login(headers, callback) {
        var req = {
          method: 'GET',
          url: '/api/auth/getUser',
          headers: headers
        };
        return sendRestRequest(req, callback);
      }

      function logout(logoutUrl) {
        var req = {
          method: 'POST',
          url: logoutUrl,
          params: {},
          data: {}
        };
        return sendRestRequest(req)

      }

    }]);

})();
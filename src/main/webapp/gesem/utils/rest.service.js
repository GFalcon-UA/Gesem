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

  angular.module('gesem').service('gfREST', gfREST);

  gfREST.$inject = ['$http', '$q'];
  function gfREST($http, $q) {
    var self = this;

    self.serv = {
      get: getRequest,
      post: postRequest,
      del: deleteRequest,
      put: putRequest
    };

    function sendRestRequest(req, callback) {
      var cb = callback || angular.noop;
      var deferred = $q.defer();

      $http(req).then(
        function (response) {
          deferred.resolve(response.data);
          debugger;
          return cb();
        },
        function (response) {
          deferred.reject(response);
          debugger;
          return cb(response);
        }.bind(this));
      return deferred.promise;
    }

    return self.serv;

    function getRequest(sURL, oParams, callback) {
      var req = {
        method: 'GET',
        url: sURL,
        params: oParams
      };
      return sendRestRequest(req, callback);

    }

    function postRequest(sURL, oParams, oData, callback) {
      var req = {
        method: 'POST',
        url: sURL,
        data: oData,
        params: oParams
      };
      return sendRestRequest(req, callback);
    }

    function deleteRequest(sURL,oParams, callback) {
      var req = {
        method: 'DELETE',
        url: sURL,
        params: oParams
      };
      return sendRestRequest(req, callback);
    }

    function putRequest(sURL, oParams, oData, callback) {
      var req = {
        method: 'PUT',
        url: sURL,
        data: oData,
        params: oParams
      };
      return sendRestRequest(req, callback);
    }

  }


})();

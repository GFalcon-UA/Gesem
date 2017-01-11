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

    return self.serv;

    function sendRestRequest(req, callback) {
      req.responseType = 'json';
      var cb = callback || angular.noop;
      var deferred = $q.defer();

      $http(req).
        success(function (data, status) {
          var resp = {
            nStatus : status
          };
          try {
            resp.oData = angular.fromJson(data);
          } catch (err){
            resp.oData = data;
          }
          deferred.resolve(resp);
          return cb();
      }).
        error(function (data, status) {
          var error = {
            nErrorCode: status,
            oErrorData: data
          };
          deferred.reject(error);
          return cb(error);
      }.bind(this)).
        finally(function () {

      });

      return deferred.promise;
    }

    function getRequest(sURL, oParams, callback) {
      var req = {
        method: 'GET',
        url: sURL,
        params: oParams
      };
      return sendRestRequest(req, callback);

    }

    function postRequest(sURL, oData, oParams, callback) {
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

    function putRequest(sURL, oData, oParams, callback) {
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

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

    angular
        .module('gesem')
        .factory('UsersService', UsersService);

    UsersService.$inject = ['$http', '$q'];
    function UsersService($http, $q) {
        var oUsersService = {};

        oUsersService.getUsersList = getUsersList;
        oUsersService.deleteUserById = deleteUserById;
        oUsersService.updateUser = updateUser;

        function sendRestRequest(req, callback) {
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            req.url = '/api/users/' + req.url;

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

        return oUsersService;

        function getUsersList(callback) {
            var req = {
                method: 'GET',
                url: 'list',
                params: {}
            };
            return sendRestRequest(req, callback);
        }

        function deleteUserById(nUserId, callback) {
            var req = {
                method: 'DELETE',
                url: 'delete',
                params: {
                    nUserId: nUserId
                }
            };
            return sendRestRequest(req, callback);
        }

        function updateUser(oUser, callback) {
            var req = {
                method: 'PUT',
                url: 'update',
                params: {
                    nUserId: oUser.nID
                },
                data: {
                    bActivated: oUser.bActivated,
                    bAdministrator: oUser.bAdministrator,
                    sPassword: oUser.sPassword
                }
            };
            return sendRestRequest(req, callback);
        }

    }
})();
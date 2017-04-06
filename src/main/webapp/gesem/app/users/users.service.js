/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';

    angular
        .module('gesem')
        .service('UsersService', UsersService);

    UsersService.$inject = ['$http', '$q'];
    function UsersService($http, $q) {
        var oUsersService = {};

        oUsersService.getUsersList = getUsersList;
        oUsersService.deleteUserById = deleteUserById;
        oUsersService.updateUser = updateUser;

        function sendRestRequest(req, callback) {
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            req.url = 'users/' + req.url;

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
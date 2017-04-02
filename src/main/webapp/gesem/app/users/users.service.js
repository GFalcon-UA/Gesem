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
                url: 'delete_user_by_id',
                params: {
                    nUserId: nUserId
                }
            };
            return sendRestRequest(req, callback);
        }

    }
})();
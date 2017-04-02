/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';

    angular
        .module('authorizationModule')
        .factory('$userProvider', function () {

            var rolesEnum = {
                Admin: 0,
                User: 1
            };

            var setUser = function (u) {
                this.user = u;
            };

            var getUser = function () {
                return this.user;
            };

            return {
                getUser: getUser,
                setUser: setUser,
                rolesEnum: rolesEnum
            }
        });
})();
/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';

    angular
        .module('gesem')
        .factory('$userProvider', function () {

            var rolesEnum = {
                Admin: 0,
                User: 1
            };

            var setUser = function (u) {
                if (u) {
                    if (u.bAdministrator) {
                        u.Roles = {
                            Admin: 0,
                            User: 1
                        };
                    } else {
                        u.Roles = {
                            User: 1
                        };
                    }
                    if (!u.bActivated) {
                        u.Roles = {};
                    }
                }
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
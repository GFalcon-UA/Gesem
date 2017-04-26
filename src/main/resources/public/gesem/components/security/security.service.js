/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';
    angular.module('gesem')
        .factory('$pagesSecurityService', ['$userProvider', '$location',
            function ($userProvider, $location) {

                var checkAuthorize = function (path) {
                    if (!$userProvider.getUser()) {
                        $location.path('/login');
                    }
                    switch (path) {
                        case '/users':
                            return checkPageSecurity({
                                UserRoles: $userProvider.getUser().Roles,
                                AvailableRoles: [
                                    $userProvider.rolesEnum.Admin
                                ]
                            });
                        default:
                            return true;
                    }
                };

                var checkPageSecurity = function (config) {
                    var authorize = false;
                    for (var i in config.UserRoles) {
                        if ($.inArray(config.UserRoles[i], config.AvailableRoles) == -1) {
                            authorize = false;
                        } else {
                            authorize = true;
                            break;
                        }
                    }
                    debugger;
                    return authorize;
                };

                return {
                    checkAuthorize: checkAuthorize
                };
            }])
})();
/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
var authorizationModule = angular.module('authorizationModule', ['ngCookies']);

authorizationModule.controller('loginCtrl', ['$scope', 'authorizationFactory', '$location',
    function ($scope, authorizationFactory, $location) {
        $scope.loginClick = function () {
            if (authorizationFactory.login($scope.login, $scope.pass)) {
                $location.path('/books');
            } else {
                alert('Pass is 123456!');
            }
        }
    }]);

authorizationModule.factory('authorizationFactory', ['$userProvider',
    function ($userProvider) {

        var login = function (login, pass) {
            if (pass !== '123456') {
                return false;
            }
            if (login === 'admin') {
                $userProvider.setUser({Login: login, Roles: [$userProvider.rolesEnum.Admin]});
            } else {
                $userProvider.setUser({Login: login, Roles: [$userProvider.rolesEnum.User]});
            }
            return true;
        };

        return {
            login: login
        }
    }]);


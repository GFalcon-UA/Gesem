/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';

    angular
        .module('gesem')
        .controller('UsersCtrl', UsersCtrl);

    UsersCtrl.$inject = ['UsersService'];
    function UsersCtrl(UsersService) {
        var vm = this;

        vm.aUsers = [];

        vm.init = initController;

        vm.func = {
            getUsersList: getUsersList,
            deleteUser: deleteUser
        };

        vm.initController();

        function initController() {
            vm.func.getUsersList();
        }

        function getUsersList() {
            UsersService.getUsersList().then
            (function (data) {
                vm.aUsers = data;
            }, function (err) {
                console.error(err);
            })
        }

        function deleteUser(nUserId) {

        }


    }
})();
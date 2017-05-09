/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';

    angular
        .module('gesem')
        .controller('UsersCtrl', UsersCtrl);

    UsersCtrl.$inject = ['UsersService', 'Modal'];
    function UsersCtrl(UsersService, Modal) {
        var vm = this;

        vm.aUsers = {
            loaded: [],
            displayed: []
        };
        vm.isProcessing = false;

        vm.init = initController;

        vm.func = {
            getUsersList: getUsersList,
            deleteUser: deleteUser,
            editUser: editUser
        };

        vm.initController();

        function initController() {
            vm.func.getUsersList();
        }

        function getUsersList() {
            vm.isProcessing = true;
            UsersService.getUsersList().then
            (function (data) {
                vm.aUsers.loaded = data;
            }, function (err) {
                console.error(err);
            }).finally(function () {
                vm.isProcessing = false;
            })
        }

        function deleteUser(oUser) {
            Modal.confirm.delete(function (event) {
                vm.isProcessing = true;
                UsersService.deleteUserById(oUser.nID)
                    .then(function () {
                        UsersService.getUsersList().success(function (data) {
                            vm.aUsers.loaded = data;
                        });
                        Modal.inform.success()("Пользователь удален");
                    }, function (err) {
                        Modal.inform.error()("Ошибка при удалении пользоваетля " + angular.toJson(err));
                    }).finally(function () {
                    vm.isProcessing = false;
                });
            })('Пользователя ' + oUser.sLogin);
        }

        function editUser(oUser) {

        }


    }
})();
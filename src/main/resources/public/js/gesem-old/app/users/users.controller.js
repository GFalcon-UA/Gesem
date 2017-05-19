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
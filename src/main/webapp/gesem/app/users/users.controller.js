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

        vm.init = initController;
        vm.initController();

        function initController() {

        }

    }
})();
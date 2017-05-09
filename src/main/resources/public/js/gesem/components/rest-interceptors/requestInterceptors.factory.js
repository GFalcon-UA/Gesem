/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';
    angular.module('gesem')
        .factory('requestInterceptor', ['$q', '$rootScope', '$injector', '$sce',
            function ($q, $rootScope, $injector, $sce) {

                var httpInterceptors = {

                    'request': function (config) {
                        // что-то делает при успешном статусе ответа сервера

                        return config || $q.when(config);
                    },

                    'requestError': function (rejection) {
                        //var Modal= $injector.get('Modal');

                        return $q.reject(rejection);
                    }

                };

                return httpInterceptors;
            }])
})();
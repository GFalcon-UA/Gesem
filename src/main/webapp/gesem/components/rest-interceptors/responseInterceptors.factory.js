/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';
    angular.module('gesem')
        .factory('responseInterceptor', ['$q', '$rootScope', '$injector', '$sce',
            function ($q, $rootScope, $injector, $sce) {

                var httpInterceptors = {

                    'response': function (response) {
                        /*var Modal= $injector.get('Modal');
                         if(response.status < 400 || (response.data && response.data.statusCode < 400)) {
                         return response || $q.when(response);
                         }

                         Modal.inform.warning()(response.data.message || response.data.serverMessage || angular.toJson(response));*/
                        return response || $q.when(response);
                    },

                    'responseError': function (rejection) {

                        //var Modal= $injector.get('Modal');
                        var $http = $injector.get('$http');

                        /**
                         * Потеряна связь с сервером NodeJS
                         * либо отключился интернет
                         */
                        if (rejection.status == -1) {
                            alert("Виникла помилка при спробі звернення до сервера");
                            return rejection;
                        }

                        return $q.reject(rejection);
                    }
                };

                return httpInterceptors;
            }])
})();
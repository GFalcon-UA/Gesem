/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 */
(function () {
    'use strict';
    angular.module('gesem')
        .config(['$httpProvider',
            function ($httpProvider) {
                $httpProvider.interceptors.push('requestInterceptor');
                $httpProvider.interceptors.push('responseInterceptor');
            }])
})();
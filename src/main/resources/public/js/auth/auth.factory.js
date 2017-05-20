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

  angular.module('auth')
    .factory('auth', ['$rootScope', '$location', 'AuthService',
      function ($rootScope, $location, AuthService) {

        var enter = function () {
          if ($location.path() !== auth.loginPath) {
            auth.path = $location.path();
            if (!auth.authenticated) {
              $location.path(auth.loginPath);
            }
          }
        };

        var auth = {

          authenticated: false,

          loginPath: '/login',
          logoutPath: '/logout',
          homePath: '/',
          path: $location.path(),

          authenticate: function (credentials, callback) {

            var headers = credentials && credentials.username ? {
              authorization: "Basic "
              + btoa(credentials.username + ":"
                + credentials.password)
            } : {};

            AuthService.login(headers).then(function (response) {
              if (response.data && response.data.name) {
                auth.authenticated = true;
              } else {
                auth.authenticated = false;
              }
              callback && callback(auth.authenticated);
              $location.path(auth.path === auth.loginPath ? auth.homePath : auth.path);
            }, function () {
              auth.authenticated = false;
              callback && callback(false);
            });

          },

          clear: function () {
            $location.path(auth.loginPath);
            auth.authenticated = false;
            AuthService.logout(auth.logoutPath).then(function () {
              console.log("Logout succeeded");
            }, function () {
              console.log("Logout failed");
            });
          },

          init: function (homePath, loginPath, logoutPath) {

            auth.homePath = homePath;
            auth.loginPath = loginPath;
            auth.logoutPath = logoutPath;

            auth.authenticate({}, function (authenticated) {
              if (authenticated) {
                $location.path(auth.path);
              }
            });

            // Guard route changes and switch to login page if unauthenticated
            $rootScope.$on('$routeChangeStart', function () {
              enter();
            });

          }

        };

        return auth;

      }]);
})();
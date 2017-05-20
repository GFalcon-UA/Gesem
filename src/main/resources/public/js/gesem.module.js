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

  angular.module('gesem', [
    'auth'
    , 'home'
    , 'message'
    , 'navigation'
    , 'ngCookies'
  ])
    .run(['auth', '$http', '$cookies', function (auth, $http, $cookies) {
      // Initialize auth module with the home page and login/logout path
      // respectively
      auth.init('/', '/login', '/logout');
      //debugger;

      //$http.defaults.headers.post['X-XSRF-TOKEN'] = $cookies.csrftoken;

    }]);

})();


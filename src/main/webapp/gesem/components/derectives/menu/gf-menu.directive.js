/*
 * Copyright (c) 2016-2017 by Oleksii KHALIKOV.
 * ========================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Created by GFalcon on 08.01.2017.
 */
(function () {
    'use strict'

    angular.module('gesem')
        .directive('gfMenu', gfMenu);

    function gfMenu() {

        var directive = {
            link: link,
            restrict: 'EA',
            templateUrl: '/gesem/components/menu/gf-menu.html',
            replace: true,
            controller: 'MainCtrl',
            controllerAs: 'vm'
        };

        return directive;

        function link(scope, element, attrs) {
            /* */
        }

    }

})();
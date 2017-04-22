'use strict';

angular.module('gesem')
    .factory('Modal', function ($rootScope, $modal) {
        /**
         * Opens a modal
         * @param  {Object} scope      - an object to be merged with modal's scope
         * @param  {String} modalClass - (optional) class(es) to be applied to the modal
         * @return {Object}            - the instance $modal.open() returns
         */
        function openModal(scope, modalClass) {
            var modalScope = $rootScope.$new();
            scope = scope || {};
            modalClass = modalClass || 'modal-default';

            angular.extend(modalScope, scope);

            return $modal.open({
                templateUrl: 'components/modal/modal.html',
                windowClass: modalClass,
                scope: modalScope
            });
        }

        // Public API here
        return {

            inform: {
                info: function (callBack) {
                    return function () {
                        var args = Array.prototype.slice.call(arguments),
                            name = args.shift(),
                            warningModal;

                        warningModal = openModal({
                            modal: {
                                dismissable: true,
                                title: 'Информация',
                                html: '<strong>' + name + '</strong>',
                                buttons: [{
                                    classes: 'btn-info',
                                    text: 'Ok',
                                    click: function (e) {
                                        warningModal.close(e);
                                    }
                                }]
                            }
                        }, 'modal-info');

                        warningModal.result.then(function (event) {
                            if (callBack) {
                                callBack.apply(event, args);
                            }
                        });
                    };
                },

                success: function (callBack) {
                    return function () {
                        var args = Array.prototype.slice.call(arguments),
                            name = args.shift(),
                            warningModal;

                        warningModal = openModal({
                            modal: {
                                dismissable: true,
                                title: 'Успех!',
                                html: '<strong>' + name + '</strong>',
                                buttons: [{
                                    classes: 'btn-success',
                                    text: 'Ok',
                                    click: function (e) {
                                        warningModal.close(e);
                                    }
                                }]
                            }
                        }, 'modal-success');

                        warningModal.result.then(function (event) {
                            if (callBack) {
                                callBack.apply(event, args);
                            }
                        });
                    };
                },

                error: function (callBack) {
                    return function () {
                        var args = Array.prototype.slice.call(arguments),
                            name = args.shift(),
                            warningModal;

                        warningModal = openModal({
                            modal: {
                                dismissable: true,
                                title: 'Ошибка!',
                                html: '<strong>' + name + '</strong>',
                                buttons: [{
                                    classes: 'btn-danger',
                                    text: 'Ok',
                                    click: function (e) {
                                        warningModal.close(e);
                                    }
                                }]
                            }
                        }, 'modal-danger');

                        warningModal.result.then(function (event) {
                            if (callBack) {
                                callBack.apply(event, args);
                            }
                        });
                    };
                },

                warning: function (callBack) {
                    return function () {
                        var args = Array.prototype.slice.call(arguments),
                            name = args.shift(),
                            warningModal;

                        warningModal = openModal({
                            modal: {
                                dismissable: true,
                                title: 'Внимание!',
                                html: '<strong>' + name + '</strong>',
                                buttons: [{
                                    classes: 'btn-warning',
                                    text: 'Ок',
                                    click: function (e) {
                                        warningModal.close(e);
                                    }
                                }]
                            }
                        }, 'modal-warning');

                        warningModal.result.then(function (event) {
                            if (callBack) {
                                callBack.apply(event, args);
                            }
                        });
                    };
                }
            },
            /* Confirmation modals */
            confirm: {

                auth: function (callBack) {
                    callBack = callBack || angular.noop;

                    return function () {
                        var args = Array.prototype.slice.call(arguments),
                            name = args.shift(),
                            authModal;

                        authModal = openModal({
                            modal: {
                                dismissable: true,
                                title: 'Необходимо пройти авторизацию',
                                html: '<strong>' + name + '</strong>',
                                buttons: [{
                                    classes: 'btn-info',
                                    text: 'Авторизация',
                                    click: function (e) {
                                        authModal.close(e);
                                    }
                                }, {
                                    classes: 'btn-default',
                                    text: 'Отмена',
                                    click: function (e) {
                                        authModal.dismiss(e);
                                    }
                                }]
                            }
                        }, 'modal-info');

                        authModal.result.then(function (event) {
                            callBack.apply(event, args);
                        });
                    };
                },

                /**
                 * Create a function to open a delete confirmation modal (ex. ng-click='myModalFn(name, arg1, arg2...)')
                 * @param  {Function} del - callback, ran when delete is confirmed
                 * @return {Function}     - the function to open the modal (ex. myModalFn)
                 */
                delete: function (del) {
                    del = del || angular.noop;

                    /**
                     * Open a delete confirmation modal
                     * @param  {String} name   - name or info to show on modal
                     * @param  {All}           - any additional args are passed staight to del callback
                     */
                    return function () {
                        var args = Array.prototype.slice.call(arguments),
                            name = args.shift(),
                            deleteModal;

                        deleteModal = openModal({
                            modal: {
                                dismissable: true,
                                title: 'Подтвердить удаление',
                                html: '<p>Вы действительно хотите удалить <strong>' + name + '</strong> ?</p>',
                                buttons: [{
                                    classes: 'btn-danger',
                                    text: 'Удалить',
                                    click: function (e) {
                                        deleteModal.close(e);
                                    }
                                }, {
                                    classes: 'btn-default',
                                    text: 'Отмена',
                                    click: function (e) {
                                        deleteModal.dismiss(e);
                                    }
                                }]
                            }
                        }, 'modal-danger');

                        deleteModal.result.then(function (event) {
                            del.apply(event, args);
                        });
                    };
                }
            }
        };
    });

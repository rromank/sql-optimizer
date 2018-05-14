angular.module('sql-optimizer')
    .controller('QueryInputController', function ($rootScope) {
        $rootScope.section = 'query';
    })
    .directive('query', function () {
        return {
            restrict: 'E',
            controller: 'QueryInputController',
            templateUrl: 'optimizer/query.html'
        }
    });
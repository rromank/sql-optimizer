angular.module('sql-optimizer')
    .controller('QueryInputController', function ($rootScope, $scope, queryStorage) {
        $rootScope.section = 'query';

        this.$onInit = function () {
            $rootScope.query = queryStorage.getQuery(0).sql;
        };

        $scope.formatQuery = function () {
            $scope.query = $rootScope.query = sqlFormatter.format($rootScope.query);
        };

    })
    .directive('query', function () {
        return {
            restrict: 'E',
            controller: 'QueryInputController',
            templateUrl: 'optimizer/query.html'
        }
    });
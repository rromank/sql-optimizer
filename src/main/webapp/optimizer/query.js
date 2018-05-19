angular.module('sql-optimizer')
    .controller('QueryInputController', function ($rootScope, $scope, queryStorageService) {
        $rootScope.section = 'query';
        $rootScope.inputQuery = {};

        this.$onInit = function () {
            $rootScope.query = $scope.query = queryStorageService.getQuery(0).sql;
        };

        $scope.formatQuery = function () {
            $rootScope.query = $scope.query = sqlFormatter.format($rootScope.query);
        };

        $scope.queryChanged = function () {
            $rootScope.query = $scope.query;
        }
    })
    .directive('query', function () {
        return {
            restrict: 'E',
            controller: 'QueryInputController',
            templateUrl: 'optimizer/query.html'
        }
    });
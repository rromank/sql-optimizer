angular.module('sql-optimizer')
    .controller('SchemaInputController', function ($rootScope, $scope, queryStorage) {
        $rootScope.section = 'schema';

        this.$onInit = function () {
            $rootScope.schema = queryStorage.getQuery(0).schema;
        };

        $scope.formatSchema = function () {
            $scope.schema = $rootScope.schema = sqlFormatter.format($rootScope.schema);
        };
    })
    .directive('schema', function () {
        return {
            restrict: 'E',
            controller: 'SchemaInputController',
            templateUrl: 'optimizer/schema.html'
        }
    });
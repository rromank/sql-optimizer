angular.module('sql-optimizer')
    .controller('SchemaInputController', function ($rootScope, $scope, queryStorageService) {
        $rootScope.section = 'schema';

        this.$onInit = function () {
            $rootScope.schema = queryStorageService.getQuery(0).schema;
        };

        $scope.formatSchema = function () {
            $scope.schema = $rootScope.schema = sqlFormatter.format($rootScope.schema);
        };

        $scope.schemaChanged = function () {
            $rootScope.schema = $scope.schema;
        }
    })
    .directive('schema', function () {
        return {
            restrict: 'E',
            controller: 'SchemaInputController',
            templateUrl: 'optimizer/schema.html'
        }
    });
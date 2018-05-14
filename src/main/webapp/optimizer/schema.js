angular.module('sql-optimizer')
    .controller('SchemaInputController', function ($rootScope) {
        $rootScope.section = 'schema';
    })
    .directive('schema', function () {
        return {
            restrict: 'E',
            controller: 'SchemaInputController',
            templateUrl: 'optimizer/schema.html'
        }
    });
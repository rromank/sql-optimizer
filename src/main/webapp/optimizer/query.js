angular.module('sql-optimizer')
    .controller('QueryInputController', function ($rootScope) {
        $rootScope.section = 'query';
        $rootScope.query = 'SELECT * FROM columns WHERE table_schema = \'information_schema\'';
    })
    .directive('query', function () {
        return {
            restrict: 'E',
            controller: 'QueryInputController',
            templateUrl: 'optimizer/query.html'
        }
    });
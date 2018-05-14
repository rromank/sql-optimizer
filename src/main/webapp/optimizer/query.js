angular.module('sql-optimizer')
    .controller('QueryInputController', function ($scope) {
        $scope.aceValue = 'Foobar';
    })
    .directive('query', function () {
        return {
            restrict: 'E',
            controller: 'QueryInputController',
            templateUrl: 'optimizer/query.html'
        }
    });
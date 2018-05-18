angular.module('sql-optimizer')
    .directive('tip', function () {
        return {
            restrict: 'E',
            scope: {
                tip: '=tip'
            },
            templateUrl: 'recommendations/tip.html'
        }
    });
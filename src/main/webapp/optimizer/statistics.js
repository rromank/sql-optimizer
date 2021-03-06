angular.module('sql-optimizer')
    .controller('StatisticsController', function ($rootScope, $scope, explainRepository) {
        $rootScope.section = 'statistics';

        this.$onInit = function () {
            explainQuery();
        };

        var explainQuery = function () {
            if (!$rootScope.query || !$rootScope.schema) {
                console.log('Can not explain query, query or schema is not provided');
                return;
            }
            explainRepository.explainQuery($rootScope.query, $rootScope.schema).then(function (result) {
                console.log(result);
            });
        };
    })
    .directive('statistics', function () {
        return {
            restrict: 'E',
            controller: 'StatisticsController',
            templateUrl: 'optimizer/statistics.html'
        }
    });
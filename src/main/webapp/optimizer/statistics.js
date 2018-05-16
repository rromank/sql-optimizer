angular.module('sql-optimizer')
    .controller('StatisticsController', function ($rootScope, $scope, databaseRepository, explainRepository) {
        $rootScope.section = 'statistics';

        this.$onInit = function () {
            databaseRepository.createDatabase().then(function (database) {
                console.log(database);
                $scope.databaseName = database.name;

                explainQuery();
            });
        };

        var explainQuery = function () {
            if (!$rootScope.query || !$rootScope.schema || !$scope.databaseName) {
                console.log('Can not explain query, query or schema is not provided');
                return;
            }
            explainRepository.explainQuery($rootScope.query, $rootScope.schema, $scope.databaseName).then(function (result) {
                console.log(result);
            });
        };

        this.$onDestroy = function () {
            databaseRepository.dropDatabase($scope.databaseName).then(function () {
                console.log('Database dropped!');
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
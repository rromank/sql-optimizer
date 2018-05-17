angular.module('sql-optimizer')
    .config(function ($stateProvider) {
        $stateProvider
            .state('recommendations', {
                url: '/recommendations',
                views: {
                    'main': {
                        templateUrl: 'recommendations/recommendations.html',
                        controller: 'RecommendationsController'
                    }
                }
            })
    })
    .controller('RecommendationsController', function ($rootScope, $scope, explainRepository) {
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
    });
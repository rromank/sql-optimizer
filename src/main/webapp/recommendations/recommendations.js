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
    .controller('RecommendationsController', function ($rootScope, $scope, recommendationRepository, tipsService) {
        var self = this;
        $scope.tips = [];

        self.$onInit = function () {
            explainQuery();
        };

        var explainQuery = function () {
            if (!$rootScope.query || !$rootScope.schema) {
                console.log('Can not explain query, query or schema is not provided');
                return;
            }
            recommendationRepository.checkForRecommendations($rootScope.query, $rootScope.schema)
                .then(getTips);
        };

        var getTips = function (recommendations) {
            for (var i = 0; i < recommendations.length; i++) {
                var tip = tipsService.getTip(recommendations[i].type);
                if (tip) {
                    $scope.tips.push(tip);
                }
            }
            console.log($scope.tips);
        };
    });
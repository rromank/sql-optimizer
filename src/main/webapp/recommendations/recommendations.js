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
        $scope.wideTips = [];
        $scope.narrowTips = [];

        self.$onInit = function () {
            checkForRecommendations();
        };

        var checkForRecommendations = function () {
            if (!$rootScope.query || !$rootScope.schema) {
                console.log('Can not explain query, query or schema is not provided');
                return;
            }
            recommendationRepository.checkForRecommendations($rootScope.query, $rootScope.schema).then(getTips);
        };

        var getTips = function (recommendations) {
            var tips = [];
            for (var i = 0; i < recommendations.length; i++) {
                var tip = tipsService.getTip(recommendations[i].type);
                if (tip) {
                    tip.sql = recommendations[i].sql.join('\n');
                    tips.push(tip);
                }
            }

            console.log(tips);

            $scope.wideTips = _.where(tips, {'width': 'wide'});
            $scope.narrowTips = _.where(tips, {'width': 'narrow'});

            console.log($scope.wideTips);
            console.log($scope.narrowTips);
        };
    });
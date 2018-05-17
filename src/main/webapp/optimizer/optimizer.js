angular.module('sql-optimizer')
    .config(function ($stateProvider) {
        $stateProvider
            .state('optimizer', {
                abstract: true,
                views: {
                    'main': {
                        templateUrl: 'optimizer/optimizer.html'
                    }
                }
            })
            .state('optimizer.query', {
                url: '/optimizer/query',
                views: {
                    'optimizer': {
                        template: '<query></query>'
                    }
                }
            })
            .state('optimizer.schema', {
                url: '/optimizer/schema',
                views: {
                    'optimizer': {
                        template: '<schema></schema>'
                    }
                }
            })
            .state('optimizer.statistics', {
                url: '/optimizer/statistics',
                views: {
                    'optimizer': {
                        template: '<statistics></statistics>'
                    }
                }
            });
    }).controller('OptimizerController', function ($scope) {
    });
angular.module('sql-optimizer')
    .factory('recommendationRepository', function ($http, config) {
        var checkForRecommendations = function (query, schema) {
            var request = {
                query: query,
                schema: schema
            };

            return $http.post(config.apiUrl + 'recommendation/query', request)
                .then(function (response) {
                    return response.data;
                });
        };

        return {
            checkForRecommendations: checkForRecommendations
        }
    });

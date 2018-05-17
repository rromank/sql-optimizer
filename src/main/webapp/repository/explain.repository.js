angular.module('sql-optimizer')
    .factory('explainRepository', function ($http, config) {
        var explainQuery = function (query, schema) {
            var request = {
                query: query,
                schema: schema
            };

            return $http.post(config.apiUrl + 'explain/query', request)
                .then(function (response) {
                    return response.data;
                });
        };

        return {
            explainQuery: explainQuery
        }
    });

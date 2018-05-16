angular.module('sql-optimizer')
    .factory('explainRepository', function ($http, config) {
        var explainQuery = function (query, schema, database) {
            var request = {
                query: query,
                schema: schema,
                database: database
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

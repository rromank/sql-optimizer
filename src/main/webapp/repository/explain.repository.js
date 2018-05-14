angular.module('sql-optimizer')
    .factory('explainRepository', function ($http, config) {
        var explainQuery = function (sql) {
            return $http.post(config.apiUrl + '/api/v1/explain', sql)
                .then(function (response) {
                    return response.data;
                });
        };

        return {
            explainQuery: explainQuery,
        }
    });

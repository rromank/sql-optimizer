angular.module('sql-optimizer')
    .factory('databaseRepository', function ($http, config) {
        var createDatabase = function () {
            return $http.post(config.apiUrl + 'database/create')
                .then(function (response) {
                    return response.data;
                });
        };

        var dropDatabase = function (databaseName) {
            return $http.post(config.apiUrl + 'database/drop/' + databaseName)
                .then(function (response) {
                    return response.data;
                });
        };

        return {
            createDatabase: createDatabase,
            dropDatabase: dropDatabase
        }
    });

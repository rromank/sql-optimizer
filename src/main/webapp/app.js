var app = angular.module('sql-optimizer', [
    'ngRoute',
    'ngCookies',
    'ui.router',
    'ngStorage'
])
    .constant('config', {
        apiUrl: 'http://localhost:8080/api/v1/'
    })
    .config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
        $locationProvider.hashPrefix('!');
        $routeProvider.otherwise({redirectTo: '/'});
    }]);

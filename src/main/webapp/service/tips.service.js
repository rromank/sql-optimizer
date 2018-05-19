angular.module('sql-optimizer')
    .factory('tipsService', function () {
        var tips = [
            {
                type: 'ASTERISK',
                title: 'Avoid Selecting Unnecessary Columns',
                description: 'Avoid selecting all columns with the \'*\' wildcard, unless you intend to use them all. Selecting redundant columns may result in unnecessary performance degradation.',
                bestPractice: 'SELECT col1, col2 FROM table',
                badPractice: 'SELECT * FROM table',
                width: 'col-6'
            },
            {
                type: 'INDEX',
                title: 'Indexing Tips',
                description: 'The most important step in query optimization is creating the optimal indexes.<br>' +
                'Please execute the following statements on your database to create the indexes required for optimal query performance.',
                suggestions: [],
                width: 'col-12'
            }
        ];

        var getTip = function (recommendationType) {
            return _.findWhere(tips, {type: recommendationType});
        };

        return {
            getTip: getTip
        }
    });

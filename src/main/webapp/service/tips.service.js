angular.module('sql-optimizer')
    .factory('tipsService', function () {
        var tips = [{
            type: 'ASTERISK',
            title: 'Avoid Selecting Unnecessary Columns',
            description: 'Avoid selecting all columns with the \'*\' wildcard, unless you intend to use them all. Selecting redundant columns may result in unnecessary performance degradation.',
            bestPractice: 'SELECT col1, col2 FROM table',
            badPractice: 'SELECT * FROM table'
        }];

        var getTip = function (recommendationType) {
            return _.findWhere(tips, {type: recommendationType});
        };

        return {
            getTip: getTip
        }
    });

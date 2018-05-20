angular.module('sql-optimizer')
    .factory('tipsService', function () {
        var tips = [
            {
                type: 'INDEX',
                title: 'Indexing Tips',
                description: 'The most important step in query optimization is creating the optimal indexes.\n' +
                'Please execute the following statements on your database to create the indexes required for optimal query performance.',
                suggestions: [],
                width: 'wide'
            },
            {
                type: 'ASTERISK',
                title: 'Avoid Selecting Unnecessary Columns',
                description: 'Avoid selecting all columns with the \'*\' wildcard, unless you intend to use them all. Selecting redundant columns may result in unnecessary performance degradation.',
                bestPractice: 'SELECT col1, col2 FROM table',
                badPractice: 'SELECT * FROM table',
                width: 'narrow'
            },
            {
                type: 'GROUP_BY_WITHOUT_ORDER_BY',
                title: 'Explicitly ORDER BY After GROUP BY',
                description: 'By default, MySQL sorts all \'GROUP BY col1, col2, ...\' queries as if you specified \'ORDER BY col1, col2, ...\' in the query as well. If a query includes a GROUP BY clause but you want to avoid the overhead of sorting the result, you can suppress sorting by specifying \'ORDER BY NULL\'.',
                bestPractice: 'SELECT\n' +
                '  age,\n' +
                '  COUNT(*)\n' +
                'FROM\n' +
                '  employees\n' +
                'WHERE\n' +
                '  age > 50\n' +
                'GROUP BY\n' +
                '  age\n' +
                'ORDER BY\n' +
                '  NULL;',
                badPractice: 'SELECT\n' +
                '  age,\n' +
                '  COUNT(*)\n' +
                'FROM\n' +
                '  employees\n' +
                'WHERE\n' +
                '  age > 50\n' +
                'GROUP BY\n' +
                '  age;',
                width: 'narrow'
            },
            {
                type: 'LIKE_WITH_LEADING_WILDCARD',
                title: 'Avoid LIKE Searches With Leading Wildcard',
                description: 'MySQL will not use an index when using like searches with a leading wildcard (i.e, \'%bar%\'). Although it\'s not always a satisfactory solution, please consider using prefix-match LIKE patterns (i.e, \'TERM%\').',
                bestPractice: 'SELECT\n' +
                '  id,\n' +
                '  text\n' +
                'FROM\n' +
                '  tbl\n' +
                'WHERE\n' +
                '  text LIKE \'TERM%\';',
                badPractice: 'SELECT\n' +
                '  id,\n' +
                '  text\n' +
                'FROM\n' +
                '  tbl\n' +
                'WHERE\n' +
                '  text LIKE \'%TERM%\';',
                width: 'narrow'
            },
            {
                type: 'SUBQUERIES',
                title: 'Avoid Subqueries',
                description: 'The MySQL team advises against using subqueries as they are not optimized well by the optimizer. Therefore, it\'s recommended to join a newly created temporary table that holds the data, which also includes the relevant search index.',
                width: 'narrow'
            }
        ];

        var getTip = function (recommendationType) {
            return _.findWhere(tips, {type: recommendationType});
        };

        return {
            getTip: getTip
        }
    });

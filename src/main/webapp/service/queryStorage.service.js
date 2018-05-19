angular.module('sql-optimizer')
    .factory('queryStorageService', function () {
        var queries = [{
            sql: 'SELECT *\n' +
            'FROM\n' +
            '   test_table tbl1\n' +
            'GROUP BY\n' +
            '   tbl1.name',
            schema: 'CREATE TABLE `test_table` (\n' +
            '  `id` INT(11) NOT NULL AUTO_INCREMENT,\n' +
            '  `name` VARCHAR(50) NOT NULL DEFAULT \'0\',\n' +
            '  `model` VARCHAR(50) NOT NULL DEFAULT \'0\',\n' +
            '  PRIMARY KEY (`id`)\n' +
            ') ENGINE = InnoDB;\n'
        }, {
            sql: 'SELECT * FROM columns WHERE table_schema = \'information_schema\'',
            schema: 'CREATE TABLE `COLUMNS` (\n' +
            '  `TABLE_CATALOG` VARCHAR(512) NOT NULL DEFAULT \'\',\n' +
            '  `TABLE_SCHEMA` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '  `TABLE_NAME` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '  `COLUMN_NAME` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '  `ORDINAL_POSITION` BIGINT(21) UNSIGNED NOT NULL DEFAULT \'0\',\n' +
            '  `COLUMN_DEFAULT` LONGTEXT NULL,\n' +
            '  `IS_NULLABLE` VARCHAR(3) NOT NULL DEFAULT \'\',\n' +
            '  `DATA_TYPE` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '  `CHARACTER_MAXIMUM_LENGTH` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '  `CHARACTER_OCTET_LENGTH` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '  `NUMERIC_PRECISION` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '  `NUMERIC_SCALE` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '  `CHARACTER_SET_NAME` VARCHAR(32) NULL DEFAULT NULL,\n' +
            '  `COLLATION_NAME` VARCHAR(32) NULL DEFAULT NULL,\n' +
            '  `COLUMN_TYPE` LONGTEXT NOT NULL,\n' +
            '  `COLUMN_KEY` VARCHAR(3) NOT NULL DEFAULT \'\',\n' +
            '  `EXTRA` VARCHAR(27) NOT NULL DEFAULT \'\',\n' +
            '  `PRIVILEGES` VARCHAR(80) NOT NULL DEFAULT \'\',\n' +
            '  `COLUMN_COMMENT` VARCHAR(1024) NOT NULL DEFAULT \'\'\n' +
            ') COLLATE = \'utf8_general_ci\' ENGINE = MyISAM;\n'
        }];

        var getQuery = function (index) {
            return queries[index];
        };

        return {
            getQuery: getQuery
        }
    });

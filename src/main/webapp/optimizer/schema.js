angular.module('sql-optimizer')
    .controller('SchemaInputController', function ($rootScope) {
        $rootScope.section = 'schema';
        $rootScope.schema = 'CREATE TABLE `COLUMNS` (\n' +
            '        `TABLE_CATALOG` VARCHAR(512) NOT NULL DEFAULT \'\',\n' +
            '        `TABLE_SCHEMA` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '        `TABLE_NAME` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '        `COLUMN_NAME` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '        `ORDINAL_POSITION` BIGINT(21) UNSIGNED NOT NULL DEFAULT \'0\',\n' +
            '        `COLUMN_DEFAULT` LONGTEXT NULL,\n' +
            '        `IS_NULLABLE` VARCHAR(3) NOT NULL DEFAULT \'\',\n' +
            '        `DATA_TYPE` VARCHAR(64) NOT NULL DEFAULT \'\',\n' +
            '        `CHARACTER_MAXIMUM_LENGTH` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '        `CHARACTER_OCTET_LENGTH` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '        `NUMERIC_PRECISION` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '        `NUMERIC_SCALE` BIGINT(21) UNSIGNED NULL DEFAULT NULL,\n' +
            '        `CHARACTER_SET_NAME` VARCHAR(32) NULL DEFAULT NULL,\n' +
            '        `COLLATION_NAME` VARCHAR(32) NULL DEFAULT NULL,\n' +
            '        `COLUMN_TYPE` LONGTEXT NOT NULL,\n' +
            '        `COLUMN_KEY` VARCHAR(3) NOT NULL DEFAULT \'\',\n' +
            '        `EXTRA` VARCHAR(27) NOT NULL DEFAULT \'\',\n' +
            '        `PRIVILEGES` VARCHAR(80) NOT NULL DEFAULT \'\',\n' +
            '        `COLUMN_COMMENT` VARCHAR(1024) NOT NULL DEFAULT \'\'\n' +
            '        )\n' +
            '        COLLATE=\'utf8_general_ci\'\n' +
            '        ENGINE=MyISAM\n' +
            '        ;'
    })
    .directive('schema', function () {
        return {
            restrict: 'E',
            controller: 'SchemaInputController',
            templateUrl: 'optimizer/schema.html'
        }
    });
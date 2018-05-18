module.exports = function (grunt) {
    grunt.initConfig({
        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: [
                    './src/main/webapp/app.js',
                    './src/main/webapp/**/*.js'
                ],
                dest: './build/resources/main/public/js/sql-optimizer.concat.js'
            }
        },

        uglify: {
            app: {
                options: {
                    compress: {
                        drop_debugger: false
                    },
                    mangle: false,
                    preserveComments: 'all',
                    beautify: true,
                    ASCIIOnly: true
                },
                files: {
                    './build/resources/main/public/js/sql-optimizer.js': [
                        './build/resources/main/public/js/sql-optimizer.concat.js'
                    ]
                }
            },
            vendor: {
                options: {
                    compress: false,
                    mangle: false,
                    preserveComments: 'all',
                    beautify: false,
                    ASCIIOnly: true
                },
                files: {
                    './build/resources/main/public/js/vendor.js': [
                        './node_modules/angular/angular.js',

                        './node_modules/codemirror/lib/codemirror.js',
                        './node_modules/angular-ui-codemirror/src/ui-codemirror.js',

                        './node_modules/ace-builds/src-min-noconflict/ace.js',
                        './node_modules/angular-ui-ace/src/ui-ace.js',

                        './node_modules/angular-route/angular-route.js',
                        './node_modules/@uirouter/angularjs/release/angular-ui-router.js',
                        './node_modules/angular-cookies/angular-cookies.min.js',
                        './node_modules/ngstorage/ngStorage.min.js',
                        './node_modules/underscore/underscore-min.js',
                        './node_modules/angular-sanitize/angular-sanitize.js',
                        './node_modules/angular-highlightjs/build/angular-highlightjs.min.js',
                        './node_modules/sql-formatter/dist/sql-formatter.min.js',
                        './node_modules/underscore/underscore-min.js'
                    ]
                }
            }
        },

        copy: {
            js:  {
                expand: true,
                flatten: true,
                src: [
                    './node_modules/ace-builds/src-min-noconflict/**/*.js'
                ],
                dest: './build/resources/main/public/'
            },
            css: {
                expand: true,
                src: [
                    './node_modules/bootstrap/dist/css/bootstrap.css',
                    './node_modules/font-awesome/css/font-awesome.min.css',
                    './node_modules/font-awesome/fonts/**.*',
                    './node_modules/codemirror/lib/codemirror.css'
                ],
                dest: './build/resources/main/public/css/'
            },
            images: {
                expand: true,
                flatten: true,
                src: [
                    './src/main/webapp/public/images/*.*'
                ],
                dest: './build/resources/main/public/images/'
            },
            fonts: {
                expand: true,
                flatten: true,
                src: [
                    './src/main/webapp/fonts/*.*'
                ],
                dest: './build/resources/main/public/fonts/'
            }
        },

        html2js: {
            options: {
                module: 'partials',
                base: './src/main/webapp'
            },
            main: {
                src: ['./src/main/webapp/**/*.html'],
                dest: './build/resources/main/public/js/partials.js'
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-html2js');

    grunt.registerTask('default', ['concat', 'uglify', 'copy', 'html2js']);
};
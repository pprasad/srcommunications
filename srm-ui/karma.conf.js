module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine'],
    reporters: ['progress','junit','coverage'],
    files: [
            { pattern: 'main.js',watched:false}
    ],
    exclude: [
    ],
    preprocessors: {
    	'main.js': ['webpack','coverage']
    },
    webpack: require('./webpack.config'),
    junitReporter: {
        outputFile: 'report/junit/TESTS-xunit.html'
    },
    htmlReporter: {
        outputFile: 'report/index.html'
    },
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: false,
    browsers: ['Chrome'],
    singleRun: true,
    concurrency: Infinity
  })
}
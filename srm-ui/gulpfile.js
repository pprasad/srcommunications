var path = require('path');
var gulp = require('gulp');
var gutil = require('gulp-util');
var uglify = require('gulp-uglify');
var minifyCSS = require('gulp-cssnano');
var autoprefixer = require('gulp-autoprefixer');
var minifyHTML = require('gulp-htmlmin');
var concat = require('gulp-concat');
var del = require('del');
var webpack = require('webpack-stream');
var htmlReplace = require('gulp-html-replace');
var cssimport = require("gulp-cssimport");
var browserSync = require('browser-sync');
var $ = require('gulp-load-plugins')();
var wiredep = require('wiredep').stream;
var _ = require('lodash');
/*Reload page if any changes in html/js/css*/
var plumber   = require('gulp-plumber');
var sass      = require('gulp-sass');
var webserver = require('gulp-webserver');
var opn       = require('opn');
var options = {};

var server = {
  host: 'localhost',
  port: 3000,
  path: '/srm/index.php'
}

gulp.task('clean', function() {
   return del.sync(['dist/**']);
});
gulp.task('webpack', ['clean'], function() {
  return gulp.src('src/app/main.ts')
    .pipe(webpack(require('./webpack.config.js')))
    .pipe(gulp.dest('src/'));
});

gulp.task('js', ['webpack'], function() {
  return gulp.src('src/srcomms.min.js')
    .pipe(uglify({mangle:true}))
    .pipe(gulp.dest('dist/'));
});

gulp.task('minify-css',['clean'],function() {
	  return gulp.src(['node_modules/bootstrap/dist/css/bootstrap.css','node_modules/bootstrap/dist/css/bootstrap.css.map','node_modules/bootstrap/dist/css/bootstrap-theme.css','node_modules/bootstrap/dist/css/bootstrap-theme.css.map','webapp/UI/app/styles/*.css'])
	    .pipe(concat('srcomms.min.css'))
	    .pipe(gulp.dest('dist/'));
});

gulp.task('copy:fonts',['clean'],function() {
	 return gulp.src(['node_modules/bootstrap/dist/fonts/*'])
    .pipe(gulp.dest('fonts/'))
});
gulp.task('copy:il8n',['clean'],function(){
    return gulp.src(['/src/i18n/**'])
		.pipe(gulp.dest('dist/'))
})
gulp.task('prod', ['js','minify-css',"copy:fonts"], function() {
  // add git push heroku master;heroku ps:scale web=1
  return del.sync(['src/srcomms.min.js'])
});
/*Watching changes on js/html/css*/
gulp.task('dev:js',['clean'], function() {
  return gulp.src('src/app/main.ts')
    .pipe(webpack(require('./webpack.config.js')))
    .pipe(gulp.dest('dist/'));
});
gulp.task('dev:css',function(){
   return gulp.src(['node_modules/bootstrap/dist/css/bootstrap.css',
     'node_modules/bootstrap/dist/css/bootstrap.css.map',
     'node_modules/bootstrap/dist/css/bootstrap-theme.css',
     'node_modules/bootstrap/dist/css/bootstrap-theme.css.map',
     'src/styles/style.css'])
	    .pipe(concat('srcomms.min.css'))
	    .pipe(gulp.dest('dist/'));
})
gulp.task('copy:fonts',function(){
   return gulp.src(['node_modules/bootstrap/dist/fonts/*.*'])
   .pipe(gulp.dest('fonts'));
})
gulp.task('copy:i18n',function(){
   return gulp.src(['src/assets/i18n/*.*'])
   .pipe(gulp.dest('i18n'));
})
gulp.task('webserver', function() {
  gulp.src( '.' )
    .pipe(webserver({
      host:             server.host,
      port:             server.port,
      livereload:       true,
      directoryListing: false
    }));
});
gulp.task('openbrowser', function() {
  var path='http://' + server.host + ':' + server.port;
  path=server.port==80?(path+server.path):path;
  opn(path);
});
gulp.task('watch', function(){
  gulp.watch(['src/app/**/*.*','src/styles/style.css'],['dev:js','dev:css','copy:fonts','copy:i18n']);
});
gulp.task('live',['dev:js','dev:css','copy:fonts','copy:i18n','webserver', 'watch','openbrowser']);
gulp.task('dev',['js','dev:css','copy:fonts','copy:i18n']);
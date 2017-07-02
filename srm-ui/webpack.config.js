var path = require('path');
var webpack = require('webpack');
var CopyWebpackPlugin = require('copy-webpack-plugin');
module.exports = {
	entry : './src/app/main',
	output : {
		path : __dirname + '/src',
		publicPath : 'src/',
		filename : 'srcomms.min.js'
	},
	module : {
		loaders: [{
			test : /\.ts$/,
			loaders : [ 'ts-loader','angular2-template-loader'],
			exclude: /(node_modules)/
		},{
			test : /\.html$/,
			loader : 'html-loader'
		},{
			test: /\.scss$/,
			loaders:['css-to-string-loader','css-loader','sass-loader']
		},{
		   test: /\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot|ico)$/, 
		   loader:'file-loader?name=fonts/[name].[hash].[ext]?'
		}]
	},
	resolve : {
		root: path.resolve(__dirname),
		modulesDirectories:['node_modules'],
		extensions:['','.ts', '.js','.umd.js','.css', '.scss', '.html'],
	},
	plugins :[new webpack.optimize.OccurrenceOrderPlugin(true)],
	devtool: 'inline-source-map'
};

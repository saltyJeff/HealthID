var webpack = require('webpack');
module.exports = {
	entry: "./src/index.js",
	output: {
		path: __dirname,
		publicPath: __dirname,
		filename: "bundle.js"
	},
	module: {
		rules: [{
			test: /\.tag$/,
			use: {
				loader: "riot-tag-loader",
				options: {
					hot: false
				}
			}
		}]
	},
	devtool: 'source-map'
}

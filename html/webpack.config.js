var webpack = require('webpack');
module.exports = {
	entry: "./src/index.js",
	output: {
		path: __dirname,
		filename: "bundle.js"
	},
	module: {
		rules: [{
			test: /\.tag$/,
			use: {
				loader: "riot-tag-loader",
				options: {
					hot: true
				}
			}
		}]	
	},
	plugins: [
		new webpack.NamedModulesPlugin(),
		new webpack.HotModuleReplacementPlugin()
	]
}

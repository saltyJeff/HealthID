var webpack = require('webpack');
module.exports = {
	entry: "./src/index.js",
	output: {
		path: __dirname,
		publicPath: __dirname,
		filename: "bundle.js"
	},
	module: {}
}

var compressjs = require('compressjs');
var algorithm = compressjs.Lzp3;
var base64js = require('./base64-js');
module.exports = {
	makePatient: function (str) {
        var compressed = base64js.toByteArray(str);
        var decompressed = algorithm.decompressFile(compressed);
        var jsonStr = new Buffer(decompressed).toString('utf8');
        return JSON.parse(jsonStr);
	},
	makeString: function(str) {
        var encoder = new TextEncoder('utf-8');
        var buffer = encoder.encode(str);
        var compressed = algorithm.compressFile(buffer);
        var newStr = base64js.fromByteArray(compressed);
        console.log(newStr);
        return newStr;
	}
}
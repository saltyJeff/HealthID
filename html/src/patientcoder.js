var compressjs = require('compressjs');
var algorithm = compressjs.Lzp3;
var base64js = require('base64-js');
module.exports = {
	makePatient: function (str) {
		var bytes = base64js.toByteArray(str);
        var decoded = (new TextDecoder('utf-8')).decode(bytes);
        var decompressed = algorithm.decompressFile(compressed);
        var jsonStr = new Buffer(decompressed).toString('utf8');
        return JSON.parse(jsonStr);
	},
	makeString: function(patient) {
		var str = JSON.stringify(patient);
        var encoder = new TextEncoder('utf-8');
        var buffer = encoder.encode(str);
        var compressed = algorithm.compressFile(buffer);
        var newStr = base64js.fromByteArray(compressed);
        return newStr;
	}
}
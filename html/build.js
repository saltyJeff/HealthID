var browserify = require('browserify');
var fs = require('fs');

var b = browserify('src/index.js');
b.transform('riotify');
b.transform('deamdify');

b.bundle().pipe(fs.createWriteStream('bundle.js'));
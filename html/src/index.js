require('./view/app-view.tag');
//bad practice but i'm tired as fuck
require('./edit/app-edit.tag');
var riot = require('riot');

riot.mount('*');
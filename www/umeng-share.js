cordova.define("cordova-plugin-umeng-share.UMengShare", function(require, exports, module) {
var exec = require('cordova/exec');
module.exports = {

    init:function (successHandler,errorHandler){
        exec(successHandler,errorHandler,"UMengShare","init",[]);
    },
    share:function (text,title,url,imgUrl,successHandler,errorHandler){
        exec(successHandler,errorHandler,"UMengShare","share",[text,title,url,imgUrl]);
    },

    // 直接分享，不会出现分享面板
    directShare:function (platform, text,title,url,imgUrl,successHandler,errorHandler){
        exec(successHandler,errorHandler,"UMengShare","directShare",[platform, text, title, url, imgUrl]);
    }

};
});

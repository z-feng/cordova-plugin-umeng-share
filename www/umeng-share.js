var exec = require('cordova/exec');
module.exports = {
    init:function (successHandler,errorHandler){
        exec(successHandler,errorHandler,"UMengShare","init",[]);
    },
    share:function (text,title,url,imgUrl,successHandler,errorHandler){
        exec(successHandler,errorHandler,"UMengShare","share",[text,title,url,imgUrl]);
    }
};
package com.hoolai.cordova.umeng.share;

import android.view.Gravity;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import com.hoolai.hongcai.MainActivity;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.Log;


public class UMengShare extends CordovaPlugin{

    private final static String WXAppId="wx12342956d1cab4f9";
    private final static String WXAppSecret="a5ae111de7d9ea137e88a5e02c07c94d";
    private final static String QQAppId="1103720765";
    private final static String QQAppKey="BHJ96WiKIltClmbh";
    private final static String SinaSSOAppKey="";
    private final static String SinaSSOAppSecret="";

    @Override
    public boolean execute(String action,JSONArray args,CallbackContext callbackContext) throws JSONException{
        if(action.equals("init")){
            init(args,callbackContext);
        }else if(action.equals("share")){
            share(args,callbackContext);
        }
        return false;
    }

    private void init(JSONArray args,CallbackContext callbackContext){
        Config.DEBUG = true;
        PlatformConfig.setWeixin(WXAppId,WXAppSecret);
        PlatformConfig.setQQZone(QQAppId,QQAppKey);
        PlatformConfig.setSinaWeibo(SinaSSOAppKey,SinaSSOAppSecret, "");

        callbackContext.success();
    }

    private void share(final JSONArray args,final CallbackContext callbackContext){

        cordova.getActivity().runOnUiThread(new Runnable(){
            @Override
            public void run(){
                String text=args.optString(0);
                String title=args.optString(1);
                String url=args.optString(2);
                String imgUrl=args.optString(3);

                final SHARE_MEDIA[] displaylist=new SHARE_MEDIA[]{
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.QQ
                };

                ShareAction action =  new ShareAction(cordova.getActivity());
                UMWeb umWeb = new UMWeb(url);
                umWeb.setTitle(title);
                umWeb.setThumb(new UMImage(cordova.getActivity(), imgUrl));
                action.withText(text)
                        .setDisplayList(displaylist)
                        .withMedia(umWeb)
                        .setCallback(getListener())
                        .open();
                callbackContext.success();
            }
        });
    }

    private UMShareListener getListener() {
        final UMShareListener umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
                UMShareAPI umShareAPI = UMShareAPI.get(cordova.getActivity().getApplicationContext());
                if (!umShareAPI.isInstall(cordova.getActivity(), platform)) {
                    Toast toaster = Toast.makeText(cordova.getActivity(), "没有安装应用", Toast.LENGTH_SHORT);
                    toaster.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toaster.show();
                }

            }
            @Override
            public void onResult(SHARE_MEDIA platform) {
                Log.d("plat","platform"+platform);

                Toast.makeText(cordova.getActivity(), "分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                if(!UMShareAPI.get(cordova.getActivity().getApplicationContext()).isInstall(cordova.getActivity(), platform)){
                   return;
                }

                Toast.makeText(cordova.getActivity(), "分享失败啦", Toast.LENGTH_SHORT).show();
                if(t!=null){
                    Log.d("throw","throw:"+t.getMessage());
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(cordova.getActivity(), "分享取消了", Toast.LENGTH_SHORT).show();
            }
        };
        return umShareListener;
    }
}
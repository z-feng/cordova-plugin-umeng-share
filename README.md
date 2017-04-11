# cordova-plugin-umeng-share

cordova友盟分享插件，暂时只支持分享到微信、QQ以及微博，后续会添加更多平台，并提供选择功能。

基于友盟最新SDK v6.4.3(2017-04-05)


## 支持平台

 - Android
 - IOS

## 安装

### 1. 克隆代码

```
git clone https://github.com/z-feng/cordova-plugin-umeng-share.git
```

### 2. 修改各平台配置

打开根目录下的 `plugin.xml` 文件

- ####  （i）修改友盟 `API_KEY`
找到
```
<meta-data android:name="UMENG_APPKEY" android:value="API_KEY"/>
```
将 `API_KEY` 为友盟平台申请的 `API_KEY`；


- ####（ii）修改微信配置
找到，

```
<dict>
    <key>CFBundleURLName</key>
    <string>weixin</string>
    <key>CFBundleURLSchemes</key>
    <array>
        <string>wxd9a39c7122aa6516</string>
    </array>
</dict>
```
将 `wxd9a39c7122aa6516` 修改为 `wx` + 微信应用 `appId`；

- #### （iii）修改QQ平台配置
找到，

```
<dict>
    <key>CFBundleURLName</key>
    <string>QQ</string>
    <key>CFBundleURLSchemes</key>
    <array>
        <string>QQ41E97DA9</string>
    </array>
</dict>
```
将 `QQ41E97DA9` 修改为 `QQ` + 腾讯QQ互联应用 `appId` 转换成十六进制（不足8位前面补0），例如“`QQ05FC5B14`”；

找到，

```
<dict>
    <key>CFBundleURLName</key>
    <string>tencent</string>
    <key>CFBundleURLSchemes</key>
    <array>
        <string>tencent1105821097</string>
    </array>
</dict>
```
将 `tencent1105821097` 修改为 `tencent1105821097` + 腾讯QQ互联应用 `appId`；

- #### （IV）修改微博配置
找到，

```
<dict>
    <key>CFBundleURLName</key>
    <string>sina</string>
    <key>CFBundleURLSchemes</key>
    <array>
        <string>wb126663232</string>
    </array>
</dict>
```
将 `wb126663232` 修改为 `wb` + 新浪 `appkey`；





### 3. 安装
```
cordova plugin add ${path}/cordova-plugin-umeng-share
```
其中，`path` 为插件所在路径。

## 使用

### 初始化

可在 `$ionicPlatform.ready` 函数中执行 `init`，只需要执行一次。
```
UMengShare.init(function () {
	console.log('UMengShare init success');
});
```

### 分享

在需要的地方，直接调用
```
UMengShare.share('内容','标题','http://www.baidu.com',’https://wwww.example.com/logo.png’, function(){
    console.log('分享成功');
}, function(){
    console.log('分享失败');
});

```

### 注意事项

由于苹果ATS限制，如需使用网络图片，确保URL为HTTPS图片链接，以便于`U-Share SDK` 下载并进行分享，否则会分享失败。

#import <Cordova/CDV.h>
#import <UMSocialCore/UMSocialCore.h>
#import "UMSocialWechatHandler.h"
#import "UMSocialSinaHandler.h"
#import "UMSocialQQHandler.h"
#import <UShareUI/UShareUI.h>

#define UmengAppkey @"507fcab25270157b37000010"
#define WXAppId @"wxdafdsafdsfsaf"
#define WXAppSecret @"507fcab25270157b37000010"
#define QQAppId @""
#define QQAppKey @""
#define SinaSSOAppKey @""
#define SinaSSOAppSecret @""

@interface UMengShare : CDVPlugin<UMSocialShareMenuViewDelegate>
 - (void)init:(CDVInvokedUrlCommand*)command;
 - (void)share:(CDVInvokedUrlCommand*)command;
 - (void)directShare:(CDVInvokedUrlCommand*)command;
@end

#ifdef __OBJC__
#import <UIKit/UIKit.h>
#else
#ifndef FOUNDATION_EXPORT
#if defined(__cplusplus)
#define FOUNDATION_EXPORT extern "C"
#else
#define FOUNDATION_EXPORT extern
#endif
#endif
#endif

#import "CDV.h"
#import "CDVAppDelegate.h"
#import "CDVAvailability.h"
#import "CDVAvailabilityDeprecated.h"
#import "CDVCommandDelegate.h"
#import "CDVCommandDelegateImpl.h"
#import "CDVCommandQueue.h"
#import "CDVConfigParser.h"
#import "CDVInvokedUrlCommand.h"
#import "CDVPlugin+Resources.h"
#import "CDVPlugin.h"
#import "CDVPluginResult.h"
#import "CDVScreenOrientationDelegate.h"
#import "CDVTimer.h"
#import "CDVURLProtocol.h"
#import "CDVUserAgentUtil.h"
#import "CDVViewController.h"
#import "CDVWebViewEngineProtocol.h"
#import "CDVWhitelist.h"
#import "NSDictionary+CordovaPreferences.h"
#import "NSMutableArray+QueueAdditions.h"
#import "CDVDebug.h"
#import "CDVJSON_private.h"
#import "CDVPlugin+Private.h"
#import "CDVGestureHandler.h"
#import "CDVHandleOpenURL.h"
#import "CDVIntentAndNavigationFilter.h"
#import "CDVLocalStorage.h"
#import "CDVLogger.h"
#import "CDVUIWebViewDelegate.h"
#import "CDVUIWebViewEngine.h"
#import "CDVUIWebViewNavigationDelegate.h"

FOUNDATION_EXPORT double CordovaVersionNumber;
FOUNDATION_EXPORT const unsigned char CordovaVersionString[];


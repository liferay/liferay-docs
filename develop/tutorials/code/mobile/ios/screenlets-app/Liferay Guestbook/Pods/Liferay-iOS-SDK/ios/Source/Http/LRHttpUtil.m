/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

#import "LRHttpUtil.h"

#import "LRBatchSession.h"
#import "LRRedirectDelegate.h"
#import "LRResponseParser.h"

NSString *const LR_BLANK = @"";
NSString *const LR_GET = @"GET";
NSString *const LR_HEAD = @"HEAD";
NSString *const LR_JSONWS_PATH_V61 = @"api/secure/jsonws";
NSString *const LR_JSONWS_PATH_V62 = @"api/jsonws";
NSString *const LR_POST = @"POST";

static NSString *_JSONWS_PATH;

/**
 * @author Bruno Farache
 */
@implementation LRHttpUtil

typedef void (^LRHandler)(
	NSData *data, NSURLResponse *response, NSError *error);

+ (void)initialize {
	if (!_JSONWS_PATH) {
		_JSONWS_PATH = LR_JSONWS_PATH_V62;
	}
}

+ (NSString *)encodeURL:(NSString *)URL {
	NSString *include = @":/?#[]@!$ &'()*+,;=\"<>%{}|\\^~`";

	return [LRHttpUtil escape:URL include:include ignore:nil];
}

+ (NSString *)escape:(NSString *)string include:(NSString *)include
		ignore:(NSString *)ignore {

	CFStringRef stringRef = (__bridge CFStringRef)string;
	CFStringRef includeRef = (__bridge CFStringRef)include;
	CFStringRef ignoreRef = (__bridge CFStringRef)ignore;

	return CFBridgingRelease(
		CFURLCreateStringByAddingPercentEscapes(
			kCFAllocatorDefault, stringRef, ignoreRef, includeRef,
			CFStringConvertNSStringEncodingToEncoding(NSUTF8StringEncoding)));
}

+ (NSMutableURLRequest *)getRequestWithSession:(LRSession *)session
		URL:(NSURL *)URL {

	NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:URL
		cachePolicy:NSURLRequestReloadIgnoringLocalCacheData
		timeoutInterval:session.connectionTimeout];

	[request setAllHTTPHeaderFields:session.headers];

	return request;
}

+ (NSURL *)getURL:(LRSession *)session path:(NSString *)path {
	NSString *server = session.server;

	if (![server hasSuffix:@"/"]) {
		server = [NSString stringWithFormat:@"%@/", server];
	}

	NSString *URL = [NSString stringWithFormat:@"%@%@%@", server, _JSONWS_PATH,
		path];

	return [NSURL URLWithString:URL];
}

+ (NSArray *)post:(LRSession *)session command:(NSDictionary *)command
		error:(NSError **)error {

	NSArray *commands = [NSArray arrayWithObject:command];

	return [self post:session commands:commands error:error];
}

+ (NSArray *)post:(LRSession *)session commands:(NSArray *)commands
		error:(NSError **)error {

	NSURL *URL = [self getURL:session path:@"/invoke"];

	NSMutableURLRequest *request = [self getRequestWithSession:session URL:URL];

	NSData *body = [NSJSONSerialization dataWithJSONObject:commands options:0
		error:error];

	if (body == nil) {
		return nil;
	}

	[request setHTTPBody:body];
	[request setHTTPMethod:LR_POST];
	[request setValue:@"application/json; charset=utf-8"
		forHTTPHeaderField:@"Content-Type"];

	if (session.authentication) {
		[session.authentication authenticate:request];
	}

	return [self _sendRequest:request session:session error:error];
}

+ (void)setJSONWSPath:(NSString *)path {
	_JSONWS_PATH = path;
}

+ (void)_dispatchMainThread:(void (^)(void))block {
	dispatch_async(dispatch_get_main_queue(), ^{
		block();
	});
}

+ (id)_sendRequest:(NSMutableURLRequest *)request session:(LRSession *)session
		error:(NSError **)error {

	if (session.callback) {
		[self _sendAsynchronousRequest:request session:session];

		return nil;
	}
	else {
		NSHTTPURLResponse *response;

		NSData *data = [NSURLConnection sendSynchronousRequest:request
			returningResponse:&response error:error];

		if (*error) {
			return nil;
		}

		return [LRResponseParser parse:data request:request response:response
			error:error];
	}
}

+ (void)_sendAsynchronousRequest:(NSURLRequest *)request
		session:(LRSession *)session {

	id<LRCallback> callback = session.callback;

	LRHandler handler = ^(NSData *data, NSURLResponse *response, NSError *e) {
			if (e) {
				[self _dispatchMainThread: ^{
					[callback onFailure:e];
				}];
			}
			else {
				NSError *serverError;
				NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;

				id json = [LRResponseParser parse:data request:request
					response:httpResponse error:&serverError];

				if (serverError) {
					[self _dispatchMainThread: ^{
						[callback onFailure:serverError];
					}];

					return;
				}

				if ([session isKindOfClass:[LRBatchSession class]]) {
					[self _dispatchMainThread: ^{
						[callback onSuccess:json];
					}];
				}
				else if ([json isKindOfClass:[NSArray class]]) {
					[self _dispatchMainThread: ^{
						[callback onSuccess:[json objectAtIndex:0]];
					}];
				}
			}
	};

	NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration
		ephemeralSessionConfiguration];

	LRRedirectDelegate *delegate = [[LRRedirectDelegate alloc] init];

	NSURLSession *urlSession = [NSURLSession
		sessionWithConfiguration:configuration delegate:delegate
		delegateQueue:session.queue];

	NSURLSessionDataTask *task = [urlSession dataTaskWithRequest:request
		completionHandler:handler];

	[task resume];
}

@end
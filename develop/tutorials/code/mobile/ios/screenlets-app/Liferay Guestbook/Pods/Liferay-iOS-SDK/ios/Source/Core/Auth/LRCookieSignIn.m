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

#import "LRCookieSignIn.h"
#import "LRBasicAuthentication.h"
#import "LRCookieAuthentication.h"
#import "LRError.h"
#import "LRHttpUtil.h"

@interface LRCookieSignIn() <NSURLSessionDelegate,
		NSURLSessionDataDelegate, NSURLSessionTaskDelegate>

@property (nonatomic) id<LRCookieCallback> callback;
@property (nonatomic, copy) NSString *server;
@property (nonatomic, copy) NSString *username;
@property (nonatomic, copy) NSString *password;
@property (nonatomic) NSMutableData *responseData;
@property (nonatomic) void (^challengeBlock)(NSURLAuthenticationChallenge *challenge,
		void (^)(NSURLSessionAuthChallengeDisposition, NSURLCredential *));

@end

/**
 * @author Victor Galán
 */
@implementation LRCookieSignIn

- (instancetype) init {
	self = [super init];

	if (self) {
		self.responseData = [[NSMutableData alloc] init];
	}

	return self;
}

+ (void)signInWithSession:(LRSession *)session
		callback:(id<LRCookieCallback>)callback {
	[self signInWithSession:session callback:callback challengeBlock:nil];
}

+ (void)signInWithSession:(LRSession *)session
		callback:(id<LRCookieCallback>)callback
		challengeBlock: (void (^)(NSURLAuthenticationChallenge *challenge,
			void (^)(NSURLSessionAuthChallengeDisposition,
			NSURLCredential *))) challengeBlock {

	LRCookieSignIn *cookieSignIn = [[LRCookieSignIn alloc] init];
	cookieSignIn.challengeBlock = challengeBlock;

	[cookieSignIn _signInWithSession:session callback:callback];
}

- (void)URLSession:(NSURLSession *)session
		task:(NSURLSessionTask *)task
		willPerformHTTPRedirection:(NSHTTPURLResponse *)response
		newRequest:(NSURLRequest *)request
		completionHandler:(void (^)(NSURLRequest *))completionHandler {

	NSString *cookies = [self _getHttpCookies:
		[NSHTTPCookieStorage sharedHTTPCookieStorage]
			requestURL:response.URL];

	NSMutableURLRequest *mutableRequest = request.mutableCopy;

	[mutableRequest addValue:[NSString stringWithFormat:@"%@", cookies]
		  forHTTPHeaderField:@"Cookie"];

	completionHandler(mutableRequest);
}

- (void)URLSession:(NSURLSession *)session task:(NSURLSessionTask *)task
		didReceiveChallenge:(NSURLAuthenticationChallenge *)challenge
		completionHandler:(void (^)(NSURLSessionAuthChallengeDisposition,
			NSURLCredential *))completionHandler {

	if (challenge.previousFailureCount == 0) {
		if (self.challengeBlock) {
			self.challengeBlock(challenge, completionHandler);
		}
		else {
			completionHandler(NSURLSessionAuthChallengePerformDefaultHandling, nil);
		}
	}
	else {
		NSHTTPURLResponse *response = (NSHTTPURLResponse *)challenge.failureResponse;
		[self.callback onFailure:[LRError errorWithCode:response.statusCode
			description:@"Error authenticating"
			userInfo:@{@"underlyingResponse": response}]];
	}
}

- (void)URLSession:(NSURLSession *)session
		dataTask:(NSURLSessionDataTask *)dataTask
		didReceiveData:(NSData *)data {

	[self.responseData appendData:data];
}

-(void)URLSession:(NSURLSession *)session
		task:(NSURLSessionTask *)task didCompleteWithError:(NSError *)error {

	if (error != nil) {
		NSHTTPURLResponse *response = (NSHTTPURLResponse *)task.response;

		if(response.statusCode == 500) {
			error = [LRError errorWithCode:403
				description:@"Failed to get the cookie auth"];
		}

		[self.callback onFailure:error];
	}
	else {
		NSString *html = [[NSString alloc]initWithData: self.responseData
			encoding:NSUTF8StringEncoding];
		NSString *authToken = [self _getAuthToken:html];

		if (!authToken) {
			error = [LRError errorWithCode:400 description:@"Failed to get the auth token"];
			[self.callback onFailure:error];
			return;
		}

		NSString *cookieHeader =
			[self _getHttpCookies:[NSHTTPCookieStorage sharedHTTPCookieStorage]
				requestURL:task.response.URL];

		[[NSHTTPCookieStorage sharedHTTPCookieStorage]
		 setCookieAcceptPolicy:NSHTTPCookieAcceptPolicyOnlyFromMainDocumentDomain];

		LRCookieAuthentication *auth = [[LRCookieAuthentication alloc]
			initWithAuthToken:authToken
			cookieHeader:cookieHeader
			username:self.username
			password:self.password];

		LRSession *cookieSession = [[LRSession alloc]
			initWithServer:self.server authentication:auth];

		[self.callback onSuccess:cookieSession];
	}
}

- (NSString *)_getAuthToken:(NSString *)html {

	NSRegularExpression *regex = [NSRegularExpression
		regularExpressionWithPattern:@"Liferay.authToken\\s*=\\s*[\"'](.{8})[\"']"
		options:0 error:nil];

	NSTextCheckingResult *match = [regex firstMatchInString:html
		options:0 range:NSMakeRange(0, html.length)];

	if (match.numberOfRanges < 2) {
		return nil;
	}

	NSRange range = [match rangeAtIndex:1];

	if (range.location == NSNotFound) {
		return nil;
	}

	return [html substringWithRange:range];
}

- (NSData *)_getBodyWithUsername:(NSString *)username
	password:(NSString *) password {

	NSString *bodyString = [NSString stringWithFormat:@"login=%@&password=%@",
		username, password];

	return [bodyString dataUsingEncoding:NSASCIIStringEncoding];
}

- (NSString *)_getHttpCookies:(NSHTTPCookieStorage *)storage
	   requestURL:(NSURL *)requestURL {

	NSArray *allCookies = [storage cookiesForURL:requestURL];

	NSMutableString *cookieHeader = [NSMutableString string];
	
	for(NSHTTPCookie *cookie in allCookies) {
		[cookieHeader appendFormat:@"%@=%@;", cookie.name, cookie.value];
	}

	return cookieHeader;
}

- (NSURL *)_getLoginURL:(NSString *)server {
	if (![server hasSuffix:@"/"]) {
		server = [server stringByAppendingString:@"/"];
	}

	server = [server stringByAppendingString:@"c/portal/login"];

	return [NSURL URLWithString:server];
}

- (void)_signInWithSession:(LRSession *)session
	callback:(id<LRCookieCallback>)callback {

	self.server = session.server;
	self.callback = callback;

	[[NSHTTPCookieStorage sharedHTTPCookieStorage]
		setCookieAcceptPolicy:NSHTTPCookieAcceptPolicyAlways];

	id<LRAuthentication> authentication = session.authentication;

	if (!authentication) {
		[NSException raise:@"" format:@"Session authentication can't be null"];
	}

	if ([authentication isKindOfClass:[LRBasicAuthentication class]]) {
		LRBasicAuthentication *basicAuth = authentication;
		self.username = basicAuth.username;
		self.password = basicAuth.password;
	}
	else if ([authentication isKindOfClass:[LRCookieAuthentication class]]){
		LRCookieAuthentication *cookieAuth = authentication;
		self.username = cookieAuth.username;
		self.password = cookieAuth.password;
	}
	else {
		[NSException raise:@""
			format:@"Can't sign in if authentication implementation is not " \
			"BasicAuthentication or CookieAuthentication"];
	}

	NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:
									[self _getLoginURL: session.server]];

	NSData *body = [self _getBodyWithUsername:self.username
		password:self.password];

	NSString *postLength = [NSString stringWithFormat:@"%d",(int)[body length]];

	[request setValue:postLength forHTTPHeaderField:@"Content-Length"];
	[request addValue:@"application/x-www-form-urlencoded"
	forHTTPHeaderField:@"Content-Type"];
	[request addValue:@"COOKIE_SUPPORT=true" forHTTPHeaderField:@"Cookie"];

	[request setHTTPMethod:LR_POST];
	[request setHTTPBody:body];
	NSURLSessionConfiguration *config = [NSURLSessionConfiguration
		defaultSessionConfiguration];

	NSURLSession *urlSession = [NSURLSession
								sessionWithConfiguration:config
								delegate:self delegateQueue:nil];

	NSURLSessionTask *task = [urlSession dataTaskWithRequest:request];
	
	[task resume];
}

@end

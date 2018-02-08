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

#import "LROAuthConfig.h"

#import "LROAuth.h"

/**
 * @author Bruno Farache
 */
@implementation LROAuthConfig

- (id)initWithServer:(NSString *)server consumerKey:(NSString *)consumerKey
		consumerSecret:(NSString *)consumerSecret
		callbackURL:(NSString *)callbackURL {

	return [self initWithServer:server consumerKey:consumerKey
		consumerSecret:consumerSecret token:nil tokenSecret:nil
		callbackURL:callbackURL];
}

- (id)initWithConsumerKey:(NSString *)consumerKey
		consumerSecret:(NSString *)consumerSecret token:(NSString *)token
		tokenSecret:(NSString *)tokenSecret {

	return [self initWithServer:nil consumerKey:consumerKey
		consumerSecret:consumerSecret token:token tokenSecret:tokenSecret
		callbackURL:nil];
}

- (id)initWithServer:(NSString *)server consumerKey:(NSString *)consumerKey
		consumerSecret:(NSString *)consumerSecret token:(NSString *)token
		tokenSecret:(NSString *)tokenSecret
		callbackURL:(NSString *)callbackURL {

	self = [super init];

	if (self) {
		self.accessTokenURL = @"/c/portal/oauth/access_token";
		self.authorizeTokenURL = @"/c/portal/oauth/authorize";
		self.callbackURL = callbackURL;
		self.consumerKey = consumerKey;
		self.consumerSecret = consumerSecret;
		self.requestTokenURL = @"/c/portal/oauth/request_token";
		self.server = server;
		self.token = token;
		self.tokenSecret = tokenSecret ? : @"";
	}

	return self;
}

- (NSDictionary *)params {
	NSString *nonce = self.nonce ? : [self _generateNonce];
	NSString *timestamp = self.timestamp ? : [self _generateTimestamp];

	NSMutableDictionary *params = [NSMutableDictionary
	   dictionaryWithDictionary:@{
			@"oauth_consumer_key": self.consumerKey,
			@"oauth_nonce": nonce,
			@"oauth_timestamp": timestamp,
			@"oauth_version": @"1.0",
			@"oauth_signature_method": @"HMAC-SHA1"
		}
	];

	if (self.callbackURL) {
		params[@"oauth_callback"] = [LROAuth escape:self.callbackURL];
	}

	if (self.token) {
		params[@"oauth_token"] = self.token;
	}

	if (self.verifier) {
		params[@"oauth_verifier"] = [LROAuth escape:self.verifier];
	}

	return params;
}

- (NSString *)accessTokenURL {
	if (!self.server) {
		return _accessTokenURL;
	}

	return  [NSString stringWithFormat:@"%@%@", self.server, _accessTokenURL];
}

- (NSString *)requestTokenURL {
	if (!self.server) {
		return _requestTokenURL;
	}

	return  [NSString stringWithFormat:@"%@%@", self.server, _requestTokenURL];
}

- (void)setAuthorizeTokenURLWithParams:(NSDictionary *)params {
	[self setToken:params[@"oauth_token"]];
	[self setTokenSecret:params[@"oauth_token_secret"]];
	[self setAuthorizeTokenURL:[NSString
		stringWithFormat:@"%@%@?oauth_token=%@&oauth_callback=%@", self.server,
		self.authorizeTokenURL, self.token, [LROAuth escape:self.callbackURL]]];
}

- (NSString *)_generateNonce {
	CFUUIDRef uuid = CFUUIDCreate(NULL);
	CFStringRef string = CFUUIDCreateString(NULL, uuid);
	CFRelease(uuid);

	return (NSString *)CFBridgingRelease(string);
}

- (NSString *)_generateTimestamp {
	return [@(floor([[NSDate date] timeIntervalSince1970])) stringValue];
}

@end
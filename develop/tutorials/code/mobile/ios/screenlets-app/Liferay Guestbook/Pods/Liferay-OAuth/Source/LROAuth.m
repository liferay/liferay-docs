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

#import "LROAuth.h"

#import <CommonCrypto/CommonHMAC.h>

/**
 * @author Bruno Farache
 */
@implementation LROAuth

- (id)initWithConsumerKey:(NSString *)consumerKey
		consumerSecret:(NSString *)consumerSecret token:(NSString *)token
		tokenSecret:(NSString *)tokenSecret {

	return [self initWithConfig:[[LROAuthConfig alloc]
		initWithConsumerKey:consumerKey consumerSecret:consumerSecret
		token:token tokenSecret:tokenSecret]];
}

- (id)initWithConfig:(LROAuthConfig *)config {
	self = [super init];

	if (self) {
		self.config = config;
	}

	return self;
}

- (void)authenticate:(NSMutableURLRequest *)request {
	NSString *method = request.HTTPMethod;
	NSString *URL = [[request.URL absoluteString]
		componentsSeparatedByString:@"?"][0];

	NSMutableDictionary *oauthParams = [NSMutableDictionary
		dictionaryWithDictionary:self.config.params];

	NSMutableDictionary *params = [LROAuth
		extractRequestParams:[request.URL query]];

	[params addEntriesFromDictionary:oauthParams];

	NSString *signature = [self _getSignatureWithMethod:method URL:URL
		params:params];

	oauthParams[@"oauth_signature"] = [LROAuth escape:signature];

	NSMutableString *header = [NSMutableString string];

	[header appendString:@"OAuth "];

	NSArray *sortedKeys = [[oauthParams allKeys]
		sortedArrayUsingSelector:@selector(caseInsensitiveCompare:)];

	for (int i = 0; i < [sortedKeys count]; i++) {
		if (i > 0) {
			[header appendString:@", "];
		}

		NSString *key = sortedKeys[i];
		[header appendFormat:@"%@=\"%@\"", key, oauthParams[key]];
	}

	[request setValue:header forHTTPHeaderField:@"Authorization"];
}

+ (NSString *)escape:(NSString *)string {
	CFStringRef escape = CFSTR(":/?&=;+!@#$()',*");
	CFStringRef ignore = CFSTR("[].");

	return CFBridgingRelease(
		CFURLCreateStringByAddingPercentEscapes(
			kCFAllocatorDefault, (CFStringRef)string, ignore, escape,
			CFStringConvertNSStringEncodingToEncoding(NSUTF8StringEncoding)));
}

+ (NSMutableDictionary *)extractRequestParams:(NSString *)query {
	NSMutableDictionary *params = [NSMutableDictionary dictionary];
	NSArray *paramsArray = [query componentsSeparatedByString:@"&"];

	for (NSString *param in paramsArray) {
		NSArray *pair = [param componentsSeparatedByString:@"="];

		if ([pair count] == 2) {
			params[pair[0]] = pair[1];
		}
	}

	return params;
}

- (NSString *)_getSignatureBaseWithMethod:(NSString *)method URL:(NSString *)URL
		params:(NSDictionary *)params {

	NSArray *sortedKeys = [[params allKeys]
		sortedArrayUsingSelector:@selector(caseInsensitiveCompare:)];

	NSMutableString *paramsString = [NSMutableString string];

	for (int i = 0; i < [sortedKeys count]; i++) {
		if (i > 0) {
			[paramsString appendString:@"&"];
		}

		NSString *key = sortedKeys[i];
		[paramsString appendFormat:@"%@=%@", key, params[key]];
	}

	NSString *signatureBase = [NSString stringWithFormat:@"%@&%@&%@",
		method, [LROAuth escape:URL], [LROAuth escape:paramsString]];

	return signatureBase;
}

- (NSString *)_getSignatureWithMethod:(NSString *)method URL:(NSString *)URL
		params:(NSDictionary *)params {

	NSString *signatureBase = [self _getSignatureBaseWithMethod:method URL:URL
		params:params];

	NSData *signatureBaseData = [signatureBase
		dataUsingEncoding:NSUTF8StringEncoding];

	NSString *secret = [NSString stringWithFormat:@"%@&%@",
		self.config.consumerSecret, self.config.tokenSecret];

	NSData *secretData = [secret dataUsingEncoding:NSUTF8StringEncoding];

	NSMutableData *digest = [NSMutableData
		dataWithLength:CC_SHA1_DIGEST_LENGTH];

	CCHmac(
		kCCHmacAlgSHA1, secretData.bytes, secretData.length,
		signatureBaseData.bytes, signatureBaseData.length, digest.mutableBytes);

	NSDataBase64EncodingOptions options =
		NSDataBase64Encoding76CharacterLineLength;

	return [digest base64EncodedStringWithOptions:options];
}

@end
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

#import "LRAccessToken.h"

#import "LROAuth.h"

/**
 * @author Bruno Farache
 */
@implementation LRAccessToken

+ (void)accessTokenWithConfig:(LROAuthConfig *)config
		onSuccess:(void (^)(LROAuthConfig *))success
		onFailure:(void (^)(NSError *))failure {

	LROAuth *oauth = [[LROAuth alloc] initWithConfig:config];

	NSMutableURLRequest *request = [[NSMutableURLRequest alloc]
		initWithURL:[NSURL URLWithString:config.accessTokenURL]];

	[request setHTTPMethod:@"POST"];

	[oauth authenticate:request];

	[[[NSURLSession sharedSession]
		dataTaskWithRequest:request
		completionHandler:^(NSData *data, NSURLResponse *response, NSError *e) {
			NSString *result = [[NSString alloc] initWithData:data
				encoding:NSUTF8StringEncoding];

			NSDictionary *params = [LROAuth extractRequestParams:result];

			LROAuthConfig *authorized = [[LROAuthConfig alloc]
				initWithConsumerKey:config.consumerKey
				consumerSecret:config.consumerSecret
				token:params[@"oauth_token"]
				tokenSecret:params[@"oauth_token_secret"]];

			dispatch_async(dispatch_get_main_queue(), ^{
				success(authorized);
			});
		}
	] resume];
}

@end
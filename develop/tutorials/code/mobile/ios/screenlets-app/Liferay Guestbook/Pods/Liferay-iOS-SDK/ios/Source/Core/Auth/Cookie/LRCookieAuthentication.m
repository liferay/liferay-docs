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

#import "LRCookieAuthentication.h"

/**
 * @author Victor Galán
 */
@implementation LRCookieAuthentication

- (id)initWithAuthToken:(NSString *)authToken
		cookieHeader:(NSString *)cookieHeader
		username:(NSString *)username
		password:(NSString *) password {

	self = [super init];

	if (self) {
		self.authToken = authToken;
		self.cookieHeader = cookieHeader;
		self.username = username;
		self.password = password;
	}

	return self;
}

-(void)authenticate:(NSMutableURLRequest *)request {
	NSString *value = [NSString
		stringWithFormat:@"COOKIE_SUPPORT=true; %@", self.cookieHeader];

	[request addValue:value forHTTPHeaderField:@"Cookie"];
	[request addValue:self.authToken forHTTPHeaderField:@"X-CSRF-Token"];
}

@end

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

#import "LRBasicAuthentication.h"

/**
 * @author Bruno Farache
 */
@implementation LRBasicAuthentication

- (id)initWithUsername:(NSString *)username password:(NSString *)password {
	self = [super init];

	if (self) {
		self.username = username;
		self.password = password;
	}

	return self;
}

- (void)authenticate:(NSMutableURLRequest *)request {
	NSString *credentials = [NSString stringWithFormat:@"%@:%@",
		self.username, self.password];

	NSData *auth = [credentials dataUsingEncoding:NSUTF8StringEncoding];
	NSString *encoded = [auth base64EncodedStringWithOptions:0];

	NSString *header = [NSString stringWithFormat:@"Basic %@", encoded];

	[request setValue:header forHTTPHeaderField:@"Authorization"];
}

@end
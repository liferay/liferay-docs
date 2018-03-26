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

#import "LRError.h"
#import "LRLocalizationUtil.h"

NSString *const LR_ERROR_DOMAIN = @"com.liferay.mobile.sdk";

/**
 * @author Bruno Farache
 */
@implementation LRError

+ (NSError *)errorWithCode:(LRErrorCode)code
		description:(NSString *)description {

	return [self errorWithCode:code description:description userInfo:nil];
}

+ (NSError *)errorWithCode:(LRErrorCode)code description:(NSString *)description
		userInfo:(NSDictionary *)userInfo {

	NSMutableDictionary *values = [[NSMutableDictionary alloc]
	   initWithDictionary:userInfo];

	if (description) {
		[values setObject:description forKey:NSLocalizedDescriptionKey];
	}

	return [super errorWithDomain:LR_ERROR_DOMAIN code:code userInfo:values];
}

- (NSString *)localizedDescription {
	NSString *description = [super localizedDescription];

	return [LRLocalizationUtil localize:description];
}

@end
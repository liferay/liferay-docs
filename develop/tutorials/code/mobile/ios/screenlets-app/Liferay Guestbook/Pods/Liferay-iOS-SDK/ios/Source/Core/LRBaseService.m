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

#import "LRBaseService.h"
#import "LRValidator.h"

NSString *const _SERVICE_CONTEXT_62 = @"com.liferay.portal.service.ServiceContext";
NSString *const _SERVICE_CONTEXT_70 = @"com.liferay.portal.kernel.service.ServiceContext";

/**
 * @author Bruno Farache
 */
@implementation LRBaseService

- (id)initWithSession:(LRSession *)session {
	self = [super init];

	if (self) {
		self.session = session;
	}

	return self;
}

- (BOOL)boolValue:(NSNumber *)number {
	return [number boolValue];
}

- (id)checkNull:(id)object {
	if (object == nil) {
		return [NSNull null];
	}

	return object;
}

- (void)mangleWrapperWithParams:(NSMutableDictionary *)params
		name:(NSString *)name className:(NSString *)className
		wrapper:(LRJSONObjectWrapper *)wrapper {

	if (!wrapper) {
		if (![className isEqualToString:_SERVICE_CONTEXT_62] &&
			![className isEqualToString:_SERVICE_CONTEXT_70]) {

			[params setObject:[NSNull null] forKey:name];
		}

		return;
	}

	[wrapper mangleWithParams:params name:name className:className];
}

- (NSString *)toString:(NSData *)data {
	if ([LRValidator isNull:data]) {
		return @"";
	}

	const uint8_t *values = [data bytes];
	NSMutableString *value = [NSMutableString stringWithString:@"["];

	for (int i = 0; i < [data length]; i++) {
		if (i > 0) {
			[value appendString:@","];
		}

		[value appendFormat:@"%d", values[i]];
	}

	[value appendString:@"]"];

	return value;
}

@end
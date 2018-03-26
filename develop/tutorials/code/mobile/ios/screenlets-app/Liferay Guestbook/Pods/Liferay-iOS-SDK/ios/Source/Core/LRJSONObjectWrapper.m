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

#import "LRJSONObjectWrapper.h"

/**
 * @author Bruno Farache
 */
@implementation LRJSONObjectWrapper

- (id)initWithJSONObject:(NSDictionary *)jsonObject {
	return [self initWithClassName:nil jsonObject:jsonObject];
}

- (id)initWithClassName:(NSString *)className
		jsonObject:(NSDictionary *)jsonObject {

	self = [super init];

	if (self) {
		self.className = className;
		self.jsonObject = jsonObject;
	}

	return self;
}

- (void)mangleWithParams:(NSMutableDictionary *)params name:(NSString *)name
	   className:(NSString *)className {

	[self _addClassNameWithParams:params name:name className:className];
	[self _addJSONObjectWithParams:params name:name];
}

- (void)_addClassNameWithParams:(NSMutableDictionary *)params
		name:(NSString *)name className:(NSString *)className {

	if (!self.className) {
		self.className = className;
	}

	NSString *key = [NSString stringWithFormat:@"+%@", name];

	[params setObject:self.className forKey:key];
}

- (void)_addJSONObjectWithParams:(NSMutableDictionary *)params
		name:(NSString *)name {

	for (NSString *key in self.jsonObject) {
		NSString *paramKey = [NSString stringWithFormat:@"%@.%@", name, key];
		id value = [self.jsonObject objectForKey:key];

		[params setObject:value forKey:paramKey];
	}
}

@end
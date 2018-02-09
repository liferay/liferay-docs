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

/**
 * @author Bruno Farache
 */
@interface LRJSONObjectWrapper : NSObject

@property (nonatomic, strong) NSString *className;
@property (nonatomic, strong) NSDictionary *jsonObject;

- (id)initWithJSONObject:(NSDictionary *)jsonObject;
- (id)initWithClassName:(NSString *)className
	jsonObject:(NSDictionary *)jsonObject;

- (void)mangleWithParams:(NSMutableDictionary *)params name:(NSString *)name
	className:(NSString *)className;

@end
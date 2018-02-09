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

#import "LRClassNameService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRClassNameService_v7

- (NSDictionary *)fetchClassNameWithValue:(NSString *)value error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"value": [self checkNull: value]
	}];

	NSDictionary *_command = @{@"/classname/fetch-class-name": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
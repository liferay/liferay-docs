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

#import "LRListTypeService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRListTypeService_v7

- (void)validateWithListTypeId:(long long)listTypeId classNameId:(long long)classNameId type:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"listTypeId": @(listTypeId),
		@"classNameId": @(classNameId),
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/listtype/validate": _params};

	[self.session invoke:_command error:error];
}

- (void)validateWithListTypeId:(long long)listTypeId type:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"listTypeId": @(listTypeId),
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/listtype/validate": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getListTypeWithListTypeId:(long long)listTypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"listTypeId": @(listTypeId)
	}];

	NSDictionary *_command = @{@"/listtype/get-list-type": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getListTypesWithType:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/listtype/get-list-types": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

@end
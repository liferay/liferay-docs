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

#import "LRAssetTagPropertyService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRAssetTagPropertyService_v62

- (NSDictionary *)addTagPropertyWithTagId:(long long)tagId key:(NSString *)key value:(NSString *)value error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagId": @(tagId),
		@"key": [self checkNull: key],
		@"value": [self checkNull: value]
	}];

	NSDictionary *_command = @{@"/assettagproperty/add-tag-property": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteTagPropertyWithTagPropertyId:(long long)tagPropertyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagPropertyId": @(tagPropertyId)
	}];

	NSDictionary *_command = @{@"/assettagproperty/delete-tag-property": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getTagPropertiesWithTagId:(long long)tagId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagId": @(tagId)
	}];

	NSDictionary *_command = @{@"/assettagproperty/get-tag-properties": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagPropertyValuesWithCompanyId:(long long)companyId key:(NSString *)key error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"key": [self checkNull: key]
	}];

	NSDictionary *_command = @{@"/assettagproperty/get-tag-property-values": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateTagPropertyWithTagPropertyId:(long long)tagPropertyId key:(NSString *)key value:(NSString *)value error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagPropertyId": @(tagPropertyId),
		@"key": [self checkNull: key],
		@"value": [self checkNull: value]
	}];

	NSDictionary *_command = @{@"/assettagproperty/update-tag-property": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
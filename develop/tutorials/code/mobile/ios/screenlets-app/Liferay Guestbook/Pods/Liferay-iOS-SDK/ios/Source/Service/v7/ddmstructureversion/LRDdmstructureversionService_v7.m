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

#import "LRDdmstructureversionService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDdmstructureversionService_v7

- (NSDictionary *)getLatestStructureVersionWithStructureId:(long long)structureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructureversion/get-latest-structure-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureVersionWithStructureVersionId:(long long)structureVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureVersionId": @(structureVersionId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructureversion/get-structure-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getStructureVersionsCountWithStructureId:(long long)structureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructureversion/get-structure-versions-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getStructureVersionsWithStructureId:(long long)structureId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMStructureVersion>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmstructureversion/get-structure-versions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

@end
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

#import "LRDLFileVersionService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDLFileVersionService_v62

- (NSDictionary *)getFileVersionWithFileVersionId:(long long)fileVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileVersionId": @(fileVersionId)
	}];

	NSDictionary *_command = @{@"/dlfileversion/get-file-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileVersionsWithFileEntryId:(long long)fileEntryId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/dlfileversion/get-file-versions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileVersionsCountWithFileEntryId:(long long)fileEntryId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/dlfileversion/get-file-versions-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getLatestFileVersionWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileversion/get-latest-file-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
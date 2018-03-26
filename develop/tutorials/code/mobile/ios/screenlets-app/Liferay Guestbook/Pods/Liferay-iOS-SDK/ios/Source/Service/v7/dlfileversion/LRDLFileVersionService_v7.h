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

/**
 * @author Bruno Farache
 */
@interface LRDLFileVersionService_v7 : LRBaseService

- (NSDictionary *)getFileVersionWithFileVersionId:(long long)fileVersionId error:(NSError **)error;
- (NSArray *)getFileVersionsWithFileEntryId:(long long)fileEntryId status:(int)status error:(NSError **)error;
- (NSNumber *)getFileVersionsCountWithFileEntryId:(long long)fileEntryId status:(int)status error:(NSError **)error;
- (NSDictionary *)getLatestFileVersionWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)getLatestFileVersionWithFileEntryId:(long long)fileEntryId excludeWorkingCopy:(BOOL)excludeWorkingCopy error:(NSError **)error;

@end
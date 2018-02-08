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
@interface LRAssetEntryService_v7 : LRBaseService

- (NSArray *)getEntriesWithEntryQuery:(LRJSONObjectWrapper *)entryQuery error:(NSError **)error;
- (NSDictionary *)getEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (NSDictionary *)updateEntryWithGroupId:(long long)groupId createDate:(long long)createDate modifiedDate:(long long)modifiedDate className:(NSString *)className classPK:(long long)classPK classUuid:(NSString *)classUuid classTypeId:(long long)classTypeId categoryIds:(NSArray *)categoryIds tagNames:(NSArray *)tagNames visible:(BOOL)visible startDate:(long long)startDate endDate:(long long)endDate expirationDate:(long long)expirationDate mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description summary:(NSString *)summary url:(NSString *)url layoutUuid:(NSString *)layoutUuid height:(int)height width:(int)width priority:(int)priority sync:(BOOL)sync error:(NSError **)error;
- (NSDictionary *)updateEntryWithGroupId:(long long)groupId createDate:(long long)createDate modifiedDate:(long long)modifiedDate className:(NSString *)className classPK:(long long)classPK classUuid:(NSString *)classUuid classTypeId:(long long)classTypeId categoryIds:(NSArray *)categoryIds tagNames:(NSArray *)tagNames visible:(BOOL)visible startDate:(long long)startDate endDate:(long long)endDate expirationDate:(long long)expirationDate mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description summary:(NSString *)summary url:(NSString *)url layoutUuid:(NSString *)layoutUuid height:(int)height width:(int)width priority:(double)priority error:(NSError **)error;
- (NSDictionary *)fetchEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (NSDictionary *)incrementViewCounterWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSNumber *)getEntriesCountWithEntryQuery:(LRJSONObjectWrapper *)entryQuery error:(NSError **)error;
- (NSArray *)getCompanyEntriesWithCompanyId:(long long)companyId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getCompanyEntriesCountWithCompanyId:(long long)companyId error:(NSError **)error;

@end
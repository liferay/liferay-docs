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

#import "LRAssetEntryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRAssetEntryService_v7

- (NSArray *)getEntriesWithEntryQuery:(LRJSONObjectWrapper *)entryQuery error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"entryQuery" className:@"com.liferay.asset.kernel.service.persistence.AssetEntryQuery" wrapper:entryQuery];

	NSDictionary *_command = @{@"/assetentry/get-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/assetentry/get-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEntryWithGroupId:(long long)groupId createDate:(long long)createDate modifiedDate:(long long)modifiedDate className:(NSString *)className classPK:(long long)classPK classUuid:(NSString *)classUuid classTypeId:(long long)classTypeId categoryIds:(NSArray *)categoryIds tagNames:(NSArray *)tagNames visible:(BOOL)visible startDate:(long long)startDate endDate:(long long)endDate expirationDate:(long long)expirationDate mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description summary:(NSString *)summary url:(NSString *)url layoutUuid:(NSString *)layoutUuid height:(int)height width:(int)width priority:(int)priority sync:(BOOL)sync error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"createDate": @(createDate),
		@"modifiedDate": @(modifiedDate),
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"classUuid": [self checkNull: classUuid],
		@"classTypeId": @(classTypeId),
		@"categoryIds": [self checkNull: categoryIds],
		@"tagNames": [self checkNull: tagNames],
		@"visible": @(visible),
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"expirationDate": @(expirationDate),
		@"mimeType": [self checkNull: mimeType],
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"summary": [self checkNull: summary],
		@"url": [self checkNull: url],
		@"layoutUuid": [self checkNull: layoutUuid],
		@"height": @(height),
		@"width": @(width),
		@"priority": @(priority),
		@"sync": @(sync)
	}];

	NSDictionary *_command = @{@"/assetentry/update-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEntryWithGroupId:(long long)groupId createDate:(long long)createDate modifiedDate:(long long)modifiedDate className:(NSString *)className classPK:(long long)classPK classUuid:(NSString *)classUuid classTypeId:(long long)classTypeId categoryIds:(NSArray *)categoryIds tagNames:(NSArray *)tagNames visible:(BOOL)visible startDate:(long long)startDate endDate:(long long)endDate expirationDate:(long long)expirationDate mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description summary:(NSString *)summary url:(NSString *)url layoutUuid:(NSString *)layoutUuid height:(int)height width:(int)width priority:(double)priority error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"createDate": @(createDate),
		@"modifiedDate": @(modifiedDate),
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"classUuid": [self checkNull: classUuid],
		@"classTypeId": @(classTypeId),
		@"categoryIds": [self checkNull: categoryIds],
		@"tagNames": [self checkNull: tagNames],
		@"visible": @(visible),
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"expirationDate": @(expirationDate),
		@"mimeType": [self checkNull: mimeType],
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"summary": [self checkNull: summary],
		@"url": [self checkNull: url],
		@"layoutUuid": [self checkNull: layoutUuid],
		@"height": @(height),
		@"width": @(width),
		@"priority": @(priority)
	}];

	NSDictionary *_command = @{@"/assetentry/update-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/assetentry/fetch-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)incrementViewCounterWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/assetentry/increment-view-counter": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getEntriesCountWithEntryQuery:(LRJSONObjectWrapper *)entryQuery error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"entryQuery" className:@"com.liferay.asset.kernel.service.persistence.AssetEntryQuery" wrapper:entryQuery];

	NSDictionary *_command = @{@"/assetentry/get-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getCompanyEntriesWithCompanyId:(long long)companyId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetentry/get-company-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCompanyEntriesCountWithCompanyId:(long long)companyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId)
	}];

	NSDictionary *_command = @{@"/assetentry/get-company-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

@end
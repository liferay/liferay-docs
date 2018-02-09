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

#import "LREntryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LREntryService_v7

- (NSDictionary *)addEntryWithUserId:(long long)userId guestbookId:(long long)guestbookId name:(NSString *)name email:(NSString *)email message:(NSString *)message serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"guestbookId": @(guestbookId),
		@"name": [self checkNull: name],
		@"email": [self checkNull: email],
		@"message": [self checkNull: message],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/gb.entry/add-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getEntriesWithGroupId:(long long)groupId guestbookId:(long long)guestbookId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"guestbookId": @(guestbookId)
	}];

	NSDictionary *_command = @{@"/gb.entry/get-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getEntriesWithGroupId:(long long)groupId guestbookId:(long long)guestbookId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"guestbookId": @(guestbookId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/gb.entry/get-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEntryWithUserId:(long long)userId guestbookId:(long long)guestbookId entryId:(long long)entryId name:(NSString *)name email:(NSString *)email message:(NSString *)message serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"guestbookId": @(guestbookId),
		@"entryId": @(entryId),
		@"name": [self checkNull: name],
		@"email": [self checkNull: email],
		@"message": [self checkNull: message],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/gb.entry/update-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getEntriesCountWithGroupId:(long long)groupId guestbookId:(long long)guestbookId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"guestbookId": @(guestbookId)
	}];

	NSDictionary *_command = @{@"/gb.entry/get-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteEntryWithEntryId:(long long)entryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/gb.entry/delete-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
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

#import "LRMicroblogsentryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRMicroblogsentryService_v7

- (NSArray *)getMicroblogsEntriesWithStart:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-microblogs-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getMicroblogsEntriesWithAssetTagName:(NSString *)assetTagName start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"assetTagName": [self checkNull: assetTagName],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-microblogs-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getMicroblogsEntriesCountWithAssetTagName:(NSString *)assetTagName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"assetTagName": [self checkNull: assetTagName]
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-microblogs-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getMicroblogsEntriesCount:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-microblogs-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getMicroblogsEntryWithMicroblogsEntryId:(long long)microblogsEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryId": @(microblogsEntryId)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-microblogs-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addMicroblogsEntryWithUserId:(long long)userId content:(NSString *)content type:(int)type parentMicroblogsEntryId:(long long)parentMicroblogsEntryId socialRelationType:(int)socialRelationType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"content": [self checkNull: content],
		@"type": @(type),
		@"parentMicroblogsEntryId": @(parentMicroblogsEntryId),
		@"socialRelationType": @(socialRelationType),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/add-microblogs-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteMicroblogsEntryWithMicroblogsEntryId:(long long)microblogsEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryId": @(microblogsEntryId)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/delete-microblogs-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserMicroblogsEntriesWithMicroblogsEntryUserId:(long long)microblogsEntryUserId type:(int)type start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryUserId": @(microblogsEntryUserId),
		@"type": @(type),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-user-microblogs-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserMicroblogsEntriesWithMicroblogsEntryUserId:(long long)microblogsEntryUserId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryUserId": @(microblogsEntryUserId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-user-microblogs-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getUserMicroblogsEntriesCountWithMicroblogsEntryUserId:(long long)microblogsEntryUserId type:(int)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryUserId": @(microblogsEntryUserId),
		@"type": @(type)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-user-microblogs-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getUserMicroblogsEntriesCountWithMicroblogsEntryUserId:(long long)microblogsEntryUserId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryUserId": @(microblogsEntryUserId)
	}];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/get-user-microblogs-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateMicroblogsEntryWithMicroblogsEntryId:(long long)microblogsEntryId content:(NSString *)content socialRelationType:(int)socialRelationType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"microblogsEntryId": @(microblogsEntryId),
		@"content": [self checkNull: content],
		@"socialRelationType": @(socialRelationType),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/microblogs.microblogsentry/update-microblogs-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
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

#import "LRMBThreadService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRMBThreadService_v62

- (void)deleteThreadWithThreadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/delete-thread": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId modifiedDate:(long long)modifiedDate status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"modifiedDate": @(modifiedDate),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status),
		@"subscribed": @(subscribed),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed includeAnonymous:(BOOL)includeAnonymous start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status),
		@"subscribed": @(subscribed),
		@"includeAnonymous": @(includeAnonymous),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId modifiedDate:(long long)modifiedDate status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"modifiedDate": @(modifiedDate),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status),
		@"subscribed": @(subscribed)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed includeAnonymous:(BOOL)includeAnonymous error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status),
		@"subscribed": @(subscribed),
		@"includeAnonymous": @(includeAnonymous)
	}];

	NSDictionary *_command = @{@"/mbthread/get-group-threads-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getThreadsWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/get-threads": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getThreadsCountWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/mbthread/get-threads-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)lockThreadWithThreadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/lock-thread": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveThreadWithCategoryId:(long long)categoryId threadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/move-thread": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveThreadFromTrashWithCategoryId:(long long)categoryId threadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/move-thread-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveThreadToTrashWithThreadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/move-thread-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restoreThreadFromTrashWithThreadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/restore-thread-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"creatorUserId": @(creatorUserId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/search": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId startDate:(long long)startDate endDate:(long long)endDate status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"creatorUserId": @(creatorUserId),
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbthread/search": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)splitThreadWithMessageId:(long long)messageId subject:(NSString *)subject serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"messageId": @(messageId),
		@"subject": [self checkNull: subject],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mbthread/split-thread": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)unlockThreadWithThreadId:(long long)threadId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"threadId": @(threadId)
	}];

	NSDictionary *_command = @{@"/mbthread/unlock-thread": _params};

	[self.session invoke:_command error:error];
}

@end
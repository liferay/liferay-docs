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

#import "LRBookmarksEntryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRBookmarksEntryService_v7

- (NSDictionary *)addEntryWithGroupId:(long long)groupId folderId:(long long)folderId name:(NSString *)name url:(NSString *)url description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"name": [self checkNull: name],
		@"url": [self checkNull: url],
		@"description": [self checkNull: description],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/add-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getEntriesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getEntriesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.bookmarks.model.BookmarksEntry>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"creatorUserId": @(creatorUserId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/search": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/delete-entry": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEntryWithEntryId:(long long)entryId groupId:(long long)groupId folderId:(long long)folderId name:(NSString *)name url:(NSString *)url description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId),
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"name": [self checkNull: name],
		@"url": [self checkNull: url],
		@"description": [self checkNull: description],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/update-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveEntryWithEntryId:(long long)entryId parentFolderId:(long long)parentFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId),
		@"parentFolderId": @(parentFolderId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/move-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-group-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-group-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-group-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveEntryToTrashWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/move-entry-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restoreEntryFromTrashWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/restore-entry-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId userId:(long long)userId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersEntriesCountWithGroupId:(long long)groupId folderIds:(NSArray *)folderIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds]
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/get-folders-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)openEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/open-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)openEntryWithEntry:(LRJSONObjectWrapper *)entry error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"entry" className:@"com.liferay.bookmarks.model.BookmarksEntry" wrapper:entry];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/open-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveEntryFromTrashWithEntryId:(long long)entryId parentFolderId:(long long)parentFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId),
		@"parentFolderId": @(parentFolderId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/move-entry-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)subscribeEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/subscribe-entry": _params};

	[self.session invoke:_command error:error];
}

- (void)unsubscribeEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/bookmarks.bookmarksentry/unsubscribe-entry": _params};

	[self.session invoke:_command error:error];
}

@end
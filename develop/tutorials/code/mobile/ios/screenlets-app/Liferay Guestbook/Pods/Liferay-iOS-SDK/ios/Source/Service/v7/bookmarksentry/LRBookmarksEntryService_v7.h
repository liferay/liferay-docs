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
@interface LRBookmarksEntryService_v7 : LRBaseService

- (NSDictionary *)addEntryWithGroupId:(long long)groupId folderId:(long long)folderId name:(NSString *)name url:(NSString *)url description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getEntriesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getEntriesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)getEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (void)deleteEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (NSDictionary *)updateEntryWithEntryId:(long long)entryId groupId:(long long)groupId folderId:(long long)folderId name:(NSString *)name url:(NSString *)url description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)moveEntryWithEntryId:(long long)entryId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSNumber *)getEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error;
- (NSNumber *)getEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error;
- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId error:(NSError **)error;
- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)moveEntryToTrashWithEntryId:(long long)entryId error:(NSError **)error;
- (void)restoreEntryFromTrashWithEntryId:(long long)entryId error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId userId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getFoldersEntriesCountWithGroupId:(long long)groupId folderIds:(NSArray *)folderIds error:(NSError **)error;
- (NSDictionary *)openEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (NSDictionary *)openEntryWithEntry:(LRJSONObjectWrapper *)entry error:(NSError **)error;
- (NSDictionary *)moveEntryFromTrashWithEntryId:(long long)entryId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (void)subscribeEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (void)unsubscribeEntryWithEntryId:(long long)entryId error:(NSError **)error;

@end
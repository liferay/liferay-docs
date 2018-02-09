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

@import LRMobileSDK;

/**
 * @author Bruno Farache
 */
@interface LREntryService_v7 : LRBaseService

- (NSDictionary *)addEntryWithUserId:(long long)userId guestbookId:(long long)guestbookId name:(NSString *)name email:(NSString *)email message:(NSString *)message serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getEntriesWithGroupId:(long long)groupId guestbookId:(long long)guestbookId error:(NSError **)error;
- (NSArray *)getEntriesWithGroupId:(long long)groupId guestbookId:(long long)guestbookId start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)updateEntryWithUserId:(long long)userId guestbookId:(long long)guestbookId entryId:(long long)entryId name:(NSString *)name email:(NSString *)email message:(NSString *)message serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSNumber *)getEntriesCountWithGroupId:(long long)groupId guestbookId:(long long)guestbookId error:(NSError **)error;
- (NSDictionary *)deleteEntryWithEntryId:(long long)entryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end

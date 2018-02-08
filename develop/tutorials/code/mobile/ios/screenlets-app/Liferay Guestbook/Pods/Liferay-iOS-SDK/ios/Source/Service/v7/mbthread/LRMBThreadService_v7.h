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
@interface LRMBThreadService_v7 : LRBaseService

- (NSArray *)getThreadsWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId startDate:(long long)startDate endDate:(long long)endDate status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (void)deleteThreadWithThreadId:(long long)threadId error:(NSError **)error;
- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed includeAnonymous:(BOOL)includeAnonymous error:(NSError **)error;
- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed error:(NSError **)error;
- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status error:(NSError **)error;
- (NSNumber *)getGroupThreadsCountWithGroupId:(long long)groupId userId:(long long)userId modifiedDate:(long long)modifiedDate status:(int)status error:(NSError **)error;
- (NSNumber *)getThreadsCountWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status error:(NSError **)error;
- (NSDictionary *)moveThreadWithCategoryId:(long long)categoryId threadId:(long long)threadId error:(NSError **)error;
- (NSDictionary *)moveThreadFromTrashWithCategoryId:(long long)categoryId threadId:(long long)threadId error:(NSError **)error;
- (NSDictionary *)moveThreadToTrashWithThreadId:(long long)threadId error:(NSError **)error;
- (void)restoreThreadFromTrashWithThreadId:(long long)threadId error:(NSError **)error;
- (NSDictionary *)splitThreadWithMessageId:(long long)messageId subject:(NSString *)subject serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)lockThreadWithThreadId:(long long)threadId error:(NSError **)error;
- (void)unlockThreadWithThreadId:(long long)threadId error:(NSError **)error;
- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId modifiedDate:(long long)modifiedDate status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupThreadsWithGroupId:(long long)groupId userId:(long long)userId status:(int)status subscribed:(BOOL)subscribed includeAnonymous:(BOOL)includeAnonymous start:(int)start end:(int)end error:(NSError **)error;

@end
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
@interface LRCommentmanagerjsonwsService_v7 : LRBaseService

- (NSArray *)getCommentsWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)addCommentWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK body:(NSString *)body error:(NSError **)error;
- (void)deleteCommentWithCommentId:(long long)commentId error:(NSError **)error;
- (NSNumber *)getCommentsCountWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (BOOL)hasDiscussionWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (void)subscribeDiscussionWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (void)unsubscribeDiscussionWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSNumber *)updateCommentWithClassName:(NSString *)className classPK:(long long)classPK commentId:(long long)commentId subject:(NSString *)subject body:(NSString *)body error:(NSError **)error;

@end
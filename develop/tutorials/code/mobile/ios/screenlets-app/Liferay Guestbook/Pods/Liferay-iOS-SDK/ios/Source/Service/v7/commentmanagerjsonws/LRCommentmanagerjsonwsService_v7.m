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

#import "LRCommentmanagerjsonwsService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRCommentmanagerjsonwsService_v7

- (NSArray *)getCommentsWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/get-comments": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)addCommentWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK body:(NSString *)body error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"body": [self checkNull: body]
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/add-comment": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)deleteCommentWithCommentId:(long long)commentId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"commentId": @(commentId)
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/delete-comment": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)getCommentsCountWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/get-comments-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (BOOL)hasDiscussionWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/has-discussion": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (void)subscribeDiscussionWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/subscribe-discussion": _params};

	[self.session invoke:_command error:error];
}

- (void)unsubscribeDiscussionWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/unsubscribe-discussion": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)updateCommentWithClassName:(NSString *)className classPK:(long long)classPK commentId:(long long)commentId subject:(NSString *)subject body:(NSString *)body error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"commentId": @(commentId),
		@"subject": [self checkNull: subject],
		@"body": [self checkNull: body]
	}];

	NSDictionary *_command = @{@"/comment.commentmanagerjsonws/update-comment": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

@end
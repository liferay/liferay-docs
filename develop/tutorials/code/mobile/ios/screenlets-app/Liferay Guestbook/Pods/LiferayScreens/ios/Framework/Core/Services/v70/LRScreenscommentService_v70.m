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

#import "LRScreenscommentService_v70.h"

/**
 * @author Bruno Farache
 */
@implementation LRScreenscommentService_v70

- (NSArray *)getCommentsWithClassName:(NSString *)className classPK:(long long)classPK start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/screens.screenscomment/get-comments": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCommentWithClassName:(NSString *)className classPK:(long long)classPK body:(NSString *)body error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"body": [self checkNull: body]
	}];

	NSDictionary *_command = @{@"/screens.screenscomment/add-comment": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCommentWithCommentId:(long long)commentId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"commentId": @(commentId)
	}];

	NSDictionary *_command = @{@"/screens.screenscomment/get-comment": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCommentWithCommentId:(long long)commentId body:(NSString *)body error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"commentId": @(commentId),
		@"body": [self checkNull: body]
	}];

	NSDictionary *_command = @{@"/screens.screenscomment/update-comment": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCommentsCountWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/screens.screenscomment/get-comments-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

@end
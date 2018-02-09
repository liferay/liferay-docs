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

#ifdef LIFERAY_SCREENS_FRAMEWORK
	@import LRMobileSDK;
#else
	#import "LRBaseService.h"
#endif

/**
 * @author Bruno Farache
 */
@interface LRScreenscommentService_v70 : LRBaseService

- (NSArray *)getCommentsWithClassName:(NSString *)className classPK:(long long)classPK start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)addCommentWithClassName:(NSString *)className classPK:(long long)classPK body:(NSString *)body error:(NSError **)error;
- (NSDictionary *)getCommentWithCommentId:(long long)commentId error:(NSError **)error;
- (NSDictionary *)updateCommentWithCommentId:(long long)commentId body:(NSString *)body error:(NSError **)error;
- (NSNumber *)getCommentsCountWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error;

@end
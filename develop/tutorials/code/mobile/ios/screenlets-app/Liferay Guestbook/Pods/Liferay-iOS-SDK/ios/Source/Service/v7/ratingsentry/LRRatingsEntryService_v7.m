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

#import "LRRatingsEntryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRRatingsEntryService_v7

- (void)deleteEntryWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/ratingsentry/delete-entry": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEntryWithClassName:(NSString *)className classPK:(long long)classPK score:(double)score error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"score": @(score)
	}];

	NSDictionary *_command = @{@"/ratingsentry/update-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
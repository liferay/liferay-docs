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

#import "LRScreensratingsentryService_v70.h"

/**
 * @author Bruno Farache
 */
@implementation LRScreensratingsentryService_v70

- (NSDictionary *)getRatingsEntriesWithClassPK:(long long)classPK className:(NSString *)className ratingsLength:(int)ratingsLength error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classPK": @(classPK),
		@"className": [self checkNull: className],
		@"ratingsLength": @(ratingsLength)
	}];

	NSDictionary *_command = @{@"/screens.screensratingsentry/get-ratings-entries": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRatingsEntriesWithAssetEntryId:(long long)assetEntryId ratingsLength:(int)ratingsLength error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"assetEntryId": @(assetEntryId),
		@"ratingsLength": @(ratingsLength)
	}];

	NSDictionary *_command = @{@"/screens.screensratingsentry/get-ratings-entries": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRatingsEntryWithClassPK:(long long)classPK className:(NSString *)className score:(double)score ratingsLength:(int)ratingsLength error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classPK": @(classPK),
		@"className": [self checkNull: className],
		@"score": @(score),
		@"ratingsLength": @(ratingsLength)
	}];

	NSDictionary *_command = @{@"/screens.screensratingsentry/update-ratings-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteRatingsEntryWithClassPK:(long long)classPK className:(NSString *)className ratingsLength:(int)ratingsLength error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classPK": @(classPK),
		@"className": [self checkNull: className],
		@"ratingsLength": @(ratingsLength)
	}];

	NSDictionary *_command = @{@"/screens.screensratingsentry/delete-ratings-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
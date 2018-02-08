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

#import "LRFlagsEntryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRFlagsEntryService_v7

- (void)addEntryWithClassName:(NSString *)className classPK:(long long)classPK reporterEmailAddress:(NSString *)reporterEmailAddress reportedUserId:(long long)reportedUserId contentTitle:(NSString *)contentTitle contentURL:(NSString *)contentURL reason:(NSString *)reason serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"reporterEmailAddress": [self checkNull: reporterEmailAddress],
		@"reportedUserId": @(reportedUserId),
		@"contentTitle": [self checkNull: contentTitle],
		@"contentURL": [self checkNull: contentURL],
		@"reason": [self checkNull: reason],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/flags.flagsentry/add-entry": _params};

	[self.session invoke:_command error:error];
}

@end
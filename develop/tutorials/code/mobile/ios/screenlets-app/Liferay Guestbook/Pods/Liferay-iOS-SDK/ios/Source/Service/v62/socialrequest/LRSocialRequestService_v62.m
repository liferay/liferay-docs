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

#import "LRSocialRequestService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRSocialRequestService_v62

- (NSDictionary *)updateRequestWithRequestId:(long long)requestId status:(int)status themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"requestId": @(requestId),
		@"status": @(status),
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/socialrequest/update-request": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
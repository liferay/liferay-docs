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

#import "LRPortletPreferencesService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRPortletPreferencesService_v7

- (void)deleteArchivedPreferencesWithPortletItemId:(long long)portletItemId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"portletItemId": @(portletItemId)
	}];

	NSDictionary *_command = @{@"/portletpreferences/delete-archived-preferences": _params};

	[self.session invoke:_command error:error];
}

@end
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

#import "LRPortletPreferencesService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRPortletPreferencesService_v62

- (void)deleteArchivedPreferencesWithPortletItemId:(long long)portletItemId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"portletItemId": @(portletItemId)
	}];

	NSDictionary *_command = @{@"/portletpreferences/delete-archived-preferences": _params};

	[self.session invoke:_command error:error];
}

- (void)restoreArchivedPreferencesWithGroupId:(long long)groupId layout:(LRJSONObjectWrapper *)layout portletId:(NSString *)portletId portletItem:(LRJSONObjectWrapper *)portletItem preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
	}];

	[self mangleWrapperWithParams:_params name:@"layout" className:@"com.liferay.portal.model.Layout" wrapper:layout];
	[self mangleWrapperWithParams:_params name:@"portletItem" className:@"com.liferay.portal.model.PortletItem" wrapper:portletItem];
	[self mangleWrapperWithParams:_params name:@"preferences" className:@"javax.portlet.PortletPreferences" wrapper:preferences];

	NSDictionary *_command = @{@"/portletpreferences/restore-archived-preferences": _params};

	[self.session invoke:_command error:error];
}

- (void)restoreArchivedPreferencesWithGroupId:(long long)groupId layout:(LRJSONObjectWrapper *)layout portletId:(NSString *)portletId portletItemId:(long long)portletItemId preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"portletItemId": @(portletItemId),
	}];

	[self mangleWrapperWithParams:_params name:@"layout" className:@"com.liferay.portal.model.Layout" wrapper:layout];
	[self mangleWrapperWithParams:_params name:@"preferences" className:@"javax.portlet.PortletPreferences" wrapper:preferences];

	NSDictionary *_command = @{@"/portletpreferences/restore-archived-preferences": _params};

	[self.session invoke:_command error:error];
}

- (void)restoreArchivedPreferencesWithGroupId:(long long)groupId name:(NSString *)name layout:(LRJSONObjectWrapper *)layout portletId:(NSString *)portletId preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"portletId": [self checkNull: portletId],
	}];

	[self mangleWrapperWithParams:_params name:@"layout" className:@"com.liferay.portal.model.Layout" wrapper:layout];
	[self mangleWrapperWithParams:_params name:@"preferences" className:@"javax.portlet.PortletPreferences" wrapper:preferences];

	NSDictionary *_command = @{@"/portletpreferences/restore-archived-preferences": _params};

	[self.session invoke:_command error:error];
}

- (void)updateArchivePreferencesWithUserId:(long long)userId groupId:(long long)groupId name:(NSString *)name portletId:(NSString *)portletId preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"portletId": [self checkNull: portletId],
	}];

	[self mangleWrapperWithParams:_params name:@"preferences" className:@"javax.portlet.PortletPreferences" wrapper:preferences];

	NSDictionary *_command = @{@"/portletpreferences/update-archive-preferences": _params};

	[self.session invoke:_command error:error];
}

@end
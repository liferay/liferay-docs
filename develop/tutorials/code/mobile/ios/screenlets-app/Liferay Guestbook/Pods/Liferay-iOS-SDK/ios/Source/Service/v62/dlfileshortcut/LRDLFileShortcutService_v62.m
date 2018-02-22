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

#import "LRDLFileShortcutService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDLFileShortcutService_v62

- (NSDictionary *)addFileShortcutWithGroupId:(long long)groupId folderId:(long long)folderId toFileEntryId:(long long)toFileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"toFileEntryId": @(toFileEntryId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileshortcut/add-file-shortcut": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteFileShortcutWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileShortcutId": @(fileShortcutId)
	}];

	NSDictionary *_command = @{@"/dlfileshortcut/delete-file-shortcut": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getFileShortcutWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileShortcutId": @(fileShortcutId)
	}];

	NSDictionary *_command = @{@"/dlfileshortcut/get-file-shortcut": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFileShortcutWithFileShortcutId:(long long)fileShortcutId folderId:(long long)folderId toFileEntryId:(long long)toFileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileShortcutId": @(fileShortcutId),
		@"folderId": @(folderId),
		@"toFileEntryId": @(toFileEntryId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileshortcut/update-file-shortcut": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
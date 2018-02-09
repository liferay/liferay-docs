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

#import "LRDltrashService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDltrashService_v7

- (NSDictionary *)moveFileEntryToTrashWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dltrash/move-file-entry-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restoreFileEntryFromTrashWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dltrash/restore-file-entry-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)moveFileEntryFromTrashWithFileEntryId:(long long)fileEntryId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"newFolderId": @(newFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dltrash/move-file-entry-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveFileShortcutFromTrashWithFileShortcutId:(long long)fileShortcutId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileShortcutId": @(fileShortcutId),
		@"newFolderId": @(newFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dltrash/move-file-shortcut-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveFileShortcutToTrashWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileShortcutId": @(fileShortcutId)
	}];

	NSDictionary *_command = @{@"/dltrash/move-file-shortcut-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveFolderFromTrashWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"parentFolderId": @(parentFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dltrash/move-folder-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveFolderToTrashWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dltrash/move-folder-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restoreFileShortcutFromTrashWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileShortcutId": @(fileShortcutId)
	}];

	NSDictionary *_command = @{@"/dltrash/restore-file-shortcut-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (void)restoreFolderFromTrashWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dltrash/restore-folder-from-trash": _params};

	[self.session invoke:_command error:error];
}

@end
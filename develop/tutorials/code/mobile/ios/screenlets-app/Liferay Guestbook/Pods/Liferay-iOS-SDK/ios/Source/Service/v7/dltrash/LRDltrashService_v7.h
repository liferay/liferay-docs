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

#import "LRBaseService.h"

/**
 * @author Bruno Farache
 */
@interface LRDltrashService_v7 : LRBaseService

- (NSDictionary *)moveFileEntryToTrashWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (void)restoreFileEntryFromTrashWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)moveFileEntryFromTrashWithFileEntryId:(long long)fileEntryId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)moveFileShortcutFromTrashWithFileShortcutId:(long long)fileShortcutId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)moveFileShortcutToTrashWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error;
- (NSDictionary *)moveFolderFromTrashWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)moveFolderToTrashWithFolderId:(long long)folderId error:(NSError **)error;
- (void)restoreFileShortcutFromTrashWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error;
- (void)restoreFolderFromTrashWithFolderId:(long long)folderId error:(NSError **)error;

@end
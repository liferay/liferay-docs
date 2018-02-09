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

#import "LRDLFolderService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDLFolderService_v7

- (NSDictionary *)getFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getFolderWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder>" wrapper:obc];

	NSDictionary *_command = @{@"/dlfolder/get-folders": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status includeMountfolders:(BOOL)includeMountfolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"status": @(status),
		@"includeMountfolders": @(includeMountfolders),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder>" wrapper:obc];

	NSDictionary *_command = @{@"/dlfolder/get-folders": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveFolderWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"parentFolderId": @(parentFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfolder/move-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (BOOL)hasInheritableLockWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/has-inheritable-lock": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)hasFolderLockWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/has-folder-lock": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)isFolderLockedWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/is-folder-locked": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (NSArray *)getFolderIdsWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folder-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFolderWithFolderId:(long long)folderId name:(NSString *)name description:(NSString *)description defaultFileEntryTypeId:(long long)defaultFileEntryTypeId fileEntryTypeIds:(NSArray *)fileEntryTypeIds restrictionType:(int)restrictionType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"defaultFileEntryTypeId": @(defaultFileEntryTypeId),
		@"fileEntryTypeIds": [self checkNull: fileEntryTypeIds],
		@"restrictionType": @(restrictionType),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfolder/update-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFolderWithFolderId:(long long)folderId name:(NSString *)name description:(NSString *)description defaultFileEntryTypeId:(long long)defaultFileEntryTypeId fileEntryTypeIds:(NSArray *)fileEntryTypeIds overrideFileEntryTypes:(BOOL)overrideFileEntryTypes serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"defaultFileEntryTypeId": @(defaultFileEntryTypeId),
		@"fileEntryTypeIds": [self checkNull: fileEntryTypeIds],
		@"overrideFileEntryTypes": @(overrideFileEntryTypes),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfolder/update-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFolderWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description defaultFileEntryTypeId:(long long)defaultFileEntryTypeId fileEntryTypeIds:(NSArray *)fileEntryTypeIds restrictionType:(int)restrictionType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"parentFolderId": @(parentFolderId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"defaultFileEntryTypeId": @(defaultFileEntryTypeId),
		@"fileEntryTypeIds": [self checkNull: fileEntryTypeIds],
		@"restrictionType": @(restrictionType),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfolder/update-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders queryDefinition:(LRJSONObjectWrapper *)queryDefinition error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"includeMountFolders": @(includeMountFolders),
	}];

	[self mangleWrapperWithParams:_params name:@"queryDefinition" className:@"com.liferay.portal.kernel.dao.orm.QueryDefinition" wrapper:queryDefinition];

	NSDictionary *_command = @{@"/dlfolder/get-folders-and-file-entries-and-file-shortcuts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"includeMountFolders": @(includeMountFolders),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfolder/get-folders-and-file-entries-and-file-shortcuts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"includeMountFolders": @(includeMountFolders),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfolder/get-folders-and-file-entries-and-file-shortcuts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-file-entries-and-file-shortcuts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"mimeTypes": [self checkNull: mimeTypes]
	}];

	NSDictionary *_command = @{@"/dlfolder/get-file-entries-and-file-shortcuts-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-file-entries-and-file-shortcuts-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders queryDefinition:(LRJSONObjectWrapper *)queryDefinition error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"includeMountFolders": @(includeMountFolders),
	}];

	[self mangleWrapperWithParams:_params name:@"queryDefinition" className:@"com.liferay.portal.kernel.dao.orm.QueryDefinition" wrapper:queryDefinition];

	NSDictionary *_command = @{@"/dlfolder/get-folders-and-file-entries-and-file-shortcuts-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"includeMountFolders": @(includeMountFolders)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folders-and-file-entries-and-file-shortcuts-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"includeMountFolders": @(includeMountFolders)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folders-and-file-entries-and-file-shortcuts-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status includeMountfolders:(BOOL)includeMountfolders error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"status": @(status),
		@"includeMountfolders": @(includeMountfolders)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folders-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-folders-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getMountFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.document.library.kernel.model.DLFolder>" wrapper:obc];

	NSDictionary *_command = @{@"/dlfolder/get-mount-folders": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getMountFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-mount-folders-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)getSubfolderIdsWithFolderIds:(NSArray *)folderIds groupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderIds": [self checkNull: folderIds],
		@"groupId": @(groupId),
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-subfolder-ids": _params};

	[self.session invoke:_command error:error];
}

- (void)getSubfolderIdsWithFolderIds:(NSArray *)folderIds groupId:(long long)groupId folderId:(long long)folderId recurse:(BOOL)recurse error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderIds": [self checkNull: folderIds],
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"recurse": @(recurse)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-subfolder-ids": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getSubfolderIdsWithGroupId:(long long)groupId folderId:(long long)folderId recurse:(BOOL)recurse error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"recurse": @(recurse)
	}];

	NSDictionary *_command = @{@"/dlfolder/get-subfolder-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)lockFolderWithFolderId:(long long)folderId owner:(NSString *)owner inheritable:(BOOL)inheritable expirationTime:(long long)expirationTime error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"owner": [self checkNull: owner],
		@"inheritable": @(inheritable),
		@"expirationTime": @(expirationTime)
	}];

	NSDictionary *_command = @{@"/dlfolder/lock-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)lockFolderWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/lock-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)refreshFolderLockWithLockUuid:(NSString *)lockUuid companyId:(long long)companyId expirationTime:(long long)expirationTime error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"lockUuid": [self checkNull: lockUuid],
		@"companyId": @(companyId),
		@"expirationTime": @(expirationTime)
	}];

	NSDictionary *_command = @{@"/dlfolder/refresh-folder-lock": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)unlockFolderWithFolderId:(long long)folderId lockUuid:(NSString *)lockUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"lockUuid": [self checkNull: lockUuid]
	}];

	NSDictionary *_command = @{@"/dlfolder/unlock-folder": _params};

	[self.session invoke:_command error:error];
}

- (void)unlockFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name lockUuid:(NSString *)lockUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"name": [self checkNull: name],
		@"lockUuid": [self checkNull: lockUuid]
	}];

	NSDictionary *_command = @{@"/dlfolder/unlock-folder": _params};

	[self.session invoke:_command error:error];
}

- (BOOL)verifyInheritableLockWithFolderId:(long long)folderId lockUuid:(NSString *)lockUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"lockUuid": [self checkNull: lockUuid]
	}];

	NSDictionary *_command = @{@"/dlfolder/verify-inheritable-lock": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (void)deleteFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentFolderId": @(parentFolderId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/dlfolder/delete-folder": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteFolderWithFolderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfolder/delete-folder": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteFolderWithFolderId:(long long)folderId includeTrashedEntries:(BOOL)includeTrashedEntries error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"includeTrashedEntries": @(includeTrashedEntries)
	}];

	NSDictionary *_command = @{@"/dlfolder/delete-folder": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)addFolderWithGroupId:(long long)groupId repositoryId:(long long)repositoryId mountPoint:(BOOL)mountPoint parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"repositoryId": @(repositoryId),
		@"mountPoint": @(mountPoint),
		@"parentFolderId": @(parentFolderId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfolder/add-folder": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
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
@interface LRDLFolderService_v62 : LRBaseService

- (NSDictionary *)addFolderWithGroupId:(long long)groupId repositoryId:(long long)repositoryId mountPoint:(BOOL)mountPoint parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (void)deleteFolderWithFolderId:(long long)folderId includeTrashedEntries:(BOOL)includeTrashedEntries error:(NSError **)error;
- (void)deleteFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name error:(NSError **)error;
- (NSArray *)getFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error;
- (NSNumber *)getFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes error:(NSError **)error;
- (NSDictionary *)getFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)getFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name error:(NSError **)error;
- (NSArray *)getFolderIdsWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status includeMountfolders:(BOOL)includeMountfolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSNumber *)getFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSNumber *)getFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status includeMountfolders:(BOOL)includeMountfolders error:(NSError **)error;
- (NSArray *)getMountFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getMountFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (void)getSubfolderIdsWithFolderIds:(NSArray *)folderIds groupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getSubfolderIdsWithGroupId:(long long)groupId folderId:(long long)folderId recurse:(BOOL)recurse error:(NSError **)error;
- (BOOL)hasFolderLockWithFolderId:(long long)folderId error:(NSError **)error;
- (BOOL)hasInheritableLockWithFolderId:(long long)folderId error:(NSError **)error;
- (BOOL)isFolderLockedWithFolderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)lockFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)lockFolderWithFolderId:(long long)folderId owner:(NSString *)owner inheritable:(BOOL)inheritable expirationTime:(long long)expirationTime error:(NSError **)error;
- (NSDictionary *)moveFolderWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)refreshFolderLockWithLockUuid:(NSString *)lockUuid companyId:(long long)companyId expirationTime:(long long)expirationTime error:(NSError **)error;
- (void)unlockFolderWithFolderId:(long long)folderId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (void)unlockFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (NSDictionary *)updateFolderWithFolderId:(long long)folderId name:(NSString *)name description:(NSString *)description defaultFileEntryTypeId:(long long)defaultFileEntryTypeId fileEntryTypeIds:(NSArray *)fileEntryTypeIds overrideFileEntryTypes:(BOOL)overrideFileEntryTypes serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (BOOL)verifyInheritableLockWithFolderId:(long long)folderId lockUuid:(NSString *)lockUuid error:(NSError **)error;

@end
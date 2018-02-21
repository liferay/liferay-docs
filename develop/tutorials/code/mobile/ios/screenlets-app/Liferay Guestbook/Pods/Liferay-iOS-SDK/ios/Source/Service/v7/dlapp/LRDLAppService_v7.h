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
@interface LRDLAppService_v7 : LRBaseService

- (NSDictionary *)searchWithRepositoryId:(long long)repositoryId searchContext:(LRJSONObjectWrapper *)searchContext error:(NSError **)error;
- (NSDictionary *)searchWithRepositoryId:(long long)repositoryId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchWithRepositoryId:(long long)repositoryId searchContext:(LRJSONObjectWrapper *)searchContext query:(LRJSONObjectWrapper *)query error:(NSError **)error;
- (NSDictionary *)searchWithRepositoryId:(long long)repositoryId creatorUserId:(long long)creatorUserId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)getFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)getFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId title:(NSString *)title error:(NSError **)error;
- (NSDictionary *)getFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)getFolderWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId name:(NSString *)name error:(NSError **)error;
- (NSDictionary *)getFileVersionWithFileVersionId:(long long)fileVersionId error:(NSError **)error;
- (NSDictionary *)getFileShortcutWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error;
- (NSDictionary *)getFileEntryByUuidAndGroupIdWithUuid:(NSString *)uuid groupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId status:(int)status includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getFileEntriesWithRepositoryId:(long long)repositoryId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (NSDictionary *)moveFolderWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addFileEntryWithRepositoryId:(long long)repositoryId folderId:(long long)folderId sourceFileName:(NSString *)sourceFileName mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description changeLog:(NSString *)changeLog bytes:(NSData *)bytes serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addFileEntryWithRepositoryId:(long long)repositoryId folderId:(long long)folderId sourceFileName:(NSString *)sourceFileName mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description changeLog:(NSString *)changeLog file:(LRUploadData *)file serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addTempFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId folderName:(NSString *)folderName fileName:(NSString *)fileName file:(LRUploadData *)file mimeType:(NSString *)mimeType error:(NSError **)error;
- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId majorVersion:(BOOL)majorVersion changeLog:(NSString *)changeLog serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)cancelCheckOutWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)addFileShortcutWithRepositoryId:(long long)repositoryId folderId:(long long)folderId toFileEntryId:(long long)toFileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFileShortcutWithFileShortcutId:(long long)fileShortcutId error:(NSError **)error;
- (NSDictionary *)moveFileEntryWithFileEntryId:(long long)fileEntryId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)subscribeFileEntryTypeWithGroupId:(long long)groupId fileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (void)subscribeFolderWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (void)unsubscribeFileEntryTypeWithGroupId:(long long)groupId fileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (void)unsubscribeFolderWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)updateFileEntryWithFileEntryId:(long long)fileEntryId sourceFileName:(NSString *)sourceFileName mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description changeLog:(NSString *)changeLog majorVersion:(BOOL)majorVersion file:(LRUploadData *)file serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateFileEntryWithFileEntryId:(long long)fileEntryId sourceFileName:(NSString *)sourceFileName mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description changeLog:(NSString *)changeLog majorVersion:(BOOL)majorVersion bytes:(NSData *)bytes serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateFileShortcutWithFileShortcutId:(long long)fileShortcutId folderId:(long long)folderId toFileEntryId:(long long)toFileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateFolderWithFolderId:(long long)folderId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyFolderWithRepositoryId:(long long)repositoryId sourceFolderId:(long long)sourceFolderId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getFoldersAndFileEntriesAndFileShortcutsWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (void)deleteFileEntryByTitleWithRepositoryId:(long long)repositoryId folderId:(long long)folderId title:(NSString *)title error:(NSError **)error;
- (void)deleteFileVersionWithFileEntryId:(long long)fileEntryId version:(NSString *)version error:(NSError **)error;
- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId owner:(NSString *)owner expirationTime:(long long)expirationTime serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)checkOutFileEntryWithFileEntryId:(long long)fileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getFileEntriesAndFileShortcutsWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getFileEntriesAndFileShortcutsCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status error:(NSError **)error;
- (NSNumber *)getFileEntriesAndFileShortcutsCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes error:(NSError **)error;
- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSNumber *)getFoldersAndFileEntriesAndFileShortcutsCountWithRepositoryId:(long long)repositoryId folderId:(long long)folderId status:(int)status mimeTypes:(NSArray *)mimeTypes includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSNumber *)getFoldersCountWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSNumber *)getFoldersCountWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSNumber *)getFoldersCountWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId status:(int)status includeMountFolders:(BOOL)includeMountFolders error:(NSError **)error;
- (NSNumber *)getFoldersFileEntriesCountWithRepositoryId:(long long)repositoryId folderIds:(NSArray *)folderIds status:(int)status error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId mimeTypes:(NSArray *)mimeTypes status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error;
- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId error:(NSError **)error;
- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId mimeTypes:(NSArray *)mimeTypes status:(int)status error:(NSError **)error;
- (NSArray *)getMountFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getMountFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getMountFoldersWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSNumber *)getMountFoldersCountWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (void)getSubfolderIdsWithRepositoryId:(long long)repositoryId folderIds:(NSArray *)folderIds folderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getSubfolderIdsWithRepositoryId:(long long)repositoryId folderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getSubfolderIdsWithRepositoryId:(long long)repositoryId folderId:(long long)folderId recurse:(BOOL)recurse error:(NSError **)error;
- (NSArray *)getTempFileNamesWithGroupId:(long long)groupId folderId:(long long)folderId folderName:(NSString *)folderName error:(NSError **)error;
- (NSDictionary *)lockFolderWithRepositoryId:(long long)repositoryId folderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)lockFolderWithRepositoryId:(long long)repositoryId folderId:(long long)folderId owner:(NSString *)owner inheritable:(BOOL)inheritable expirationTime:(long long)expirationTime error:(NSError **)error;
- (NSDictionary *)refreshFileEntryLockWithLockUuid:(NSString *)lockUuid companyId:(long long)companyId expirationTime:(long long)expirationTime error:(NSError **)error;
- (NSDictionary *)refreshFolderLockWithLockUuid:(NSString *)lockUuid companyId:(long long)companyId expirationTime:(long long)expirationTime error:(NSError **)error;
- (void)revertFileEntryWithFileEntryId:(long long)fileEntryId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)unlockFolderWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId name:(NSString *)name lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (void)unlockFolderWithRepositoryId:(long long)repositoryId folderId:(long long)folderId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (NSDictionary *)updateFileEntryAndCheckInWithFileEntryId:(long long)fileEntryId sourceFileName:(NSString *)sourceFileName mimeType:(NSString *)mimeType title:(NSString *)title description:(NSString *)description changeLog:(NSString *)changeLog majorVersion:(BOOL)majorVersion file:(LRUploadData *)file serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (BOOL)verifyFileEntryCheckOutWithRepositoryId:(long long)repositoryId fileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (BOOL)verifyFileEntryLockWithRepositoryId:(long long)repositoryId fileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (BOOL)verifyInheritableLockWithRepositoryId:(long long)repositoryId folderId:(long long)folderId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (void)deleteTempFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId folderName:(NSString *)folderName fileName:(NSString *)fileName error:(NSError **)error;
- (void)deleteFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (void)deleteFolderWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId name:(NSString *)name error:(NSError **)error;
- (NSDictionary *)addFolderWithRepositoryId:(long long)repositoryId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error;

@end
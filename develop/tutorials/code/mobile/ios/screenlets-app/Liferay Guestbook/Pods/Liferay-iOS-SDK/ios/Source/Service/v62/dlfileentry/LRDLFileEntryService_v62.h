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
@interface LRDLFileEntryService_v62 : LRBaseService

- (NSDictionary *)cancelCheckOutWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId major:(BOOL)major changeLog:(NSString *)changeLog serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId owner:(NSString *)owner expirationTime:(long long)expirationTime error:(NSError **)error;
- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId owner:(NSString *)owner expirationTime:(long long)expirationTime serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyFileEntryWithGroupId:(long long)groupId repositoryId:(long long)repositoryId fileEntryId:(long long)fileEntryId destFolderId:(long long)destFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (void)deleteFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId title:(NSString *)title error:(NSError **)error;
- (void)deleteFileVersionWithFileEntryId:(long long)fileEntryId version:(NSString *)version error:(NSError **)error;
- (NSDictionary *)fetchFileEntryByImageIdWithImageId:(long long)imageId error:(NSError **)error;
- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes error:(NSError **)error;
- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error;
- (NSDictionary *)getFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)getFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId title:(NSString *)title error:(NSError **)error;
- (NSDictionary *)getFileEntryByUuidAndGroupIdWithUuid:(NSString *)uuid groupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)getFileEntryLockWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSNumber *)getFoldersFileEntriesCountWithGroupId:(long long)groupId folderIds:(NSArray *)folderIds status:(int)status error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId mimeTypes:(NSArray *)mimeTypes status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId error:(NSError **)error;
- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId mimeTypes:(NSArray *)mimeTypes status:(int)status error:(NSError **)error;
- (BOOL)hasFileEntryLockWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (BOOL)isFileEntryCheckedOutWithFileEntryId:(long long)fileEntryId error:(NSError **)error;
- (NSDictionary *)moveFileEntryWithFileEntryId:(long long)fileEntryId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)refreshFileEntryLockWithLockUuid:(NSString *)lockUuid companyId:(long long)companyId expirationTime:(long long)expirationTime error:(NSError **)error;
- (void)revertFileEntryWithFileEntryId:(long long)fileEntryId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (BOOL)verifyFileEntryCheckOutWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error;
- (BOOL)verifyFileEntryLockWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error;

@end
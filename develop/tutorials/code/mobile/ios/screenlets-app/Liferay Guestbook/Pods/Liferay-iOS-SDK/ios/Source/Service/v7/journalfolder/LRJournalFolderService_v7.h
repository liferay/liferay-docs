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
@interface LRJournalFolderService_v7 : LRBaseService

- (NSDictionary *)getFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getFoldersWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)moveFolderWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getDdmStructuresWithGroupIds:(NSArray *)groupIds folderId:(long long)folderId restrictionType:(int)restrictionType error:(NSError **)error;
- (void)subscribeWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (void)unsubscribeWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getFolderIdsWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSDictionary *)updateFolderWithGroupId:(long long)groupId folderId:(long long)folderId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description mergeWithParentFolder:(BOOL)mergeWithParentFolder serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateFolderWithGroupId:(long long)groupId folderId:(long long)folderId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description ddmStructureIds:(NSArray *)ddmStructureIds restrictionType:(int)restrictionType mergeWithParentFolder:(BOOL)mergeWithParentFolder serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSNumber *)getFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId status:(int)status error:(NSError **)error;
- (NSNumber *)getFoldersCountWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId error:(NSError **)error;
- (NSArray *)getSubfolderIdsWithGroupId:(long long)groupId folderId:(long long)folderId recurse:(BOOL)recurse error:(NSError **)error;
- (void)getSubfolderIdsWithFolderIds:(NSArray *)folderIds groupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (void)getSubfolderIdsWithFolderIds:(NSArray *)folderIds groupId:(long long)groupId folderId:(long long)folderId recurse:(BOOL)recurse error:(NSError **)error;
- (NSDictionary *)moveFolderFromTrashWithFolderId:(long long)folderId parentFolderId:(long long)parentFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)moveFolderToTrashWithFolderId:(long long)folderId error:(NSError **)error;
- (void)restoreFolderFromTrashWithFolderId:(long long)folderId error:(NSError **)error;
- (void)deleteFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (void)deleteFolderWithFolderId:(long long)folderId includeTrashedEntries:(BOOL)includeTrashedEntries error:(NSError **)error;
- (NSDictionary *)addFolderWithGroupId:(long long)groupId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)fetchFolderWithFolderId:(long long)folderId error:(NSError **)error;
- (NSArray *)getFoldersAndArticlesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersAndArticlesWithGroupId:(long long)groupId userId:(long long)userId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getFoldersAndArticlesWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getFoldersAndArticlesCountWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error;
- (NSNumber *)getFoldersAndArticlesCountWithGroupId:(long long)groupId folderIds:(NSArray *)folderIds status:(int)status error:(NSError **)error;
- (NSNumber *)getFoldersAndArticlesCountWithGroupId:(long long)groupId userId:(long long)userId folderId:(long long)folderId status:(int)status error:(NSError **)error;
- (NSNumber *)getFoldersAndArticlesCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error;

@end
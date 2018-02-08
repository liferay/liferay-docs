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
@interface LRAssetVocabularyService_v7 : LRBaseService

- (NSDictionary *)addVocabularyWithGroupId:(long long)groupId title:(NSString *)title serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addVocabularyWithGroupId:(long long)groupId title:(NSString *)title titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap settings:(NSString *)settings serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)deleteVocabulariesWithVocabularyIds:(NSArray *)vocabularyIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteVocabularyWithVocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSArray *)getCompanyVocabulariesWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSArray *)getGroupsVocabulariesWithGroupIds:(NSArray *)groupIds className:(NSString *)className error:(NSError **)error;
- (NSArray *)getGroupsVocabulariesWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)getGroupsVocabulariesWithGroupIds:(NSArray *)groupIds className:(NSString *)className classTypePK:(long long)classTypePK error:(NSError **)error;
- (NSArray *)getGroupVocabulariesWithGroupId:(long long)groupId createDefaultVocabulary:(BOOL)createDefaultVocabulary start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupVocabulariesWithGroupId:(long long)groupId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupVocabulariesWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupVocabulariesWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)getGroupVocabulariesWithGroupId:(long long)groupId createDefaultVocabulary:(BOOL)createDefaultVocabulary error:(NSError **)error;
- (NSArray *)getGroupVocabulariesWithGroupId:(long long)groupId error:(NSError **)error;
- (NSNumber *)getGroupVocabulariesCountWithGroupId:(long long)groupId name:(NSString *)name error:(NSError **)error;
- (NSNumber *)getGroupVocabulariesCountWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSNumber *)getGroupVocabulariesCountWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getVocabulariesWithVocabularyIds:(NSArray *)vocabularyIds error:(NSError **)error;
- (NSDictionary *)updateVocabularyWithVocabularyId:(long long)vocabularyId title:(NSString *)title titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap settings:(NSString *)settings serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)fetchVocabularyWithVocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSDictionary *)searchVocabulariesDisplayWithGroupId:(long long)groupId title:(NSString *)title addDefaultVocabulary:(BOOL)addDefaultVocabulary start:(int)start end:(int)end sort:(LRJSONObjectWrapper *)sort error:(NSError **)error;
- (NSDictionary *)searchVocabulariesDisplayWithGroupId:(long long)groupId title:(NSString *)title addDefaultVocabulary:(BOOL)addDefaultVocabulary start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)getGroupVocabulariesDisplayWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)getGroupVocabulariesDisplayWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end addDefaultVocabulary:(BOOL)addDefaultVocabulary obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)getVocabularyWithVocabularyId:(long long)vocabularyId error:(NSError **)error;

@end
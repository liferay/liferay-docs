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
@interface LRAssetCategoryService_v62 : LRBaseService

- (NSDictionary *)addCategoryWithTitle:(NSString *)title vocabularyId:(long long)vocabularyId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addCategoryWithParentCategoryId:(long long)parentCategoryId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap vocabularyId:(long long)vocabularyId categoryProperties:(NSArray *)categoryProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteCategoriesWithCategoryIds:(NSArray *)categoryIds error:(NSError **)error;
- (NSArray *)deleteCategoriesWithCategoryIds:(NSArray *)categoryIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteCategoryWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getCategoriesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSDictionary *)getCategoryWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getChildCategoriesWithParentCategoryId:(long long)parentCategoryId error:(NSError **)error;
- (NSArray *)getChildCategoriesWithParentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getJsonSearchWithGroupId:(long long)groupId name:(NSString *)name vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)getJsonVocabularyCategoriesWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)getJsonVocabularyCategoriesWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithParentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSDictionary *)getVocabularyCategoriesDisplayWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)getVocabularyCategoriesDisplayWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyRootCategoriesWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyRootCategoriesWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getVocabularyRootCategoriesCountWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSDictionary *)moveCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)searchWithGroupId:(long long)groupId name:(NSString *)name categoryProperties:(NSArray *)categoryProperties start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)searchWithGroupIds:(NSArray *)groupIds name:(NSString *)name vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)searchWithGroupId:(long long)groupId keywords:(NSString *)keywords vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)updateCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap vocabularyId:(long long)vocabularyId categoryProperties:(NSArray *)categoryProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
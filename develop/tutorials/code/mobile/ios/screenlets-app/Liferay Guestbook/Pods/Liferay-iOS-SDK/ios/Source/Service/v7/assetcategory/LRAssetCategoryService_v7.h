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
@interface LRAssetCategoryService_v7 : LRBaseService

- (NSArray *)searchWithGroupId:(long long)groupId keywords:(NSString *)keywords vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)searchWithGroupId:(long long)groupId name:(NSString *)name categoryProperties:(NSArray *)categoryProperties start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)searchWithGroupIds:(NSArray *)groupIds name:(NSString *)name vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)getCategoryWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSDictionary *)fetchCategoryWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getCategoriesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithParentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyCategoriesWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId parentCategory:(long long)parentCategory vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSDictionary *)addCategoryWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap vocabularyId:(long long)vocabularyId categoryProperties:(NSArray *)categoryProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addCategoryWithGroupId:(long long)groupId title:(NSString *)title vocabularyId:(long long)vocabularyId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteCategoriesWithCategoryIds:(NSArray *)categoryIds error:(NSError **)error;
- (NSArray *)deleteCategoriesWithCategoryIds:(NSArray *)categoryIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteCategoryWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getChildCategoriesWithParentCategoryId:(long long)parentCategoryId error:(NSError **)error;
- (NSArray *)getChildCategoriesWithParentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getVocabularyRootCategoriesWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getVocabularyRootCategoriesCountWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId error:(NSError **)error;
- (NSDictionary *)moveCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap vocabularyId:(long long)vocabularyId categoryProperties:(NSArray *)categoryProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSString *)getCategoryPathWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSDictionary *)getVocabularyCategoriesDisplayWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)getVocabularyCategoriesDisplayWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)searchCategoriesDisplayWithGroupIds:(NSArray *)groupIds title:(NSString *)title parentCategoryIds:(NSArray *)parentCategoryIds vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchCategoriesDisplayWithGroupIds:(NSArray *)groupIds title:(NSString *)title vocabularyIds:(NSArray *)vocabularyIds parentCategoryIds:(NSArray *)parentCategoryIds start:(int)start end:(int)end sort:(LRJSONObjectWrapper *)sort error:(NSError **)error;
- (NSDictionary *)searchCategoriesDisplayWithGroupId:(long long)groupId title:(NSString *)title vocabularyId:(long long)vocabularyId parentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end sort:(LRJSONObjectWrapper *)sort error:(NSError **)error;
- (NSDictionary *)searchCategoriesDisplayWithGroupId:(long long)groupId title:(NSString *)title parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchCategoriesDisplayWithGroupId:(long long)groupId title:(NSString *)title vocabularyId:(long long)vocabularyId start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)searchCategoriesDisplayWithGroupIds:(NSArray *)groupIds title:(NSString *)title vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error;

@end
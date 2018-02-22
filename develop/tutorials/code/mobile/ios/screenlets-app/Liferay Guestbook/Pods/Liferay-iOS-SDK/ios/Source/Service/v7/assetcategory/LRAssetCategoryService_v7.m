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

#import "LRAssetCategoryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRAssetCategoryService_v7

- (NSArray *)searchWithGroupId:(long long)groupId keywords:(NSString *)keywords vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"keywords": [self checkNull: keywords],
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithGroupId:(long long)groupId name:(NSString *)name categoryProperties:(NSArray *)categoryProperties start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"categoryProperties": [self checkNull: categoryProperties],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetcategory/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithGroupIds:(NSArray *)groupIds name:(NSString *)name vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"name": [self checkNull: name],
		@"vocabularyIds": [self checkNull: vocabularyIds],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetcategory/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCategoryWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchCategoryWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/assetcategory/fetch-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getVocabularyCategoriesWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getVocabularyCategoriesWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryId": @(parentCategoryId),
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getVocabularyCategoriesWithParentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCategoryId": @(parentCategoryId),
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getVocabularyCategoriesWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId parentCategory:(long long)parentCategory vocabularyId:(long long)vocabularyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategory": @(parentCategory),
		@"vocabularyId": @(vocabularyId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"vocabularyId": @(vocabularyId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getVocabularyCategoriesCountWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"vocabularyId": @(vocabularyId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCategoryWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap vocabularyId:(long long)vocabularyId categoryProperties:(NSArray *)categoryProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryId": @(parentCategoryId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"vocabularyId": @(vocabularyId),
		@"categoryProperties": [self checkNull: categoryProperties],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assetcategory/add-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCategoryWithGroupId:(long long)groupId title:(NSString *)title vocabularyId:(long long)vocabularyId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"title": [self checkNull: title],
		@"vocabularyId": @(vocabularyId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assetcategory/add-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteCategoriesWithCategoryIds:(NSArray *)categoryIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryIds": [self checkNull: categoryIds]
	}];

	NSDictionary *_command = @{@"/assetcategory/delete-categories": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)deleteCategoriesWithCategoryIds:(NSArray *)categoryIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryIds": [self checkNull: categoryIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assetcategory/delete-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)deleteCategoryWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/assetcategory/delete-category": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getChildCategoriesWithParentCategoryId:(long long)parentCategoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCategoryId": @(parentCategoryId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-child-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getChildCategoriesWithParentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCategoryId": @(parentCategoryId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-child-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getVocabularyRootCategoriesWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-root-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getVocabularyRootCategoriesCountWithGroupId:(long long)groupId vocabularyId:(long long)vocabularyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"vocabularyId": @(vocabularyId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-root-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"parentCategoryId": @(parentCategoryId),
		@"vocabularyId": @(vocabularyId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assetcategory/move-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap vocabularyId:(long long)vocabularyId categoryProperties:(NSArray *)categoryProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"parentCategoryId": @(parentCategoryId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"vocabularyId": @(vocabularyId),
		@"categoryProperties": [self checkNull: categoryProperties],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assetcategory/update-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSString *)getCategoryPathWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/assetcategory/get-category-path": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getVocabularyCategoriesDisplayWithVocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getVocabularyCategoriesDisplayWithGroupId:(long long)groupId name:(NSString *)name vocabularyId:(long long)vocabularyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetCategory>" wrapper:obc];

	NSDictionary *_command = @{@"/assetcategory/get-vocabulary-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchCategoriesDisplayWithGroupIds:(NSArray *)groupIds title:(NSString *)title parentCategoryIds:(NSArray *)parentCategoryIds vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"title": [self checkNull: title],
		@"parentCategoryIds": [self checkNull: parentCategoryIds],
		@"vocabularyIds": [self checkNull: vocabularyIds],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetcategory/search-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchCategoriesDisplayWithGroupIds:(NSArray *)groupIds title:(NSString *)title vocabularyIds:(NSArray *)vocabularyIds parentCategoryIds:(NSArray *)parentCategoryIds start:(int)start end:(int)end sort:(LRJSONObjectWrapper *)sort error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"title": [self checkNull: title],
		@"vocabularyIds": [self checkNull: vocabularyIds],
		@"parentCategoryIds": [self checkNull: parentCategoryIds],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"sort" className:@"com.liferay.portal.kernel.search.Sort" wrapper:sort];

	NSDictionary *_command = @{@"/assetcategory/search-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchCategoriesDisplayWithGroupId:(long long)groupId title:(NSString *)title vocabularyId:(long long)vocabularyId parentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end sort:(LRJSONObjectWrapper *)sort error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"title": [self checkNull: title],
		@"vocabularyId": @(vocabularyId),
		@"parentCategoryId": @(parentCategoryId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"sort" className:@"com.liferay.portal.kernel.search.Sort" wrapper:sort];

	NSDictionary *_command = @{@"/assetcategory/search-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchCategoriesDisplayWithGroupId:(long long)groupId title:(NSString *)title parentCategoryId:(long long)parentCategoryId vocabularyId:(long long)vocabularyId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"title": [self checkNull: title],
		@"parentCategoryId": @(parentCategoryId),
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetcategory/search-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchCategoriesDisplayWithGroupId:(long long)groupId title:(NSString *)title vocabularyId:(long long)vocabularyId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"title": [self checkNull: title],
		@"vocabularyId": @(vocabularyId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetcategory/search-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchCategoriesDisplayWithGroupIds:(NSArray *)groupIds title:(NSString *)title vocabularyIds:(NSArray *)vocabularyIds start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"title": [self checkNull: title],
		@"vocabularyIds": [self checkNull: vocabularyIds],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assetcategory/search-categories-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
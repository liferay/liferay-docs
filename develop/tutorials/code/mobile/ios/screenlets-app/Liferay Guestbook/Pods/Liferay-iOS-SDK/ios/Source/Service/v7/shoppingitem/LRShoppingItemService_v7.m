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

#import "LRShoppingItemService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRShoppingItemService_v7

- (NSDictionary *)addItemWithGroupId:(long long)groupId categoryId:(long long)categoryId sku:(NSString *)sku name:(NSString *)name description:(NSString *)description properties:(NSString *)properties fieldsQuantities:(NSString *)fieldsQuantities requiresShipping:(BOOL)requiresShipping stockQuantity:(int)stockQuantity featured:(BOOL)featured sale:(BOOL)sale smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile mediumImage:(BOOL)mediumImage mediumImageURL:(NSString *)mediumImageURL mediumFile:(LRUploadData *)mediumFile largeImage:(BOOL)largeImage largeImageURL:(NSString *)largeImageURL largeFile:(LRUploadData *)largeFile itemFields:(NSArray *)itemFields itemPrices:(NSArray *)itemPrices serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId),
		@"sku": [self checkNull: sku],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"properties": [self checkNull: properties],
		@"fieldsQuantities": [self checkNull: fieldsQuantities],
		@"requiresShipping": @(requiresShipping),
		@"stockQuantity": @(stockQuantity),
		@"featured": @(featured),
		@"sale": @(sale),
		@"smallImage": @(smallImage),
		@"smallImageURL": [self checkNull: smallImageURL],
		@"smallFile": [self checkNull: smallFile],
		@"mediumImage": @(mediumImage),
		@"mediumImageURL": [self checkNull: mediumImageURL],
		@"mediumFile": [self checkNull: mediumFile],
		@"largeImage": @(largeImage),
		@"largeImageURL": [self checkNull: largeImageURL],
		@"largeFile": [self checkNull: largeFile],
		@"itemFields": [self checkNull: itemFields],
		@"itemPrices": [self checkNull: itemPrices],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/shopping.shoppingitem/add-item": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)getItemWithItemId:(long long)itemId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"itemId": @(itemId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingitem/get-item": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getItemsWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingitem/get-items": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getItemsWithGroupId:(long long)groupId categoryId:(long long)categoryId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.shopping.model.ShoppingItem>" wrapper:obc];

	NSDictionary *_command = @{@"/shopping.shoppingitem/get-items": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)deleteItemWithItemId:(long long)itemId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"itemId": @(itemId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingitem/delete-item": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)getCategoriesItemsCountWithGroupId:(long long)groupId categoryIds:(NSArray *)categoryIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryIds": [self checkNull: categoryIds]
	}];

	NSDictionary *_command = @{@"/shopping.shoppingitem/get-categories-items-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getItemsCountWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingitem/get-items-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getItemsPrevAndNextWithItemId:(long long)itemId obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"itemId": @(itemId),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.shopping.model.ShoppingItem>" wrapper:obc];

	NSDictionary *_command = @{@"/shopping.shoppingitem/get-items-prev-and-next": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateItemWithItemId:(long long)itemId groupId:(long long)groupId categoryId:(long long)categoryId sku:(NSString *)sku name:(NSString *)name description:(NSString *)description properties:(NSString *)properties fieldsQuantities:(NSString *)fieldsQuantities requiresShipping:(BOOL)requiresShipping stockQuantity:(int)stockQuantity featured:(BOOL)featured sale:(BOOL)sale smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile mediumImage:(BOOL)mediumImage mediumImageURL:(NSString *)mediumImageURL mediumFile:(LRUploadData *)mediumFile largeImage:(BOOL)largeImage largeImageURL:(NSString *)largeImageURL largeFile:(LRUploadData *)largeFile itemFields:(NSArray *)itemFields itemPrices:(NSArray *)itemPrices serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"itemId": @(itemId),
		@"groupId": @(groupId),
		@"categoryId": @(categoryId),
		@"sku": [self checkNull: sku],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"properties": [self checkNull: properties],
		@"fieldsQuantities": [self checkNull: fieldsQuantities],
		@"requiresShipping": @(requiresShipping),
		@"stockQuantity": @(stockQuantity),
		@"featured": @(featured),
		@"sale": @(sale),
		@"smallImage": @(smallImage),
		@"smallImageURL": [self checkNull: smallImageURL],
		@"smallFile": [self checkNull: smallFile],
		@"mediumImage": @(mediumImage),
		@"mediumImageURL": [self checkNull: mediumImageURL],
		@"mediumFile": [self checkNull: mediumFile],
		@"largeImage": @(largeImage),
		@"largeImageURL": [self checkNull: largeImageURL],
		@"largeFile": [self checkNull: largeFile],
		@"itemFields": [self checkNull: itemFields],
		@"itemPrices": [self checkNull: itemPrices],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/shopping.shoppingitem/update-item": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

@end
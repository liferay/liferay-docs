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
@interface LRShoppingItemService_v62 : LRBaseService

- (void)addBookItemsWithGroupId:(long long)groupId categoryId:(long long)categoryId isbns:(NSArray *)isbns error:(NSError **)error;
- (NSDictionary *)addItemWithGroupId:(long long)groupId categoryId:(long long)categoryId sku:(NSString *)sku name:(NSString *)name description:(NSString *)description properties:(NSString *)properties fieldsQuantities:(NSString *)fieldsQuantities requiresShipping:(BOOL)requiresShipping stockQuantity:(int)stockQuantity featured:(BOOL)featured sale:(BOOL)sale smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile mediumImage:(BOOL)mediumImage mediumImageURL:(NSString *)mediumImageURL mediumFile:(LRUploadData *)mediumFile largeImage:(BOOL)largeImage largeImageURL:(NSString *)largeImageURL largeFile:(LRUploadData *)largeFile itemFields:(NSArray *)itemFields itemPrices:(NSArray *)itemPrices serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteItemWithItemId:(long long)itemId error:(NSError **)error;
- (NSNumber *)getCategoriesItemsCountWithGroupId:(long long)groupId categoryIds:(NSArray *)categoryIds error:(NSError **)error;
- (NSDictionary *)getItemWithItemId:(long long)itemId error:(NSError **)error;
- (NSArray *)getItemsWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getItemsWithGroupId:(long long)groupId categoryId:(long long)categoryId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getItemsCountWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getItemsPrevAndNextWithItemId:(long long)itemId obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)updateItemWithItemId:(long long)itemId groupId:(long long)groupId categoryId:(long long)categoryId sku:(NSString *)sku name:(NSString *)name description:(NSString *)description properties:(NSString *)properties fieldsQuantities:(NSString *)fieldsQuantities requiresShipping:(BOOL)requiresShipping stockQuantity:(int)stockQuantity featured:(BOOL)featured sale:(BOOL)sale smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile mediumImage:(BOOL)mediumImage mediumImageURL:(NSString *)mediumImageURL mediumFile:(LRUploadData *)mediumFile largeImage:(BOOL)largeImage largeImageURL:(NSString *)largeImageURL largeFile:(LRUploadData *)largeFile itemFields:(NSArray *)itemFields itemPrices:(NSArray *)itemPrices serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
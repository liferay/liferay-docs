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
@interface LRAssetTagPropertyService_v62 : LRBaseService

- (NSDictionary *)addTagPropertyWithTagId:(long long)tagId key:(NSString *)key value:(NSString *)value error:(NSError **)error;
- (void)deleteTagPropertyWithTagPropertyId:(long long)tagPropertyId error:(NSError **)error;
- (NSArray *)getTagPropertiesWithTagId:(long long)tagId error:(NSError **)error;
- (NSArray *)getTagPropertyValuesWithCompanyId:(long long)companyId key:(NSString *)key error:(NSError **)error;
- (NSDictionary *)updateTagPropertyWithTagPropertyId:(long long)tagPropertyId key:(NSString *)key value:(NSString *)value error:(NSError **)error;

@end
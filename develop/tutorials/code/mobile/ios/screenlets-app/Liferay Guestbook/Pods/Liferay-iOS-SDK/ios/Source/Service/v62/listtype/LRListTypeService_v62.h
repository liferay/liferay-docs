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
@interface LRListTypeService_v62 : LRBaseService

- (NSDictionary *)getListTypeWithListTypeId:(int)listTypeId error:(NSError **)error;
- (NSArray *)getListTypesWithType:(NSString *)type error:(NSError **)error;
- (void)validateWithListTypeId:(int)listTypeId type:(NSString *)type error:(NSError **)error;
- (void)validateWithListTypeId:(int)listTypeId classNameId:(long long)classNameId type:(NSString *)type error:(NSError **)error;

@end
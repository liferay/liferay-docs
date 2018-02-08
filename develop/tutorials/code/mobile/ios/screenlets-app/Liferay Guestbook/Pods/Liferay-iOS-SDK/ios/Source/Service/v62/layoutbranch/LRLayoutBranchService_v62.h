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
@interface LRLayoutBranchService_v62 : LRBaseService

- (NSDictionary *)addLayoutBranchWithLayoutRevisionId:(long long)layoutRevisionId name:(NSString *)name description:(NSString *)description master:(BOOL)master serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteLayoutBranchWithLayoutBranchId:(long long)layoutBranchId error:(NSError **)error;
- (NSDictionary *)updateLayoutBranchWithLayoutBranchId:(long long)layoutBranchId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
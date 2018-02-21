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
@interface LRResourcePermissionService_v62 : LRBaseService

- (void)addResourcePermissionWithGroupId:(long long)groupId companyId:(long long)companyId name:(NSString *)name scope:(int)scope primKey:(NSString *)primKey roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)removeResourcePermissionWithGroupId:(long long)groupId companyId:(long long)companyId name:(NSString *)name scope:(int)scope primKey:(NSString *)primKey roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)removeResourcePermissionsWithGroupId:(long long)groupId companyId:(long long)companyId name:(NSString *)name scope:(int)scope roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)setIndividualResourcePermissionsWithGroupId:(long long)groupId companyId:(long long)companyId name:(NSString *)name primKey:(NSString *)primKey roleIdsToActionIds:(NSDictionary *)roleIdsToActionIds error:(NSError **)error;
- (void)setIndividualResourcePermissionsWithGroupId:(long long)groupId companyId:(long long)companyId name:(NSString *)name primKey:(NSString *)primKey roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error;

@end
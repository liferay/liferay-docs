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
@interface LRResourceBlockService_v7 : LRBaseService

- (void)setIndividualScopePermissionsWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error;
- (void)setIndividualScopePermissionsWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleIdsToActionIds:(NSDictionary *)roleIdsToActionIds error:(NSError **)error;
- (void)setCompanyScopePermissionsWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error;
- (void)setGroupScopePermissionsWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error;
- (void)addCompanyScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)addGroupScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)addIndividualScopePermissionWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)removeAllGroupScopePermissionsWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)removeCompanyScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)removeGroupScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;
- (void)removeIndividualScopePermissionWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error;

@end
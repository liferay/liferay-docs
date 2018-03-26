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
@interface LRRoleService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId keywords:(NSString *)keywords types:(NSArray *)types params:(NSDictionary *)params start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getRolesWithType:(int)type subtype:(NSString *)subtype error:(NSError **)error;
- (NSArray *)getRolesWithCompanyId:(long long)companyId types:(NSArray *)types error:(NSError **)error;
- (NSDictionary *)addRoleWithClassName:(NSString *)className classPK:(long long)classPK name:(NSString *)name titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type subtype:(NSString *)subtype serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)getRoleWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error;
- (NSDictionary *)getRoleWithRoleId:(long long)roleId error:(NSError **)error;
- (NSArray *)getUserRelatedRolesWithUserId:(long long)userId groups:(NSArray *)groups error:(NSError **)error;
- (BOOL)hasUserRolesWithUserId:(long long)userId companyId:(long long)companyId names:(NSArray *)names inherited:(BOOL)inherited error:(NSError **)error;
- (NSDictionary *)updateRoleWithRoleId:(long long)roleId name:(NSString *)name titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap subtype:(NSString *)subtype serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId keywords:(NSString *)keywords types:(NSArray *)types params:(NSDictionary *)params error:(NSError **)error;
- (BOOL)hasUserRoleWithUserId:(long long)userId companyId:(long long)companyId name:(NSString *)name inherited:(BOOL)inherited error:(NSError **)error;
- (NSDictionary *)fetchRoleWithRoleId:(long long)roleId error:(NSError **)error;
- (NSArray *)getUserRolesWithUserId:(long long)userId error:(NSError **)error;
- (NSArray *)getUserGroupRolesWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error;
- (void)deleteRoleWithRoleId:(long long)roleId error:(NSError **)error;
- (void)addUserRolesWithUserId:(long long)userId roleIds:(NSArray *)roleIds error:(NSError **)error;
- (NSArray *)getUserGroupGroupRolesWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error;
- (void)unsetUserRolesWithUserId:(long long)userId roleIds:(NSArray *)roleIds error:(NSError **)error;
- (NSArray *)getGroupRolesWithGroupId:(long long)groupId error:(NSError **)error;

@end
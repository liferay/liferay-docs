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
@interface LRUserGroupRoleService_v62 : LRBaseService

- (void)addUserGroupRolesWithUserId:(long long)userId groupId:(long long)groupId roleIds:(NSArray *)roleIds error:(NSError **)error;
- (void)addUserGroupRolesWithUserIds:(NSArray *)userIds groupId:(long long)groupId roleId:(long long)roleId error:(NSError **)error;
- (void)deleteUserGroupRolesWithUserId:(long long)userId groupId:(long long)groupId roleIds:(NSArray *)roleIds error:(NSError **)error;
- (void)deleteUserGroupRolesWithUserIds:(NSArray *)userIds groupId:(long long)groupId roleId:(long long)roleId error:(NSError **)error;

@end
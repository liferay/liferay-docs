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

#import "LRUserGroupRoleService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRUserGroupRoleService_v7

- (void)addUserGroupRolesWithUserIds:(NSArray *)userIds groupId:(long long)groupId roleId:(long long)roleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userIds": [self checkNull: userIds],
		@"groupId": @(groupId),
		@"roleId": @(roleId)
	}];

	NSDictionary *_command = @{@"/usergrouprole/add-user-group-roles": _params};

	[self.session invoke:_command error:error];
}

- (void)addUserGroupRolesWithUserId:(long long)userId groupId:(long long)groupId roleIds:(NSArray *)roleIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"roleIds": [self checkNull: roleIds]
	}];

	NSDictionary *_command = @{@"/usergrouprole/add-user-group-roles": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteUserGroupRolesWithUserId:(long long)userId groupId:(long long)groupId roleIds:(NSArray *)roleIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"roleIds": [self checkNull: roleIds]
	}];

	NSDictionary *_command = @{@"/usergrouprole/delete-user-group-roles": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteUserGroupRolesWithUserIds:(NSArray *)userIds groupId:(long long)groupId roleId:(long long)roleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userIds": [self checkNull: userIds],
		@"groupId": @(groupId),
		@"roleId": @(roleId)
	}];

	NSDictionary *_command = @{@"/usergrouprole/delete-user-group-roles": _params};

	[self.session invoke:_command error:error];
}

@end
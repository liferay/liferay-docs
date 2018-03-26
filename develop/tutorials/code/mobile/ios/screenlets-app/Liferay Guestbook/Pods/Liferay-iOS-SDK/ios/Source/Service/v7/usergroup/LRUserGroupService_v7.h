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
@interface LRUserGroupService_v7 : LRBaseService

- (NSArray *)getUserGroupsWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSDictionary *)getUserGroupWithUserGroupId:(long long)userGroupId error:(NSError **)error;
- (NSDictionary *)getUserGroupWithName:(NSString *)name error:(NSError **)error;
- (NSDictionary *)addUserGroupWithName:(NSString *)name description:(NSString *)description error:(NSError **)error;
- (NSDictionary *)addUserGroupWithName:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateUserGroupWithUserGroupId:(long long)userGroupId name:(NSString *)name description:(NSString *)description error:(NSError **)error;
- (NSDictionary *)updateUserGroupWithUserGroupId:(long long)userGroupId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)fetchUserGroupWithUserGroupId:(long long)userGroupId error:(NSError **)error;
- (NSArray *)getUserUserGroupsWithUserId:(long long)userId error:(NSError **)error;
- (void)deleteUserGroupWithUserGroupId:(long long)userGroupId error:(NSError **)error;
- (void)addGroupUserGroupsWithGroupId:(long long)groupId userGroupIds:(NSArray *)userGroupIds error:(NSError **)error;
- (void)addTeamUserGroupsWithTeamId:(long long)teamId userGroupIds:(NSArray *)userGroupIds error:(NSError **)error;
- (void)unsetGroupUserGroupsWithGroupId:(long long)groupId userGroupIds:(NSArray *)userGroupIds error:(NSError **)error;
- (void)unsetTeamUserGroupsWithTeamId:(long long)teamId userGroupIds:(NSArray *)userGroupIds error:(NSError **)error;

@end
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
@interface LRGroupService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId name:(NSString *)name description:(NSString *)description params:(NSArray *)params start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId classNameIds:(NSArray *)classNameIds name:(NSString *)name description:(NSString *)description params:(NSDictionary *)params andOperator:(BOOL)andOperator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords params:(NSDictionary *)params start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupsWithCompanyId:(long long)companyId parentGroupId:(long long)parentGroupId site:(BOOL)site error:(NSError **)error;
- (NSDictionary *)getGroupWithGroupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)getGroupWithCompanyId:(long long)companyId groupKey:(NSString *)groupKey error:(NSError **)error;
- (NSDictionary *)addGroupWithParentGroupId:(long long)parentGroupId liveGroupId:(long long)liveGroupId name:(NSString *)name description:(NSString *)description type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL site:(BOOL)site active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addGroupWithParentGroupId:(long long)parentGroupId liveGroupId:(long long)liveGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL site:(BOOL)site inheritContent:(BOOL)inheritContent active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addGroupWithParentGroupId:(long long)parentGroupId liveGroupId:(long long)liveGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL site:(BOOL)site active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)getUserGroupWithCompanyId:(long long)companyId userId:(long long)userId error:(NSError **)error;
- (void)disableStagingWithGroupId:(long long)groupId error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId name:(NSString *)name description:(NSString *)description params:(NSArray *)params error:(NSError **)error;
- (NSDictionary *)updateGroupWithGroupId:(long long)groupId parentGroupId:(long long)parentGroupId name:(NSString *)name description:(NSString *)description type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL inheritContent:(BOOL)inheritContent active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateGroupWithGroupId:(long long)groupId parentGroupId:(long long)parentGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL inheritContent:(BOOL)inheritContent active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateGroupWithGroupId:(long long)groupId typeSettings:(NSString *)typeSettings error:(NSError **)error;
- (NSDictionary *)getCompanyGroupWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSArray *)getUserSitesGroups:(NSError **)error;
- (NSArray *)getUserSitesGroupsWithClassNames:(NSArray *)classNames max:(int)max error:(NSError **)error;
- (NSArray *)getUserSitesGroupsWithUserId:(long long)userId classNames:(NSArray *)classNames max:(int)max error:(NSError **)error;
- (BOOL)hasUserGroupWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error;
- (void)addRoleGroupsWithRoleId:(long long)roleId groupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)getOrganizationsGroupsWithOrganizations:(NSArray *)organizations error:(NSError **)error;
- (NSArray *)getUserGroupsGroupsWithUserGroups:(NSArray *)userGroups error:(NSError **)error;
- (NSArray *)getUserOrganizationsGroupsWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (void)setRoleGroupsWithRoleId:(long long)roleId groupIds:(NSArray *)groupIds error:(NSError **)error;
- (void)unsetRoleGroupsWithRoleId:(long long)roleId groupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSDictionary *)updateFriendlyUrlWithGroupId:(long long)groupId friendlyURL:(NSString *)friendlyURL error:(NSError **)error;
- (NSArray *)getManageableSiteGroupsWithPortlets:(NSArray *)portlets max:(int)max error:(NSError **)error;
- (void)checkRemoteStagingGroupWithGroupId:(long long)groupId error:(NSError **)error;
- (NSString *)getGroupDisplayUrlWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout secureConnection:(BOOL)secureConnection error:(NSError **)error;
- (NSNumber *)getUserSitesGroupsCount:(NSError **)error;
- (void)updateStagedPortletsWithGroupId:(long long)groupId stagedPortletIds:(NSDictionary *)stagedPortletIds error:(NSError **)error;
- (void)deleteGroupWithGroupId:(long long)groupId error:(NSError **)error;
- (void)enableStagingWithGroupId:(long long)groupId error:(NSError **)error;

@end
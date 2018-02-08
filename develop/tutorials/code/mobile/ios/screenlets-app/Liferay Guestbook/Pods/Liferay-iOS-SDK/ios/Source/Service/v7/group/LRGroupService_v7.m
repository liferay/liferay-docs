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

#import "LRGroupService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRGroupService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId name:(NSString *)name description:(NSString *)description params:(NSArray *)params start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"params": [self checkNull: params],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/group/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId classNameIds:(NSArray *)classNameIds name:(NSString *)name description:(NSString *)description params:(NSDictionary *)params andOperator:(BOOL)andOperator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"classNameIds": [self checkNull: classNameIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"params": [self checkNull: params],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group>" wrapper:obc];

	NSDictionary *_command = @{@"/group/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords params:(NSDictionary *)params start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"classNameIds": [self checkNull: classNameIds],
		@"keywords": [self checkNull: keywords],
		@"params": [self checkNull: params],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Group>" wrapper:obc];

	NSDictionary *_command = @{@"/group/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupsWithCompanyId:(long long)companyId parentGroupId:(long long)parentGroupId site:(BOOL)site error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"parentGroupId": @(parentGroupId),
		@"site": @(site)
	}];

	NSDictionary *_command = @{@"/group/get-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getGroupWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/group/get-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getGroupWithCompanyId:(long long)companyId groupKey:(NSString *)groupKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupKey": [self checkNull: groupKey]
	}];

	NSDictionary *_command = @{@"/group/get-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addGroupWithParentGroupId:(long long)parentGroupId liveGroupId:(long long)liveGroupId name:(NSString *)name description:(NSString *)description type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL site:(BOOL)site active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentGroupId": @(parentGroupId),
		@"liveGroupId": @(liveGroupId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": @(type),
		@"manualMembership": @(manualMembership),
		@"membershipRestriction": @(membershipRestriction),
		@"friendlyURL": [self checkNull: friendlyURL],
		@"site": @(site),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/group/add-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addGroupWithParentGroupId:(long long)parentGroupId liveGroupId:(long long)liveGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL site:(BOOL)site inheritContent:(BOOL)inheritContent active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentGroupId": @(parentGroupId),
		@"liveGroupId": @(liveGroupId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": @(type),
		@"manualMembership": @(manualMembership),
		@"membershipRestriction": @(membershipRestriction),
		@"friendlyURL": [self checkNull: friendlyURL],
		@"site": @(site),
		@"inheritContent": @(inheritContent),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/group/add-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addGroupWithParentGroupId:(long long)parentGroupId liveGroupId:(long long)liveGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL site:(BOOL)site active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentGroupId": @(parentGroupId),
		@"liveGroupId": @(liveGroupId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": @(type),
		@"manualMembership": @(manualMembership),
		@"membershipRestriction": @(membershipRestriction),
		@"friendlyURL": [self checkNull: friendlyURL],
		@"site": @(site),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/group/add-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getUserGroupWithCompanyId:(long long)companyId userId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/group/get-user-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)disableStagingWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/group/disable-staging": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId name:(NSString *)name description:(NSString *)description params:(NSArray *)params error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"params": [self checkNull: params]
	}];

	NSDictionary *_command = @{@"/group/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateGroupWithGroupId:(long long)groupId parentGroupId:(long long)parentGroupId name:(NSString *)name description:(NSString *)description type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL inheritContent:(BOOL)inheritContent active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentGroupId": @(parentGroupId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": @(type),
		@"manualMembership": @(manualMembership),
		@"membershipRestriction": @(membershipRestriction),
		@"friendlyURL": [self checkNull: friendlyURL],
		@"inheritContent": @(inheritContent),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/group/update-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateGroupWithGroupId:(long long)groupId parentGroupId:(long long)parentGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type manualMembership:(BOOL)manualMembership membershipRestriction:(int)membershipRestriction friendlyURL:(NSString *)friendlyURL inheritContent:(BOOL)inheritContent active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentGroupId": @(parentGroupId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": @(type),
		@"manualMembership": @(manualMembership),
		@"membershipRestriction": @(membershipRestriction),
		@"friendlyURL": [self checkNull: friendlyURL],
		@"inheritContent": @(inheritContent),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/group/update-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateGroupWithGroupId:(long long)groupId typeSettings:(NSString *)typeSettings error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"typeSettings": [self checkNull: typeSettings]
	}];

	NSDictionary *_command = @{@"/group/update-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCompanyGroupWithCompanyId:(long long)companyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId)
	}];

	NSDictionary *_command = @{@"/group/get-company-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserSitesGroups:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/group/get-user-sites-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserSitesGroupsWithClassNames:(NSArray *)classNames max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNames": [self checkNull: classNames],
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/group/get-user-sites-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserSitesGroupsWithUserId:(long long)userId classNames:(NSArray *)classNames max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"classNames": [self checkNull: classNames],
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/group/get-user-sites-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (BOOL)hasUserGroupWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/group/has-user-group": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (void)addRoleGroupsWithRoleId:(long long)roleId groupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/group/add-role-groups": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getOrganizationsGroupsWithOrganizations:(NSArray *)organizations error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizations": [self checkNull: organizations]
	}];

	NSDictionary *_command = @{@"/group/get-organizations-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserGroupsGroupsWithUserGroups:(NSArray *)userGroups error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userGroups": [self checkNull: userGroups]
	}];

	NSDictionary *_command = @{@"/group/get-user-groups-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserOrganizationsGroupsWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/group/get-user-organizations-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)setRoleGroupsWithRoleId:(long long)roleId groupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/group/set-role-groups": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetRoleGroupsWithRoleId:(long long)roleId groupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/group/unset-role-groups": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFriendlyUrlWithGroupId:(long long)groupId friendlyURL:(NSString *)friendlyURL error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"friendlyURL": [self checkNull: friendlyURL]
	}];

	NSDictionary *_command = @{@"/group/update-friendly-url": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getManageableSiteGroupsWithPortlets:(NSArray *)portlets max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"portlets": [self checkNull: portlets],
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/group/get-manageable-site-groups": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)checkRemoteStagingGroupWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/group/check-remote-staging-group": _params};

	[self.session invoke:_command error:error];
}

- (NSString *)getGroupDisplayUrlWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout secureConnection:(BOOL)secureConnection error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"secureConnection": @(secureConnection)
	}];

	NSDictionary *_command = @{@"/group/get-group-display-url": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSNumber *)getUserSitesGroupsCount:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/group/get-user-sites-groups-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)updateStagedPortletsWithGroupId:(long long)groupId stagedPortletIds:(NSDictionary *)stagedPortletIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"stagedPortletIds": [self checkNull: stagedPortletIds]
	}];

	NSDictionary *_command = @{@"/group/update-staged-portlets": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteGroupWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/group/delete-group": _params};

	[self.session invoke:_command error:error];
}

- (void)enableStagingWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/group/enable-staging": _params};

	[self.session invoke:_command error:error];
}

@end
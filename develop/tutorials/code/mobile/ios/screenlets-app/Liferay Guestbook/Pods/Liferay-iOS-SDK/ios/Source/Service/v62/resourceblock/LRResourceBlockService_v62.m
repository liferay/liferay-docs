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

#import "LRResourceBlockService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRResourceBlockService_v62

- (void)addCompanyScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/add-company-scope-permission": _params};

	[self.session invoke:_command error:error];
}

- (void)addGroupScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/add-group-scope-permission": _params};

	[self.session invoke:_command error:error];
}

- (void)addIndividualScopePermissionWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"primKey": @(primKey),
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/add-individual-scope-permission": _params};

	[self.session invoke:_command error:error];
}

- (void)removeAllGroupScopePermissionsWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/remove-all-group-scope-permissions": _params};

	[self.session invoke:_command error:error];
}

- (void)removeCompanyScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/remove-company-scope-permission": _params};

	[self.session invoke:_command error:error];
}

- (void)removeGroupScopePermissionWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/remove-group-scope-permission": _params};

	[self.session invoke:_command error:error];
}

- (void)removeIndividualScopePermissionWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleId:(long long)roleId actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"primKey": @(primKey),
		@"roleId": @(roleId),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/resourceblock/remove-individual-scope-permission": _params};

	[self.session invoke:_command error:error];
}

- (void)setCompanyScopePermissionsWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId name:(NSString *)name roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionIds": [self checkNull: actionIds]
	}];

	NSDictionary *_command = @{@"/resourceblock/set-company-scope-permissions": _params};

	[self.session invoke:_command error:error];
}

- (void)setGroupScopePermissionsWithScopeGroupId:(long long)scopeGroupId companyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"scopeGroupId": @(scopeGroupId),
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"roleId": @(roleId),
		@"actionIds": [self checkNull: actionIds]
	}];

	NSDictionary *_command = @{@"/resourceblock/set-group-scope-permissions": _params};

	[self.session invoke:_command error:error];
}

- (void)setIndividualScopePermissionsWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleIdsToActionIds:(NSDictionary *)roleIdsToActionIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"primKey": @(primKey),
		@"roleIdsToActionIds": [self checkNull: roleIdsToActionIds]
	}];

	NSDictionary *_command = @{@"/resourceblock/set-individual-scope-permissions": _params};

	[self.session invoke:_command error:error];
}

- (void)setIndividualScopePermissionsWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name primKey:(long long)primKey roleId:(long long)roleId actionIds:(NSArray *)actionIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"primKey": @(primKey),
		@"roleId": @(roleId),
		@"actionIds": [self checkNull: actionIds]
	}];

	NSDictionary *_command = @{@"/resourceblock/set-individual-scope-permissions": _params};

	[self.session invoke:_command error:error];
}

@end
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

#import "LRRoleService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRRoleService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId keywords:(NSString *)keywords types:(NSArray *)types params:(NSDictionary *)params start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"keywords": [self checkNull: keywords],
		@"types": [self checkNull: types],
		@"params": [self checkNull: params],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.Role>" wrapper:obc];

	NSDictionary *_command = @{@"/role/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getRolesWithType:(int)type subtype:(NSString *)subtype error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"type": @(type),
		@"subtype": [self checkNull: subtype]
	}];

	NSDictionary *_command = @{@"/role/get-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getRolesWithCompanyId:(long long)companyId types:(NSArray *)types error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"types": [self checkNull: types]
	}];

	NSDictionary *_command = @{@"/role/get-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addRoleWithClassName:(NSString *)className classPK:(long long)classPK name:(NSString *)name titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap type:(int)type subtype:(NSString *)subtype serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"name": [self checkNull: name],
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": @(type),
		@"subtype": [self checkNull: subtype],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/role/add-role": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRoleWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/role/get-role": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRoleWithRoleId:(long long)roleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId)
	}];

	NSDictionary *_command = @{@"/role/get-role": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserRelatedRolesWithUserId:(long long)userId groups:(NSArray *)groups error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groups": [self checkNull: groups]
	}];

	NSDictionary *_command = @{@"/role/get-user-related-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (BOOL)hasUserRolesWithUserId:(long long)userId companyId:(long long)companyId names:(NSArray *)names inherited:(BOOL)inherited error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"companyId": @(companyId),
		@"names": [self checkNull: names],
		@"inherited": @(inherited)
	}];

	NSDictionary *_command = @{@"/role/has-user-roles": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (NSDictionary *)updateRoleWithRoleId:(long long)roleId name:(NSString *)name titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap subtype:(NSString *)subtype serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"name": [self checkNull: name],
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"subtype": [self checkNull: subtype],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/role/update-role": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId keywords:(NSString *)keywords types:(NSArray *)types params:(NSDictionary *)params error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"keywords": [self checkNull: keywords],
		@"types": [self checkNull: types],
		@"params": [self checkNull: params]
	}];

	NSDictionary *_command = @{@"/role/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (BOOL)hasUserRoleWithUserId:(long long)userId companyId:(long long)companyId name:(NSString *)name inherited:(BOOL)inherited error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"inherited": @(inherited)
	}];

	NSDictionary *_command = @{@"/role/has-user-role": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (NSDictionary *)fetchRoleWithRoleId:(long long)roleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId)
	}];

	NSDictionary *_command = @{@"/role/fetch-role": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserRolesWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/role/get-user-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserGroupRolesWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/role/get-user-group-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)deleteRoleWithRoleId:(long long)roleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId)
	}];

	NSDictionary *_command = @{@"/role/delete-role": _params};

	[self.session invoke:_command error:error];
}

- (void)addUserRolesWithUserId:(long long)userId roleIds:(NSArray *)roleIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"roleIds": [self checkNull: roleIds]
	}];

	NSDictionary *_command = @{@"/role/add-user-roles": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getUserGroupGroupRolesWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/role/get-user-group-group-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)unsetUserRolesWithUserId:(long long)userId roleIds:(NSArray *)roleIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"roleIds": [self checkNull: roleIds]
	}];

	NSDictionary *_command = @{@"/role/unset-user-roles": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getGroupRolesWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/role/get-group-roles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

@end
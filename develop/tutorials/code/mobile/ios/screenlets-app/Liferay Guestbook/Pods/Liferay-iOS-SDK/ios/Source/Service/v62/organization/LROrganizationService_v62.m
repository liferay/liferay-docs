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

#import "LROrganizationService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LROrganizationService_v62

- (void)addGroupOrganizationsWithGroupId:(long long)groupId organizationIds:(NSArray *)organizationIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"organizationIds": [self checkNull: organizationIds]
	}];

	NSDictionary *_command = @{@"/organization/add-group-organizations": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"recursable": @(recursable),
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/add-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"orgLabors": [self checkNull: orgLabors],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/add-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"recursable": @(recursable),
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"orgLabors": [self checkNull: orgLabors],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/add-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/add-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)addPasswordPolicyOrganizationsWithPasswordPolicyId:(long long)passwordPolicyId organizationIds:(NSArray *)organizationIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId),
		@"organizationIds": [self checkNull: organizationIds]
	}];

	NSDictionary *_command = @{@"/organization/add-password-policy-organizations": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteLogoWithOrganizationId:(long long)organizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId)
	}];

	NSDictionary *_command = @{@"/organization/delete-logo": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteOrganizationWithOrganizationId:(long long)organizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId)
	}];

	NSDictionary *_command = @{@"/organization/delete-organization": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getManageableOrganizationsWithActionId:(NSString *)actionId max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"actionId": [self checkNull: actionId],
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/organization/get-manageable-organizations": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getOrganizationWithOrganizationId:(long long)organizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId)
	}];

	NSDictionary *_command = @{@"/organization/get-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getOrganizationIdWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/organization/get-organization-id": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getOrganizationsWithCompanyId:(long long)companyId parentOrganizationId:(long long)parentOrganizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"parentOrganizationId": @(parentOrganizationId)
	}];

	NSDictionary *_command = @{@"/organization/get-organizations": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getOrganizationsWithCompanyId:(long long)companyId parentOrganizationId:(long long)parentOrganizationId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"parentOrganizationId": @(parentOrganizationId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/organization/get-organizations": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getOrganizationsCountWithCompanyId:(long long)companyId parentOrganizationId:(long long)parentOrganizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"parentOrganizationId": @(parentOrganizationId)
	}];

	NSDictionary *_command = @{@"/organization/get-organizations-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserOrganizationsWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/organization/get-user-organizations": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)setGroupOrganizationsWithGroupId:(long long)groupId organizationIds:(NSArray *)organizationIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"organizationIds": [self checkNull: organizationIds]
	}];

	NSDictionary *_command = @{@"/organization/set-group-organizations": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetGroupOrganizationsWithGroupId:(long long)groupId organizationIds:(NSArray *)organizationIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"organizationIds": [self checkNull: organizationIds]
	}];

	NSDictionary *_command = @{@"/organization/unset-group-organizations": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetPasswordPolicyOrganizationsWithPasswordPolicyId:(long long)passwordPolicyId organizationIds:(NSArray *)organizationIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId),
		@"organizationIds": [self checkNull: organizationIds]
	}];

	NSDictionary *_command = @{@"/organization/unset-password-policy-organizations": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/update-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"recursable": @(recursable),
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/update-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"orgLabors": [self checkNull: orgLabors],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/update-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"parentOrganizationId": @(parentOrganizationId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"recursable": @(recursable),
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"statusId": @(statusId),
		@"comments": [self checkNull: comments],
		@"site": @(site),
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"orgLabors": [self checkNull: orgLabors],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/organization/update-organization": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
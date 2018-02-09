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

#import "LRUserService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRUserService_v62

- (void)addGroupUsersWithGroupId:(long long)groupId userIds:(NSArray *)userIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userIds": [self checkNull: userIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/add-group-users": _params};

	[self.session invoke:_command error:error];
}

- (void)addOrganizationUsersWithOrganizationId:(long long)organizationId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/add-organization-users": _params};

	[self.session invoke:_command error:error];
}

- (void)addPasswordPolicyUsersWithPasswordPolicyId:(long long)passwordPolicyId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/add-password-policy-users": _params};

	[self.session invoke:_command error:error];
}

- (void)addRoleUsersWithRoleId:(long long)roleId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/add-role-users": _params};

	[self.session invoke:_command error:error];
}

- (void)addTeamUsersWithTeamId:(long long)teamId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"teamId": @(teamId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/add-team-users": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)addUserWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"autoPassword": @(autoPassword),
		@"password1": [self checkNull: password1],
		@"password2": [self checkNull: password2],
		@"autoScreenName": @(autoScreenName),
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"locale": [self checkNull: locale],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"jobTitle": [self checkNull: jobTitle],
		@"groupIds": [self checkNull: groupIds],
		@"organizationIds": [self checkNull: organizationIds],
		@"roleIds": [self checkNull: roleIds],
		@"userGroupIds": [self checkNull: userGroupIds],
		@"sendEmail": @(sendEmail),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/add-user": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addUserWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"autoPassword": @(autoPassword),
		@"password1": [self checkNull: password1],
		@"password2": [self checkNull: password2],
		@"autoScreenName": @(autoScreenName),
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"locale": [self checkNull: locale],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"jobTitle": [self checkNull: jobTitle],
		@"groupIds": [self checkNull: groupIds],
		@"organizationIds": [self checkNull: organizationIds],
		@"roleIds": [self checkNull: roleIds],
		@"userGroupIds": [self checkNull: userGroupIds],
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
		@"announcementsDelivers": [self checkNull: announcementsDelivers],
		@"sendEmail": @(sendEmail),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/add-user": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)addUserGroupUsersWithUserGroupId:(long long)userGroupId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userGroupId": @(userGroupId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/add-user-group-users": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)addUserWithWorkflowWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"autoPassword": @(autoPassword),
		@"password1": [self checkNull: password1],
		@"password2": [self checkNull: password2],
		@"autoScreenName": @(autoScreenName),
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"locale": [self checkNull: locale],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"jobTitle": [self checkNull: jobTitle],
		@"groupIds": [self checkNull: groupIds],
		@"organizationIds": [self checkNull: organizationIds],
		@"roleIds": [self checkNull: roleIds],
		@"userGroupIds": [self checkNull: userGroupIds],
		@"sendEmail": @(sendEmail),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/add-user-with-workflow": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addUserWithWorkflowWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"autoPassword": @(autoPassword),
		@"password1": [self checkNull: password1],
		@"password2": [self checkNull: password2],
		@"autoScreenName": @(autoScreenName),
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"locale": [self checkNull: locale],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"jobTitle": [self checkNull: jobTitle],
		@"groupIds": [self checkNull: groupIds],
		@"organizationIds": [self checkNull: organizationIds],
		@"roleIds": [self checkNull: roleIds],
		@"userGroupIds": [self checkNull: userGroupIds],
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
		@"announcementsDelivers": [self checkNull: announcementsDelivers],
		@"sendEmail": @(sendEmail),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/add-user-with-workflow": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deletePortraitWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/user/delete-portrait": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteRoleUserWithRoleId:(long long)roleId userId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/user/delete-role-user": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteUserWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/user/delete-user": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getCompanyUsersWithCompanyId:(long long)companyId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/user/get-company-users": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCompanyUsersCountWithCompanyId:(long long)companyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId)
	}];

	NSDictionary *_command = @{@"/user/get-company-users-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupUserIdsWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/user/get-group-user-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupUsersWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/user/get-group-users": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getOrganizationUserIdsWithOrganizationId:(long long)organizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId)
	}];

	NSDictionary *_command = @{@"/user/get-organization-user-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getOrganizationUsersWithOrganizationId:(long long)organizationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId)
	}];

	NSDictionary *_command = @{@"/user/get-organization-users": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getRoleUserIdsWithRoleId:(long long)roleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId)
	}];

	NSDictionary *_command = @{@"/user/get-role-user-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getUserByEmailAddressWithCompanyId:(long long)companyId emailAddress:(NSString *)emailAddress error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"emailAddress": [self checkNull: emailAddress]
	}];

	NSDictionary *_command = @{@"/user/get-user-by-email-address": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getUserByIdWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/user/get-user-by-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getUserByScreenNameWithCompanyId:(long long)companyId screenName:(NSString *)screenName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"screenName": [self checkNull: screenName]
	}];

	NSDictionary *_command = @{@"/user/get-user-by-screen-name": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserGroupUsersWithUserGroupId:(long long)userGroupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userGroupId": @(userGroupId)
	}];

	NSDictionary *_command = @{@"/user/get-user-group-users": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getUserIdByEmailAddressWithCompanyId:(long long)companyId emailAddress:(NSString *)emailAddress error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"emailAddress": [self checkNull: emailAddress]
	}];

	NSDictionary *_command = @{@"/user/get-user-id-by-email-address": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getUserIdByScreenNameWithCompanyId:(long long)companyId screenName:(NSString *)screenName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"screenName": [self checkNull: screenName]
	}];

	NSDictionary *_command = @{@"/user/get-user-id-by-screen-name": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (BOOL)hasGroupUserWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/user/has-group-user": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)hasRoleUserWithRoleId:(long long)roleId userId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/user/has-role-user": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)hasRoleUserWithCompanyId:(long long)companyId name:(NSString *)name userId:(long long)userId inherited:(BOOL)inherited error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name],
		@"userId": @(userId),
		@"inherited": @(inherited)
	}];

	NSDictionary *_command = @{@"/user/has-role-user": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (void)setRoleUsersWithRoleId:(long long)roleId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/set-role-users": _params};

	[self.session invoke:_command error:error];
}

- (void)setUserGroupUsersWithUserGroupId:(long long)userGroupId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userGroupId": @(userGroupId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/set-user-group-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetGroupTeamsUsersWithGroupId:(long long)groupId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/unset-group-teams-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetGroupUsersWithGroupId:(long long)groupId userIds:(NSArray *)userIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userIds": [self checkNull: userIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/unset-group-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetOrganizationUsersWithOrganizationId:(long long)organizationId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/unset-organization-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetPasswordPolicyUsersWithPasswordPolicyId:(long long)passwordPolicyId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/unset-password-policy-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetRoleUsersWithRoleId:(long long)roleId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"roleId": @(roleId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/unset-role-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetTeamUsersWithTeamId:(long long)teamId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"teamId": @(teamId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/unset-team-users": _params};

	[self.session invoke:_command error:error];
}

- (void)unsetUserGroupUsersWithUserGroupId:(long long)userGroupId userIds:(NSArray *)userIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userGroupId": @(userGroupId),
		@"userIds": [self checkNull: userIds]
	}];

	NSDictionary *_command = @{@"/user/unset-user-group-users": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateAgreedToTermsOfUseWithUserId:(long long)userId agreedToTermsOfUse:(BOOL)agreedToTermsOfUse error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"agreedToTermsOfUse": @(agreedToTermsOfUse)
	}];

	NSDictionary *_command = @{@"/user/update-agreed-to-terms-of-use": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEmailAddressWithUserId:(long long)userId password:(NSString *)password emailAddress1:(NSString *)emailAddress1 emailAddress2:(NSString *)emailAddress2 serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"password": [self checkNull: password],
		@"emailAddress1": [self checkNull: emailAddress1],
		@"emailAddress2": [self checkNull: emailAddress2],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/update-email-address": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateIncompleteUserWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle updateUserInformation:(BOOL)updateUserInformation sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"autoPassword": @(autoPassword),
		@"password1": [self checkNull: password1],
		@"password2": [self checkNull: password2],
		@"autoScreenName": @(autoScreenName),
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"locale": [self checkNull: locale],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"jobTitle": [self checkNull: jobTitle],
		@"updateUserInformation": @(updateUserInformation),
		@"sendEmail": @(sendEmail),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/update-incomplete-user": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLockoutByIdWithUserId:(long long)userId lockout:(BOOL)lockout error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"lockout": @(lockout)
	}];

	NSDictionary *_command = @{@"/user/update-lockout-by-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOpenIdWithUserId:(long long)userId openId:(NSString *)openId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"openId": [self checkNull: openId]
	}];

	NSDictionary *_command = @{@"/user/update-open-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)updateOrganizationsWithUserId:(long long)userId organizationIds:(NSArray *)organizationIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"organizationIds": [self checkNull: organizationIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/update-organizations": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePasswordWithUserId:(long long)userId password1:(NSString *)password1 password2:(NSString *)password2 passwordReset:(BOOL)passwordReset error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"password1": [self checkNull: password1],
		@"password2": [self checkNull: password2],
		@"passwordReset": @(passwordReset)
	}];

	NSDictionary *_command = @{@"/user/update-password": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePortraitWithUserId:(long long)userId bytes:(NSData *)bytes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"bytes": [self toString:bytes]
	}];

	NSDictionary *_command = @{@"/user/update-portrait": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateReminderQueryWithUserId:(long long)userId question:(NSString *)question answer:(NSString *)answer error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"question": [self checkNull: question],
		@"answer": [self checkNull: answer]
	}];

	NSDictionary *_command = @{@"/user/update-reminder-query": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateScreenNameWithUserId:(long long)userId screenName:(NSString *)screenName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"screenName": [self checkNull: screenName]
	}];

	NSDictionary *_command = @{@"/user/update-screen-name": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStatusWithUserId:(long long)userId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/user/update-status": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateUserWithUserId:(long long)userId oldPassword:(NSString *)oldPassword newPassword1:(NSString *)newPassword1 newPassword2:(NSString *)newPassword2 passwordReset:(BOOL)passwordReset reminderQueryQuestion:(NSString *)reminderQueryQuestion reminderQueryAnswer:(NSString *)reminderQueryAnswer screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId greeting:(NSString *)greeting comments:(NSString *)comments firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear smsSn:(NSString *)smsSn aimSn:(NSString *)aimSn facebookSn:(NSString *)facebookSn icqSn:(NSString *)icqSn jabberSn:(NSString *)jabberSn msnSn:(NSString *)msnSn mySpaceSn:(NSString *)mySpaceSn skypeSn:(NSString *)skypeSn twitterSn:(NSString *)twitterSn ymSn:(NSString *)ymSn jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupRoles:(NSArray *)userGroupRoles userGroupIds:(NSArray *)userGroupIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"oldPassword": [self checkNull: oldPassword],
		@"newPassword1": [self checkNull: newPassword1],
		@"newPassword2": [self checkNull: newPassword2],
		@"passwordReset": @(passwordReset),
		@"reminderQueryQuestion": [self checkNull: reminderQueryQuestion],
		@"reminderQueryAnswer": [self checkNull: reminderQueryAnswer],
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"languageId": [self checkNull: languageId],
		@"timeZoneId": [self checkNull: timeZoneId],
		@"greeting": [self checkNull: greeting],
		@"comments": [self checkNull: comments],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"smsSn": [self checkNull: smsSn],
		@"aimSn": [self checkNull: aimSn],
		@"facebookSn": [self checkNull: facebookSn],
		@"icqSn": [self checkNull: icqSn],
		@"jabberSn": [self checkNull: jabberSn],
		@"msnSn": [self checkNull: msnSn],
		@"mySpaceSn": [self checkNull: mySpaceSn],
		@"skypeSn": [self checkNull: skypeSn],
		@"twitterSn": [self checkNull: twitterSn],
		@"ymSn": [self checkNull: ymSn],
		@"jobTitle": [self checkNull: jobTitle],
		@"groupIds": [self checkNull: groupIds],
		@"organizationIds": [self checkNull: organizationIds],
		@"roleIds": [self checkNull: roleIds],
		@"userGroupRoles": [self checkNull: userGroupRoles],
		@"userGroupIds": [self checkNull: userGroupIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/update-user": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateUserWithUserId:(long long)userId oldPassword:(NSString *)oldPassword newPassword1:(NSString *)newPassword1 newPassword2:(NSString *)newPassword2 passwordReset:(BOOL)passwordReset reminderQueryQuestion:(NSString *)reminderQueryQuestion reminderQueryAnswer:(NSString *)reminderQueryAnswer screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId greeting:(NSString *)greeting comments:(NSString *)comments firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(int)prefixId suffixId:(int)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear smsSn:(NSString *)smsSn aimSn:(NSString *)aimSn facebookSn:(NSString *)facebookSn icqSn:(NSString *)icqSn jabberSn:(NSString *)jabberSn msnSn:(NSString *)msnSn mySpaceSn:(NSString *)mySpaceSn skypeSn:(NSString *)skypeSn twitterSn:(NSString *)twitterSn ymSn:(NSString *)ymSn jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupRoles:(NSArray *)userGroupRoles userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"oldPassword": [self checkNull: oldPassword],
		@"newPassword1": [self checkNull: newPassword1],
		@"newPassword2": [self checkNull: newPassword2],
		@"passwordReset": @(passwordReset),
		@"reminderQueryQuestion": [self checkNull: reminderQueryQuestion],
		@"reminderQueryAnswer": [self checkNull: reminderQueryAnswer],
		@"screenName": [self checkNull: screenName],
		@"emailAddress": [self checkNull: emailAddress],
		@"facebookId": @(facebookId),
		@"openId": [self checkNull: openId],
		@"languageId": [self checkNull: languageId],
		@"timeZoneId": [self checkNull: timeZoneId],
		@"greeting": [self checkNull: greeting],
		@"comments": [self checkNull: comments],
		@"firstName": [self checkNull: firstName],
		@"middleName": [self checkNull: middleName],
		@"lastName": [self checkNull: lastName],
		@"prefixId": @(prefixId),
		@"suffixId": @(suffixId),
		@"male": @(male),
		@"birthdayMonth": @(birthdayMonth),
		@"birthdayDay": @(birthdayDay),
		@"birthdayYear": @(birthdayYear),
		@"smsSn": [self checkNull: smsSn],
		@"aimSn": [self checkNull: aimSn],
		@"facebookSn": [self checkNull: facebookSn],
		@"icqSn": [self checkNull: icqSn],
		@"jabberSn": [self checkNull: jabberSn],
		@"msnSn": [self checkNull: msnSn],
		@"mySpaceSn": [self checkNull: mySpaceSn],
		@"skypeSn": [self checkNull: skypeSn],
		@"twitterSn": [self checkNull: twitterSn],
		@"ymSn": [self checkNull: ymSn],
		@"jobTitle": [self checkNull: jobTitle],
		@"groupIds": [self checkNull: groupIds],
		@"organizationIds": [self checkNull: organizationIds],
		@"roleIds": [self checkNull: roleIds],
		@"userGroupRoles": [self checkNull: userGroupRoles],
		@"userGroupIds": [self checkNull: userGroupIds],
		@"addresses": [self checkNull: addresses],
		@"emailAddresses": [self checkNull: emailAddresses],
		@"phones": [self checkNull: phones],
		@"websites": [self checkNull: websites],
		@"announcementsDelivers": [self checkNull: announcementsDelivers],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/user/update-user": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
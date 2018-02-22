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
@interface LRUserService_v7 : LRBaseService

- (NSDictionary *)addUserWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addUserWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)getUserByIdWithUserId:(long long)userId error:(NSError **)error;
- (NSArray *)getGroupUsersWithGroupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)updatePortraitWithUserId:(long long)userId bytes:(NSData *)bytes error:(NSError **)error;
- (NSDictionary *)getUserByScreenNameWithCompanyId:(long long)companyId screenName:(NSString *)screenName error:(NSError **)error;
- (NSDictionary *)updateUserWithUserId:(long long)userId oldPassword:(NSString *)oldPassword newPassword1:(NSString *)newPassword1 newPassword2:(NSString *)newPassword2 passwordReset:(BOOL)passwordReset reminderQueryQuestion:(NSString *)reminderQueryQuestion reminderQueryAnswer:(NSString *)reminderQueryAnswer screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId greeting:(NSString *)greeting comments:(NSString *)comments firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear smsSn:(NSString *)smsSn facebookSn:(NSString *)facebookSn jabberSn:(NSString *)jabberSn skypeSn:(NSString *)skypeSn twitterSn:(NSString *)twitterSn jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupRoles:(NSArray *)userGroupRoles userGroupIds:(NSArray *)userGroupIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateUserWithUserId:(long long)userId oldPassword:(NSString *)oldPassword newPassword1:(NSString *)newPassword1 newPassword2:(NSString *)newPassword2 passwordReset:(BOOL)passwordReset reminderQueryQuestion:(NSString *)reminderQueryQuestion reminderQueryAnswer:(NSString *)reminderQueryAnswer screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId greeting:(NSString *)greeting comments:(NSString *)comments firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear smsSn:(NSString *)smsSn facebookSn:(NSString *)facebookSn jabberSn:(NSString *)jabberSn skypeSn:(NSString *)skypeSn twitterSn:(NSString *)twitterSn jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupRoles:(NSArray *)userGroupRoles userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateUserWithUserId:(long long)userId oldPassword:(NSString *)oldPassword newPassword1:(NSString *)newPassword1 newPassword2:(NSString *)newPassword2 passwordReset:(BOOL)passwordReset reminderQueryQuestion:(NSString *)reminderQueryQuestion reminderQueryAnswer:(NSString *)reminderQueryAnswer screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId portrait:(BOOL)portrait portraitBytes:(NSData *)portraitBytes languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId greeting:(NSString *)greeting comments:(NSString *)comments firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear smsSn:(NSString *)smsSn facebookSn:(NSString *)facebookSn jabberSn:(NSString *)jabberSn skypeSn:(NSString *)skypeSn twitterSn:(NSString *)twitterSn jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupRoles:(NSArray *)userGroupRoles userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateEmailAddressWithUserId:(long long)userId password:(NSString *)password emailAddress1:(NSString *)emailAddress1 emailAddress2:(NSString *)emailAddress2 serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (BOOL)hasGroupUserWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error;
- (NSArray *)getOrganizationUsersWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (NSDictionary *)updateStatusWithUserId:(long long)userId status:(int)status serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateStatusWithUserId:(long long)userId status:(int)status error:(NSError **)error;
- (NSArray *)getRoleUserIdsWithRoleId:(long long)roleId error:(NSError **)error;
- (NSDictionary *)updateLockoutByIdWithUserId:(long long)userId lockout:(BOOL)lockout error:(NSError **)error;
- (void)unsetGroupUsersWithGroupId:(long long)groupId userIds:(NSArray *)userIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)addGroupUsersWithGroupId:(long long)groupId userIds:(NSArray *)userIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteUserWithUserId:(long long)userId error:(NSError **)error;
- (NSDictionary *)updatePasswordWithUserId:(long long)userId password1:(NSString *)password1 password2:(NSString *)password2 passwordReset:(BOOL)passwordReset error:(NSError **)error;
- (void)addOrganizationUsersWithOrganizationId:(long long)organizationId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)addPasswordPolicyUsersWithPasswordPolicyId:(long long)passwordPolicyId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)addRoleUsersWithRoleId:(long long)roleId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)addTeamUsersWithTeamId:(long long)teamId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)addUserGroupUsersWithUserGroupId:(long long)userGroupId userIds:(NSArray *)userIds error:(NSError **)error;
- (NSDictionary *)addUserWithWorkflowWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addUserWithWorkflowWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle groupIds:(NSArray *)groupIds organizationIds:(NSArray *)organizationIds roleIds:(NSArray *)roleIds userGroupIds:(NSArray *)userGroupIds addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses phones:(NSArray *)phones websites:(NSArray *)websites announcementsDelivers:(NSArray *)announcementsDelivers sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deletePortraitWithUserId:(long long)userId error:(NSError **)error;
- (void)deleteRoleUserWithRoleId:(long long)roleId userId:(long long)userId error:(NSError **)error;
- (NSArray *)getCompanyUsersWithCompanyId:(long long)companyId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getCompanyUsersCountWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSArray *)getGroupUserIdsWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getOrganizationUserIdsWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (NSDictionary *)getUserByEmailAddressWithCompanyId:(long long)companyId emailAddress:(NSString *)emailAddress error:(NSError **)error;
- (NSNumber *)getUserIdByEmailAddressWithCompanyId:(long long)companyId emailAddress:(NSString *)emailAddress error:(NSError **)error;
- (NSNumber *)getUserIdByScreenNameWithCompanyId:(long long)companyId screenName:(NSString *)screenName error:(NSError **)error;
- (BOOL)hasRoleUserWithRoleId:(long long)roleId userId:(long long)userId error:(NSError **)error;
- (BOOL)hasRoleUserWithCompanyId:(long long)companyId name:(NSString *)name userId:(long long)userId inherited:(BOOL)inherited error:(NSError **)error;
- (BOOL)sendPasswordByEmailAddressWithCompanyId:(long long)companyId emailAddress:(NSString *)emailAddress error:(NSError **)error;
- (BOOL)sendPasswordByScreenNameWithCompanyId:(long long)companyId screenName:(NSString *)screenName error:(NSError **)error;
- (BOOL)sendPasswordByUserIdWithUserId:(long long)userId error:(NSError **)error;
- (void)setRoleUsersWithRoleId:(long long)roleId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)setUserGroupUsersWithUserGroupId:(long long)userGroupId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)unsetGroupTeamsUsersWithGroupId:(long long)groupId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)unsetOrganizationUsersWithOrganizationId:(long long)organizationId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)unsetPasswordPolicyUsersWithPasswordPolicyId:(long long)passwordPolicyId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)unsetRoleUsersWithRoleId:(long long)roleId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)unsetTeamUsersWithTeamId:(long long)teamId userIds:(NSArray *)userIds error:(NSError **)error;
- (void)unsetUserGroupUsersWithUserGroupId:(long long)userGroupId userIds:(NSArray *)userIds error:(NSError **)error;
- (NSDictionary *)updateAgreedToTermsOfUseWithUserId:(long long)userId agreedToTermsOfUse:(BOOL)agreedToTermsOfUse error:(NSError **)error;
- (NSDictionary *)updateIncompleteUserWithCompanyId:(long long)companyId autoPassword:(BOOL)autoPassword password1:(NSString *)password1 password2:(NSString *)password2 autoScreenName:(BOOL)autoScreenName screenName:(NSString *)screenName emailAddress:(NSString *)emailAddress facebookId:(long long)facebookId openId:(NSString *)openId locale:(NSString *)locale firstName:(NSString *)firstName middleName:(NSString *)middleName lastName:(NSString *)lastName prefixId:(long long)prefixId suffixId:(long long)suffixId male:(BOOL)male birthdayMonth:(int)birthdayMonth birthdayDay:(int)birthdayDay birthdayYear:(int)birthdayYear jobTitle:(NSString *)jobTitle updateUserInformation:(BOOL)updateUserInformation sendEmail:(BOOL)sendEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateOpenIdWithUserId:(long long)userId openId:(NSString *)openId error:(NSError **)error;
- (void)updateOrganizationsWithUserId:(long long)userId organizationIds:(NSArray *)organizationIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateReminderQueryWithUserId:(long long)userId question:(NSString *)question answer:(NSString *)answer error:(NSError **)error;
- (NSDictionary *)updateScreenNameWithUserId:(long long)userId screenName:(NSString *)screenName error:(NSError **)error;
- (NSDictionary *)getCurrentUser:(NSError **)error;
- (NSArray *)getUserGroupUsersWithUserGroupId:(long long)userGroupId error:(NSError **)error;

@end
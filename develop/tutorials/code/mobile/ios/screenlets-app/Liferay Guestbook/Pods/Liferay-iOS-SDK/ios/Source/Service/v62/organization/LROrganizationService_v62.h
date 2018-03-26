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
@interface LROrganizationService_v62 : LRBaseService

- (void)addGroupOrganizationsWithGroupId:(long long)groupId organizationIds:(NSArray *)organizationIds error:(NSError **)error;
- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addOrganizationWithParentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)addPasswordPolicyOrganizationsWithPasswordPolicyId:(long long)passwordPolicyId organizationIds:(NSArray *)organizationIds error:(NSError **)error;
- (void)deleteLogoWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (void)deleteOrganizationWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (NSArray *)getManageableOrganizationsWithActionId:(NSString *)actionId max:(int)max error:(NSError **)error;
- (NSDictionary *)getOrganizationWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (NSNumber *)getOrganizationIdWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error;
- (NSArray *)getOrganizationsWithCompanyId:(long long)companyId parentOrganizationId:(long long)parentOrganizationId error:(NSError **)error;
- (NSArray *)getOrganizationsWithCompanyId:(long long)companyId parentOrganizationId:(long long)parentOrganizationId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getOrganizationsCountWithCompanyId:(long long)companyId parentOrganizationId:(long long)parentOrganizationId error:(NSError **)error;
- (NSArray *)getUserOrganizationsWithUserId:(long long)userId error:(NSError **)error;
- (void)setGroupOrganizationsWithGroupId:(long long)groupId organizationIds:(NSArray *)organizationIds error:(NSError **)error;
- (void)unsetGroupOrganizationsWithGroupId:(long long)groupId organizationIds:(NSArray *)organizationIds error:(NSError **)error;
- (void)unsetPasswordPolicyOrganizationsWithPasswordPolicyId:(long long)passwordPolicyId organizationIds:(NSArray *)organizationIds error:(NSError **)error;
- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateOrganizationWithOrganizationId:(long long)organizationId parentOrganizationId:(long long)parentOrganizationId name:(NSString *)name type:(NSString *)type recursable:(BOOL)recursable regionId:(long long)regionId countryId:(long long)countryId statusId:(int)statusId comments:(NSString *)comments site:(BOOL)site addresses:(NSArray *)addresses emailAddresses:(NSArray *)emailAddresses orgLabors:(NSArray *)orgLabors phones:(NSArray *)phones websites:(NSArray *)websites serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
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
@interface LRSapentryService_v7 : LRBaseService

- (NSDictionary *)updateSapEntryWithSapEntryId:(long long)sapEntryId allowedServiceSignatures:(NSString *)allowedServiceSignatures defaultSAPEntry:(BOOL)defaultSAPEntry enabled:(BOOL)enabled name:(NSString *)name titleMap:(NSDictionary *)titleMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSNumber *)getCompanySapEntriesCountWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSDictionary *)deleteSapEntryWithSapEntryId:(long long)sapEntryId error:(NSError **)error;
- (NSDictionary *)deleteSapEntryWithSapEntry:(LRJSONObjectWrapper *)sapEntry error:(NSError **)error;
- (NSDictionary *)fetchSapEntryWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error;
- (NSArray *)getCompanySapEntriesWithCompanyId:(long long)companyId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCompanySapEntriesWithCompanyId:(long long)companyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSDictionary *)getSapEntryWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error;
- (NSDictionary *)getSapEntryWithSapEntryId:(long long)sapEntryId error:(NSError **)error;
- (NSDictionary *)addSapEntryWithAllowedServiceSignatures:(NSString *)allowedServiceSignatures defaultSAPEntry:(BOOL)defaultSAPEntry enabled:(BOOL)enabled name:(NSString *)name titleMap:(NSDictionary *)titleMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
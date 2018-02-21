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
@interface LRDdmdataproviderinstanceService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSDictionary *)addDataProviderInstanceWithGroupId:(long long)groupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmFormValues:(LRJSONObjectWrapper *)ddmFormValues type:(NSString *)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteDataProviderInstanceWithDataProviderInstanceId:(long long)dataProviderInstanceId error:(NSError **)error;
- (NSDictionary *)fetchDataProviderInstanceWithDataProviderInstanceId:(long long)dataProviderInstanceId error:(NSError **)error;
- (NSDictionary *)getDataProviderInstanceWithDataProviderInstanceId:(long long)dataProviderInstanceId error:(NSError **)error;
- (NSDictionary *)updateDataProviderInstanceWithDataProviderInstanceId:(long long)dataProviderInstanceId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmFormValues:(LRJSONObjectWrapper *)ddmFormValues serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
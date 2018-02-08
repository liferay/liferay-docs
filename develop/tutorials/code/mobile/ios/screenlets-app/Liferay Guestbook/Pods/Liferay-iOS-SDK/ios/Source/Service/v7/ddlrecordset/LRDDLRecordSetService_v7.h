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
@interface LRDDLRecordSetService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name description:(NSString *)description scope:(int)scope andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId keywords:(NSString *)keywords scope:(int)scope start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (void)deleteRecordSetWithRecordSetId:(long long)recordSetId error:(NSError **)error;
- (NSDictionary *)addRecordSetWithGroupId:(long long)groupId ddmStructureId:(long long)ddmStructureId recordSetKey:(NSString *)recordSetKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap minDisplayRows:(int)minDisplayRows scope:(int)scope serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)fetchRecordSetWithRecordSetId:(long long)recordSetId error:(NSError **)error;
- (NSArray *)getRecordSetsWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSDictionary *)getRecordSetWithRecordSetId:(long long)recordSetId error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name description:(NSString *)description scope:(int)scope andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId keywords:(NSString *)keywords scope:(int)scope error:(NSError **)error;
- (NSDictionary *)updateMinDisplayRowsWithRecordSetId:(long long)recordSetId minDisplayRows:(int)minDisplayRows serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateRecordSetWithRecordSetId:(long long)recordSetId ddmStructureId:(long long)ddmStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap minDisplayRows:(int)minDisplayRows serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateRecordSetWithGroupId:(long long)groupId ddmStructureId:(long long)ddmStructureId recordSetKey:(NSString *)recordSetKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap minDisplayRows:(int)minDisplayRows serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateRecordSetWithRecordSetId:(long long)recordSetId settingsDDMFormValues:(LRJSONObjectWrapper *)settingsDDMFormValues error:(NSError **)error;

@end
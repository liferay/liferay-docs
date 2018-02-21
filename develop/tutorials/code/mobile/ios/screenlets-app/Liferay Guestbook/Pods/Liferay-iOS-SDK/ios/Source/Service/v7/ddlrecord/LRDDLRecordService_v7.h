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
@interface LRDDLRecordService_v7 : LRBaseService

- (NSDictionary *)addRecordWithGroupId:(long long)groupId recordSetId:(long long)recordSetId displayIndex:(int)displayIndex fieldsMap:(NSDictionary *)fieldsMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addRecordWithGroupId:(long long)groupId recordSetId:(long long)recordSetId displayIndex:(int)displayIndex fields:(LRJSONObjectWrapper *)fields serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addRecordWithGroupId:(long long)groupId recordSetId:(long long)recordSetId displayIndex:(int)displayIndex ddmFormValues:(LRJSONObjectWrapper *)ddmFormValues serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteRecordWithRecordId:(long long)recordId error:(NSError **)error;
- (NSDictionary *)deleteRecordLocaleWithRecordId:(long long)recordId locale:(NSString *)locale serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)getRecordWithRecordId:(long long)recordId error:(NSError **)error;
- (void)revertRecordWithRecordId:(long long)recordId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)revertRecordVersionWithRecordId:(long long)recordId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateRecordWithRecordId:(long long)recordId majorVersion:(BOOL)majorVersion displayIndex:(int)displayIndex fields:(LRJSONObjectWrapper *)fields mergeFields:(BOOL)mergeFields serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateRecordWithRecordId:(long long)recordId displayIndex:(int)displayIndex fieldsMap:(NSDictionary *)fieldsMap mergeFields:(BOOL)mergeFields serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateRecordWithRecordId:(long long)recordId majorVersion:(BOOL)majorVersion displayIndex:(int)displayIndex ddmFormValues:(LRJSONObjectWrapper *)ddmFormValues serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
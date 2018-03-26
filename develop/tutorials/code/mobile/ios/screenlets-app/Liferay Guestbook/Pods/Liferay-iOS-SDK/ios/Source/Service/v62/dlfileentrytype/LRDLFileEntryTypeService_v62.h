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
@interface LRDLFileEntryTypeService_v62 : LRBaseService

- (NSDictionary *)addFileEntryTypeWithGroupId:(long long)groupId name:(NSString *)name description:(NSString *)description ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addFileEntryTypeWithGroupId:(long long)groupId fileEntryTypeKey:(NSString *)fileEntryTypeKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (NSDictionary *)getFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error;
- (NSArray *)getFileEntryTypesWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)getFileEntryTypesWithGroupIds:(NSArray *)groupIds start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getFileEntryTypesCountWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)getFolderFileEntryTypesWithGroupIds:(NSArray *)groupIds folderId:(long long)folderId inherited:(BOOL)inherited error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords includeBasicFileEntryType:(BOOL)includeBasicFileEntryType start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords includeBasicFileEntryType:(BOOL)includeBasicFileEntryType error:(NSError **)error;
- (void)updateFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId name:(NSString *)name description:(NSString *)description ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)updateFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
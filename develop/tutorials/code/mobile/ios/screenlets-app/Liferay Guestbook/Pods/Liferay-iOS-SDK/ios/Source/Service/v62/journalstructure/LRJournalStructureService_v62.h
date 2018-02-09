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
@interface LRJournalStructureService_v62 : LRBaseService

- (NSDictionary *)addStructureWithGroupId:(long long)groupId structureId:(NSString *)structureId autoStructureId:(BOOL)autoStructureId parentStructureId:(NSString *)parentStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyStructureWithGroupId:(long long)groupId oldStructureId:(NSString *)oldStructureId newStructureId:(NSString *)newStructureId autoStructureId:(BOOL)autoStructureId error:(NSError **)error;
- (void)deleteStructureWithGroupId:(long long)groupId structureId:(NSString *)structureId error:(NSError **)error;
- (NSDictionary *)getStructureWithGroupId:(long long)groupId structureId:(NSString *)structureId error:(NSError **)error;
- (NSDictionary *)getStructureWithGroupId:(long long)groupId structureId:(NSString *)structureId includeGlobalStructures:(BOOL)includeGlobalStructures error:(NSError **)error;
- (NSArray *)getStructuresWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getStructuresWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds structureId:(NSString *)structureId name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds structureId:(NSString *)structureId name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSDictionary *)updateStructureWithGroupId:(long long)groupId structureId:(NSString *)structureId parentStructureId:(NSString *)parentStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
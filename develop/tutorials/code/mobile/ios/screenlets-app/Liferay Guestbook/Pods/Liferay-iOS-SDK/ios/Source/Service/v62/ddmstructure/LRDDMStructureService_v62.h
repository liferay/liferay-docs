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
@interface LRDDMStructureService_v62 : LRBaseService

- (NSDictionary *)addStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd storageType:(NSString *)storageType type:(int)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addStructureWithUserId:(long long)userId groupId:(long long)groupId parentStructureKey:(NSString *)parentStructureKey classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd storageType:(NSString *)storageType type:(int)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addStructureWithUserId:(long long)userId groupId:(long long)groupId classNameId:(long long)classNameId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyStructureWithStructureId:(long long)structureId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyStructureWithStructureId:(long long)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteStructureWithStructureId:(long long)structureId error:(NSError **)error;
- (NSDictionary *)fetchStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey error:(NSError **)error;
- (NSDictionary *)getStructureWithStructureId:(long long)structureId error:(NSError **)error;
- (NSDictionary *)getStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey error:(NSError **)error;
- (NSDictionary *)getStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey includeGlobalStructures:(BOOL)includeGlobalStructures error:(NSError **)error;
- (NSArray *)getStructuresWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getStructuresWithGroupIds:(NSArray *)groupIds error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds name:(NSString *)name description:(NSString *)description storageType:(NSString *)storageType type:(int)type andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds name:(NSString *)name description:(NSString *)description storageType:(NSString *)storageType type:(int)type andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSDictionary *)updateStructureWithStructureId:(long long)structureId parentStructureId:(long long)parentStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
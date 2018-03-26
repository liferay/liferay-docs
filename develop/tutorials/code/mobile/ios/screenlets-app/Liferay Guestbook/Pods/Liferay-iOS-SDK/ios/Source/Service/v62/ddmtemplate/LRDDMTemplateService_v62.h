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
@interface LRDDMTemplateService_v62 : LRBaseService

- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK templateKey:(NSString *)templateKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)copyTemplatesWithClassNameId:(long long)classNameId classPK:(long long)classPK newClassPK:(long long)newClassPK type:(NSString *)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteTemplateWithTemplateId:(long long)templateId error:(NSError **)error;
- (NSDictionary *)fetchTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error;
- (NSDictionary *)getTemplateWithTemplateId:(long long)templateId error:(NSError **)error;
- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error;
- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey includeGlobalTemplates:(BOOL)includeGlobalTemplates error:(NSError **)error;
- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId error:(NSError **)error;
- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK error:(NSError **)error;
- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK type:(NSString *)type error:(NSError **)error;
- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK type:(NSString *)type mode:(NSString *)mode error:(NSError **)error;
- (NSArray *)getTemplatesByClassPkWithGroupId:(long long)groupId classPK:(long long)classPK error:(NSError **)error;
- (NSArray *)getTemplatesByStructureClassNameIdWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSNumber *)getTemplatesByStructureClassNameIdCountWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode error:(NSError **)error;
- (NSDictionary *)updateTemplateWithTemplateId:(long long)templateId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
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
@interface LRDDMTemplateService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error;
- (NSDictionary *)getTemplateWithTemplateId:(long long)templateId error:(NSError **)error;
- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey includeAncestorTemplates:(BOOL)includeAncestorTemplates error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator error:(NSError **)error;
- (void)deleteTemplateWithTemplateId:(long long)templateId error:(NSError **)error;
- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId type:(NSString *)type mode:(NSString *)mode status:(int)status error:(NSError **)error;
- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId status:(int)status error:(NSError **)error;
- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId includeAncestorTemplates:(BOOL)includeAncestorTemplates status:(int)status error:(NSError **)error;
- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId resourceClassNameId:(long long)resourceClassNameId status:(int)status error:(NSError **)error;
- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId type:(NSString *)type status:(int)status error:(NSError **)error;
- (NSDictionary *)fetchTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error;
- (NSDictionary *)updateTemplateWithTemplateId:(long long)templateId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateTemplateWithTemplateId:(long long)templateId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId templateKey:(NSString *)templateKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)copyTemplatesWithClassNameId:(long long)classNameId oldClassPK:(long long)oldClassPK resourceClassNameId:(long long)resourceClassNameId newClassPK:(long long)newClassPK type:(NSString *)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getTemplatesByClassPkWithCompanyId:(long long)companyId groupId:(long long)groupId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId status:(int)status error:(NSError **)error;
- (NSArray *)getTemplatesByStructureClassNameIdWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSNumber *)getTemplatesByStructureClassNameIdCountWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId status:(int)status error:(NSError **)error;
- (void)revertTemplateWithTemplateId:(long long)templateId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
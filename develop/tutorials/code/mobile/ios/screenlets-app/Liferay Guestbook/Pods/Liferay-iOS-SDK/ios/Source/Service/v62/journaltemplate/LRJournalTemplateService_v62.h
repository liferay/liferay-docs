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
@interface LRJournalTemplateService_v62 : LRBaseService

- (NSDictionary *)addTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId autoTemplateId:(BOOL)autoTemplateId structureId:(NSString *)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsl:(NSString *)xsl formatXsl:(BOOL)formatXsl langType:(NSString *)langType cacheable:(BOOL)cacheable serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId autoTemplateId:(BOOL)autoTemplateId structureId:(NSString *)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsl:(NSString *)xsl formatXsl:(BOOL)formatXsl langType:(NSString *)langType cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyTemplateWithGroupId:(long long)groupId oldTemplateId:(NSString *)oldTemplateId newTemplateId:(NSString *)newTemplateId autoTemplateId:(BOOL)autoTemplateId error:(NSError **)error;
- (void)deleteTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId error:(NSError **)error;
- (NSArray *)getStructureTemplatesWithGroupId:(long long)groupId structureId:(NSString *)structureId error:(NSError **)error;
- (NSDictionary *)getTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId error:(NSError **)error;
- (NSDictionary *)getTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId includeGlobalTemplates:(BOOL)includeGlobalTemplates error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds templateId:(NSString *)templateId structureId:(NSString *)structureId structureIdComparator:(NSString *)structureIdComparator name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords structureId:(NSString *)structureId structureIdComparator:(NSString *)structureIdComparator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords structureId:(NSString *)structureId structureIdComparator:(NSString *)structureIdComparator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds templateId:(NSString *)templateId structureId:(NSString *)structureId structureIdComparator:(NSString *)structureIdComparator name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSDictionary *)updateTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId structureId:(NSString *)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsl:(NSString *)xsl formatXsl:(BOOL)formatXsl langType:(NSString *)langType cacheable:(BOOL)cacheable serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateTemplateWithGroupId:(long long)groupId templateId:(NSString *)templateId structureId:(NSString *)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsl:(NSString *)xsl formatXsl:(BOOL)formatXsl langType:(NSString *)langType cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
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

#import "LRDDMTemplateService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDDMTemplateService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"resourceClassNameId": @(resourceClassNameId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"status": @(status),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMTemplate>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"resourceClassNameId": @(resourceClassNameId),
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMTemplate>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"status": @(status),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMTemplate>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMTemplate>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"templateKey": [self checkNull: templateKey]
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateWithTemplateId:(long long)templateId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey includeAncestorTemplates:(BOOL)includeAncestorTemplates error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"templateKey": [self checkNull: templateKey],
		@"includeAncestorTemplates": @(includeAncestorTemplates)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"resourceClassNameId": @(resourceClassNameId),
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"resourceClassNameId": @(resourceClassNameId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"status": @(status),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language status:(int)status andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"status": @(status),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)deleteTemplateWithTemplateId:(long long)templateId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/delete-template": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId type:(NSString *)type mode:(NSString *)mode status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId includeAncestorTemplates:(BOOL)includeAncestorTemplates status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"includeAncestorTemplates": @(includeAncestorTemplates),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId resourceClassNameId:(long long)resourceClassNameId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"resourceClassNameId": @(resourceClassNameId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId type:(NSString *)type status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"type": [self checkNull: type],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"templateKey": [self checkNull: templateKey]
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/fetch-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateTemplateWithTemplateId:(long long)templateId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
		@"classPK": @(classPK),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"script": [self checkNull: script],
		@"cacheable": @(cacheable),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/update-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateTemplateWithTemplateId:(long long)templateId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
		@"classPK": @(classPK),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"script": [self checkNull: script],
		@"cacheable": @(cacheable),
		@"smallImage": @(smallImage),
		@"smallImageURL": [self checkNull: smallImageURL],
		@"smallImageFile": [self checkNull: smallImageFile],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/update-template": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId templateKey:(NSString *)templateKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"templateKey": [self checkNull: templateKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"script": [self checkNull: script],
		@"cacheable": @(cacheable),
		@"smallImage": @(smallImage),
		@"smallImageURL": [self checkNull: smallImageURL],
		@"smallImageFile": [self checkNull: smallImageFile],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/add-template": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"script": [self checkNull: script],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/add-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/copy-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/copy-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)copyTemplatesWithClassNameId:(long long)classNameId oldClassPK:(long long)oldClassPK resourceClassNameId:(long long)resourceClassNameId newClassPK:(long long)newClassPK type:(NSString *)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameId": @(classNameId),
		@"oldClassPK": @(oldClassPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"newClassPK": @(newClassPK),
		@"type": [self checkNull: type],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/copy-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesByClassPkWithCompanyId:(long long)companyId groupId:(long long)groupId classPK:(long long)classPK resourceClassNameId:(long long)resourceClassNameId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classPK": @(classPK),
		@"resourceClassNameId": @(resourceClassNameId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates-by-class-pk": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesByStructureClassNameIdWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"structureClassNameId": @(structureClassNameId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMTemplate>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates-by-structure-class-name-id": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getTemplatesByStructureClassNameIdCountWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"structureClassNameId": @(structureClassNameId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/get-templates-by-structure-class-name-id-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)revertTemplateWithTemplateId:(long long)templateId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
		@"version": [self checkNull: version],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmtemplate/revert-template": _params};

	[self.session invoke:_command error:error];
}

@end
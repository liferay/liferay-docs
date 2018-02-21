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

#import "LRDDMTemplateService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDDMTemplateService_v62

- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"script": [self checkNull: script],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmtemplate/add-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK templateKey:(NSString *)templateKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap type:(NSString *)type mode:(NSString *)mode language:(NSString *)language script:(NSString *)script cacheable:(BOOL)cacheable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallImageFile:(LRUploadData *)smallImageFile serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
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

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmtemplate/add-template": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmtemplate/copy-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyTemplateWithTemplateId:(long long)templateId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmtemplate/copy-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)copyTemplatesWithClassNameId:(long long)classNameId classPK:(long long)classPK newClassPK:(long long)newClassPK type:(NSString *)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"newClassPK": @(newClassPK),
		@"type": [self checkNull: type],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmtemplate/copy-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)deleteTemplateWithTemplateId:(long long)templateId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/delete-template": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"templateKey": [self checkNull: templateKey]
	}];

	NSDictionary *_command = @{@"/ddmtemplate/fetch-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateWithTemplateId:(long long)templateId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"templateKey": [self checkNull: templateKey]
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateWithGroupId:(long long)groupId classNameId:(long long)classNameId templateKey:(NSString *)templateKey includeGlobalTemplates:(BOOL)includeGlobalTemplates error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"templateKey": [self checkNull: templateKey],
		@"includeGlobalTemplates": @(includeGlobalTemplates)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK type:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK type:(NSString *)type mode:(NSString *)mode error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode]
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesByClassPkWithGroupId:(long long)groupId classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates-by-class-pk": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplatesByStructureClassNameIdWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"structureClassNameId": @(structureClassNameId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates-by-structure-class-name-id": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getTemplatesByStructureClassNameIdCountWithGroupId:(long long)groupId structureClassNameId:(long long)structureClassNameId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"structureClassNameId": @(structureClassNameId)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/get-templates-by-structure-class-name-id-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmtemplate/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs name:(NSString *)name description:(NSString *)description type:(NSString *)type mode:(NSString *)mode language:(NSString *)language andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode],
		@"language": [self checkNull: language],
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode]
	}];

	NSDictionary *_command = @{@"/ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds classPKs:(NSArray *)classPKs keywords:(NSString *)keywords type:(NSString *)type mode:(NSString *)mode error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"classPKs": [self checkNull: classPKs],
		@"keywords": [self checkNull: keywords],
		@"type": [self checkNull: type],
		@"mode": [self checkNull: mode]
	}];

	NSDictionary *_command = @{@"/ddmtemplate/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
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

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmtemplate/update-template": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

@end
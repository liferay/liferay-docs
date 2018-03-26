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

#import "LRDDMStructureService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDDMStructureService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameId:(long long)classNameId keywords:(NSString *)keywords status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameId": @(classNameId),
		@"keywords": [self checkNull: keywords],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMStructure>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmstructure/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameId:(long long)classNameId name:(NSString *)name description:(NSString *)description storageType:(NSString *)storageType type:(int)type status:(int)status andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameId": @(classNameId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
		@"status": @(status),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMStructure>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmstructure/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey includeAncestorStructures:(BOOL)includeAncestorStructures error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"includeAncestorStructures": @(includeAncestorStructures)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/fetch-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey]
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/fetch-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureWithStructureId:(long long)structureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/get-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey includeAncestorStructures:(BOOL)includeAncestorStructures error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"includeAncestorStructures": @(includeAncestorStructures)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/get-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey]
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/get-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameId:(long long)classNameId name:(NSString *)name description:(NSString *)description storageType:(NSString *)storageType type:(int)type status:(int)status andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameId": @(classNameId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
		@"status": @(status),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameId:(long long)classNameId keywords:(NSString *)keywords status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameId": @(classNameId),
		@"keywords": [self checkNull: keywords],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)deleteStructureWithStructureId:(long long)structureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/delete-structure": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)addStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmForm:(LRJSONObjectWrapper *)ddmForm ddmFormLayout:(LRJSONObjectWrapper *)ddmFormLayout storageType:(NSString *)storageType type:(int)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentStructureId": @(parentStructureId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
	}];

	[self mangleWrapperWithParams:_params name:@"ddmForm" className:@"com.liferay.dynamic.data.mapping.model.DDMForm" wrapper:ddmForm];
	[self mangleWrapperWithParams:_params name:@"ddmFormLayout" className:@"com.liferay.dynamic.data.mapping.model.DDMFormLayout" wrapper:ddmFormLayout];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addStructureWithUserId:(long long)userId groupId:(long long)groupId classNameId:(long long)classNameId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"xsd": [self checkNull: xsd],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addStructureWithUserId:(long long)userId groupId:(long long)groupId classNameId:(long long)classNameId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmForm:(LRJSONObjectWrapper *)ddmForm ddmFormLayout:(LRJSONObjectWrapper *)ddmFormLayout storageType:(NSString *)storageType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"storageType": [self checkNull: storageType],
	}];

	[self mangleWrapperWithParams:_params name:@"ddmForm" className:@"com.liferay.dynamic.data.mapping.model.DDMForm" wrapper:ddmForm];
	[self mangleWrapperWithParams:_params name:@"ddmFormLayout" className:@"com.liferay.dynamic.data.mapping.model.DDMFormLayout" wrapper:ddmFormLayout];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addStructureWithUserId:(long long)userId groupId:(long long)groupId parentStructureKey:(NSString *)parentStructureKey classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd storageType:(NSString *)storageType type:(int)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"parentStructureKey": [self checkNull: parentStructureKey],
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"xsd": [self checkNull: xsd],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addStructureWithUserId:(long long)userId groupId:(long long)groupId parentStructureKey:(NSString *)parentStructureKey classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmForm:(LRJSONObjectWrapper *)ddmForm ddmFormLayout:(LRJSONObjectWrapper *)ddmFormLayout storageType:(NSString *)storageType type:(int)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"parentStructureKey": [self checkNull: parentStructureKey],
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
	}];

	[self mangleWrapperWithParams:_params name:@"ddmForm" className:@"com.liferay.dynamic.data.mapping.model.DDMForm" wrapper:ddmForm];
	[self mangleWrapperWithParams:_params name:@"ddmFormLayout" className:@"com.liferay.dynamic.data.mapping.model.DDMFormLayout" wrapper:ddmFormLayout];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd storageType:(NSString *)storageType type:(int)type serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentStructureId": @(parentStructureId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"xsd": [self checkNull: xsd],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStructureWithStructureId:(long long)structureId parentStructureId:(long long)parentStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap definition:(NSString *)definition serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"parentStructureId": @(parentStructureId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"definition": [self checkNull: definition],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/update-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStructureWithStructureId:(long long)structureId parentStructureId:(long long)parentStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmForm:(LRJSONObjectWrapper *)ddmForm ddmFormLayout:(LRJSONObjectWrapper *)ddmFormLayout serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"parentStructureId": @(parentStructureId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"ddmForm" className:@"com.liferay.dynamic.data.mapping.model.DDMForm" wrapper:ddmForm];
	[self mangleWrapperWithParams:_params name:@"ddmFormLayout" className:@"com.liferay.dynamic.data.mapping.model.DDMFormLayout" wrapper:ddmFormLayout];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/update-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmForm:(LRJSONObjectWrapper *)ddmForm ddmFormLayout:(LRJSONObjectWrapper *)ddmFormLayout serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentStructureId": @(parentStructureId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"ddmForm" className:@"com.liferay.dynamic.data.mapping.model.DDMForm" wrapper:ddmForm];
	[self mangleWrapperWithParams:_params name:@"ddmFormLayout" className:@"com.liferay.dynamic.data.mapping.model.DDMFormLayout" wrapper:ddmFormLayout];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/update-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap definition:(NSString *)definition serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentStructureId": @(parentStructureId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"definition": [self checkNull: definition],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/update-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyStructureWithStructureId:(long long)structureId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/copy-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyStructureWithStructureId:(long long)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/copy-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getStructuresWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameId:(long long)classNameId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameId": @(classNameId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/ddm.ddmstructure/get-structures": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getStructuresWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameId:(long long)classNameId status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameId": @(classNameId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMStructure>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmstructure/get-structures": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)revertStructureWithStructureId:(long long)structureId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"version": [self checkNull: version],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddm.ddmstructure/revert-structure": _params};

	[self.session invoke:_command error:error];
}

@end
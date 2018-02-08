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

#import "LRDDMStructureService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDDMStructureService_v62

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

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/add-structure": _params};

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

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/add-structure": _params};

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

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/add-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyStructureWithStructureId:(long long)structureId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/copy-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyStructureWithStructureId:(long long)structureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/copy-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteStructureWithStructureId:(long long)structureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId)
	}];

	NSDictionary *_command = @{@"/ddmstructure/delete-structure": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey]
	}];

	NSDictionary *_command = @{@"/ddmstructure/fetch-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureWithStructureId:(long long)structureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId)
	}];

	NSDictionary *_command = @{@"/ddmstructure/get-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey]
	}];

	NSDictionary *_command = @{@"/ddmstructure/get-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getStructureWithGroupId:(long long)groupId classNameId:(long long)classNameId structureKey:(NSString *)structureKey includeGlobalStructures:(BOOL)includeGlobalStructures error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"includeGlobalStructures": @(includeGlobalStructures)
	}];

	NSDictionary *_command = @{@"/ddmstructure/get-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getStructuresWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/ddmstructure/get-structures": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getStructuresWithGroupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/ddmstructure/get-structures": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds name:(NSString *)name description:(NSString *)description storageType:(NSString *)storageType type:(int)type andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmstructure/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"keywords": [self checkNull: keywords],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddmstructure/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"keywords": [self checkNull: keywords]
	}];

	NSDictionary *_command = @{@"/ddmstructure/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds name:(NSString *)name description:(NSString *)description storageType:(NSString *)storageType type:(int)type andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"storageType": [self checkNull: storageType],
		@"type": @(type),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddmstructure/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStructureWithStructureId:(long long)structureId parentStructureId:(long long)parentStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"structureId": @(structureId),
		@"parentStructureId": @(parentStructureId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"xsd": [self checkNull: xsd],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/update-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStructureWithGroupId:(long long)groupId parentStructureId:(long long)parentStructureId classNameId:(long long)classNameId structureKey:(NSString *)structureKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap xsd:(NSString *)xsd serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentStructureId": @(parentStructureId),
		@"classNameId": @(classNameId),
		@"structureKey": [self checkNull: structureKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"xsd": [self checkNull: xsd],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddmstructure/update-structure": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
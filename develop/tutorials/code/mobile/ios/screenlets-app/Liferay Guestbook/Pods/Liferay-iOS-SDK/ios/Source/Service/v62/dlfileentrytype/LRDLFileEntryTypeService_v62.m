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

#import "LRDLFileEntryTypeService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDLFileEntryTypeService_v62

- (NSDictionary *)addFileEntryTypeWithGroupId:(long long)groupId name:(NSString *)name description:(NSString *)description ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"ddmStructureIds": [self checkNull: ddmStructureIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentrytype/add-file-entry-type": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addFileEntryTypeWithGroupId:(long long)groupId fileEntryTypeKey:(NSString *)fileEntryTypeKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"fileEntryTypeKey": [self checkNull: fileEntryTypeKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"ddmStructureIds": [self checkNull: ddmStructureIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentrytype/add-file-entry-type": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryTypeId": @(fileEntryTypeId)
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/delete-file-entry-type": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryTypeId": @(fileEntryTypeId)
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/get-file-entry-type": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntryTypesWithGroupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/get-file-entry-types": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntryTypesWithGroupIds:(NSArray *)groupIds start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/get-file-entry-types": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntryTypesCountWithGroupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/get-file-entry-types-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getFolderFileEntryTypesWithGroupIds:(NSArray *)groupIds folderId:(long long)folderId inherited:(BOOL)inherited error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"folderId": @(folderId),
		@"inherited": @(inherited)
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/get-folder-file-entry-types": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords includeBasicFileEntryType:(BOOL)includeBasicFileEntryType start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"keywords": [self checkNull: keywords],
		@"includeBasicFileEntryType": @(includeBasicFileEntryType),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/dlfileentrytype/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds keywords:(NSString *)keywords includeBasicFileEntryType:(BOOL)includeBasicFileEntryType error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"keywords": [self checkNull: keywords],
		@"includeBasicFileEntryType": @(includeBasicFileEntryType)
	}];

	NSDictionary *_command = @{@"/dlfileentrytype/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)updateFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId name:(NSString *)name description:(NSString *)description ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryTypeId": @(fileEntryTypeId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"ddmStructureIds": [self checkNull: ddmStructureIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentrytype/update-file-entry-type": _params};

	[self.session invoke:_command error:error];
}

- (void)updateFileEntryTypeWithFileEntryTypeId:(long long)fileEntryTypeId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap ddmStructureIds:(NSArray *)ddmStructureIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryTypeId": @(fileEntryTypeId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"ddmStructureIds": [self checkNull: ddmStructureIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentrytype/update-file-entry-type": _params};

	[self.session invoke:_command error:error];
}

@end
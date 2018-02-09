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

#import "LRDDLRecordSetService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDDLRecordSetService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name description:(NSString *)description scope:(int)scope andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"scope": @(scope),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.lists.model.DDLRecordSet>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId keywords:(NSString *)keywords scope:(int)scope start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"keywords": [self checkNull: keywords],
		@"scope": @(scope),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.lists.model.DDLRecordSet>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)deleteRecordSetWithRecordSetId:(long long)recordSetId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordSetId": @(recordSetId)
	}];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/delete-record-set": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)addRecordSetWithGroupId:(long long)groupId ddmStructureId:(long long)ddmStructureId recordSetKey:(NSString *)recordSetKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap minDisplayRows:(int)minDisplayRows scope:(int)scope serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"ddmStructureId": @(ddmStructureId),
		@"recordSetKey": [self checkNull: recordSetKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"minDisplayRows": @(minDisplayRows),
		@"scope": @(scope),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/add-record-set": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchRecordSetWithRecordSetId:(long long)recordSetId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordSetId": @(recordSetId)
	}];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/fetch-record-set": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getRecordSetsWithGroupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/get-record-sets": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRecordSetWithRecordSetId:(long long)recordSetId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordSetId": @(recordSetId)
	}];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/get-record-set": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId name:(NSString *)name description:(NSString *)description scope:(int)scope andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"scope": @(scope),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId keywords:(NSString *)keywords scope:(int)scope error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"keywords": [self checkNull: keywords],
		@"scope": @(scope)
	}];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateMinDisplayRowsWithRecordSetId:(long long)recordSetId minDisplayRows:(int)minDisplayRows serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordSetId": @(recordSetId),
		@"minDisplayRows": @(minDisplayRows),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/update-min-display-rows": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRecordSetWithRecordSetId:(long long)recordSetId ddmStructureId:(long long)ddmStructureId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap minDisplayRows:(int)minDisplayRows serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordSetId": @(recordSetId),
		@"ddmStructureId": @(ddmStructureId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"minDisplayRows": @(minDisplayRows),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/update-record-set": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRecordSetWithGroupId:(long long)groupId ddmStructureId:(long long)ddmStructureId recordSetKey:(NSString *)recordSetKey nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap minDisplayRows:(int)minDisplayRows serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"ddmStructureId": @(ddmStructureId),
		@"recordSetKey": [self checkNull: recordSetKey],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"minDisplayRows": @(minDisplayRows),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/update-record-set": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRecordSetWithRecordSetId:(long long)recordSetId settingsDDMFormValues:(LRJSONObjectWrapper *)settingsDDMFormValues error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordSetId": @(recordSetId),
	}];

	[self mangleWrapperWithParams:_params name:@"settingsDDMFormValues" className:@"com.liferay.dynamic.data.mapping.storage.DDMFormValues" wrapper:settingsDDMFormValues];

	NSDictionary *_command = @{@"/ddl.ddlrecordset/update-record-set": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
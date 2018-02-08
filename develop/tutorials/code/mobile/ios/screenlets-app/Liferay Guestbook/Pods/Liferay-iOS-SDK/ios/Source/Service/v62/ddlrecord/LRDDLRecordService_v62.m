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

#import "LRDDLRecordService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDDLRecordService_v62

- (NSDictionary *)addRecordWithGroupId:(long long)groupId recordSetId:(long long)recordSetId displayIndex:(int)displayIndex fields:(LRJSONObjectWrapper *)fields serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"recordSetId": @(recordSetId),
		@"displayIndex": @(displayIndex),
	}];

	[self mangleWrapperWithParams:_params name:@"fields" className:@"com.liferay.portlet.dynamicdatamapping.storage.Fields" wrapper:fields];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddlrecord/add-record": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addRecordWithGroupId:(long long)groupId recordSetId:(long long)recordSetId displayIndex:(int)displayIndex fieldsMap:(NSDictionary *)fieldsMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"recordSetId": @(recordSetId),
		@"displayIndex": @(displayIndex),
		@"fieldsMap": [self checkNull: fieldsMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddlrecord/add-record": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteRecordLocaleWithRecordId:(long long)recordId locale:(NSString *)locale serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordId": @(recordId),
		@"locale": [self checkNull: locale],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddlrecord/delete-record-locale": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRecordWithRecordId:(long long)recordId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordId": @(recordId)
	}];

	NSDictionary *_command = @{@"/ddlrecord/get-record": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRecordWithRecordId:(long long)recordId displayIndex:(int)displayIndex fieldsMap:(NSDictionary *)fieldsMap mergeFields:(BOOL)mergeFields serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordId": @(recordId),
		@"displayIndex": @(displayIndex),
		@"fieldsMap": [self checkNull: fieldsMap],
		@"mergeFields": @(mergeFields),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddlrecord/update-record": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRecordWithRecordId:(long long)recordId majorVersion:(BOOL)majorVersion displayIndex:(int)displayIndex fields:(LRJSONObjectWrapper *)fields mergeFields:(BOOL)mergeFields serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"recordId": @(recordId),
		@"majorVersion": @(majorVersion),
		@"displayIndex": @(displayIndex),
		@"mergeFields": @(mergeFields),
	}];

	[self mangleWrapperWithParams:_params name:@"fields" className:@"com.liferay.portlet.dynamicdatamapping.storage.Fields" wrapper:fields];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/ddlrecord/update-record": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
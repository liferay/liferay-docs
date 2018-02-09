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

#import "LRLayoutPrototypeService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRLayoutPrototypeService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId active:(BOOL)active obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.portal.kernel.model.LayoutPrototype>" wrapper:obc];

	NSDictionary *_command = @{@"/layoutprototype/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addLayoutPrototypeWithNameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutprototype/add-layout-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addLayoutPrototypeWithNameMap:(NSDictionary *)nameMap description:(NSString *)description active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nameMap": [self checkNull: nameMap],
		@"description": [self checkNull: description],
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutprototype/add-layout-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutPrototypeWithLayoutPrototypeId:(long long)layoutPrototypeId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutPrototypeId": @(layoutPrototypeId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutprototype/update-layout-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutPrototypeWithLayoutPrototypeId:(long long)layoutPrototypeId nameMap:(NSDictionary *)nameMap description:(NSString *)description active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutPrototypeId": @(layoutPrototypeId),
		@"nameMap": [self checkNull: nameMap],
		@"description": [self checkNull: description],
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutprototype/update-layout-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getLayoutPrototypeWithLayoutPrototypeId:(long long)layoutPrototypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutPrototypeId": @(layoutPrototypeId)
	}];

	NSDictionary *_command = @{@"/layoutprototype/get-layout-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteLayoutPrototypeWithLayoutPrototypeId:(long long)layoutPrototypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutPrototypeId": @(layoutPrototypeId)
	}];

	NSDictionary *_command = @{@"/layoutprototype/delete-layout-prototype": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchLayoutPrototypeWithLayoutPrototypeId:(long long)layoutPrototypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutPrototypeId": @(layoutPrototypeId)
	}];

	NSDictionary *_command = @{@"/layoutprototype/fetch-layout-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
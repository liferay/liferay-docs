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

#import "LRLayoutSetPrototypeService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRLayoutSetPrototypeService_v62

- (NSDictionary *)addLayoutSetPrototypeWithNameMap:(NSDictionary *)nameMap description:(NSString *)description active:(BOOL)active layoutsUpdateable:(BOOL)layoutsUpdateable serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nameMap": [self checkNull: nameMap],
		@"description": [self checkNull: description],
		@"active": @(active),
		@"layoutsUpdateable": @(layoutsUpdateable),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutsetprototype/add-layout-set-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteLayoutSetPrototypeWithLayoutSetPrototypeId:(long long)layoutSetPrototypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutSetPrototypeId": @(layoutSetPrototypeId)
	}];

	NSDictionary *_command = @{@"/layoutsetprototype/delete-layout-set-prototype": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getLayoutSetPrototypeWithLayoutSetPrototypeId:(long long)layoutSetPrototypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutSetPrototypeId": @(layoutSetPrototypeId)
	}];

	NSDictionary *_command = @{@"/layoutsetprototype/get-layout-set-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId active:(BOOL)active obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/layoutsetprototype/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutSetPrototypeWithLayoutSetPrototypeId:(long long)layoutSetPrototypeId settings:(NSString *)settings error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutSetPrototypeId": @(layoutSetPrototypeId),
		@"settings": [self checkNull: settings]
	}];

	NSDictionary *_command = @{@"/layoutsetprototype/update-layout-set-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutSetPrototypeWithLayoutSetPrototypeId:(long long)layoutSetPrototypeId nameMap:(NSDictionary *)nameMap description:(NSString *)description active:(BOOL)active layoutsUpdateable:(BOOL)layoutsUpdateable serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutSetPrototypeId": @(layoutSetPrototypeId),
		@"nameMap": [self checkNull: nameMap],
		@"description": [self checkNull: description],
		@"active": @(active),
		@"layoutsUpdateable": @(layoutsUpdateable),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutsetprototype/update-layout-set-prototype": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
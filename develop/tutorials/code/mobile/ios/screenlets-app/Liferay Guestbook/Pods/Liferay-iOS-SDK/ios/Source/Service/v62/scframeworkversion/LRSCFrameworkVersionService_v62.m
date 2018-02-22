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

#import "LRSCFrameworkVersionService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRSCFrameworkVersionService_v62

- (NSDictionary *)addFrameworkVersionWithName:(NSString *)name url:(NSString *)url active:(BOOL)active priority:(int)priority serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"name": [self checkNull: name],
		@"url": [self checkNull: url],
		@"active": @(active),
		@"priority": @(priority),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/scframeworkversion/add-framework-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteFrameworkVersionWithFrameworkVersionId:(long long)frameworkVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"frameworkVersionId": @(frameworkVersionId)
	}];

	NSDictionary *_command = @{@"/scframeworkversion/delete-framework-version": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getFrameworkVersionWithFrameworkVersionId:(long long)frameworkVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"frameworkVersionId": @(frameworkVersionId)
	}];

	NSDictionary *_command = @{@"/scframeworkversion/get-framework-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getFrameworkVersionsWithGroupId:(long long)groupId active:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/scframeworkversion/get-framework-versions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFrameworkVersionsWithGroupId:(long long)groupId active:(BOOL)active start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"active": @(active),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/scframeworkversion/get-framework-versions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFrameworkVersionWithFrameworkVersionId:(long long)frameworkVersionId name:(NSString *)name url:(NSString *)url active:(BOOL)active priority:(int)priority error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"frameworkVersionId": @(frameworkVersionId),
		@"name": [self checkNull: name],
		@"url": [self checkNull: url],
		@"active": @(active),
		@"priority": @(priority)
	}];

	NSDictionary *_command = @{@"/scframeworkversion/update-framework-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
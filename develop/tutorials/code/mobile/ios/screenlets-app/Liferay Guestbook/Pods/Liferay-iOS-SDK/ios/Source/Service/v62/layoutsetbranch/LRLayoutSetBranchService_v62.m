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

#import "LRLayoutSetBranchService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRLayoutSetBranchService_v62

- (NSDictionary *)addLayoutSetBranchWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout name:(NSString *)name description:(NSString *)description master:(BOOL)master copyLayoutSetBranchId:(long long)copyLayoutSetBranchId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"master": @(master),
		@"copyLayoutSetBranchId": @(copyLayoutSetBranchId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutsetbranch/add-layout-set-branch": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteLayoutSetBranchWithLayoutSetBranchId:(long long)layoutSetBranchId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutSetBranchId": @(layoutSetBranchId)
	}];

	NSDictionary *_command = @{@"/layoutsetbranch/delete-layout-set-branch": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getLayoutSetBranchesWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout)
	}];

	NSDictionary *_command = @{@"/layoutsetbranch/get-layout-set-branches": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)mergeLayoutSetBranchWithLayoutSetBranchId:(long long)layoutSetBranchId mergeLayoutSetBranchId:(long long)mergeLayoutSetBranchId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"layoutSetBranchId": @(layoutSetBranchId),
		@"mergeLayoutSetBranchId": @(mergeLayoutSetBranchId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutsetbranch/merge-layout-set-branch": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutSetBranchWithGroupId:(long long)groupId layoutSetBranchId:(long long)layoutSetBranchId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"layoutSetBranchId": @(layoutSetBranchId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layoutsetbranch/update-layout-set-branch": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
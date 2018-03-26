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

#import "LRMembershipRequestService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRMembershipRequestService_v62

- (NSDictionary *)addMembershipRequestWithGroupId:(long long)groupId comments:(NSString *)comments serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"comments": [self checkNull: comments],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/membershiprequest/add-membership-request": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteMembershipRequestsWithGroupId:(long long)groupId statusId:(int)statusId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"statusId": @(statusId)
	}];

	NSDictionary *_command = @{@"/membershiprequest/delete-membership-requests": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getMembershipRequestWithMembershipRequestId:(long long)membershipRequestId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"membershipRequestId": @(membershipRequestId)
	}];

	NSDictionary *_command = @{@"/membershiprequest/get-membership-request": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)updateStatusWithMembershipRequestId:(long long)membershipRequestId reviewComments:(NSString *)reviewComments statusId:(int)statusId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"membershipRequestId": @(membershipRequestId),
		@"reviewComments": [self checkNull: reviewComments],
		@"statusId": @(statusId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/membershiprequest/update-status": _params};

	[self.session invoke:_command error:error];
}

@end
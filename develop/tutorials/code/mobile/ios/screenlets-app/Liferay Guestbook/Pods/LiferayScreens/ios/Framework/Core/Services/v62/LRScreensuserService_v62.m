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

#import "LRScreensuserService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRScreensuserService_v62

- (NSDictionary *)getCurrentUser:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/screens-web.screensuser/get-current-user": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (BOOL)sendPasswordByEmailAddressWithCompanyId:(long long)companyId emailAddress:(NSString *)emailAddress error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"emailAddress": emailAddress
	}];

	NSDictionary *_command = @{@"/screens-web.screensuser/send-password-by-email-address": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)sendPasswordByScreenNameWithCompanyId:(long long)companyId screenName:(NSString *)screenName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"screenName": screenName
	}];

	NSDictionary *_command = @{@"/screens-web.screensuser/send-password-by-screen-name": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)sendPasswordByUserIdWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/screens-web.screensuser/send-password-by-user-id": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

@end
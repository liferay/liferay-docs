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

#import "LRSCLicenseService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRSCLicenseService_v62

- (NSDictionary *)addLicenseWithName:(NSString *)name url:(NSString *)url openSource:(BOOL)openSource active:(BOOL)active recommended:(BOOL)recommended error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"name": [self checkNull: name],
		@"url": [self checkNull: url],
		@"openSource": @(openSource),
		@"active": @(active),
		@"recommended": @(recommended)
	}];

	NSDictionary *_command = @{@"/sclicense/add-license": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteLicenseWithLicenseId:(long long)licenseId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"licenseId": @(licenseId)
	}];

	NSDictionary *_command = @{@"/sclicense/delete-license": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getLicenseWithLicenseId:(long long)licenseId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"licenseId": @(licenseId)
	}];

	NSDictionary *_command = @{@"/sclicense/get-license": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLicenseWithLicenseId:(long long)licenseId name:(NSString *)name url:(NSString *)url openSource:(BOOL)openSource active:(BOOL)active recommended:(BOOL)recommended error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"licenseId": @(licenseId),
		@"name": [self checkNull: name],
		@"url": [self checkNull: url],
		@"openSource": @(openSource),
		@"active": @(active),
		@"recommended": @(recommended)
	}];

	NSDictionary *_command = @{@"/sclicense/update-license": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
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

#import "LRAppService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRAppService_v7

- (NSDictionary *)deleteAppWithAppId:(long long)appId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"appId": @(appId)
	}];

	NSDictionary *_command = @{@"/marketplace.app/delete-app": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)installAppWithRemoteAppId:(long long)remoteAppId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"remoteAppId": @(remoteAppId)
	}];

	NSDictionary *_command = @{@"/marketplace.app/install-app": _params};

	[self.session invoke:_command error:error];
}

- (void)uninstallAppWithRemoteAppId:(long long)remoteAppId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"remoteAppId": @(remoteAppId)
	}];

	NSDictionary *_command = @{@"/marketplace.app/uninstall-app": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateAppWithFile:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/marketplace.app/update-app": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

@end
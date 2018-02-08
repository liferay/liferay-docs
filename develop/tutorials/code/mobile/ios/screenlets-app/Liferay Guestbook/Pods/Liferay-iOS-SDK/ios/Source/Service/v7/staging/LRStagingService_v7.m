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

#import "LRStagingService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRStagingService_v7

- (NSNumber *)createStagingRequestWithGroupId:(long long)groupId checksum:(NSString *)checksum error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"checksum": [self checkNull: checksum]
	}];

	NSDictionary *_command = @{@"/staging/create-staging-request": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)publishStagingRequestWithStagingRequestId:(long long)stagingRequestId exportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"stagingRequestId": @(stagingRequestId),
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/staging/publish-staging-request": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)publishStagingRequestWithStagingRequestId:(long long)stagingRequestId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"stagingRequestId": @(stagingRequestId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap]
	}];

	NSDictionary *_command = @{@"/staging/publish-staging-request": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)cleanUpStagingRequestWithStagingRequestId:(long long)stagingRequestId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"stagingRequestId": @(stagingRequestId)
	}];

	NSDictionary *_command = @{@"/staging/clean-up-staging-request": _params};

	[self.session invoke:_command error:error];
}

- (void)updateStagingRequestWithStagingRequestId:(long long)stagingRequestId fileName:(NSString *)fileName bytes:(NSData *)bytes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"stagingRequestId": @(stagingRequestId),
		@"fileName": [self checkNull: fileName],
		@"bytes": [self toString:bytes]
	}];

	NSDictionary *_command = @{@"/staging/update-staging-request": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)validateStagingRequestWithStagingRequestId:(long long)stagingRequestId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"stagingRequestId": @(stagingRequestId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap]
	}];

	NSDictionary *_command = @{@"/staging/validate-staging-request": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
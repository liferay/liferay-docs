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

#import "LRBaseService.h"

/**
 * @author Bruno Farache
 */
@interface LRStagingService_v62 : LRBaseService

- (void)cleanUpStagingRequestWithStagingRequestId:(long long)stagingRequestId error:(NSError **)error;
- (NSNumber *)createStagingRequestWithGroupId:(long long)groupId checksum:(NSString *)checksum error:(NSError **)error;
- (void)publishStagingRequestWithStagingRequestId:(long long)stagingRequestId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap error:(NSError **)error;
- (void)updateStagingRequestWithStagingRequestId:(long long)stagingRequestId fileName:(NSString *)fileName bytes:(NSData *)bytes error:(NSError **)error;
- (NSDictionary *)validateStagingRequestWithStagingRequestId:(long long)stagingRequestId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap error:(NSError **)error;

@end
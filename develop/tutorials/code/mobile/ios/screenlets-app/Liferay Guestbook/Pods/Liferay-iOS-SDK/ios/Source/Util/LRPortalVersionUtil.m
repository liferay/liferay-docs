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

#import "LRPortalVersionUtil.h"

#import "LRError.h"
#import "LRHttpUtil.h"
#import "LRPortalService_v7.h"

const int LR_UNKNOWN_VERSION = -1;
const int LR_VERSION_6_2 = 6200;
const int LR_VERSION_7 = 7000;

/**
 * @author Bruno Farache
 */
@implementation LRPortalVersionUtil

+ (int)getPortalVersion:(LRSession *)session error:(NSError **)error {
	int version = [self _getBuilderNumberHeader:session.server error:error];

	if (*error) {
		return LR_UNKNOWN_VERSION;
	}

	if (version == LR_UNKNOWN_VERSION) {
		NSError *buildNumberError;

		version = [self _getBuildNumber:session jsonWSPath:LR_JSONWS_PATH_V62
			error:&buildNumberError];

		if (buildNumberError.code == LRErrorCodeRedirect) {
			*error = buildNumberError;

			return LR_UNKNOWN_VERSION;
		}

		if (version == LR_UNKNOWN_VERSION) {
			buildNumberError = nil;

			version = [self _getBuildNumber:session
				jsonWSPath:LR_JSONWS_PATH_V61 error:&buildNumberError];

			if (buildNumberError) {
				*error = buildNumberError;

				return LR_UNKNOWN_VERSION;
			}
		}
	}

	return version;
}

#pragma mark - Private methods

+ (int)_getBuildNumber:(LRSession *)session jsonWSPath:(NSString *)jsonWSPath
		error:(NSError **)error {

	[LRHttpUtil setJSONWSPath:jsonWSPath];

	LRPortalService_v7 *service = [[LRPortalService_v7 alloc]
		initWithSession:session];

	int version = [[service getBuildNumber:error] intValue];

	[LRHttpUtil setJSONWSPath:LR_JSONWS_PATH_V62];

	if (*error) {
		return LR_UNKNOWN_VERSION;
	}

	return version;
}

+ (int)_getBuilderNumberHeader:(NSString *)URL error:(NSError **)error {
	NSMutableURLRequest *request = [[NSMutableURLRequest alloc]
		initWithURL:[NSURL URLWithString:URL]];

	[request setHTTPMethod:LR_HEAD];

	NSHTTPURLResponse *response;

	[NSURLConnection sendSynchronousRequest:request returningResponse:&response
		error:error];

	if (*error) {
		return LR_UNKNOWN_VERSION;
	}

	NSDictionary *headers = [response allHeaderFields];
	NSString *portalHeader = [headers objectForKey:@"Liferay-Portal"];

	if (!portalHeader) {
		return LR_UNKNOWN_VERSION;
	}

	NSRange buildRange;
	NSRange searchRange = NSMakeRange(0, [portalHeader length]);

	buildRange = [portalHeader rangeOfString:@"Build"
		options:NSCaseInsensitiveSearch range:searchRange];

	if (buildRange.location == NSNotFound) {
		return LR_UNKNOWN_VERSION;
	}
	else {
		long index = buildRange.location + buildRange.length;
		NSRange versionRange = NSMakeRange(index, 5);
		NSString *buildNumber = [portalHeader substringWithRange:versionRange];

		return [buildNumber intValue];
	}
}

@end
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

#import "LRLocalizationUtil.h"
#import "LRSession.h"

static NSBundle *_appBundle;
static NSBundle *_sdkBundle;

/**
 * @author Bruno Farache
 */
@implementation LRLocalizationUtil

+ (void)initialize {
	if (!_appBundle) {
		_appBundle = [NSBundle bundleForClass:[LRSession class]];

		NSString *path = [_appBundle pathForResource:@"Liferay-iOS-SDK"
			ofType:@"bundle"];

		_sdkBundle = [NSBundle bundleWithPath:path];
	}
}

+ (NSString *)localize:(NSString *)key {
	NSString *localizedString = [_appBundle localizedStringForKey:key value:key
		table:nil];

	if (![localizedString isEqualToString:key]) {
		return localizedString;
	}

	if (_sdkBundle) {
		return [_sdkBundle localizedStringForKey:key value:key table:nil];
	}

	return key;
}

@end
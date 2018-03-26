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

#import "LRSession.h"

/**
 * @author Bruno Farache
 * @author Josiane Bezerra
 */
@interface LRPortraitUtil : NSObject

+ (NSString *)downloadPortrait:(LRSession *)session
	portraitURL:(NSString *)portraitURL data:(NSData **)data
	error:(NSError **)error;

+ (NSString *)downloadPortrait:(LRSession *)session
	portraitURL:(NSString *)portraitURL data:(NSData **)data
	modifiedDate:(NSString *)modifiedDate error:(NSError **)error;

+ (NSString *)getPortraitURL:(LRSession *)session male:(BOOL)male
	portraitId:(long long)portraitId uuid:(NSString *)uuid;

@end
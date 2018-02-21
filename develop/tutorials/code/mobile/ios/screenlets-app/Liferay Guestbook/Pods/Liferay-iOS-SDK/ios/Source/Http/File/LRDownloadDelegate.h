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

#import "LRAuthentication.h"
#import "LRFileProgressDelegate.h"

/**
 * @author Bruno Farache
 */
@interface LRDownloadDelegate : NSObject <NSURLConnectionDelegate>

@property (nonatomic, strong) id<LRAuthentication> auth;
@property (nonatomic, strong) NSOutputStream *outputStream;
@property (nonatomic) long long totalBytes;
@property (nonatomic, strong) id<LRFileProgressDelegate> progressDelegate;

- (id)initWithAuth:(id<LRAuthentication>)auth
	outputStream:(NSOutputStream *)outputStream
	progressDelegate:(id<LRFileProgressDelegate>)progressDelegate;

@end
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

#import <Foundation/Foundation.h>

#import "LRJSONObjectWrapper.h"
#import "LRUploadData.h"
#import "LRSession.h"

/**
 * @author Bruno Farache
 */
@interface LRBaseService : NSObject

@property (nonatomic, strong) LRSession *session;

- (id)initWithSession:(LRSession *)session;
- (BOOL)boolValue:(NSNumber *)number;
- (id)checkNull:(id)object;
- (void)mangleWrapperWithParams:(NSMutableDictionary *)params
	name:(NSString *)name className:(NSString *)className
	wrapper:(LRJSONObjectWrapper *)wrapper;

- (NSString *)toString:(NSData *)data;

@end
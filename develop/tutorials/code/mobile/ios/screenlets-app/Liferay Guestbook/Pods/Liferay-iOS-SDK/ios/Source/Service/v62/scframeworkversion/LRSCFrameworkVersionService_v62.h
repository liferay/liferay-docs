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
@interface LRSCFrameworkVersionService_v62 : LRBaseService

- (NSDictionary *)addFrameworkVersionWithName:(NSString *)name url:(NSString *)url active:(BOOL)active priority:(int)priority serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFrameworkVersionWithFrameworkVersionId:(long long)frameworkVersionId error:(NSError **)error;
- (NSDictionary *)getFrameworkVersionWithFrameworkVersionId:(long long)frameworkVersionId error:(NSError **)error;
- (NSArray *)getFrameworkVersionsWithGroupId:(long long)groupId active:(BOOL)active error:(NSError **)error;
- (NSArray *)getFrameworkVersionsWithGroupId:(long long)groupId active:(BOOL)active start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)updateFrameworkVersionWithFrameworkVersionId:(long long)frameworkVersionId name:(NSString *)name url:(NSString *)url active:(BOOL)active priority:(int)priority error:(NSError **)error;

@end
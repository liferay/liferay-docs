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
@interface LRSocialActivitySettingService_v62 : LRBaseService

- (NSDictionary *)getActivityDefinitionWithGroupId:(long long)groupId className:(NSString *)className activityType:(int)activityType error:(NSError **)error;
- (NSArray *)getActivityDefinitionsWithGroupId:(long long)groupId className:(NSString *)className error:(NSError **)error;
- (NSArray *)getActivitySettingsWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getJsonActivityDefinitionsWithGroupId:(long long)groupId className:(NSString *)className error:(NSError **)error;
- (void)updateActivitySettingWithGroupId:(long long)groupId className:(NSString *)className enabled:(BOOL)enabled error:(NSError **)error;
- (void)updateActivitySettingWithGroupId:(long long)groupId className:(NSString *)className activityType:(int)activityType activityCounterDefinition:(LRJSONObjectWrapper *)activityCounterDefinition error:(NSError **)error;
- (void)updateActivitySettingsWithGroupId:(long long)groupId className:(NSString *)className activityType:(int)activityType activityCounterDefinitions:(NSArray *)activityCounterDefinitions error:(NSError **)error;

@end
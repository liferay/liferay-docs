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
@interface LRSocialActivityService_v62 : LRBaseService

- (NSArray *)getActivitiesWithClassName:(NSString *)className start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getActivitiesWithClassNameId:(long long)classNameId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getActivitiesWithMirrorActivityId:(long long)mirrorActivityId className:(NSString *)className classPK:(long long)classPK start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getActivitiesWithMirrorActivityId:(long long)mirrorActivityId classNameId:(long long)classNameId classPK:(long long)classPK start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getActivitiesCountWithClassName:(NSString *)className error:(NSError **)error;
- (NSNumber *)getActivitiesCountWithClassNameId:(long long)classNameId error:(NSError **)error;
- (NSNumber *)getActivitiesCountWithMirrorActivityId:(long long)mirrorActivityId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSNumber *)getActivitiesCountWithMirrorActivityId:(long long)mirrorActivityId classNameId:(long long)classNameId classPK:(long long)classPK error:(NSError **)error;
- (NSDictionary *)getActivityWithActivityId:(long long)activityId error:(NSError **)error;
- (NSArray *)getActivitySetActivitiesWithActivitySetId:(long long)activitySetId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupActivitiesWithGroupId:(long long)groupId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getGroupActivitiesCountWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getGroupUsersActivitiesWithGroupId:(long long)groupId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getGroupUsersActivitiesCountWithGroupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)getMirrorActivityWithMirrorActivityId:(long long)mirrorActivityId error:(NSError **)error;
- (NSArray *)getOrganizationActivitiesWithOrganizationId:(long long)organizationId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getOrganizationActivitiesCountWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (NSArray *)getOrganizationUsersActivitiesWithOrganizationId:(long long)organizationId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getOrganizationUsersActivitiesCountWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (NSArray *)getRelationActivitiesWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getRelationActivitiesWithUserId:(long long)userId type:(int)type start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getRelationActivitiesCountWithUserId:(long long)userId error:(NSError **)error;
- (NSNumber *)getRelationActivitiesCountWithUserId:(long long)userId type:(int)type error:(NSError **)error;
- (NSArray *)getUserActivitiesWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getUserActivitiesCountWithUserId:(long long)userId error:(NSError **)error;
- (NSArray *)getUserGroupsActivitiesWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getUserGroupsActivitiesCountWithUserId:(long long)userId error:(NSError **)error;
- (NSArray *)getUserGroupsAndOrganizationsActivitiesWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getUserGroupsAndOrganizationsActivitiesCountWithUserId:(long long)userId error:(NSError **)error;
- (NSArray *)getUserOrganizationsActivitiesWithUserId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getUserOrganizationsActivitiesCountWithUserId:(long long)userId error:(NSError **)error;

@end
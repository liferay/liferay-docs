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
@interface LRTeamService_v62 : LRBaseService

- (NSDictionary *)addTeamWithGroupId:(long long)groupId name:(NSString *)name description:(NSString *)description error:(NSError **)error;
- (void)deleteTeamWithTeamId:(long long)teamId error:(NSError **)error;
- (NSArray *)getGroupTeamsWithGroupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)getTeamWithTeamId:(long long)teamId error:(NSError **)error;
- (NSDictionary *)getTeamWithGroupId:(long long)groupId name:(NSString *)name error:(NSError **)error;
- (NSArray *)getUserTeamsWithUserId:(long long)userId error:(NSError **)error;
- (NSArray *)getUserTeamsWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error;
- (BOOL)hasUserTeamWithUserId:(long long)userId teamId:(long long)teamId error:(NSError **)error;
- (NSDictionary *)updateTeamWithTeamId:(long long)teamId name:(NSString *)name description:(NSString *)description error:(NSError **)error;

@end
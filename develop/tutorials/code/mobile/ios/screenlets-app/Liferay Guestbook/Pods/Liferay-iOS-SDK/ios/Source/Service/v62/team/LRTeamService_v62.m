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

#import "LRTeamService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRTeamService_v62

- (NSDictionary *)addTeamWithGroupId:(long long)groupId name:(NSString *)name description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/team/add-team": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteTeamWithTeamId:(long long)teamId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"teamId": @(teamId)
	}];

	NSDictionary *_command = @{@"/team/delete-team": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getGroupTeamsWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/team/get-group-teams": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTeamWithTeamId:(long long)teamId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"teamId": @(teamId)
	}];

	NSDictionary *_command = @{@"/team/get-team": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTeamWithGroupId:(long long)groupId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/team/get-team": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserTeamsWithUserId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/team/get-user-teams": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getUserTeamsWithUserId:(long long)userId groupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/team/get-user-teams": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (BOOL)hasUserTeamWithUserId:(long long)userId teamId:(long long)teamId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"teamId": @(teamId)
	}];

	NSDictionary *_command = @{@"/team/has-user-team": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (NSDictionary *)updateTeamWithTeamId:(long long)teamId name:(NSString *)name description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"teamId": @(teamId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/team/update-team": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
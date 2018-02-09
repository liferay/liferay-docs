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

#import "LRBackgroundTaskService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRBackgroundTaskService_v7

- (NSNumber *)getBackgroundTasksCountWithGroupId:(long long)groupId taskExecutorClassName:(NSString *)taskExecutorClassName completed:(NSString *)completed error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"taskExecutorClassName": [self checkNull: taskExecutorClassName],
		@"completed": [self checkNull: completed]
	}];

	NSDictionary *_command = @{@"/backgroundtask.backgroundtask/get-background-tasks-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSString *)getBackgroundTaskStatusJsonWithBackgroundTaskId:(long long)backgroundTaskId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"backgroundTaskId": @(backgroundTaskId)
	}];

	NSDictionary *_command = @{@"/backgroundtask.backgroundtask/get-background-task-status-json": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

@end
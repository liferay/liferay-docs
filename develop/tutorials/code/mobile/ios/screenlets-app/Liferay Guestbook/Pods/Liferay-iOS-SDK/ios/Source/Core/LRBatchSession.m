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

#import "LRHttpUtil.h"
#import "LRBatchSession.h"

/**
 * @author Bruno Farache
 */

@implementation LRBatchSession

@synthesize commands = _commands;

- (id)initWithServer:(NSString *)server
		authentication:(id<LRAuthentication>)authentication
		connectionTimeout:(int)connectionTimeout
		callback:(id<LRCallback>)callback queue:(NSOperationQueue *)queue {

	self = [super initWithServer:server authentication:authentication
		connectionTimeout:connectionTimeout callback:callback queue:queue];

	if (self) {
		_commands = [[NSMutableArray alloc] init];
	}

	return self;
}

- (NSArray *)commands {
	return [NSArray arrayWithArray:_commands];
}

- (NSArray *)invoke:(NSError **)error {
	if ([_commands count] == 0) {
		return nil;
	}

	NSArray *results = [LRHttpUtil post:self commands:self.commands
		error:error];

	_commands = [[NSMutableArray alloc] init];

	return results;
}

- (NSArray *)invoke:(NSDictionary *)command error:(NSError **)error {
	[(NSMutableArray *)_commands addObject:command];

	return nil;
}

- (NSOperation *)upload:(NSDictionary *)command error:(NSError **)error {
	[NSException raise:@"" format:@"Can't batch upload requests"];

	return nil;
}

@end
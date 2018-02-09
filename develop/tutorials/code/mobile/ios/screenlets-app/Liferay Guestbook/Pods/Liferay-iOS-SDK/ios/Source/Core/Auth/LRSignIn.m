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

#import "LRSignIn.h"

#import "LRBasicAuthentication.h"
#import "LRError.h"
#import "LRGroupService_v7.h"
#import "LRUserService_v7.h"
#import "LRValidator.h"

/**
 * @author Bruno Farache
 */
@implementation LRSignIn

+ (void)signInWithSession:(LRSession *)session callback:(id<LRCallback>)callback
		error:(NSError **)error {

	LRSignInMethod method = LRSignInMethodScreenName;

	NSString *username = [self _getUsername:session];

	if ([LRValidator isEmailAddress:username]) {
		method = LRSignInMethodEmail;
	}
	else if ([username intValue] != 0) {
		method = LRSignInMethodUserID;
	}

	[self signInWithSession:session callback:callback method:method
		error:error];
}

+ (void)signInWithSession:(LRSession *)session callback:(id<LRCallback>)callback
		method:(LRSignInMethod)method error:(NSError **)error {

	LRGroupService_v7 *service = [[LRGroupService_v7 alloc]
		initWithSession:session];

	[session
		onSuccess:^(NSArray *groups) {
			if ([groups count] == 0) {
				*error = [LRError errorWithCode:LRErrorCodeUnauthorized
					description:@"User doesn't belong to any site"];

				return;
			}

			NSDictionary *site = [groups objectAtIndex:0];
			long long companyId = [site[@"companyId"] longLongValue];

			LRSession *userSession = [[LRSession alloc]
				initWithSession:session];

			[userSession setCallback:callback];

			LRUserService_v7 *userService = [[LRUserService_v7 alloc]
				initWithSession:userSession];

			NSString *username = [self _getUsername:session];

			if (method == LRSignInMethodEmail) {
				[userService getUserByEmailAddressWithCompanyId:companyId
				   emailAddress:username error:error];
			}
			else if (method == LRSignInMethodUserID) {
				long long userId = [username longLongValue];

				[userService getUserByIdWithUserId:userId error:error];
			}
			else if (method == LRSignInMethodScreenName) {
				[userService getUserByScreenNameWithCompanyId:companyId
					screenName:username error:error];
			}
		}
		onFailure:^(NSError *e) {
			[callback onFailure:e];
		}
	 ];

	[service getUserSitesGroups:error];
}

+ (NSString *)_getUsername:(LRSession *)session {
	id<LRAuthentication> authentication = session.authentication;

	if (!authentication) {
		[NSException raise:@"" format:@"Session authentication can't be null"];
	}

	if (![authentication isKindOfClass:[LRBasicAuthentication class]]) {
		[NSException raise:@"" format:@"Can't sign in if authentication " \
			"implementation is not BasicAuthentication"];
	}

	return ((LRBasicAuthentication *)authentication).username;
}

@end
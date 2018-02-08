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

#import "LRPasswordPolicyService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRPasswordPolicyService_v7

- (NSDictionary *)addPasswordPolicyWithName:(NSString *)name description:(NSString *)description changeable:(BOOL)changeable changeRequired:(BOOL)changeRequired minAge:(long long)minAge checkSyntax:(BOOL)checkSyntax allowDictionaryWords:(BOOL)allowDictionaryWords minAlphanumeric:(int)minAlphanumeric minLength:(int)minLength minLowerCase:(int)minLowerCase minNumbers:(int)minNumbers minSymbols:(int)minSymbols minUpperCase:(int)minUpperCase regex:(NSString *)regex history:(BOOL)history historyCount:(int)historyCount expireable:(BOOL)expireable maxAge:(long long)maxAge warningTime:(long long)warningTime graceLimit:(int)graceLimit lockout:(BOOL)lockout maxFailure:(int)maxFailure lockoutDuration:(long long)lockoutDuration resetFailureCount:(long long)resetFailureCount resetTicketMaxAge:(long long)resetTicketMaxAge serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"changeable": @(changeable),
		@"changeRequired": @(changeRequired),
		@"minAge": @(minAge),
		@"checkSyntax": @(checkSyntax),
		@"allowDictionaryWords": @(allowDictionaryWords),
		@"minAlphanumeric": @(minAlphanumeric),
		@"minLength": @(minLength),
		@"minLowerCase": @(minLowerCase),
		@"minNumbers": @(minNumbers),
		@"minSymbols": @(minSymbols),
		@"minUpperCase": @(minUpperCase),
		@"regex": [self checkNull: regex],
		@"history": @(history),
		@"historyCount": @(historyCount),
		@"expireable": @(expireable),
		@"maxAge": @(maxAge),
		@"warningTime": @(warningTime),
		@"graceLimit": @(graceLimit),
		@"lockout": @(lockout),
		@"maxFailure": @(maxFailure),
		@"lockoutDuration": @(lockoutDuration),
		@"resetFailureCount": @(resetFailureCount),
		@"resetTicketMaxAge": @(resetTicketMaxAge),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/passwordpolicy/add-password-policy": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePasswordPolicyWithPasswordPolicyId:(long long)passwordPolicyId name:(NSString *)name description:(NSString *)description changeable:(BOOL)changeable changeRequired:(BOOL)changeRequired minAge:(long long)minAge checkSyntax:(BOOL)checkSyntax allowDictionaryWords:(BOOL)allowDictionaryWords minAlphanumeric:(int)minAlphanumeric minLength:(int)minLength minLowerCase:(int)minLowerCase minNumbers:(int)minNumbers minSymbols:(int)minSymbols minUpperCase:(int)minUpperCase regex:(NSString *)regex history:(BOOL)history historyCount:(int)historyCount expireable:(BOOL)expireable maxAge:(long long)maxAge warningTime:(long long)warningTime graceLimit:(int)graceLimit lockout:(BOOL)lockout maxFailure:(int)maxFailure lockoutDuration:(long long)lockoutDuration resetFailureCount:(long long)resetFailureCount resetTicketMaxAge:(long long)resetTicketMaxAge serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"changeable": @(changeable),
		@"changeRequired": @(changeRequired),
		@"minAge": @(minAge),
		@"checkSyntax": @(checkSyntax),
		@"allowDictionaryWords": @(allowDictionaryWords),
		@"minAlphanumeric": @(minAlphanumeric),
		@"minLength": @(minLength),
		@"minLowerCase": @(minLowerCase),
		@"minNumbers": @(minNumbers),
		@"minSymbols": @(minSymbols),
		@"minUpperCase": @(minUpperCase),
		@"regex": [self checkNull: regex],
		@"history": @(history),
		@"historyCount": @(historyCount),
		@"expireable": @(expireable),
		@"maxAge": @(maxAge),
		@"warningTime": @(warningTime),
		@"graceLimit": @(graceLimit),
		@"lockout": @(lockout),
		@"maxFailure": @(maxFailure),
		@"lockoutDuration": @(lockoutDuration),
		@"resetFailureCount": @(resetFailureCount),
		@"resetTicketMaxAge": @(resetTicketMaxAge),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/passwordpolicy/update-password-policy": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deletePasswordPolicyWithPasswordPolicyId:(long long)passwordPolicyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId)
	}];

	NSDictionary *_command = @{@"/passwordpolicy/delete-password-policy": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchPasswordPolicyWithPasswordPolicyId:(long long)passwordPolicyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"passwordPolicyId": @(passwordPolicyId)
	}];

	NSDictionary *_command = @{@"/passwordpolicy/fetch-password-policy": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
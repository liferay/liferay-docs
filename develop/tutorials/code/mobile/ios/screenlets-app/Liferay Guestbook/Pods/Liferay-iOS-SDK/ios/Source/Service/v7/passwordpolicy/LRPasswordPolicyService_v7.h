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
@interface LRPasswordPolicyService_v7 : LRBaseService

- (NSDictionary *)addPasswordPolicyWithName:(NSString *)name description:(NSString *)description changeable:(BOOL)changeable changeRequired:(BOOL)changeRequired minAge:(long long)minAge checkSyntax:(BOOL)checkSyntax allowDictionaryWords:(BOOL)allowDictionaryWords minAlphanumeric:(int)minAlphanumeric minLength:(int)minLength minLowerCase:(int)minLowerCase minNumbers:(int)minNumbers minSymbols:(int)minSymbols minUpperCase:(int)minUpperCase regex:(NSString *)regex history:(BOOL)history historyCount:(int)historyCount expireable:(BOOL)expireable maxAge:(long long)maxAge warningTime:(long long)warningTime graceLimit:(int)graceLimit lockout:(BOOL)lockout maxFailure:(int)maxFailure lockoutDuration:(long long)lockoutDuration resetFailureCount:(long long)resetFailureCount resetTicketMaxAge:(long long)resetTicketMaxAge serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updatePasswordPolicyWithPasswordPolicyId:(long long)passwordPolicyId name:(NSString *)name description:(NSString *)description changeable:(BOOL)changeable changeRequired:(BOOL)changeRequired minAge:(long long)minAge checkSyntax:(BOOL)checkSyntax allowDictionaryWords:(BOOL)allowDictionaryWords minAlphanumeric:(int)minAlphanumeric minLength:(int)minLength minLowerCase:(int)minLowerCase minNumbers:(int)minNumbers minSymbols:(int)minSymbols minUpperCase:(int)minUpperCase regex:(NSString *)regex history:(BOOL)history historyCount:(int)historyCount expireable:(BOOL)expireable maxAge:(long long)maxAge warningTime:(long long)warningTime graceLimit:(int)graceLimit lockout:(BOOL)lockout maxFailure:(int)maxFailure lockoutDuration:(long long)lockoutDuration resetFailureCount:(long long)resetFailureCount resetTicketMaxAge:(long long)resetTicketMaxAge serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deletePasswordPolicyWithPasswordPolicyId:(long long)passwordPolicyId error:(NSError **)error;
- (NSDictionary *)fetchPasswordPolicyWithPasswordPolicyId:(long long)passwordPolicyId error:(NSError **)error;

@end
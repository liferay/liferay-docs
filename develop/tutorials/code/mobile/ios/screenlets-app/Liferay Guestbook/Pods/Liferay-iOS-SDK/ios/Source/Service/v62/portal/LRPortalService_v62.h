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
@interface LRPortalService_v62 : LRBaseService

- (NSString *)getAutoDeployDirectory:(NSError **)error;
- (NSNumber *)getBuildNumber:(NSError **)error;
- (void)testAddClassNameAndTestTransactionPortletBar_PortalRollbackWithTransactionPortletBarText:(NSString *)transactionPortletBarText error:(NSError **)error;
- (void)testAddClassNameAndTestTransactionPortletBar_PortletRollbackWithTransactionPortletBarText:(NSString *)transactionPortletBarText error:(NSError **)error;
- (void)testAddClassNameAndTestTransactionPortletBar_SuccessWithTransactionPortletBarText:(NSString *)transactionPortletBarText error:(NSError **)error;
- (void)testAddClassName_RollbackWithClassNameValue:(NSString *)classNameValue error:(NSError **)error;
- (void)testAddClassName_SuccessWithClassNameValue:(NSString *)classNameValue error:(NSError **)error;
- (void)testAutoSyncHibernateSessionStateOnTxCreation:(NSError **)error;
- (void)testDeleteClassName:(NSError **)error;
- (NSNumber *)testGetBuildNumber:(NSError **)error;
- (void)testGetUserId:(NSError **)error;
- (BOOL)testHasClassName:(NSError **)error;

@end
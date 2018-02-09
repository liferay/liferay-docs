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

#import "LRPortalService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRPortalService_v62

- (NSString *)getAutoDeployDirectory:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/get-auto-deploy-directory": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSNumber *)getBuildNumber:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/get-build-number": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)testAddClassNameAndTestTransactionPortletBar_PortalRollbackWithTransactionPortletBarText:(NSString *)transactionPortletBarText error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"transactionPortletBarText": [self checkNull: transactionPortletBarText]
	}];

	NSDictionary *_command = @{@"/portal/test-add-class-name-and-test-transaction-portlet-bar_-portal-rollback": _params};

	[self.session invoke:_command error:error];
}

- (void)testAddClassNameAndTestTransactionPortletBar_PortletRollbackWithTransactionPortletBarText:(NSString *)transactionPortletBarText error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"transactionPortletBarText": [self checkNull: transactionPortletBarText]
	}];

	NSDictionary *_command = @{@"/portal/test-add-class-name-and-test-transaction-portlet-bar_-portlet-rollback": _params};

	[self.session invoke:_command error:error];
}

- (void)testAddClassNameAndTestTransactionPortletBar_SuccessWithTransactionPortletBarText:(NSString *)transactionPortletBarText error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"transactionPortletBarText": [self checkNull: transactionPortletBarText]
	}];

	NSDictionary *_command = @{@"/portal/test-add-class-name-and-test-transaction-portlet-bar_-success": _params};

	[self.session invoke:_command error:error];
}

- (void)testAddClassName_RollbackWithClassNameValue:(NSString *)classNameValue error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameValue": [self checkNull: classNameValue]
	}];

	NSDictionary *_command = @{@"/portal/test-add-class-name_-rollback": _params};

	[self.session invoke:_command error:error];
}

- (void)testAddClassName_SuccessWithClassNameValue:(NSString *)classNameValue error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameValue": [self checkNull: classNameValue]
	}];

	NSDictionary *_command = @{@"/portal/test-add-class-name_-success": _params};

	[self.session invoke:_command error:error];
}

- (void)testAutoSyncHibernateSessionStateOnTxCreation:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/test-auto-sync-hibernate-session-state-on-tx-creation": _params};

	[self.session invoke:_command error:error];
}

- (void)testDeleteClassName:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/test-delete-class-name": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)testGetBuildNumber:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/test-get-build-number": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)testGetUserId:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/test-get-user-id": _params};

	[self.session invoke:_command error:error];
}

- (BOOL)testHasClassName:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/portal/test-has-class-name": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

@end
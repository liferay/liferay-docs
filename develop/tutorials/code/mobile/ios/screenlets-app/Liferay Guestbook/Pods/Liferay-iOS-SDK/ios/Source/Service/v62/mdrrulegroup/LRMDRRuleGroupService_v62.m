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

#import "LRMDRRuleGroupService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRMDRRuleGroupService_v62

- (NSDictionary *)addRuleGroupWithGroupId:(long long)groupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mdrrulegroup/add-rule-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyRuleGroupWithRuleGroupId:(long long)ruleGroupId groupId:(long long)groupId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"ruleGroupId": @(ruleGroupId),
		@"groupId": @(groupId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mdrrulegroup/copy-rule-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteRuleGroupWithRuleGroupId:(long long)ruleGroupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"ruleGroupId": @(ruleGroupId)
	}];

	NSDictionary *_command = @{@"/mdrrulegroup/delete-rule-group": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchRuleGroupWithRuleGroupId:(long long)ruleGroupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"ruleGroupId": @(ruleGroupId)
	}];

	NSDictionary *_command = @{@"/mdrrulegroup/fetch-rule-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRuleGroupWithRuleGroupId:(long long)ruleGroupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"ruleGroupId": @(ruleGroupId)
	}];

	NSDictionary *_command = @{@"/mdrrulegroup/get-rule-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateRuleGroupWithRuleGroupId:(long long)ruleGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"ruleGroupId": @(ruleGroupId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mdrrulegroup/update-rule-group": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
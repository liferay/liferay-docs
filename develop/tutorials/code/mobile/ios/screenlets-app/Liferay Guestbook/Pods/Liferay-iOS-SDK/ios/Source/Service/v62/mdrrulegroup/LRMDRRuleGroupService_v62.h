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
@interface LRMDRRuleGroupService_v62 : LRBaseService

- (NSDictionary *)addRuleGroupWithGroupId:(long long)groupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)copyRuleGroupWithRuleGroupId:(long long)ruleGroupId groupId:(long long)groupId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteRuleGroupWithRuleGroupId:(long long)ruleGroupId error:(NSError **)error;
- (NSDictionary *)fetchRuleGroupWithRuleGroupId:(long long)ruleGroupId error:(NSError **)error;
- (NSDictionary *)getRuleGroupWithRuleGroupId:(long long)ruleGroupId error:(NSError **)error;
- (NSDictionary *)updateRuleGroupWithRuleGroupId:(long long)ruleGroupId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
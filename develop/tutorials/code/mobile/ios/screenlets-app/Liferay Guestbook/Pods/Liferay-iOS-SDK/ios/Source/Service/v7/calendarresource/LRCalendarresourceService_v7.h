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
@interface LRCalendarresourceService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds code:(NSString *)code name:(NSString *)name description:(NSString *)description active:(BOOL)active andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords active:(BOOL)active andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSDictionary *)getCalendarResourceWithCalendarResourceId:(long long)calendarResourceId error:(NSError **)error;
- (NSDictionary *)addCalendarResourceWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK classUuid:(NSString *)classUuid code:(NSString *)code nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateCalendarResourceWithCalendarResourceId:(long long)calendarResourceId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds code:(NSString *)code name:(NSString *)name description:(NSString *)description active:(BOOL)active andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords active:(BOOL)active error:(NSError **)error;
- (NSDictionary *)deleteCalendarResourceWithCalendarResourceId:(long long)calendarResourceId error:(NSError **)error;
- (NSDictionary *)fetchCalendarResourceWithClassNameId:(long long)classNameId classPK:(long long)classPK error:(NSError **)error;

@end
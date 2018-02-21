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
@interface LRCalendarService_v7 : LRBaseService

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator actionId:(NSString *)actionId error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error;
- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator actionId:(NSString *)actionId error:(NSError **)error;
- (NSDictionary *)getCalendarWithCalendarId:(long long)calendarId error:(NSError **)error;
- (NSArray *)getCalendarResourceCalendarsWithGroupId:(long long)groupId calendarResourceId:(long long)calendarResourceId defaultCalendar:(BOOL)defaultCalendar error:(NSError **)error;
- (NSArray *)getCalendarResourceCalendarsWithGroupId:(long long)groupId calendarResourceId:(long long)calendarResourceId error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator actionId:(NSString *)actionId error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator error:(NSError **)error;
- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator actionId:(NSString *)actionId error:(NSError **)error;
- (NSDictionary *)addCalendarWithGroupId:(long long)groupId calendarResourceId:(long long)calendarResourceId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap timeZoneId:(NSString *)timeZoneId color:(int)color defaultCalendar:(BOOL)defaultCalendar enableComments:(BOOL)enableComments enableRatings:(BOOL)enableRatings serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateCalendarWithCalendarId:(long long)calendarId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap color:(int)color serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateCalendarWithCalendarId:(long long)calendarId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap timeZoneId:(NSString *)timeZoneId color:(int)color defaultCalendar:(BOOL)defaultCalendar enableComments:(BOOL)enableComments enableRatings:(BOOL)enableRatings serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)deleteCalendarWithCalendarId:(long long)calendarId error:(NSError **)error;
- (BOOL)isManageableFromGroupWithCalendarId:(long long)calendarId groupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)fetchCalendarWithCalendarId:(long long)calendarId error:(NSError **)error;
- (NSString *)exportCalendarWithCalendarId:(long long)calendarId type:(NSString *)type error:(NSError **)error;
- (void)importCalendarWithCalendarId:(long long)calendarId data:(NSString *)data type:(NSString *)type error:(NSError **)error;
- (NSDictionary *)updateColorWithCalendarId:(long long)calendarId color:(int)color serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
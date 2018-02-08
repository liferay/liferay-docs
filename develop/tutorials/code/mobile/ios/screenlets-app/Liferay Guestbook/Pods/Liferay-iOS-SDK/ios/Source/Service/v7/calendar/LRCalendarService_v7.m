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

#import "LRCalendarService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRCalendarService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"keywords": [self checkNull: keywords],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/calendar.calendar/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"keywords": [self checkNull: keywords],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
		@"actionId": [self checkNull: actionId]
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar>" wrapper:orderByComparator];
	NSDictionary *_command = @{@"/calendar.calendar/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/calendar.calendar/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
		@"actionId": [self checkNull: actionId]
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.Calendar>" wrapper:orderByComparator];
	NSDictionary *_command = @{@"/calendar.calendar/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCalendarWithCalendarId:(long long)calendarId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/get-calendar": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getCalendarResourceCalendarsWithGroupId:(long long)groupId calendarResourceId:(long long)calendarResourceId defaultCalendar:(BOOL)defaultCalendar error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"calendarResourceId": @(calendarResourceId),
		@"defaultCalendar": @(defaultCalendar)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/get-calendar-resource-calendars": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCalendarResourceCalendarsWithGroupId:(long long)groupId calendarResourceId:(long long)calendarResourceId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"calendarResourceId": @(calendarResourceId)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/get-calendar-resource-calendars": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"keywords": [self checkNull: keywords],
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds keywords:(NSString *)keywords andOperator:(BOOL)andOperator actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"keywords": [self checkNull: keywords],
		@"andOperator": @(andOperator),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/calendar.calendar/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarResourceIds:(NSArray *)calendarResourceIds name:(NSString *)name description:(NSString *)description andOperator:(BOOL)andOperator actionId:(NSString *)actionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"andOperator": @(andOperator),
		@"actionId": [self checkNull: actionId]
	}];

	NSDictionary *_command = @{@"/calendar.calendar/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCalendarWithGroupId:(long long)groupId calendarResourceId:(long long)calendarResourceId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap timeZoneId:(NSString *)timeZoneId color:(int)color defaultCalendar:(BOOL)defaultCalendar enableComments:(BOOL)enableComments enableRatings:(BOOL)enableRatings serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"calendarResourceId": @(calendarResourceId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"timeZoneId": [self checkNull: timeZoneId],
		@"color": @(color),
		@"defaultCalendar": @(defaultCalendar),
		@"enableComments": @(enableComments),
		@"enableRatings": @(enableRatings),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendar/add-calendar": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarWithCalendarId:(long long)calendarId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap color:(int)color serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"color": @(color),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendar/update-calendar": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarWithCalendarId:(long long)calendarId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap timeZoneId:(NSString *)timeZoneId color:(int)color defaultCalendar:(BOOL)defaultCalendar enableComments:(BOOL)enableComments enableRatings:(BOOL)enableRatings serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"timeZoneId": [self checkNull: timeZoneId],
		@"color": @(color),
		@"defaultCalendar": @(defaultCalendar),
		@"enableComments": @(enableComments),
		@"enableRatings": @(enableRatings),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendar/update-calendar": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteCalendarWithCalendarId:(long long)calendarId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/delete-calendar": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (BOOL)isManageableFromGroupWithCalendarId:(long long)calendarId groupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/is-manageable-from-group": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (NSDictionary *)fetchCalendarWithCalendarId:(long long)calendarId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId)
	}];

	NSDictionary *_command = @{@"/calendar.calendar/fetch-calendar": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSString *)exportCalendarWithCalendarId:(long long)calendarId type:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/calendar.calendar/export-calendar": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (void)importCalendarWithCalendarId:(long long)calendarId data:(NSString *)data type:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"data": [self checkNull: data],
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/calendar.calendar/import-calendar": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateColorWithCalendarId:(long long)calendarId color:(int)color serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"color": @(color),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendar/update-color": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
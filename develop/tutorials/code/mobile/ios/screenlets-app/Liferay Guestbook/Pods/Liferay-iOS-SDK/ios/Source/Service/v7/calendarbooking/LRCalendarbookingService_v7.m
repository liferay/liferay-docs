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

#import "LRCalendarbookingService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRCalendarbookingService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarIds:(NSArray *)calendarIds calendarResourceIds:(NSArray *)calendarResourceIds parentCalendarBookingId:(long long)parentCalendarBookingId title:(NSString *)title description:(NSString *)description location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime recurring:(BOOL)recurring statuses:(NSArray *)statuses andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarIds": [self checkNull: calendarIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"recurring": @(recurring),
		@"statuses": [self checkNull: statuses],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/calendar.calendarbooking/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarIds:(NSArray *)calendarIds calendarResourceIds:(NSArray *)calendarResourceIds parentCalendarBookingId:(long long)parentCalendarBookingId keywords:(NSString *)keywords startTime:(long long)startTime endTime:(long long)endTime recurring:(BOOL)recurring statuses:(NSArray *)statuses start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarIds": [self checkNull: calendarIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"keywords": [self checkNull: keywords],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"recurring": @(recurring),
		@"statuses": [self checkNull: statuses],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarBooking>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/calendar.calendarbooking/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getChildCalendarBookingsWithParentCalendarBookingId:(long long)parentCalendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCalendarBookingId": @(parentCalendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-child-calendar-bookings": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getChildCalendarBookingsWithParentCalendarBookingId:(long long)parentCalendarBookingId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-child-calendar-bookings": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCalendarBookingWithCalendarId:(long long)calendarId parentCalendarBookingId:(long long)parentCalendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"parentCalendarBookingId": @(parentCalendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCalendarBookingWithCalendarBookingId:(long long)calendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCalendarBookingWithCalendarId:(long long)calendarId childCalendarIds:(NSArray *)childCalendarIds parentCalendarBookingId:(long long)parentCalendarBookingId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTimeYear:(int)startTimeYear startTimeMonth:(int)startTimeMonth startTimeDay:(int)startTimeDay startTimeHour:(int)startTimeHour startTimeMinute:(int)startTimeMinute endTimeYear:(int)endTimeYear endTimeMonth:(int)endTimeMonth endTimeDay:(int)endTimeDay endTimeHour:(int)endTimeHour endTimeMinute:(int)endTimeMinute timeZoneId:(NSString *)timeZoneId allDay:(BOOL)allDay recurrence:(NSString *)recurrence firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"childCalendarIds": [self checkNull: childCalendarIds],
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTimeYear": @(startTimeYear),
		@"startTimeMonth": @(startTimeMonth),
		@"startTimeDay": @(startTimeDay),
		@"startTimeHour": @(startTimeHour),
		@"startTimeMinute": @(startTimeMinute),
		@"endTimeYear": @(endTimeYear),
		@"endTimeMonth": @(endTimeMonth),
		@"endTimeDay": @(endTimeDay),
		@"endTimeHour": @(endTimeHour),
		@"endTimeMinute": @(endTimeMinute),
		@"timeZoneId": [self checkNull: timeZoneId],
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/add-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCalendarBookingWithCalendarId:(long long)calendarId childCalendarIds:(NSArray *)childCalendarIds parentCalendarBookingId:(long long)parentCalendarBookingId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime allDay:(BOOL)allDay recurrence:(NSString *)recurrence firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"childCalendarIds": [self checkNull: childCalendarIds],
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/add-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarBookingWithCalendarBookingId:(long long)calendarBookingId calendarId:(long long)calendarId childCalendarIds:(NSArray *)childCalendarIds titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime allDay:(BOOL)allDay recurrence:(NSString *)recurrence firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"calendarId": @(calendarId),
		@"childCalendarIds": [self checkNull: childCalendarIds],
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarBookingWithCalendarBookingId:(long long)calendarBookingId calendarId:(long long)calendarId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime allDay:(BOOL)allDay recurrence:(NSString *)recurrence firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"calendarId": @(calendarId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarIds:(NSArray *)calendarIds calendarResourceIds:(NSArray *)calendarResourceIds parentCalendarBookingId:(long long)parentCalendarBookingId title:(NSString *)title description:(NSString *)description location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime recurring:(BOOL)recurring statuses:(NSArray *)statuses andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarIds": [self checkNull: calendarIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"recurring": @(recurring),
		@"statuses": [self checkNull: statuses],
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds calendarIds:(NSArray *)calendarIds calendarResourceIds:(NSArray *)calendarResourceIds parentCalendarBookingId:(long long)parentCalendarBookingId keywords:(NSString *)keywords startTime:(long long)startTime endTime:(long long)endTime recurring:(BOOL)recurring statuses:(NSArray *)statuses error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"calendarIds": [self checkNull: calendarIds],
		@"calendarResourceIds": [self checkNull: calendarResourceIds],
		@"parentCalendarBookingId": @(parentCalendarBookingId),
		@"keywords": [self checkNull: keywords],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"recurring": @(recurring),
		@"statuses": [self checkNull: statuses]
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSString *)getCalendarBookingsRssWithCalendarId:(long long)calendarId startTime:(long long)startTime endTime:(long long)endTime max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-bookings-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getNewStartTimeAndDurationCalendarBookingWithCalendarBookingId:(long long)calendarBookingId offset:(long long)offset duration:(long long)duration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"offset": @(offset),
		@"duration": @(duration)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-new-start-time-and-duration-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (BOOL)hasChildCalendarBookingsWithParentCalendarBookingId:(long long)parentCalendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCalendarBookingId": @(parentCalendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/has-child-calendar-bookings": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (void)invokeTransitionWithCalendarBookingId:(long long)calendarBookingId status:(int)status serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"status": @(status),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/invoke-transition": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOffsetAndDurationWithCalendarBookingId:(long long)calendarBookingId calendarId:(long long)calendarId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location offset:(long long)offset duration:(long long)duration allDay:(BOOL)allDay recurrence:(NSString *)recurrence firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"calendarId": @(calendarId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"offset": @(offset),
		@"duration": @(duration),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-offset-and-duration": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOffsetAndDurationWithCalendarBookingId:(long long)calendarBookingId calendarId:(long long)calendarId childCalendarIds:(NSArray *)childCalendarIds titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location offset:(long long)offset duration:(long long)duration allDay:(BOOL)allDay recurrence:(NSString *)recurrence firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"calendarId": @(calendarId),
		@"childCalendarIds": [self checkNull: childCalendarIds],
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"offset": @(offset),
		@"duration": @(duration),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-offset-and-duration": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteCalendarBookingWithCalendarBookingId:(long long)calendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/delete-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteCalendarBookingInstanceWithCalendarBookingId:(long long)calendarBookingId instanceIndex:(int)instanceIndex allFollowing:(BOOL)allFollowing error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"instanceIndex": @(instanceIndex),
		@"allFollowing": @(allFollowing)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/delete-calendar-booking-instance": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteCalendarBookingInstanceWithCalendarBookingId:(long long)calendarBookingId startTime:(long long)startTime allFollowing:(BOOL)allFollowing error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"startTime": @(startTime),
		@"allFollowing": @(allFollowing)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/delete-calendar-booking-instance": _params};

	[self.session invoke:_command error:error];
}

- (NSString *)exportCalendarBookingWithCalendarBookingId:(long long)calendarBookingId type:(NSString *)type error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"type": [self checkNull: type]
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/export-calendar-booking": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchCalendarBookingWithCalendarBookingId:(long long)calendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/fetch-calendar-booking": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCalendarBookingInstanceWithCalendarBookingId:(long long)calendarBookingId instanceIndex:(int)instanceIndex error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"instanceIndex": @(instanceIndex)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-booking-instance": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getCalendarBookingsWithCalendarId:(long long)calendarId startTime:(long long)startTime endTime:(long long)endTime max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-bookings": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCalendarBookingsWithCalendarId:(long long)calendarId startTime:(long long)startTime endTime:(long long)endTime error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"startTime": @(startTime),
		@"endTime": @(endTime)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-bookings": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCalendarBookingsWithCalendarId:(long long)calendarId statuses:(NSArray *)statuses error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"statuses": [self checkNull: statuses]
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/get-calendar-bookings": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveCalendarBookingToTrashWithCalendarBookingId:(long long)calendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/move-calendar-booking-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)restoreCalendarBookingFromTrashWithCalendarBookingId:(long long)calendarBookingId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarbooking/restore-calendar-booking-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarBookingInstanceWithCalendarBookingId:(long long)calendarBookingId instanceIndex:(int)instanceIndex calendarId:(long long)calendarId childCalendarIds:(NSArray *)childCalendarIds titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime allDay:(BOOL)allDay recurrence:(NSString *)recurrence allFollowing:(BOOL)allFollowing firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"instanceIndex": @(instanceIndex),
		@"calendarId": @(calendarId),
		@"childCalendarIds": [self checkNull: childCalendarIds],
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"allFollowing": @(allFollowing),
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-calendar-booking-instance": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarBookingInstanceWithCalendarBookingId:(long long)calendarBookingId instanceIndex:(int)instanceIndex calendarId:(long long)calendarId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTime:(long long)startTime endTime:(long long)endTime allDay:(BOOL)allDay recurrence:(NSString *)recurrence allFollowing:(BOOL)allFollowing firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"instanceIndex": @(instanceIndex),
		@"calendarId": @(calendarId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTime": @(startTime),
		@"endTime": @(endTime),
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"allFollowing": @(allFollowing),
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-calendar-booking-instance": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarBookingInstanceWithCalendarBookingId:(long long)calendarBookingId instanceIndex:(int)instanceIndex calendarId:(long long)calendarId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap location:(NSString *)location startTimeYear:(int)startTimeYear startTimeMonth:(int)startTimeMonth startTimeDay:(int)startTimeDay startTimeHour:(int)startTimeHour startTimeMinute:(int)startTimeMinute endTimeYear:(int)endTimeYear endTimeMonth:(int)endTimeMonth endTimeDay:(int)endTimeDay endTimeHour:(int)endTimeHour endTimeMinute:(int)endTimeMinute timeZoneId:(NSString *)timeZoneId allDay:(BOOL)allDay recurrence:(NSString *)recurrence allFollowing:(BOOL)allFollowing firstReminder:(long long)firstReminder firstReminderType:(NSString *)firstReminderType secondReminder:(long long)secondReminder secondReminderType:(NSString *)secondReminderType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarBookingId": @(calendarBookingId),
		@"instanceIndex": @(instanceIndex),
		@"calendarId": @(calendarId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"location": [self checkNull: location],
		@"startTimeYear": @(startTimeYear),
		@"startTimeMonth": @(startTimeMonth),
		@"startTimeDay": @(startTimeDay),
		@"startTimeHour": @(startTimeHour),
		@"startTimeMinute": @(startTimeMinute),
		@"endTimeYear": @(endTimeYear),
		@"endTimeMonth": @(endTimeMonth),
		@"endTimeDay": @(endTimeDay),
		@"endTimeHour": @(endTimeHour),
		@"endTimeMinute": @(endTimeMinute),
		@"timeZoneId": [self checkNull: timeZoneId],
		@"allDay": @(allDay),
		@"recurrence": [self checkNull: recurrence],
		@"allFollowing": @(allFollowing),
		@"firstReminder": @(firstReminder),
		@"firstReminderType": [self checkNull: firstReminderType],
		@"secondReminder": @(secondReminder),
		@"secondReminderType": [self checkNull: secondReminderType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarbooking/update-calendar-booking-instance": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
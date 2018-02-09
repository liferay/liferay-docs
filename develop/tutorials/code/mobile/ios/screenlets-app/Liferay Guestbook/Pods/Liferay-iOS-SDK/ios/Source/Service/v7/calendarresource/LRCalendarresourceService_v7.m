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

#import "LRCalendarresourceService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRCalendarresourceService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds code:(NSString *)code name:(NSString *)name description:(NSString *)description active:(BOOL)active andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"code": [self checkNull: code],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"active": @(active),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/calendar.calendarresource/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords active:(BOOL)active andOperator:(BOOL)andOperator start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"keywords": [self checkNull: keywords],
		@"active": @(active),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.calendar.model.CalendarResource>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/calendar.calendarresource/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCalendarResourceWithCalendarResourceId:(long long)calendarResourceId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarResourceId": @(calendarResourceId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarresource/get-calendar-resource": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCalendarResourceWithGroupId:(long long)groupId classNameId:(long long)classNameId classPK:(long long)classPK classUuid:(NSString *)classUuid code:(NSString *)code nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"classUuid": [self checkNull: classUuid],
		@"code": [self checkNull: code],
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarresource/add-calendar-resource": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarResourceWithCalendarResourceId:(long long)calendarResourceId nameMap:(NSDictionary *)nameMap descriptionMap:(NSDictionary *)descriptionMap active:(BOOL)active serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarResourceId": @(calendarResourceId),
		@"nameMap": [self checkNull: nameMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"active": @(active),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarresource/update-calendar-resource": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds code:(NSString *)code name:(NSString *)name description:(NSString *)description active:(BOOL)active andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"code": [self checkNull: code],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"active": @(active),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/calendar.calendarresource/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupIds:(NSArray *)groupIds classNameIds:(NSArray *)classNameIds keywords:(NSString *)keywords active:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupIds": [self checkNull: groupIds],
		@"classNameIds": [self checkNull: classNameIds],
		@"keywords": [self checkNull: keywords],
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/calendar.calendarresource/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteCalendarResourceWithCalendarResourceId:(long long)calendarResourceId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarResourceId": @(calendarResourceId)
	}];

	NSDictionary *_command = @{@"/calendar.calendarresource/delete-calendar-resource": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchCalendarResourceWithClassNameId:(long long)classNameId classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameId": @(classNameId),
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/calendar.calendarresource/fetch-calendar-resource": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
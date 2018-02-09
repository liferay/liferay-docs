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

#import "LRCalendarnotificationtemplateService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRCalendarnotificationtemplateService_v7

- (NSDictionary *)addCalendarNotificationTemplateWithCalendarId:(long long)calendarId notificationType:(LRJSONObjectWrapper *)notificationType notificationTypeSettings:(NSString *)notificationTypeSettings notificationTemplateType:(LRJSONObjectWrapper *)notificationTemplateType subject:(NSString *)subject body:(NSString *)body serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarId": @(calendarId),
		@"notificationTypeSettings": [self checkNull: notificationTypeSettings],
		@"subject": [self checkNull: subject],
		@"body": [self checkNull: body],
	}];

	[self mangleWrapperWithParams:_params name:@"notificationType" className:@"com.liferay.calendar.notification.NotificationType" wrapper:notificationType];
	[self mangleWrapperWithParams:_params name:@"notificationTemplateType" className:@"com.liferay.calendar.notification.NotificationTemplateType" wrapper:notificationTemplateType];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarnotificationtemplate/add-calendar-notification-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCalendarNotificationTemplateWithCalendarNotificationTemplateId:(long long)calendarNotificationTemplateId notificationTypeSettings:(NSString *)notificationTypeSettings subject:(NSString *)subject body:(NSString *)body serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"calendarNotificationTemplateId": @(calendarNotificationTemplateId),
		@"notificationTypeSettings": [self checkNull: notificationTypeSettings],
		@"subject": [self checkNull: subject],
		@"body": [self checkNull: body],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/calendar.calendarnotificationtemplate/update-calendar-notification-template": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
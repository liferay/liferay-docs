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

#import "LRAnnouncementsDeliveryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRAnnouncementsDeliveryService_v62

- (NSDictionary *)updateDeliveryWithUserId:(long long)userId type:(NSString *)type email:(BOOL)email sms:(BOOL)sms website:(BOOL)website error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"type": [self checkNull: type],
		@"email": @(email),
		@"sms": @(sms),
		@"website": @(website)
	}];

	NSDictionary *_command = @{@"/announcementsdelivery/update-delivery": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
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

#import "LRPhoneService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRPhoneService_v62

- (NSDictionary *)addPhoneWithClassName:(NSString *)className classPK:(long long)classPK number:(NSString *)number extension:(NSString *)extension typeId:(int)typeId primary:(BOOL)primary error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"number": [self checkNull: number],
		@"extension": [self checkNull: extension],
		@"typeId": @(typeId),
		@"primary": @(primary)
	}];

	NSDictionary *_command = @{@"/phone/add-phone": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addPhoneWithClassName:(NSString *)className classPK:(long long)classPK number:(NSString *)number extension:(NSString *)extension typeId:(int)typeId primary:(BOOL)primary serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"number": [self checkNull: number],
		@"extension": [self checkNull: extension],
		@"typeId": @(typeId),
		@"primary": @(primary),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/phone/add-phone": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deletePhoneWithPhoneId:(long long)phoneId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"phoneId": @(phoneId)
	}];

	NSDictionary *_command = @{@"/phone/delete-phone": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getPhoneWithPhoneId:(long long)phoneId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"phoneId": @(phoneId)
	}];

	NSDictionary *_command = @{@"/phone/get-phone": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getPhonesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/phone/get-phones": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePhoneWithPhoneId:(long long)phoneId number:(NSString *)number extension:(NSString *)extension typeId:(int)typeId primary:(BOOL)primary error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"phoneId": @(phoneId),
		@"number": [self checkNull: number],
		@"extension": [self checkNull: extension],
		@"typeId": @(typeId),
		@"primary": @(primary)
	}];

	NSDictionary *_command = @{@"/phone/update-phone": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
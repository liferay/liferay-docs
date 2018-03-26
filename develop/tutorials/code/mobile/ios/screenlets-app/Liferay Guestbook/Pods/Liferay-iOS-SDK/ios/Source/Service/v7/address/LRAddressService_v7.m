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

#import "LRAddressService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRAddressService_v7

- (NSDictionary *)getAddressWithAddressId:(long long)addressId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"addressId": @(addressId)
	}];

	NSDictionary *_command = @{@"/address/get-address": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getAddressesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/address/get-addresses": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addAddressWithClassName:(NSString *)className classPK:(long long)classPK street1:(NSString *)street1 street2:(NSString *)street2 street3:(NSString *)street3 city:(NSString *)city zip:(NSString *)zip regionId:(long long)regionId countryId:(long long)countryId typeId:(long long)typeId mailing:(BOOL)mailing primary:(BOOL)primary serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK),
		@"street1": [self checkNull: street1],
		@"street2": [self checkNull: street2],
		@"street3": [self checkNull: street3],
		@"city": [self checkNull: city],
		@"zip": [self checkNull: zip],
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"typeId": @(typeId),
		@"mailing": @(mailing),
		@"primary": @(primary),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/address/add-address": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateAddressWithAddressId:(long long)addressId street1:(NSString *)street1 street2:(NSString *)street2 street3:(NSString *)street3 city:(NSString *)city zip:(NSString *)zip regionId:(long long)regionId countryId:(long long)countryId typeId:(long long)typeId mailing:(BOOL)mailing primary:(BOOL)primary error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"addressId": @(addressId),
		@"street1": [self checkNull: street1],
		@"street2": [self checkNull: street2],
		@"street3": [self checkNull: street3],
		@"city": [self checkNull: city],
		@"zip": [self checkNull: zip],
		@"regionId": @(regionId),
		@"countryId": @(countryId),
		@"typeId": @(typeId),
		@"mailing": @(mailing),
		@"primary": @(primary)
	}];

	NSDictionary *_command = @{@"/address/update-address": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteAddressWithAddressId:(long long)addressId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"addressId": @(addressId)
	}];

	NSDictionary *_command = @{@"/address/delete-address": _params};

	[self.session invoke:_command error:error];
}

@end
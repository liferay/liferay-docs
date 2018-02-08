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

#import "LRRegionService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRRegionService_v62

- (NSDictionary *)addRegionWithCountryId:(long long)countryId regionCode:(NSString *)regionCode name:(NSString *)name active:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId),
		@"regionCode": [self checkNull: regionCode],
		@"name": [self checkNull: name],
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/region/add-region": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchRegionWithCountryId:(long long)countryId regionCode:(NSString *)regionCode error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId),
		@"regionCode": [self checkNull: regionCode]
	}];

	NSDictionary *_command = @{@"/region/fetch-region": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRegionWithRegionId:(long long)regionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"regionId": @(regionId)
	}];

	NSDictionary *_command = @{@"/region/get-region": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRegionWithCountryId:(long long)countryId regionCode:(NSString *)regionCode error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId),
		@"regionCode": [self checkNull: regionCode]
	}];

	NSDictionary *_command = @{@"/region/get-region": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getRegions:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/region/get-regions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getRegionsWithActive:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/region/get-regions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getRegionsWithCountryId:(long long)countryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId)
	}];

	NSDictionary *_command = @{@"/region/get-regions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getRegionsWithCountryId:(long long)countryId active:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId),
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/region/get-regions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

@end
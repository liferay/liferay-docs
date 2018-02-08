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

#import "LRCountryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRCountryService_v62

- (NSDictionary *)addCountryWithName:(NSString *)name a2:(NSString *)a2 a3:(NSString *)a3 number:(NSString *)number idd:(NSString *)idd active:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"name": [self checkNull: name],
		@"a2": [self checkNull: a2],
		@"a3": [self checkNull: a3],
		@"number": [self checkNull: number],
		@"idd": [self checkNull: idd],
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/country/add-country": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchCountryWithCountryId:(long long)countryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId)
	}];

	NSDictionary *_command = @{@"/country/fetch-country": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchCountryByA2WithA2:(NSString *)a2 error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"a2": [self checkNull: a2]
	}];

	NSDictionary *_command = @{@"/country/fetch-country-by-a2": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchCountryByA3WithA3:(NSString *)a3 error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"a3": [self checkNull: a3]
	}];

	NSDictionary *_command = @{@"/country/fetch-country-by-a3": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getCountries:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	NSDictionary *_command = @{@"/country/get-countries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCountriesWithActive:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/country/get-countries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCountryWithCountryId:(long long)countryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"countryId": @(countryId)
	}];

	NSDictionary *_command = @{@"/country/get-country": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCountryByA2WithA2:(NSString *)a2 error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"a2": [self checkNull: a2]
	}];

	NSDictionary *_command = @{@"/country/get-country-by-a2": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCountryByA3WithA3:(NSString *)a3 error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"a3": [self checkNull: a3]
	}];

	NSDictionary *_command = @{@"/country/get-country-by-a3": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCountryByNameWithName:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/country/get-country-by-name": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
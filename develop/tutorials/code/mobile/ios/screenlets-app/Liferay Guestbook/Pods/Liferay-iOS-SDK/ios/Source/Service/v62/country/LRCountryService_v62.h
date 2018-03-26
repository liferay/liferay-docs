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
@interface LRCountryService_v62 : LRBaseService

- (NSDictionary *)addCountryWithName:(NSString *)name a2:(NSString *)a2 a3:(NSString *)a3 number:(NSString *)number idd:(NSString *)idd active:(BOOL)active error:(NSError **)error;
- (NSDictionary *)fetchCountryWithCountryId:(long long)countryId error:(NSError **)error;
- (NSDictionary *)fetchCountryByA2WithA2:(NSString *)a2 error:(NSError **)error;
- (NSDictionary *)fetchCountryByA3WithA3:(NSString *)a3 error:(NSError **)error;
- (NSArray *)getCountries:(NSError **)error;
- (NSArray *)getCountriesWithActive:(BOOL)active error:(NSError **)error;
- (NSDictionary *)getCountryWithCountryId:(long long)countryId error:(NSError **)error;
- (NSDictionary *)getCountryByA2WithA2:(NSString *)a2 error:(NSError **)error;
- (NSDictionary *)getCountryByA3WithA3:(NSString *)a3 error:(NSError **)error;
- (NSDictionary *)getCountryByNameWithName:(NSString *)name error:(NSError **)error;

@end
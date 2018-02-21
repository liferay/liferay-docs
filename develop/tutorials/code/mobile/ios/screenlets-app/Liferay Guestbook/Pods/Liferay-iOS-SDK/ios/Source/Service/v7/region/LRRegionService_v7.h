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
@interface LRRegionService_v7 : LRBaseService

- (NSDictionary *)getRegionWithCountryId:(long long)countryId regionCode:(NSString *)regionCode error:(NSError **)error;
- (NSDictionary *)getRegionWithRegionId:(long long)regionId error:(NSError **)error;
- (NSDictionary *)addRegionWithCountryId:(long long)countryId regionCode:(NSString *)regionCode name:(NSString *)name active:(BOOL)active error:(NSError **)error;
- (NSDictionary *)fetchRegionWithCountryId:(long long)countryId regionCode:(NSString *)regionCode error:(NSError **)error;
- (NSDictionary *)fetchRegionWithRegionId:(long long)regionId error:(NSError **)error;
- (NSArray *)getRegionsWithCountryId:(long long)countryId active:(BOOL)active error:(NSError **)error;
- (NSArray *)getRegionsWithCountryId:(long long)countryId error:(NSError **)error;
- (NSArray *)getRegionsWithActive:(BOOL)active error:(NSError **)error;
- (NSArray *)getRegions:(NSError **)error;

@end
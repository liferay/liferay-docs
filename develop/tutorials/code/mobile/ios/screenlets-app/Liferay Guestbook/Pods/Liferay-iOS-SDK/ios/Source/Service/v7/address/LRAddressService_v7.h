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
@interface LRAddressService_v7 : LRBaseService

- (NSDictionary *)getAddressWithAddressId:(long long)addressId error:(NSError **)error;
- (NSArray *)getAddressesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSDictionary *)addAddressWithClassName:(NSString *)className classPK:(long long)classPK street1:(NSString *)street1 street2:(NSString *)street2 street3:(NSString *)street3 city:(NSString *)city zip:(NSString *)zip regionId:(long long)regionId countryId:(long long)countryId typeId:(long long)typeId mailing:(BOOL)mailing primary:(BOOL)primary serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateAddressWithAddressId:(long long)addressId street1:(NSString *)street1 street2:(NSString *)street2 street3:(NSString *)street3 city:(NSString *)city zip:(NSString *)zip regionId:(long long)regionId countryId:(long long)countryId typeId:(long long)typeId mailing:(BOOL)mailing primary:(BOOL)primary error:(NSError **)error;
- (void)deleteAddressWithAddressId:(long long)addressId error:(NSError **)error;

@end
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
@interface LRPhoneService_v62 : LRBaseService

- (NSDictionary *)addPhoneWithClassName:(NSString *)className classPK:(long long)classPK number:(NSString *)number extension:(NSString *)extension typeId:(int)typeId primary:(BOOL)primary error:(NSError **)error;
- (NSDictionary *)addPhoneWithClassName:(NSString *)className classPK:(long long)classPK number:(NSString *)number extension:(NSString *)extension typeId:(int)typeId primary:(BOOL)primary serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deletePhoneWithPhoneId:(long long)phoneId error:(NSError **)error;
- (NSDictionary *)getPhoneWithPhoneId:(long long)phoneId error:(NSError **)error;
- (NSArray *)getPhonesWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error;
- (NSDictionary *)updatePhoneWithPhoneId:(long long)phoneId number:(NSString *)number extension:(NSString *)extension typeId:(int)typeId primary:(BOOL)primary error:(NSError **)error;

@end
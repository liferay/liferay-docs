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

#import "LRExpandoValueService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRExpandoValueService_v62

- (NSDictionary *)addValueWithCompanyId:(long long)companyId className:(NSString *)className tableName:(NSString *)tableName columnName:(NSString *)columnName classPK:(long long)classPK data:(NSString *)data error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"className": [self checkNull: className],
		@"tableName": [self checkNull: tableName],
		@"columnName": [self checkNull: columnName],
		@"classPK": @(classPK),
		@"data": [self checkNull: data]
	}];

	NSDictionary *_command = @{@"/expandovalue/add-value": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)addValuesWithCompanyId:(long long)companyId className:(NSString *)className tableName:(NSString *)tableName classPK:(long long)classPK attributeValues:(NSDictionary *)attributeValues error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"className": [self checkNull: className],
		@"tableName": [self checkNull: tableName],
		@"classPK": @(classPK),
		@"attributeValues": [self checkNull: attributeValues]
	}];

	NSDictionary *_command = @{@"/expandovalue/add-values": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getDataWithCompanyId:(long long)companyId className:(NSString *)className tableName:(NSString *)tableName columnName:(NSString *)columnName classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"className": [self checkNull: className],
		@"tableName": [self checkNull: tableName],
		@"columnName": [self checkNull: columnName],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/expandovalue/get-data": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getDataWithCompanyId:(long long)companyId className:(NSString *)className tableName:(NSString *)tableName columnNames:(NSDictionary *)columnNames classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"className": [self checkNull: className],
		@"tableName": [self checkNull: tableName],
		@"columnNames": [self checkNull: columnNames],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/expandovalue/get-data": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getJsonDataWithCompanyId:(long long)companyId className:(NSString *)className tableName:(NSString *)tableName columnName:(NSString *)columnName classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"className": [self checkNull: className],
		@"tableName": [self checkNull: tableName],
		@"columnName": [self checkNull: columnName],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/expandovalue/get-json-data": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
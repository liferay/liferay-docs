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

#import "LRSapentryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRSapentryService_v7

- (NSDictionary *)updateSapEntryWithSapEntryId:(long long)sapEntryId allowedServiceSignatures:(NSString *)allowedServiceSignatures defaultSAPEntry:(BOOL)defaultSAPEntry enabled:(BOOL)enabled name:(NSString *)name titleMap:(NSDictionary *)titleMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sapEntryId": @(sapEntryId),
		@"allowedServiceSignatures": [self checkNull: allowedServiceSignatures],
		@"defaultSAPEntry": @(defaultSAPEntry),
		@"enabled": @(enabled),
		@"name": [self checkNull: name],
		@"titleMap": [self checkNull: titleMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/sap.sapentry/update-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCompanySapEntriesCountWithCompanyId:(long long)companyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId)
	}];

	NSDictionary *_command = @{@"/sap.sapentry/get-company-sap-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteSapEntryWithSapEntryId:(long long)sapEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sapEntryId": @(sapEntryId)
	}];

	NSDictionary *_command = @{@"/sap.sapentry/delete-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)deleteSapEntryWithSapEntry:(LRJSONObjectWrapper *)sapEntry error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"sapEntry" className:@"com.liferay.service.access.policy.model.SAPEntry" wrapper:sapEntry];

	NSDictionary *_command = @{@"/sap.sapentry/delete-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchSapEntryWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/sap.sapentry/fetch-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getCompanySapEntriesWithCompanyId:(long long)companyId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/sap.sapentry/get-company-sap-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCompanySapEntriesWithCompanyId:(long long)companyId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.service.access.policy.model.SAPEntry>" wrapper:obc];

	NSDictionary *_command = @{@"/sap.sapentry/get-company-sap-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getSapEntryWithCompanyId:(long long)companyId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/sap.sapentry/get-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getSapEntryWithSapEntryId:(long long)sapEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sapEntryId": @(sapEntryId)
	}];

	NSDictionary *_command = @{@"/sap.sapentry/get-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addSapEntryWithAllowedServiceSignatures:(NSString *)allowedServiceSignatures defaultSAPEntry:(BOOL)defaultSAPEntry enabled:(BOOL)enabled name:(NSString *)name titleMap:(NSDictionary *)titleMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"allowedServiceSignatures": [self checkNull: allowedServiceSignatures],
		@"defaultSAPEntry": @(defaultSAPEntry),
		@"enabled": @(enabled),
		@"name": [self checkNull: name],
		@"titleMap": [self checkNull: titleMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/sap.sapentry/add-sap-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
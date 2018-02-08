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

#import "LRCompanyService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRCompanyService_v62

- (void)deleteLogoWithCompanyId:(long long)companyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId)
	}];

	NSDictionary *_command = @{@"/company/delete-logo": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getCompanyByIdWithCompanyId:(long long)companyId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId)
	}];

	NSDictionary *_command = @{@"/company/get-company-by-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCompanyByLogoIdWithLogoId:(long long)logoId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"logoId": @(logoId)
	}];

	NSDictionary *_command = @{@"/company/get-company-by-logo-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCompanyByMxWithMx:(NSString *)mx error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"mx": [self checkNull: mx]
	}];

	NSDictionary *_command = @{@"/company/get-company-by-mx": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCompanyByVirtualHostWithVirtualHost:(NSString *)virtualHost error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"virtualHost": [self checkNull: virtualHost]
	}];

	NSDictionary *_command = @{@"/company/get-company-by-virtual-host": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCompanyByWebIdWithWebId:(NSString *)webId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"webId": [self checkNull: webId]
	}];

	NSDictionary *_command = @{@"/company/get-company-by-web-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCompanyWithCompanyId:(long long)companyId virtualHost:(NSString *)virtualHost mx:(NSString *)mx homeURL:(NSString *)homeURL name:(NSString *)name legalName:(NSString *)legalName legalId:(NSString *)legalId legalType:(NSString *)legalType sicCode:(NSString *)sicCode tickerSymbol:(NSString *)tickerSymbol industry:(NSString *)industry type:(NSString *)type size:(NSString *)size error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"virtualHost": [self checkNull: virtualHost],
		@"mx": [self checkNull: mx],
		@"homeURL": [self checkNull: homeURL],
		@"name": [self checkNull: name],
		@"legalName": [self checkNull: legalName],
		@"legalId": [self checkNull: legalId],
		@"legalType": [self checkNull: legalType],
		@"sicCode": [self checkNull: sicCode],
		@"tickerSymbol": [self checkNull: tickerSymbol],
		@"industry": [self checkNull: industry],
		@"type": [self checkNull: type],
		@"size": [self checkNull: size]
	}];

	NSDictionary *_command = @{@"/company/update-company": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCompanyWithCompanyId:(long long)companyId virtualHost:(NSString *)virtualHost mx:(NSString *)mx maxUsers:(int)maxUsers active:(BOOL)active error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"virtualHost": [self checkNull: virtualHost],
		@"mx": [self checkNull: mx],
		@"maxUsers": @(maxUsers),
		@"active": @(active)
	}];

	NSDictionary *_command = @{@"/company/update-company": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)updateDisplayWithCompanyId:(long long)companyId languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"languageId": [self checkNull: languageId],
		@"timeZoneId": [self checkNull: timeZoneId]
	}];

	NSDictionary *_command = @{@"/company/update-display": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLogoWithCompanyId:(long long)companyId bytes:(NSData *)bytes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"bytes": [self toString:bytes]
	}];

	NSDictionary *_command = @{@"/company/update-logo": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
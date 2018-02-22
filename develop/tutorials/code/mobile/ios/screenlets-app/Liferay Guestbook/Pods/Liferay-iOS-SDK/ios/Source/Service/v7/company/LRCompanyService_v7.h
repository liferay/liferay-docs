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
@interface LRCompanyService_v7 : LRBaseService

- (NSDictionary *)getCompanyByIdWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSDictionary *)updateCompanyWithCompanyId:(long long)companyId virtualHost:(NSString *)virtualHost mx:(NSString *)mx maxUsers:(int)maxUsers active:(BOOL)active error:(NSError **)error;
- (NSDictionary *)updateCompanyWithCompanyId:(long long)companyId virtualHost:(NSString *)virtualHost mx:(NSString *)mx homeURL:(NSString *)homeURL logo:(BOOL)logo logoBytes:(NSData *)logoBytes name:(NSString *)name legalName:(NSString *)legalName legalId:(NSString *)legalId legalType:(NSString *)legalType sicCode:(NSString *)sicCode tickerSymbol:(NSString *)tickerSymbol industry:(NSString *)industry type:(NSString *)type size:(NSString *)size error:(NSError **)error;
- (NSDictionary *)updateCompanyWithCompanyId:(long long)companyId virtualHost:(NSString *)virtualHost mx:(NSString *)mx homeURL:(NSString *)homeURL name:(NSString *)name legalName:(NSString *)legalName legalId:(NSString *)legalId legalType:(NSString *)legalType sicCode:(NSString *)sicCode tickerSymbol:(NSString *)tickerSymbol industry:(NSString *)industry type:(NSString *)type size:(NSString *)size error:(NSError **)error;
- (void)deleteLogoWithCompanyId:(long long)companyId error:(NSError **)error;
- (NSDictionary *)getCompanyByLogoIdWithLogoId:(long long)logoId error:(NSError **)error;
- (NSDictionary *)getCompanyByMxWithMx:(NSString *)mx error:(NSError **)error;
- (NSDictionary *)getCompanyByVirtualHostWithVirtualHost:(NSString *)virtualHost error:(NSError **)error;
- (NSDictionary *)getCompanyByWebIdWithWebId:(NSString *)webId error:(NSError **)error;
- (void)updateDisplayWithCompanyId:(long long)companyId languageId:(NSString *)languageId timeZoneId:(NSString *)timeZoneId error:(NSError **)error;
- (NSDictionary *)updateLogoWithCompanyId:(long long)companyId bytes:(NSData *)bytes error:(NSError **)error;

@end
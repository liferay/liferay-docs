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
@interface LROrgLaborService_v7 : LRBaseService

- (NSDictionary *)addOrgLaborWithOrganizationId:(long long)organizationId typeId:(long long)typeId sunOpen:(int)sunOpen sunClose:(int)sunClose monOpen:(int)monOpen monClose:(int)monClose tueOpen:(int)tueOpen tueClose:(int)tueClose wedOpen:(int)wedOpen wedClose:(int)wedClose thuOpen:(int)thuOpen thuClose:(int)thuClose friOpen:(int)friOpen friClose:(int)friClose satOpen:(int)satOpen satClose:(int)satClose error:(NSError **)error;
- (NSDictionary *)updateOrgLaborWithOrgLaborId:(long long)orgLaborId typeId:(long long)typeId sunOpen:(int)sunOpen sunClose:(int)sunClose monOpen:(int)monOpen monClose:(int)monClose tueOpen:(int)tueOpen tueClose:(int)tueClose wedOpen:(int)wedOpen wedClose:(int)wedClose thuOpen:(int)thuOpen thuClose:(int)thuClose friOpen:(int)friOpen friClose:(int)friClose satOpen:(int)satOpen satClose:(int)satClose error:(NSError **)error;
- (NSDictionary *)getOrgLaborWithOrgLaborId:(long long)orgLaborId error:(NSError **)error;
- (NSArray *)getOrgLaborsWithOrganizationId:(long long)organizationId error:(NSError **)error;
- (void)deleteOrgLaborWithOrgLaborId:(long long)orgLaborId error:(NSError **)error;

@end
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
@interface LRBlogsEntryService_v7 : LRBaseService

- (NSDictionary *)addEntryWithTitle:(NSString *)title subtitle:(NSString *)subtitle description:(NSString *)description content:(NSString *)content displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute allowPingbacks:(BOOL)allowPingbacks allowTrackbacks:(BOOL)allowTrackbacks trackbacks:(NSArray *)trackbacks coverImageCaption:(NSString *)coverImageCaption coverImageImageSelector:(LRJSONObjectWrapper *)coverImageImageSelector smallImageImageSelector:(LRJSONObjectWrapper *)smallImageImageSelector serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)getEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (NSDictionary *)getEntryWithGroupId:(long long)groupId urlTitle:(NSString *)urlTitle error:(NSError **)error;
- (void)deleteEntryWithEntryId:(long long)entryId error:(NSError **)error;
- (NSDictionary *)updateEntryWithEntryId:(long long)entryId title:(NSString *)title subtitle:(NSString *)subtitle description:(NSString *)description content:(NSString *)content displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute allowPingbacks:(BOOL)allowPingbacks allowTrackbacks:(BOOL)allowTrackbacks trackbacks:(NSArray *)trackbacks coverImageCaption:(NSString *)coverImageCaption coverImageImageSelector:(LRJSONObjectWrapper *)coverImageImageSelector smallImageImageSelector:(LRJSONObjectWrapper *)smallImageImageSelector serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)subscribeWithGroupId:(long long)groupId error:(NSError **)error;
- (void)unsubscribeWithGroupId:(long long)groupId error:(NSError **)error;
- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status error:(NSError **)error;
- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId status:(int)status error:(NSError **)error;
- (NSArray *)getGroupsEntriesWithCompanyId:(long long)companyId groupId:(long long)groupId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error;
- (NSArray *)getGroupUserEntriesWithGroupId:(long long)groupId userId:(long long)userId statuses:(NSArray *)statuses start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupUserEntriesWithGroupId:(long long)groupId userId:(long long)userId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getGroupUserEntriesCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status error:(NSError **)error;
- (NSNumber *)getGroupUserEntriesCountWithGroupId:(long long)groupId userId:(long long)userId statuses:(NSArray *)statuses error:(NSError **)error;
- (NSArray *)getOrganizationEntriesWithOrganizationId:(long long)organizationId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error;
- (NSDictionary *)moveEntryToTrashWithEntryId:(long long)entryId error:(NSError **)error;
- (void)restoreEntryFromTrashWithEntryId:(long long)entryId error:(NSError **)error;
- (NSString *)getCompanyEntriesRssWithCompanyId:(long long)companyId displayDate:(long long)displayDate status:(int)status max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error;
- (NSString *)getGroupEntriesRssWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error;
- (NSString *)getOrganizationEntriesRssWithOrganizationId:(long long)organizationId displayDate:(long long)displayDate status:(int)status max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error;
- (NSArray *)getCompanyEntriesWithCompanyId:(long long)companyId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status max:(int)max error:(NSError **)error;

@end
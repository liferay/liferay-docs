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

#import "LRBlogsEntryService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRBlogsEntryService_v7

- (NSDictionary *)addEntryWithTitle:(NSString *)title subtitle:(NSString *)subtitle description:(NSString *)description content:(NSString *)content displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute allowPingbacks:(BOOL)allowPingbacks allowTrackbacks:(BOOL)allowTrackbacks trackbacks:(NSArray *)trackbacks coverImageCaption:(NSString *)coverImageCaption coverImageImageSelector:(LRJSONObjectWrapper *)coverImageImageSelector smallImageImageSelector:(LRJSONObjectWrapper *)smallImageImageSelector serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"title": [self checkNull: title],
		@"subtitle": [self checkNull: subtitle],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"displayDateMonth": @(displayDateMonth),
		@"displayDateDay": @(displayDateDay),
		@"displayDateYear": @(displayDateYear),
		@"displayDateHour": @(displayDateHour),
		@"displayDateMinute": @(displayDateMinute),
		@"allowPingbacks": @(allowPingbacks),
		@"allowTrackbacks": @(allowTrackbacks),
		@"trackbacks": [self checkNull: trackbacks],
		@"coverImageCaption": [self checkNull: coverImageCaption],
	}];

	[self mangleWrapperWithParams:_params name:@"coverImageImageSelector" className:@"com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector" wrapper:coverImageImageSelector];
	[self mangleWrapperWithParams:_params name:@"smallImageImageSelector" className:@"com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector" wrapper:smallImageImageSelector];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/blogsentry/add-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getEntryWithGroupId:(long long)groupId urlTitle:(NSString *)urlTitle error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"urlTitle": [self checkNull: urlTitle]
	}];

	NSDictionary *_command = @{@"/blogsentry/get-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/blogsentry/delete-entry": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateEntryWithEntryId:(long long)entryId title:(NSString *)title subtitle:(NSString *)subtitle description:(NSString *)description content:(NSString *)content displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute allowPingbacks:(BOOL)allowPingbacks allowTrackbacks:(BOOL)allowTrackbacks trackbacks:(NSArray *)trackbacks coverImageCaption:(NSString *)coverImageCaption coverImageImageSelector:(LRJSONObjectWrapper *)coverImageImageSelector smallImageImageSelector:(LRJSONObjectWrapper *)smallImageImageSelector serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId),
		@"title": [self checkNull: title],
		@"subtitle": [self checkNull: subtitle],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"displayDateMonth": @(displayDateMonth),
		@"displayDateDay": @(displayDateDay),
		@"displayDateYear": @(displayDateYear),
		@"displayDateHour": @(displayDateHour),
		@"displayDateMinute": @(displayDateMinute),
		@"allowPingbacks": @(allowPingbacks),
		@"allowTrackbacks": @(allowTrackbacks),
		@"trackbacks": [self checkNull: trackbacks],
		@"coverImageCaption": [self checkNull: coverImageCaption],
	}];

	[self mangleWrapperWithParams:_params name:@"coverImageImageSelector" className:@"com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector" wrapper:coverImageImageSelector];
	[self mangleWrapperWithParams:_params name:@"smallImageImageSelector" className:@"com.liferay.portal.kernel.servlet.taglib.ui.ImageSelector" wrapper:smallImageImageSelector];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/blogsentry/update-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)subscribeWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/blogsentry/subscribe": _params};

	[self.session invoke:_command error:error];
}

- (void)unsubscribeWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/blogsentry/unsubscribe": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"displayDate": @(displayDate),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupsEntriesWithCompanyId:(long long)companyId groupId:(long long)groupId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-groups-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupUserEntriesWithGroupId:(long long)groupId userId:(long long)userId statuses:(NSArray *)statuses start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"statuses": [self checkNull: statuses],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blogs.kernel.model.BlogsEntry>" wrapper:obc];

	NSDictionary *_command = @{@"/blogsentry/get-group-user-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupUserEntriesWithGroupId:(long long)groupId userId:(long long)userId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blogs.kernel.model.BlogsEntry>" wrapper:obc];

	NSDictionary *_command = @{@"/blogsentry/get-group-user-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupUserEntriesCountWithGroupId:(long long)groupId userId:(long long)userId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-user-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupUserEntriesCountWithGroupId:(long long)groupId userId:(long long)userId statuses:(NSArray *)statuses error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"statuses": [self checkNull: statuses]
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-user-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getOrganizationEntriesWithOrganizationId:(long long)organizationId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-organization-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveEntryToTrashWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/blogsentry/move-entry-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restoreEntryFromTrashWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/blogsentry/restore-entry-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (NSString *)getCompanyEntriesRssWithCompanyId:(long long)companyId displayDate:(long long)displayDate status:(int)status max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL],
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/blogsentry/get-company-entries-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getGroupEntriesRssWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL],
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getOrganizationEntriesRssWithOrganizationId:(long long)organizationId displayDate:(long long)displayDate status:(int)status max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"organizationId": @(organizationId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL],
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/blogsentry/get-organization-entries-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSArray *)getCompanyEntriesWithCompanyId:(long long)companyId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-company-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.blogs.kernel.model.BlogsEntry>" wrapper:obc];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId displayDate:(long long)displayDate status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"displayDate": @(displayDate),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

@end
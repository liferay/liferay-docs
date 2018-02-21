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

#import "LRBlogsEntryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRBlogsEntryService_v62

- (void)deleteEntryWithEntryId:(long long)entryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"entryId": @(entryId)
	}];

	NSDictionary *_command = @{@"/blogsentry/delete-entry": _params};

	[self.session invoke:_command error:error];
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

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/blogsentry/get-company-entries-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
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

- (NSArray *)getGroupEntriesWithGroupId:(long long)groupId status:(int)status max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status),
		@"max": @(max)
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

- (NSNumber *)getGroupEntriesCountWithGroupId:(long long)groupId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
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

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/blogsentry/get-group-entries-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
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

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/blogsentry/get-organization-entries-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
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

@end
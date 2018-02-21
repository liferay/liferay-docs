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

#import "LRDLFileEntryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRDLFileEntryService_v62

- (NSDictionary *)cancelCheckOutWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/cancel-check-out": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"lockUuid": [self checkNull: lockUuid]
	}];

	NSDictionary *_command = @{@"/dlfileentry/check-in-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"lockUuid": [self checkNull: lockUuid],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/check-in-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (void)checkInFileEntryWithFileEntryId:(long long)fileEntryId major:(BOOL)major changeLog:(NSString *)changeLog serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"major": @(major),
		@"changeLog": [self checkNull: changeLog],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/check-in-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/check-out-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/check-out-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId owner:(NSString *)owner expirationTime:(long long)expirationTime error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"owner": [self checkNull: owner],
		@"expirationTime": @(expirationTime)
	}];

	NSDictionary *_command = @{@"/dlfileentry/check-out-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)checkOutFileEntryWithFileEntryId:(long long)fileEntryId owner:(NSString *)owner expirationTime:(long long)expirationTime serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"owner": [self checkNull: owner],
		@"expirationTime": @(expirationTime),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/check-out-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyFileEntryWithGroupId:(long long)groupId repositoryId:(long long)repositoryId fileEntryId:(long long)fileEntryId destFolderId:(long long)destFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"repositoryId": @(repositoryId),
		@"fileEntryId": @(fileEntryId),
		@"destFolderId": @(destFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/copy-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/delete-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/dlfileentry/delete-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteFileVersionWithFileEntryId:(long long)fileEntryId version:(NSString *)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"version": [self checkNull: version]
	}];

	NSDictionary *_command = @{@"/dlfileentry/delete-file-version": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchFileEntryByImageIdWithImageId:(long long)imageId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"imageId": @(imageId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/fetch-file-entry-by-image-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"fileEntryTypeId": @(fileEntryTypeId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getFileEntriesWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId fileEntryTypeId:(long long)fileEntryTypeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"fileEntryTypeId": @(fileEntryTypeId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"mimeTypes": [self checkNull: mimeTypes]
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFileEntriesCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getFileEntryWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getFileEntryWithGroupId:(long long)groupId folderId:(long long)folderId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getFileEntryByUuidAndGroupIdWithUuid:(NSString *)uuid groupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"uuid": [self checkNull: uuid],
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entry-by-uuid-and-group-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getFileEntryLockWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-file-entry-lock": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersFileEntriesCountWithGroupId:(long long)groupId folderIds:(NSArray *)folderIds status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-folders-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfileentry/get-group-file-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupFileEntriesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId mimeTypes:(NSArray *)mimeTypes status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/dlfileentry/get-group-file-entries": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-group-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupFileEntriesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId mimeTypes:(NSArray *)mimeTypes status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/dlfileentry/get-group-file-entries-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (BOOL)hasFileEntryLockWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/has-file-entry-lock": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)isFileEntryCheckedOutWithFileEntryId:(long long)fileEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId)
	}];

	NSDictionary *_command = @{@"/dlfileentry/is-file-entry-checked-out": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (NSDictionary *)moveFileEntryWithFileEntryId:(long long)fileEntryId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"newFolderId": @(newFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/move-file-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)refreshFileEntryLockWithLockUuid:(NSString *)lockUuid companyId:(long long)companyId expirationTime:(long long)expirationTime error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"lockUuid": [self checkNull: lockUuid],
		@"companyId": @(companyId),
		@"expirationTime": @(expirationTime)
	}];

	NSDictionary *_command = @{@"/dlfileentry/refresh-file-entry-lock": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)revertFileEntryWithFileEntryId:(long long)fileEntryId version:(NSString *)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"version": [self checkNull: version],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/dlfileentry/revert-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"creatorUserId": @(creatorUserId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/dlfileentry/search": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId folderId:(long long)folderId mimeTypes:(NSArray *)mimeTypes status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"creatorUserId": @(creatorUserId),
		@"folderId": @(folderId),
		@"mimeTypes": [self checkNull: mimeTypes],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/dlfileentry/search": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (BOOL)verifyFileEntryCheckOutWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"lockUuid": [self checkNull: lockUuid]
	}];

	NSDictionary *_command = @{@"/dlfileentry/verify-file-entry-check-out": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

- (BOOL)verifyFileEntryLockWithFileEntryId:(long long)fileEntryId lockUuid:(NSString *)lockUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fileEntryId": @(fileEntryId),
		@"lockUuid": [self checkNull: lockUuid]
	}];

	NSDictionary *_command = @{@"/dlfileentry/verify-file-entry-lock": _params};

	return [self boolValue:(NSNumber *)[self.session invoke:_command error:error]];
}

@end
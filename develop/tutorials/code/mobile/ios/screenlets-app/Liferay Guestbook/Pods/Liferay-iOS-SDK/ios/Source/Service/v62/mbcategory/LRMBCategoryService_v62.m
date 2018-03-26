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

#import "LRMBCategoryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRMBCategoryService_v62

- (NSDictionary *)addCategoryWithParentCategoryId:(long long)parentCategoryId name:(NSString *)name description:(NSString *)description displayStyle:(NSString *)displayStyle emailAddress:(NSString *)emailAddress inProtocol:(NSString *)inProtocol inServerName:(NSString *)inServerName inServerPort:(int)inServerPort inUseSSL:(BOOL)inUseSSL inUserName:(NSString *)inUserName inPassword:(NSString *)inPassword inReadInterval:(int)inReadInterval outEmailAddress:(NSString *)outEmailAddress outCustom:(BOOL)outCustom outServerName:(NSString *)outServerName outServerPort:(int)outServerPort outUseSSL:(BOOL)outUseSSL outUserName:(NSString *)outUserName outPassword:(NSString *)outPassword mailingListActive:(BOOL)mailingListActive allowAnonymousEmail:(BOOL)allowAnonymousEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"parentCategoryId": @(parentCategoryId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"displayStyle": [self checkNull: displayStyle],
		@"emailAddress": [self checkNull: emailAddress],
		@"inProtocol": [self checkNull: inProtocol],
		@"inServerName": [self checkNull: inServerName],
		@"inServerPort": @(inServerPort),
		@"inUseSSL": @(inUseSSL),
		@"inUserName": [self checkNull: inUserName],
		@"inPassword": [self checkNull: inPassword],
		@"inReadInterval": @(inReadInterval),
		@"outEmailAddress": [self checkNull: outEmailAddress],
		@"outCustom": @(outCustom),
		@"outServerName": [self checkNull: outServerName],
		@"outServerPort": @(outServerPort),
		@"outUseSSL": @(outUseSSL),
		@"outUserName": [self checkNull: outUserName],
		@"outPassword": [self checkNull: outPassword],
		@"mailingListActive": @(mailingListActive),
		@"allowAnonymousEmail": @(allowAnonymousEmail),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mbcategory/add-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCategoryWithUserId:(long long)userId parentCategoryId:(long long)parentCategoryId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"parentCategoryId": @(parentCategoryId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mbcategory/add-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteCategoryWithCategoryId:(long long)categoryId includeTrashedEntries:(BOOL)includeTrashedEntries error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"includeTrashedEntries": @(includeTrashedEntries)
	}];

	NSDictionary *_command = @{@"/mbcategory/delete-category": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteCategoryWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/delete-category": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithGroupId:(long long)groupId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryId": @(parentCategoryId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryIds": [self checkNull: parentCategoryIds],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryId": @(parentCategoryId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryIds": [self checkNull: parentCategoryIds],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryId": @(parentCategoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryIds": [self checkNull: parentCategoryIds]
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryId": @(parentCategoryId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"parentCategoryIds": [self checkNull: parentCategoryIds],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getCategoryWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getCategoryIdsWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-category-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getSubcategoryIdsWithCategoryIds:(NSArray *)categoryIds groupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryIds": [self checkNull: categoryIds],
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-subcategory-ids": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getSubscribedCategoriesWithGroupId:(long long)groupId userId:(long long)userId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-subscribed-categories": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getSubscribedCategoriesCountWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId)
	}];

	NSDictionary *_command = @{@"/mbcategory/get-subscribed-categories-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId mergeWithParentCategory:(BOOL)mergeWithParentCategory error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"parentCategoryId": @(parentCategoryId),
		@"mergeWithParentCategory": @(mergeWithParentCategory)
	}];

	NSDictionary *_command = @{@"/mbcategory/move-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveCategoryFromTrashWithCategoryId:(long long)categoryId newCategoryId:(long long)newCategoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"newCategoryId": @(newCategoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/move-category-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveCategoryToTrashWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/move-category-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restoreCategoryFromTrashWithCategoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/restore-category-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (void)subscribeCategoryWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/subscribe-category": _params};

	[self.session invoke:_command error:error];
}

- (void)unsubscribeCategoryWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"categoryId": @(categoryId)
	}];

	NSDictionary *_command = @{@"/mbcategory/unsubscribe-category": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId name:(NSString *)name description:(NSString *)description displayStyle:(NSString *)displayStyle emailAddress:(NSString *)emailAddress inProtocol:(NSString *)inProtocol inServerName:(NSString *)inServerName inServerPort:(int)inServerPort inUseSSL:(BOOL)inUseSSL inUserName:(NSString *)inUserName inPassword:(NSString *)inPassword inReadInterval:(int)inReadInterval outEmailAddress:(NSString *)outEmailAddress outCustom:(BOOL)outCustom outServerName:(NSString *)outServerName outServerPort:(int)outServerPort outUseSSL:(BOOL)outUseSSL outUserName:(NSString *)outUserName outPassword:(NSString *)outPassword mailingListActive:(BOOL)mailingListActive allowAnonymousEmail:(BOOL)allowAnonymousEmail mergeWithParentCategory:(BOOL)mergeWithParentCategory serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"categoryId": @(categoryId),
		@"parentCategoryId": @(parentCategoryId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"displayStyle": [self checkNull: displayStyle],
		@"emailAddress": [self checkNull: emailAddress],
		@"inProtocol": [self checkNull: inProtocol],
		@"inServerName": [self checkNull: inServerName],
		@"inServerPort": @(inServerPort),
		@"inUseSSL": @(inUseSSL),
		@"inUserName": [self checkNull: inUserName],
		@"inPassword": [self checkNull: inPassword],
		@"inReadInterval": @(inReadInterval),
		@"outEmailAddress": [self checkNull: outEmailAddress],
		@"outCustom": @(outCustom),
		@"outServerName": [self checkNull: outServerName],
		@"outServerPort": @(outServerPort),
		@"outUseSSL": @(outUseSSL),
		@"outUserName": [self checkNull: outUserName],
		@"outPassword": [self checkNull: outPassword],
		@"mailingListActive": @(mailingListActive),
		@"allowAnonymousEmail": @(allowAnonymousEmail),
		@"mergeWithParentCategory": @(mergeWithParentCategory),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/mbcategory/update-category": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
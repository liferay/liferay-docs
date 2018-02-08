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
@interface LRMBCategoryService_v7 : LRBaseService

- (NSDictionary *)getCategoryWithCategoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId excludedCategoryId:(long long)excludedCategoryId parentCategoryId:(long long)parentCategoryId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId excludedCategoryIds:(NSArray *)excludedCategoryIds parentCategoryIds:(NSArray *)parentCategoryIds status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId status:(int)status error:(NSError **)error;
- (NSArray *)getCategoriesWithGroupId:(long long)groupId error:(NSError **)error;
- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId status:(int)status error:(NSError **)error;
- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId excludedCategoryId:(long long)excludedCategoryId parentCategoryId:(long long)parentCategoryId status:(int)status error:(NSError **)error;
- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryId:(long long)parentCategoryId error:(NSError **)error;
- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId excludedCategoryIds:(NSArray *)excludedCategoryIds parentCategoryIds:(NSArray *)parentCategoryIds status:(int)status error:(NSError **)error;
- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds status:(int)status error:(NSError **)error;
- (NSNumber *)getCategoriesCountWithGroupId:(long long)groupId parentCategoryIds:(NSArray *)parentCategoryIds error:(NSError **)error;
- (NSArray *)getCategoryIdsWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSDictionary *)addCategoryWithParentCategoryId:(long long)parentCategoryId name:(NSString *)name description:(NSString *)description displayStyle:(NSString *)displayStyle emailAddress:(NSString *)emailAddress inProtocol:(NSString *)inProtocol inServerName:(NSString *)inServerName inServerPort:(int)inServerPort inUseSSL:(BOOL)inUseSSL inUserName:(NSString *)inUserName inPassword:(NSString *)inPassword inReadInterval:(int)inReadInterval outEmailAddress:(NSString *)outEmailAddress outCustom:(BOOL)outCustom outServerName:(NSString *)outServerName outServerPort:(int)outServerPort outUseSSL:(BOOL)outUseSSL outUserName:(NSString *)outUserName outPassword:(NSString *)outPassword mailingListActive:(BOOL)mailingListActive allowAnonymousEmail:(BOOL)allowAnonymousEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addCategoryWithUserId:(long long)userId parentCategoryId:(long long)parentCategoryId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getCategoriesAndThreadsWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status error:(NSError **)error;
- (NSArray *)getCategoriesAndThreadsWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getCategoriesAndThreadsWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getCategoriesAndThreadsCountWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSNumber *)getCategoriesAndThreadsCountWithGroupId:(long long)groupId categoryId:(long long)categoryId status:(int)status error:(NSError **)error;
- (NSArray *)getSubscribedCategoriesWithGroupId:(long long)groupId userId:(long long)userId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getSubscribedCategoriesCountWithGroupId:(long long)groupId userId:(long long)userId error:(NSError **)error;
- (NSDictionary *)moveCategoryFromTrashWithCategoryId:(long long)categoryId newCategoryId:(long long)newCategoryId error:(NSError **)error;
- (NSDictionary *)moveCategoryToTrashWithCategoryId:(long long)categoryId error:(NSError **)error;
- (void)restoreCategoryFromTrashWithCategoryId:(long long)categoryId error:(NSError **)error;
- (void)subscribeCategoryWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (void)unsubscribeCategoryWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (void)deleteCategoryWithCategoryId:(long long)categoryId includeTrashedEntries:(BOOL)includeTrashedEntries error:(NSError **)error;
- (void)deleteCategoryWithGroupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSArray *)getSubcategoryIdsWithCategoryIds:(NSArray *)categoryIds groupId:(long long)groupId categoryId:(long long)categoryId error:(NSError **)error;
- (NSDictionary *)moveCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId mergeWithParentCategory:(BOOL)mergeWithParentCategory error:(NSError **)error;
- (NSDictionary *)updateCategoryWithCategoryId:(long long)categoryId parentCategoryId:(long long)parentCategoryId name:(NSString *)name description:(NSString *)description displayStyle:(NSString *)displayStyle emailAddress:(NSString *)emailAddress inProtocol:(NSString *)inProtocol inServerName:(NSString *)inServerName inServerPort:(int)inServerPort inUseSSL:(BOOL)inUseSSL inUserName:(NSString *)inUserName inPassword:(NSString *)inPassword inReadInterval:(int)inReadInterval outEmailAddress:(NSString *)outEmailAddress outCustom:(BOOL)outCustom outServerName:(NSString *)outServerName outServerPort:(int)outServerPort outUseSSL:(BOOL)outUseSSL outUserName:(NSString *)outUserName outPassword:(NSString *)outPassword mailingListActive:(BOOL)mailingListActive allowAnonymousEmail:(BOOL)allowAnonymousEmail mergeWithParentCategory:(BOOL)mergeWithParentCategory serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
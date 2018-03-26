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
@interface LRSCProductVersionService_v62 : LRBaseService

- (NSDictionary *)addProductVersionWithProductEntryId:(long long)productEntryId version:(NSString *)version changeLog:(NSString *)changeLog downloadPageURL:(NSString *)downloadPageURL directDownloadURL:(NSString *)directDownloadURL testDirectDownloadURL:(BOOL)testDirectDownloadURL repoStoreArtifact:(BOOL)repoStoreArtifact frameworkVersionIds:(NSArray *)frameworkVersionIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteProductVersionWithProductVersionId:(long long)productVersionId error:(NSError **)error;
- (NSDictionary *)getProductVersionWithProductVersionId:(long long)productVersionId error:(NSError **)error;
- (NSArray *)getProductVersionsWithProductEntryId:(long long)productEntryId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getProductVersionsCountWithProductEntryId:(long long)productEntryId error:(NSError **)error;
- (NSDictionary *)updateProductVersionWithProductVersionId:(long long)productVersionId version:(NSString *)version changeLog:(NSString *)changeLog downloadPageURL:(NSString *)downloadPageURL directDownloadURL:(NSString *)directDownloadURL testDirectDownloadURL:(BOOL)testDirectDownloadURL repoStoreArtifact:(BOOL)repoStoreArtifact frameworkVersionIds:(NSArray *)frameworkVersionIds error:(NSError **)error;

@end
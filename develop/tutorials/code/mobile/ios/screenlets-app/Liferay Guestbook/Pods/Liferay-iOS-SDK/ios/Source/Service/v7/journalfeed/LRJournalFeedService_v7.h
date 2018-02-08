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
@interface LRJournalFeedService_v7 : LRBaseService

- (NSDictionary *)addFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId autoFeedId:(BOOL)autoFeedId name:(NSString *)name description:(NSString *)description ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey ddmRendererTemplateKey:(NSString *)ddmRendererTemplateKey delta:(int)delta orderByCol:(NSString *)orderByCol orderByType:(NSString *)orderByType targetLayoutFriendlyUrl:(NSString *)targetLayoutFriendlyUrl targetPortletId:(NSString *)targetPortletId contentField:(NSString *)contentField feedType:(NSString *)feedType feedVersion:(double)feedVersion serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId error:(NSError **)error;
- (void)deleteFeedWithFeedId:(long long)feedId error:(NSError **)error;
- (NSDictionary *)getFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId error:(NSError **)error;
- (NSDictionary *)getFeedWithFeedId:(long long)feedId error:(NSError **)error;
- (NSDictionary *)updateFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId name:(NSString *)name description:(NSString *)description ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey ddmRendererTemplateKey:(NSString *)ddmRendererTemplateKey delta:(int)delta orderByCol:(NSString *)orderByCol orderByType:(NSString *)orderByType targetLayoutFriendlyUrl:(NSString *)targetLayoutFriendlyUrl targetPortletId:(NSString *)targetPortletId contentField:(NSString *)contentField feedType:(NSString *)feedType feedVersion:(double)feedVersion serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
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
@interface LRPortletPreferencesService_v62 : LRBaseService

- (void)deleteArchivedPreferencesWithPortletItemId:(long long)portletItemId error:(NSError **)error;
- (void)restoreArchivedPreferencesWithGroupId:(long long)groupId layout:(LRJSONObjectWrapper *)layout portletId:(NSString *)portletId portletItem:(LRJSONObjectWrapper *)portletItem preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error;
- (void)restoreArchivedPreferencesWithGroupId:(long long)groupId layout:(LRJSONObjectWrapper *)layout portletId:(NSString *)portletId portletItemId:(long long)portletItemId preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error;
- (void)restoreArchivedPreferencesWithGroupId:(long long)groupId name:(NSString *)name layout:(LRJSONObjectWrapper *)layout portletId:(NSString *)portletId preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error;
- (void)updateArchivePreferencesWithUserId:(long long)userId groupId:(long long)groupId name:(NSString *)name portletId:(NSString *)portletId preferences:(LRJSONObjectWrapper *)preferences error:(NSError **)error;

@end
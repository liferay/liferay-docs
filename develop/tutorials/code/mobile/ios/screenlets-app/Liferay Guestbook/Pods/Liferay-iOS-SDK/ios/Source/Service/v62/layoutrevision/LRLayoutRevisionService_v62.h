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
@interface LRLayoutRevisionService_v62 : LRBaseService

- (NSDictionary *)addLayoutRevisionWithUserId:(long long)userId layoutSetBranchId:(long long)layoutSetBranchId layoutBranchId:(long long)layoutBranchId parentLayoutRevisionId:(long long)parentLayoutRevisionId head:(BOOL)head plid:(long long)plid portletPreferencesPlid:(long long)portletPreferencesPlid privateLayout:(BOOL)privateLayout name:(NSString *)name title:(NSString *)title description:(NSString *)description keywords:(NSString *)keywords robots:(NSString *)robots typeSettings:(NSString *)typeSettings iconImage:(BOOL)iconImage iconImageId:(long long)iconImageId themeId:(NSString *)themeId colorSchemeId:(NSString *)colorSchemeId wapThemeId:(NSString *)wapThemeId wapColorSchemeId:(NSString *)wapColorSchemeId css:(NSString *)css serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
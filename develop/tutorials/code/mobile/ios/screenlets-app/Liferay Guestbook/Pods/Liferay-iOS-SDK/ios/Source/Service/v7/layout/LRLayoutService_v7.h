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
@interface LRLayoutService_v7 : LRBaseService

- (NSArray *)getLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout error:(NSError **)error;
- (NSArray *)getLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId error:(NSError **)error;
- (NSArray *)getLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId incomplete:(BOOL)incomplete start:(int)start end:(int)end error:(NSError **)error;
- (NSDictionary *)updateNameWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId name:(NSString *)name languageId:(NSString *)languageId error:(NSError **)error;
- (NSDictionary *)updateNameWithPlid:(long long)plid name:(NSString *)name languageId:(NSString *)languageId error:(NSError **)error;
- (NSDictionary *)addLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId localeNamesMap:(NSDictionary *)localeNamesMap localeTitlesMap:(NSDictionary *)localeTitlesMap descriptionMap:(NSDictionary *)descriptionMap keywordsMap:(NSDictionary *)keywordsMap robotsMap:(NSDictionary *)robotsMap type:(NSString *)type typeSettings:(NSString *)typeSettings hidden:(BOOL)hidden friendlyURLMap:(NSDictionary *)friendlyURLMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId name:(NSString *)name title:(NSString *)title description:(NSString *)description type:(NSString *)type hidden:(BOOL)hidden friendlyURL:(NSString *)friendlyURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId typeSettings:(NSString *)typeSettings error:(NSError **)error;
- (NSDictionary *)updateLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId parentLayoutId:(long long)parentLayoutId localeNamesMap:(NSDictionary *)localeNamesMap localeTitlesMap:(NSDictionary *)localeTitlesMap descriptionMap:(NSDictionary *)descriptionMap keywordsMap:(NSDictionary *)keywordsMap robotsMap:(NSDictionary *)robotsMap type:(NSString *)type hidden:(BOOL)hidden friendlyURLMap:(NSDictionary *)friendlyURLMap iconImage:(BOOL)iconImage iconBytes:(NSData *)iconBytes serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)schedulePublishToLiveWithSourceGroupId:(long long)sourceGroupId targetGroupId:(long long)targetGroupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error;
- (void)schedulePublishToLiveWithSourceGroupId:(long long)sourceGroupId targetGroupId:(long long)targetGroupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap scope:(NSString *)scope startDate:(long long)startDate endDate:(long long)endDate groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error;
- (void)schedulePublishToLiveWithSourceGroupId:(long long)sourceGroupId targetGroupId:(long long)targetGroupId privateLayout:(BOOL)privateLayout layoutIdMap:(NSDictionary *)layoutIdMap parameterMap:(NSDictionary *)parameterMap scope:(NSString *)scope startDate:(long long)startDate endDate:(long long)endDate groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error;
- (void)schedulePublishToRemoteWithSourceGroupId:(long long)sourceGroupId privateLayout:(BOOL)privateLayout layoutIdMap:(NSDictionary *)layoutIdMap parameterMap:(NSDictionary *)parameterMap remoteAddress:(NSString *)remoteAddress remotePort:(int)remotePort remotePathContext:(NSString *)remotePathContext secureConnection:(BOOL)secureConnection remoteGroupId:(long long)remoteGroupId remotePrivateLayout:(BOOL)remotePrivateLayout startDate:(long long)startDate endDate:(long long)endDate groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error;
- (void)unschedulePublishToLiveWithGroupId:(long long)groupId jobName:(NSString *)jobName groupName:(NSString *)groupName error:(NSError **)error;
- (void)unschedulePublishToRemoteWithGroupId:(long long)groupId jobName:(NSString *)jobName groupName:(NSString *)groupName error:(NSError **)error;
- (NSNumber *)getDefaultPlidWithGroupId:(long long)groupId scopeGroupId:(long long)scopeGroupId portletId:(NSString *)portletId error:(NSError **)error;
- (NSNumber *)getDefaultPlidWithGroupId:(long long)groupId scopeGroupId:(long long)scopeGroupId privateLayout:(BOOL)privateLayout portletId:(NSString *)portletId error:(NSError **)error;
- (NSNumber *)getLayoutsCountWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId error:(NSError **)error;
- (void)setLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId layoutIds:(NSArray *)layoutIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)getLayoutByUuidAndGroupIdWithUuid:(NSString *)uuid groupId:(long long)groupId privateLayout:(BOOL)privateLayout error:(NSError **)error;
- (NSDictionary *)exportLayoutsAsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error;
- (NSDictionary *)exportLayoutsAsFileWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSDictionary *)exportPortletInfoAsFileWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSDictionary *)exportPortletInfoAsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error;
- (NSDictionary *)exportPortletInfoAsFileWithPortletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSArray *)getTempFileNamesWithGroupId:(long long)groupId folderName:(NSString *)folderName error:(NSError **)error;
- (void)deleteTempFileEntryWithGroupId:(long long)groupId folderName:(NSString *)folderName fileName:(NSString *)fileName error:(NSError **)error;
- (void)deleteLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteLayoutWithPlid:(long long)plid serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)importLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap bytes:(NSData *)bytes error:(NSError **)error;
- (void)importLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (void)importLayoutsWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error;
- (NSDictionary *)updateLookAndFeelWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId themeId:(NSString *)themeId colorSchemeId:(NSString *)colorSchemeId css:(NSString *)css error:(NSError **)error;
- (NSArray *)getAncestorLayoutsWithPlid:(long long)plid error:(NSError **)error;
- (NSArray *)exportLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSArray *)exportLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSArray *)exportPortletInfoWithCompanyId:(long long)companyId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSArray *)exportPortletInfoWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSDictionary *)updateIconImageWithPlid:(long long)plid bytes:(NSData *)bytes error:(NSError **)error;
- (NSDictionary *)updateParentLayoutIdWithPlid:(long long)plid parentPlid:(long long)parentPlid error:(NSError **)error;
- (NSDictionary *)updateParentLayoutIdWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId parentLayoutId:(long long)parentLayoutId error:(NSError **)error;
- (NSDictionary *)updateParentLayoutIdAndPriorityWithPlid:(long long)plid parentPlid:(long long)parentPlid priority:(int)priority error:(NSError **)error;
- (NSDictionary *)updatePriorityWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId priority:(int)priority error:(NSError **)error;
- (NSDictionary *)updatePriorityWithPlid:(long long)plid priority:(int)priority error:(NSError **)error;
- (NSDictionary *)updatePriorityWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId nextLayoutId:(long long)nextLayoutId previousLayoutId:(long long)previousLayoutId error:(NSError **)error;
- (NSString *)getLayoutNameWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId languageId:(NSString *)languageId error:(NSError **)error;
- (NSArray *)getLayoutReferencesWithCompanyId:(long long)companyId portletId:(NSString *)portletId preferencesKey:(NSString *)preferencesKey preferencesValue:(NSString *)preferencesValue error:(NSError **)error;
- (NSNumber *)exportLayoutsAsFileInBackgroundWithTaskName:(NSString *)taskName groupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate fileName:(NSString *)fileName error:(NSError **)error;
- (NSNumber *)exportLayoutsAsFileInBackgroundWithTaskName:(NSString *)taskName groupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error;
- (NSNumber *)exportLayoutsAsFileInBackgroundWithExportImportConfigurationId:(long long)exportImportConfigurationId error:(NSError **)error;
- (NSNumber *)exportLayoutsAsFileInBackgroundWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error;
- (NSNumber *)exportPortletInfoAsFileInBackgroundWithTaskName:(NSString *)taskName portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate fileName:(NSString *)fileName error:(NSError **)error;
- (NSNumber *)exportPortletInfoAsFileInBackgroundWithTaskName:(NSString *)taskName plid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate fileName:(NSString *)fileName error:(NSError **)error;
- (NSNumber *)importLayoutsInBackgroundWithTaskName:(NSString *)taskName groupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (void)importPortletInfoWithPortletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (void)importPortletInfoWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error;
- (void)importPortletInfoWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (void)importPortletInfoInBackgroundWithTaskName:(NSString *)taskName portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (NSNumber *)importPortletInfoInBackgroundWithTaskName:(NSString *)taskName plid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (NSDictionary *)validateImportLayoutsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error;
- (NSDictionary *)validateImportLayoutsFileWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;
- (NSDictionary *)validateImportPortletInfoWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error;
- (NSDictionary *)validateImportPortletInfoWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error;

@end
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

#import "LRLayoutService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRLayoutService_v7

- (NSArray *)getLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout)
	}];

	NSDictionary *_command = @{@"/layout/get-layouts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parentLayoutId": @(parentLayoutId)
	}];

	NSDictionary *_command = @{@"/layout/get-layouts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId incomplete:(BOOL)incomplete start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parentLayoutId": @(parentLayoutId),
		@"incomplete": @(incomplete),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/layout/get-layouts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateNameWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId name:(NSString *)name languageId:(NSString *)languageId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"name": [self checkNull: name],
		@"languageId": [self checkNull: languageId]
	}];

	NSDictionary *_command = @{@"/layout/update-name": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateNameWithPlid:(long long)plid name:(NSString *)name languageId:(NSString *)languageId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"name": [self checkNull: name],
		@"languageId": [self checkNull: languageId]
	}];

	NSDictionary *_command = @{@"/layout/update-name": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId localeNamesMap:(NSDictionary *)localeNamesMap localeTitlesMap:(NSDictionary *)localeTitlesMap descriptionMap:(NSDictionary *)descriptionMap keywordsMap:(NSDictionary *)keywordsMap robotsMap:(NSDictionary *)robotsMap type:(NSString *)type typeSettings:(NSString *)typeSettings hidden:(BOOL)hidden friendlyURLMap:(NSDictionary *)friendlyURLMap serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parentLayoutId": @(parentLayoutId),
		@"localeNamesMap": [self checkNull: localeNamesMap],
		@"localeTitlesMap": [self checkNull: localeTitlesMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"keywordsMap": [self checkNull: keywordsMap],
		@"robotsMap": [self checkNull: robotsMap],
		@"type": [self checkNull: type],
		@"typeSettings": [self checkNull: typeSettings],
		@"hidden": @(hidden),
		@"friendlyURLMap": [self checkNull: friendlyURLMap],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layout/add-layout": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId name:(NSString *)name title:(NSString *)title description:(NSString *)description type:(NSString *)type hidden:(BOOL)hidden friendlyURL:(NSString *)friendlyURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parentLayoutId": @(parentLayoutId),
		@"name": [self checkNull: name],
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"type": [self checkNull: type],
		@"hidden": @(hidden),
		@"friendlyURL": [self checkNull: friendlyURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layout/add-layout": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId typeSettings:(NSString *)typeSettings error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"typeSettings": [self checkNull: typeSettings]
	}];

	NSDictionary *_command = @{@"/layout/update-layout": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId parentLayoutId:(long long)parentLayoutId localeNamesMap:(NSDictionary *)localeNamesMap localeTitlesMap:(NSDictionary *)localeTitlesMap descriptionMap:(NSDictionary *)descriptionMap keywordsMap:(NSDictionary *)keywordsMap robotsMap:(NSDictionary *)robotsMap type:(NSString *)type hidden:(BOOL)hidden friendlyURLMap:(NSDictionary *)friendlyURLMap iconImage:(BOOL)iconImage iconBytes:(NSData *)iconBytes serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"parentLayoutId": @(parentLayoutId),
		@"localeNamesMap": [self checkNull: localeNamesMap],
		@"localeTitlesMap": [self checkNull: localeTitlesMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"keywordsMap": [self checkNull: keywordsMap],
		@"robotsMap": [self checkNull: robotsMap],
		@"type": [self checkNull: type],
		@"hidden": @(hidden),
		@"friendlyURLMap": [self checkNull: friendlyURLMap],
		@"iconImage": @(iconImage),
		@"iconBytes": [self toString:iconBytes],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layout/update-layout": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)schedulePublishToLiveWithSourceGroupId:(long long)sourceGroupId targetGroupId:(long long)targetGroupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sourceGroupId": @(sourceGroupId),
		@"targetGroupId": @(targetGroupId),
		@"privateLayout": @(privateLayout),
		@"layoutIds": [self checkNull: layoutIds],
		@"parameterMap": [self checkNull: parameterMap],
		@"groupName": [self checkNull: groupName],
		@"cronText": [self checkNull: cronText],
		@"schedulerStartDate": @(schedulerStartDate),
		@"schedulerEndDate": @(schedulerEndDate),
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/layout/schedule-publish-to-live": _params};

	[self.session invoke:_command error:error];
}

- (void)schedulePublishToLiveWithSourceGroupId:(long long)sourceGroupId targetGroupId:(long long)targetGroupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap scope:(NSString *)scope startDate:(long long)startDate endDate:(long long)endDate groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sourceGroupId": @(sourceGroupId),
		@"targetGroupId": @(targetGroupId),
		@"privateLayout": @(privateLayout),
		@"layoutIds": [self checkNull: layoutIds],
		@"parameterMap": [self checkNull: parameterMap],
		@"scope": [self checkNull: scope],
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"groupName": [self checkNull: groupName],
		@"cronText": [self checkNull: cronText],
		@"schedulerStartDate": @(schedulerStartDate),
		@"schedulerEndDate": @(schedulerEndDate),
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/layout/schedule-publish-to-live": _params};

	[self.session invoke:_command error:error];
}

- (void)schedulePublishToLiveWithSourceGroupId:(long long)sourceGroupId targetGroupId:(long long)targetGroupId privateLayout:(BOOL)privateLayout layoutIdMap:(NSDictionary *)layoutIdMap parameterMap:(NSDictionary *)parameterMap scope:(NSString *)scope startDate:(long long)startDate endDate:(long long)endDate groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sourceGroupId": @(sourceGroupId),
		@"targetGroupId": @(targetGroupId),
		@"privateLayout": @(privateLayout),
		@"layoutIdMap": [self checkNull: layoutIdMap],
		@"parameterMap": [self checkNull: parameterMap],
		@"scope": [self checkNull: scope],
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"groupName": [self checkNull: groupName],
		@"cronText": [self checkNull: cronText],
		@"schedulerStartDate": @(schedulerStartDate),
		@"schedulerEndDate": @(schedulerEndDate),
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/layout/schedule-publish-to-live": _params};

	[self.session invoke:_command error:error];
}

- (void)schedulePublishToRemoteWithSourceGroupId:(long long)sourceGroupId privateLayout:(BOOL)privateLayout layoutIdMap:(NSDictionary *)layoutIdMap parameterMap:(NSDictionary *)parameterMap remoteAddress:(NSString *)remoteAddress remotePort:(int)remotePort remotePathContext:(NSString *)remotePathContext secureConnection:(BOOL)secureConnection remoteGroupId:(long long)remoteGroupId remotePrivateLayout:(BOOL)remotePrivateLayout startDate:(long long)startDate endDate:(long long)endDate groupName:(NSString *)groupName cronText:(NSString *)cronText schedulerStartDate:(long long)schedulerStartDate schedulerEndDate:(long long)schedulerEndDate description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"sourceGroupId": @(sourceGroupId),
		@"privateLayout": @(privateLayout),
		@"layoutIdMap": [self checkNull: layoutIdMap],
		@"parameterMap": [self checkNull: parameterMap],
		@"remoteAddress": [self checkNull: remoteAddress],
		@"remotePort": @(remotePort),
		@"remotePathContext": [self checkNull: remotePathContext],
		@"secureConnection": @(secureConnection),
		@"remoteGroupId": @(remoteGroupId),
		@"remotePrivateLayout": @(remotePrivateLayout),
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"groupName": [self checkNull: groupName],
		@"cronText": [self checkNull: cronText],
		@"schedulerStartDate": @(schedulerStartDate),
		@"schedulerEndDate": @(schedulerEndDate),
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/layout/schedule-publish-to-remote": _params};

	[self.session invoke:_command error:error];
}

- (void)unschedulePublishToLiveWithGroupId:(long long)groupId jobName:(NSString *)jobName groupName:(NSString *)groupName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"jobName": [self checkNull: jobName],
		@"groupName": [self checkNull: groupName]
	}];

	NSDictionary *_command = @{@"/layout/unschedule-publish-to-live": _params};

	[self.session invoke:_command error:error];
}

- (void)unschedulePublishToRemoteWithGroupId:(long long)groupId jobName:(NSString *)jobName groupName:(NSString *)groupName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"jobName": [self checkNull: jobName],
		@"groupName": [self checkNull: groupName]
	}];

	NSDictionary *_command = @{@"/layout/unschedule-publish-to-remote": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)getDefaultPlidWithGroupId:(long long)groupId scopeGroupId:(long long)scopeGroupId portletId:(NSString *)portletId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"scopeGroupId": @(scopeGroupId),
		@"portletId": [self checkNull: portletId]
	}];

	NSDictionary *_command = @{@"/layout/get-default-plid": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getDefaultPlidWithGroupId:(long long)groupId scopeGroupId:(long long)scopeGroupId privateLayout:(BOOL)privateLayout portletId:(NSString *)portletId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"scopeGroupId": @(scopeGroupId),
		@"privateLayout": @(privateLayout),
		@"portletId": [self checkNull: portletId]
	}];

	NSDictionary *_command = @{@"/layout/get-default-plid": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getLayoutsCountWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parentLayoutId": @(parentLayoutId)
	}];

	NSDictionary *_command = @{@"/layout/get-layouts-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)setLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parentLayoutId:(long long)parentLayoutId layoutIds:(NSArray *)layoutIds serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parentLayoutId": @(parentLayoutId),
		@"layoutIds": [self checkNull: layoutIds],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layout/set-layouts": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getLayoutByUuidAndGroupIdWithUuid:(NSString *)uuid groupId:(long long)groupId privateLayout:(BOOL)privateLayout error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"uuid": [self checkNull: uuid],
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout)
	}];

	NSDictionary *_command = @{@"/layout/get-layout-by-uuid-and-group-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)exportLayoutsAsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/layout/export-layouts-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)exportLayoutsAsFileWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutIds": [self checkNull: layoutIds],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-layouts-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)exportPortletInfoAsFileWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-portlet-info-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)exportPortletInfoAsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/layout/export-portlet-info-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)exportPortletInfoAsFileWithPortletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-portlet-info-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getTempFileNamesWithGroupId:(long long)groupId folderName:(NSString *)folderName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderName": [self checkNull: folderName]
	}];

	NSDictionary *_command = @{@"/layout/get-temp-file-names": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)deleteTempFileEntryWithGroupId:(long long)groupId folderName:(NSString *)folderName fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderName": [self checkNull: folderName],
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/layout/delete-temp-file-entry": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteLayoutWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layout/delete-layout": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteLayoutWithPlid:(long long)plid serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/layout/delete-layout": _params};

	[self.session invoke:_command error:error];
}

- (void)importLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap bytes:(NSData *)bytes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap],
		@"bytes": [self toString:bytes]
	}];

	NSDictionary *_command = @{@"/layout/import-layouts": _params};

	[self.session invoke:_command error:error];
}

- (void)importLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/import-layouts": _params};

	[self.session invoke:_command error:error];
}

- (void)importLayoutsWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/layout/import-layouts": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLookAndFeelWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId themeId:(NSString *)themeId colorSchemeId:(NSString *)colorSchemeId css:(NSString *)css error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"themeId": [self checkNull: themeId],
		@"colorSchemeId": [self checkNull: colorSchemeId],
		@"css": [self checkNull: css]
	}];

	NSDictionary *_command = @{@"/layout/update-look-and-feel": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getAncestorLayoutsWithPlid:(long long)plid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid)
	}];

	NSDictionary *_command = @{@"/layout/get-ancestor-layouts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)exportLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-layouts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)exportLayoutsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutIds": [self checkNull: layoutIds],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-layouts": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)exportPortletInfoWithCompanyId:(long long)companyId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-portlet-info": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)exportPortletInfoWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-portlet-info": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateIconImageWithPlid:(long long)plid bytes:(NSData *)bytes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"bytes": [self toString:bytes]
	}];

	NSDictionary *_command = @{@"/layout/update-icon-image": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateParentLayoutIdWithPlid:(long long)plid parentPlid:(long long)parentPlid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"parentPlid": @(parentPlid)
	}];

	NSDictionary *_command = @{@"/layout/update-parent-layout-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateParentLayoutIdWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId parentLayoutId:(long long)parentLayoutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"parentLayoutId": @(parentLayoutId)
	}];

	NSDictionary *_command = @{@"/layout/update-parent-layout-id": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateParentLayoutIdAndPriorityWithPlid:(long long)plid parentPlid:(long long)parentPlid priority:(int)priority error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"parentPlid": @(parentPlid),
		@"priority": @(priority)
	}];

	NSDictionary *_command = @{@"/layout/update-parent-layout-id-and-priority": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePriorityWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId priority:(int)priority error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"priority": @(priority)
	}];

	NSDictionary *_command = @{@"/layout/update-priority": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePriorityWithPlid:(long long)plid priority:(int)priority error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"priority": @(priority)
	}];

	NSDictionary *_command = @{@"/layout/update-priority": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePriorityWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId nextLayoutId:(long long)nextLayoutId previousLayoutId:(long long)previousLayoutId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"nextLayoutId": @(nextLayoutId),
		@"previousLayoutId": @(previousLayoutId)
	}];

	NSDictionary *_command = @{@"/layout/update-priority": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSString *)getLayoutNameWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutId:(long long)layoutId languageId:(NSString *)languageId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutId": @(layoutId),
		@"languageId": [self checkNull: languageId]
	}];

	NSDictionary *_command = @{@"/layout/get-layout-name": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSArray *)getLayoutReferencesWithCompanyId:(long long)companyId portletId:(NSString *)portletId preferencesKey:(NSString *)preferencesKey preferencesValue:(NSString *)preferencesValue error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"portletId": [self checkNull: portletId],
		@"preferencesKey": [self checkNull: preferencesKey],
		@"preferencesValue": [self checkNull: preferencesValue]
	}];

	NSDictionary *_command = @{@"/layout/get-layout-references": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportLayoutsAsFileInBackgroundWithTaskName:(NSString *)taskName groupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutIds": [self checkNull: layoutIds],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/layout/export-layouts-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportLayoutsAsFileInBackgroundWithTaskName:(NSString *)taskName groupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutIds:(NSArray *)layoutIds parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutIds": [self checkNull: layoutIds],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate)
	}];

	NSDictionary *_command = @{@"/layout/export-layouts-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportLayoutsAsFileInBackgroundWithExportImportConfigurationId:(long long)exportImportConfigurationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"exportImportConfigurationId": @(exportImportConfigurationId)
	}];

	NSDictionary *_command = @{@"/layout/export-layouts-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportLayoutsAsFileInBackgroundWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/layout/export-layouts-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportPortletInfoAsFileInBackgroundWithTaskName:(NSString *)taskName portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/layout/export-portlet-info-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportPortletInfoAsFileInBackgroundWithTaskName:(NSString *)taskName plid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap startDate:(long long)startDate endDate:(long long)endDate fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"plid": @(plid),
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"startDate": @(startDate),
		@"endDate": @(endDate),
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/layout/export-portlet-info-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)importLayoutsInBackgroundWithTaskName:(NSString *)taskName groupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/import-layouts-in-background": _params};

	return (NSNumber *)[self.session upload:_command error:error];
}

- (void)importPortletInfoWithPortletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/import-portlet-info": _params};

	[self.session invoke:_command error:error];
}

- (void)importPortletInfoWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/layout/import-portlet-info": _params};

	[self.session invoke:_command error:error];
}

- (void)importPortletInfoWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/import-portlet-info": _params};

	[self.session invoke:_command error:error];
}

- (void)importPortletInfoInBackgroundWithTaskName:(NSString *)taskName portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/import-portlet-info-in-background": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)importPortletInfoInBackgroundWithTaskName:(NSString *)taskName plid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"taskName": [self checkNull: taskName],
		@"plid": @(plid),
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/import-portlet-info-in-background": _params};

	return (NSNumber *)[self.session upload:_command error:error];
}

- (NSDictionary *)validateImportLayoutsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/layout/validate-import-layouts-file": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)validateImportLayoutsFileWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/validate-import-layouts-file": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)validateImportPortletInfoWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/layout/validate-import-portlet-info": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)validateImportPortletInfoWithPlid:(long long)plid groupId:(long long)groupId portletId:(NSString *)portletId parameterMap:(NSDictionary *)parameterMap file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"plid": @(plid),
		@"groupId": @(groupId),
		@"portletId": [self checkNull: portletId],
		@"parameterMap": [self checkNull: parameterMap],
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layout/validate-import-portlet-info": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

@end
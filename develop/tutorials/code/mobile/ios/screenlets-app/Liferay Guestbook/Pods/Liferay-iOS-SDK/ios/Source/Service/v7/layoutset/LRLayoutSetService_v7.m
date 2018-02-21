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

#import "LRLayoutSetService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRLayoutSetService_v7

- (NSDictionary *)updateVirtualHostWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout virtualHost:(NSString *)virtualHost error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"virtualHost": [self checkNull: virtualHost]
	}];

	NSDictionary *_command = @{@"/layoutset/update-virtual-host": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateSettingsWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout settings:(NSString *)settings error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"settings": [self checkNull: settings]
	}];

	NSDictionary *_command = @{@"/layoutset/update-settings": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateLookAndFeelWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout themeId:(NSString *)themeId colorSchemeId:(NSString *)colorSchemeId css:(NSString *)css error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"themeId": [self checkNull: themeId],
		@"colorSchemeId": [self checkNull: colorSchemeId],
		@"css": [self checkNull: css]
	}];

	NSDictionary *_command = @{@"/layoutset/update-look-and-feel": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)updateLayoutSetPrototypeLinkEnabledWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout layoutSetPrototypeLinkEnabled:(BOOL)layoutSetPrototypeLinkEnabled layoutSetPrototypeUuid:(NSString *)layoutSetPrototypeUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"layoutSetPrototypeLinkEnabled": @(layoutSetPrototypeLinkEnabled),
		@"layoutSetPrototypeUuid": [self checkNull: layoutSetPrototypeUuid]
	}];

	NSDictionary *_command = @{@"/layoutset/update-layout-set-prototype-link-enabled": _params};

	[self.session invoke:_command error:error];
}

- (void)updateLogoWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout logo:(BOOL)logo file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"logo": @(logo),
		@"file": [self checkNull: file]
	}];

	NSDictionary *_command = @{@"/layoutset/update-logo": _params};

	[self.session invoke:_command error:error];
}

- (void)updateLogoWithGroupId:(long long)groupId privateLayout:(BOOL)privateLayout logo:(BOOL)logo bytes:(NSData *)bytes error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"privateLayout": @(privateLayout),
		@"logo": @(logo),
		@"bytes": [self toString:bytes]
	}];

	NSDictionary *_command = @{@"/layoutset/update-logo": _params};

	[self.session invoke:_command error:error];
}

@end
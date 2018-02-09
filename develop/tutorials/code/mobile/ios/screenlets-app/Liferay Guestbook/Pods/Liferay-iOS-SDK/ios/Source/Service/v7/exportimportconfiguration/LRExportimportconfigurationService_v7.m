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

#import "LRExportimportconfigurationService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRExportimportconfigurationService_v7

- (void)deleteExportImportConfigurationWithExportImportConfigurationId:(long long)exportImportConfigurationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"exportImportConfigurationId": @(exportImportConfigurationId)
	}];

	NSDictionary *_command = @{@"/exportimportconfiguration/delete-export-import-configuration": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)moveExportImportConfigurationToTrashWithExportImportConfigurationId:(long long)exportImportConfigurationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"exportImportConfigurationId": @(exportImportConfigurationId)
	}];

	NSDictionary *_command = @{@"/exportimportconfiguration/move-export-import-configuration-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)restoreExportImportConfigurationFromTrashWithExportImportConfigurationId:(long long)exportImportConfigurationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"exportImportConfigurationId": @(exportImportConfigurationId)
	}];

	NSDictionary *_command = @{@"/exportimportconfiguration/restore-export-import-configuration-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
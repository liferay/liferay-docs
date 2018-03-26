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

#import "LRExportimportService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRExportimportService_v7

- (NSDictionary *)exportLayoutsAsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/exportimport/export-layouts-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)exportPortletInfoAsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/exportimport/export-portlet-info-as-file": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)importLayoutsWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/exportimport/import-layouts": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)exportLayoutsAsFileInBackgroundWithExportImportConfigurationId:(long long)exportImportConfigurationId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"exportImportConfigurationId": @(exportImportConfigurationId)
	}];

	NSDictionary *_command = @{@"/exportimport/export-layouts-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportLayoutsAsFileInBackgroundWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/exportimport/export-layouts-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)exportPortletInfoAsFileInBackgroundWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];

	NSDictionary *_command = @{@"/exportimport/export-portlet-info-as-file-in-background": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)importLayoutsInBackgroundWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/exportimport/import-layouts-in-background": _params};

	return (NSNumber *)[self.session upload:_command error:error];
}

- (void)importPortletInfoWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/exportimport/import-portlet-info": _params};

	[self.session invoke:_command error:error];
}

- (NSNumber *)importPortletInfoInBackgroundWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/exportimport/import-portlet-info-in-background": _params};

	return (NSNumber *)[self.session upload:_command error:error];
}

- (NSDictionary *)validateImportLayoutsFileWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/exportimport/validate-import-layouts-file": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)validateImportPortletInfoWithExportImportConfiguration:(LRJSONObjectWrapper *)exportImportConfiguration file:(LRUploadData *)file error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"file": [self checkNull: file]
	}];

	[self mangleWrapperWithParams:_params name:@"exportImportConfiguration" className:@"com.liferay.exportimport.kernel.model.ExportImportConfiguration" wrapper:exportImportConfiguration];
	NSDictionary *_command = @{@"/exportimport/validate-import-portlet-info": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

@end
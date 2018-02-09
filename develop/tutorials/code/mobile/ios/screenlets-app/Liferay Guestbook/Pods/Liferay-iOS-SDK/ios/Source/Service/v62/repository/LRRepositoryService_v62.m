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

#import "LRRepositoryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRRepositoryService_v62

- (NSDictionary *)addRepositoryWithGroupId:(long long)groupId classNameId:(long long)classNameId parentFolderId:(long long)parentFolderId name:(NSString *)name description:(NSString *)description portletId:(NSString *)portletId typeSettingsProperties:(LRJSONObjectWrapper *)typeSettingsProperties serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"parentFolderId": @(parentFolderId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"portletId": [self checkNull: portletId],
	}];

	[self mangleWrapperWithParams:_params name:@"typeSettingsProperties" className:@"com.liferay.portal.kernel.util.UnicodeProperties" wrapper:typeSettingsProperties];
	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/repository/add-repository": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)checkRepositoryWithRepositoryId:(long long)repositoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId)
	}];

	NSDictionary *_command = @{@"/repository/check-repository": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteRepositoryWithRepositoryId:(long long)repositoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId)
	}];

	NSDictionary *_command = @{@"/repository/delete-repository": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getLocalRepositoryImplWithRepositoryId:(long long)repositoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId)
	}];

	NSDictionary *_command = @{@"/repository/get-local-repository-impl": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getLocalRepositoryImplWithFolderId:(long long)folderId fileEntryId:(long long)fileEntryId fileVersionId:(long long)fileVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"fileEntryId": @(fileEntryId),
		@"fileVersionId": @(fileVersionId)
	}];

	NSDictionary *_command = @{@"/repository/get-local-repository-impl": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRepositoryWithRepositoryId:(long long)repositoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId)
	}];

	NSDictionary *_command = @{@"/repository/get-repository": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRepositoryImplWithRepositoryId:(long long)repositoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId)
	}];

	NSDictionary *_command = @{@"/repository/get-repository-impl": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getRepositoryImplWithFolderId:(long long)folderId fileEntryId:(long long)fileEntryId fileVersionId:(long long)fileVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"folderId": @(folderId),
		@"fileEntryId": @(fileEntryId),
		@"fileVersionId": @(fileVersionId)
	}];

	NSDictionary *_command = @{@"/repository/get-repository-impl": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getSupportedConfigurationsWithClassNameId:(long long)classNameId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameId": @(classNameId)
	}];

	NSDictionary *_command = @{@"/repository/get-supported-configurations": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getSupportedParametersWithClassNameId:(long long)classNameId configuration:(NSString *)configuration error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classNameId": @(classNameId),
		@"configuration": [self checkNull: configuration]
	}];

	NSDictionary *_command = @{@"/repository/get-supported-parameters": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTypeSettingsPropertiesWithRepositoryId:(long long)repositoryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId)
	}];

	NSDictionary *_command = @{@"/repository/get-type-settings-properties": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)updateRepositoryWithRepositoryId:(long long)repositoryId name:(NSString *)name description:(NSString *)description error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"repositoryId": @(repositoryId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description]
	}];

	NSDictionary *_command = @{@"/repository/update-repository": _params};

	[self.session invoke:_command error:error];
}

@end
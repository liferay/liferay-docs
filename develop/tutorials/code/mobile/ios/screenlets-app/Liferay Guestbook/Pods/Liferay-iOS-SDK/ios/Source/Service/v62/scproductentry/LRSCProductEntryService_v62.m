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

#import "LRSCProductEntryService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRSCProductEntryService_v62

- (NSDictionary *)addProductEntryWithName:(NSString *)name type:(NSString *)type tags:(NSString *)tags shortDescription:(NSString *)shortDescription longDescription:(NSString *)longDescription pageURL:(NSString *)pageURL author:(NSString *)author repoGroupId:(NSString *)repoGroupId repoArtifactId:(NSString *)repoArtifactId licenseIds:(NSArray *)licenseIds thumbnails:(NSArray *)thumbnails fullImages:(NSArray *)fullImages serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"tags": [self checkNull: tags],
		@"shortDescription": [self checkNull: shortDescription],
		@"longDescription": [self checkNull: longDescription],
		@"pageURL": [self checkNull: pageURL],
		@"author": [self checkNull: author],
		@"repoGroupId": [self checkNull: repoGroupId],
		@"repoArtifactId": [self checkNull: repoArtifactId],
		@"licenseIds": [self checkNull: licenseIds],
		@"thumbnails": [self checkNull: thumbnails],
		@"fullImages": [self checkNull: fullImages],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/scproductentry/add-product-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteProductEntryWithProductEntryId:(long long)productEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"productEntryId": @(productEntryId)
	}];

	NSDictionary *_command = @{@"/scproductentry/delete-product-entry": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getProductEntryWithProductEntryId:(long long)productEntryId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"productEntryId": @(productEntryId)
	}];

	NSDictionary *_command = @{@"/scproductentry/get-product-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateProductEntryWithProductEntryId:(long long)productEntryId name:(NSString *)name type:(NSString *)type tags:(NSString *)tags shortDescription:(NSString *)shortDescription longDescription:(NSString *)longDescription pageURL:(NSString *)pageURL author:(NSString *)author repoGroupId:(NSString *)repoGroupId repoArtifactId:(NSString *)repoArtifactId licenseIds:(NSArray *)licenseIds thumbnails:(NSArray *)thumbnails fullImages:(NSArray *)fullImages error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"productEntryId": @(productEntryId),
		@"name": [self checkNull: name],
		@"type": [self checkNull: type],
		@"tags": [self checkNull: tags],
		@"shortDescription": [self checkNull: shortDescription],
		@"longDescription": [self checkNull: longDescription],
		@"pageURL": [self checkNull: pageURL],
		@"author": [self checkNull: author],
		@"repoGroupId": [self checkNull: repoGroupId],
		@"repoArtifactId": [self checkNull: repoArtifactId],
		@"licenseIds": [self checkNull: licenseIds],
		@"thumbnails": [self checkNull: thumbnails],
		@"fullImages": [self checkNull: fullImages]
	}];

	NSDictionary *_command = @{@"/scproductentry/update-product-entry": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
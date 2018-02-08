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

#import "LRJournalFeedService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRJournalFeedService_v7

- (NSDictionary *)addFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId autoFeedId:(BOOL)autoFeedId name:(NSString *)name description:(NSString *)description ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey ddmRendererTemplateKey:(NSString *)ddmRendererTemplateKey delta:(int)delta orderByCol:(NSString *)orderByCol orderByType:(NSString *)orderByType targetLayoutFriendlyUrl:(NSString *)targetLayoutFriendlyUrl targetPortletId:(NSString *)targetPortletId contentField:(NSString *)contentField feedType:(NSString *)feedType feedVersion:(double)feedVersion serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"feedId": [self checkNull: feedId],
		@"autoFeedId": @(autoFeedId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"ddmRendererTemplateKey": [self checkNull: ddmRendererTemplateKey],
		@"delta": @(delta),
		@"orderByCol": [self checkNull: orderByCol],
		@"orderByType": [self checkNull: orderByType],
		@"targetLayoutFriendlyUrl": [self checkNull: targetLayoutFriendlyUrl],
		@"targetPortletId": [self checkNull: targetPortletId],
		@"contentField": [self checkNull: contentField],
		@"feedType": [self checkNull: feedType],
		@"feedVersion": @(feedVersion),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalfeed/add-feed": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"feedId": [self checkNull: feedId]
	}];

	NSDictionary *_command = @{@"/journal.journalfeed/delete-feed": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteFeedWithFeedId:(long long)feedId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"feedId": @(feedId)
	}];

	NSDictionary *_command = @{@"/journal.journalfeed/delete-feed": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"feedId": [self checkNull: feedId]
	}];

	NSDictionary *_command = @{@"/journal.journalfeed/get-feed": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getFeedWithFeedId:(long long)feedId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"feedId": @(feedId)
	}];

	NSDictionary *_command = @{@"/journal.journalfeed/get-feed": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateFeedWithGroupId:(long long)groupId feedId:(NSString *)feedId name:(NSString *)name description:(NSString *)description ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey ddmRendererTemplateKey:(NSString *)ddmRendererTemplateKey delta:(int)delta orderByCol:(NSString *)orderByCol orderByType:(NSString *)orderByType targetLayoutFriendlyUrl:(NSString *)targetLayoutFriendlyUrl targetPortletId:(NSString *)targetPortletId contentField:(NSString *)contentField feedType:(NSString *)feedType feedVersion:(double)feedVersion serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"feedId": [self checkNull: feedId],
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"ddmRendererTemplateKey": [self checkNull: ddmRendererTemplateKey],
		@"delta": @(delta),
		@"orderByCol": [self checkNull: orderByCol],
		@"orderByType": [self checkNull: orderByType],
		@"targetLayoutFriendlyUrl": [self checkNull: targetLayoutFriendlyUrl],
		@"targetPortletId": [self checkNull: targetPortletId],
		@"contentField": [self checkNull: contentField],
		@"feedType": [self checkNull: feedType],
		@"feedVersion": @(feedVersion),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalfeed/update-feed": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
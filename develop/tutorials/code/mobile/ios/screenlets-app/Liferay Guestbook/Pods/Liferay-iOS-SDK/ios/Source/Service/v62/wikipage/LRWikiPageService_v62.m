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

#import "LRWikiPageService_v62.h"

/**
 * @author Bruno Farache
 */
@implementation LRWikiPageService_v62

- (NSDictionary *)addPageWithNodeId:(long long)nodeId title:(NSString *)title content:(NSString *)content summary:(NSString *)summary minorEdit:(BOOL)minorEdit serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"content": [self checkNull: content],
		@"summary": [self checkNull: summary],
		@"minorEdit": @(minorEdit),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/wikipage/add-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addPageWithNodeId:(long long)nodeId title:(NSString *)title content:(NSString *)content summary:(NSString *)summary minorEdit:(BOOL)minorEdit format:(NSString *)format parentTitle:(NSString *)parentTitle redirectTitle:(NSString *)redirectTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"content": [self checkNull: content],
		@"summary": [self checkNull: summary],
		@"minorEdit": @(minorEdit),
		@"format": [self checkNull: format],
		@"parentTitle": [self checkNull: parentTitle],
		@"redirectTitle": [self checkNull: redirectTitle],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/wikipage/add-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)addPageAttachmentWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName file:(LRUploadData *)file mimeType:(NSString *)mimeType error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"fileName": [self checkNull: fileName],
		@"file": [self checkNull: file],
		@"mimeType": [self checkNull: mimeType]
	}];

	NSDictionary *_command = @{@"/wikipage/add-page-attachment": _params};

	[self.session invoke:_command error:error];
}

- (void)addPageAttachmentsWithNodeId:(long long)nodeId title:(NSString *)title inputStreamOVPs:(NSArray *)inputStreamOVPs error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"inputStreamOVPs": [self checkNull: inputStreamOVPs]
	}];

	NSDictionary *_command = @{@"/wikipage/add-page-attachments": _params};

	[self.session invoke:_command error:error];
}

- (void)changeParentWithNodeId:(long long)nodeId title:(NSString *)title newParentTitle:(NSString *)newParentTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"newParentTitle": [self checkNull: newParentTitle],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/wikipage/change-parent": _params};

	[self.session invoke:_command error:error];
}

- (void)copyPageAttachmentsWithTemplateNodeId:(long long)templateNodeId templateTitle:(NSString *)templateTitle nodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateNodeId": @(templateNodeId),
		@"templateTitle": [self checkNull: templateTitle],
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/copy-page-attachments": _params};

	[self.session invoke:_command error:error];
}

- (void)deletePageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/delete-page": _params};

	[self.session invoke:_command error:error];
}

- (void)deletePageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"version": @(version)
	}];

	NSDictionary *_command = @{@"/wikipage/delete-page": _params};

	[self.session invoke:_command error:error];
}

- (void)deletePageAttachmentWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/wikipage/delete-page-attachment": _params};

	[self.session invoke:_command error:error];
}

- (void)deletePageAttachmentsWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/delete-page-attachments": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteTempPageAttachmentWithNodeId:(long long)nodeId fileName:(NSString *)fileName tempFolderName:(NSString *)tempFolderName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"fileName": [self checkNull: fileName],
		@"tempFolderName": [self checkNull: tempFolderName]
	}];

	NSDictionary *_command = @{@"/wikipage/delete-temp-page-attachment": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteTrashPageAttachmentsWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/delete-trash-page-attachments": _params};

	[self.session invoke:_command error:error];
}

- (void)discardDraftWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"version": @(version)
	}];

	NSDictionary *_command = @{@"/wikipage/discard-draft": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getChildrenWithGroupId:(long long)groupId nodeId:(long long)nodeId head:(BOOL)head parentTitle:(NSString *)parentTitle error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId),
		@"head": @(head),
		@"parentTitle": [self checkNull: parentTitle]
	}];

	NSDictionary *_command = @{@"/wikipage/get-children": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getDraftPageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/get-draft-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getNodePagesWithNodeId:(long long)nodeId max:(int)max error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"max": @(max)
	}];

	NSDictionary *_command = @{@"/wikipage/get-node-pages": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSString *)getNodePagesRssWithNodeId:(long long)nodeId max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL]
	}];

	NSDictionary *_command = @{@"/wikipage/get-node-pages-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getNodePagesRssWithNodeId:(long long)nodeId max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL attachmentURLPrefix:(NSString *)attachmentURLPrefix error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL],
		@"attachmentURLPrefix": [self checkNull: attachmentURLPrefix]
	}];

	NSDictionary *_command = @{@"/wikipage/get-node-pages-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSArray *)getOrphansWithGroupId:(long long)groupId nodeId:(long long)nodeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId)
	}];

	NSDictionary *_command = @{@"/wikipage/get-orphans": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getPageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/get-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getPageWithGroupId:(long long)groupId nodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/get-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getPageWithNodeId:(long long)nodeId title:(NSString *)title head:(BOOL)head error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"head": @(head)
	}];

	NSDictionary *_command = @{@"/wikipage/get-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getPageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"version": @(version)
	}];

	NSDictionary *_command = @{@"/wikipage/get-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getPagesWithGroupId:(long long)groupId userId:(long long)userId nodeId:(long long)nodeId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"nodeId": @(nodeId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/wikipage/get-pages": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getPagesWithGroupId:(long long)groupId nodeId:(long long)nodeId head:(BOOL)head status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId),
		@"head": @(head),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator" wrapper:obc];

	NSDictionary *_command = @{@"/wikipage/get-pages": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getPagesCountWithGroupId:(long long)groupId nodeId:(long long)nodeId head:(BOOL)head error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId),
		@"head": @(head)
	}];

	NSDictionary *_command = @{@"/wikipage/get-pages-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getPagesCountWithGroupId:(long long)groupId userId:(long long)userId nodeId:(long long)nodeId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"nodeId": @(nodeId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/wikipage/get-pages-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSString *)getPagesRssWithCompanyId:(long long)companyId nodeId:(long long)nodeId title:(NSString *)title max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL locale:(NSString *)locale error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL],
		@"locale": [self checkNull: locale]
	}];

	NSDictionary *_command = @{@"/wikipage/get-pages-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getPagesRssWithCompanyId:(long long)companyId nodeId:(long long)nodeId title:(NSString *)title max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL attachmentURLPrefix:(NSString *)attachmentURLPrefix locale:(NSString *)locale error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"max": @(max),
		@"type": [self checkNull: type],
		@"version": @(version),
		@"displayStyle": [self checkNull: displayStyle],
		@"feedURL": [self checkNull: feedURL],
		@"entryURL": [self checkNull: entryURL],
		@"attachmentURLPrefix": [self checkNull: attachmentURLPrefix],
		@"locale": [self checkNull: locale]
	}];

	NSDictionary *_command = @{@"/wikipage/get-pages-rss": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSArray *)getRecentChangesWithGroupId:(long long)groupId nodeId:(long long)nodeId start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/wikipage/get-recent-changes": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getRecentChangesCountWithGroupId:(long long)groupId nodeId:(long long)nodeId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"nodeId": @(nodeId)
	}];

	NSDictionary *_command = @{@"/wikipage/get-recent-changes-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getTempPageAttachmentNamesWithNodeId:(long long)nodeId tempFolderName:(NSString *)tempFolderName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"tempFolderName": [self checkNull: tempFolderName]
	}];

	NSDictionary *_command = @{@"/wikipage/get-temp-page-attachment-names": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (void)movePageWithNodeId:(long long)nodeId title:(NSString *)title newTitle:(NSString *)newTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"newTitle": [self checkNull: newTitle],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/wikipage/move-page": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)movePageAttachmentToTrashWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/wikipage/move-page-attachment-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)movePageToTrashWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/move-page-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)movePageToTrashWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"version": @(version)
	}];

	NSDictionary *_command = @{@"/wikipage/move-page-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)restorePageAttachmentFromTrashWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"fileName": [self checkNull: fileName]
	}];

	NSDictionary *_command = @{@"/wikipage/restore-page-attachment-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (void)restorePageFromTrashWithResourcePrimKey:(long long)resourcePrimKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"resourcePrimKey": @(resourcePrimKey)
	}];

	NSDictionary *_command = @{@"/wikipage/restore-page-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)revertPageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"version": @(version),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/wikipage/revert-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)subscribePageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/subscribe-page": _params};

	[self.session invoke:_command error:error];
}

- (void)unsubscribePageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title]
	}];

	NSDictionary *_command = @{@"/wikipage/unsubscribe-page": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updatePageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version content:(NSString *)content summary:(NSString *)summary minorEdit:(BOOL)minorEdit format:(NSString *)format parentTitle:(NSString *)parentTitle redirectTitle:(NSString *)redirectTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"nodeId": @(nodeId),
		@"title": [self checkNull: title],
		@"version": @(version),
		@"content": [self checkNull: content],
		@"summary": [self checkNull: summary],
		@"minorEdit": @(minorEdit),
		@"format": [self checkNull: format],
		@"parentTitle": [self checkNull: parentTitle],
		@"redirectTitle": [self checkNull: redirectTitle],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/wikipage/update-page": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
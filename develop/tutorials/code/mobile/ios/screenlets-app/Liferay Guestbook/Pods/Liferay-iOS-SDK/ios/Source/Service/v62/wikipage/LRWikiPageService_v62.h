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
@interface LRWikiPageService_v62 : LRBaseService

- (NSDictionary *)addPageWithNodeId:(long long)nodeId title:(NSString *)title content:(NSString *)content summary:(NSString *)summary minorEdit:(BOOL)minorEdit serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addPageWithNodeId:(long long)nodeId title:(NSString *)title content:(NSString *)content summary:(NSString *)summary minorEdit:(BOOL)minorEdit format:(NSString *)format parentTitle:(NSString *)parentTitle redirectTitle:(NSString *)redirectTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)addPageAttachmentWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName file:(LRUploadData *)file mimeType:(NSString *)mimeType error:(NSError **)error;
- (void)addPageAttachmentsWithNodeId:(long long)nodeId title:(NSString *)title inputStreamOVPs:(NSArray *)inputStreamOVPs error:(NSError **)error;
- (void)changeParentWithNodeId:(long long)nodeId title:(NSString *)title newParentTitle:(NSString *)newParentTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)copyPageAttachmentsWithTemplateNodeId:(long long)templateNodeId templateTitle:(NSString *)templateTitle nodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (void)deletePageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (void)deletePageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error;
- (void)deletePageAttachmentWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName error:(NSError **)error;
- (void)deletePageAttachmentsWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (void)deleteTempPageAttachmentWithNodeId:(long long)nodeId fileName:(NSString *)fileName tempFolderName:(NSString *)tempFolderName error:(NSError **)error;
- (void)deleteTrashPageAttachmentsWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (void)discardDraftWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error;
- (NSArray *)getChildrenWithGroupId:(long long)groupId nodeId:(long long)nodeId head:(BOOL)head parentTitle:(NSString *)parentTitle error:(NSError **)error;
- (NSDictionary *)getDraftPageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (NSArray *)getNodePagesWithNodeId:(long long)nodeId max:(int)max error:(NSError **)error;
- (NSString *)getNodePagesRssWithNodeId:(long long)nodeId max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL error:(NSError **)error;
- (NSString *)getNodePagesRssWithNodeId:(long long)nodeId max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL attachmentURLPrefix:(NSString *)attachmentURLPrefix error:(NSError **)error;
- (NSArray *)getOrphansWithGroupId:(long long)groupId nodeId:(long long)nodeId error:(NSError **)error;
- (NSDictionary *)getPageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (NSDictionary *)getPageWithGroupId:(long long)groupId nodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (NSDictionary *)getPageWithNodeId:(long long)nodeId title:(NSString *)title head:(BOOL)head error:(NSError **)error;
- (NSDictionary *)getPageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error;
- (NSArray *)getPagesWithGroupId:(long long)groupId userId:(long long)userId nodeId:(long long)nodeId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getPagesWithGroupId:(long long)groupId nodeId:(long long)nodeId head:(BOOL)head status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSNumber *)getPagesCountWithGroupId:(long long)groupId nodeId:(long long)nodeId head:(BOOL)head error:(NSError **)error;
- (NSNumber *)getPagesCountWithGroupId:(long long)groupId userId:(long long)userId nodeId:(long long)nodeId status:(int)status error:(NSError **)error;
- (NSString *)getPagesRssWithCompanyId:(long long)companyId nodeId:(long long)nodeId title:(NSString *)title max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL locale:(NSString *)locale error:(NSError **)error;
- (NSString *)getPagesRssWithCompanyId:(long long)companyId nodeId:(long long)nodeId title:(NSString *)title max:(int)max type:(NSString *)type version:(double)version displayStyle:(NSString *)displayStyle feedURL:(NSString *)feedURL entryURL:(NSString *)entryURL attachmentURLPrefix:(NSString *)attachmentURLPrefix locale:(NSString *)locale error:(NSError **)error;
- (NSArray *)getRecentChangesWithGroupId:(long long)groupId nodeId:(long long)nodeId start:(int)start end:(int)end error:(NSError **)error;
- (NSNumber *)getRecentChangesCountWithGroupId:(long long)groupId nodeId:(long long)nodeId error:(NSError **)error;
- (NSArray *)getTempPageAttachmentNamesWithNodeId:(long long)nodeId tempFolderName:(NSString *)tempFolderName error:(NSError **)error;
- (void)movePageWithNodeId:(long long)nodeId title:(NSString *)title newTitle:(NSString *)newTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)movePageAttachmentToTrashWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName error:(NSError **)error;
- (NSDictionary *)movePageToTrashWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (NSDictionary *)movePageToTrashWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version error:(NSError **)error;
- (void)restorePageAttachmentFromTrashWithNodeId:(long long)nodeId title:(NSString *)title fileName:(NSString *)fileName error:(NSError **)error;
- (void)restorePageFromTrashWithResourcePrimKey:(long long)resourcePrimKey error:(NSError **)error;
- (NSDictionary *)revertPageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)subscribePageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (void)unsubscribePageWithNodeId:(long long)nodeId title:(NSString *)title error:(NSError **)error;
- (NSDictionary *)updatePageWithNodeId:(long long)nodeId title:(NSString *)title version:(double)version content:(NSString *)content summary:(NSString *)summary minorEdit:(BOOL)minorEdit format:(NSString *)format parentTitle:(NSString *)parentTitle redirectTitle:(NSString *)redirectTitle serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;

@end
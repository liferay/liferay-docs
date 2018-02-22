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
@interface LRWikiNodeService_v7 : LRBaseService

- (NSDictionary *)getNodeWithNodeId:(long long)nodeId error:(NSError **)error;
- (NSDictionary *)getNodeWithGroupId:(long long)groupId name:(NSString *)name error:(NSError **)error;
- (NSDictionary *)updateNodeWithNodeId:(long long)nodeId name:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)addNodeWithName:(NSString *)name description:(NSString *)description serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSArray *)getNodesWithGroupId:(long long)groupId status:(int)status start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getNodesWithGroupId:(long long)groupId start:(int)start end:(int)end error:(NSError **)error;
- (NSArray *)getNodesWithGroupId:(long long)groupId status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error;
- (NSArray *)getNodesWithGroupId:(long long)groupId error:(NSError **)error;
- (NSArray *)getNodesWithGroupId:(long long)groupId status:(int)status error:(NSError **)error;
- (NSNumber *)getNodesCountWithGroupId:(long long)groupId status:(int)status error:(NSError **)error;
- (NSNumber *)getNodesCountWithGroupId:(long long)groupId error:(NSError **)error;
- (NSDictionary *)moveNodeToTrashWithNodeId:(long long)nodeId error:(NSError **)error;
- (void)restoreNodeFromTrashWithNodeId:(long long)nodeId error:(NSError **)error;
- (void)subscribeNodeWithNodeId:(long long)nodeId error:(NSError **)error;
- (void)unsubscribeNodeWithNodeId:(long long)nodeId error:(NSError **)error;
- (void)deleteNodeWithNodeId:(long long)nodeId error:(NSError **)error;

@end
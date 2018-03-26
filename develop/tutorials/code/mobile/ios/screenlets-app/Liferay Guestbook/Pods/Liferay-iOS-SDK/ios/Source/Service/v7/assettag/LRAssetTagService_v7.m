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

#import "LRAssetTagService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRAssetTagService_v7

- (NSArray *)searchWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assettag/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithGroupIds:(NSArray *)groupIds name:(NSString *)name start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assettag/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTagWithTagId:(long long)tagId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagId": @(tagId)
	}];

	NSDictionary *_command = @{@"/assettag/get-tag": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteTagWithTagId:(long long)tagId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagId": @(tagId)
	}];

	NSDictionary *_command = @{@"/assettag/delete-tag": _params};

	[self.session invoke:_command error:error];
}

- (NSArray *)getGroupsTagsWithGroupIds:(NSArray *)groupIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds]
	}];

	NSDictionary *_command = @{@"/assettag/get-groups-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupTagsWithGroupId:(long long)groupId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag>" wrapper:obc];

	NSDictionary *_command = @{@"/assettag/get-group-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupTagsWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/assettag/get-group-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupTagsCountWithGroupId:(long long)groupId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId)
	}];

	NSDictionary *_command = @{@"/assettag/get-group-tags-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)mergeTagsWithFromTagIds:(NSArray *)fromTagIds toTagId:(long long)toTagId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fromTagIds": [self checkNull: fromTagIds],
		@"toTagId": @(toTagId)
	}];

	NSDictionary *_command = @{@"/assettag/merge-tags": _params};

	[self.session invoke:_command error:error];
}

- (void)mergeTagsWithFromTagId:(long long)fromTagId toTagId:(long long)toTagId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"fromTagId": @(fromTagId),
		@"toTagId": @(toTagId)
	}];

	NSDictionary *_command = @{@"/assettag/merge-tags": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateTagWithTagId:(long long)tagId name:(NSString *)name serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagId": @(tagId),
		@"name": [self checkNull: name],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assettag/update-tag": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithGroupIds:(NSArray *)groupIds name:(NSString *)name start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag>" wrapper:obc];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithClassName:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithGroupId:(long long)groupId classNameId:(long long)classNameId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithGroupId:(long long)groupId classNameId:(long long)classNameId name:(NSString *)name start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag>" wrapper:obc];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.asset.kernel.model.AssetTag>" wrapper:obc];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getTagsWithGroupIds:(NSArray *)groupIds name:(NSString *)name start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupIds": [self checkNull: groupIds],
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assettag/get-tags": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addTagWithGroupId:(long long)groupId name:(NSString *)name serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/assettag/add-tag": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getVisibleAssetsTagsCountWithGroupId:(long long)groupId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/assettag/get-visible-assets-tags-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getVisibleAssetsTagsCountWithGroupId:(long long)groupId classNameId:(long long)classNameId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/assettag/get-visible-assets-tags-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getTagsCountWithGroupId:(long long)groupId name:(NSString *)name error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name]
	}];

	NSDictionary *_command = @{@"/assettag/get-tags-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (void)deleteTagsWithTagIds:(NSArray *)tagIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"tagIds": [self checkNull: tagIds]
	}];

	NSDictionary *_command = @{@"/assettag/delete-tags": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getGroupTagsDisplayWithGroupId:(long long)groupId name:(NSString *)name start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"name": [self checkNull: name],
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/assettag/get-group-tags-display": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
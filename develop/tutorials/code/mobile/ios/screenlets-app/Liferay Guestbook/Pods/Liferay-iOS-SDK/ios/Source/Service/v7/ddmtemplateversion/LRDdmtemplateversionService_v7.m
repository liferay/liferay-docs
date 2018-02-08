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

#import "LRDdmtemplateversionService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRDdmtemplateversionService_v7

- (NSDictionary *)getLatestTemplateVersionWithTemplateId:(long long)templateId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplateversion/get-latest-template-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getTemplateVersionWithTemplateVersionId:(long long)templateVersionId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateVersionId": @(templateVersionId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplateversion/get-template-version": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getTemplateVersionsWithTemplateId:(long long)templateId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.dynamic.data.mapping.model.DDMTemplateVersion>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/ddm.ddmtemplateversion/get-template-versions": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getTemplateVersionsCountWithTemplateId:(long long)templateId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"templateId": @(templateId)
	}];

	NSDictionary *_command = @{@"/ddm.ddmtemplateversion/get-template-versions-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

@end
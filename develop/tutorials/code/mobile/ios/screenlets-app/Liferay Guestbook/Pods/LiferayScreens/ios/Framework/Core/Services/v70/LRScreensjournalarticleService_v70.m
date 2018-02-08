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

#import "LRScreensjournalarticleService_v70.h"

/**
 * @author Bruno Farache
 */
@implementation LRScreensjournalarticleService_v70

- (NSString *)getJournalArticleContentWithGroupId:(long long)groupId articleId:(NSString *)articleId ddmTemplateId:(long long)ddmTemplateId locale:(NSString *)locale error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"ddmTemplateId": @(ddmTemplateId),
		@"locale": [self checkNull: locale]
	}];

	NSDictionary *_command = @{@"/screens.screensjournalarticle/get-journal-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getJournalArticleContentWithClassPK:(long long)classPK ddmTemplateId:(long long)ddmTemplateId locale:(NSString *)locale error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classPK": @(classPK),
		@"ddmTemplateId": @(ddmTemplateId),
		@"locale": [self checkNull: locale]
	}];

	NSDictionary *_command = @{@"/screens.screensjournalarticle/get-journal-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getJournalArticleContentWithClassPK:(long long)classPK locale:(NSString *)locale error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"classPK": @(classPK),
		@"locale": [self checkNull: locale]
	}];

	NSDictionary *_command = @{@"/screens.screensjournalarticle/get-journal-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

@end
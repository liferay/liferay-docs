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

#import "LRJournalArticleService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRJournalArticleService_v7

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId folderIds:(NSArray *)folderIds classNameId:(long long)classNameId articleId:(NSString *)articleId version:(double)version title:(NSString *)title description:(NSString *)description content:(NSString *)content ddmStructureKeys:(NSArray *)ddmStructureKeys ddmTemplateKeys:(NSArray *)ddmTemplateKeys displayDateGT:(long long)displayDateGT displayDateLT:(long long)displayDateLT status:(int)status reviewDate:(long long)reviewDate andOperator:(BOOL)andOperator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"classNameId": @(classNameId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"ddmStructureKeys": [self checkNull: ddmStructureKeys],
		@"ddmTemplateKeys": [self checkNull: ddmTemplateKeys],
		@"displayDateGT": @(displayDateGT),
		@"displayDateLT": @(displayDateLT),
		@"status": @(status),
		@"reviewDate": @(reviewDate),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId folderIds:(NSArray *)folderIds classNameId:(long long)classNameId articleId:(NSString *)articleId version:(double)version title:(NSString *)title description:(NSString *)description content:(NSString *)content ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey displayDateGT:(long long)displayDateGT displayDateLT:(long long)displayDateLT status:(int)status reviewDate:(long long)reviewDate andOperator:(BOOL)andOperator start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"classNameId": @(classNameId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"displayDateGT": @(displayDateGT),
		@"displayDateLT": @(displayDateLT),
		@"status": @(status),
		@"reviewDate": @(reviewDate),
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)searchWithGroupId:(long long)groupId creatorUserId:(long long)creatorUserId status:(int)status start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"creatorUserId": @(creatorUserId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/search": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)searchWithCompanyId:(long long)companyId groupId:(long long)groupId folderIds:(NSArray *)folderIds classNameId:(long long)classNameId keywords:(NSString *)keywords version:(double)version ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey displayDateGT:(long long)displayDateGT displayDateLT:(long long)displayDateLT status:(int)status reviewDate:(long long)reviewDate start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"classNameId": @(classNameId),
		@"keywords": [self checkNull: keywords],
		@"version": @(version),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"displayDateGT": @(displayDateGT),
		@"displayDateLT": @(displayDateLT),
		@"status": @(status),
		@"reviewDate": @(reviewDate),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getArticleWithId:(long long)id error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"id": @(id)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getArticleWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId folderIds:(NSArray *)folderIds classNameId:(long long)classNameId articleId:(NSString *)articleId version:(double)version title:(NSString *)title description:(NSString *)description content:(NSString *)content ddmStructureKeys:(NSArray *)ddmStructureKeys ddmTemplateKeys:(NSArray *)ddmTemplateKeys displayDateGT:(long long)displayDateGT displayDateLT:(long long)displayDateLT status:(int)status reviewDate:(long long)reviewDate andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"classNameId": @(classNameId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"ddmStructureKeys": [self checkNull: ddmStructureKeys],
		@"ddmTemplateKeys": [self checkNull: ddmTemplateKeys],
		@"displayDateGT": @(displayDateGT),
		@"displayDateLT": @(displayDateLT),
		@"status": @(status),
		@"reviewDate": @(reviewDate),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId folderIds:(NSArray *)folderIds classNameId:(long long)classNameId articleId:(NSString *)articleId version:(double)version title:(NSString *)title description:(NSString *)description content:(NSString *)content ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey displayDateGT:(long long)displayDateGT displayDateLT:(long long)displayDateLT status:(int)status reviewDate:(long long)reviewDate andOperator:(BOOL)andOperator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"classNameId": @(classNameId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"displayDateGT": @(displayDateGT),
		@"displayDateLT": @(displayDateLT),
		@"status": @(status),
		@"reviewDate": @(reviewDate),
		@"andOperator": @(andOperator)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)searchCountWithCompanyId:(long long)companyId groupId:(long long)groupId folderIds:(NSArray *)folderIds classNameId:(long long)classNameId keywords:(NSString *)keywords version:(double)version ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey displayDateGT:(long long)displayDateGT displayDateLT:(long long)displayDateLT status:(int)status reviewDate:(long long)reviewDate error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds],
		@"classNameId": @(classNameId),
		@"keywords": [self checkNull: keywords],
		@"version": @(version),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"displayDateGT": @(displayDateGT),
		@"displayDateLT": @(displayDateLT),
		@"status": @(status),
		@"reviewDate": @(reviewDate)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/search-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getLatestArticleWithGroupId:(long long)groupId className:(NSString *)className classPK:(long long)classPK error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"className": [self checkNull: className],
		@"classPK": @(classPK)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-latest-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getLatestArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-latest-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getLatestArticleWithResourcePrimKey:(long long)resourcePrimKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"resourcePrimKey": @(resourcePrimKey)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-latest-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSArray *)getArticlesWithGroupId:(long long)groupId folderId:(long long)folderId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getArticlesWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateArticleWithGroupId:(long long)groupId folderId:(long long)folderId articleId:(NSString *)articleId version:(double)version content:(NSString *)content serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"content": [self checkNull: content],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/update-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateArticleWithUserId:(long long)userId groupId:(long long)groupId folderId:(long long)folderId articleId:(NSString *)articleId version:(double)version titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap content:(NSString *)content layoutUuid:(NSString *)layoutUuid serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"userId": @(userId),
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"content": [self checkNull: content],
		@"layoutUuid": [self checkNull: layoutUuid],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/update-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateArticleWithGroupId:(long long)groupId folderId:(long long)folderId articleId:(NSString *)articleId version:(double)version titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap content:(NSString *)content ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey layoutUuid:(NSString *)layoutUuid displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute expirationDateMonth:(int)expirationDateMonth expirationDateDay:(int)expirationDateDay expirationDateYear:(int)expirationDateYear expirationDateHour:(int)expirationDateHour expirationDateMinute:(int)expirationDateMinute neverExpire:(BOOL)neverExpire reviewDateMonth:(int)reviewDateMonth reviewDateDay:(int)reviewDateDay reviewDateYear:(int)reviewDateYear reviewDateHour:(int)reviewDateHour reviewDateMinute:(int)reviewDateMinute neverReview:(BOOL)neverReview indexable:(BOOL)indexable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile images:(NSDictionary *)images articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"content": [self checkNull: content],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"layoutUuid": [self checkNull: layoutUuid],
		@"displayDateMonth": @(displayDateMonth),
		@"displayDateDay": @(displayDateDay),
		@"displayDateYear": @(displayDateYear),
		@"displayDateHour": @(displayDateHour),
		@"displayDateMinute": @(displayDateMinute),
		@"expirationDateMonth": @(expirationDateMonth),
		@"expirationDateDay": @(expirationDateDay),
		@"expirationDateYear": @(expirationDateYear),
		@"expirationDateHour": @(expirationDateHour),
		@"expirationDateMinute": @(expirationDateMinute),
		@"neverExpire": @(neverExpire),
		@"reviewDateMonth": @(reviewDateMonth),
		@"reviewDateDay": @(reviewDateDay),
		@"reviewDateYear": @(reviewDateYear),
		@"reviewDateHour": @(reviewDateHour),
		@"reviewDateMinute": @(reviewDateMinute),
		@"neverReview": @(neverReview),
		@"indexable": @(indexable),
		@"smallImage": @(smallImage),
		@"smallImageURL": [self checkNull: smallImageURL],
		@"smallFile": [self checkNull: smallFile],
		@"images": [self checkNull: images],
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/update-article": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSArray *)getArticlesByStructureIdWithGroupId:(long long)groupId classNameId:(long long)classNameId ddmStructureKey:(NSString *)ddmStructureKey status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-by-structure-id": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getArticlesByStructureIdWithGroupId:(long long)groupId ddmStructureKey:(NSString *)ddmStructureKey status:(int)status start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-by-structure-id": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getArticlesByStructureIdWithGroupId:(long long)groupId ddmStructureKey:(NSString *)ddmStructureKey start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-by-structure-id": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateStatusWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version status:(int)status articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"status": @(status),
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/update-status": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addArticleWithGroupId:(long long)groupId folderId:(long long)folderId classNameId:(long long)classNameId classPK:(long long)classPK articleId:(NSString *)articleId autoArticleId:(BOOL)autoArticleId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap content:(NSString *)content ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey layoutUuid:(NSString *)layoutUuid displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute expirationDateMonth:(int)expirationDateMonth expirationDateDay:(int)expirationDateDay expirationDateYear:(int)expirationDateYear expirationDateHour:(int)expirationDateHour expirationDateMinute:(int)expirationDateMinute neverExpire:(BOOL)neverExpire reviewDateMonth:(int)reviewDateMonth reviewDateDay:(int)reviewDateDay reviewDateYear:(int)reviewDateYear reviewDateHour:(int)reviewDateHour reviewDateMinute:(int)reviewDateMinute neverReview:(BOOL)neverReview indexable:(BOOL)indexable smallImage:(BOOL)smallImage smallImageURL:(NSString *)smallImageURL smallFile:(LRUploadData *)smallFile images:(NSDictionary *)images articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"articleId": [self checkNull: articleId],
		@"autoArticleId": @(autoArticleId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"content": [self checkNull: content],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"layoutUuid": [self checkNull: layoutUuid],
		@"displayDateMonth": @(displayDateMonth),
		@"displayDateDay": @(displayDateDay),
		@"displayDateYear": @(displayDateYear),
		@"displayDateHour": @(displayDateHour),
		@"displayDateMinute": @(displayDateMinute),
		@"expirationDateMonth": @(expirationDateMonth),
		@"expirationDateDay": @(expirationDateDay),
		@"expirationDateYear": @(expirationDateYear),
		@"expirationDateHour": @(expirationDateHour),
		@"expirationDateMinute": @(expirationDateMinute),
		@"neverExpire": @(neverExpire),
		@"reviewDateMonth": @(reviewDateMonth),
		@"reviewDateDay": @(reviewDateDay),
		@"reviewDateYear": @(reviewDateYear),
		@"reviewDateHour": @(reviewDateHour),
		@"reviewDateMinute": @(reviewDateMinute),
		@"neverReview": @(neverReview),
		@"indexable": @(indexable),
		@"smallImage": @(smallImage),
		@"smallImageURL": [self checkNull: smallImageURL],
		@"smallFile": [self checkNull: smallFile],
		@"images": [self checkNull: images],
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/add-article": _params};

	return (NSDictionary *)[self.session upload:_command error:error];
}

- (NSDictionary *)addArticleWithGroupId:(long long)groupId folderId:(long long)folderId classNameId:(long long)classNameId classPK:(long long)classPK articleId:(NSString *)articleId autoArticleId:(BOOL)autoArticleId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap content:(NSString *)content ddmStructureKey:(NSString *)ddmStructureKey ddmTemplateKey:(NSString *)ddmTemplateKey layoutUuid:(NSString *)layoutUuid displayDateMonth:(int)displayDateMonth displayDateDay:(int)displayDateDay displayDateYear:(int)displayDateYear displayDateHour:(int)displayDateHour displayDateMinute:(int)displayDateMinute expirationDateMonth:(int)expirationDateMonth expirationDateDay:(int)expirationDateDay expirationDateYear:(int)expirationDateYear expirationDateHour:(int)expirationDateHour expirationDateMinute:(int)expirationDateMinute neverExpire:(BOOL)neverExpire reviewDateMonth:(int)reviewDateMonth reviewDateDay:(int)reviewDateDay reviewDateYear:(int)reviewDateYear reviewDateHour:(int)reviewDateHour reviewDateMinute:(int)reviewDateMinute neverReview:(BOOL)neverReview indexable:(BOOL)indexable articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"classNameId": @(classNameId),
		@"classPK": @(classPK),
		@"articleId": [self checkNull: articleId],
		@"autoArticleId": @(autoArticleId),
		@"titleMap": [self checkNull: titleMap],
		@"descriptionMap": [self checkNull: descriptionMap],
		@"content": [self checkNull: content],
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"ddmTemplateKey": [self checkNull: ddmTemplateKey],
		@"layoutUuid": [self checkNull: layoutUuid],
		@"displayDateMonth": @(displayDateMonth),
		@"displayDateDay": @(displayDateDay),
		@"displayDateYear": @(displayDateYear),
		@"displayDateHour": @(displayDateHour),
		@"displayDateMinute": @(displayDateMinute),
		@"expirationDateMonth": @(expirationDateMonth),
		@"expirationDateDay": @(expirationDateDay),
		@"expirationDateYear": @(expirationDateYear),
		@"expirationDateHour": @(expirationDateHour),
		@"expirationDateMinute": @(expirationDateMinute),
		@"neverExpire": @(neverExpire),
		@"reviewDateMonth": @(reviewDateMonth),
		@"reviewDateDay": @(reviewDateDay),
		@"reviewDateYear": @(reviewDateYear),
		@"reviewDateHour": @(reviewDateHour),
		@"reviewDateMinute": @(reviewDateMinute),
		@"neverReview": @(neverReview),
		@"indexable": @(indexable),
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/add-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateContentWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version content:(NSString *)content error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"content": [self checkNull: content]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/update-content": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)copyArticleWithGroupId:(long long)groupId oldArticleId:(NSString *)oldArticleId newArticleId:(NSString *)newArticleId autoArticleId:(BOOL)autoArticleId version:(double)version error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"oldArticleId": [self checkNull: oldArticleId],
		@"newArticleId": [self checkNull: newArticleId],
		@"autoArticleId": @(autoArticleId),
		@"version": @(version)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/copy-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/delete-article": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/delete-article": _params};

	[self.session invoke:_command error:error];
}

- (void)expireArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/expire-article": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)expireArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version articleURL:(NSString *)articleURL serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"articleURL": [self checkNull: articleURL],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/expire-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)fetchArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/fetch-article": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getArticleByUrlTitleWithGroupId:(long long)groupId urlTitle:(NSString *)urlTitle error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"urlTitle": [self checkNull: urlTitle]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article-by-url-title": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSString *)getArticleContentWithGroupId:(long long)groupId articleId:(NSString *)articleId languageId:(NSString *)languageId themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"languageId": [self checkNull: languageId],
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getArticleContentWithGroupId:(long long)groupId articleId:(NSString *)articleId languageId:(NSString *)languageId portletRequestModel:(LRJSONObjectWrapper *)portletRequestModel themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"languageId": [self checkNull: languageId],
	}];

	[self mangleWrapperWithParams:_params name:@"portletRequestModel" className:@"com.liferay.portal.kernel.portlet.PortletRequestModel" wrapper:portletRequestModel];
	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getArticleContentWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version languageId:(NSString *)languageId themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"languageId": [self checkNull: languageId],
	}];

	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSString *)getArticleContentWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version languageId:(NSString *)languageId portletRequestModel:(LRJSONObjectWrapper *)portletRequestModel themeDisplay:(LRJSONObjectWrapper *)themeDisplay error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"languageId": [self checkNull: languageId],
	}];

	[self mangleWrapperWithParams:_params name:@"portletRequestModel" className:@"com.liferay.portal.kernel.portlet.PortletRequestModel" wrapper:portletRequestModel];
	[self mangleWrapperWithParams:_params name:@"themeDisplay" className:@"com.liferay.portal.kernel.theme.ThemeDisplay" wrapper:themeDisplay];

	NSDictionary *_command = @{@"/journal.journalarticle/get-article-content": _params};

	return (NSString *)[self.session invoke:_command error:error];
}

- (NSNumber *)getArticlesCountWithGroupId:(long long)groupId folderId:(long long)folderId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getArticlesCountWithGroupId:(long long)groupId folderId:(long long)folderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderId": @(folderId)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSDictionary *)getDisplayArticleByUrlTitleWithGroupId:(long long)groupId urlTitle:(NSString *)urlTitle error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"urlTitle": [self checkNull: urlTitle]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-display-article-by-url-title": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)moveArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId newFolderId:(long long)newFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"newFolderId": @(newFolderId)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/move-article": _params};

	[self.session invoke:_command error:error];
}

- (void)moveArticleWithGroupId:(long long)groupId articleId:(NSString *)articleId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"newFolderId": @(newFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/move-article": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)moveArticleFromTrashWithGroupId:(long long)groupId resourcePrimKey:(long long)resourcePrimKey newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"resourcePrimKey": @(resourcePrimKey),
		@"newFolderId": @(newFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/move-article-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveArticleFromTrashWithGroupId:(long long)groupId articleId:(NSString *)articleId newFolderId:(long long)newFolderId serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"newFolderId": @(newFolderId),
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/move-article-from-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)moveArticleToTrashWithGroupId:(long long)groupId articleId:(NSString *)articleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/move-article-to-trash": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)removeArticleLocaleWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version languageId:(NSString *)languageId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"languageId": [self checkNull: languageId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/remove-article-locale": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)removeArticleLocaleWithCompanyId:(long long)companyId languageId:(NSString *)languageId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"companyId": @(companyId),
		@"languageId": [self checkNull: languageId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/remove-article-locale": _params};

	[self.session invoke:_command error:error];
}

- (void)restoreArticleFromTrashWithGroupId:(long long)groupId articleId:(NSString *)articleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/restore-article-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (void)restoreArticleFromTrashWithResourcePrimKey:(long long)resourcePrimKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"resourcePrimKey": @(resourcePrimKey)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/restore-article-from-trash": _params};

	[self.session invoke:_command error:error];
}

- (void)subscribeStructureWithGroupId:(long long)groupId userId:(long long)userId ddmStructureId:(long long)ddmStructureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"ddmStructureId": @(ddmStructureId)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/subscribe-structure": _params};

	[self.session invoke:_command error:error];
}

- (void)unsubscribeStructureWithGroupId:(long long)groupId userId:(long long)userId ddmStructureId:(long long)ddmStructureId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"ddmStructureId": @(ddmStructureId)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/unsubscribe-structure": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateArticleTranslationWithGroupId:(long long)groupId articleId:(NSString *)articleId version:(double)version locale:(NSString *)locale title:(NSString *)title description:(NSString *)description content:(NSString *)content images:(NSDictionary *)images serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"version": @(version),
		@"locale": [self checkNull: locale],
		@"title": [self checkNull: title],
		@"description": [self checkNull: description],
		@"content": [self checkNull: content],
		@"images": [self checkNull: images],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/journal.journalarticle/update-article-translation": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSNumber *)getArticlesCountByArticleIdWithGroupId:(long long)groupId articleId:(NSString *)articleId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-count-by-article-id": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getArticlesCountByStructureIdWithGroupId:(long long)groupId ddmStructureKey:(NSString *)ddmStructureKey status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-count-by-structure-id": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getArticlesCountByStructureIdWithGroupId:(long long)groupId classNameId:(long long)classNameId ddmStructureKey:(NSString *)ddmStructureKey status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"classNameId": @(classNameId),
		@"ddmStructureKey": [self checkNull: ddmStructureKey],
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-count-by-structure-id": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getArticlesCountByStructureIdWithGroupId:(long long)groupId ddmStructureKey:(NSString *)ddmStructureKey error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"ddmStructureKey": [self checkNull: ddmStructureKey]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-count-by-structure-id": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupArticlesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId status:(int)status start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"status": @(status),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/journal.journalarticle/get-group-articles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupArticlesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/journal.journalarticle/get-group-articles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getGroupArticlesWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId status:(int)status includeOwner:(BOOL)includeOwner start:(int)start end:(int)end orderByComparator:(LRJSONObjectWrapper *)orderByComparator error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"status": @(status),
		@"includeOwner": @(includeOwner),
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"orderByComparator" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:orderByComparator];

	NSDictionary *_command = @{@"/journal.journalarticle/get-group-articles": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getArticlesByLayoutUuidWithGroupId:(long long)groupId layoutUuid:(NSString *)layoutUuid error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"layoutUuid": [self checkNull: layoutUuid]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-by-layout-uuid": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSArray *)getArticlesByArticleIdWithGroupId:(long long)groupId articleId:(NSString *)articleId start:(int)start end:(int)end obc:(LRJSONObjectWrapper *)obc error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"articleId": [self checkNull: articleId],
		@"start": @(start),
		@"end": @(end),
	}];

	[self mangleWrapperWithParams:_params name:@"obc" className:@"com.liferay.portal.kernel.util.OrderByComparator<com.liferay.journal.model.JournalArticle>" wrapper:obc];

	NSDictionary *_command = @{@"/journal.journalarticle/get-articles-by-article-id": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSNumber *)getFoldersAndArticlesCountWithGroupId:(long long)groupId folderIds:(NSArray *)folderIds error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"folderIds": [self checkNull: folderIds]
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-folders-and-articles-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupArticlesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId status:(int)status error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"status": @(status)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-group-articles-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupArticlesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId status:(int)status includeOwner:(BOOL)includeOwner error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId),
		@"status": @(status),
		@"includeOwner": @(includeOwner)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-group-articles-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

- (NSNumber *)getGroupArticlesCountWithGroupId:(long long)groupId userId:(long long)userId rootFolderId:(long long)rootFolderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"userId": @(userId),
		@"rootFolderId": @(rootFolderId)
	}];

	NSDictionary *_command = @{@"/journal.journalarticle/get-group-articles-count": _params};

	return (NSNumber *)[self.session invoke:_command error:error];
}

@end
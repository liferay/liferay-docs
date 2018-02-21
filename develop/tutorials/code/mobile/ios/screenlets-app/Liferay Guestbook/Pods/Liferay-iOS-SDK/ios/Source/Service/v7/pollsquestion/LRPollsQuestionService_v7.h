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
@interface LRPollsQuestionService_v7 : LRBaseService

- (NSDictionary *)getQuestionWithQuestionId:(long long)questionId error:(NSError **)error;
- (NSDictionary *)addQuestionWithTitleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap expirationDateMonth:(int)expirationDateMonth expirationDateDay:(int)expirationDateDay expirationDateYear:(int)expirationDateYear expirationDateHour:(int)expirationDateHour expirationDateMinute:(int)expirationDateMinute neverExpire:(BOOL)neverExpire choices:(NSArray *)choices serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (NSDictionary *)updateQuestionWithQuestionId:(long long)questionId titleMap:(NSDictionary *)titleMap descriptionMap:(NSDictionary *)descriptionMap expirationDateMonth:(int)expirationDateMonth expirationDateDay:(int)expirationDateDay expirationDateYear:(int)expirationDateYear expirationDateHour:(int)expirationDateHour expirationDateMinute:(int)expirationDateMinute neverExpire:(BOOL)neverExpire choices:(NSArray *)choices serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteQuestionWithQuestionId:(long long)questionId error:(NSError **)error;

@end
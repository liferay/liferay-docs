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

#import "LRShoppingCouponService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRShoppingCouponService_v7

- (NSArray *)searchWithGroupId:(long long)groupId companyId:(long long)companyId code:(NSString *)code active:(BOOL)active discountType:(NSString *)discountType andOperator:(BOOL)andOperator start:(int)start end:(int)end error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"companyId": @(companyId),
		@"code": [self checkNull: code],
		@"active": @(active),
		@"discountType": [self checkNull: discountType],
		@"andOperator": @(andOperator),
		@"start": @(start),
		@"end": @(end)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingcoupon/search": _params};

	return (NSArray *)[self.session invoke:_command error:error];
}

- (NSDictionary *)addCouponWithCode:(NSString *)code autoCode:(BOOL)autoCode name:(NSString *)name description:(NSString *)description startDateMonth:(int)startDateMonth startDateDay:(int)startDateDay startDateYear:(int)startDateYear startDateHour:(int)startDateHour startDateMinute:(int)startDateMinute endDateMonth:(int)endDateMonth endDateDay:(int)endDateDay endDateYear:(int)endDateYear endDateHour:(int)endDateHour endDateMinute:(int)endDateMinute neverExpire:(BOOL)neverExpire active:(BOOL)active limitCategories:(NSString *)limitCategories limitSkus:(NSString *)limitSkus minOrder:(double)minOrder discount:(double)discount discountType:(NSString *)discountType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"code": [self checkNull: code],
		@"autoCode": @(autoCode),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"startDateMonth": @(startDateMonth),
		@"startDateDay": @(startDateDay),
		@"startDateYear": @(startDateYear),
		@"startDateHour": @(startDateHour),
		@"startDateMinute": @(startDateMinute),
		@"endDateMonth": @(endDateMonth),
		@"endDateDay": @(endDateDay),
		@"endDateYear": @(endDateYear),
		@"endDateHour": @(endDateHour),
		@"endDateMinute": @(endDateMinute),
		@"neverExpire": @(neverExpire),
		@"active": @(active),
		@"limitCategories": [self checkNull: limitCategories],
		@"limitSkus": [self checkNull: limitSkus],
		@"minOrder": @(minOrder),
		@"discount": @(discount),
		@"discountType": [self checkNull: discountType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/shopping.shoppingcoupon/add-coupon": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)deleteCouponWithGroupId:(long long)groupId couponId:(long long)couponId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"couponId": @(couponId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingcoupon/delete-coupon": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)getCouponWithGroupId:(long long)groupId couponId:(long long)couponId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"couponId": @(couponId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingcoupon/get-coupon": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateCouponWithCouponId:(long long)couponId name:(NSString *)name description:(NSString *)description startDateMonth:(int)startDateMonth startDateDay:(int)startDateDay startDateYear:(int)startDateYear startDateHour:(int)startDateHour startDateMinute:(int)startDateMinute endDateMonth:(int)endDateMonth endDateDay:(int)endDateDay endDateYear:(int)endDateYear endDateHour:(int)endDateHour endDateMinute:(int)endDateMinute neverExpire:(BOOL)neverExpire active:(BOOL)active limitCategories:(NSString *)limitCategories limitSkus:(NSString *)limitSkus minOrder:(double)minOrder discount:(double)discount discountType:(NSString *)discountType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"couponId": @(couponId),
		@"name": [self checkNull: name],
		@"description": [self checkNull: description],
		@"startDateMonth": @(startDateMonth),
		@"startDateDay": @(startDateDay),
		@"startDateYear": @(startDateYear),
		@"startDateHour": @(startDateHour),
		@"startDateMinute": @(startDateMinute),
		@"endDateMonth": @(endDateMonth),
		@"endDateDay": @(endDateDay),
		@"endDateYear": @(endDateYear),
		@"endDateHour": @(endDateHour),
		@"endDateMinute": @(endDateMinute),
		@"neverExpire": @(neverExpire),
		@"active": @(active),
		@"limitCategories": [self checkNull: limitCategories],
		@"limitSkus": [self checkNull: limitSkus],
		@"minOrder": @(minOrder),
		@"discount": @(discount),
		@"discountType": [self checkNull: discountType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/shopping.shoppingcoupon/update-coupon": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
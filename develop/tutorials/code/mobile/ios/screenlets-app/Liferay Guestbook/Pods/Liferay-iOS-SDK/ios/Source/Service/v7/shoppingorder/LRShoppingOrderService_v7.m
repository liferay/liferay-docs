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

#import "LRShoppingOrderService_v7.h"

/**
 * @author Bruno Farache
 */
@implementation LRShoppingOrderService_v7

- (NSDictionary *)getOrderWithGroupId:(long long)groupId orderId:(long long)orderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"orderId": @(orderId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingorder/get-order": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (void)sendEmailWithGroupId:(long long)groupId orderId:(long long)orderId emailType:(NSString *)emailType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"orderId": @(orderId),
		@"emailType": [self checkNull: emailType],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/shopping.shoppingorder/send-email": _params};

	[self.session invoke:_command error:error];
}

- (void)completeOrderWithGroupId:(long long)groupId number:(NSString *)number ppTxnId:(NSString *)ppTxnId ppPaymentStatus:(NSString *)ppPaymentStatus ppPaymentGross:(double)ppPaymentGross ppReceiverEmail:(NSString *)ppReceiverEmail ppPayerEmail:(NSString *)ppPayerEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"number": [self checkNull: number],
		@"ppTxnId": [self checkNull: ppTxnId],
		@"ppPaymentStatus": [self checkNull: ppPaymentStatus],
		@"ppPaymentGross": @(ppPaymentGross),
		@"ppReceiverEmail": [self checkNull: ppReceiverEmail],
		@"ppPayerEmail": [self checkNull: ppPayerEmail],
	}];

	[self mangleWrapperWithParams:_params name:@"serviceContext" className:@"com.liferay.portal.kernel.service.ServiceContext" wrapper:serviceContext];

	NSDictionary *_command = @{@"/shopping.shoppingorder/complete-order": _params};

	[self.session invoke:_command error:error];
}

- (void)deleteOrderWithGroupId:(long long)groupId orderId:(long long)orderId error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"orderId": @(orderId)
	}];

	NSDictionary *_command = @{@"/shopping.shoppingorder/delete-order": _params};

	[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOrderWithGroupId:(long long)groupId orderId:(long long)orderId ppTxnId:(NSString *)ppTxnId ppPaymentStatus:(NSString *)ppPaymentStatus ppPaymentGross:(double)ppPaymentGross ppReceiverEmail:(NSString *)ppReceiverEmail ppPayerEmail:(NSString *)ppPayerEmail error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"orderId": @(orderId),
		@"ppTxnId": [self checkNull: ppTxnId],
		@"ppPaymentStatus": [self checkNull: ppPaymentStatus],
		@"ppPaymentGross": @(ppPaymentGross),
		@"ppReceiverEmail": [self checkNull: ppReceiverEmail],
		@"ppPayerEmail": [self checkNull: ppPayerEmail]
	}];

	NSDictionary *_command = @{@"/shopping.shoppingorder/update-order": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

- (NSDictionary *)updateOrderWithGroupId:(long long)groupId orderId:(long long)orderId billingFirstName:(NSString *)billingFirstName billingLastName:(NSString *)billingLastName billingEmailAddress:(NSString *)billingEmailAddress billingCompany:(NSString *)billingCompany billingStreet:(NSString *)billingStreet billingCity:(NSString *)billingCity billingState:(NSString *)billingState billingZip:(NSString *)billingZip billingCountry:(NSString *)billingCountry billingPhone:(NSString *)billingPhone shipToBilling:(BOOL)shipToBilling shippingFirstName:(NSString *)shippingFirstName shippingLastName:(NSString *)shippingLastName shippingEmailAddress:(NSString *)shippingEmailAddress shippingCompany:(NSString *)shippingCompany shippingStreet:(NSString *)shippingStreet shippingCity:(NSString *)shippingCity shippingState:(NSString *)shippingState shippingZip:(NSString *)shippingZip shippingCountry:(NSString *)shippingCountry shippingPhone:(NSString *)shippingPhone ccName:(NSString *)ccName ccType:(NSString *)ccType ccNumber:(NSString *)ccNumber ccExpMonth:(int)ccExpMonth ccExpYear:(int)ccExpYear ccVerNumber:(NSString *)ccVerNumber comments:(NSString *)comments error:(NSError **)error {
	NSMutableDictionary *_params = [NSMutableDictionary dictionaryWithDictionary:@{
		@"groupId": @(groupId),
		@"orderId": @(orderId),
		@"billingFirstName": [self checkNull: billingFirstName],
		@"billingLastName": [self checkNull: billingLastName],
		@"billingEmailAddress": [self checkNull: billingEmailAddress],
		@"billingCompany": [self checkNull: billingCompany],
		@"billingStreet": [self checkNull: billingStreet],
		@"billingCity": [self checkNull: billingCity],
		@"billingState": [self checkNull: billingState],
		@"billingZip": [self checkNull: billingZip],
		@"billingCountry": [self checkNull: billingCountry],
		@"billingPhone": [self checkNull: billingPhone],
		@"shipToBilling": @(shipToBilling),
		@"shippingFirstName": [self checkNull: shippingFirstName],
		@"shippingLastName": [self checkNull: shippingLastName],
		@"shippingEmailAddress": [self checkNull: shippingEmailAddress],
		@"shippingCompany": [self checkNull: shippingCompany],
		@"shippingStreet": [self checkNull: shippingStreet],
		@"shippingCity": [self checkNull: shippingCity],
		@"shippingState": [self checkNull: shippingState],
		@"shippingZip": [self checkNull: shippingZip],
		@"shippingCountry": [self checkNull: shippingCountry],
		@"shippingPhone": [self checkNull: shippingPhone],
		@"ccName": [self checkNull: ccName],
		@"ccType": [self checkNull: ccType],
		@"ccNumber": [self checkNull: ccNumber],
		@"ccExpMonth": @(ccExpMonth),
		@"ccExpYear": @(ccExpYear),
		@"ccVerNumber": [self checkNull: ccVerNumber],
		@"comments": [self checkNull: comments]
	}];

	NSDictionary *_command = @{@"/shopping.shoppingorder/update-order": _params};

	return (NSDictionary *)[self.session invoke:_command error:error];
}

@end
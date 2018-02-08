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
@interface LRShoppingOrderService_v7 : LRBaseService

- (NSDictionary *)getOrderWithGroupId:(long long)groupId orderId:(long long)orderId error:(NSError **)error;
- (void)sendEmailWithGroupId:(long long)groupId orderId:(long long)orderId emailType:(NSString *)emailType serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)completeOrderWithGroupId:(long long)groupId number:(NSString *)number ppTxnId:(NSString *)ppTxnId ppPaymentStatus:(NSString *)ppPaymentStatus ppPaymentGross:(double)ppPaymentGross ppReceiverEmail:(NSString *)ppReceiverEmail ppPayerEmail:(NSString *)ppPayerEmail serviceContext:(LRJSONObjectWrapper *)serviceContext error:(NSError **)error;
- (void)deleteOrderWithGroupId:(long long)groupId orderId:(long long)orderId error:(NSError **)error;
- (NSDictionary *)updateOrderWithGroupId:(long long)groupId orderId:(long long)orderId ppTxnId:(NSString *)ppTxnId ppPaymentStatus:(NSString *)ppPaymentStatus ppPaymentGross:(double)ppPaymentGross ppReceiverEmail:(NSString *)ppReceiverEmail ppPayerEmail:(NSString *)ppPayerEmail error:(NSError **)error;
- (NSDictionary *)updateOrderWithGroupId:(long long)groupId orderId:(long long)orderId billingFirstName:(NSString *)billingFirstName billingLastName:(NSString *)billingLastName billingEmailAddress:(NSString *)billingEmailAddress billingCompany:(NSString *)billingCompany billingStreet:(NSString *)billingStreet billingCity:(NSString *)billingCity billingState:(NSString *)billingState billingZip:(NSString *)billingZip billingCountry:(NSString *)billingCountry billingPhone:(NSString *)billingPhone shipToBilling:(BOOL)shipToBilling shippingFirstName:(NSString *)shippingFirstName shippingLastName:(NSString *)shippingLastName shippingEmailAddress:(NSString *)shippingEmailAddress shippingCompany:(NSString *)shippingCompany shippingStreet:(NSString *)shippingStreet shippingCity:(NSString *)shippingCity shippingState:(NSString *)shippingState shippingZip:(NSString *)shippingZip shippingCountry:(NSString *)shippingCountry shippingPhone:(NSString *)shippingPhone ccName:(NSString *)ccName ccType:(NSString *)ccType ccNumber:(NSString *)ccNumber ccExpMonth:(int)ccExpMonth ccExpYear:(int)ccExpYear ccVerNumber:(NSString *)ccVerNumber comments:(NSString *)comments error:(NSError **)error;

@end
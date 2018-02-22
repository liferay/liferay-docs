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

#import "LRValidator.h"

/**
 * @author Bruno Farache
 */
@implementation LRValidator

+ (BOOL)isEmailAddress:(NSString *)emailAddress {
	NSString *regex =
		@"[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@"
			"(?:[a-zA-Z0-9](?:-*[a-zA-Z0-9])?\\.*)+";

	NSPredicate *predicate = [NSPredicate
		predicateWithFormat:@"SELF MATCHES %@", regex];
	
	return [predicate evaluateWithObject:emailAddress];
}

+ (BOOL)isEmpty:(NSString *)string {
	return [self isEmpty:string trim:YES];
}

+ (BOOL)isEmpty:(NSString *)string trim:(BOOL)trim {
	if ((string == nil) || [string isKindOfClass:[NSNull class]]) {
		return YES;
	}

	if (trim) {
		NSCharacterSet *whitespace = [NSCharacterSet whitespaceCharacterSet];
		string = [string stringByTrimmingCharactersInSet:whitespace];
	}

	if (string.length == 0) {
		return YES;
	}

	return NO;
}

+ (BOOL)isNotEmpty:(NSString *)string {
	return ![self isEmpty:string];
}

+ (BOOL)isNull:(NSData *)data {
	return ((data == nil) || ([data length] == 0));
}

@end
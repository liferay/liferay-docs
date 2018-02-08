/**
 * Copyright (c) 2000-2014 Liferay, Inc. All rights reserved.
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

#import "LROAuthConfig.h"

/**
 * @author Allan Melo
 */
typedef enum {
	ASK_PERMISSION = 0,
	DENIED = 1,
	GRANTED = 2
} Page;

@protocol LROAuthCallback <NSObject>

- (void)onFailure:(NSError *)error;
- (void)onLoadPage:(Page)page webview:(UIWebView *)webView url:(NSURL *)url;
- (void)onSuccess:(LROAuthConfig *)config;

@end
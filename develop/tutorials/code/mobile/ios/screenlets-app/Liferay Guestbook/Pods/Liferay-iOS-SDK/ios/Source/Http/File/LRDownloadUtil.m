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

#import "LRDownloadUtil.h"

#import "LRHttpUtil.h"
#import "LRPortalVersionUtil.h"
#import "LRValidator.h"

/**
 * @author Bruno Farache
 */
@implementation LRDownloadUtil

+ (void)downloadWithSession:(LRSession *)session
		URL:(NSString *)URL outputStream:(NSOutputStream *)outputStream
		progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	id<LRAuthentication> auth = session.authentication;

	LRDownloadDelegate *delegate = [[LRDownloadDelegate alloc]
		initWithAuth:auth outputStream:outputStream
		progressDelegate:progressDelegate];

	NSMutableURLRequest *request = [LRHttpUtil getRequestWithSession:session
		URL:[NSURL URLWithString:URL]];

	[auth authenticate:request];

	NSURLConnection *connection = [[NSURLConnection alloc]
		initWithRequest:request delegate:delegate startImmediately:NO];

	[connection start];
}

+ (void)downloadWebDAVFileWithSession:(LRSession *)session
		portalVersion:(NSInteger)portalVersion
		groupFriendlyURL:(NSString *)groupFriendlyURL
		folderPath:(NSString *)folderPath fileTitle:(NSString *)fileTitle
		outputStream:(NSOutputStream *)outputStream
		progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	NSString *URL = [LRDownloadUtil getWebDAVFileURLWithSession:session
		portalVersion:portalVersion groupFriendlyURL:groupFriendlyURL
		folderPath:folderPath fileTitle:fileTitle];

	[LRDownloadUtil downloadWithSession:session URL:URL
		outputStream:outputStream progressDelegate:progressDelegate];
}

+ (NSString *)getWebDAVFileURLWithSession:(LRSession *)session
		portalVersion:(NSInteger)portalversion
		groupFriendlyURL:(NSString *)groupFriendlyURL
		folderPath:(NSString *)folderPath fileTitle:(NSString *)fileTitle {

	NSString *webdavPath = [NSString stringWithFormat:@"%@%@",
		[LRDownloadUtil _prependSlash:folderPath],
		[LRDownloadUtil _prependSlash:fileTitle]];

	NSString *webdavPathEscaped = [LRHttpUtil
		escape:webdavPath include:@":?#[]@!$ &'()*+,;=\"<>%{}|\\^~`"
		ignore:@"/"];

	NSString *path = @"";

	if (portalversion < LR_VERSION_6_2) {
		path = @"/api/secure";
	}

	return [NSString
		stringWithFormat:@"%@%@/webdav%@/document_library%@",
		session.server, path, [LRDownloadUtil _prependSlash:groupFriendlyURL],
		webdavPathEscaped];
}

+ (NSString *)_prependSlash:(NSString *)string {
	if ([LRValidator isEmpty:string]) {
		return @"";
	}

	if (![string hasPrefix:@"/"]) {
		return [NSString stringWithFormat:@"/%@", string];
	}

	return string;
}

@end
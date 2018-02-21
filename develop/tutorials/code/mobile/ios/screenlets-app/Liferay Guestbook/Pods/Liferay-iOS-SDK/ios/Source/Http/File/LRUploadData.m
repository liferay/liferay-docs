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

#import "LRUploadData.h"

/**
 * @author Bruno Farache
 */
@implementation LRUploadData

- (id)initWithData:(NSData *)data fileName:(NSString *)fileName
		mimeType:(NSString *)mimeType
  		progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	return [self _init:data fileURL:nil inputStream:nil length:0
		fileName:fileName mimeType:mimeType progressDelegate:progressDelegate];
}

- (id)initWithFileURL:(NSURL *)fileURL fileName:(NSString *)fileName
		mimeType:(NSString *)mimeType
	 	progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	return [self _init:nil fileURL:fileURL inputStream:nil length:0
		fileName:fileName mimeType:mimeType progressDelegate:progressDelegate];
}

- (id)initWithInputStream:(NSInputStream *)inputStream length:(int64_t)length
		fileName:(NSString *)fileName mimeType:(NSString *)mimeType
		progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	return [self _init:nil  fileURL:nil inputStream:inputStream length:length
		fileName:fileName mimeType:mimeType progressDelegate:progressDelegate];
}

- (id)_init:(NSData *)data fileURL:(NSURL *)fileURL
		inputStream:(NSInputStream *)inputStream length:(int64_t)length
		fileName:(NSString *)fileName mimeType:(NSString *)mimeType
		progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	self = [super init];

	if (self) {
		self.data = data;
		self.fileURL = fileURL;
		self.inputStream = inputStream;
		self.length = length;
		self.fileName = fileName;
		self.mimeType = mimeType;
		self.progressDelegate = progressDelegate;
	}

	return self;
}

@end
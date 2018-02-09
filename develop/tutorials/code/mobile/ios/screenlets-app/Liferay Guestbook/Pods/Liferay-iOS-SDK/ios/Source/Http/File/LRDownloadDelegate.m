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

#import "LRDownloadDelegate.h"

#import "LRBasicAuthentication.h"
#import "LRError.h"
#import "LRResponseParser.h"

/**
 * @author Bruno Farache
 */
@implementation LRDownloadDelegate

- (id)initWithAuth:(id<LRAuthentication>)auth
		outputStream:(NSOutputStream *)outputStream
		progressDelegate:(id<LRFileProgressDelegate>)progressDelegate {

	self = [super init];

	if (self) {
		self.auth = auth;
		self.outputStream = outputStream;
		self.progressDelegate = progressDelegate;
	}

	return self;
}

#pragma mark - NSURLConnectionDelegate

- (void)connection:(NSURLConnection *)connection
		didFailWithError:(NSError *)error {

	[self.progressDelegate onFailure:error];
}

- (void)connection:(NSURLConnection *)connection
		didReceiveAuthenticationChallenge:(NSURLAuthenticationChallenge *)c  {

	if ([c previousFailureCount] > 1) {
		NSError *error = [LRError errorWithCode:LRErrorCodeUnauthorized
			description:@"Authentication failed during download"];

		[self.progressDelegate onFailure:error];
	}
	else {
		if (!self.auth) {
			NSError *error = [LRError errorWithCode:LRErrorCodeUnauthorized
				description:@"Session authentication can't be nil"];

			[self.progressDelegate onFailure:error];

			return;
		}

		NSString *authClass = NSStringFromClass([self.auth class]);
		NSString *basicAuthClass = NSStringFromClass(
			[LRBasicAuthentication class]);

		LRBasicAuthentication *basic = (LRBasicAuthentication *)self.auth;

		if (![authClass isEqualToString:basicAuthClass]) {
			NSError *error = [LRError errorWithCode:LRErrorCodeUnauthorized
				description:@"Can't download if authentication implementation" \
					" is not BasicAuthentication"];

			[self.progressDelegate onFailure:error];

			return;
		}

		NSURLCredential *credential = [NSURLCredential
			credentialWithUser:basic.username password:basic.password
			persistence:NSURLCredentialPersistenceNone];

		[c.sender useCredential:credential forAuthenticationChallenge:c];
	}
}

#pragma mark - NSURLConnectionDataDelegate

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data {
	if ([self.progressDelegate respondsToSelector:@selector(isCancelled)] &&
		[self.progressDelegate isCancelled]) {

		[connection cancel];

		return;
	}

	NSUInteger length = [data length];
	self.totalBytes = self.totalBytes + length;

	if ([self.outputStream hasSpaceAvailable]) {
		const uint8_t *buffer = (uint8_t *)[data bytes];
		[self.outputStream write:&buffer[0] maxLength:length];
	}

	[self.progressDelegate onProgress:data totalBytes:self.totalBytes];
}

- (void)connection:(NSURLConnection *)connection
		didReceiveResponse:(NSURLResponse *)response {

	NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse *)response;
	NSInteger code = [httpResponse statusCode];

	if (code != LR_HTTP_STATUS_OK) {
		NSString *description = [NSString stringWithFormat:@"HTTP Error: %li",
			(long)code];

		NSError *error = [LRError errorWithCode:code description:description];
		[self.progressDelegate onFailure:error];
	}
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection {
	if ([self.progressDelegate respondsToSelector:@selector(onFinished)]) {
		[self.progressDelegate onFinished];
	}
}

@end
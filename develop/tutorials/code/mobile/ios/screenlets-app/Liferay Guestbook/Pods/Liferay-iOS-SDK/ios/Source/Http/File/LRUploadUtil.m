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

#import "LRUploadUtil.h"

#import "AFNetworking.h"
#import "LRHttpUtil.h"
#import "LRUploadData.h"
#import "LRResponseParser.h"

/**
 * @author Bruno Farache
 */
@implementation LRUploadUtil

+ (void)upload:(LRSession *)session command:(NSDictionary *)command
		error:(NSError **)error {

	NSArray *keys = [command allKeys];

	if ([keys count] > 1) {
		return;
	}

	NSString *path = keys[0];
	NSString *URL = [[LRHttpUtil getURL:session path:path] absoluteString];

	NSMutableDictionary *parameters = [NSMutableDictionary
		dictionaryWithDictionary:[command valueForKey:path]];

	LRUploadData *data = [self _extractUploadData:parameters];

	[self _post:session data:data URL:URL parameters:parameters
			constructingBodyWithBlock:^(id<AFMultipartFormData> form) {
				if (data.data) {
					[form appendPartWithFileData:data.data
						name:data.parameterName fileName:data.fileName
						mimeType:data.mimeType];
				}
				else if (data.fileURL) {
					[form appendPartWithFileURL:data.fileURL
						name:data.parameterName fileName:data.fileName
						mimeType:data.mimeType error:error];
				}
				else if (data.inputStream) {
					[form appendPartWithInputStream:data.inputStream
						name:data.parameterName fileName:data.fileName
						length:data.length mimeType:data.mimeType];
				}
			}
			success:^(AFHTTPRequestOperation *operation, id json) {
				NSError *serverError;

				NSURLRequest *request = operation.request;
				NSHTTPURLResponse *response = operation.response;

				[LRResponseParser parse:json request:request response:response
					error:&serverError];

				if (serverError) {
					[session.callback onFailure:serverError];

					return;
				}

				[session.callback onSuccess:json];
			}
			failure:^(AFHTTPRequestOperation *operation, NSError *error) {
				[session.callback onFailure:error];
			}
			error:error
		];
}

+ (LRUploadData *)_extractUploadData:(NSMutableDictionary *)params {
	for (NSString *key in params) {
		id param = [params objectForKey:key];

		NSString *paramClazz = NSStringFromClass([params[key] class]);
		NSString *uploadDataClazz = NSStringFromClass([LRUploadData class]);

		if ([paramClazz isEqualToString:uploadDataClazz]) {
			[params removeObjectForKey:key];
			[param setParameterName:key];

			return param;
		}
	}

	return nil;
}

+ (void)_post:(LRSession *)session data:(LRUploadData *)data URL:(NSString *)URL
		parameters:(id)parameters
		constructingBodyWithBlock:(void (^)(id <AFMultipartFormData> form))block
		success:(void (^)(AFHTTPRequestOperation *o, id json))success
		failure:(void (^)(AFHTTPRequestOperation *o, NSError *error))failure
		error:(NSError **)error {

	AFHTTPRequestOperationManager *manager = [AFHTTPRequestOperationManager
		manager];

    NSMutableURLRequest *request = [manager.requestSerializer
		multipartFormRequestWithMethod:LR_POST URLString:URL
		parameters:parameters constructingBodyWithBlock:block error:nil];

	[request setAllHTTPHeaderFields:session.headers];
	[request setTimeoutInterval:session.connectionTimeout];

	if (session.authentication) {
		[session.authentication authenticate:request];
	}

    __weak __typeof(AFHTTPRequestOperation *)operation = [manager
		HTTPRequestOperationWithRequest:request success:success
		failure:failure];

	if (data.progressDelegate) {
		[operation setUploadProgressBlock:
			^(NSUInteger bytes, long long totalBytes, long long fileSize) {
				id<LRFileProgressDelegate> progressDelegate =
					data.progressDelegate;

				if ([progressDelegate respondsToSelector:@selector(isCancelled)]
					&& [progressDelegate isCancelled]) {

					[operation cancel];

					return;
				}

				NSData *uploadedData = [NSData dataWithBytes:&bytes
					length:sizeof(bytes)];

				[data.progressDelegate onProgress:uploadedData
					totalBytes:totalBytes];
			}
		];
	}

    [manager.operationQueue addOperation:operation];
}

@end
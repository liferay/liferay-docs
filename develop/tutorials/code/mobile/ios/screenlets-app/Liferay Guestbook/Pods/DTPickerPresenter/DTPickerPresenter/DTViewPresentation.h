//
//  DTPickerPresentation.h
//  DTPickerPresenter
//
//  Created by Denys Telezhkin on 27.08.14.
//  Copyright (c) 2014 MLSDev. All rights reserved.
//

#import <Foundation/Foundation.h>

/**
 DTViewPresentation is a protocol, that defines interface for presenters, that can be used to show arbitrary interfaces as an inputViews, popovers, etc.
 */
@protocol DTViewPresentation <NSObject>

/**
 UIView object, that is managed by DTPickerPresenter.
 */
@property (nonatomic, strong, readonly) UIView * presenterView;

@end

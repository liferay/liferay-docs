//
//  TNRadioButtonGroupProtocol.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 24/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import <Foundation/Foundation.h>

@protocol TNRadioButtonGroupProtocol <NSObject>

@required
- (void)setup;
- (void)createRadioButton;
- (void)selectWithAnimation:(BOOL)animated;

@optional

@end

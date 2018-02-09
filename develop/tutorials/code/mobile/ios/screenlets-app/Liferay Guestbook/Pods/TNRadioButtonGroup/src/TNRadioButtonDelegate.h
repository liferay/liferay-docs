//
//  TNRadioButtonDelegate.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import <Foundation/Foundation.h>

@class TNRadioButton;

@protocol TNRadioButtonDelegate <NSObject>

@required
- (void)radioButtonDidChange:(TNRadioButton *)radioButton;

@optional

@end

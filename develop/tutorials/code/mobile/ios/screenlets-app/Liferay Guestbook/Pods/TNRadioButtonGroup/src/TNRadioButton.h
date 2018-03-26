//
//  RadioButton.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TNRadioButtonData.h"
#import "TNRadioButtonDelegate.h"
#import "TNRadioButtonGroupProtocol.h"

#import "TNCircularRadioButtonData.h"
#import "TNRectangularRadioButtonData.h"
#import "TNImageRadioButtonData.h"

@interface TNRadioButton : UIView <TNRadioButtonGroupProtocol>

@property (nonatomic, copy) NSString *identifier;
@property (nonatomic, copy) NSString *label;
@property (nonatomic, strong) TNRadioButtonData *data;

@property (nonatomic, strong) UIView *radioButton;
@property (nonatomic, strong) UILabel *lblLabel;
@property (nonatomic, strong) UIButton *btnHidden;

@property (nonatomic, weak) id<TNRadioButtonDelegate> delegate;

- (instancetype)initWithData:(TNRadioButtonData *)data;
- (void)setup;
- (void)update;
- (void)selectWithAnimation:(BOOL)animated;

@end

//
//  RadioButtonGroup.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TNCircularRadioButton.h"
#import "TNRectangularRadioButton.h"
#import "TNImageRadioButton.h"

extern NSString *const SELECTED_RADIO_BUTTON_CHANGED;

typedef enum : NSUInteger {
    TNRadioButtonGroupLayoutHorizontal,
    TNRadioButtonGroupLayoutVertical
} TNRadioButtonGroupLayout;

@interface TNRadioButtonGroup : UIView <TNRadioButtonDelegate>

@property (nonatomic, copy) NSString *identifier;
@property (nonatomic) CGPoint position;
@property (nonatomic) NSInteger marginBetweenItems;
@property (nonatomic) UIEdgeInsets itemsInsets;

@property (nonatomic, strong) UIFont *labelFont;
@property (nonatomic, strong) UIColor *labelColor;

@property (nonatomic, strong) NSArray *radioButtons;
@property (nonatomic, weak) TNRadioButton *selectedRadioButton;

- (instancetype)initWithRadioButtonData:(NSArray *)radioButtonData layout:(TNRadioButtonGroupLayout)layout;
- (void)create;
- (void)update;
@end

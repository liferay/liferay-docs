//
//  TNCircularRadioButton.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "TNRadioButton.h"

@interface TNCircularRadioButton : TNRadioButton

@property (nonatomic, strong) TNCircularRadioButtonData *data;

- (instancetype)initWithData:(TNCircularRadioButtonData *)data;

@end

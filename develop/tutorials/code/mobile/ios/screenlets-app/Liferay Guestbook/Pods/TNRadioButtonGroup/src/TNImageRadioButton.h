//
//  CustomImageRadioButton.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRadioButton.h"

@interface TNImageRadioButton : TNRadioButton

@property (nonatomic, strong) TNImageRadioButtonData *data;

- (instancetype)initWithData:(TNImageRadioButtonData *)data;

@end

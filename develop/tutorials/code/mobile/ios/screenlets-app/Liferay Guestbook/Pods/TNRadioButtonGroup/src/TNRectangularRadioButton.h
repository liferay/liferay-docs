//
//  TNRectangularRadioButton.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRadioButton.h"

@interface TNRectangularRadioButton : TNRadioButton

@property (nonatomic, strong) TNRectangularRadioButtonData *data;

- (instancetype)initWithData:(TNRectangularRadioButtonData *)data;

@end

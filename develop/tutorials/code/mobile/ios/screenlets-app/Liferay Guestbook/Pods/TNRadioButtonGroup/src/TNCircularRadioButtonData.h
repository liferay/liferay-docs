//
//  TNCircularRadioButtonData.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRadioButtonData.h"

@interface TNCircularRadioButtonData : TNRadioButtonData

@property (nonatomic, strong) UIColor *borderColor;
@property (nonatomic, strong) UIColor *circleColor;

@property (nonatomic) NSInteger borderRadius;
@property (nonatomic) NSInteger circleRadius;

@property (nonatomic) CGFloat circleLineThickness;

@end

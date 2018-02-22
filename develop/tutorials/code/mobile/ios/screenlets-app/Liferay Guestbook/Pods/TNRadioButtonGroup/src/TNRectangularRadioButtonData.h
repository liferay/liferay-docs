//
//  TNRectangularRadioButtonData.h
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRadioButtonData.h"

@interface TNRectangularRadioButtonData : TNRadioButtonData

@property (nonatomic, strong) UIColor *borderColor;
@property (nonatomic, strong) UIColor *rectangleColor;

@property (nonatomic) NSInteger borderWidth;
@property (nonatomic) NSInteger borderHeight;
@property (nonatomic) NSInteger rectangleWidth;
@property (nonatomic) NSInteger rectangleHeight;

@property (nonatomic) CGFloat borderLineThickness;

@end

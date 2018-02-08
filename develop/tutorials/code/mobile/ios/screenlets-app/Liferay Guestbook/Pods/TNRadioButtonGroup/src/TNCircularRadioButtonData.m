//
//  TNCircularRadioButtonData.m
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNCircularRadioButtonData.h"

@implementation TNCircularRadioButtonData

- (instancetype)init {
    
    self = [super init];

    if (self) {
        
    }
    
    return self;
}

#pragma mark - Getters and setters
- (UIColor *)borderColor {
    
    if( !_borderColor ){
        _borderColor = [UIColor blackColor];
    }
    
    return _borderColor;
}

- (UIColor *)circleColor {
    
    if( !_circleColor ){
        _circleColor = [UIColor blackColor];
    }
    
    return _circleColor;
}

- (NSInteger)borderRadius {
    
    if( !_borderRadius ){
        _borderRadius = 12;
    }
    
    return _borderRadius;
}

- (NSInteger)circleRadius {
    
    if( !_circleRadius ){
        _circleRadius = 6;
    }
    
    return _circleRadius;
}

- (CGFloat)circleLineThickness {
	if( !_circleLineThickness ){
        _circleLineThickness = 2.0f;
    }
    
    return _circleLineThickness;
}

@end

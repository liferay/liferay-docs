//
//  TNRectangularRadioButtonData.m
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRectangularRadioButtonData.h"

@implementation TNRectangularRadioButtonData


#pragma mark - Getters and setters
- (UIColor *)borderColor {
    
    if( !_borderColor ){
        _borderColor = [UIColor blackColor];
    }
    
    return _borderColor;
}

- (UIColor *)rectangleColor {
    
    if( !_rectangleColor ){
        _rectangleColor = [UIColor blackColor];
    }
    
    return _rectangleColor;
}

- (NSInteger)borderWidth {
    
    if( !_borderWidth ){
        _borderWidth = 12;
    }
    
    return _borderWidth;
}

- (NSInteger)borderHeight {
    
    if( !_borderHeight ){
        _borderHeight = 12;
    }
    
    return _borderHeight;
}

- (NSInteger)rectangleWidth {
    
    if( !_rectangleWidth ){
        _rectangleWidth = 6;
    }
    
    return _rectangleWidth;
}

- (NSInteger)rectangleHeight {
    
    if( !_rectangleHeight ){
        _rectangleHeight = 6;
    }
    
    return _rectangleHeight;
}

- (CGFloat)borderLineThickness {
	if( !_borderLineThickness ){
        _borderLineThickness = 2.0f;
    }
    
    return _borderLineThickness;
}



@end

//
//  TNCircularRadioButton.m
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRectangularRadioButton.h"

@interface TNRectangularRadioButton()

@property (nonatomic, strong) CAShapeLayer *border;
@property (nonatomic, strong) CAShapeLayer *rectangle;

@end

@implementation TNRectangularRadioButton

#pragma mark - Initializers

- (instancetype)initWithData:(TNRectangularRadioButtonData *)data {
    
    self = [super initWithData:data];
    
    if (self) {
        // Initialization code
        self.data = data;
        
        [self setup];
    }
    
    return self;
}

#pragma mark - Creation
- (void)setup{
    
    [self createRadioButton];
    
    [super setup];
}

- (void)update {
	[super update];

    self.radioButton.frame = CGRectMake(0, 0, self.data.borderWidth, self.data.borderHeight);

	[self updateBorder];
	[self updateRectangle];
}

- (void)updateBorder {
   self.border.frame = CGRectMake(0, 0, self.data.borderWidth, self.data.borderHeight);
    self.border.lineWidth = self.data.borderLineThickness;
    self.border.strokeColor = self.data.borderColor.CGColor;
    self.border.fillColor = [UIColor clearColor].CGColor;
}

- (void)updateRectangle {
    self.rectangle.frame = CGRectMake((self.border.frame.size.width - self.data.rectangleWidth) / 2, (self.border.frame.size.height - self.data.rectangleHeight) / 2, self.data.rectangleWidth, self.data.rectangleHeight);
    self.rectangle.lineWidth = 0;
    self.rectangle.fillColor = self.data.rectangleColor.CGColor;
}

- (void)createRadioButton {
    self.radioButton = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.data.borderWidth, self.data.borderHeight)];
    
    UIBezierPath *borderPath = [UIBezierPath bezierPathWithRect:CGRectMake(0, 0, self.data.borderWidth, self.data.borderHeight)];
    
    UIBezierPath *rectangularPath = [UIBezierPath bezierPathWithRect:CGRectMake(0, 0, self.data.rectangleWidth, self.data.rectangleHeight)];
    
    self.border = [CAShapeLayer layer];
    self.border.path = borderPath.CGPath;
	[self updateBorder];

    [self.radioButton.layer addSublayer:self.border];

    self.rectangle = [CAShapeLayer layer];
    self.rectangle.path = rectangularPath.CGPath;
	[self updateRectangle];

    [self.radioButton.layer addSublayer:self.rectangle];
    
    [self addSubview:self.radioButton];
}

#pragma mark - Animations
- (void)selectWithAnimation:(BOOL)animated {
    NSNumber *scaleValue = (self.data.selected) ? @1 : @0;
    
    if( animated ){
        CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:@"transform.scale"];
        animation.toValue = scaleValue;
        animation.duration = 0.3f;
        
        [self.rectangle addAnimation:animation forKey:@"scale"];
    }
    self.rectangle.transform = CATransform3DMakeScale(scaleValue.floatValue, scaleValue.floatValue, 0);
}

@end

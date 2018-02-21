//
//  TNCircularRadioButton.m
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNCircularRadioButton.h"

@interface TNCircularRadioButton()

@property (nonatomic, strong) CAShapeLayer *border;
@property (nonatomic, strong) CAShapeLayer *circle;

@end

@implementation TNCircularRadioButton

#pragma mark - Initializers

- (instancetype)initWithData:(TNCircularRadioButtonData *)data {
    
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

    self.radioButton.frame = CGRectMake(0, 0, self.data.borderRadius, self.data.borderRadius);

	[self updateBorder];
	[self updateCircle];
}

- (void)updateBorder {
    self.border.frame = CGRectMake(0, 0, self.data.borderRadius, self.data.borderRadius);
    self.border.lineWidth = self.data.circleLineThickness;
    self.border.strokeColor = self.data.borderColor.CGColor;
    self.border.fillColor = [UIColor clearColor].CGColor;
}

- (void)updateCircle {
    self.circle.frame = CGRectMake((self.border.frame.size.width - self.data.circleRadius) / 2, (self.border.frame.size.height - self.data.circleRadius) / 2, self.data.circleRadius, self.data.circleRadius);
    self.circle.lineWidth = 0;
    self.circle.fillColor = self.data.circleColor.CGColor;
}

- (void)createRadioButton {
    self.radioButton = [[UIView alloc] initWithFrame:CGRectMake(0, 0, self.data.borderRadius, self.data.borderRadius)];
    
    UIBezierPath *borderPath = [UIBezierPath bezierPathWithOvalInRect:CGRectMake(0, 0, self.data.borderRadius, self.data.borderRadius)];
    
    UIBezierPath *circlePath = [UIBezierPath bezierPathWithOvalInRect:CGRectMake(0, 0, self.data.circleRadius, self.data.circleRadius)];
    
    self.border = [CAShapeLayer layer];
    self.border.path = borderPath.CGPath;
	[self updateBorder];

    [self.radioButton.layer addSublayer:self.border];
    
    self.circle = [CAShapeLayer layer];
    self.circle.path = circlePath.CGPath;
	[self updateCircle];

    [self.radioButton.layer addSublayer:self.circle];
    
    [self addSubview:self.radioButton];
    
}

#pragma mark - Animations
- (void)selectWithAnimation:(BOOL)animated {
    NSNumber *scaleValue = (self.data.selected) ? @1 : @0;
    
    if( animated ){
        CABasicAnimation *animation = [CABasicAnimation animationWithKeyPath:@"transform.scale"];
        animation.toValue = scaleValue;
        animation.duration = 0.3f;
        
        [self.circle addAnimation:animation forKey:@"scale"];
    }
    self.circle.transform = CATransform3DMakeScale(scaleValue.floatValue, scaleValue.floatValue, 0);
}

@end

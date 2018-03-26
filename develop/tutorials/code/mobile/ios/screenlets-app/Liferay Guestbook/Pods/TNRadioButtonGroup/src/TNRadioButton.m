//
//  RadioButton.m
//  TNRadioButtonGroupDemo
//
//  Created by Frederik Jacques on 22/04/14.
//  Copyright (c) 2014 Frederik Jacques. All rights reserved.
//

#import "TNRadioButton.h"

@interface TNRadioButton()

@end

@implementation TNRadioButton

- (instancetype)initWithData:(TNRadioButtonData *)data {
    
    self = [super init];
    
    if (self) {
        self.data = data;
    }
    
    return self;
}

- (void)setup {
    
    [self createLabel];
    [self createHiddenButton];
    
    [self selectWithAnimation:NO];
    self.frame = self.btnHidden.frame;
}

- (void)update {
	[self updateLabel];
}

- (void)updateLabel {
    self.lblLabel.backgroundColor = [UIColor clearColor];
    self.lblLabel.numberOfLines = 0;
    self.lblLabel.lineBreakMode = NSLineBreakByWordWrapping;
    self.lblLabel.font = self.data.labelFont;
    self.lblLabel.textColor = self.data.labelColor;
    self.lblLabel.text = self.data.labelText;
}

- (void)createRadioButton {}

- (void)createLabel {
    
    CGSize labelSize;
    
    if (floor(NSFoundationVersionNumber) <= NSFoundationVersionNumber_iOS_6_1) {
        labelSize = [self.data.labelText sizeWithFont:self.data.labelFont forWidth:150 lineBreakMode:NSLineBreakByWordWrapping];
        
    } else {
        CGRect labelRect = [self.data.labelText boundingRectWithSize:CGSizeMake(150, CGFLOAT_MAX) options:NSStringDrawingUsesLineFragmentOrigin attributes:@{NSFontAttributeName:self.data.labelFont} context:nil];
        
        labelSize = CGSizeMake(labelRect.size.width, labelRect.size.height);
        
    }

    self.lblLabel = [[UILabel alloc] initWithFrame:CGRectMake(self.radioButton.frame.origin.x + self.radioButton.frame.size.width + 15, (self.radioButton.frame.size.height - labelSize.height) / 2, labelSize.width, labelSize.height)];
	[self updateLabel];
    [self addSubview:self.lblLabel];
}

- (void)createHiddenButton {
    
    int height = MAX(self.lblLabel.frame.size.height, self.radioButton.frame.size.height);
    
    self.btnHidden = [UIButton buttonWithType:UIButtonTypeCustom];
    self.btnHidden.frame = CGRectMake(0, 0, self.lblLabel.frame.origin.x + self.lblLabel.frame.size.width, height);
    [self addSubview:self.btnHidden];
    
    [self.btnHidden addTarget:self action:@selector(buttonTapped:) forControlEvents:UIControlEventTouchUpInside];
}

- (void)buttonTapped:(id)sender {
    
    if( !self.data.selected ){
        self.data.selected = !self.data.selected;
        
        if ([self.delegate respondsToSelector:@selector(radioButtonDidChange:)]) {
            [self.delegate radioButtonDidChange:self];
        }
        
    }
}

#pragma mark - Animations
- (void)selectWithAnimation:(BOOL)animated {}

@end

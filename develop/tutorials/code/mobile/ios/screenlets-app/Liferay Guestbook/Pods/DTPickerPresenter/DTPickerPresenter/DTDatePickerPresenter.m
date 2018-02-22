//
//  DTDatePickerPresenter.m
//  DTPickerPresenter
//
//  Created by Denys Telezhkin on 12.02.14.
//  Copyright (c) 2014 MLSDev. All rights reserved.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

#import "DTDatePickerPresenter.h"

@implementation DTDatePickerPresenter

@synthesize datePicker = _datePicker;

+ (instancetype)presenterWithChangeBlock:(DTDatePickerChangeBlock)changeBlock
{
    DTDatePickerPresenter * presenter = [self new];

    presenter.changeBlock = changeBlock;

    return presenter;
}

- (void)dealloc
{
    [self.datePicker removeTarget:self action:NULL forControlEvents:UIControlEventValueChanged];
}

- (void)setDatePicker:(UIDatePicker *)newDatePicker
{
    NSParameterAssert([newDatePicker isKindOfClass:[UIDatePicker class]]);

    _datePicker = newDatePicker;
    [_datePicker addTarget:self action:@selector(valueChanged:) forControlEvents:UIControlEventValueChanged];
}

- (UIDatePicker *)datePicker
{
    if (!_datePicker) {
        _datePicker = [UIDatePicker new];
        [_datePicker addTarget:self action:@selector(valueChanged:) forControlEvents:UIControlEventValueChanged];
    }
    return _datePicker;
}

#pragma mark - Base presenter view

- (UIView *)presenterView {
	return self.datePicker;
}

- (void)valueChanged:(UIDatePicker *)picker
{
    if (self.changeBlock) {
        self.changeBlock(self.datePicker.date);
    }
}

@end

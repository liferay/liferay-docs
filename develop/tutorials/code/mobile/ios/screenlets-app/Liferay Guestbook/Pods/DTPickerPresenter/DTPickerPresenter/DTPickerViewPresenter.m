//
//  DTPickerViewPresenter.m
//  DTPickerPresenter
//
//  Created by Denys Telezhkin on 30.01.14.
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

#import "DTPickerViewPresenter.h"

@interface DTPickerViewPresenter () <UIPickerViewDelegate>
@end

@implementation DTPickerViewPresenter

@synthesize pickerView=_pickerView;

+ (instancetype)presenterWithDatasource:(id <DTPickerDatasourceProtocol>)dataSource
                            changeBlock:(DTPickerChangeBlock)changeBlock
{
    DTPickerViewPresenter * presenter = [self new];

    presenter.dataSource = dataSource;
    [dataSource setDelegate:presenter];
    presenter.changeBlock = changeBlock;

    return presenter;
}

- (void)setPickerView:(UIPickerView *)pickerView
{
    NSParameterAssert([pickerView isKindOfClass:[UIPickerView class]]);

    _pickerView = pickerView;
}

- (UIPickerView *)pickerView
{
    if (!_pickerView) {
        _pickerView = [UIPickerView new];
        _pickerView.dataSource = self.dataSource;
        _pickerView.delegate = self.dataSource;
        _pickerView.showsSelectionIndicator = YES;
    }
    return _pickerView;
}

#pragma mark - Base presenter view

- (UIView *)presenterView {
	return self.pickerView;
}

#pragma mark - UIPickerViewDelegate

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
    NSIndexPath * selectedIndexPath = [NSIndexPath new];

    for (NSInteger i = 0; i < self.pickerView.numberOfComponents; i++) {
        selectedIndexPath = [selectedIndexPath indexPathByAddingIndex:(NSUInteger)[self.pickerView selectedRowInComponent:i]];
    }

    NSMutableArray * resultsArray = [NSMutableArray arrayWithCapacity:[selectedIndexPath length]];

    for (NSUInteger position = 0; position < [selectedIndexPath length]; position++) {
        NSUInteger selectedValue = [selectedIndexPath indexAtPosition:position];
        [resultsArray addObject:[self.dataSource.items[position] objectAtIndex:selectedValue]];
    }

    self.changeBlock([resultsArray copy], selectedIndexPath);
}

@end

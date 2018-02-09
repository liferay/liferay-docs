//
// Created by Denys Telezhkin on 16.02.14.
// Copyright (c) 2014 MLSDev. All rights reserved.
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

#import "DTViewPresentation.h"

/**
 This category allows easily create UIPopoverController instance with presenter to control UIPickerView or UIDatePicker object.
 */

@interface UIPopoverController (DTPickerPresenter)

/**
 Method to create UIPopoverController with UIPickerView(or UIDatePicker) of default size(320x216 points).
 
 @param presenter DTPickerViewPresenter or DTDatePickerPresenter instance to manage picker.
 
 @result UIPopoverController object.
 */
+ (UIPopoverController *)dt_popoverWithPresenter:(id <DTViewPresentation>)presenter;

/**
 Method to create UIPopoverController of concrete size. Keep in mind, however, that UIPickerView on iOS 6 and iOS 7 generally has a default size of 320x216 points.
 
 @param presenter DTPickerViewPresenter or DTDatePickerPresenter instance to manage picker.
 
 @param size desired size of UIPopoverController
 
 @result UIPopoverController object.
 */
+ (UIPopoverController *)dt_popoverWithPresenter:(id <DTViewPresentation>)presenter ofSize:(CGSize)size;

/**
 Getter for presenter object, previously set on UIPopoverController object.
 
 @return DTPickerViewPresenter or DTDatePickerPresenter object.
 */
- (id <DTViewPresentation>)dt_presenter;

@end
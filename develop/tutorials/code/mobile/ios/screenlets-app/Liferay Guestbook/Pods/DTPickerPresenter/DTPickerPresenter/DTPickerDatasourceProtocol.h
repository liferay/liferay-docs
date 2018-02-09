//
//  DTPickerViewDatasource.h
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

/**
 `DTPickerDatasourceProtocol` is a protocol for UIPickerView datasource class. Unfortunately, separation between UIPickerViewDelegate and UIPickerViewDatasource is pretty much wrong. Class, implementing this protocol should respond to all datasource methods, even if they are declared in UIPickerViewDelegate. 
 
 One additional request is to trampoline UIPickerViewDelegate didSelectRow: method to delegate of this class, which is supposed to be `true` delegate, responding to events, rather than providing datasource.
 */

@protocol DTPickerDatasourceProtocol <UIPickerViewDelegate, UIPickerViewDataSource>

/**
 UIPickerViewDelegate object.
 */
@property (nonatomic, weak) id <UIPickerViewDelegate> delegate;

/**
 Array of arrays of items, providing data for UIPickerView. 
 */
@property (nonatomic, retain) NSArray * items;

@end

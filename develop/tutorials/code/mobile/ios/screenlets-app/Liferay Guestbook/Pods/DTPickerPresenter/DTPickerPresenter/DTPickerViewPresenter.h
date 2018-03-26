//
//  DTPickerViewPresenter.h
//  LiverpoolOne
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

#import "DTPickerDatasourceProtocol.h"
#import "DTViewPresentation.h"

typedef void(^DTPickerChangeBlock)(NSArray * selectedComponents, NSIndexPath * selectedIndexPath);

/**
 `DTPickerViewPresenter` manages UIPickerView presentation and wraps UIPickerViewDelegate callbacks into nice, modern block API. While datasource is a separate class, presenter needs to be notified when UIPickerView values change. Which is datasource needs to call presenter when that happens. If you use default datasource class provided - `DTPickerDatasource`, this happens automatically.
 */

@interface DTPickerViewPresenter : NSObject <DTViewPresentation>

/**
 UIPickerView object, that is managed by DTPickerViewPresenter.
 */
@property (nonatomic, strong) UIPickerView * pickerView;

/**
 Datasource object, providing UIPickerView with data to display.
 */
@property (nonatomic, strong) id <DTPickerDatasourceProtocol> dataSource;

/**
 Change block, that will be called automatically, whenever UIPickerView value changes.
 */
@property (nonatomic, copy) DTPickerChangeBlock changeBlock;

/**
 Convenience method to create DTPickerViewPresenter.
 
 @param dataSource datasource object, that needs to provide UIPickerView with data to display.
 
 @param changeBlock block, that will be called for each UIPickerView change
 
 @result instance of DTPickerViewPresenter object.
 */

+ (instancetype)presenterWithDatasource:(id <DTPickerDatasourceProtocol>)dataSource
                            changeBlock:(DTPickerChangeBlock)changeBlock;

@end

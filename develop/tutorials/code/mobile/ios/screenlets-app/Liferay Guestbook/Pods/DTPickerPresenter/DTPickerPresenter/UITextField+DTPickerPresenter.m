//
//  UITextField+DTPickerPresenterAdditions.m
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

#import "UITextField+DTPickerPresenter.h"
#import "DTViewPresenter.h"
#import <objc/runtime.h>

@implementation UITextField (DTPickerPresenter)

- (void)dt_setPresenter:(DTViewPresenter *)presenter
{
    objc_setAssociatedObject(self, @selector(dt_presenter), presenter, OBJC_ASSOCIATION_RETAIN_NONATOMIC);

    self.inputView = [presenter presenterView];
}

- (DTViewPresenter *)dt_presenter
{
    return objc_getAssociatedObject(self, @selector(dt_presenter));
}

@end

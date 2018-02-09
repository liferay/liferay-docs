![Build Status](https://travis-ci.org/DenHeadless/DTRequestVerifier.png?branch=master) &nbsp;
![CocoaPod platform](https://cocoapod-badges.herokuapp.com/p/DTPickerPresenter/badge.png) &nbsp; 
![CocoaPod version](https://cocoapod-badges.herokuapp.com/v/DTPickerPresenter/badge.png) &nbsp; 
![License MIT](https://go-shields.herokuapp.com/license-MIT-blue.png)
DTPickerPresenter
=================

DTPickerPresenter is clean and modern wrapper for UIDatePicker and UIPickerView. It provides easy way to present UIDatePicker UIPickerView, and any arbitrary UIView both on iPhone and iPad with very little amount of code. For example, presenting UIDatePicker is just two lines of code:

```objective-c
DTDatePickerPresenter * presenter = [DTDatePickerPresenter presenterWithChangeBlock:^(NSDate * selectedDate) {
    // Probably format with locale and time zones?
}];
[self.datePickerTextField dt_setPresenter:presenter];
```

## UIDatePicker 

On the iPhone, you'll likely use UITextField inputView to present UIDatePicker.

#### iPhone

```objective-c
@interface iPhonePickerController () 
@property (weak, nonatomic) IBOutlet UITextField * datePickerTextField;
@end

-(void)setupPicker 
{
    __weak typeof (self) weakSelf = self;
    DTDatePickerPresenter * presenter = [DTDatePickerPresenter presenterWithChangeBlock:^(NSDate * selectedDate) {
        // Probably format with locale and time zones?
    }];
    [self.datePickerTextField dt_setPresenter:presenter];
}
```

It's that simple. You just create presenter and attach it to the UITextField, and presenter will do the rest.

#### iPad

Conventional use of UIDatePicker on iPad includes creating UIDatePicker, UIViewController, that will contain it, UIPopoverController with contentSize of UIDatePicker, and then handle events of UIDatePicker. Let's simplify that! With DTPickerPresenter here's what you need to do:

```objective-c
__weak typeof (self) weakSelf = self;
DTDatePickerPresenter * presenter = [DTDatePickerPresenter presenterWithChangeBlock:^(NSDate * selectedDate) {
    //Process date
}];
self.popover = [UIPopoverController dt_popoverWithPresenter:presenter];
// present popover.
```
And so, with just two lines of code, we created date picking UIPopoverController. But let's not stop there!

## UIPickerView

The same rules, that apply to UIDatePicker, are common sense for UIPickerView. UITextField inputView on iPhone and UIPopoverController for iPad. We'll need additional datasource and delegate properties, though. Before we start on UI presentation, let's dive into UIPickerViewDelegate and UIPickerViewDatasource wrapper class.

#### UIPickerViewDatasource

DTPickerPresenter provides datasource wrapper in a form of abstract protocol `DTPickerDatasourceProtocol` and concrete implementation, `DTPickerDatasource`. Creating datasource object is simple

```objective-c
DTPickerDataSource * datasource = [DTPickerDataSource datasourceWithItems:@[@[@"foo", @"bar"], @[@"1", @"2"]]];
```
This datasource would represent two components(wheels) with two rows each. Currently supported for models are NSString and NSAttributedString classes. If you need additional customization, for example components widths and heights, or custom views for rows, subclass DTPickerDatasource class. Let's move on to presenter stuff. First let's create change block, that will be called each time UIPickerView selection changes

```objective-c
__weak typeof (self) weakSelf = self;
DTPickerChangeBlock block = ^(NSArray * selectedComponents, NSIndexPath * selectedIndexPath) {
        // Process UIPickerView changes        
};
```

Presentation is identical to UIDatePicker earlier.

#### iPhone

```objective-c
DTPickerDataSource * datasource = [DTPickerDataSource datasourceWithItems:@[@[@"foo", @"bar"], @[@"1", @"2"]]];
__weak typeof (self) weakSelf = self;
DTPickerChangeBlock block = ^(NSArray * selectedComponents, NSIndexPath * selectedIndexPath) {
    // Process UIPickerView changes
};
DTPickerViewPresenter * wheelPresenter = [DTPickerViewPresenter presenterWithDatasource:datasource
                                                                                changeBlock:block];
[self.wheelPickerTextField dt_setPresenter:wheelPresenter];
```  

#### iPad

```objective-c
DTPickerDataSource * datasource = [DTPickerDataSource datasourceWithItems:@[@[@"foo", @"bar"], @[@"1", @"2"]]];
__weak typeof (self) weakSelf = self;
DTPickerChangeBlock block = ^(NSArray * selectedComponents, NSIndexPath * selectedIndexPath) {
    // Process UIPickerView changes
};
DTPickerViewPresenter * wheelPresenter = [DTPickerViewPresenter presenterWithDatasource:datasource
                                                                            changeBlock:block];
self.popover = [UIPopoverController dt_popoverWithPresenter:wheelPresenter];
// Present popover
``` 

## Installation

```
pod 'DTPickerPresenter', '~> 0.2.0'
```

## Best practices
    
- Beware of retain cycles! Presenter objects are stored on UITextField or UIPopoverController instances, so if you reference self without making it weak, you'll produce retain cycle.

## Requirements

* iOS 6 and higher
* ARC

Radio buttons for Objective-C
=========================

U can use the TNRadioButtonGroup class to use some pre-built radio buttons, or you can extend it really easy to suit your own design.
Every radio button has a selected animation.  When the text is longer then the available space, it will automatically wrap the text over multiple lines.

The current version supports 3 different radio buttons:

1. Circle radio button

2. Square radio button

3. Custom image radio button

![Screenshot](http://cl.ly/VBJJ/tnradiobuttongroup.png)

Installation
=============

##Manual
* Just drag the files in the src folder to your project.
* Import the radio button class you want to use

##Cocoapods
* Add ``` pod 'TNRadioButtonGroup' ``` to your Podfile
* Done

How to use
==========

Import the ```TNRadioButtonGroup.h``` file.

Create data objects for every option in your radio button group.  You can use the following data objects: ```TNCircularRadioButtonData```, ```TNRectangularRadioButtonData```, ```TNImageRadioButtonData```.

Every data object extends ```TNRadioButtonData```, but has specific properties you can set.

You can set the following properties on every data object:

Property  | What does it do
 ------------- | ------------- 
 identifier    | Set a human readeble name for the radio button
 labelText    | Set the text for the label
 selected    | Set the selected state
 labelFont    | Set the font for the label
 labelColor    | Set the color for the label

The different classes have the following extra properties:

### TNCircularRadioButtonData

Arguments  | What does it do
 ------------- | ------------- 
 borderColor    | Set the border color for the outer circle
 circleColor    | Set the color for the inner circle
 borderRadius    | Set the radius for the outer circle
 circleRadius    | Set the color for the inner circle

Example
 
    TNCircularRadioButtonData *maleData = [TNCircularRadioButtonData new];
    maleData.labelText = @"Male";
    maleData.identifier = @"male";
    maleData.selected = YES;
    maleData.borderColor = [UIColor blackColor];
    maleData.circleColor = [UIColor blackColor];
    maleData.borderRadius = 12;
    maleData.circleRadius = 5;
 
### TNRectangularRadioButtonData

Arguments  | What does it do
 ------------- | ------------- 
 borderColor    | Set the border color for the outer rectangle
 rectangleColor    | Set the color for the inner rectangle
 borderWidth    | Set the width for the outer rectangle
 borderHeight    | Set the height for the outer rectangle
 rectangleWidth    | Set the width for the inner rectangle
 rectangleHeight    | Set the height for the inner rectangle
 
Example

    TNRectangularRadioButtonData *snowboardData = [TNRectangularRadioButtonData new];
    snowboardData.labelText = @"Snowboarding";
    snowboardData.identifier = @"snowboarding";
    snowboardData.selected = NO;
    snowboardData.borderColor = [UIColor blackColor];
    snowboardData.rectangleColor = [UIColor blackColor];
    snowboardData.borderWidth = snowboardData.borderHeight = 12;
    snowboardData.rectangleWidth = snowboardData.rectangleHeight = 5;

### TNImageRadioButtonData

Arguments  | What does it do
 ------------- | ------------- 
 selectedImage    | Set the image for the selected state
 unselectedImage    | Set the image for the unselected state
 
Example 

    TNImageRadioButtonData *coldData = [TNImageRadioButtonData new];
    coldData.labelText = @"Hot";
    coldData.identifier = @"hot";
    coldData.selected = YES;
    coldData.unselectedImage = [UIImage imageNamed:@"unchecked"];
    coldData.selectedImage = [UIImage imageNamed:@"checked"];

When you have created the different data objects, you can create an instance of ```TNRadioButtonGroup``` and pass an array with the data objects and set the layout. The layout can be horizontal or vertical.

You can also set the margin between each item via the ``` marginBetweenItems ``` property. By default it's set to 15 pixels.

If you want, you can also set the ``` identifier``` property to have a human readable name for the group.

Call the ```create``` method and set the ```position``` property to place the group at a specific point.  Don't worry about the frame, this will be automatically calculated.
And add the object to the view hierarchy.

    self.myGroup = [[TNRadioButtonGroup alloc] initWithRadioButtonData:@[data1, data2, data3] style:TNRadioButtonGroupStyleHorizontal];
    self.myGroup.identifier = @"My group";
    [self.myGroup create];
    self.myGroup.position = CGPointMake(15, 50);
    [self addSubview:self.myGroup];

Done!

##Getting change notifications

When the state of the radio button group has changed an _NSNotification_ is broadcasted.

You can listen to the ``` SELECTED_RADIO_BUTTON_CHANGED ``` message.

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(myGroupGotUpdated:) name:SELECTED_RADIO_BUTTON_CHANGED object:self.myGroup];

## Styling checkboxes
### Group-level
If you want to provide the same label font and color for all radio buttons in a specific group, you can set the ``` labelFont ``` and ```labelColor ``` properties.

### Checkbox-level
If you want to provide different label fonts and/or colors for specific radio buttons in a group, you can set the ``` labelFont ``` and ``` labelColor ``` properties on the TNRadioButton subclass.

Creating custom radio buttons
===========================
If you want it is really easy to add your own custom radio buttons.

1. Create a data model class in which you store the specific data for your checkbox. This class needs to extend ``` TNRadioButtonData ```.

2. Create a radio button class which extends ``` TNRadioButton ```.

3. Override the ``` setup ``` method (don't forget to call the super method at the end).

4. Override the ``` createRadioButton ``` method, here you can draw your custom radio button.

5. Override the ``` checkWithAnimation: ``` method to supply your own animations when the radio button state changes.

6. There is no step 6.


Demo
=====
There is a demo project added to this repository, so you can see how it works.

License
=======
TNRadioButtonGroup published under the MIT license:

Copyright (C) 2014, Frederik Jacques

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
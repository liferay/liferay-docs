# Using the Clay Taglib in Your portlets [](id=using-the-clay-taglib-in-your-portlets)

The Liferay Clay tag library provides a set of tags for creating 
[Clay](https://claycss.com/docs/clay/) 
UI components in your app. To use the Clay taglib, add the following declaration 
to your JSP:

    <%@ taglib prefix="clay" uri="http://liferay.com/tld/clay" %>

The 
[`com.liferay.frontend.taglib.clay.sample.web` portlet](https://github.com/liferay/liferay-portal/tree/7.0.x/modules/apps/foundation/frontend-taglib/frontend-taglib-clay-sample-web) 
is referenced throughout this tutorial as an example. It's included 
out-of-the-box with @product@. Open the Control Menu and add the 
*Clay Sample* portlet from the *Applications* &rarr; *Sample* menu to see it in 
action.

![Figure 1: The Clay Sample portlet demonstrates the various types of clay components you can create with the clay taglbs.](../../images/clay-taglib-sample-portlet.png)

Clay taglibs provide the following UI components for your apps:

- [Alerts](#alerts-)
- [Badges](#badges-)
- [Buttons](#buttons-)
- [Cards](#cards-)
- [Dropdowns](#dropdowns-)
- [Form Elements](#form-elements-)
- [Icons](#icons-)
- [Labels](#labels-)
- [Links](#links-)
- [Navigation Bars](#navigation-bars-)
- [Progress Bars](#progress-bars-)
- [Stickers](#stickers-)

This tutorial covers how to create these components with the Clay taglibs. Each 
section contains a set of clay component examples along with a screenshot of the 
resulting UI.

## Alerts [](id=alerts)

Clay alerts come in two types: embedded and stripe. This section covers both 
these types and explains the key differences.

### Embedded Alerts [](id=embedded-alerts)

Embedded alerts are usually used inside forms. An embedded alert's width is 
determined by the width of the element that contains it. The close action is not 
required for embedded alerts. The following embedded alerts can be created with 
Clay taglibs:

Danger alert (embedded):

    <clay:alert
    	message="This is an error message."
    	style="danger"
    	title="Error"
    />

![Figure 2: A bar chart models the data in bars](../../images/clay-taglib-alert-danger.png)

Success alert (embedded):

    <clay:alert
    	message="This is a success message."
    	style="success"
    	title="Success"
    />

![Figure 3: A bar chart models the data in bars](../../images/clay-taglib-alert-success.png)

Info alert (embedded):

    <clay:alert
    	message="This is an info message."
    	title="Info"
    />

![Figure 4: A bar chart models the data in bars](../../images/clay-taglib-alert-info.png)

Warning alert (embedded):

    <clay:alert
    	message="This is a warning message."
    	style="warning"
    	title="Warning"
    />

![Figure 5: A bar chart models the data in bars](../../images/clay-taglib-alert-warning.png)

### Stripe Alerts [](id=stripe-alerts)

Stripe alerts are placed below the last navigation element, either the header or 
the navigation bar. Stripe alerts usually appear on *Save* action, communicating 
the status of the action once received from the server. Stripe alert types 
require the close action. A strip alert is always the full width of the full 
container and pushes all the content below it. The following stripe alerts can 
be created with Clay taglibs:

Danger alert (stripe):

    <clay:stripe
    	message="This is an error message."
    	style="danger"
    	title="Error"
    />

![Figure 6: A bar chart models the data in bars](../../images/clay-taglib-alert-danger-stripe.png)

Success alert (stripe):

    <clay:stripe
    	message="This is a success message."
    	style="success"
    	title="Success"
    />

![Figure 7: A bar chart models the data in bars](../../images/clay-taglib-alert-success-stripe.png)

Info alert (stripe):

    <clay:stripe
    	message="This is an info message."
    	title="Info"
    />

![Figure 8: A bar chart models the data in bars](../../images/clay-taglib-alert-info-stripe.png)

Warning alert (stripe):

    <clay:stripe
    	message="This is a warning message."
    	style="warning"
    	title="Warning"
    />
    
![Figure 9: A bar chart models the data in bars](../../images/clay-taglib-alert-warning-stripe.png)

## Badges [](id=badges)

Badges help highlight important information such as notifications or new and 
unread messages. Badges have circular borders and are only used to specify a 
number. The following badge styles are available:

Primary badge:

    <div class="col-md-1">
        <clay:badge label="8" />

        <div>Primary</div>
    </div>

![Figure 10: A bar chart models the data in bars](../../images/clay-taglib-badge-primary.png)

Secondary badge:

    <div class="col-md-1">
        <clay:badge label="87" style="secondary" />

        <div>Secondary</div>
    </div>

![Figure 11: A bar chart models the data in bars](../../images/clay-taglib-badge-secondary.png)

Info badge:

    <div class="col-md-1">
        <clay:badge label="91" style="info" />

        <div>Info</div>
    </div>

![Figure 12: A bar chart models the data in bars](../../images/clay-taglib-badge-info.png)

Error badge:

    <div class="col-md-1">
        <clay:badge label="130" style="danger" />

        <div>Error</div>
    </div>

![Figure 13: A bar chart models the data in bars](../../images/clay-taglib-badge-error.png)

Success badge:

    <div class="col-md-1">
        <clay:badge label="1111" style="success" />

        <div>Success</div>
    </div>

![Figure 14: A bar chart models the data in bars](../../images/clay-taglib-badge-success.png)

Warning badge:

    <div class="col-md-1">
        <clay:badge label="21" style="warning" />

        <div>Warning</div>
    </div>

![Figure 15: A bar chart models the data in bars](../../images/clay-taglib-badge-warning.png)

## Buttons [](id=buttons)

Buttons come in several types and variations. This section covers the different 
styles and variations of buttons you can create with the Clay taglibs.

### Types [](id=types)

Primary button: Used for the most important actions. Two primary buttons should 
not be together or near by each other. You can use labels or icons for your 
buttons:

Primary button with label:

    <clay:button label="Primary" />

![Figure 16: A bar chart models the data in bars](../../images/clay-taglib-button-primary.png)
    
Primary button with icon:
    
    <clay:button ariaLabel="Workflow" icon="workflow" />

![Figure 17: A bar chart models the data in bars](../../images/clay-taglib-button-primary-icon.png)

+$$$

**Note:** You can disable a button by adding the `disabled` attribute:

    <div class="col">
        <clay:button disabled="<%= true %>" label="Primary" />
    </div>
    <div class="col">
        <clay:button ariaLabel="Workflow" disabled="<%= true %>" icon="workflow" />
    </div>

![Figure 18: A bar chart models the data in bars](../../images/clay-taglib-button-primary-disabled.png)

$$$

Secondary button: Used for secondary actions. There can be multiple secondary 
buttons together or near each other. 

    <div class="col">
        <clay:button label="Secondary" style="secondary" />
    </div>
    <div class="col">
        <clay:button ariaLabel="Wiki" icon="wiki" style="secondary" />
    </div>

![Figure 19: A bar chart models the data in bars](../../images/clay-taglib-button-secondary.png)

Borderless button: Used in cases, such as toolbars, where the secondary button 
would be too heavy for the design. This keeps the design clean.

    <div class="col">
        <clay:button label="Borderless" style="borderless" />
    </div>
    <div class="col">
        <clay:button ariaLabel="Page Template" icon="page-template" style="borderless" />
    </div>

![Figure 20: A bar chart models the data in bars](../../images/clay-taglib-button-borderless.png)

Link button: Used for Cancel actions.

    <div class="col">
        <clay:button label="Link" style="link" />
    </div>
    <div class="col">
        <clay:button ariaLabel="Add Role" icon="add-role" style="link" />
    </div>

![Figure 21: A bar chart models the data in bars](../../images/clay-taglib-button-link.png)

### Variations [](id=variations)

Button with icon and text:

    <clay:button icon="share" label="Share" />

![Figure 22: A bar chart models the data in bars](../../images/clay-taglib-button-icon-text.png)

Button with monospaced text:

    <clay:button icon="indent-less" monospaced="<%= true %>" style="secondary" />

![Figure 23: A bar chart models the data in bars](../../images/clay-taglib-button-monospaced.png)

Block level button: 

    <clay:button block="<%= true %>" label="Button" />
    
![Figure 24: A bar chart models the data in bars](../../images/clay-taglib-button-block-level.png)

Plus button:

    <clay:button icon="plus" monospaced="<%= true %>" style="secondary" />

![Figure 25: A bar chart models the data in bars](../../images/clay-taglib-button-plus.png)

Action button:

    <clay:button icon="ellipsis-v" monospaced="<%= true %>" style="borderless" />
    
![Figure 26: A bar chart models the data in bars](../../images/clay-taglib-button-action.png)

## Cards [](id=cards)

Cards are used to visually represent data. They can be used for images, document 
libraries, user profiles and more. There are four main types of Cards:

- Image Cards
- File Cards
- User Cards
- Horizontal Cards

Each of these types is covered in this section. 

### Image Cards [](id=image-cards)

Image Cards are used for image/document galleries. 

Image Card:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1506976773555-b3da30a63b57"
    	subtitle="Author Action"
    	title="Madrid"
    />
    
![Figure 27: A bar chart models the data in bars](../../images/clay-taglib-image-card.png)

Image Card with icon:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	icon="camera"
    	subtitle="Author Action"
    	title="<%= SVG_FILE_TITLE %>"
    />

![Figure 28: A bar chart models the data in bars](../../images/clay-taglib-image-card-icon.png)

Image Card empty:

    <clay:image-card 
      actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	subtitle="Author Action"
    	title="<%= SVG_FILE_TITLE %>"
    />

![Figure 29: A bar chart models the data in bars](../../images/clay-taglib-image-card-empty.png)

Cards can also contain file types. Specify the file type with the `filetype` 
attribute:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="JPG"
    	fileTypeStyle="danger"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1499310226026-b9d598980b90"
    	subtitle="Author Action"
    	title="California"
    />

![Figure 30: A bar chart models the data in bars](../../images/clay-taglib-image-card-file-type.png)

Include the `labels` attribute to add a label to a Card:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="JPG"
    	fileTypeStyle="danger"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1503703294279-c83bdf7b4bf4"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	subtitle="Author Action"
    	title="Beetle"
    />

![Figure 31: A bar chart models the data in bars](../../images/clay-taglib-image-card-icon-label.png)

Include the `selectable` attribute to make cards selectable:

    <clay:image-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="JPG"
    	fileTypeStyle="danger"
    	href="#1"
    	imageAlt="thumbnail"
    	imageSrc="https://images.unsplash.com/photo-1506020647804-b04ee956dc04"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	subtitle="Author Action"
    	title="Beetle"
    />

![Figure 32: A bar chart models the data in bars](../../images/clay-taglib-image-card-icon-selectable.png)

### File Cards [](id=file-cards)

File Cards display an icon of the file's type for the image. They are used to 
represent file types other than image files (i.e. PDF, MP3, DOC, etc.).

    <clay:file-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="MP3"
    	fileTypeStyle="warning"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	labelStylesMap="<%= cardsDisplayContext.getLabelStylesMap() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	subtitle="Jimi Hendrix"
    	title="<%= MP3_FILE_TITLE %>"
    />

![Figure 33: A bar chart models the data in bars](../../images/clay-taglib-file-card.png)

You can optionally use the `labelStylesMap` attribute to pass a `HashMap` of 
multiple labels, as shown above.

The example below specifies a list `icon` instead of the default file icon:  

    <clay:file-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	fileType="DOC"
    	fileTypeStyle="info"
    	icon="list"
    	labels="<%= cardsDisplayContext.getLabels() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	subtitle="Paco de Lucia"
    	title="<%= DOC_FILE_TITLE %>"
    />

### User Cards [](id=user-cards)

User Cards are used to display user profile images or the initials of the user's 
name or name+surname.

User Card with initials:

		<clay:user-card
			actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
			initials="HS"
			name="User Name"
			subtitle="Latest Action"
			userColor="danger"
		/>

![Figure 34: A bar chart models the data in bars](../../images/clay-taglib-user-card-initial.png)

User Card with profile image:

		<clay:user-card
			actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
			disabled="<%= true %>"
			imageAlt="thumbnail"
			imageSrc="https://images.unsplash.com/photo-1502290822284-9538ef1f1291"
			name="User name"
			selectable="<%= true %>"
			selected="<%= true %>"
			subtitle="Latest Action"
		/>

![Figure 35: A bar chart models the data in bars](../../images/clay-taglib-user-card-profile-image.png)

### Horizontal Cards [](id=horizontal-cards)

Horizontal Cards are primarily meant to represent folders and can have the same 
amount of information as other Cards. The key difference is that horizontal 
Cards let you remove the image portion of the Card, since only the folder icon 
is required.

    <clay:horizontal-card
    	actionItems="<%= cardsDisplayContext.getDefaultActionItems() %>"
    	selectable="<%= true %>"
    	selected="<%= true %>"
    	title="ReallySuperInsanelyJustIncrediblyLongAndTotallyNotPossibleWordButWeAreReallyTryingToCoverAllOurBasesHereJustInCaseSomeoneIsNutsAsPerUsual"
    />
    
![Figure 36: A bar chart models the data in bars](../../images/clay-taglib-horizontal-card.png)

## Navigation Menus [](id=navigation-menus)

You can add dropdown menus to your app via the `clay:dropdown-menu` and 
`clay:actions-menu` taglibs. The Clay taglibs provide several menu variations 
for you to choose. Both taglibs with several examples are shown below.

### Dropdown Menus [](id=dropdown-menus)

Basic dropdown menu:

    <clay:dropdown-menu
    	items="<%= dropdownsDisplayContext.getDefaultDropdownItems() %>"
    	label="Default"
    />
    
![Figure 37: A bar chart models the data in bars](../../images/clay-taglib-dropdown-basic.png)
    
The dropdown menu's items are defined in its java class--`dropdownDisplayContext` 
in this case. Menu items are `NavigationItem` objects. You can disable menu 
items with the `setDisabled(true)` method and make a menu item active with the 
`setActive(true)` method. The `href` attribute is set with the `setHref()` 
method, and labels are defined with the `setLabel()` method. An example 
implementation for the `dropdownDisplayContext` class is shown below:

    if (_defaultDropdownItems != null) {
      return _defaultDropdownItems;
    }

    _defaultDropdownItems = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      NavigationItem navigationItem = new NavigationItem();

      if (i == 1) {
        navigationItem.setDisabled(true);
      }
      else if (i == 2) {
        navigationItem.setActive(true);
      }

      navigationItem.setHref("#" + i);
      navigationItem.setLabel("Option " + i);

      _defaultDropdownItems.add(navigationItem);
    }

    return _defaultDropdownItems;
    }

You can organize your menu items into groups by setting the `NavigationItem`'s 
type to `TYPE_GROUP` and nesting the items in separate `ArrayList`s. You can add 
a horizontal separator to visually separate the groups with the 
`setSeparator(true)` method. Below is a code snippet from the 
`dropdownsDisplayContext` class:

    group1NavigationItem.setSeparator(true);
    group1NavigationItem.setType(NavigationItem.TYPE_GROUP);

Corresponding taglib:

    <clay:dropdown-menu
    	items="<%= dropdownsDisplayContext.getGroupDropdownItems() %>"
    	label="Dividers"
    />

![Figure 38: A bar chart models the data in bars](../../images/clay-taglib-dropdown-group.png)

You can also add inputs to dropdown menus. To add an input to a dropdown menu, 
set the input's type with the `setType()` method 
(e.g. `NavigationItem.TYPE_CHECKBOX`), its name with the `setInputName()` 
method, and its value with the `setInputValue()` method. An example 
implementation is shown below:

    navigationItem.setInputName("checkbox" + i);
    navigationItem.setInputValue("checkboxValue" + i);
    navigationItem.setLabel("Group 1 - Option " + i);
    navigationItem.setType(NavigationItem.TYPE_CHECKBOX);

Corresponding taglib:

    <clay:dropdown-menu
    	buttonLabel="Done"
    	items="<%= dropdownsDisplayContext.getInputDropdownItems() %>"
    	label="Inputs"
    	searchable="<%= true %>"
    />

![Figure 39: A bar chart models the data in bars](../../images/clay-taglib-dropdown-input.png)

Menu items can also contain icons. To add an icon to a menu item, use the 
`setIcon()` method. Below is an example:

    navigationItem.setIcon("check-circle-full")

Corresponding taglib:

    <clay:dropdown-menu
    	items="<%= dropdownsDisplayContext.getIconDropdownItems() %>"
    	itemsIconAlignment="left"
    	label="Icons"
    />

![Figure 40: A bar chart models the data in bars](../../images/clay-taglib-dropdown-icons.png)

### Actions Menus [](id=actions-menus)

Basic actions menu:

    <clay:dropdown-actions
    	items="<%= dropdownsDisplayContext.getDefaultDropdownItems() %>"
    />

![Figure 41: A bar chart models the data in bars](../../images/clay-taglib-dropdown-actions.png)

Actions menu can also have help text for the user: 

    <clay:dropdown-actions
    	buttonLabel="More"
    	buttonStyle="secondary"
    	caption="Showing 4 of 32 Options"
    	helpText="You can customize this menu or see all you have by pressing \"more\"."
    	items="<%= dropdownsDisplayContext.getDefaultDropdownItems() %>"
    />

![Figure 42: A bar chart models the data in bars](../../images/clay-taglib-dropdown-actions-help.png)

## Form Elements [](id=form-elements)

The Liferay Clay tag library provides several tags for creating form elements.

### Checkbox [](id=checkbox)
			
    <clay:checkbox checked="<%= true %>" hideLabel="<%= true %>" label="My Input" name="name" />

attributes:
 
- checked: Whether the checkbox is checked.
- disabled: Whether the checkbox is enabled.
- hideLabel: Whether to display the checkbox label.
- indeterminate: Checkbox variable for multiple selection.
- label: The checkbox's label.
- name: The checkbox's name.

![Figure 43: A bar chart models the data in bars](../../images/clay-taglib-form-checkbox.png)

### Radio [](id=radio)

    <clay:radio checked="<%= true %>" hideLabel="<%= true %>" label="My Input" name="name" /></td>

attributes:
- checked: Whether the radio button is checked.
- hideLabel: Whether to display the radio button label.
- disabled: Whether the radio button is enabled.
- label: The radio button's label.
- name: The radio button's name.

![Figure 44: A bar chart models the data in bars](../../images/clay-taglib-form-radio-button.png)

### Selector [](id=selector)

The Java scriplet below creates eight dummy options for the selector: 

    <%
    List<Map<String, Object>> options = new ArrayList<>();

    for (int i = 0; i < 8; i++) {
    	Map<String, Object> option = new HashMap<>();

    	option.put("label", "Sample " + i);
    	option.put("value", i);

    	options.add(option);
    }
    %>

    <clay:select label="Regular Select Element" name="name" options="<%= options %>" />

![Figure 45: A bar chart models the data in bars](../../images/clay-taglib-form-selector.png)

If you want let users select multiple options at once, set the `multiple` 
attribute to `true`:

    <clay:select label="Multiple Select Element" multiple="<%= true %>" name="name" options="<%= options %>" />

![Figure 46: A bar chart models the data in bars](../../images/clay-taglib-form-selector-multiple.png)

attributes:
- disabled: Whether the selector is enabled.
- label: The selector's label.
- multiple: Whether multiple options can be selected
- name: The selector's name.

### Icons [](id=icons)

The Liferay Clay taglibs provide several icons that you can use in your apps. To 
include an icon in your app, use the `clay:icon` tag and specify the icon with 
the `symbol` attribute:

    <clay:icon symbol="folder" />
    
![Figure 47: A bar chart models the data in bars](../../images/clay-taglib-icon-folder.png)

The full list of icons is shown below:

![Figure 48: A bar chart models the data in bars](../../images/clay-taglib-icon-library.png)

The Liferay Clay taglibs also provide a set of language flag icons that you can 
use in your app. The full list of language flags is shown below:

![Figure 49: You can include language flags in your apps.](../../images/clay-taglib-icon-language-flags.png)

## Labels [](id=labels)

The Liferay Clay taglibs provide a few different labels for your app. Use the 
`clay:label` tag to add a label to your app. You can create color-coded labels, 
removable labels, and labels that contain links. This section demonstrates all 
of these options.

### Color-coded Labels [](id=color-coded-labels)

The Liferay Clay labels come in four different colors: dark-blue for info, 
light-grey for status, orange for pending, red for rejected, and green for 
approved.

Info labels are dark-blue, and since they stand out a bit more than status 
labels, they are best for conveying basic information. To use an info label, set 
the `style` attribute to `info`:

<!-- clarify if labels can be small too? I know they can be default and lg. -->

    <div><clay:label label="Label text" style="info" /></div>

![Figure 50: You can include language flags in your apps.](../../images/clay-taglib-label-info.png)

Status labels are light-grey, and due to their neutral color, they are best 
for conveying basic information. To use a status label, set the `style` 
attribute to `status`:

		<div><clay:label label="Status" /></div>

![Figure 51: You can include language flags in your apps.](../../images/clay-taglib-label-status.png)

Warning labels are orange, and due to their pop of color, they are best 
for conveying a warning message. To use a warning label, set the `style` 
attribute to `warning`:

		<div><clay:label label="Pending" style="warning" /></div>

![Figure 52: You can include language flags in your apps.](../../images/clay-taglib-label-warning.png)

Danger labels are red and indicate that something is wrong or has failed. To use 
a danger label, set the `style` attribute to `danger`:

		<div><clay:label label="Rejected" style="danger" /></div>

![Figure 53: You can include language flags in your apps.](../../images/clay-taglib-label-danger.png)

Success labels are green and indicate that something has completed successfully. 
To use a success label, set the `style` attribute to `success`:

		<div><clay:label label="Approved" style="success" /></div>

![Figure 54: You can include language flags in your apps.](../../images/clay-taglib-label-success.png)

Labels can also be bigger if you require it. Set the `size` attribute to `lg` to 
display large labels:
    
    <div><clay:label label="Approved" size="lg" style="success" /></div>

### Removable Labels [](id=removable-labels)

If you want to let a user close a label (e.g. a temporary notification), you can 
make the label removable by setting the `closeable` attribute to `true`. This 
includes an *X* in the label so that the user can close it:

		<clay:label closeable="<%= true %>" label="Normal Label" />

![Figure 55: You can include language flags in your apps.](../../images/clay-taglib-label-removable.png)

### Labels with Links [](id=labels-with-links)

You can make a label a link by adding the `href` attribute to it just as you 
would an anchor tag:

    <clay:label href="#" label="Label Text" />
    
![Figure 56: You can include language flags in your apps.](../../images/clay-taglib-label-link.png)
    
## Links [](id=links)

You can add traditional hyperlinks to your app with the `<clay:link>` tag:

    <clay:link href="#" label="link text" />
    
![Figure 57: You can include language flags in your apps.](../../images/clay-taglib-link.png)

## Navigation Bars [](id=navigation-bars)

Similar to dropdown menus, navigation bars display a list of navigation items. 
The key difference is navigation bars are displayed in a horizontal bar with all 
navigation items visible at all times. The navigation bar also indicates the 
active navigation item with an underline. Navigation bars come in two styles: 
white background with dark-grey text (default) and dark-grey background with 
white text (inverted).

Default navigation bar:

    <clay:navigation-bar items="<%= navigationBarsDisplayContext.getNavigationItems() %>" />

![Figure 58: You can include language flags in your apps.](../../images/clay-taglib-nav-bars.png)

Inverted navigation bar (set `inverted` attribute to `true`):

    <clay:navigation-bar inverted="<%= true %>" items="<%= navigationBarsDisplayContext.getNavigationItems() %>" />

![Figure 59: You can include language flags in your apps.](../../images/clay-taglib-nav-bars-inverted.png)

## Progress Bars [](id=progress-bars)

You can add progress bars to your app with the `clay:progressbar` tag. These 
indicate the completion percentage of a task and come in three status styles: 
default (blue), warning (red), and complete (green with checkmark). You can 
provide a minimum value (`minValue`) and a maximum value (`maxValue`).

Default progress bar:

    <clay:progressbar maxValue="<%= 100 %>" minValue="<%= 0 %>" value="<%= 30 %>" />

![Figure 60: You can include language flags in your apps.](../../images/clay-taglib-progress-bar.png)

Warning progress bar:

    <clay:progressbar maxValue="<%= 100 %>" minValue="<%= 0 %>" status="warning" value="<%= 70 %>" />

![Figure 61: You can include language flags in your apps.](../../images/clay-taglib-progress-bar-warning.png)

Complete progress bar:

    <clay:progressbar status="complete" />
    
![Figure 62: You can include language flags in your apps.](../../images/clay-taglib-progress-bar-complete.png)


## Stickers [](id=stickers)

Whereas badges are for displaying numbers and labels are for short information, 
stickers are small visual indicators of the content 
(usually the content type). They  can include a small label or a Liferay icon, 
and they come in two shapes: circle and square. 

Square sticker with label:

    <clay:sticker label="JPG" />

![Figure 63: You can include language flags in your apps.](../../images/clay-taglib-sticker-square-label.png)

Square sticker with icon:

    <clay:sticker icon="picture" />

![Figure 64: You can include language flags in your apps.](../../images/clay-taglib-sticker-square-icon.png)

Circle sticker:

    <clay:sticker label="JPG" shape="circle" />

![Figure 65: You can include language flags in your apps.](../../images/clay-taglib-sticker-round.png)

Stickers can be positioned in any corner of a div. Indicate the position with 
the `position` attribute (`top-left`, `bottom-left`, `top-right`, or 
`bottom-right`):

    <div class="aspect-ratio">
    	<img class="aspect-ratio-item-fluid" src="https://claycss.com/images/thumbnail_hot_air_ballon.jpg" />

    	<clay:sticker label="PDF" position="top-left" style="danger" />
    </div>

![Figure 66: You can include language flags in your apps.](../../images/clay-taglib-sticker-position.png)

Now you know how to use the Liferay Clay taglibs in your app to build powerful 
UIs!

## Related Topics [](id=related-topics)


# Look and Feel Configuration [](id=look-and-feel-configuration)

To access the look and feel configuration menu of any widget,

1.  Click *Options* (![Options](../../../images/icon-options.png)) in the top
    right corner of the widget.

2.  Select *Look and Feel Configuration*.

*Look and Feel Configuration* has six tabs: 

- General
- Text Styles
- Background Styles
- Border Styles
- Margin and Padding
- Advanced Styling

After making customizations, click *Save* and refresh your page to apply your
changes. If you don't like the effect of your changes, some tabs have a *Reset*
button to discard changes.

## General Settings [](id=general-settings)

On the General tab are the following options:

**Use Custom Title** enables changes to your widget's title. The value in the
title box is displayed on widget's decorator. The title is localizable, so you
can provide translations of the title for different languages.

**Link Portlet URLs to Page** configures all URLs inside the widget (including
pagination URLs) to link to the context of the selected page. The current page
is the default. This is an elegant way to show web content articles' full view
from the Asset Publisher. If you use the View in a Specific Portlet feature or
web content articles' Display Page attribute, you must be careful since it
redirects all links and not just links to assets. See the 
[Configuring Display Settings](/discover/portal/-/knowledge_base/7-1/configuring-display-settings)
article for more details.

**Application Decorators** gives you the choice between three decorators: 
*Barebone*, *Borderless*, and *Decorate*. The Decorate application decorator is
the default. Be careful about turning widget borders off; some themes assume
widget borders are turned on and may not display correctly with them turned
off.

![Figure 1: The General tab of the Look and Feel Configuration menu allows you to define a custom widget title, link widget URLs to a specific page, and select the widget contrast option using decorators.](../../../images/look-and-feel-portlet-configuration-menu.png)

## Text Styles [](id=text-styles)

*Text Styles* configures the format of the text that appears in the widget. The
options include

**Font:** Choose various fonts. You can set the text to bold, italics, or both.

**Size:** Set the font size anywhere from 0.1 em to 12 em, with 0.1 em
increments. 1 em is the default.

**Color:** Set to any six digit hex color code. Click on the text box to open
the color palette.

**Alignment:** Set to *Left*, *Center*, *Right*, or *Justified*. 

**Text Decoration:** Set to *Underline*, *Overline*, or *Strikethrough*. The
default text decoration is *None*.

![Figure 2: The Text Styles tab lets you configure the format of the text that appears in the widget.](../../../images/look-and-feel-text-styles.png)

**Word Spacing:** Set from -1 em to 0.95 em, with 0.05 em increments. 0 em is
the default.

**Line Spacing:** Set from 0 em to 12 em, with 0.1 em increments. 0 em is the
default. 

**Letter Spacing:** Set from -10 px to 50 px, with 1 px increments. 0 px is the
default.

## Background Styles [](id=background-styles)

The Background Styles tab specifies the widget's background color. When you
select the text space, you're given a color palette to choose your background
color or you can manually enter any six digit hex color code.

![Figure 3: The Background Styles tab lets you specify the widget's background color.](../../../images/look-and-feel-background-styles.png)

## Border Styles [](id=border-styles)

The Border Styles tab, configures your widget's border width, style, and color.
For each of these attributes, leave the *Same for All* selector enabled to apply
the same settings to top, right, bottom, and left borders.

![Figure 4: The Border Styles tab lets you specify a border width, style, and color for each side of the widget.](../../../images/look-and-feel-border-styles.png)

For border width, you can specify any % value, em value, or px value. For
border style, you can select Dashed, Double, Dotted, Groove, Hidden, Inset,
Outset, Ridge, or Solid. For border color, you can enter any six digit hex
color code, just like for the text color and background color. You can also use
the color palette.

## Margin and Padding [](id=margin-and-padding)

The Margin and Padding tab specifies margin and padding lengths for the edges of
your widget. Just like for border styles, leave the *Same for All* selector
enabled to apply the same settings to each side (top, right, bottom, and left)
of the widget.

![Figure 5: The Margin and Padding tab allows you to specify margin and padding lengths for the sides of your widget.](../../../images/look-and-feel-margin-and-padding.png)

For both padding and margin, you can specify any % value, em value, or px
value.

## Advanced Styling [](id=advanced-styling)

The Advanced Styling tab displays current information about your widget, 
including your widget's Liferay ID and CSS classes.

![Figure 6: The Advanced Styling tab displays your widget's Liferay ID and allows you to enter CSS code to customize the look and feel of your widget.](../../../images/look-and-feel-advanced-styling.png)

You can also enter custom CSS class names for your widget and custom CSS code.
Clicking the *Add a CSS rule for just this portlet* or *Add a CSS rule for all
portlets like this one* links adds the CSS code shells into your custom CSS text
box. If you check the *Update my styles as I type* box, your CSS code is applied
dynamically to your widget so you can see the effects of your edits.

Next, you'll learn about communication between widgets.

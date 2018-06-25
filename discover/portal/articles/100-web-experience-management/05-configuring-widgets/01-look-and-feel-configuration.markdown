# Look and Feel Configuration [](id=look-and-feel-configuration)

To access the look and feel configuration menu of any widget:

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

After making customizations click *Save* and refresh your page to apply your 
changes. If you don't like the effect of your changes, some tabs have a *Reset*
button to discard changes.

## General Settings

On the General tab, you have the following options:

-  **Use Custom Title** enables changes to your widget's title. The value in 
    the title box will be displayed on widget's decorator. The title is
    localizable and you can provide translations of the title for different
    languages.

-  **Link Portlet URLs to Page** configures all URLs inside the widget 
    (including pagination URLs) to link to the context of the selected page. 
    The current page is the default. This is intended as an elegant 
    way to show web content articles' full view from the Asset Publisher. If 
    you use the View in a Specific Portlet feature or web content articles' 
    Display Page attribute, you must be careful since it redirects all links 
    and not just links to assets. See the [Configuring Display Settings](/discover/portal/-/knowledge_base/7-1/configuring-display-settings) article for more details.

-  **Application Decorators** gives you the choice between three decorators: 
    *Barebone*, *Borderless*, and *Decorate*. The Decorate application 
    decorator is applied by default. Be careful about turning widget borders 
    off; some themes assume that widget borders are turned on and may not 
    display correctly with them turned off.

![Figure X: The General tab of the Look and Feel Configuration menu allows you to define a custom widget title, link widget URLs to a specific page, and select the widget contrast option using decorators.](../../../images/look-and-feel-portlet-configuration-menu.png)

## Text Styles

*Text Styles* allows you to configure the format of the text that appears
in the widget. The options include:

-  **Font** allows you to choose from include Arial, Georgia, Times New
    Roman, Tahoma, Trebuchet MS, and Verdana. Arial is the default. You can set 
    the text to bold, italics, or both.

-  **Size** let's you set the font size anywhere from 0.1 em to 12 em, with 0.1 
    em increments. 1 em is the default.

-  **Color** can be set to any six digit hex color code. Click on the text box 
    to open the color palette.

-  **Alignment** can be set to *Left*, *Center*, *Right*, or *Justified*. 

-  **Text Decoration** can be set to *Underline*, *Overline*, or *Strikethrough*. The default text decoration is *None*.

![Figure 3: The Text Styles tab lets you configure the format of the text that appears in the widget.](../../../images/look-and-feel-text-styles.png)

-  **Word Spacing** can be set from -1 em to 0.95 em, with 0.05 em
    increments. 0 em is the default.

-  **Line Spacing** can be set from 0 em to 12 em, with 0.1 em 
    increments. 0 em is the default. 
    
-  **Letter Spacing** can be set from -10 px to 50 px, with 1 px increments. 0 
    px is the default.

## Background Styles

The Background Styles tab allows you to specify the widget's background color.
When you select the text space, you're given a color palette to choose your
background color or you can manually enter any six digit hex color code.

![Figure X: The Background Styles tab lets you specify the widget's background color.](../../../images/look-and-feel-background-styles.png)

## Border Styles

On the Border Styles tab, you can configure your widget's border width, style, 
and color. For each of these attributes, leave the *Same for All* selector 
enabled to apply the same settings to top, right, bottom, and left borders.

![Figure X: The Border Styles tab lets you specify a border width, style, and color for each side of the widget.](../../../images/look-and-feel-border-styles.png)

For border width, you can specify any % value, em value, or px value. For
border style, you can select Dashed, Double, Dotted, Groove, Hidden, Inset,
Outset, Ridge, or Solid. For border color, you can enter any six digit hex
color code, just like for the text color and background color. You can also use
the color palette.

## Margin and Padding

The Margin and Padding tab allows you to specify margin and padding lengths for
the edges of your widget. Just like for border styles, leave the *Same for All*
selector enabled to apply the same settings to each side (top, right, bottom,
and left) of the widget.

![Figure X: The Margin and Padding tab allows you to specify margin and padding lengths for the sides of your widget.](../../../images/look-and-feel-margin-and-padding.png)

For both padding and margin, you can specify any % value, em value, or px
value.

## Advanced Styling

The Advanced Styling tab displays current information about your widget, 
including your widget's Liferay ID and CSS classes.

![Figure X: The Advanced Styling tab displays your widget's Liferay ID and allows you to enter CSS code to customize the look and feel of your widget.](../../../images/look-and-feel-advanced-styling.png)

On this tab, you can also enter custom CSS class names for your widget and 
custom CSS code. Clicking the *Add a CSS rule for just this portlet* or *Add a 
CSS rule for all portlets like this one* links adds the CSS code shells into 
your custom CSS text box. If you check the *Update my styles as I type* box, 
your CSS code will be dynamically applied to your widget so you can see the 
effects of your edits.

Next, you'll learn about exporting and importing widget data.

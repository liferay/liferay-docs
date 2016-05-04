# Look and Feel Configuration [](id=look-and-feel-configuration)

An administrator can access the look and feel configuration menu of any @product@
application by clicking on the *Options* icon
(![Options](../../../images/icon-options.png)) at the top right corner of the
app and selecting *Look and Feel Configuration*. The location of the Options
icon may vary, depending on your theme. Liferay apps' look and feel dialog boxes
contain six tabs:

- General
- Text Styles
- Background Styles
- Border Styles
- Margin and Padding
- Advanced Styling

After making customizations on any tab, remember to click the *Save* button to
apply your changes. To see the effect of your changes, you may have to refresh
the page. If you don't like the effect of your changes, click the *Reset*
button to discard them.

On the General tab, you can enable the *Use Custom Title* selector to
rename your app's title. The value you enter in the title box will
be displayed at the top of the app's window on the page. You can also select
a language from the app title drop-down menu. If you've provided a language
key translation for the language you select, then your app's title will be
displayed in the selected language.

![Figure 1: To illustrate using a custom title, the Asset Publisher's default title has been replaced with a more appropriate heading.](../../../images/look-and-feel-portlet-configuration.png)

If you select a page in the *Link Portlet URLs to Page* drop-down menu, all
app URLs will point to the page you selected. The current page is the
default. Note that you can use the Asset Publisher's View in a Specific Portlet
feature and web content articles' Display Page attribute to achieve a more
elegant solution for displaying the full view of web content articles on
specific pages. Please see the
[Configuring Display Settings](/discover/portal/-/knowledge_base/7-0/publishing-assets#configuring-display-settings)
article for details.

<!--
Check that below note is valid for 7.0. This functionality is still available.
-Cody

+$$$

**Note:** The *Link Portlet URLs to Page* menu is deprecated for Liferay 6.2 and
will be removed in Liferay 7.0. The functionality of this menu can also be found
in the Asset Publisher. 

$$$
-->

You can also choose whether or not to apply application decorators to your app.
Decorators can be used to add contrast between your app and the page. There are
three decorators available: *Barebone*, *Borderless*, and *Decorate*. The
Decorate application decorator is applied by default. Be careful about turning
app borders off; some themes assume that app borders are turned on and may not
display correctly with them turned off.

![Figure 2: The General tab of the Look and Feel Configuration menu allows you to define a custom app title, link app URLs to a specific page, and select the app contrast option using decorators.](../../../images/look-and-feel-portlet-configuration-menu.png)

The Text Styles tab allows you to configure the format of the text that appears
in the app. The fonts you can choose from include Arial, Georgia, Times New
Roman, Tahoma, Trebuchet MS, and Verdana. Arial is the default. You can set the
text to bold, italics, or both. You can set the font size anywhere from 0.1 em
to 12 em, with 0.1 em increments. 1 em is the default. You can set the text
color to any six digit hex color code. If you'd like help choosing a color,
click on the text box to open the color palette. You can set the text alignment
to Left, Center, Right, or Justified. (Justified text is both left and right
aligned.) You can set an Underline, Overline, or Strikethrough as the text
decoration. The default text decoration is None.

![Figure 3: The Text Styles tab lets you configure the format of the text that appears in the app.](../../../images/look-and-feel-text-styles.png)

You can set the word spacing anywhere from -1 em to 0.95 em, with 0.05 em
increments. 0 em is the default. You can set the line height anywhere from 0 em
to 12 em, with 0.1 em increments. 0 em is the default. Finally, you can set the
letter spacing anywhere from -10 px to 50 px, with 1 px increments. 0 px is the
default.

The Background Styles tab allows you to specify the app's background color.
When you select the text space, you're given a color palette to choose your
background color or you can manually enter any six digit hex color code.

![Figure 4: The Background Styles tab lets you specify the app's background color.](../../../images/look-and-feel-background-styles.png)

On the Border Styles tab, you can configure your app's border width, style, and
color. For each of these attributes, leave the *Same for All* selector enabled
to apply the same settings to top, right, bottom, and left borders.

![Figure 5: The Border Styles tab lets you specify a border width, style, and color for each side of the app.](../../../images/look-and-feel-border-styles.png)

For border width, you can specify any % value, em value, or px value. For
border style, you can select Dashed, Double, Dotted, Groove, Hidden, Inset,
Outset, Ridge, or Solid. For border color, you can enter any six digit hex
color code, just like for the text color and background color. You can also use
the color palette.

The Margin and Padding tab allows you to specify margin and padding lengths for
the edges of your app. Just like for border styles, leave the *Same for All*
selector enabled to apply the same settings to each side (top, right, bottom,
and left) of the app.

![Figure 6: The Margin and Padding tab allows you to specify margin and padding lengths for the sides of your app.](../../../images/look-and-feel-margin-and-padding.png)

For both padding and margin, you can specify any % value, em value, or px
value.

The Advanced Styling tab displays current information about your app, including
your app's Liferay ID and CSS classes.

![Figure 7: The Advanced Styling tab displays your app's Liferay ID and allows you to enter CSS code to customize the look and feel of your app.](../../../images/look-and-feel-advanced-styling.png)

On this tab, you can also enter custom CSS class names for your app and custom
CSS code. Clicking the *Add a CSS rule for just this portlet* or *Add a CSS rule
for all portlets like this one* links adds the CSS code shells into your custom
CSS text box. If you check the *Update my styles as I type* box, your CSS code
will be dynamically applied to your app so you can see the effects of your
edits.

Next, you'll learn about exporting and importing app data.

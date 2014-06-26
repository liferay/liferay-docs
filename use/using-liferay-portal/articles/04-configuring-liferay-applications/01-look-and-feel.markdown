# Look and Feel [](id=look-and-feel-lp-6-2-use-useportal)

An administrator can access the look and feel configuration menu of any Liferay
portlet by clicking on the gear icon at the top right corner of the portlet and
selecting *Look and Feel*. The location of the gear icon may vary, depending on
your theme. Liferay portlets' look and feel dialog boxes contain six tabs:

- Portlet Configuration
- Text Styles
- Background Styles
- Border Styles
- Margin and Padding
- Advanced Styling

After making customizations on any tab, remember to click the *Save* button to
apply your changes. To see the effect of your changes, you may have to refresh
the page. If you don't like the effect of your changes, click the *Reset*
button to discard them.

On the Portlet Configuration tab, you can check the *Use Custom Title* box to
rename your portlet's title. The value you enter in the Portlet Title box will
be displayed at the top of the portlet window on the page. You can also select
a language from the Portlet Title drop-down menu. If you've provided a language
key translation for the language you select, the your portlet's title will be
displayed in the selected language.

![Figure 4.1: The Portlet Configuration tab of the Look and Feel Box allows you to define a custom portlet title, link portlet URLs to a specific page, and select whether or not portlet borders should be displayed.](../../images/look-and-feel-portlet-configuration.png)

If you select a page in the *Link Portlet URLs to Page* drop-down menu, all
portlet URLs will point to the page you selected. The current page is the
default. Note that you can use the Asset Publisher's View in a Specific Portlet
feature and web content articles' Display Page attribute to achieve a more
elegant solution for displaying the full view of web content articles on
specific pages. Please see the Display Page section of chapter 5 for details.

You can also choose whether or not to display borders around your portlet. By
default, borders are displayed. Be careful about turning portlet borders off;
some themes assume that portlet borders are turned on and may not display
correctly with them turned off.

The Text Styles tab allows you to configure format of the text that appears in
the portlet. The fonts you can choose from include Arial, Georgia, Times New
Roman, Tahoma, Trebuchet MS, and Verdana. Arial is the default. You can set the
text to bold, italics, or both. You can set the font size anywhere from 0.1 em
to 12 em, with 0.1 em increments. 1 em is the default. You can set the text
color to any six digit hex color code. If you'd like help choosing a color,
click on the text box to open the color palette. You can set the text
alignment to left, center, right, or justified. (Justified text is both left
and right aligned.) You can set an underline, overline, or strikethrough as the
text decoration. The default text decoration is none.

![Figure 4.2: The Text Styles tab lets you configure the format of the text that appears in the portlet.](../../images/look-and-feel-text-styles.png)

You can set the word spacing anywhere from -1 em to 0.95 em, with 0.5 em
increments. 0 em is the default. You can set the line height anywhere from 0 em
to 12 em, with 0.1 em increments. 0 em is the default. Finally, you can set the
letter spacing anywhere from -10 px to 50 px, with 1 px increments. 0 px is the
default.

The Background Styles tab allows you to specify the portlet's background color.
When you select the text space, you're given a color palette to choose your
background color or you can manually enter any six digit hex color code.

![Figure 4.3: The Background Styles tab lets you specify the portlet's background color.](../../images/look-and-feel-background-styles.png)

On the Border Styles tab, you can configure your portlet's border width, border
style, and border color. For each of these attributes, leave the *Same for All*
box checked to apply the same settings to top, right, bottom, and left borders. 

![Figure 4.4: The Border Styles tab lets you specify a border width, style, and color for each side of the portlet.](../../images/look-and-feel-border-styles.png)

For border width, you can specify any % value, em value, or px value. For
border style, you can select dashed, double, dotted, groove, hidden, inset,
outset, ridge, or solid. For border color, you can enter any six digit hex
color code, just like for the text color and background color. You can also use
the color palette.

The Margin and Padding tab allows you to specify margin and padding lengths for
the edges of your portlet. Just like for border styles, leave the *Same for
All* box checked to apply the same settings to each side (top, right, bottom,
and left) of the portlet.

![Figure 4.5: The Margin and Padding tab allows you to specify margin and padding lengths for the sides of your portlet.](../../images/look-and-feel-margin-and-padding.png)

For both padding and margin, you can specify any % value, em value, or px
value.

The Advanced Styling tab displays current information about your portlet,
including your portlet's Liferay ID and CSS classes.

![Figure 4.6: The Advanced Styling tab displays your portlet's Liferay ID and allows you to enter CSS code to customize the look and feel of your portlet.](../../images/look-and-feel-advanced-styling.png)

On this tab, you can also enter custom CSS class names for your portlet and
custom CSS code. Clicking the *Add a CSS rule for just this portlet* or *Add a
CSS rule for all portlets like this one* links adds the CSS code shells into
your custom CSS text box. If you check the *Update my styles as I type* box,
your CSS code will be dynamically applied to your portlet so you can see the
effects of your edits.

For Liferay 6.2, the WAP Styling functionality has been deprecated. Liferay
Portal uses a new responsive design making WAP Styling configuration unnecessary
for your portlet's look and feel. You can enable the WAP functionality for your
portal's Look and Feel section by opening/creating your `portal-ext.properties`
file in your `${LIFERAY_HOME}` directory and setting
`mobile.device.styling.wap.enabled=true`. WAP functionality will be completely
removed from Liferay in the next release.

Next, let's discuss exporting and importing portlet data.

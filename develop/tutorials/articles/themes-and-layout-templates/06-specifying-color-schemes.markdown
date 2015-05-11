# Specifying Color Schemes [](id=specifying-color-schemes)

You can provide various "flavors" of your theme by creating color schemes for
it. Color schemes let you keep the styles and overall design for your theme,
while at the same time giving a new look for your users to enjoy. You specify
color schemes with a CSS class name, which of course also lets you choose
different background images, different border colors, and more.
 
![Figure 1: Liferay's Classic theme offers three color schemes. You too can offer eye-pleasing color schemes for your themes.](../../images/classic-theme-color-schemes.png)

The example below shows defines a *Day* color scheme and a *Night* color scheme.
Here's the code as specified in the plugin's `liferay-look-and-feel.xml` file:

    <theme id="deep-blue" name="Deep Blue">
        <color-scheme id="01" name="Day">
            <css-class>day</css-class>
            <color-scheme-images-path>
                ${images-path}/color_schemes/${css-class}
            </color-scheme-images-path>
        </color-scheme>
        <color-scheme id="02" name="Night">
            <css-class>night</css-class>
        </color-scheme>
    </theme>

In your theme's `_diffs/css` folder, you can create a `color_schemes` folder and
place a `.css` file in it for each color scheme your theme supports. If you
don't specify a `.css` file for a color scheme, the theme's default color scheme
is used. 

To make the color schemes of the `.css` files available, import them into your
`docroot/css/custom.css` file. The example's `day.css` and `night.css` files
could be specified in the `custom.css` file using these statements:

    @import url(color_schemes/day.css);
    @import url(color_schemes/night.css);

The color scheme CSS class is placed on the page's `<body>` element, so you can
use it to identify your styling. It's conventional to prefix all your CSS
styles. For example, the developer would prefix all the styles with the word
*day* in a color scheme CSS file named `day.css`: 

    body.day { background-color: #ddf; }
    .day a { color: #66a; }

After specifying your CSS files, you can create separate thumbnail images for
your color schemes. 

In the `liferay-look-and-feel.xml` file, the `<color-scheme-images-path>`
element tells Liferay where to look for a theme's thumbnail images (you only
have to place this element in one color scheme for it to affect both). For the
example above you could use the folders `_diffs/images/color_schemes/day` and
`_diffs/images/color_schemes/night`. In each of your color scheme's folders,
place a `thumbnail.png` and `screenshot.png` file. Make sure your thumbnail
images follow the specifications defined in the
[Creating a Theme Thumbnail](/develop/tutorials/-/knowledge_base/6-2/creating-a-theme-thumbnail)
tutorial. 

There you have it. Now you can go color scheme crazy with your themes!

**Related Topics**

[Designing a Layout Template](/develop/tutorials/-/knowledge_base/6-2/designing-a-layout-template)

[Creating a Theme Thumbnail](/develop/tutorials/-/knowledge_base/6-2/creating-a-theme-thumbnail)

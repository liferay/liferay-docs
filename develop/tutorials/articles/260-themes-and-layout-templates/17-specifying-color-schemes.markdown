# Specifying Color Schemes in your Theme [](id=specifying-color-schemes)

You can provide various "flavors" of your theme by creating color schemes. Color
schemes let you keep the styles and overall design for your theme, while giving
a new look for your users to enjoy. You specify color schemes with a CSS class
name, which also lets you choose different background images, different border
colors, and more.
 
![Figure 1: You can offer eye-pleasing color schemes for your themes.](../../images/theme-color-schemes.png)

Follow these steps to create color schemes for your theme:

1.  Create a folder to hold color schemes (`color_schemes` for example) in the 
    theme's `css` folder.
 
2.  Create an `.scss` file in the color schemes folder for each color scheme 
    your theme supports. If you don't specify a `.scss` file for a color scheme, 
    the theme's default color scheme is used.

3.  Prefix all CSS styles with the name of your color scheme. The color scheme
    CSS class is placed on the page's `<body>` element, so you can use it to
    identify your styling. For example, you'd prefix all the styles with the
    word *day* in a color scheme CSS file named `_day.scss`: 

        body.day { background-color: #DDF; }
        .day a { color: #66A; }

    +$$$
    
    **Note:** The default color scheme does not require a prefix, as it uses 
    the theme's `_custom.scss` for styling.
    
    $$$

4.  Import the color scheme `.scss` files into the `_custom.scss` file. The 
    example below imports `_day.scss` and `_night.scss` files:

        @import "color_schemes/day";
        @import "color_schemes/night";

5.  Open the theme's `liferay-look-and-feel.xml` file and add the default color 
    scheme for the theme. Pass the default color scheme's CSS class name 
    (the name of the CSS file) in the `<css-class>` element. If the default color 
    scheme styling is in the theme's `_custom.scss` file, use `default` 
    for the `<css-class>`:

        <theme id="my-theme-id" name="My Theme Name">
           <color-scheme id="01" name="My Default Color Scheme Name">
               <default-cs>true</default-cs>
               <css-class>default</css-class>
               
               <color-scheme-images-path>
                   ${images-path}/my_color_schemes_folder_name/${css-class}
               </color-scheme-images-path>
           </color-scheme
           ...
        </theme>

    A default color scheme lets users return to the theme's default look and
    feel. Note that the color scheme's `name` is arbitrary. Only the color
    scheme's `css-class` element must match the name of the color scheme's CSS
    class.
 
    Note that color schemes are sorted alphabetically by `name` rather than `id`. 
    For example, a color scheme named `Day` and `id` `02` would be selected by 
    default over a color scheme named `Clouds` with `id` `01`. The `<default-cs>` 
    element overrides the alphabetical sorting and sets the color scheme that is 
    selected by default, when the theme is chosen. Adding this element to the 
    default color scheme ensures that it is selected when the theme is chosen.
 
    The `<color-scheme-images-path>` element specifies theme thumbnail image
    location. Place this element in the first color scheme to affect them all.
    For example you could use the folders `/images/color_schemes/default`,
    `/images/color_schemes/day`, and `/images/color_schemes/night`. 
 
6.  Add the remaining color schemes to the `liferay-look-and-feel.xml` using 
    the pattern below:
 
        <color-scheme id="02" name="my-color-scheme-name">
           <css-class>my-color-scheme-css-class-name</css-class>
        </color-scheme>

    The example below defines a *Day* color scheme and a *Night* color scheme 
    for a theme named *Big Green*. Here's the code as specified in the
    `liferay-look-and-feel.xml` file:

        <theme id="big-green" name="Big Green">
            <color-scheme id="01" name="Default">
                <default-cs>true</default-cs>
                <css-class>default</css-class>
                <color-scheme-images-path>
                    ${images-path}/color_schemes/${css-class}
                </color-scheme-images-path>
            </color-scheme>
            <color-scheme id="02" name="Day">
                <css-class>day</css-class>
            </color-scheme>
            <color-scheme id="03" name="Night">
                <css-class>night</css-class>
            </color-scheme>
        </theme>

7.  Place a `thumbnail.png` and `screenshot.png` file in each of the color 
    scheme's folders. Make sure thumbnail images follow the specifications 
    defined in the [Creating a Theme Thumbnail](/develop/tutorials/-/knowledge_base/7-0/creating-a-theme-thumbnail) 
    tutorial.

There you have it. Now you can go color scheme crazy with your themes!

## Related Topics [](id=related-topics)

[Layout Templates with the Theme Generator](/develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-with-the-themes-generator-0)

[Creating a Theme Thumbnail](/develop/tutorials/-/knowledge_base/7-0/creating-a-theme-thumbnail)

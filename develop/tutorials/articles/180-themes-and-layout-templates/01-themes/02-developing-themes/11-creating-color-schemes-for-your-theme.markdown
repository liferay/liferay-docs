# Creating Color Schemes for Your Theme [](id=creating-color-schemes-for-your-theme)

Color schemes give your theme additional color palettes. They only require
a small amount of changes to your theme's CSS. This is an easy way to subtly
change the look of your theme, while maintaining the same design and feel to it.

Follow these steps to create color schemes for your theme:

1.  Create a folder to hold color schemes (`color_schemes` for example) in the 
    theme's `css` folder and add a `.scss` file to it for each color scheme your 
    theme supports. 
 
2.  Choose a CSS class to identify each color scheme, and specify this class in 
    the color scheme's styles. The color scheme's name is a good choice. This 
    class is added to the site's `<body>` element when the color scheme is 
    applied, so you *must* specify this class in the color schemes styles for 
    them to work. For example, you could specify `.day` for a color scheme CSS 
    file named `_day.scss`: 

        body.day { background-color: #DDF; }
        .day a { color: #66A; }

    +$$$
 
    **Note:** The default color scheme uses the theme's `_custom.scss` for
    styling, so you don't need to specify its name in its styles.
 
    $$$

3.  Import the color scheme `.scss` files into the theme's `_custom.scss` file. 
    The example below imports `_day.scss` and `_night.scss` files:

        @import "color_schemes/day";
        @import "color_schemes/night";

4.  Create a folder for each color scheme in your theme's `images` folder, and 
    add 
    [a thumbnail preview](/develop/tutorials/-/knowledge_base/7-1/creating-a-thumbnail-preview-for-your-theme) 
    for each color scheme. The folder name *must match* the color scheme's CSS 
    class name you specified in step 2.

5.  Open the theme's `liferay-look-and-feel.xml` file and follow the pattern 
    below to add the default color scheme. A default color scheme is required so 
    users can return to the theme's default look and feel after choosing a 
    different color scheme. Note that the color scheme's `name` value is 
    arbitrary, but the `<css-class>` element's value *must match* the CSS 
    class you specified in step 2. If the default color scheme styling is in the 
    theme's `_custom.scss` file, use `default` for the `<css-class>`. Also note 
    the inclusion of the `<color-scheme-images-path>` element. This specifies 
    the theme thumbnail image location. Place this element in the first color 
    scheme to configure the images path for all the color schemes:

        <theme id="my-theme-id" name="My Theme Name">
           <color-scheme id="01" name="My Default Color Scheme Name">
               <default-cs>true</default-cs>
               <css-class>default</css-class>
               
               <color-scheme-images-path>
                   ${images-path}/my_color_schemes_folder_name/${css-class}
               </color-scheme-images-path>
           </color-scheme>
           ...
        </theme>
 
    +$$$
 
    **Note:** Color schemes are sorted alphabetically by `name` rather than `id`. 
    For example, a color scheme named `Day` and `id` `02` would be selected by 
    default over a color scheme named `Clouds` with `id` `01`. The `<default-cs>` 
    element overrides the alphabetical sorting and sets the color scheme that is 
    selected by default when the theme is chosen. 
 
    $$$ 
 
6.  Add the remaining color schemes below the default color scheme, using the 
    pattern below:
 
        <color-scheme id="id-number" name="Color Scheme Name">
           <css-class>color-scheme-css-class</css-class>
        </color-scheme>

An example `liferay-look-and-feel.xml` file is shown below:

    <look-and-feel>
    	<compatibility>
    		<version>7.1.0+</version>
    	</compatibility>
    	<theme id="my-liferay-theme" name="My Liferay Theme">
    		<template-extension>ftl</template-extension>
    		<color-scheme id="01" name="Default">
    			<default-cs>true</default-cs>
    			<css-class>default</css-class>
    			<color-scheme-images-path>
    				${images-path}/color_schemes/${css-class}
    			</color-scheme-images-path>
    		</color-scheme>
    		<color-scheme id="02" name="Red">
    			<css-class>red</css-class>
    		</color-scheme>
    		<color-scheme id="03" name="Green">
    			<css-class>green</css-class>
    		</color-scheme>
    	</theme>
    </look-and-feel>

![Figure 1: Color schemes give administrators some choices for your theme's look.](../../../../images/theme-dev-color-schemes.png)

There you have it. Now you can go color scheme crazy with your themes!

## Related Topics [](id=related-topics)

[Creating Layout Templates](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator)

[Creating a Thumbnail Preview for Your Theme](/develop/tutorials/-/knowledge_base/7-1/creating-a-thumbnail-preview-for-your-theme)

---
header-id: creating-color-schemes-for-your-theme
---

# Creating Color Schemes for Your Theme

[TOC levels=1-4]

Color schemes give your theme additional color palettes. With just a small 
amount of changes to your theme's CSS, you can subtly change the look of your 
theme, while maintaining the same design and feel to it. 

![Figure 1: Color schemes give administrators some choices for your theme's look.](../../../../images/theme-dev-color-schemes.png)

Follow these steps to create color schemes for your theme:

1.  Open the theme's `WEB-INF/liferay-look-and-feel.xml` file and follow the 
    pattern below to add the default color scheme. If your default styles are in 
    `_custom.scss`, use the `default` `<css-class>` as shown in the example 
    below. See the 
    [liferay-look-and-feel DTD](@platform-ref@/7.2-latest/definitions/liferay-look-and-feel_7_2_0.dtd.html#color-scheme)
    for an explanation of each of the elements used below:

    ```xml
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
     ```

    | **Note:** Color schemes are sorted alphabetically by `name` rather than 
    | `id`. For example, a color scheme named `Clouds` and `id` `02` would be 
    | selected by default over a color scheme named `Day` with `id` `01`. The 
    | `<default-cs>` element overrides the alphabetical sorting and sets the 
    | color scheme that is selected by default when the theme is chosen.  
 
2.  Add the remaining color schemes below the default color scheme, using the 
    pattern below. Note that the IDs, names, and CSS classes must be unique for 
    each color scheme.
 
    ```xml
    <color-scheme id="id-number" name="Color Scheme Name">
       <css-class>color-scheme-css-class</css-class>
    </color-scheme>
    ```
    An example `liferay-look-and-feel.xml` configuration is shown below:

    ```xml
    <look-and-feel>
    	<compatibility>
    		<version>7.2.0+</version>
    	</compatibility>
    	<theme id="my-great-theme" name="My Great Theme">
    		<template-extension>ftl</template-extension>
    		<color-scheme id="01" name="Default">
    				<default-cs>true</default-cs>
    				<css-class>default</css-class>
    				<color-scheme-images-path>
    						${images-path}/color_schemes/${css-class}
    				</color-scheme-images-path>
    		</color-scheme>
    		<color-scheme id="02" name="Dark">
    			<css-class>dark</css-class>
    		</color-scheme>
    		<color-scheme id="03" name="Light">
    			<css-class>light</css-class>
    		</color-scheme>
    		<portlet-decorator ...>
    			...
    	</theme>
    </look-and-feel>
    ```

3.  Create a folder for your color schemes (`color_schemes` for example) in the 
    theme's `css` folder, and add a `.scss` file to it for each color scheme 
    your theme supports, excluding the default color scheme since those styles 
    are included in `_custom.scss`. 
 
4.  The color scheme class is added to the theme's `<body>` element when the 
    color scheme is applied, so add the class to the color scheme's styles to 
    target the proper color scheme. The example below specifies styles for a 
    color scheme with the class `day`: 

    ```css
    body.day { background-color: #DDF; }
    .day a { color: #66A; }
    ```

5.  Import the color scheme `.scss` files into the theme's `_custom.scss` file. 
    The example below imports `_day.scss` and `_night.scss` files:

    ```css
    @import "color_schemes/day";
    @import "color_schemes/night";
    ```

6.  Create a folder for each color scheme in your theme's `images` folder, and 
    add 
    [a thumbnail preview](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-a-thumbnail-preview-for-your-theme) 
    for them. The folder name *must match* the color scheme's CSS class name.

There you have it. Now you can go color scheme crazy with your themes!

## Related Topics

- [Generating Layout Templates](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-layout-templates-with-the-themes-generator)
- [Creating a Thumbnail Preview for Your Theme](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-a-thumbnail-preview-for-your-theme)

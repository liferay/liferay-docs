# Color Schemes [](id=color-schemes)

Specify color schemes with a CSS class name, which of course also lets you
choose different background images, different border colors, and more. 

Here's how you can define your color schemes in `liferay-look-and-feel.xml`:

    <theme id="deep-blue" name="Deep Blue">
        <settings>
            <setting key="my-setting" value="my-value" />
        </settings>
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

In your `_diffs/css` folder, create a `color_schemes` folder and place a `.css`
file in it for each color scheme. In the case above, we can have either one file
called `night.css`, letting the default styling handle the first color scheme,
or we can use both `day.css` and `night.css` to specify each scheme. Let's use
the latter option here, creating both files to define our color schemes.

Place the following lines at the bottom of your `custom.css` file:

    @import url(color_schemes/day.css);
    @import url(color_schemes/night.css);

The color scheme CSS class is placed on the `<body>` element, so you can use it
to identify your styling. In `day.css`, prefix all your CSS styles like this:

    body.day { background-color: #ddf; }
    .day a { color: #66a; }

In `night.css`, prefix all your CSS styles like this:

    body.night { background-color: #447; color: #777; }
    .night a { color: #bbd; }

You can create separate thumbnail images for your color schemes. The
`<color-scheme-images-path>` element tells Liferay where to look for these
images (you only have to place this element in one color scheme for it to affect
both). For our example, create the folders `_diffs/images/color_schemes/day` and
`_diffs/images/color_schemes/night`. In each folder place a `thumbnail.png` and
`screenshot.png` file, according to the specifications defined in the Thumbnails
section above. 

Let's review the predefined settings available for your theme. 

# Importing Clay CSS into a Theme [](id=importing-clay-css-into-a-theme)

Clay CSS fills the gaps between Bootstrap and the specific needs of @product@. 
Bootstrap features have been extended to cover more use cases. Here are some of 
the new components added by Clay CSS: 

- Aspect Ratio
- Cards
- Dropdown Wide and Dropdown Full
- Figures
- Nameplates
- Sidebar / Sidenav
- Stickers
- SVG Icons
- Timelines
- Toggles

Several reusable CSS patterns have also been added to help accomplish time 
consuming tasks such as these:

- truncating text
- content filling the remaining container width
- truncating text inside table cells
- table cells filling remaining container width and table cells only being as 
  wide as their content
- open and close icons inside collapsible panels
- nested vertical navigations
- slide out panels
- notification icons/messages
- vertical alignment of content

Next you can learn more about Clay's structure.

## Clay CSS Structure [](id=clay-css-structure)

[Clay CSS](https://claycss.com/) 
is bundled with two sub-themes: 
[Clay Base](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/foundation/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay) 
and 
[Atlas](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/foundation/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay/atlas). 
Clay Base is @product@'s Bootstrap API extension. It adds all the features and 
components you need and inherits Bootstrap's styles. As a result, Clay Base is 
fully compatible with 
[third party themes](/develop/tutorials/-/knowledge_base/7-1/integrating-third-party-themes-with-clay) 
that leverage Bootstrap's Sass variable API.

Atlas is @product@'s custom Bootstrap theme that is used in the Classic Theme. 
Its purpose is to overwrite and manipulate Bootstrap and Clay Base to create its 
classic look and feel. Atlas is equivalent to installing a Bootstrap third party 
theme.

+$$$

**Note:** It is not recommended to integrate third party themes with Atlas, as
it adds variables and styles that are outside the scope of Bootstrap's API.

$$$

You can learn how to customize the Atlas theme next.

## Customizing Atlas in @product@ [](id=customizing-atlas-in-liferay)

If you want to include all the Classic Theme's files, you can skip these steps
and move on to the next section.

Follow these steps to customize the Atlas theme:

1.  In your theme's `/src/css` directory (for legacy ant themes, place in
    `/_diff/css`) add a file named `clay.scss` with the code below and save:

        @import "clay/atlas";

2.  Add a file named `_imports.scss` with the code below and save:

        @import "bourbon";
        @import "mixins";
        @import "clay/atlas-variables";


3.  Add a file named `font_awesome.scss` and add the font-awesome path and 
    import:

        // Icon paths
        
        $FontAwesomePath: "font-awesome/font";
        
        @import "font-awesome/scss/font-awesome"
 
4.  Add a file named `_clay_variables.scss`. All your Atlas, Bootstrap, and Clay 
    Base variable modifications must be placed in this file.

4.  Add a file named `_custom.scss` with the code below and save:

        /* Use these inject tags to dynamically create imports for 
        themelet styles. You can place them where ever you like in this file. */
        
        /* inject:imports */
        /* endinject */
        
        /* This file allows you to override default styles in one central 
        location for easier upgrade and maintenance. */

Place your custom CSS in this file. Next you can learn how to extend Atlas with
the Classic theme.

### Extending Atlas with the Classic Theme [](id=extending-atlas-with-the-classic-theme)

To extend the Atlas theme with the Classic theme, copy all the files located in
these directories into your theme:

    frontend-theme-classic/src/css
    frontend-theme-classic/src/images
    frontend-theme-classic/src/js
    frontend-theme-classic/src/templates

You can also automatically copy these files into your theme using the 
[Liferay Theme Tasks](/develop/reference/-/knowledge_base/7-1/theme-gulp-tasks) 
`gulp kickstart` command and following the prompts.

Next you can learn how to customize the Clay Base.

## Customizing Clay Base [](id=customizing-clay-base-in-liferay)

You can customize Clay Base with just a few imports.

In your custom theme's `/src/css` folder (legacy ant themes: 
`/_diff/css`) add a file named `font_awesome.scss` with the code below and save:

    // Icon paths
    
        $FontAwesomePath: "font-awesome/font";
        
        @import "font-awesome/scss/font-awesome"

Then in that same folder, add a file named `_clay_variables.scss`. All your
Atlas, Bootstrap, and Clay Base variable modifications must be placed in this
file.

As mentioned earlier, any custom CSS should be placed in `_custom.scss`.

Now you know how to use Clay CSS in your theme!

## Related Topics [](id=related-topics)

[Applying Clay Styles to Your App](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)

[Integrating Third Party Themes with Clay](/develop/tutorials/-/knowledge_base/7-1/integrating-third-party-themes-with-clay)

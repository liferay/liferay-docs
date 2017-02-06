# Importing Lexicon CSS into a Theme [](id=importing-lexicon-css-into-a-theme)

Lexicon is an extension of Bootstrap's CSS Framework. Bootstrap is by far the 
most popular CSS framework on the web. Also, it's open source, so anyone can use 
it. Built with Sass, Lexicon fills the front-end gaps between Bootstrap and the 
specific needs of @product@.

This tutorial looks briefly at Lexicon and shows you how to use it in your
@product@ themes.

## Lexicon Features [](id=lexicon-features)

As mentioned before, Lexicon fills the gaps between Bootstrap and the specific 
needs of @product@. Bootstrap features have been extended to cover more 
use cases. Here are some of the new components added by Lexicon:

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

Next you can learn more about Lexicon's structure.

## Lexicon Structure [](id=lexicon-structure)

Lexicon is bundled with two sub-themes: Lexicon Base and Atlas. Lexicon Base is 
@product@'s Bootstrap API extension. It is also the theme that is used in 
@product@'s Styled Theme. It adds all the features and components you need and 
inherits Bootstrap's styles. As a result, Lexicon Base is fully compatible with 
third party themes that leverage Bootstrap's Sass variable API. As a best 
practice, you should use the Lexicon Base as your base theme to integrate third 
party themes into @product@.

Atlas is @product@'s custom Bootstrap theme that is used in the Classic Theme. 
Its purpose is to overwrite and manipulate Bootstrap and Lexicon Base to create
@product@'s classic look and feel. Atlas is equivalent to installing a Bootstrap 
third party theme.

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
    `/_diff/css`) add a file named `aui.scss` with the code below and save:

        @import "aui/lexicon/atlas";

2.  Add a file named `_imports.scss` with the code below and save:

        @import "bourbon";
        @import "mixins";
        @import "aui/lexicon/atlas-variables";
        @import "aui/lexicon/bootstrap/mixins";
        @import "aui/lexicon/lexicon-base/mixins";
        @import "aui/lexicon/atlas-theme/mixins";


3.  Add a file named `_aui_variables.scss` with the code below and save:

        // Icon paths
        
        $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
        $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
        $icon-font-path: "aui/lexicon/fonts/";
 
    All your Atlas, Bootstrap, and Lexicon Base variable modifications must be 
    placed in this file.

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
[Liferay Theme Tasks](https://github.com/liferay/liferay-theme-tasks) module 
[gulp kickstart](https://github.com/liferay/liferay-theme-tasks#kickstart) 
command and following the prompts.

Next you can learn how to customize the Lexicon Base.

## Customizing Lexicon Base in @product@ [](id=customizing-lexicon-base-in-liferay)

You can customize Lexicon Base with just a few imports.

In your custom theme's `/src/css` directory (for legacy ant themes, place in 
`/_diff/css`) add a file named `_aui_variables.scss` with the code below and 
save:

    // Icon paths
    
        $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
        $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
        $icon-font-path: "aui/lexicon/fonts/";
 
All your Atlas, Bootstrap, and Lexicon Base variable modifications must be 
placed in this file.

As mentioned earlier, any custom CSS should be placed in `_custom.scss`.

You can learn how to add third party themes in @product@ next.

## Adding a Third Party Theme [](id=adding-a-third-party-theme)

Third party themes must be built with Sass to be compatible with @product@. 
**Make sure the Sass files are included before making any theme purchase.**

Follow these steps to add a third party theme:

1.  Follow the steps in the [Customizing Lexicon Base in @product@](/develop/tutorials/-/knowledge_base/7-0/importing-lexicon-css-into-a-theme#customizing-lexicon-base-in-liferay) section.

2.  Create a folder inside `/src/css` (for legacy ant themes, `/_diff/css`) 
    that contains your third party theme (e.g. `/src/css/awesome-theme` or
    `/_diff/css/awesome-theme`)

3.  Copy the contents of the theme to the folder you just created.

4.  In `_aui_variables.scss`, import the file containing the theme variables.

    For example, `@import "awesome-theme/variables.scss";`
 
    +$$$ 

    **Note:** You may omit the leading underscore when importing Sass files.

    $$$

5.  In `_custom.scss`, import the file containing the CSS.

    For example, `@import "awesome-theme/main.scss";`

6.  Deploy your theme with `gulp deploy` (for legacy ant themes, use `ant deploy`)

Now you know how to use Lexicon CSS in your theme!

## Related Topics [](id=related-topics)

[Applying Lexicon Styles to Your App](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-styles-to-your-app)

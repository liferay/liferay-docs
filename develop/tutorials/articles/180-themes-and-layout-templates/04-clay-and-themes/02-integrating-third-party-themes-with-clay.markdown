# Integrating Third Party Themes with Clay [](id=integrating-third-party-themes-with-clay)

[Clay Base](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay) 
provides all the features and components your theme needs and inherits 
Bootstrap's styles. As a result, Clay Base is fully compatible with third party 
themes that leverage Bootstrap's Sass variable API. 

The 
[Styled Theme](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled) 
uses Clay Base to provide its styles and components. Therefore, as a best 
practice, you should use the Styled base theme to integrate third party themes. 

+$$$

**Note:** You can purchase third party themes from the 
[Liferay Marketplace](https://web.liferay.com/marketplace). Third party themes 
must be built with Sass to be compatible. **Make sure** Sass files are included 
before making any theme purchase.

$$$

Follow these steps to integrate a third party theme with Clay Base:

1.  Create a new theme with the Styled Theme as its base. This is the default 
    base theme for newly created themes, so no further action is required. This 
    provides the Clay Base files you need. 

2.  In the theme's `/src/css` folder (legacy ant themes: `/_diff/css`) add a 
    file named `font_awesome.scss` with the code below and save:

        // Icon paths

        $FontAwesomePath: "font-awesome/font";

        @import "font-awesome/scss/font-awesome"

3.  In that same folder, add a file named `_clay_variables.scss`. All your
    Atlas, Bootstrap, and Clay Base variable modifications must be placed in 
    this file.

4.  Create a folder inside `/src/css` (for legacy ant themes, `/_diff/css`) that 
    contains your third party theme (e.g. `/src/css/awesome-theme` or
    `/_diff/css/awesome-theme`)

5.  Copy the CSS contents of the theme to the folder you just created.

6.  In `_clay_variables.scss`, import the file containing the theme variables. 
    For example, `@import "awesome-theme/variables.scss";`
 
    +$$$ 

    **Note:** You may omit the leading underscore when importing Sass files.

    $$$

7.  In `_custom.scss`, import the file containing the CSS. For example, 
    `@import "awesome-theme/main.scss";`

8.  Deploy your theme with `gulp deploy` (for legacy ant themes, use 
    `ant deploy`)

Now you know how to integrate third party themes with Clay Base!

## Related Topics [](id=related-topics)

[Applying Clay Styles to Your App](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)

[Importing Clay CSS into a Theme](/develop/tutorials/-/knowledge_base/7-1/importing-clay-css-into-a-theme)

---
header-id: integrating-third-party-themes-with-clay
---

# Integrating Third Party Themes with Clay

[TOC levels=1-4]

[Clay Base](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay) 
provides all the features and components your theme needs and inherits 
Bootstrap's styles. As a result, Clay Base is fully compatible with third party 
themes that leverage Bootstrap's Sass variable API. 

The 
[Styled Theme](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled) 
uses Clay Base to provide its styles and components. Therefore, as a best 
practice, you should use the Styled base theme to integrate third party themes. 

| **Note:** You can purchase third party themes from the
| [Liferay Marketplace](https://web.liferay.com/marketplace). Third party themes
| must be built with Sass to be compatible. **Make sure** Sass files are included
| before making any theme purchase.

Follow these steps to integrate a third party theme with Clay Base:

1.  Create a new theme with the Styled Theme as its base. This is the default 
    base theme for newly created themes, so no further action is required. This 
    provides the Clay Base files you need. 

2.  In the theme's `/src/css/` folder, add a file named `_clay_variables.scss`. 
    Place your Atlas, Bootstrap, and Clay Base variable modifications in this 
    file.

3.  Create a folder inside `/src/css/` to house your third party theme 
    (e.g. `/src/css/my-third-party-theme/`)

4.  Copy the CSS contents of the theme to the folder you just created.

5.  In `_clay_variables.scss`, import the file containing the theme variables. 
    For example, `@import "my-third-party-theme/variables.scss";`
 
    | **Note:** You may omit the leading underscore when importing Sass files.

6.  In `_custom.scss`, import the file containing the CSS. For example, 
    `@import "my-third-party-theme/main.scss";`

Now you know how to integrate third party themes with Clay Base! 

## Related Topics

- [Customizing Atlas and Clay Base Themes](/docs/7-2/frameworks/-/knowledge_base/f/customizing-atlas-and-clay-base-themes)

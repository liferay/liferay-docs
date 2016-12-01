# Importing Lexicon CSS in a Theme [](id=importing-lexicon-css-in-a-theme)

Lexicon is an extension of Bootstrap's CSS Framework. It fills the frontend gaps between Bootstrap and the specific needs of Liferay Portal. It's built with Sass.

Bootstrap is by far the most popular CSS framework on the web. It is open source and released under the  MIT license, which means you are free to download and use for personal, private, company internal, or commercial purposes. This has created a large developer community around this project.

Builtwith.com estimates 10,057,498 live websites using Bootstrap as of August 2016. We can take advantage of Bootstrap's popularity by having access to all the crowdsourced documentation and third party resources on the web. As a manager, team lead, or someone trying to put together a team to build something, it's easier to find experienced developers versus another framework.

## Lexicon Features [](id=lexicon-features)

As mentioned before, Lexicon is fills the gaps between Bootstrap and the specific needs of Liferay Portal. We have added components and features to Bootstrap to cover more use cases. Some of the new components added by Lexicon are:

* Aspect Ratio
* Cards
* Dropdown Wide and Dropdown Full
* Figures
* Nameplates
* Sidebar / Sidenav
* Stickers
* SVG Icons
* Timelines
* Toggles

We have also added a lot of reusable css patterns to help accomplish time consuming tasks such as:

* truncating text
* content filling the remaining container width
* truncating text inside table cells
* table cells filling remaining container width and table cells only being as wide as its content
* open and close icons inside collapsible panels
* nested vertical navigations
* slideout panels
* notification icons/messages
* vertical alignment of content

## Lexicon Structure [](id=lexicon-structure)

Lexicon is bundled with two sub-themes, Lexicon Base and Atlas. Lexicon Base is our Bootstrap API extension. It is also the theme that is used in Liferay's Styled Theme. It adds all the features and components we need and inherits Bootstrap's styles. As a result, Lexicon Base is fully compatible with third party themes that leverage Bootstrap's Sass variable API. Lexicon Base should be the base theme you use to integrate third party themes into Liferay Portal.

Atlas is Liferay's custom Bootstrap theme that is used in the Classic Theme. Its purpose is to overwrite and manipulate Bootstrap and Lexicon Base to create our classic look and feel. Atlas is equivalent to installing a Bootstrap third party theme. It is not recommended to try and integrate third party themes with Atlas as it adds variables and styles that are outside the scope of Bootstrap's API.

## Customizing Atlas in Liferay [](id=customizing-atlas-in-liferay)

In your custom theme's `/src/css` directory (For Legacy Ant Theme Tasks place in `/_diff/css`):

Add a file named `aui.scss` with the code below and save:
```
@import "aui/lexicon/atlas";
```

Add a file named `_imports.scss` with the code below and save:
```
@import "bourbon";
@import "mixins";
@import "aui/lexicon/atlas-variables";
@import "aui/lexicon/bootstrap/mixins";
@import "aui/lexicon/lexicon-base/mixins";
@import "aui/lexicon/atlas-theme/mixins";
```

Add a file named `_aui_variables.scss` with the code below and save:
```
// Icon paths

$FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
$font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
$icon-font-path: "aui/lexicon/fonts/";
```
> At the bottom of this file is where all your Atlas, Bootstrap, and Lexicon Base variable overwrites will live.

Add a file named `_custom.scss` with the code below and save:
```
/* These inject tags are used for dynamically creating imports for themelet styles, you can place them where ever you like in this file. */

/* inject:imports */
/* endinject */

/* This file allows you to override default styles in one central location for easier upgrade and maintenance. */
```
> Any custom css should be placed in this file.

#### Extending Atlas with Classic Theme

If you would like to include all of Classic Theme you can skip the steps above and copy over all the files located in:
```
frontend-theme-classic/src/css
frontend-theme-classic/src/images
frontend-theme-classic/src/js
frontend-theme-classic/src/templates
```

This can also be accomplished using the [Liferay Theme Tasks](https://github.com/liferay/liferay-theme-tasks) module [gulp kickstart](https://github.com/liferay/liferay-theme-tasks#kickstart) command and following the prompts.

## Customizing Lexicon Base in Liferay [](id=customizing-lexicon-base-in-liferay)

In your custom theme's `/src/css` directory (For Legacy Ant Theme Tasks place in `/_diff/css`):

Add a file named `_aui_variables.scss` with the code below and save:
```
// Icon paths

$FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
$font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
$icon-font-path: "aui/lexicon/fonts/";
```
> At the bottom of this file is where all your Atlas, Bootstrap, and Lexicon Base variable overwrites will live.

Any custom css should be placed in `_custom.scss`.
```
/* These inject tags are used for dynamically creating imports for themelet styles, you can place them where ever you like in this file. */

/* inject:imports */
/* endinject */

/* This file allows you to override default styles in one central location for easier upgrade and maintenance. */
```
> Any custom css should be placed in this file.

## Adding a Third Party Theme [](id=adding-a-third-party-theme)

> Third party themes must be built with Sass to be compatible with Liferay. Please make sure the Sass files are included before making any theme purchase.

1. Follow the steps in [Customizing Lexicon Base in Liferay](#customizing-lexicon-base-in-liferay) in Liferay.
2. Create a directory inside `/src/css` (For Legacy Ant Theme Tasks `/_diff/css`) that contains your third party theme e.g. `/src/css/awesome-theme` or `/_diff/css/awesome-theme`
3. Copy the contents of the theme to that directory
4. In `_aui_variables.scss`, add `@import "awesome-theme/variables.scss";` or the name of the file containing the variables. Note: You may omit the leading underscore when importing Sass files.
5. In `_custom.scss`, add `@import "awesome-theme/main.scss";` or the name of the file containing the css.
6. Deploy your theme with `gulp deploy` (For Legacy Ant Theme Tasks use `ant deploy`)
click on Go To Site

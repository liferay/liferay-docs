---
header-id: setting-up-the-theme
---

# Setting up the Theme

[TOC levels=1-4]

You'll use the Liferay JS Theme Toolkit's Liferay Theme Generator to generate 
the theme's files. In this section you'll install the Liferay Theme Generator 
and its dependencies, generate the initial theme that you'll build on throughout 
the remainder of the tutorial, and set up the theme to extend the 
[Atlas base theme](/docs/7-2/frameworks/-/knowledge_base/f/customizing-atlas-and-clay-base-themes). 
Atlas, which provides the look of the Classic theme, builds on the default Clay 
Base theme and provides additional styles for you to get the theme up and 
running. 

Follow these steps to set up the theme:

1.  Install the Theme Generator. Since you're developing a theme for 
    @product-ver@, install v9.x.x if it's not installed already. Run the command 
    below:

    ```bash    
    npm install -g generator-liferay-theme@9.x.x
    ```

2.  Install the Yeoman and gulp dependencies:

    ```bash
    npm install -g yo gulp
    ```
    
3.  Generate the starting theme with the Theme Generator. Enter 
    *Lunar Resort Theme* for the name and *lunar-resort* and answer no for the 
    Font Awesome prompt since the theme uses Clay icons instead:

    ```bash
    yo liferay-theme
    ```

    ![Figure 1: Answer no for the Font Awesome Prompt](../../images/theme-tutorial-yeoman-prompt.png)

4.  Configure the theme to extend the Atlas theme. Add a `clay.scss` file to the 
    theme's `/src/css/` folder and add the import shown below:
    
    ```sass
    @import "clay/atlas";
    ```
    
5.  Create a `_imports.scss` file in the `/src/css/` folder and add the imports 
    shown below to it. This includes the default imports and replaces the 
    `clay/base-variables` with the Atlas base variables:

    ```sass
    @import "bourbon";

    @import "mixins";

    @import "compat/mixins";

    @import "clay/atlas-variables";
    ```

The theme is set up and ready to go. Jump to the next section to build the Lunar 
Resort's Header and customize the logo. 

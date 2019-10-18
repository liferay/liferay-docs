---
header-id: setting-up-the-theme
---

# Setting up the Theme

[TOC levels=1-4]

In this section, you'll use the Liferay JS Theme Toolkit's Liferay Theme 
Generator to generate the theme's files. You'll complete these tasks:

- Install the Liferay Theme Generator and its dependencies
- Generate a theme
- Configure the theme to extend the [Atlas base theme](/docs/7-2/frameworks/-/knowledge_base/f/customizing-atlas-and-clay-base-themes). 

Atlas provides the look of the Classic theme. It builds on the default Clay Base 
theme and provides additional styles.

Follow these steps to generate and configure the theme:

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
    *Lunar Resort Theme* for the name and *lunar-resort* for the ID, and answer 
    no for the Font Awesome prompt. This theme uses Clay icons instead:

    ```bash
    yo liferay-theme
    ```

    ![Figure 1: Answer no for the Font Awesome Prompt](../../images/theme-tutorial-yeoman-prompt.png)

4.  To develop the theme you must copy the default files from the theme's build 
    and modify them. The `/src/css/` folder and `_custom.scss` file are included 
    by default. Run the command below from the theme's root folder to 
    build the files:
    
    ```bash
    gulp build
    ```
    
5.  Create a new `/src/templates/` folder and copy `portal_normal.ftl` from the 
    `build/templates/` folder into it.

6.  Configure the theme to extend the Atlas theme. Add a `clay.scss` file to the 
    theme's `/src/css/` folder and add the import shown below:
    
    ```sass
    @import "clay/atlas";
    ```
    
7.  Create an `_imports.scss` file in the `/src/css/` folder and add the imports 
    shown below to it. This includes the default imports and replaces the 
    `clay/base-variables` with the Atlas base variables:

    ```sass
    @import "bourbon";

    @import "mixins";

    @import "compat/mixins";

    @import "clay/atlas-variables";
    ```

You've generated the theme, prepared it for development, and configured it to 
extend the Atlas theme. Continue to the next section to build the Lunar Resort's 
Header and customize the logo. 

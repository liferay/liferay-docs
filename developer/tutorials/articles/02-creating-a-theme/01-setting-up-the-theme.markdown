# Setting up the Theme

You'll use the Liferay JS Theme Toolkit's Liferay Theme Generator to generate 
the theme's files. In this section you'll install the Liferay Theme Generator 
and its dependencies, generate the initial theme that you'll build on throughout 
the remainder of the tutorial, and set up the theme to extend the Atlas base 
theme. Atlas builds on the default Clay Base Theme, and provides some default 
styles for you to get the Theme up and running. It provides the look of the 
Classic Theme. 

Follow these steps to set up the theme:

1.  Install the theme generator. Since you're developing a theme for 
    @product-ver@, install v9.x.x:

    ```bash    
    npm install -g generator-liferay-theme@9.x.x
    ```

2.  Install the Yeoman and gulp dependencies:

    ```bash
    npm install -g yo gulp
    ```
    
3.  Generate the starting theme with the Theme Generator:

    ```bash
    yo liferay-theme
    ```
    
4.  Configure the theme to extend the Atlas theme. Add a `clay.scss` file to the 
    theme's `css/` folder and add the following imports. This includes imports 
    for Font Awesome and Glyphicons as well. You'll use these later to add icons 
    to the theme:
    
    ```sass
    @import 'liferay-font-awesome/scss/font-awesome';
    @import 'liferay-font-awesome/scss/glyphicons';

    @import "clay/atlas";
    ```
    
5.  Create a `_imports.scss` file in the `css/` folder and add the imports shown 
    below to it. This includes the default imports and replaces the 
    `clay/base-variables` with the Atlas base variables:

    ```sass
    @import "bourbon";

    @import "mixins";

    @import "compat/mixins";

    @import "clay/atlas-variables";
    ```

Alright. The theme is set up and ready to go. Jump to the next section to build 
the Lunar Resort's Header and navigation. 
# Creating Themes with the Liferay Theme Generator [](id=creating-themes-with-the-liferay-theme-generator)

Once you've 
[installed the Liferay Theme Generator](/develop/reference/-/knowledge_base/7-1/installing-the-theme-generator) 
and its dependencies, you can use it to create and manage themes for @product@. 
This tutorial covers how to generate and develop themes with the Liferay Theme 
Generator. 

+$$$

**Note:** The Liferay Theme Generator does not currently support 7.1 theme 
creation.

$$$

## Running the Liferay Theme Generator [](id=running-the-themes-generator)

Run the following command and follow the generator prompts to create a theme in 
the current directory:

    yo liferay-theme

+$$$

Note: If you run into permissions issues during theme generation, make sure 
you have read/write access to all folders on your system. 

$$$

The information you provide is added to the `liferay-theme.json` file in your 
theme's root folder. You can update this file manually to reflect any 
updates you make to your theme (such as changing app servers), or use the 
provided 
[gulp tasks](/develop/reference/-/knowledge_base/7-1/theme-gulp-tasks)  
to make automated changes. For example, generated themes are based off of 
the 
[styled](https://www.npmjs.com/package/liferay-theme-styled)
theme by default, but you can define a different base theme to use by executing 
the 
[`gulp extend`](/develop/reference/-/knowledge_base/7-1/theme-gulp-tasks) 
command. 

If you're on Windows and using the Ruby version of Sass, you must configure your 
theme to support Compass. If you're using a different environment, you can learn 
how to 
[develop a generated theme](#developing-a-generated-theme). 

### Configuring Themes to Support Compass on Windows [](id=configuring-themes-to-support-compass-on-windows)

By default, generated themes use lib-sass/bourbon, instead of Compass. If, 
however, you are on Windows and are using the Ruby version of Sass, you must 
configure the theme to support Compass. Follow the steps below.

1.  Open the `package.json` file found in the root folder of your theme, and
    locate the `rubySass` property and change it from `false` to `true`.

    Now that your theme is set to support Compass, you must install the Ruby
    Sass middleware and save it as a dependency for your theme.
    
2.  Run the following command to install the Ruby Sass middleware:

        npm i --save gulp-ruby-sass

    The `--save` flag adds Ruby Sass to the list of dependencies in your theme's
    `package.json` file. Your theme is ready to use.

3.  Run the `gulp build` task to generate the base files for your theme. Open 
    the `build` directory of your theme to view the base files.

There you have it! You now have a working theme. At the moment, the theme is a
bit bare bones, but you have everything you need to develop it. If you're 
familiar with developing themes with the Liferay Plugins SDK, there are some 
differences that should note for generated themes. 

## Developing a Generated Theme [](id=developing-a-generated-theme)

The development process is slightly different for generated themes compared to 
themes created with the Liferay Plugins SDK. To develop a generated theme, 
follow these steps:

1.  Run the `gulp build` task to generate the base files for your theme. You'll 
    notice that all CSS files have been converted to Sass SCSS files. Sassy CSS 
    (SCSS) lets you to use the latest CSS styles and leverage Sass syntax 
    advantages, such as nesting and variables.

2.  Mirror the folder structure of the files you want to modify inside the 
    theme's `src` folder. 

3.  Copy the build files into the proper folder and make your changes. For 
    instance, to make a change to the `portal_normal.ftl` theme template, create 
    a `templates` folder in your `src` directory and copy the 
    `portal_normal.ftl` file into it. 

4.  Run the following command to deploy your theme to your configured @product@ 
    instance:

        gulp deploy

5.  Apply your theme by following the instructions found in the 
    [Creating and Managing Pages](/discover/portal/-/knowledge_base/7-1/creating-and-managing-pages#customizing-the-look-and-feel-of-site-pages)
    User Guide. 

+$$$

**Note:** By default, theme images are cached by the browser. If you need to 
update images in the theme, it is best practice to use versioning in the image 
URL ( e.g. `background-image:url("../images/image.jpg?v=1")`). Then update the 
version each time you update the image. This removes the potential for any 
caching issues.

$$$
    
Now that you've created a theme and deployed it, you can use the theme project's
[gulp tasks](/develop/reference/-/knowledge_base/7-1/theme-gulp-tasks) 
to further develop and manage your theme. 

There you have it! You're ready to design a terrific theme!

## Related Topics [](id=related-topics)

[Theme Gulp Tasks](/develop/reference/-/knowledge_base/7-1/theme-gulp-tasks)

[Installing the Liferay Theme Generator](/develop/reference/-/knowledge_base/7-1/installing-the-theme-generator) 

[Themelets](/develop/tutorials/-/knowledge_base/7-1/themelets)


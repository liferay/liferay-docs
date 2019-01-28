# Creating Themes [](id=creating-themes)

The Liferay Theme Generator lets you create themes, themelets, layout templates 
and more. It is just one of Liferay JS Theme Toolkit's 
[tools](https://github.com/liferay/liferay-themes-sdk/tree/master/packages). 
There are a few dependencies required to run the generator. If you have NodeJS 
installed, you're already one step ahead. 

Follow these steps to install the Liferay Theme Generator and generate a theme:

1.  Install [Node.js](http://nodejs.org/). We recommend installing the Long Term 
    Support (LTS) version. Note that Node Package Manager (npm) is installed 
    with this as well. You'll use npm to install the remaining dependencies and 
    generator. 

2.  Use npm to install 
    [Yeoman](http://yeoman.io/) 
    and 
    [gulp](https://gulpjs.com/):

        npm install -g yo gulp

3.  Install the Liferay Theme Generator. 7.x.x versions of the Theme Generator 
    let you create themes for 6.2 and 7.0. Versions 8.x.x and up let you create 
    themes for 7.0 and later versions of @product@.

    To create themes for **6.2** and **7.0**, run the following command:

        npm install -g generator-liferay-theme@^7.x.x
    
    To create themes for **7.0**, **7.1**, and later versions of @product@, run 
    this command:
    
        npm install -g generator-liferay-theme
   
   If you're on Windows, follow the instructions in step 4 to install Sass, 
   otherwise you can skip to step 5.

4.  To use Sass on Windows, you must use either Sass from node-sass or Sass from 
    Ruby. By default, the generator uses node-sass, but this also requires that 
    you have Visual Studio on Windows and 
    [node-gyp and Python installed](https://github.com/nodejs/node-gyp#installation). 
    Since Visual Studio is a particularly large dependency, **if you don't 
    already have it installed**, we recommend you install Ruby Sass instead. 
    Install Ruby via the 
    [Ruby installer](http://rubyinstaller.org/), 
    and then install the compass gem:

        gem install compass

    The matching sass gem is automatically fetched and installed as well. 

5.  Run the generator and follow the prompts to create your theme:

        yo liferay-theme

    ![Figure 1: You can generate a theme by answering just a few configuration questions.](../../../../images/theme-generator-theme-prompt.png)

    **Important**: By default, your theme is based on the styled theme and uses
    lib-sass/bourbon, instead of Compass. If, however, you are on Windows and
    are using the Ruby version of Sass, you must configure the theme to support
    Compass. If you don't use Ruby Sass, you can skip to step eight. 

6.  Open the `package.json` file found in the root folder of your theme, and 
    locate the `rubySass` property and change it from `false` to `true`. 
 
7.  Now that your theme is set to support Compass, you must install the Ruby 
    Sass middleware and save it as a dependency for your theme:

        npm i --save gulp-ruby-sass

    The `--save` flag adds Ruby Sass to the list of dependencies in your theme's 
    `package.json` file. Your theme is ready to use. 

8.  Navigate to your theme folder and run `gulp deploy` to deploy your new theme 
    to the server.
 
Now you have a powerful theme development tool at your disposal. The sky is the 
limit!

![Figure 2: The tools are in your hands to create any theme you can imagine.](../../../../images/theme-generator-theme-example.png)

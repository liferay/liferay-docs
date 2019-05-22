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
    generator. Make sure to 
    [set up your npm environment](/develop/reference/-/knowledge_base/7-1/setting-up-your-npm-environment) 
    before moving to the next step. Failing to do this can lead to permissions 
    issues later on. 

2.  Use npm to install 
    [Yeoman](http://yeoman.io/) 
    and 
    [gulp](https://gulpjs.com/):

        npm install -g yo gulp

3.  Install the Liferay Theme Generator. A few versions of the Liferay Theme 
    Generator are available. The version you must install depends on the version 
    of @product@ you're developing on. The required versions are listed in the 
    table below:

    | Liferay Version | Liferay Theme Generator Version | Command |
    | --- | --- | --- |
    | 6.2 | 7.x.x | `npm install -g generator-liferay-theme@^7.x.x` |
    | 7.0 | 7.x.x or 8.x.x | Same as above or below |
    | 7.1 | 8.x.x | `npm install -g generator-liferay-theme@^8.x.x` |
   
    If you're on Windows, follow the instructions in step 4 to install Sass, 
    otherwise you can skip to step 5.

4.  The generator uses node-sass. If you are on Windows, you must also install 
    [node-gyp and Python](https://github.com/nodejs/node-gyp#installation).

5.  Run the generator and follow the prompts to create your theme:

        yo liferay-theme

    ![Figure 1: You can generate a theme by answering just a few configuration questions.](../../../../images/theme-generator-theme-prompt.png)

6.  Navigate to your theme folder and run `gulp deploy` to deploy your new theme 
    to the server.
 
Now you have a powerful theme development tool at your disposal. The sky is the 
limit!

![Figure 2: The tools are in your hands to create any theme you can imagine.](../../../../images/theme-generator-theme-example.png)

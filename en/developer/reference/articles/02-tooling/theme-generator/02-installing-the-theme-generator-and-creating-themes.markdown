---
header-id: installing-the-theme-generator-and-creating-a-theme
---

# Installing the Theme Generator and Creating a Theme

[TOC levels=1-4]

The steps below show how to install the Liferay Theme Generator and generate a 
theme. 

![Figure 1: The tools are in your hands to create any theme you can imagine.](../../../images/theme-generator-theme-example.png)

Your first step in generating a theme is installing 
[NodeJS](http://nodejs.org/) 
(along with Node Package Manager(npm)) 
if it's not already installed. We recommend installing [v10.15.1](https://nodejs.org/download/release/v10.15.1/), 
which is the version Liferay Portal 7.2 supports. Once NodeJS is installed and 
you've [set up your npm environment](/docs/7-2/reference/-/knowledge_base/r/setting-up-your-npm-environment), 
you can follow these steps to install the Liferay Theme Generator and generate a 
theme:

1.  Use npm to install the 
    [Yeoman](http://yeoman.io/) 
    dependency:

    ```bash
    npm install -g yo
    ```

    | **Note:** Gulp is included as a local dependency in generated themes, so you 
    | are not required to install it. It can be accessed by running 
    | `node_modules\.bin\gulp` followed by the Gulp task from a generated theme's 
    | root folder.

2.  Install the Liferay Theme Generator with the command below:

    ```bash
    npm install -g generator-liferay-theme
    ```

    If you're on Windows, follow the instructions in step 3 to install Sass, 
    otherwise you can skip to step 4.

3.  The generator uses node-sass. If you're on Windows, you must also install 
    [node-gyp and Python](https://github.com/nodejs/node-gyp#installation).

4.  Run the generator and follow the prompts to create your theme:

    ```bash
    yo liferay-theme
    ```

    ![Figure 2: You can generate a theme by answering just a few configuration questions.](../../../images/theme-generator-theme-prompt.png)
    
    | **Note:** Since Liferay DXP Fix Pack 2 and Liferay Portal 7.2 CE GA2, Font 
    | Awesome is available globally as a system setting, which is enabled by 
    | default. If you're using Font Awesome icons in your theme, answer yes (y) 
    | to the Font Awesome question to include Font Awesome imports in your 
    | theme. This ensures that your icons won't break if a Site Administrator 
    | disables the global setting. 

5.  Navigate to your theme folder and run `gulp deploy` to deploy your new theme 
    to the server.
 
Now you have a powerful theme development tool at your disposal. The sky is the 
limit! 

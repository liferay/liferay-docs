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
if it's not already installed. We recommend installing the Long Term Support 
(LTS) version. Once NodeJS is installed, you can follow these steps to install 
the Liferay Theme Generator and generate a theme:

1.  Use npm to install the 
    [Yeoman](http://yeoman.io/) 
    dependency:

        npm install -g yo

2.  Install the Liferay Theme Generator with the command below:
    
        npm install -g generator-liferay-theme
   
    If you're on Windows, follow the instructions in step 3 to install Sass, 
    otherwise you can skip to step 4.

3.  The generator uses node-sass. If you're on Windows, you must also install 
    [node-gyp and Python](https://github.com/nodejs/node-gyp#installation).

4.  Run the generator and follow the prompts to create your theme:

        yo liferay-theme

    ![Figure 2: You can generate a theme by answering just a few configuration questions.](../../../images/theme-generator-theme-prompt.png)

5.  Navigate to your theme folder and run `gulp deploy` to deploy your new theme 
    to the server.
 
Now you have a powerful theme development tool at your disposal. The sky is the 
limit!

## Related Topics

- [Generating Layout Templates](/docs/7-2/reference/-/knowledge_base/reference/creating-layout-templates-with-the-themes-generator)
- [Theme Components](/docs/7-2/frameworks/-/knowledge_base/frameworks/theme-components)
- [Understanding the Page Layout](/docs/7-2/frameworks/-/knowledge_base/frameworks/understanding-the-page-layout)

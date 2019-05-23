---
header-id: creating-themelets-with-the-themes-generator
---

# Generating Themelets with the Theme Generator

[TOC levels=1-4]

This steps below show how to use the Liferay Theme Generator's Themelets 
sub-generator to create a themelet. 

![Figure 1: Themelets can be used to modify one aspect of the UI, that you can then reuse in your other themes.](../../../images/product-menu-animation-themelet.png)

Your first step in creating a themelet is installing the 
[Liferay Theme Generator](/docs/7-2/reference/-/knowledge_base/r/installing-the-theme-generator-and-creating-a-theme) 
if it's not already installed. Once the generator is installed, you can follow 
these steps to create a themelet:

1.  Open the Command Line and navigate to the folder you want to create your
    themelet in.

2.  Run `yo liferay-theme:themelet` and follow the prompts to generate the
    themelet.

    ![Figure 2: The Themelet sub-generator automates the themelet creation process, making it quick and easy.](../../../images/themelet-prompt.png)

    The generated themelet contains a `package.json` file with configuration 
    information and a `src/css` folder that contains a `_custom.scss` file. Just 
    like a theme, add your CSS changes to the `src/css` folder, and add your 
    JavaScript changes to the `src/js` folder.

3.  To use your themelet, you must install it globally first. This makes the 
    themelet visible to the generator. To install your themelet globally, 
    navigate into its root folder and run `npm link`. Note, you may need to run 
    the command using `sudo npm link`. This creates a globally-installed 
    symbolic link for the themelet in your npm packages folder. Now your 
    themelet is available to install in your themes. 

Awesome! You just created a themelet with the Theme Generator's Themelets 
sub-generator. 

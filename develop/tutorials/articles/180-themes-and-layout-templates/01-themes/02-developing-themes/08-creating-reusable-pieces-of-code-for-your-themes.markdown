# Creating Reusable Pieces of Code for Your Themes [](id=creating-reusable-pieces-of-code-for-your-themes)

Themelets are small, extendable, and reusable pieces of code. Whereas themes
require multiple components, a themelet only requires the files you wish to
extend. This creates a more modular approach to theme design that lends itself
well to collaboration and reduces the need for duplicated code in your theme.

Themelets let developers easily share code snippets across their themes and with 
other developers. A themelet can consist of CSS, JavaScript, and templates, just 
like a theme.

Themelets are very flexible, and therefore they have a number of possible uses.
You can make a themelet to modify the appearance of the admin tools, or a 
themelet that uses a custom JavaScript component for responsive embedded videos, 
and everything in between. For example, the 
[Liferay Product Menu Animation Themelet](https://www.npmjs.com/package/lfr-product-menu-animation-themelet) 
simply alters the animation for the Product Menu.

![Figure 1: Themelets can be used to modify one aspect of the UI, that you can then reuse in your other themes.](../../../../images/product-menu-animation-themelet.png)

If there is something you have to manually code for every theme you create, it's 
a good candidate for a themelet.

This tutorial demonstrates how to:

- Create a themelet to extend your theme

- Install a Themelet

To create a themelet, you need a theme to extend and the Node.js build tools 
installed, as explained in the 
[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)
tutorial. 

## Creating a Themelet [](id=creating-a-themelet)

Follow these steps to create a themelet:

1.  Open the Command Line and navigate to the folder you want to create your
    themelet in.

2.  Run `yo liferay-theme:themelet` and follow the prompts to generate the
    themelet.

    ![Figure 2: The Themelet sub-generator automates the themelet creation process, making it quick and easy.](../../../../images/themelet-prompt.png)
   
    The generated themelet contains a `package.json` file with configuration 
    information and a `src/css` folder that contains a `_custom.scss` file. Just 
    like a theme, all of your updated files go into the `src` folder. 

3.  To use your themelet, you must install it globally first. This makes the 
    themelet visible to the generator. To install your themelet globally, 
    navigate into its root folder and run `npm link`. Note, you may need to run 
    the command using `sudo npm link`. This creates a globally-installed 
    symbolic link for the themelet in your npm packages folder. Now your 
    themelet is available to install in your themes. 
    
That's all you need to do to prepare your themelet to use in theme development.
Now you can develop your theme, making sure to add the files/folders to the 
`src` folder of the themelet. 

## Installing a Themelet [](id=installing-a-themelet)

After you've developed your themelet, follow the steps below to install it into
your theme.

1.  Navigate to your theme's root folder and run the following command:

        gulp extend

2.  Choose *Themelet* as the theme asset to extend.

3.  Select *Search globally installed npm modules*.

    ![Figure 3: You can extend your theme using globally installed npm modules or published npm modules.](../../../../images/install-themelet.png)

4.  Highlight your themelet, press spacebar to activate it, and press *Enter* to
    install it. 

5.  Run `gulp deploy` to build and deploy your theme with the new themelet
    updates.
   
Your themelet is installed! As you can see, themelets are a handy tool to add to
your theme development bag o' tricks. 

## Related Topics [](id=related-topics)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-1/importing-resources-with-a-theme)

[Creating Themes](/develop/tutorials/-/knowledge_base/7-1/creating-themes)

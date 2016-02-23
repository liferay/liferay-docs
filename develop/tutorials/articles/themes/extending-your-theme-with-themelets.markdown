# Extending your Theme with Themelets [](id=extending-your-theme-with-themelets)

Themelets are small, extendable, and reusable pieces of code. Whereas themes 
require multiple components, a themelet only requires the files you wish to 
extend. This creates a more modular approach to theme design, that lends itself 
well to collaboration, and reduces the need for duplicated code in your theme.

Themlets allow developers to easily share snippets of code across all of 
their themes, and in addition, publish and share the same snippets with other 
developers. A themelet can consist of CSS, JavaScript, and templates, just 
like a theme.

Themelets are very flexible, and therefore have a number of possible uses. You 
could make a themelet that modifies the appearance of the new admin tools in 7.0, 
or one with a custom js component for responsive embedded videos. For example,
take a look at the [Liferay Product Menu Animation Themelet](https://www.npmjs.com/package/lfr-product-menu-animation-themelet). 
This themelet simply alters the animation for the Product Menu.

![Figure 1: Themelets can be used to modify one aspect of the UI, that you can then reuse in your other themes.](../../images/product-menu-animation-themelet.png)

If there is anything that you find yourself coding over and over again for 
themes, it’s a good candidate for a themelet.

This tutorial covers how to create a themelet to extend your theme. In order to 
create a themelet, you will need to have the Node.js build tools installed. You 
can learn how to install the Node.js build tools and create a theme in the 
[Creating Themes with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/creating-themes-with-the-liferay-theme-generator) 
tutorial.

## Creating a Themelet [](id=creating-a-themelet)

Follow these steps to create a themelet:

1. Open the Command Line and navigate to the directory you want to create your 
   themelet in.

2. Run `yo liferay-theme:themelet` and follow the prompts to generate the 
   themelet.

    ![Figure 2: The Themelet sub-generator automates the themelet creation process, making it quick and easy.](../../images/themelet-prompt.png)
   
    This creates the themelet for you, which contains a `package.json` file with 
    configuraion information, and a `src` directory with a `_custom.scss` file. 
    Just like a theme, all of your updated files will go into the `src` 
    directory. In order to use your themelet in your theme, it will first have 
    to be globally installed. This allows the themelet to be recognized by the 
    generator. You'll take care of that next.

3. Navigate to your newly created themelet in the command line and run 
   `npm link`. This creates a globally-installed symbolic link for the 
   themelet in your npm packages directory.
			
    Note you may need to run the command using `sudo npm link`. Now your 
    themelet will be available to install in your theme.
    
That's all you need to do to prepare your themelet for development. Now you can
develop your theme, making sure to add the files/directories to the `src`
directory of the themelet.

## Installing your Themelet [](id=installing-your-themelet)

Now that your themelet is developed, follow the steps below to install it into
your theme.

1. Navigate to your theme's root directory and run the following command:

        gulp extend

2. Choose **Themelet** as the theme asset to extend.

3. Select **Search globally installed npm modules**.

    ![Figure 3: You can extend your theme using globally installed npm modules or published npm modules.](../../images/install-themelet.png)

4. Highlight your themelet and press spacebar to activate it.

5. Press Enter to install the themelet.

6. Run `gulp deploy` to build and deploy your theme with the new themelet 
   updates.
   
Your themelet is installed! As you can see, themelets are a handy tool to add to
your theme development bag o' trix.

## Related Topics [](id=related-topics)

[Importing Resources with Your Themes](/develop/tutorials/-/knowledge_base/7-0/importing-resources-with-your-themes)

[Creating Themes with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/creating-themes-with-the-liferay-theme-generator)

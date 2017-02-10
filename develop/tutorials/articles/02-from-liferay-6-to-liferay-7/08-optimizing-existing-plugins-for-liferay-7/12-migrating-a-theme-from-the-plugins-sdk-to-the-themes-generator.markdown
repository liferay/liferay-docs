# Migrating a Theme from the Plugins SDK to the Themes Generator [](id=migrating-a-6-2-theme-to-liferay-7)

After you've upgraded your existing theme to @product-ver@, the Themes Generator
offers enhanced development features and tools for optimizing your theme and
streamlining theme management. To introduce one of its most powerful features,
we'll pose some questions. 

**Questions**:

-   Do you want to make a temporary change to your theme's UI?
-   Do you want to add the same UI modification to all of your themes without
    duplicating code? 
-   Do you want to share your new theme designs with a colleague?
-   Do you want to test a new design concept in your theme without altering its
    code?

**Answer**: [Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets) 
are the answer! Themelets are small, extendable, reusable modular pieces 
of code that let you make changes to your theme quickly. Because they are 
modular, you can use the same themelet for multiple themes!

Themelets are just one of the features you gain from migrating your
existing Ant-based theme project to a [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
project. The Themes Generator is a Node.js-based tool that gives you access to
an array of  [theme Gulp tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks),
that facilitate developing and managing themes. 

The *upgrade* Gulp task upgrades Liferay Portal 6 themes to @product-ver@. For details,
refer to the [Upgrading Themes](/develop/tutorials/-/knowledge_base/7-0/themelets/upgrading-themes)
tutorial. 

In addition to the *upgrade* task, there are tasks for building and deploying
themes and for interacting with deployed themes. For instance, you can
automatically redeploy your theme as you make changes to it.

Do you periodically need to make changes to your theme's settings? No problem.
You can configure your theme's settings through the command-line wizard that the
Themes Generator provides. All you have to do is answer a few questions about
the settings.

As you can see, the Themes Generator, Node.js, and Gulp development tools offer
a lot to a Liferay theme developer.

This tutorial assumes that you have already installed the [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)
and have an existing Liferay Portal 6.2 theme that was developed with the Plugins SDK.

## Importing Your 6.2 Theme [](id=importing-your-6-2-theme)

The first thing you must do is import your 6.2 theme to use the new theme
development tools. Follow the steps below to set up your theme:

1.  Navigate to the directory you want to import your theme into and run the
    following command:

        yo liferay-theme:import

    This runs the import sub-generator for the themes generator.

2.  Enter the absolute path of the theme you want to import and press `Enter`.

    +$$$

    **Note:** you must specify an absolute path, as the themes import
    sub-generator does not support relative paths.

    $$$

    The theme's modified files are copied and migrated to a newly created 
    `src` directory. A `gulpfile.js`, `liferay-theme.json`, `package.json` file 
    and a `node_modules` directory are also added.

    Next, the `gulp init` task runs and prompts you with a couple questions.

3.  Enter the path to your app server.

4.  Enter your site's URL (this can be your production site, development site,
    etc.), or press `Enter` to accept the default `http://localhost:8080`.

Your theme is now set up to use the Node.js build tools and theme Gulp tasks!

## Related Articles [](id=related-articles)

[Introduction to Themes](/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/upgrading-themes)

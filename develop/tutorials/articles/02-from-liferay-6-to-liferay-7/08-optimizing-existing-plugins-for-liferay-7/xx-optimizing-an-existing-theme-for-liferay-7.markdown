# Optimizing an Existing Theme for Liferay 7 [](id=migrating-a-6-2-theme-to-liferay-7)

Are you applying the same modifications to each of your themes? Do you want to 
make a temporary change to your theme's UI? [Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets) 
are the answer! Themelets are small, extendable, and, reusable, modular pieces 
of code, that allow you to quickly make changes to your theme. Because they are 
modular, you can use the same themelet for multiple themes! Do you want to add 
the same UI modification to all of your themes, without rewriting the code? Use 
a themelet! Do you want to share your new theme designs with a colleague? Use a 
themelet! Do you want to test out a new design concept without altering your 
theme's code? Use a themelet!

Themelets are just one of the many benefits gained when you migrate your theme
from an Ant based project to a [Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator) 
based project.

The Themes Generator is a Node.js-based tool that allows you to develop and 
manage your theme using [theme gulp tasks](/develop/reference/-/knowledge_base/7-0/theme-gulp-tasks).
These tasks allow you to run typical processes such as building and deploying, 
but they offer more as well. For instance, you can automatically deploy your 
theme when changes are made, or set the app server for your theme. Do you need 
to make changes to your theme's settings? No problem. You can quickly and easily 
configure your theme's settings through the handy command-line-wizard that the
Themes Generator provides. Just answer a few questions about the settings, and
in no time at all your theme's files are automatically updated.

As you can see, The Node.js development tools offer a lot to a Liferay theme 
developer.

This tutorial assumes that you already have the themes generator installed and a 
6.2 theme that was developed with the Plugins SDK.

This tutorial demonstrates how to:

- Migrate your theme from an Ant based project to a Themes Generator based
  project.

Go ahead and get started.

## Importing Your 6.2 Theme [](id=importing-your-6-2-theme)

The first thing you will need to do is import your 6.2 theme to use the new node
theme development tools. Follow the steps below to get your theme set up:

1.  Navigate to the directory you want to import your theme into and open the
    command line and run the following command:

        yo liferay-theme:import
        
    This runs the import sub-generator for the themes generator.

2.  Enter the direct path for your theme to import and press `Enter`.

    +$$$
    
    **Note:** the themes import sub-generator does not support relative paths.
    
    $$$

    The theme's modified files are all copied and migrated to a newly created 
    `src` directory. A `gulpfile.js`, `liferay-theme.json`, `package.json` file 
    and a `node_modules` directory is added to the existing theme.

    Next, the `gulp init` task runs and prompts you with a couple questions.
    
3.  Enter the path to your app server.

4.  Enter the URL to your production or development site, or press `Enter` to
    accept the default `http://localhost:8080`.
    
Your theme is now set up to use the Node.js build tools and theme gulp tasks!

## Related Articles [](id=related-articles)

[Introduction to Themes](/develop/tutorials/-/knowledge_base/7-0/introduction-to-themes)

[Themes Generator](/develop/tutorials/-/knowledge_base/7-0/themes-generator)

[Themelets](/develop/tutorials/-/knowledge_base/7-0/themelets)

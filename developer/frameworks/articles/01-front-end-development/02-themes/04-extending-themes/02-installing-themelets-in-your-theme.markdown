---
header-id: installing-a-themelet-in-your-theme
---

# Installing a Themelet in Your Theme

[TOC levels=1-4]

After you've created your 
[themelet](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator), 
follow the steps below to install it into your theme.

1.  Navigate to your theme's root folder and run `gulp extend`.

2.  Choose *Themelet* as the theme asset to extend.

3.  Select *Search globally installed npm modules* or *Search npm registry* to 
    locate the themelet.

    ![Figure 1: You can extend your theme using globally installed npm modules or published npm modules.](../../../../images/install-themelet.png)

4.  Highlight your themelet, press spacebar to activate it, and press *Enter* to
    install it. 
   
Great, now you know how to install a themelet in your theme! The next time you 
[deploy](/docs/7-2/frameworks/-/knowledge_base/f/deploying-and-applying-themes) 
your theme, the themelet will be bundled along with it. 

## Related Topics

-[Generating Themelets with the Theme Generator](/docs/7-2/reference/-/knowledge_base/r/creating-themelets-with-the-themes-generator)
-[Injecting Additional Context Variables and Functionality into Your Theme Templates](/docs/7-2/frameworks/-/knowledge_base/f/injecting-additional-context-variables-and-functionality-into-your-theme-templates)
-[Packaging Independent UI Resources for Your Site](/docs/7-2/frameworks/-/knowledge_base/f/packaging-independent-ui-resources-for-your-site)

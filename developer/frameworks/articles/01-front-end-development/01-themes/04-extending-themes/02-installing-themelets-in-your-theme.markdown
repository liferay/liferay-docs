---
header-id: installing-a-themelet-in-your-theme
---

# Installing a Themelet in Your Theme

[TOC levels=1-4]

After you've created your 
[themelet](/developer/reference/-/knowledge_base/7-2/creating-themelets-with-the-themes-generator), 
follow the steps below to install it into your theme.

1.  Navigate to your theme's root folder and run `gulp extend`.

2.  Choose *Themelet* as the theme asset to extend.

3.  Select *Search globally installed npm modules* or *Search npm registry* to 
    locate the themelet.

    ![Figure 1: You can extend your theme using globally installed npm modules or published npm modules.](../../../../images/install-themelet.png)

4.  Highlight your themelet, press spacebar to activate it, and press *Enter* to
    install it. 
   
Great, now you know how to install a themelet in your theme! The next time you 
[deploy](/developer/frameworks/-/knowledge_base/7-2/deploying-and-applying-themes) 
your theme, the themelet will be bundled along with it. 

## Related Topics

-[Generating Themelets with the Theme Generator](/developer/reference/-/knowledge_base/7-2/creating-themelets-with-the-themes-generator)
-[Injecting Additional Context Variables and Functionality into Your Theme Templates](/developer/frameworks/-/knowledge_base/7-2/injecting-additional-context-variables-and-functionality-into-your-theme-templates)
-[Packaging Independent UI Resources for Your Site](/developer/frameworks/-/knowledge_base/7-2/packaging-independent-ui-resources-for-your-site)

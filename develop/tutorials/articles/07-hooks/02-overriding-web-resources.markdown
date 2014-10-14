# Overriding web resources [](id=overriding-web-resources)

Hooks are commonly used to override web resources, found in `portal-web` in the
Liferay source. You can use a hook to override JSP files, JSPF files, JavaScript
files, CSS files, or images. 

+$$$

**Important:** Some resources have additional requisites:

 - ***JSPF:*** Changes won't take effect unless you modify the JSP that
   includes it. 
 - ***CSS:*** When modifying a CSS file imported by another CSS file, the
 changes won't take effect unless you modify the parent CSS file (usually
 `main.css`). 

$$$

Replacing a portal JSP is a simple task with hooks. Let's create and deploy a
hook to modify your portal's *Terms of Use* page. 

1.  Use the hook project we created earlier or create a new hook project. 

2.  Open the `liferay-hook.xml` file from your project's `docroot/WEB-INF`
    folder in Liferay IDE. By default, it opens in *Overview* mode. This mode,
    gives you a graphical user interface for developing your hook. You can
    toggle between overview mode and source mode via their respective tabs for
    the `liferay-hook.xml` file's editor. 

3.  Select the *Custom JSPs* folder from the outline to bring up the custom JSP
    options. Select the checkbox *Customize Liferay Portal JSPs* and create
    the default custom JSP folder `/META-INF/custom_jsps`, by clicking the icon
    that has the three yellow diamonds. 

    ![Figure 7.3: Liferay IDE lets you specify a folder for the custom JSPs you're developing. Create the folder by clicking the icon that has the three yellow diamonds.](../../images/hook-create-custom-jsps-folder.png)

4.  Add to the listing of custom JSPs by clicking the plus icon and specifying
    Portal's `html/portal/terms_of_use.jsp` file. Hint, the browse icon on the
    right-hand side within the custom JSP text field simplifies finding the JSP
    you want to customize. It lets you scroll through the JSPs that are
    accessible and lets you specify key words to narrow your search. 

    ![Figure 7.4: Liferay IDE simplifies adding a custom JSP to your hook by providing a powerful overview mode for editing your `liferay-hook.xml` file.](../../images/add-jsp-customization-with-hook.png)

5.  Open your hook's `docroot/META-INF/custom_jsps/html/portal/terms_of_use.jsp`
    file and modify it as necessary. 

6.  Deploy your hook and wait until it is deployed successfully. 

7.  Create a new user and log in. The *Terms of Use* page should include the
    changes you made above. 

Now there are two *Terms of Use* JSP files in the
`liferay-portal-[version]/tomcat-[tomcat-version]/webapps/ROOT/html/portal`
directory. One is called `terms_of_use.jsp` and another
`terms_of_use.portal.jsp`. `terms_of_use.jsp` is your hook's version, while
`terms_of_use.portal.jsp` is the original. To revert back to the original,
undeploy your hook. Your replacement JSP is removed, and the `.portal.jsp` file
is automatically renamed, taking its place. You can override any JSP in the
Liferay core, while retaining thew ability to easily revert your changes.
However, it's not possible to override the same JSP from multiple hooks; Liferay
won't know which version to use. 

+$$$

**Note:** We don't recommend changing the *Terms of Use* with a hook. You can
replace the *Terms of Use* with web content simply by setting two properties in
`portal-ext.properties`. Although our hook wasn't necessary, it was a good way
to demonstrate overriding a JSP with a hook. 

$$$

Next, we'll look at a different way to customize a JSP. 

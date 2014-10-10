# Overriding Web Resources [](id=overriding-web-resources)

Hooks are commonly used to override web resources, found in the `portal-web`
directory of Liferay's source. You can use a hook to override JSP files, JSPF
files, JavaScript files, CSS files, and images. 

+$$$

**Important:** Some resources have
additional requisites:

- ***JSPF:*** Changes won't take effect unless you modify the JSP that
  includes it. 
- ***CSS:*** When modifying a CSS file imported by another CSS file, the
  changes won't take effect unless you modify the parent CSS file (usually
  `main.css`). 

$$$

Replacing a portal JSP is a simple task with hooks. 

## Replacing a Liferay Portal JSP

Suppose you need to manually override one of Liferay Portal's JSPs, like its
Terms of Use page. You can accomplish this by following these steps: 

1.  Use Liferay IDE to create a new hook project in your Liferay Plugins SDK. 

2.  Open the `liferay-hook.xml` file from your project's `docroot/WEB-INF`
    folder in Liferay IDE. By default, it opens in Overview mode. This mode
    gives you a graphical user interface for developing your hook. You can
    toggle between Overview mode and Source mode via their respective tabs of
    the `liferay-hook.xml` file editor. 

3.  Select the *Custom JSPs* folder from the outline to bring up the custom JSP
    options. Select the check box *Customize Liferay Portal JSPs* and create
    the default custom JSP folder `/META-INF/custom_jsps` by clicking the icon
    that has the three yellow diamonds. 

    ![Figure 1: Liferay IDE lets you specify a folder for the custom JSPs you're developing. Create the folder by clicking the icon that has the three yellow diamonds.](../../images/hook-create-custom-jsps-folder.png)

4.  Add to the listing of custom JSPs by clicking the plus icon and specifying
    the Liferay Portal JSP file that you want to override. Hint: the browse icon
    on the right-hand side in the custom JSP text field simplifies finding
    the JSP you want to customize. It lets you scroll through the accessible
    JSPs and lets you specify key words to narrow your search. 

    ![Figure 2: Liferay IDE simplifies adding a custom JSP to your hook by providing a powerful overview mode for editing your `liferay-hook.xml` file.](../../images/add-jsp-customization-with-hook.png)

    <!-- We'll need to retake this Figure's screenshot as the ability to add a custom JSP using the graphical editor is broken. Jim -->

5.  Open the copy of the Liferay Portal JSP file from the
    `docroot/META-INF/custom_jsps/html/portal/` folder. 

    For example, the custom JSP file copy of Liferay Portal's `terms_of_use.jsp`
    would be made available in the hook project at
    `docroot/META-INF/custom_jsps/html/portal/terms_of_use.jsp`. 

    Modify the JSP file with your customization.

    Note: lots of errors may show in the editor because the resources used in
    the JSP (e.g., `PortalUtil`) are not available in the project; but they'll
    be available from the portal once the hook plugin is deployed to the portal
    server. 

6.  Deploy your hook and wait until it is deployed successfully. 

7.  Navigate to the custom JSP in Liferay and verify your customization.

    For example, in the case of customizing the Terms of Use page, you'd
    create a new user and log in to see your customization.

Now there are two JSP files in Liferay deployed on your application server. One
JSP has the original name and the other ends with `.portal.jsp`. The first file
is your hook's version of the file. The `.portal.jsp` file is a copy of Portal's
original file. 

For example, if the original Portal JSP file was `terms_of_use.jsp`, the hook's
version of the file uses that name and the original version of the file
is renamed `terms_of_use.portal.jsp`.

To revert back to the original, undeploy your hook. Your replacement JSP is
removed, and the `.portal.jsp` file is automatically renamed, taking its place.
You can override any JSP in the Liferay core, while retaining thew ability to
revert your changes easily. Be careful not to override the same JSP in multiple
hooks: you'll get whichever one happened to deploy last. 

Note: we don't recommend changing Liferay's Terms of Use with a hook. You can
replace the Terms of Use with a piece of web content simply by specifying
values for these two properties in `portal-ext.properties`:

    terms.of.use.journal.article.group.id=
    terms.of.use.journal.article.id=

Although this example hook doesn't provide any new functionality, it
demonstrates how to override Liferay's JSP files. 

Congratulations on overriding a Liferay Portal JSP! 

## Related Topics 

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

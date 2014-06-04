# Customizing JSPs by Extending the Original

Let's say you want to preserve your original JSP's content and functionality,
add more to it, and benefit from any changes made to it when you upgrade Liferay. 
Well, you can; simply include the original JSP and then add more content to it. 

For this tutorial, you'll learn how to extend an original JSP using a hook.

## Extending an Original JSP

In this demonstration, you'll customize a hook project to extend a JSP, while 
leaving the orginal JSP untouched . Let's get started! 

1. Open your hook's `liferay-hook.xml` file from your project's
   `docroot/WEB-INF` folder and select the file's *Overview* mode tab.

2. Select the *Custom JSPs* folder from the outline to bring up the custom JSP
   options. Select the checkbox *Customize Liferay Portal JSPs* and create the
   default custom JSP folder `/META-INF/custom_jsps` by clicking the icon that
   has the three yellow diamonds.

    ![Figure 1: Liferay IDE's Hook Configuration menu allows you to create a custom JSP.](../../images/custom-jsp-hook-config.png)

3. Add to the listing of custom JSPs by clicking the plus icon and specifying
   the JSP you'd like to extend. Hint: the browse icon on the right-hand side
   within the custom JSP text field simplifies finding the JSP you want to
   customize. 

4. Click OK and save the `liferay-hook.xml` file. Liferay IDE pulls a copy of
   the Liferay Portal JSP into your project so you can modify it. 

3. Open the JSP file `docroot/META-INF/custom_jsps/[Path_To_JSP]` that
   Liferay IDE pulled into your project. 

4. Make the desired edits to the JSP file and save it.

5. Deploy the hook plugin and view the changes you made in the JSP. 

The edits you made in the `custom_jsps/[Path_To-JSP]` are now visible in your
Liferay Portal. The hook you created extends the original hook in portal with
the version you created. You now know how to extend an original Liferay JSP 
using a hook!

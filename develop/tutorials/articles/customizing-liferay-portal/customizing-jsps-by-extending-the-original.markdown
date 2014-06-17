# Customizing JSPs by Extending the Original

<!-- An ending test plugin can be found at:
https://github.com/liferay/liferay-docs/develop/tutorials/code/plat-fws/extend-jsp/end/extend-original-jsp-hook
-->

Let's say you want to add more content to one of Liferay's JSPs, but you also
want to benefit from any changes made to it as you upgrade Liferay. Rather
than overwriting the JSP, you can instead create a new JSP in a hook and include
the original JSP in the new JSP. 

In this tutorial, you'll learn how to extend an original Liferay JSP using a
hook. 

## Extending a Liferay JSP

In this demonstration, you'll customize a hook project to extend a Liferay JSP,
while leaving the original JSP untouched. Let's get started! 

1. Create a hook project, using Liferay IDE or the Plugins SDK, if you're not
   already using an existing hook project. 

2. Open your hook's `liferay-hook.xml` file from your project's
   `docroot/WEB-INF` folder and select the file's *Overview* mode tab.

3. Select the *Custom JSPs* folder from the outline to bring up the custom JSP
   options. Select the checkbox *Customize Liferay Portal JSPs* and create the
   default custom JSP folder `/META-INF/custom_jsps` by clicking the icon that
   has the three yellow diamonds.

    ![Figure 1: Liferay IDE's Hook Configuration menu allows you to create a custom JSP.](../../images/custom-jsp-hook-config.png)

4. Add to the listing of custom JSPs by clicking the plus icon and specifying
   the JSP you'd like to extend. Hint: the browse icon on the right-hand side
   within the custom JSP text field simplifies finding the JSP you want to
   customize. 

5. Click *OK* and save the `liferay-hook.xml` file. Liferay IDE pulls a copy of
   the Liferay Portal JSP into your project so you can modify it. 

6. Open the JSP file `docroot/META-INF/custom_jsps/[JSP_file's_path]` that
   Liferay IDE pulled into your project and delete its content. 

    Use the `<liferay-util:buffer>` tag, from Liferay's *util* taglib to include
    the original Liferay JSP and assign the JSP's content to a buffer variable.
    Assigning the content to a buffer variable enables you to manipulate the
    original Liferay JSP content as a `String`. 

    For example, you can assign the content of the Liferay JSP to a variable
    named `html`: 

        <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

        <%@ page import="com.liferay.portal.kernel.util.StringUtil" %>

        <liferay-util:buffer var="html">
            <liferay-util:include page="/html/[JSP_file's_path]" />
        </liferay-util:buffer>

    If you use the buffer assignment from the snippet above, make sure you 
    replace `[JSP_file's_path]` with the Liferay JSP's path. Hint: In Liferay
    IDE, the JSP file's path is shown as the *Liferay Portal JSP*
    in the Liferay Hook Configuration's Custom JSPs section in the
    `liferay-hook.xml` editor. 

    **Important:** Add `.portal` to the JSP file's name, before the `.jsp`
    suffix. For example, if the original JSP's name is `search.jsp`, specify
    `search.portal.jsp`. When Liferay deploys your hook, the original JSP is
    renamed to `[file name].portal.jsp`, and your JSP takes its place. If,
    therefore, you want to reference the original, you have to reference its new
    name. 

    Add more content before and/or after the original JSP's content via the
    buffer variable. For example, you can import Liferay's
    [`StringUtil`](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/util/StringUtil.html)
    class and use it to prepend or append content to the buffer variable, like
    this: 

        <%
        html = StringUtil.add(
            "Stuff I'm adding BEFORE the original content",
            html,
            "\n");

        html = StringUtil.add(
            html,
            "Stuff I'm adding AFTER the original content",
            "\n");
        %>

    After adding your desired content to the original content, evaluate the
    buffer variable as an expression. For example, to evaluate a buffer variable
    named `html` you enter:

        <%= html %>

    Save your changes. 

7. Deploy the hook plugin and view the changes you made in the JSP. 

The edits you made in the `custom_jsps/[JSP_file's_path]` are now visible in
your Liferay Portal. In Liferay Portal, The hook you created extends the
original JSP's content with the version of the content you created. You now know
how to extend an original Liferay JSP using a hook! 


# Customizing JSPs by Extending the Original [](id=customizing-jsps-by-extending-the-original)

If we can replace a JSP with a hook plugin, why learn another way to accomplish
the same thing? Good question. Let's say you want to preserve the original JSP's
content and functionality, but you want to add more to the JSP. And when you
upgrade Liferay, you want to benefit from any changes made to that upgraded JSP.
Well, you can; simply include the original JSP and then add more stuff to it. 

Here's an example that customizes the search page of the Blogs portlet. Specifically, it
adds helpful text to aid the user in searching for content. Since this technique
involves string manipulation, it's mainly useful for making a small number of
changes to a JSP.

1.  Use the hook project we created earlier or create a new hook project. 

2.  Open the `liferay-hook.xml` file from your project's `docroot/WEB-INF`
    folder in Liferay IDE and select the file's *Overview* mode tab.  

3.  Select the *Custom JSPs* folder from the outline to bring up the custom JSP
    options. Select the checkbox *Customize Liferay Portal JSPs* and create
    the default custom JSP folder `/META-INF/custom_jsps` by clicking the icon
    that has the three yellow diamonds. 

    Add to the listing of custom JSPs by clicking the plus icon and specifying
    Portal's `html/portlet/blogs/search.jsp` file. Hint, the browse icon on the
    right-hand side within the custom JSP text field simplifies finding the JSP
    you want to customize. 

    Click OK and save the `liferay-hook.xml` file. Liferay IDE pulls a copy of
    the Liferay Portal JSP into your project so you can modify it. 

4.  Open the JSP file `docroot/META-INF/custom_jsps/html/blogs/search.jsp` that
    Liferay IDE pulled into your project. 

5.  Replace the JSPs code with the following: 

        <%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
        
        <%@ page import="com.liferay.portal.kernel.util.StringUtil" %>
        
        <liferay-util:buffer var="html">
            <liferay-util:include page="/html/portlet/blogs/search.portal.jsp" />
        </liferay-util:buffer>
        
        <%
        html = StringUtil.add(
            html,
            "Didn't find what you were looking for? Refine your search and " +
                "try again!",
            "\n");
        %>
        
        <%= html %>

    Notice how this code assigns the original JSP's HTML content to the variable
    `html`. We proceed to add some more content of our own to that HTML and then
    display it. 

6.  Deploy the hook plugin and add the Blogs portlet to a page. 

7.  Add a blog and then use the Blog portlet's search. 

Your custom message now shows below the search results. 

Next, we'll explore application adapters and what they can do for your sites and
site templates.

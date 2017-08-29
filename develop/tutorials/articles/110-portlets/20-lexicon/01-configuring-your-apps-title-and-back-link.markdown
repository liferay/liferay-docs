# Configuring Your Application's Title and Back Link [](id=configuring-your-applications-title-and-back-link)

For @product-ver@ administration applications, the title should be moved to the 
inner views of the app, and the associated back link should be moved to the 
portlet titles. 

If you open the Blogs Admin application in the control panel and add a new blog 
entry, you'll see this behavior in action:

![Figure 1: Adding a new blog entry displays the portlet title at the top, along with a back link.](../../../images/new-blog-entry-title.png)

This tutorial uses the Blogs Admin application's [`edit_entry.jsp`](https://github.com/liferay/liferay-portal/blob/b74496b5c450c134957347e7ebabd25dec1c763d/modules/apps/collaboration/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp)
as an example. 

Follow these steps to configure your app's title and back URL:

1.  Inside of a Java scriplet, Use `ParamUtil` to retrieve the redirect for the 
    URL:

        <%
        String redirect = ParamUtil.getString(request, "redirect");

2.  Display the back icon and set the back URL to the `redirect`:

        portletDisplay.setShowBackIcon(true);
        portletDisplay.setURLBack(redirect);

3.  Finally, set the title using the `renderResponse.setTitle()` method, as 
    shown in the example configuration below:

        renderResponse.setTitle((entry != null) ? entry.getTitle() : 
        LanguageUtil.get(request, "new-blog-entry"));
        %>

The example above provides a title for two scenarios. If an existing blog entry 
is being updated, the blog's title is displayed, otherwise it defaults to 
*New Blog Entry* since a new blog entry is being created. 
    
You should also update any back links in the view to use the `redirect`. For 
example the `edit_entry.jsp` form's cancel button redirects the user:

    <aui:button cssClass="btn-lg" href="<%= redirect %>" name="cancelButton" 
    type="cancel" />

Now you know how to configure your app's title and back URL!

## Related topics

[Applying Lexicon Patterns to your Forms, Navigation, and Search](/develop/tutorials/-/knowledge_base/7-0/applying-lexicon-patterns-to-forms-navigation-and-search)

[Setting Search Container Animations](/develop/tutorials/-/knowledge_base/7-0/setting-search-container-animations)

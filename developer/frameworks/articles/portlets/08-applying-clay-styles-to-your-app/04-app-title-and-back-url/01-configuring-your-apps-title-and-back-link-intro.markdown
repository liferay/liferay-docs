---
header-id: configuring-your-applications-title-and-back-link
---

# Configuring Your Application's Title and Back Link

[TOC levels=1-4]

For administration applications, the title should be moved to the inner views of 
the app and the associated back link should be moved to the portlet titles. If 
you open the Blogs Admin application in the Control Panel and add a new blog 
entry, you'll see this behavior in action:

![Figure 1: Adding a new blog entry displays the portlet title at the top, along with a back link.](../../../../images/new-blog-entry-title.png)

The Blogs Admin application is used as an example throughout this article. 
Follow these steps to configure your app's title and back URL:

1.  Use `ParamUtil` to retrieve the redirect for the URL:

    ```java
    String redirect = ParamUtil.getString(request, "redirect");
    ```

2.  Display the back icon and set the back URL to the `redirect`:

    ```java
    portletDisplay.setShowBackIcon(true);
    portletDisplay.setURLBack(redirect);
    ```

3.  Finally, set the title using the `renderResponse.setTitle()` method. The 
    example below provides a title for two scenarios: 

    - If an existing blog entry is being updated, the blog's title is displayed.
    - Otherwise it defaults to *New Blog Entry* since a new blog entry is being 
      created. 

    ```java
    renderResponse.setTitle((entry != null) ? entry.getTitle() : 
    LanguageUtil.get(request, "new-blog-entry"));
    %>
    ```

4.  Update any back links in the view to use the `redirect`. The Blog Admin 
    app's `edit_entry.jsp` form's cancel button is shown below as an example:

    ```java
    <aui:button cssClass="btn-lg" href="<%= redirect %>" name="cancelButton" 
    type="cancel" />
    ```

Great! Now you know how to configure your app's title and back URL. 

## Related Topics

- [Applying Clay Patterns to Your Navigation Bar](/docs/7-2/frameworks/-/knowledge_base/f/applying-clay-patterns-to-navigation)
- [Setting Empty Results Messages](/docs/7-2/frameworks/-/knowledge_base/f/setting-empty-results-messages)

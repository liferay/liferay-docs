---
header-id: implementing-the-card-view
---

# Implementing the Card View

[TOC levels=1-4]

The card view displays the entry's information in a vertical or horizontal card 
with an image, user profile, or an icon representing the content's type, along 
with details about the content, such as its name, workflow status, and a 
condensed description.

See the 
[Liferay Frontend Cards](/docs/7-2/reference/-/knowledge_base/r/liferay-front-end-cards) 
reference for examples and use cases of each card. 

![Figure 1: The Management Toolbar's card view gives a quick summary of the content's description and status.](../../../../../images/clay-taglib-management-toolbar-view-type-card.png)

Follow the steps below to create your card view:

1.  Inside the `<c:choose>` conditional block, add a condition for the icon 
    display style (Card view type):
    
    ```markup
    <c:when test='<%= Objects.equals(displayStyle, "icon") %>'>
        <%-- card view type configuration goes here --%>
    </c:when>
    ```

2.  Add the proper java scriplet to make the card view responsive to different 
    devices:

    Use the pattern below for vertical cards:

    ```java
    <%
    row.setCssClass("col-md-2 col-sm-4 col-xs-6");
    %>
    ```
    
    For horizontal cards use the following pattern:

    ```java  
    <%
    row.setCssClass("col-md-3 col-sm-4 col-xs-12");
    %>
    ```

3.  Add the search container column text containing your card. The card should 
    include the actions for the entry(if applicable), as well as an image, icon 
    or user profile, and the entry's title. An example configuration is shown 
    below:

    ```markup
    <liferay-frontend:icon-vertical-card
      actionJsp='<%= dlPortletInstanceSettingsHelper.isShowActions() ? 
      "/image_gallery_display/image_action.jsp" : StringPool.BLANK %>'
      actionJspServletContext="<%= application %>"
      cssClass="entry-display-style"
      icon="documents-and-media"
      resultRow="<%= row %>"
      title="<%= dlPortletInstanceSettingsHelper.isShowActions() ? 
      fileEntry.getTitle() : StringPool.BLANK %>"
    />
    ```

Great! Now you know how to implement the Card view! 

## Related Topics

- [Configuring the Clay Management Toolbar Taglib](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
- [Filtering and Sorting Items with the Management Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/filtering-and-sorting-items-with-the-management-toolbar)

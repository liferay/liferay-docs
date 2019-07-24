---
header-id: implementing-the-list-view
---

# Implementing the List View

[TOC levels=1-4]

The list view displays the entry's complete description, along with a small icon 
for the content type, and its name. 

![Figure 1: The Management Toolbar's list view gives the content's full description.](../../../../../images/clay-taglib-management-toolbar-view-type-list.png)

Follow these steps to configure the List view:

1.  Inside the `<c:choose>` conditional block, add a condition for the 
    descriptive display style (list view type):

    ```markup
    <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
        <%-- list view type configuration goes here --%>
    </c:when>
    ```

2.  Follow the example below to add the three columns to the conditional block:

Column | Content Options | Example
------------- | ------------- | -------------
1 | icon | &lt;liferay-ui:search-container-column-icon/&gt;
&nbsp; | image | &lt;liferay-ui:search-container-column-image/&gt;
&nbsp;  | user portrait | &lt;liferay-ui:search-container-column-text&gt;<br/>&nbsp;&nbsp;&lt;liferay-ui:user-portrait/&gt;<br/>&lt;/liferay-ui:search-container-column-text&gt;
2 | description | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;colspan="<%=2%>" <br/>><br/>&nbsp;&nbsp;&lt;h5&gt;<%= userGroup.getName() %>&lt;/h5&gt; <br/>&nbsp;&nbsp;&lt;h6 class="text-default"&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt;<%= userGroup.getDescription() %>&lt;/span&gt; <br/>&nbsp;&nbsp;&lt;/h6&gt; <br/>&nbsp;&nbsp;&lt;h6 class="text-default"&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt; <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<liferay-ui:message arguments="<%= usersCount%>" key="x-users"/> <br/>&nbsp;&nbsp;&nbsp;&nbsp;&lt;/span&gt; <br/>&nbsp;&nbsp;&lt;/h6&gt; <br/></liferay-ui:search-container-column-text> 
3 | actions | <liferay-ui:search-container-column-jsp<br/> &nbsp;&nbsp;path="/edit_team_assignments_user_groups_action.jsp"<br/>/>

Great! Now you know how to implement the List view! 

## Related Topics

- [Configuring the Clay Management Toolbar Taglib](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
- [Filtering and Sorting Items with the Management Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/filtering-and-sorting-items-with-the-management-toolbar)

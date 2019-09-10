---
header-id: implementing-the-table-view
---

# Implementing the Table View

[TOC levels=1-4]

The table view list the search container columns from left to right. 

![Figure 1: The Management Toolbar's table view list the content's information in individual columns.](../../../../../images/clay-taglib-management-toolbar-view-type-table.png)

Follow these steps to configure the Table view:

1.  Inside the `<c:choose>` conditional block, add a condition for the list 
    display style (table view type):

    ```markup
    <c:when test='<%= Objects.equals(displayStyle, "list") %>'>
      <%-- table view type configuration goes here --%>
    </c:when>
    ```

2.  Follow the example below to add the required columns to the conditional 
    block:

Column | Content Options | Example
------------- | ------------- | -------------
1 | name | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;cssClass="content-column name-column title-column" <br/>&nbsp;&nbsp;name="name" <br/>&nbsp;&nbsp;truncate="<%= true %>" <br/>&nbsp;&nbsp;value="<%= rule.getName(locale) %>" <br/>/>
2 | description | <liferay-ui:search-container-column-text <br/>&nbsp;&nbsp;cssClass="content-column description-column" <br/>&nbsp;&nbsp;name="description" <br/>&nbsp;&nbsp;truncate="<%= true %>" <br/>&nbsp;&nbsp;value="<%= rule.getDescription(locale) %>" <br/>/>
3 | create date | <liferay-ui:search-container-column-date <br/>&nbsp;&nbsp;cssClass="create-date-column text-column" <br/>&nbsp;&nbsp;name="create-date" <br/>&nbsp;&nbsp;property="createDate" <br/>/>
4 | actions | <liferay-ui:search-container-column-jsp <br/>&nbsp;&nbsp; cssClass="entry-action-column" <br/>&nbsp;&nbsp;path="/rule_actions.jsp" <br/>/>

Great! Now you know how to implement the Table view! 

## Related Topics

- [Configuring the Clay Management Toolbar Taglib](/docs/7-2/reference/-/knowledge_base/r/clay-management-toolbar)
- [Filtering and Sorting Items with the Management Toolbar](/docs/7-2/frameworks/-/knowledge_base/f/filtering-and-sorting-items-with-the-management-toolbar)

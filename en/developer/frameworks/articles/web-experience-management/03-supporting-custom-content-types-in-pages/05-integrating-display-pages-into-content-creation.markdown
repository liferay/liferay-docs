---
header-id: integrating-display-pages-into-content-creation
---

# Integrating Display Pages into Content Creation

[TOC levels=1-4]

After you add support for Display Pages in your custom entities, you can
integrate display page configuration into your entity's creation form.

## Display Page Taglib Example

To provide the Display Page selector for the User type after you 
[created fields for it](/docs/7-2/frameworks/-/knowledge_base/f/specifying-the-fields-of-a-custom-content-type),

1.  Open your JSP used for displaying the editing interface (e.g.,
    `.../META-INF/resources/.../edit_entry.jsp`).

2.  Add this code in the appropriate place in the layout to add the Display Page
    selector:

    ```markup
    <liferay-asset:select-asset-display-page
        classNameId="<%= PortalUtil.getClassNameId(User.class) %>"
      	classPK="<%= userId %>"
      	groupId="<%= scopeGroupId %>"
    />
    ```

Now, a selector is available to define a default Display Page when editing or
creating a User.

![Figure 1: You need to add the Display Page selection to your content type's create/edit page to define the Display Page for each instance of that asset.](../../../images/display-pages-select-default-display-page.png)

Awesome! Your custom content type is now available for Content Pages and/or
Display Page Templates.

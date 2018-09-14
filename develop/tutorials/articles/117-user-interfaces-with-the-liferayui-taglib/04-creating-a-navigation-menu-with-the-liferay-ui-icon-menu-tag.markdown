# Creating a Navigation Menu With the Liferay UI Icon Menu Tag [](id=organizing-icons-with-the-liferay-ui-icon-menu-tag)

Liferay contains some standard navigation elements that are used throughout its
interface. You can use the same elements, because they are encapsulated into
tags that build those elements for you. Liferay UI provides the Icon Menu tag to
create a pop-up navigation menu. 

This tutorial covers how to configure and use the `liferay-ui:icon-menu` tag.

Now that you know what the icon menu can do for you, it's time to see how to use
it. 

## Setting Up the Liferay UI Icon Menu Tag [](id=setting-up-the-liferay-ui-icon-menu-tag)

The `liferay-ui:icon-menu` tag takes a list of child components and renders them
in a menu. The example below shows how to add and use the `liferay-ui:icon-menu` 
tag in the `view.jsp` of a portlet:

- **Step 1:** Reference the liferay-ui Taglib.
- **Step 2:** Configure the icon-menu Tags in the View JSP.
- **Step 3:** Insert and Configure the liferay-ui:icon Tags inside of the icon-menu Tags.
 	
Go through each of these steps to create menus in your application.

### Step 1: Reference the liferay-ui Taglib [](id=step-1-reference-the-liferay-ui-taglib)

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add a directive to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

You can now use the liferay-ui tags in your portlet! Onward to step 2.

### Step 2: Configure the icon-menu Tags in the View JSP [](id=step-2-configure-the-icon-menu-tags-in-the-view-jsp)

Inside your portlet's `view.jsp`, add the `liferay-ui:icon-menu` tags at the
bottom:

        <liferay-ui:icon-menu>

        </liferay-ui:icon-menu>

You'll place all your icons for your menu inside the tags you just added.
Onward to step 3!

### Step 3: Insert and Configure the liferay-ui:icon Tags inside of the icon-menu Tags [](id=step-3-insert-and-configure-the-liferay-uiicon-tags-inside-of-the-icon-menu)

In this step you'll add the icons that you wish to display in your icon menu.

Still inside the `view.jsp` nest the `liferay-ui:icon` tags inside the 
`liferay-ui:icon-menu` tags:

        <liferay-ui:icon-menu>
            <liferay-ui:icon iconCssClass="icon-user" message="Use" url="www.liferay.com" />
            <liferay-ui:icon iconCssClass="icon-film" message="Film" url="www.liferay.com" />
            <liferay-ui:icon iconCssClass="icon-edit" message="Edit" url="www.liferay.com" />
            <liferay-ui:icon iconCssClass="icon-trash" message="Trash" url="www.liferay.com" />
        </liferay-ui:icon-menu>
 
Repeat the step above to create as many icons as you need for your navigation 
menu. It's important to note that the icons must have their `url` attribute 
configured in order to render properly in the icon menu. The settings above 
create a default menu with default attributes. You can configure the 
available attributes for the icon menu tag to give your menu more of a custom 
flare. The list below gives some of the available attributes along with how to 
configure them. You can view the full list of attributes [here](http://docs.liferay.com/portal/6.2/taglibs/liferay-ui/icon-menu.html). 

**direction:** Sets the direction the menu pops up relative to the button that 
triggers the icon menu. The default value is `left`. Possible values are `left,`
`right,` `up,` or `down.`

**maxDisplayItems:** Sets the number of icons to display in the visible area of
the menu before scrolling. The default value is `15.`

**message:** Sets the text to be displayed on the button trigger for the icon
menu. The default value is `actions.`

**showArrow:** Sets whether to display a arrow on the button trigger to indicate
a pop-up menu. The default value is `true.`

**useIconCaret:** Sets whether to point the arrow on the button trigger towards
the direction set by the `direction` attribute. The default value is `false.`

With the settings above, your icon menu should look like the figure below:

![Figure 1: Setting up an icon menu is a piece of cake.](../../images/icon-menu.png)

As you can see, the `liferay-ui:icon-menu` tag is easy to use.

## Related Topics [](id=related-topics)

 [Using Liferay UI Tabs and Sections](/develop/tutorials/-/knowledge_base/6-2/using-liferay-ui-tabs-and-sections)
 
 [Creating a Navigation Menu with the Liferay UI Icon List Tag](/develop/tutorials/-/knowledge_base/6-2/creating-a-navigation-menu-with-the-liferay-uiicon-list-tag)
 
 [Using Liferay-UI:Success and Liferay-UI:Error Messages](/develop/tutorials/-/knowledge_base/6-2/using-liferay-uisuccess-and-liferay-uierror-message)

# Organizing Icons With the Liferay UI Icon List Tag

How you organize the content in your site can have a huge impact on how people 
interact with your site. And a bad design will drive away traffic from your site.
Bad design can ruin even the best of content, causing people to run screaming
to the hills from your site to warn the other villagers of the monster you
created. This is an overexaggeration of course, but the point is that your
site's design should be taken seriously, down to the tinyiest detail.

One important aspect of design is how your icons are layed out. The placement
and layout of your site's icons can play a big part in how your site is
percieved. Liferay provides a easy to use tag, from the liferay-ui tag library,
allowing you to organize your icons with ease: `liferay-ui:icon-list` tag.

The Liferay UI Icon List tag allows you to, as the name suggests, create an
unordered list of your icons. Liferay UI also provides the `liferay-ui:icon-menu`
tag for organizing your icons in a drop-down menu which you can read more about
 [here](http://dev.liferay.com/tutorials/-/knowledge_base/6-2/organizing-icons-with-the-liferay-ui-icon-menu-tag). 

This tutorial covers how to configure and use the `liferay-ui:icon-list` tag.

By the end of this tutorial you should be able to keep the torch carrying mob at
bay, at least when it comes to the icon layout of your app and site design.

## Setting Up the Liferay UI Icon List Tag

The example below shows how to add and use the `liferay-ui:icon-list` tag in the 
`view.jsp` of a portlet:

- **Step 1:** Reference the liferay-ui Taglib.
- **Step 2:** Configure the icon-list Tags in the View JSP.
- **Step 3:** Insert and Configure the liferay-ui:icon Tags inside of the icon-list Tags.
 	
Go through each of these steps to get your list going.

### Step 1: Reference the liferay-ui Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add a directive to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

You can now use the liferay-ui tags in your portlet! Onward to step 2. I can
already hear the unruly mob starting to head back to the village.

### Step 2: Configure the icon-list Tags in the View JSP

Inside the `view.jsp` of your portlet add the `liferay-ui:icon-list` tags at 
the bottom:

        <liferay-ui:icon-list>

        </liferay-ui:icon-list>

You'll place all of your icons for your list inside of the tags you just added.
Onward to step 3!

### Step 3: Insert and Configure the liferay-ui:icon Tags inside of the icon-list Tags

In this step you'll add the icons that you want to list.

Still inside the `view.jsp` nest the `liferay-ui:icon` tags inside of the 
`liferay-ui:icon-list` tags:

        <liferay-ui:icon-list>
            <liferay-ui:icon image="status_online" message="Sign In" url="www.liferay.com"/>
            <liferay-ui:icon image="edit" message="edit" url="www.liferay.com"/>
            <liferay-ui:icon image="add_article" message="Add Article" url="www.liferay.com"/>
        </liferay-ui:icon-list>
        
Repeat the step above to create as many icons as you need for your list.
Following the steps above, your finished icon list should look like the figure
below:

![Figure 1: With the configuration above your icon list should look like this.](../../images/icon-list-01.png)

The figure below shows how Liferay Portal uses an icon list to create a 
navigation menu for the sign in portlet:

![Figure 2: The Liferay UI Icon List tag allows you to create a basic navigation with ease.](../../images/icon-list-02.png)

There you have it. You can rest easy now, knowing that the villagers will enjoy
your sites icon layout.

## Related Topics

 [Organizing Icons with the Liferay UI Icon Menu Tag](/tutorials/-/knowledge_base/6-2/organizing-icons-with-the-liferay-ui-icon-menu-tag)

 [Using Liferay UI Tabs and Sections](/tutorials/-/knowledge_base/6-2/using-liferay-ui-tabs-and-sections)

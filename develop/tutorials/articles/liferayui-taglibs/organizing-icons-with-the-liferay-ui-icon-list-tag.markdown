# Organizing Icons With the liferay-ui:icon-list Tag

How you organize the content in your site can have a huge impact on how people 
interact with it. A bad design can drive traffic away from your site. Bad design 
can ruin even the best of content, causing people to run screaming from your 
site to warn the other villagers of the monster you created. This is an 
exaggeration of course, but the point is that your site's design should be taken 
seriously, down to the tiniest detail.

One important aspect of design is how your icons are laid out. The placement and 
layout of your site's icons can play a big part in how your site is perceived. 
Liferay UI provides a easy to use tag that allows you to organize your icons 
with ease: `liferay-ui:icon-list`. In fact, Liferay's Sign In portlet makes use 
of this tag to create a list of clickable icons:

![Figure 1: Liferay's Sign In portlet uses a Liferay UI icon list.](../../images/icon-list-02.png)

As its name suggests, the `liferay-ui:icon-list` tag allows you to create an 
unordered list of your icons. Liferay UI also provides the `liferay-ui:icon-menu`
tag for organizing your icons in a drop-down menu. You can read more about this 
tag [here](http://dev.liferay.com/tutorials/-/knowledge_base/6-2/organizing-icons-with-the-liferay-ui-icon-menu-tag). 

This tutorial covers how to use the `liferay-ui:icon-list` tag to organize your 
icons. By the end of this tutorial you should be able to keep the torch carrying 
mob at bay, at least when it comes to the icon layout of your apps.

## Setting Up the liferay-ui:icon-list Tag

The example in this tutorial shows how to add and use the `liferay-ui:icon-list` 
tag in the `view.jsp` of a portlet by using the following steps:

- **Step 1:** Reference the liferay-ui Taglib.
- **Step 2:** Add the liferay-ui:icon-list Tags in the View JSP.
- **Step 3:** Insert and Configure the liferay-ui:icon Tags inside of the liferay-ui:icon-list Tags.

Go through each of these steps to get your icon list going!

### Step 1: Reference the liferay-ui Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add the following directive to reference the `liferay-ui` taglib:

        <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

You can now use Liferay UI tags in your portlet! Next, you'll add the 
`liferay-ui:icon-list` tag to your portlet. It sounds like the unruly mob is 
already starting to head back to the village!

### Step 2: Add the liferay-ui:icon-list Tags in the View JSP

Next, add the `liferay-ui:icon-list` tags to the bottom of your portlet's 
`view.jsp`. The example here shows these tags:

        <liferay-ui:icon-list>

        </liferay-ui:icon-list>

In the next step, you'll place all of the icons in your list inside of the tags 
you just added.

### Step 3: Insert and Configure the liferay-ui:icon Tags inside of the liferay-ui:icon-list Tags

In this step you'll add the icons that you want to list. Still inside the 
`view.jsp`, nest the `liferay-ui:icon` tags inside of the `liferay-ui:icon-list` 
tags. There are a few attributes for the `liferay-ui:icon` tag that you should 
take note of. One such attribute is `image`. The `image` attribute tells Liferay 
what image to use for the icon. For example, if the value of `image` is 
`"status_online"`, then Liferay uses its default icon for a user that is online. 
Next is the `message` attribute. This attribute defines the text to use 
alongside the icon. For example, if you set the value of `message` to 
`"Sign In"`, then that text appears next to the icon. Last but certainly not 
least is the `url` attribute. As you might have guessed, this attribute defines 
the URL to go to when the icon is clicked. Here's an example of an icon list 
with three icons that use these attributes:

        <liferay-ui:icon-list>
            <liferay-ui:icon image="status_online" message="Sign In" url="www.liferay.com"/>
            <liferay-ui:icon image="edit" message="edit" url="www.liferay.com"/>
            <liferay-ui:icon image="add_article" message="Add Article" url="www.liferay.com"/>
        </liferay-ui:icon-list>

Of course, you can add as many icons as you need to your list. The figure here 
shows the icon list created by the above code:

![Figure 2: An icon list with three icons.](../../images/icon-list-01.png)

There you have it! You can rest easy now, knowing that the villagers will enjoy
your site's icon layout.

## Related Topics

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Themes and Layout Templates](/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

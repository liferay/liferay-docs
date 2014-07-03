# Exposing the Permission Interface to Users 

Imagine that you bought a really nice electric guitar. Now imagine that you 
bought an even nicer amplifier to go along with it. You get them home, and much 
to the chagrin of your neighbors, you get ready to rock! However, your 
neighbors' hearing is saved. You forgot to get a cable to plug the guitar into 
the amplifier; bummer. This is a lot like what happens when you add permissions 
to your portlet but don't expose them to users.

On the portlet level, no code needs to be written in order to have the
permission system work for your custom portlet. If you've defined any custom
permissions (supported actions) in your configuration file's `portlet-resource`
tag, they're automatically added to a list of permissions in Liferay's
permissions UI. What good, however, are permissions that are available but can't
be set by users? 

This tutorial covers exposing your portlet permissions to users. Get ready to 
rock! 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** The Liferay Blogs portlet is 
  used in this tutorial to illustrate exposing permissions to users. You can 
  find it in Liferay's source code on [Github](https://github.com/liferay/liferay-portal).

---

## Adding Permissions 

To let a user set permissions on model resources, you must expose the permission
interface to the user. Just add these two Liferay UI tags to your JSP:

1.  `<liferay-security:permissionsURL>`: Returns a URL to the permission
    settings configuration page. 

2. `<liferay-ui:icon>`: Shows an icon to the user. These are defined in the
    theme, and one of them (see below) is used for permissions. 

This example demonstrates the use of both tags; it comes from the
`view_entry_content.jspf` file of Liferay's Blogs portlet. 

    <liferay-security:permissionsURL
        modelResource="<%= BlogsEntry.class.getName() %>"
        modelResourceDescription="<%= entry.getTitle() %>"
        resourcePrimKey="<%= entry.getPrimaryKey().toString() %>"
        var="entryURL"
    />

    <liferay-ui:icon image="permissions" url="<%= entryURL %>" />

For the first tag, specify the following attributes: 

-   `modelResource`: The fully qualified Java object class name. This class name
    gets translated into its more readable name as specified in
    `Language.properties`. 
-   `modelResourceDescription`: You can pass in anything that best describes
    this model instance. In this example, the blogs title was passed in. 
-   `resourcePrimKey`: The primary key of your model instance. 
-   `var`: Specifies the name of the variable to which the resulting URL string
    will be assigned. The variable is then passed to the `<liferay-ui:icon>` tag
    so the permission icon has the proper URL link.

There's an optional attribute called `redirect` that's available if you want to
override the default behavior of the upper right arrow link. That's it; now your
users can configure the permission settings for model resources!

## Next Steps 

<!-- Change link once header id is generated -->

<!--
[Checking Permissions](/tutorials/-/knowledge_base/checking-permissions-lp-6-2-develop-tutorial) 
-->

[Service Builder](/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)

[Localization](/tutorials/-/knowledge_base/localization-lp-6-2-develop-tutorial)

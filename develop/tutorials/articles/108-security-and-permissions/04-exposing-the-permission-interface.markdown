# Exposing the Permission Interface to Users [](id=exposing-the-permission-interface-to-users)

Imagine that you bought a really nice electric guitar. Now imagine that you
bought an even nicer amplifier to go along with it. You get them home, and much
to the chagrin of your neighbors, you get ready to rock! Much to *your*
chagrin, however, your neighbors' ears are saved. You forgot to get a cable to
plug the guitar into the amplifier. Bummer. This is essentially what happens
when you add permissions to your portlet but don't expose the UI to users: the
permissions exist but they're not configurable.

On the portlet level, no code needs to be written in order to have the
permission system work for your portlet. If you've defined any permissions
(supported actions) in your configuration file's `portlet-resource` tag, they're
automatically added to a list of permissions that are displayed by Liferay's
permissions UI. What good, however, are permissions that exist but can't be
configured? 

This tutorial covers exposing your portlet permissions to users. Get ready to 
rock! 

+$$$

**Note:** The Guestbook project is used in this tutorial to illustrate exposing
permissions to users. You can find it in the Liferay Docs repository here:
[Liferay Guestbook project](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/learning-paths/mvc/code/learning-sdk/portlets/guestbook-portlet).

$$$

## Adding Permissions [](id=adding-permissions)

To allow permissions to be configured for model resources, you must add the
permissions interface to the UI. Just add these two Liferay UI tags to your JSP:

1. `<liferay-security:permissionsURL>`: Returns a URL to the permission settings
   configuration page. 

2. `<liferay-ui:icon>`: Shows an icon to the user. These are defined in the
   theme and one of them (see below) is used for permissions. 

This example demonstrates the use of both tags; it comes from the
`guestbook_actions.jsp` file of the Guestbook portlet. 

    <liferay-security:permissionsURL
        modelResource="<%= Entry.class.getName() %>"
        modelResourceDescription="<%= entry.getMessage() %>"
        resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
        var="permissionsURL" />

    <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

For the `<liferay-security:permissionsURL />` tag, specify these attributes: 

`modelResource`: The fully qualified class name of the entity class. This
class name gets translated into a more readable name as specified in
`Language.properties`. 

`Language.properties`: The entity class in the example above is the guestbook
entry class for which the fully qualified class name is
`com.liferay.docs.guestbook.model.Entry`.

`modelResourceDescription`: You can enter anything that best describes this
model instance. In the example above, the guestbook entry message is used for
the model resource description. 

`resourcePrimKey`: The primary key of your entity. 

`var`: Specifies the name of the variable to which the resulting URL string
is assigned. The variable is then passed to the `<liferay-ui:icon>` tag
so the permission icon has the proper URL link.

There's an optional attribute called `redirect` that's available if you want to
override the default behavior of the upper right arrow link. That's it; now your
users can configure the permission settings for model resources!

## Related Topics [](id=related-topics)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

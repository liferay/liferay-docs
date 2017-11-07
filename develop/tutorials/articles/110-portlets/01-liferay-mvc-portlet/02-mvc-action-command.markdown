# MVC Action Command [](id=mvc-action-command)

Liferay's MVC framework lets you split your portlet's action methods into 
separate classes. This can be very helpful in portlets that have many actions. 
Each action URL in your portlet's JSPs then calls the appropriate action class 
when necessary. 

First, use the `<portlet:actionURL>` tag to create the action URL in your JSP. 
For example, the edit blog entry action in Liferay's Blogs app is defined in the 
[`edit_entry.jsp` file](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp) 
as follows: 

    <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />

When the action URL is triggered, the matching action class processes the
action. Implement the action by creating a class that implements the
[`MVCActionCommand`
interface](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html).
To avoid writing oodles of boilerplate code, your `*MVCActionCommand` class
should extend the [`BaseMVCActionCommand`
class](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html)
instead of implementing `MVCActionCommand` directly. The `BaseMVCActionCommand`
class already implements `MVCActionCommand` and provides many useful method
implementations. Naming your `*MVCActionCommand` class after the action it
performs is a good convention. For example, if your action edits some kind of
entry, you could name its class `EditEntryMVCActionCommand`. 

Your `*MVCActionCommand` class must also have a `@Component` annotation like the 
following. Set the property `javax.portlet.name` to your portlet's internal 
ID, and the property `mvc.command.name` to the value of the `name` property in 
your JSP's matching `actionURL`. To register the component in the OSGi container 
as using the `MVCActionCommand` class, you must set the `service` property to 
`MVCActionCommand.class`: 

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=your_portlet_name_YourPortlet",
            "mvc.command.name=/your/jsp/action/url"
        },
        service = MVCActionCommand.class
    )
    public class YourMVCActionCommand extends BaseMVCActionCommand {
        // implement your action
    }

For example, this is the `@Component` annotation for the Blogs app's 
[`EditEntryMVCActionCommand` class](https://github.com/liferay/liferay-portal/blob/7.0.2-ga3/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/EditEntryMVCActionCommand.java):

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
            "mvc.command.name=/blogs/edit_entry"
        },
        service = MVCActionCommand.class
    )
    public class EditEntryMVCActionCommand extends BaseMVCActionCommand {
        // the app's edit blog entry action implementation
    }

Note that you can use multiple `javax.portlet.name` values to indicate the 
component works with multiple portlets. 

In your `*MVCActionCommand` class, process the action by overriding the 
`BaseMVCActionCommand` class's
[`doProcessAction` method](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html#doProcessAction-javax.portlet.ActionRequest-javax.portlet.ActionResponse-). 
This method takes `javax.portlet.ActionRequest` and `javax.portlet.ActionResponse` parameters that you can 
use to process your action. Your `*MVCActionCommand` class should also contain 
any other code required to implement your action. For a real-world example of a
`*MVCActionCommand` class, see the Blogs app's `EditEntryMVCActionCommand`
class. 

## Related Topics [](id=related-topics)

[MVC Render Command](/develop/tutorials/-/knowledge_base/7-0/mvc-render-command)

[MVC Resource Command](/develop/tutorials/-/knowledge_base/7-0/mvc-resource-command)

[MVC Command Overrides](/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands)

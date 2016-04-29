# MVC Action Command 
<!--Liferay's MVCActionCommand-->

Liferay's MVC framework lets you split your portlet's action methods into 
separate classes. This can be very helpful in portlets that have many actions. 
Each Action URL in your portlet's JSPs then calls the appropriate action class 
when necessary. 

You can implement each action by creating a class that implements the 
[`MVCActionCommand` interface](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html) 
and is suffixed with `MVCActionCommand`. To avoid writing oodles of boilerplate 
code, your `*MVCActionCommand` class should extend the 
[`BaseMVCActionCommand` class](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html) 
instead of implementing `MVCActionCommand` directly. The `BaseMVCActionCommand` 
class already implements `MVCActionCommand` and provides many useful method 
implementations. Also, you should name your `*MVCActionCommand` class after the 
action it performs. For example, if your action edits some kind of entry, you 
should name its class `EditEntryMVCActionCommand`. 

If you're using Liferay's MVC framework, it's as simple as creating a portlet
class with your Component:

    @Component(
        immediate = true,
        property = {
            "com.liferay.portlet.css-class-wrapper=portlet-greeter",
            "com.liferay.portlet.display-category=category.sample",
            "com.liferay.portlet.instanceable=true",
            "javax.portlet.display-name=Greeter Portlet",
            "javax.portlet.init-param.template-path=/",
            "javax.portlet.init-param.view-template=/view.ftl",
            "javax.portlet.security-role-ref=power-user,user"
        },
        service = Portlet.class
    )
    public class GreeterPortlet extends FreeMarkerPortlet {
        ...
    }

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=blade_portlet_GreeterPortlet",
            "mvc.command.name=greet"
        },
        service = MVCActionCommand.class
    )
    public class GreeterActionCommand extends BaseMVCActionCommand {
        ...
    }

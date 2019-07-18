---
header-id: mvc-render-command
---

# MVC Render Command

[TOC levels=1-4]

`MVCRenderCommand`s are classes that respond to
[MVCPortlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet) [render
URLs](/docs/7-2/appdev/-/knowledge_base/a/writing-mvc-portlet-controller-code#render-logic).
If your render logic is simple and you want to implement all of your render
logic in your portlet class, see [Writing MVC Portlet Controller
Code](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet). If your render
logic is complex or you want clean separation between render paths, use
`MVCRenderCommand`s. Each render URL in your portlet's JSPs invokes an
appropriate render command class. 

Here are the steps:

1.  [Configure your JSPs](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)
    to generate render URLs via
    [`<portlet:renderURL>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/renderURL.html)
    tags. 

    For example, this
    [render-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/render-command-portlet)
    sample render URL invokes an MVC render command named `/blade/render`. 

    ```jsp
    <portlet:renderURL var="bladeRender">
    	<portlet:param name="mvcRenderCommandName" value="/blade/render" />
    </portlet:renderURL>
    ```

2.  Name the render URL via its `<portlet:param>` named 
    `mvcRenderCommandName`. The render URL and `*MVCRenderCommand` class
    (demonstrated later) map to the `mvcRenderCommandName` value. 

3.  Assign the `<portlet:renderURL>`'s `var` attribute a variable name to 
    pass to a UI component. 

4.  Assign the render URL variable (`var`) to a UI component. When the user 
    triggers the UI component, the `*MVCRenderCommand` class that matches the
    render URL handles the render request.

    For example, the render URL with the variable `bladeRender` triggers on users clicking this button.

    ```jsp
    <aui:button href="<%= bladeRender %>" value="goto page render" />
    ```

5.  Create a class that implements the 
    [`MVCRenderCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
    interface. 

6.  Annotate the class with an
    [`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html)
    annotation, like this one:

    ```java
    @Component(
    	property = {
    		"javax.portlet.name=com_liferay_blade_samples_portlet_rendercommand_BladeRenderPortlet",
    		"mvc.command.name=/blade/render"
    	},
    	service = MVCRenderCommand.class
    )
    ```

7.  Set a `javax.portlet.name` property to your portlet's internal ID. 

8.  Set a `mvc.command.name` property to your `<portlet:renderURL>` tag 
    `mvcRenderCommandName` portlet parameter value. This maps your class
    to the render URL. 

9.  Register your class as an `MVCRenderCommand` service by setting the
    `service` attribute to `MVCRenderCommand.class`. 

    Note, you can apply MVC Command classes to multiple portlets by setting
    a `javax.portlet.name` property for each portlet and apply MVC Command
    classes to  multiple command names by setting an `mvc.command.name`
    property for each command name. For example, this component's
    `javax.portlet.name` properties and `mvc.command.name` properties apply
    it to two specific portlets and two specific command names. 

    ```java
    @Component(
        immediate = true,
        property = {
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_MY_WORLD,
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
           "mvc.command.name=/hello/edit_super_entry",
           "mvc.command.name=/hello/edit_entry"
        },
        service = MVCRenderCommand.class
    )
    ```

10. Implement your render logic in a method that overrides
    [`MVCRenderCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)'s
    `render` method. Some `*MVCRenderCommand`s, such as the one below, always
    render the same JSP.

    ```java
    public class BlogsViewMVCRenderCommand implements MVCRenderCommand {

        @Override
        public String render(
            RenderRequest renderRequest, RenderResponse renderResponse) {

            return "/blogs/view.jsp";
        }

    }
    ```

As you can see, MVC render commands are easy to implement and can respond to
multiple command names for multiple portlets. 

## Related Topics

[Creating an MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/creating-an-mvc-portlet)

[Configuring the View Layer](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)

[MVC Resource Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-resource-command)

[MVC Action Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-action-command)

[MVC Command Overrides](/docs/7-2/customization/-/knowledge_base/c/overriding-liferay-mvc-commands)

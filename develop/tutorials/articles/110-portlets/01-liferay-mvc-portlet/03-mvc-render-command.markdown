# MVC Render Command [](id=mvc-render-command)

If you're here, that means you know that `MVCRenderCommand`s are used to [respond to portlet render URLs](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet#render-logic),
and you want to know how to create and use MVC render commands. If you just want
to learn about Liferay's MVC Portlet framework in general, that information is
in a [separate article](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet).

First, [configure your view layer](configuring-the-view-layer#configuring-the-view-layer)
and use the [`<portlet:renderURL>`](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/renderURL.html)
to create the render URL in your JSP. For example, the following
render URL invokes an MVC render command named `/hello/edit_entry`. This
might direct the user to a page with a form for editing. 

    <portlet:renderURL var="editEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" />
        <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
    </portlet:renderURL>

The `<portlet:param>` named `mvcRenderCommandName` declares the render URL. The
`<portlet:param>` named `entryId` declares a variable to hold the portlet render
URL object. Assign that variable to a UI component such, as a button or menu
item. When the user triggers the UI component, the `*MVCRenderCommand` class
that matches the render URL processes the render request and response.

What is it you want to do when a particular portlet render URL is invoked? By
implementing the [`MVCRenderCommand` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
and overriding its `render` method, you can perform your own logic to render
JSPs. Some `*MVCRenderCommand`s, such as the one below, always render the same
JSP.

    public class BlogsViewMVCRenderCommand implements MVCRenderCommand {

        @Override
        public String render(
            RenderRequest renderRequest, RenderResponse renderResponse) {

            return "/blogs/view.jsp";
        }

    }

Other `*MVCRenderCommand`s render JSPs based on conditions:

    @Override
    public String render(
        RenderRequest renderRequest, RenderResponse renderResponse)
        throws PortletException {

        try {
            ActionUtil.getEntry(renderRequest);
        }
        catch (Exception e) {
            if (e instanceof NoSuchEntryException ||
                e instanceof PrincipalException) {

                SessionErrors.add(renderRequest, e.getClass());

                return "/hello/error.jsp";
            }
            else {
                throw new PortletException(e);
            }
        }

        return "/hello/edit_entry.jsp";
    }

In the method above, if no exceptions are thrown on invoking
`ActionUtil.getEntry`, the method renders `/hello/edit_entry.jsp`. If a
`NoSuchEntryException` is thrown, it renders `/hello/error.jsp`. If any other
exception is thrown, the method re-throws it as a `PortletException`. 

To respond to a particular render URL, your `MVCRenderCommand` must be an OSGi
Declarative Services Component (e.g., annotated with @Component) that specifies
these properties:

- `javax.portlet.name`
- `mvc.command.name`

Here's an example of these two properties:

     "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
     "mvc.command.name=/hello/edit_entry"

The portlet name, in this case, is defined by the constant
`HelloWorldPortletKeys.HELLO_WORLD`. The `mvc.command.name` is set to
`/hello/edit_entry`, which seems to indicate the MVC render command is related
to editing entries--just a hunch. 

The Component must also publish to the OSGi runtime as a
`MVCRenderCommand.class` service. Here's a basic Component that specifies the
example properties and publishes itself as an `MVCRenderCommand.class` service:

    @Component(
        immediate = true,
        property = {
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
           "mvc.command.name=/hello/edit_entry"
        },
        service = MVCRenderCommand.class
    )
    public class EditEntryMVCRenderCommand implements MVCRenderCommand {
        ...
    }

The `mvc.command.name` value `/hello/edit_entry` matches the value of
`portlet:renderURL`'s `mvcRenderCommand` parameter shown earlier. That render
URL invokes this `*MVCRenderCommand` class. In fact, any render URL of JSPs in
this portlet (`HelloWorldPortletKeys.HELLO_WORLD`) whose `mvcRenderCommand` is
`/hello/edit_entry` invokes this `*MVCRenderCommand`. 

To make an `MVCRenderCommand` respond to multiple portlets, add them to your
`@Component` as `javax.portlet.name` properties assigned to the portlet names.
Likewise, to make it respond to multiple render URLs, add them as
`mvc.command.name` properties. If you're really feeling wild, you can specify
multiple portlets and multiple command URLs in the same command component, like
this:

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

As you can see, MVC render commands are easy to implement and can respond to
multiple command names for multiple portlets. 

## Related Topics [](id=related-topics)

[Creating an MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet)

[MVC Resource Command](/develop/tutorials/-/knowledge_base/7-1/mvc-resource-command)

[MVC Action Command](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command)

[MVC Command Overrides](/develop/tutorials/-/knowledge_base/7-1/overriding-mvc-commands)

# MVC Render Command [](id=mvc-render-command)

If you're here, that means you know that `MVCRenderCommand`s are used to respond
to portlet render URLs, and you want to know how to create and use MVC render
commands. If you just want to learn about Liferay's MVC Portlet framework in
general, that information is in a [separate article](develop/tutorials/-/knowledge_base/7-0/mvc-portlet).

To use MVC render commands, you need these things:

-  An implementation of the `MVCRenderCommand` interface.
-  A portlet render URL in your view layer.
-  a Component that publishes the `MVCRenderCommand` service, with two
   properties.

## Implementing MVCRenderCommand [](id=implementing-mvcrendercommand)

What is it you want to do when a portlet render URL is invoked? Using the
`MVCRenderCommandName`, direct the request to an `MVCRenderCommand`
implementation. Now override the `render` method.

Some `MVCRenderCommands` will simply render a particular JSP. Here's what
`BlogsViewMVCRenderCommand` looks like:

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		return "/blogs/view.jsp";
	}

Sometimes you'll want to add logic to render a certain JSP based on one or more
conditions:

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

If there's an error caught following the call to `ActionUtil.getEntry` in he
code above, the `error.jsp` is rendered. If the call is returned without an
exception being caught, `edit_entry.jsp` is rendered.

How does a request get directed to your MVC render command? Using a portlet
render URL.

## Creating a Portlet Render URL [](id=creating-a-portlet-render-url)

You can generate a render URL for your portlet using the `<portlet:renderURL>`
taglib. To invoke your MVC render command from the render URL, you need to
specify the parameter `mvcRenderCommandName` with the same value as your
Component property `mvc.command.name`.

For example, you can create a URL that directs the user to a page with a form for
editing an entry like this (in a JSP): 

    <portlet:renderURL var="editEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" />
        <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
    </portlet:renderURL>

Now the request will contain a parameter named `mvcRenderCommandName`. To find
the proper MVC render command, the OSGi runtime needs to have a
`mvc.command.name` property with a matching value.

## Registering the MVC Render Command [](id=registering-the-mvc-render-command)

In order to respond to a particular render URL, you need an
`MVCRenderCommand` Component that with two properties:

     "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
     "mvc.command.name=/hello/edit_entry"

Using the above properties as an example, any portlet render URL for the portlet
that includes a parameter called `mvcRenderCommand` with the value
`/hello/edit_entry` will be handled by this `MVCRenderCommand`.

The Component must also publish a `MVCRenderCommand.class` service to the OSGi
runtime. Here's a basic Component that publishes an MVC render command.

    @Component(
        immediate = true,
        property = {
           "javax.portlet.name=" + HelloWorldPortletKeys.HELLO_WORLD,
           "mvc.command.name=/hello/edit_entry"
        },
        service = MVCRenderCommand.class
    )
    public class EditEntryMVCRenderCommand implements MVCRenderCommand {

One command can be used by one portlet, as the example above shows. If you want,
one command can be used for multiple portlets by adding more
`javax.portlet.name` entries in the property list. Likewise, multiple commands
can invoke the MVC command class by adding more `mvc.command.name` entries. If
you're really feeling wild, you can specify multiple portlets and multiple
command URLs in the same command component, like this:

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

As you can see, MVC render commands are flexible and very easy to implement.

## Related Topics [](id=related-topics)

[MVC Resource Command](develop/tutorials/-/knowledge_base/7-0/mvc-resourcecommand)

[MVC Action Command](develop/tutorials/-/knowledge_base/7-0/mvc-actioncommand)

[MVC Command Overrides](develop/tutorials/-/knowledge_base/7-0/mvc-command-override)

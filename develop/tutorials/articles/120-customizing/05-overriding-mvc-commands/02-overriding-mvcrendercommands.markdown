# Overriding MVCRenderCommands [](id=overriding-mvcrendercommand)

You can override [`MVCRenderCommand`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
for any portlet that uses Liferay's MVC framework and publishes an
`MVCRenderCommand` component.

For example, Liferay's Blogs application has a class called
`EditEntryMVCRenderCommand`, with this component:


    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
            "mvc.command.name=/blogs/edit_entry"
        },
        service = MVCRenderCommand.class
    )

This MVC render command can be invoked from any of the portlets specified by
the `javax.portlet.name` parameter, by calling a render URL that names the
MVC command.

	<portlet:renderURL var="addEntryURL">
		<portlet:param name="mvcRenderCommandName" value="/blogs/edit_entry" />
		<portlet:param name="redirect" value="<%= viewEntriesURL %>" />
	</portlet:renderURL>

What if you want to override the command, but not for all of the portlets listed
in the original component? In your override component, just list the
`javax.portlet.name` of the portlets where you want the override to take effect.
For example, if you want to override the `/blogs/edit_entry` MVC render command
just for the Blogs Admin portlet (the Blogs Application accessed in the site
administration section of Liferay), your component could look like this:

    @Component(
      immediate = true,
      property = {
         "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
         "mvc.command.name=/blogs/edit_entry",
         "service.ranking:Integer=100"
      },
      service = MVCRenderCommand.class
    )

Note the last property listed, `service.ranking`. It's used to tell the OSGi
runtime which service to use, in cases where there are multiple components
registering the same service, with the same properties. The higher the integer
you specify here, the more weight your component carries. In this case, the
override component is used instead of the original one, since the default value
for this property is `0`.

After that, it's up to you to do whatever you'd like. MVC render commands can be customized for these purposes:

- [Adding Logic to an Existing MVC Render Command](#adding-logic-to-an-existing-mvc-render-command)
- [Redirecting to a new JSP](#redirecting-to-a-new-jsp)

Start by exploring how to add logic to an existing MVC render command. 

## Adding Logic to an Existing MVC Render Command [](id=adding-logic-to-an-existing-mvc-render-command)

You can add logic to an MVC render command following the
[general steps for MVC commands](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands).
Specifically for MVC render commands, you must directly implement the [`MVCRenderCommand` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
and override its `render` method. 

For example, this custom MVC render command has a placeholder (i.e., at comment
`//Do something here`) for adding logic to the `render` method.: 

    public CustomEditEntryRenderCommand implements MVCRenderCommand {
    	@Override
    	public String render(RenderRequest renderRequest, 
                            RenderResponse renderResponse)
               throws PortletException {

            //Do something here

    		return mvcRenderCommand.render(renderRequest, renderResponse);
    	}

        @Reference(target = 
              "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCRenderCommand)")
          protected MVCRenderCommand mvcRenderCommand;
    }

The example references an `EditEntryMVCRenderCommand` implementation of
`MVCRenderCommand`. In the `render` method, you'd replace the placeholder with
new logic and then invoke the original implementation's logic by calling its
`render` method. 

Sometimes, you might need to redirect the request to an entirely new JSP. You
can do that from a custom MVC render command module too.

## Redirecting to a New JSP [](id=redirecting-to-a-new-jsp)

`MVCRenderCommand`'s `render` method returns a JSP path as a String. By default,
the JSP must live in the original module, so you cannot simply specify a path to
a custom JSP in your override module. To redirect it to a JSP in your new
module, you must make the method skip dispatching to the original JSP
altogether, by using the constant 
[`MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderConstants.html).
Then you need to initiate your own dispatching process, directing the request to
your JSP path. Here's how that might look in practice:

    public class CustomEditEntryMVCRenderCommand implements MVCRenderCommand {

        @Override
        public String render(
            RenderRequest renderRequest, RenderResponse renderResponse) throws
                PortletException {

            System.out.println("Rendering custom_edit_entry.jsp");

            RequestDispatcher requestDispatcher =
                servletContext.getRequestDispatcher("/custom_edit_entry.jsp");

            try {
                HttpServletRequest httpServletRequest = 
                    PortalUtil.getHttpServletRequest(renderRequest);
                HttpServletResponse httpServletResponse = 
                    PortalUtil.getHttpServletResponse(renderResponse);

                requestDispatcher.include
                    (httpServletRequest, httpServletResponse);
            } catch (Exception e) {
                throw new PortletException
                    ("Unable to include custom_edit_entry.jsp", e);
            }

            return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;
        }

        @Reference(target = "(osgi.web.symbolicname=com.custom.code.web)")
        protected ServletContext servletContext;
    }

The servlet context provides access to the request dispatcher. A servlet context
is automatically created for portlets. It can be created for other modules by
including the following line in your `bnd.bnd` file:

    Web-ContextPath: /custom-code-web

Follow these steps to fetch the portlet's servlet context in your custom MVC render command:

1.  Add a `ServletContext` field.

        protected ServletContext servletContext;

2.  Add the `@Reference` annotation to the field and set the annotation to
    filter on the portlet's module. By convention, Liferay puts portlets in
    modules whose symbolic names end in `.web`. For example, this servlet
    context reference filters on a module whose symbolic name is
    `com.custom.code.web`.

        @Reference(target = "(osgi.web.symbolicname=com.custom.code.web)")
        protected ServletContext servletContext;

Implement your `render` method this way:

1.  Get a request dispatcher to your module's custom JSP. 

        RequestDispatcher requestDispatcher =
            servletContext.getRequestDispatcher("/custom_edit_entry.jsp");

2.  Include the HTTP servlet request and response in the request dispatcher.

        try {
            HttpServletRequest httpServletRequest = 
                PortalUtil.getHttpServletRequest(renderRequest);
            HttpServletResponse httpServletResponse = 
                PortalUtil.getHttpServletResponse(renderResponse);

            requestDispatcher.include
                (httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            throw new PortletException
                ("Unable to include custom_edit_entry.jsp", e);
        }

3.  Return the request dispatcher via the constant 
    `MVC_PATH_VALUE_SKIP_DISPATCH`.

        return MVCRenderConstants.MVC_PATH_VALUE_SKIP_DISPATCH;

After deploying your module, the [portlets targeted by your custom
`MVCRenderCommand` component](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands#publish-as-a-component)
render your new JSP. 

## Related Topics [](id=related-topics)

[MVC Render Command](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command)

[Adding Logic to MVC Commands](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands)

[Converting StrutsActionWrappers to MVCCommands](/develop/tutorials/-/knowledge_base/7-1/converting-strutsactionwrappers-to-mvccommands)

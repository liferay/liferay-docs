# Overriding MVC Commands [](id=overriding-mvc-commands)

MVC Commands are used to break up the controller layer of a Liferay MVC
application into smaller, more digestible code chunks.

Sometimes you'll want to override an MVC command, whether it's in a Liferay
application or another Liferay MVC application whose source code you don't own.
Since MVC commands are components registered in the OSGi runtime, you can simply
publish your own component, and give it a higher service ranking. Your MVC
command will then be invoked instead of the original one.

MVC commands are implemented in an application as OSGi components with two properties:

-  `javax.portlet.name`: the portlet that is affected by this command. It's
   often specified in a `-PortletKeys` class inside the application.
-  `mvc.command.name`: a key to identify the particular command 

You can override the MVC commands of any portlet if you can find the values for
these two properties.

The logical way of breaking up the controller layer is to do it by portlet
phase. The three MVC command classes you can override are

-  **`MVCActionCommand`:** An interface that allows the portlet to process
   a particular action request.
-  **`MVCRenderCommand`:** An interface that handles the render phase of the portlet.
-  **`MVCResourceCommand`:**  An interface that allows the portlet to serve a resource.

Find more information about implementing each of these MVC command classes in
the tutorials on Liferay MVC Portlets<!--Add Link when these are completed-->.
Here we're going to focus on overriding the logic contained in existing MVC
commands.

+$$$

**Note:** While it's possible to copy the logic from an existing MVC command
into your override class, then customize it to your liking, it's strongly
recommended to decouple the original logic from your override logic. Keeping the
override logic separate form the original logic will keep the code clean,
maintainable, and easy to understand.

To do this, use the `@Reference` method to fetch a reference to the original MVC
command. If there are no additional customizations on the same command, this
reference will be the original MVC command.

    @Reference(
        target = "(&(mvc.command.name=/blogs/edit_entry)(javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN + "))")
    protected MVCRenderCommand mvcRenderCommand;

If you use this approach, your extension will continue to work with new versions
of the original portlet, because no coupling exists between the original portlet
logic and your customization.

$$$

+$$$

**Note:** In @product-ver@ GA1, there's a bug that occurs when modules with
override MVC commands are removed from the OSGi runtime. Instead of looking for
an MVC command with a lower service ranking (the original MVC command in most
cases) to replace the removed one, the reference to the command is removed
entirely. This bug is documented and fixed
[here](https://issues.liferay.com/browse/LPS-65434)

$$$

Start by learning to override `MVCRenderCommand`. The process will be similar for
the other MVC commands.

## Overriding MVCRenderCommand [](id=overriding-mvcrendercommand)

You can override `MVCRenderCommand` for any portlet that uses Liferay's MVC
framework and publishes an `MVCRenderCommand` component.

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
`javax.portlet.name` of the portlets where you want the override to take
effect. For example, if you want to override the `/blogs/edit_entry` MVC render
command just for the Blogs Admin portlet (the Blogs Application accessed in the site
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

Note the last property listed, `service.ranking`. It's used to tell the
OSGi runtime which service to use, in cases where there are multiple components
registering the same service, with the same properties. The higher the integer
you specify here, the more weight your component carries. In this case, the
override component will be used instead of the original one, since the default value
for this property is `0`.

After that, it's up to you to do whatever you'd like. You can add logic to
the existing `render` method or redirect to an entirely new JSP.

### Adding Logic to an Existing MVC Render Command [](id=adding-logic-to-an-existing-mvc-render-command)

Don't copy the existing logic from the MVC render command into your override
command class. This unnecessary duplication of code that makes
maintenance more difficult. If you want to do something new (like set a request
attribute) and then execute the logic in the original MVC render command, obtain a
reference to the original command and call its `render` method like this: 

	@Override
	public String render(RenderRequest renderRequest, 
                        RenderResponse renderResponse) throws PortletException {

        //Do something here

		return mvcRenderCommand.render(renderRequest, renderResponse);
	}

    @Reference(target = 
          "(&(mvc.command.name=/blogs/edit_entry)(javax.portlet.name= + BlogsPortletKeys.BLOGS_ADMIN + "))")
      protected MVCRenderCommand mvcRenderCommand;
    }

Sometimes, you might need to redirect the request to an entirely new JSP that
you'll place in your command override module.

### Redirecting to a New JSP [](id=redirecting-to-a-new-jsp)

If you want to render an entirely new JSP, the process is different.

The `render` method of `MVCRenderCommand` returns the path to a JSP as a String.
The JSP must live in the original module, so you cannot simply specify a path to
a custom JSP in your override module. You need to make the method skip
dispatching to the original JSP altogether, by using the
`MVC_PATH_VALUE_SKIP_DISPATCH` constant from the `MVCRenderConstants` class.
Then you need to initiate your own dispatching process, directing the request to
your JSP path. Here's how that might look in practice:

    public class CustomEditEntryMVCRenderCommand implements MVCRenderCommand {

        @Override
        public String render
            (RenderRequest renderRequest, RenderResponse renderResponse) throws
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

In this approach, there's no reference to the original MVC render command
because the original logic isn't reused. Instead, there's a reference to the
servlet context of your module, which is needed to use the request
dispatcher.

A servlet context is automatically created for portlets. It can be created
for other modules by including the following line in your `bnd.bnd` file:

    Web-ContextPath: /custom-code-web

Once we have the servlet context we just need to dispatch to the specific jsp in
our own module. 

## Overriding MVCActionCommand [](id=overriding-mvcactioncommand)

You can override MVC action commands using a similar process to the one presented
above for MVC render commands. Again, you'll register a new OSGi component with
the same properties, but with a higher service ranking. This time the service
you're publishing is `MVCActionCommand.class`.

For MVC action command overrides, extend `BaseMVCActionCommand`, and the only method
you'll need to override is `doProcessAction`, which must return `void`.

As with MVC render commands, you can add your logic to the original behavior of
the action method by getting a reference to the original service, and calling it
after your own logic. Here's an example of an `MVCActionCommand` override that
checks whether the `delete` action is invoked on a blog entry, and prints a
message to the log, before continuing with the original processing:


    @Component(
        property = { 
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN, 
            "mvc.command.name=/blogs/edit_entry",
            "service.ranking:Integer=100" 
        }, 
        service = MVCActionCommand.class)
    public class CustomBlogsMVCActionCommand extends BaseMVCActionCommand {

        @Override
        protected void doProcessAction
            (ActionRequest actionRequest, ActionResponse actionResponse) 
            throws Exception {

            String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

            if (cmd.equals(Constants.DELETE)) {
                System.out.println("Deleting a Blog Entry");
            }

            mvcActionCommand.processAction(actionRequest, actionResponse);
        }

        @Reference(
            target = "(&(mvc.command.name=/blogs/edit_entry)
                (javax.portlet.name= + BlogsPortletKeys.BLOGS_ADMIN + "))")

        protected MVCActionCommand mvcActionCommand;

    }

It's straightforward to override MVC action commands while keeping your code
decoupled from the original action methods. You can also override MVC resource
commands. 

## Overriding MVCResourceCommand [](id=overriding-mvcresourcecommand)

There are fewer uses for overriding MVC resource commands, but it can also be
done. 

The process is similar to the one described for `MVCRenderCommand` and
`MVCActionCommand`. There's a couple things to keep in mind:

-  The service to specify in your component is `MVCResourceCommand.class`

-  As with overriding `MVCRenderCommand`, there's no base implementation class
   to extend. You'll implement the interface yourself.

-  Keep your code decoupled from the original code by adding your logic to the
   original `MVCResourceCommand`'s logic by getting a reference to the original
and returning a call to its `serveResource` method: 

         return mvcResourceCommand.serveResource(resourceRequest, resourceResponse);

The following example overrides the behavior of
`com.liferay.login.web.portlet.action.CaptchaMVCResourceCommand`, from the
`login-web` module of the Login portlet. It simply prints a line in the console
then executes the original logic: returning the Captcha image
for the account creation screen.

    @Component(
        property = { 
            "javax.portlet.name=" + LoginPortletKeys.LOGIN,
            "mvc.command.name=/login/captcha" }, 
        service = MVCResourceCommand.class)
    public class CustomCaptchaMVCResourceCommand implements MVCResourceCommand {

        @Override
        public boolean serveResource
            (ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

            System.out.println("Serving login captcha image");

            return mvcResourceCommand.serveResource(resourceRequest, resourceResponse);
        }

        @Reference(target = "(&(mvc.command.name=/login/captcha)
            (javax.portlet.name= +LoginPortletKeys.LOGIN + "))")
        protected MVCResourceCommand mvcResourceCommand;

    }

And that, as they say, is that. Even if you don't own the source code of an
application, you can override its MVC commands just by knowing the value of two
of its component properties: `javax.portlet.name`, and `mvc.command.name`.

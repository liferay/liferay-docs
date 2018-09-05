# MVC Action Command [](id=mvc-action-command)

Liferay's MVC framework lets you split your portlet's [action methods](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet#action-methods)
into separate classes. This can be very helpful in portlets that have many
actions. Each action URL in your portlet's JSPs then calls the appropriate
action class when necessary. 

First, [configure your view layer](configuring-the-view-layer#configuring-the-view-layer)
and use the [`<portlet:actionURL>` tag](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/actionURL.html)
to create the action URL in your JSP.  For example, the Blogs app's
[`edit_entry.jsp` file](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp)
defines the following action URL for editing blog entries:

    <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />

The `name` attribute declares a variable to hold the portlet action URL object.
Assign that variable to a UI component, such as a button or icon. When the user
triggers the UI component, the `*MVCActionCommand` class that matches the action
URL processes the action request and response. Create an action class by
implementing the 
[`MVCActionCommand` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html),
or extending the [`BaseMVCActionCommand` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html).
The latter may save you time, since it already implements `MVCActionCommand`.

Naming your `*MVCActionCommand` class after the action it performs is a good
convention. For example, if your action class edits some kind of entry, you
could name its class `EditEntryMVCActionCommand`. If your application has
several MVC command classes, naming them this way helps differentiate them. 

Your `*MVCActionCommand` class must also have an `@Component` annotation like
the following example. Set the property `javax.portlet.name` to your portlet's
internal ID. Set the property `mvc.command.name` to the value of the `name`
property in your JSP's matching `actionURL`. To register the component in the
OSGi container as an `MVCActionCommand` service, set the `service` property to
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

The Blogs app's `EditEntryMVCActionCommand` class is a real world example of a
`*MVCActionCommand` class:

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

        	@Override
        	protected void doProcessAction(
        			ActionRequest actionRequest, ActionResponse actionResponse)
        		throws Exception {

        		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        		try {
        			BlogsEntry entry = null;

        			UploadException uploadException =
        				(UploadException)actionRequest.getAttribute(
        					WebKeys.UPLOAD_EXCEPTION);

        			if (uploadException != null) {
        				Throwable cause = uploadException.getCause();

        				if (uploadException.isExceededFileSizeLimit()) {
        					throw new FileSizeException(cause);
        				}

        				if (uploadException.isExceededLiferayFileItemSizeLimit()) {
        					throw new LiferayFileItemException(cause);
        				}

        				if (uploadException.isExceededUploadRequestSizeLimit()) {
        					throw new UploadRequestSizeException(cause);
        				}

        				throw new PortalException(cause);
        			}
        			else if (cmd.equals(Constants.ADD) ||
        					 cmd.equals(Constants.UPDATE)) {

        				Callable<BlogsEntry> updateEntryCallable =
        					new UpdateEntryCallable(actionRequest);

        				entry = TransactionInvokerUtil.invoke(
        					_transactionConfig, updateEntryCallable);
        			}
        			else if (cmd.equals(Constants.DELETE)) {
        				deleteEntries(actionRequest, false);
        			}
        			else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
        				deleteEntries(actionRequest, true);
        			}
        			else if (cmd.equals(Constants.RESTORE)) {
        				restoreTrashEntries(actionRequest);
        			}
        			else if (cmd.equals(Constants.SUBSCRIBE)) {
        				subscribe(actionRequest);
        			}
        			else if (cmd.equals(Constants.UNSUBSCRIBE)) {
        				unsubscribe(actionRequest);
        			}

                    ... do more action processing
                }

                ... handle exceptions
        }
    }

The `@Component`'s multiple `javax.portlet.name` property values make this
`*MVCActionCommand` class available to those portlets as a Service Component.
The `mvc.command.name` property setting `/blogs/edit_entry` matches the
`actionURL`'s `name` attribute shown earlier, and the `service` property set to
`MVCActionCommand.class` makes the class an `MVCActionCommand` Service
Component. 

The `EditEntryMVCActionCommand` class extends `BaseMVCActionCommand` and
overrides the `doProcessAction` method. Similarly, `*MVCActionCommand` classes
that implement `MVCActionCommand` directly must implement the `processAction`
method. Both methods process resource requests and responses via their
`javax.portlet.ActionRequest` and `javax.portlet.ActionResponse` parameters,
respectively.

`EditEntryMVCActionCommand`'s `doProcessAction` method gets the value of a
command parameter named by [constant `Constants.CMD`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/Constants.html)
from the `ActionRequest`.

	String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

Then the `doProcessAction` method checks whether an entry-related upload
occurred or handles any exceptions the upload throws. Based on the command
(stored in `cmd`) accessed from the action request, one of the following
actions is performed: 

- add or update an entry
- delete an entry
- move an entry to the Recycle Bin
- restore an entry from the Recycle Bin
- subscribe a user to a blog
- unsubscribe a user from a blog

`EditEntryMVCActionCommand`'s `doProcessAction` method continues with some more
processing and prepares to redirect the portlet to an appropriate view. This
shows you can do as much as you need for processing your portlet's actions. 

+$$$

**Note**: [Liferay Blade Sample `action-command-portlet`]( https://github.com/liferay/liferay-blade-samples/tree/master/gradle/apps/action-command-portlet)
demonstrates implementing `MVCActionCommand` directly.

$$$ 

Now you can create your own action URLs and `*MVCActionCommand` classes in your
applications that use Liferay's MVC framework. Your `*MVCActionCommand`s can do
whatever you need them to do. 

## Related Topics [](id=related-topics)

[Creating an MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet)

[MVC Render Command](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command)

[MVC Resource Command](/develop/tutorials/-/knowledge_base/7-1/mvc-resource-command)

[MVC Command Overrides](/develop/tutorials/-/knowledge_base/7-1/overriding-mvc-commands)

# Overriding MVCActionCommands [](id=overriding-mvcactioncommand)

In case you want add to a Liferay MVC action command, you can. The OSGi
framework lets you override MVC action commands if you follow the instructions
for [adding logic to MVC commands](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands).
It involves [registering your custom MVC action command as an OSGi component](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands#publish-as-a-component)
with the same properties as the original, but with a higher service ranking.

Custom MVC action commands typically extend the [`BaseMVCActionCommand` class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html),
and override its `doProcessAction` method, which returns `void`. Add your logic
to the original behavior of the action method by
[getting a reference to the original service](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands#refer-to-the-original-implementation),
and [calling it after your own logic](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands#add-the-logic).
For example, this `MVCActionCommand` override checks whether the `delete` action
is invoked on a blog entry, and prints a message to the log, before continuing
with the original processing:

    @Component(
        property = { 
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN, 
            "mvc.command.name=/blogs/edit_entry",
            "service.ranking:Integer=100" 
        }, 
        service = MVCActionCommand.class
    )
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
            target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
        protected MVCActionCommand mvcActionCommand;

    }

Adding MVC action command logic before existing logic is straightforward and
maintains loose coupling between new and old code. 

## Related Topics [](id=related-topics)

[MVC Action Command](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command)

[Adding Logic to MVC Commands](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands)

[Overriding MVCRenderCommands](/develop/tutorials/-/knowledge_base/7-1/redirecting-to-a-new-jsp)

[Converting StrutsActionWrappers to MVCCommands](/develop/tutorials/-/knowledge_base/7-1/converting-strutsactionwrappers-to-mvccommands)

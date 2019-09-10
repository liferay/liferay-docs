---
header-id: overriding-mvcactioncommand
---

# Overriding MVCActionCommands

[TOC levels=1-4]

In case you want add to a Liferay MVC action command, you can. The OSGi 
framework lets you override MVC action commands if you follow the instructions 
for [adding logic to MVC commands](/docs/7-2/customization/-/knowledge_base/c/adding-logic-to-mvc-commands). 
It involves [registering your custom MVC action command as an OSGi component](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands#publish-as-a-component) 
with the same properties as the original, but with a higher service ranking. 

Custom MVC action commands typically extend the [`BaseMVCActionCommand` class](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html), 
and override its `doProcessAction` method, which returns `void`. Add your logic 
to the original behavior of the action method by getting a reference to the 
original service, and calling it after your own logic. 

<!--Add link back for 'getting a reference to the original service' once 
adding-logic-to-mvc-commands#refer-to-the-original-implementation article is available

And add link back for 'calling it after your own logic' once 
adding-logic-to-mvc-commands#add-the-logic article is available
-->

For example, this 
`MVCActionCommand` override checks whether the `delete` action is invoked on a 
blog entry, and prints a message to the log, before continuing with the original 
processing:

```java
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
```

Adding MVC action command logic before existing logic is straightforward and 
maintains loose coupling between new and old code. 

## Related Topics

- [Adding Logic to MVC Commands](/docs/7-2/customization/-/knowledge_base/c/adding-logic-to-mvc-commands)
- [Overriding MVCRenderCommands](/docs/7-2/customization/-/knowledge_base/c/overriding-mvcrendercommand)
- [Converting StrutsActionWrappers to MVCCommands](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-struts-action-hooks)

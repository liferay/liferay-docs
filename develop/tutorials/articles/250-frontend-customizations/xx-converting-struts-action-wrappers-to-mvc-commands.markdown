# Converting StrutsActionWrappers to MVCCommands

Since Liferay 6.1, developers could customize the Portal and Portlet Struts 
Actions using a Hook and StrutsActionWrappers. For example, take a look at the 
`liferay-hook.xml` file for a hook that overrode the login portlet's login 
action:

    <struts-action>
        <struts-action-path>/login/login</struts-action-path>
        <struts-action-impl>
        com.liferay.sample.hook.action.ExampleStrutsPortletAction
        </struts-action-impl>
    </struts-action>
    
The `liferay-hook.xml` contains the struts mapping and the new class that 
overrides the default login action.    

The wrapper could extend either `BaseStrutsAction` or `BaseStrutsPortletAction`, 
depending on whether the struts action was a portal or portlet action 
respectively.

Starting in Liferay 7.0 and Liferay DXP, this mechanism no longer applies for 
most of the portal portlets because they are no longer using Struts Actions, but 
instead use MVCCommands.

This tutorial demonstrates the following:

- How to convert your existing StrutsActionWrappers to MVCCommands

Go ahead and get started.

## Converting Your old wrapper to MVCCommands

Converting StrutsActionWrappers to MVCCommands is easier than you may think.

As a review, legacy StrutsActionWrappers needed to implement all the methods,
`processAction`, `render`, and `serveResource`, even if only one method was 
being customized. Each of these methods can now be customized independently, 
using different classes, making the logic simpler and easier to maintain. 
Depending on which method you customized in your StrutsActionWrapper, you will 
need to use the matching MVCCommand shown below:

-   processAction ->  MVCActionCommand
-   render -> MVCRenderCommand
-   serveResource -> MVCResourceCommand

Take a look at the [ExampleStrutsPortletAction class](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/overriding-and-adding-struts-actions)
as a StrutsActionWrapper example. Depending on which actions were overridden, the 
user will have to use different MVCCommands. In this example, the action and 
render were overwritten, so in order to migrate to the new pattern, you would 
need to create two classes: `MVCActionCommand` and `MVCRenderCommand`.

Next you'll need to determine the associated mapping that is used by the 
MVCCommand.

## Mapping Your MVCCommand URLs

For most cases, the MCVCCommand mapping is the same mapping defined in the 
legacy struts-action.

Using the beginning login example once again, the `struts-action-path` mapping, 
`/login/login`, remains the same for the MVCCommand mapping in Liferay 7.

Some of the portlets mapping may have changed, so it’s best to check Liferay's 
source code to determine the correct mapping.

Depending on the URL it will be a different param:

-   RenderURLs will contain a parameter named `mvcRenderCommandName`. For
    example:
    
        <portlet:renderURL var="editEntryURL">
            <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" 
            />
            <portlet:param name="entryId" value="<%= String.valueOf(
            entry.getEntryId()) %>" />
        </portlet:renderURL>    

-   ActionURLs will be contained in the attribute `name` of the taglib
    or with the parameter `ActionRequest.ACTION_NAME`. For example:
    
        <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />
    
-   ResourceURLs will be contained as the attribute `id`. For example:

        <portlet:resourceURL id="/login/captcha" var="captchaURL" />

Once you have this information you can override the MVCCommand following the
instructions found in these sections of the Overriding MVC Commands tutorial:

-   Overriding MVCActionCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcactioncommand](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcactioncommand)
-   Overriding MVCResourceCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcresourcecommand](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcresourcecommand)
-   Overriding MVCRenderCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#adding-logic-to-an-existing-mvc-render-command](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#adding-logic-to-an-existing-mvc-render-command)

Now you know how to convert your StrutsActionWrappers to MVCCommands!

## Related Topics

[Overriding MVC Commands](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands)

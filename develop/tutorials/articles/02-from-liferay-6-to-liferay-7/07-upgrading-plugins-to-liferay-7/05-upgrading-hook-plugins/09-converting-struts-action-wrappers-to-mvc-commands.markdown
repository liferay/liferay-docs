# Converting StrutsAction Wrappers to MVCCommands [](id=converting-strutsactionwrappers-to-mvccommands)

In Liferay Portal 6.1 and 6.2, developers could customize the Portal and Portlet
Struts Actions using a Hook and `StrutsAction` wrapper. For example, the
`liferay-hook.xml` file for a hook that overrode the login portlet's login
action had this entry:

    <struts-action>
        <struts-action-path>/login/login</struts-action-path>
        <struts-action-impl>
        com.liferay.sample.hook.action.ExampleStrutsPortletAction
        </struts-action-impl>
    </struts-action>

The `liferay-hook.xml` contains the Struts mapping and the new class that 
overrides the default login action. 

The wrapper could extend either `BaseStrutsAction` or `BaseStrutsPortletAction`,
depending on whether the Struts Action was a portal or portlet action
respectively.

Since 7.0, this mechanism no longer applies for most portlets because they no
longer use Struts Actions, but  instead use Liferay `MVCCommand`s.

This tutorial demonstrates how to convert your existing `StrutsAction` wrappers
to `MVCCommand`s. 

## Converting Your old wrapper to MVCCommands [](id=converting-your-old-wrapper-to-mvccommands)

Converting `StrutsAction` wrappers to `MVCCommand`s is easier than you may
think.

As a review, legacy `StrutsAction` wrappers implemented all methods, such as
`processAction`, `render`, and `serveResource`, even if only one method was
being customized. Each of these methods can now be customized independently
using different classes, making the logic simpler and easier to maintain.
Depending on the method you customized in your `StrutsAction` wrapper, you need
to use the matching 
[`MVCCommand` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCCommand.html)
shown below:

-   processAction &rarr;  [MVCActionCommand](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
-   render &rarr; [MVCRenderCommand](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
-   serveResource &rarr; [MVCResourceCommand](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)

Look at the
[`ExampleStrutsPortletAction` class](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/overriding-and-adding-struts-actions)
for a `StrutsAction` wrapper example. Depending on the actions overridden, the
user must use different `MVCCommand`s. In this example, the action and render
were overridden, so to migrate to the new pattern, you would create two classes:
an `MVCActionCommand` and `MVCRenderCommand`.

Next you'll determine the mapping the `MVCCommand` uses.

## Mapping Your MVCCommand URLs [](id=mapping-your-mvccommand-urls)

For most cases, the `MVCCommand` mapping is the same mapping defined in the 
legacy Struts Action.

Using the beginning login example once again, the `struts-action-path` mapping,
`/login/login`, remains the same for the `MVCCommand` mapping in @product-ver@,
but some of the mappings may have changed. It's best to check @product@'s source
code to determine the correct mapping.

Map to your MVCCommand URLs using portlet URL tags:

-   `MVCRenderCommand` URLs go in `mvcRenderCommandName` parameters. For
    example:

        <portlet:renderURL var="editEntryURL">
            <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" 
            />
            <portlet:param name="entryId" value="<%= String.valueOf(
            entry.getEntryId()) %>" />
        </portlet:renderURL>    

-   `MVCActionCommand` URLs go in `actionURL` tag `name` attributes or in a
    parameter `ActionRequest.ACTION_NAME`. For example:

        <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />
 
-   `MVCResourceCommand` URLs go in `resourceURL` tag `id` attributes. For
    example:

        <portlet:resourceURL id="/login/captcha" var="captchaURL" />

Once you have this information, you can override the `MVCCommand` by following
the instructions found in these `MVCCommand` tutorials:

-   [Adding Logic to MVCCommands](/develop/tutorials/-/knowledge_base/7-1/adding-logic-to-mvc-commands)
-   [Overriding MVCRenderCommands](/develop/tutorials/-/knowledge_base/7-1/overriding-mvcrendercommand)
-   [Overriding MVCActionCommands](/develop/tutorials/-/knowledge_base/7-1/overriding-mvcactioncommand)
-   [Overriding MVCResourceCommands](/develop/tutorials/-/knowledge_base/7-1/overriding-mvcresourcecommand)

Now you know how to convert your `StrutsActionWrapper`s to `MVCCommand`s!

## Related Topics [](id=related-topics)

[Overriding MVC Commands](/develop/tutorials/-/knowledge_base/7-1/overriding-mvc-commands)

[Liferay MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/liferay-mvc-portlet)

[Resolving a Plugin's Dependencies](/develop/tutorials/-/knowledge_base/7-1/resolving-a-plugins-dependencies)

[Configuring Dependencies](/develop/tutorials/-/knowledge_base/7-1/configuring-dependencies)

[Upgrading the Liferay Maven Build](/develop/tutorials/-/knowledge_base/7-1/upgrading-the-liferay-maven-build)

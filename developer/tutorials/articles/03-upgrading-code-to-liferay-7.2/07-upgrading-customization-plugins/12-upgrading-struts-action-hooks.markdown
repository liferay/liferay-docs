# Upgrading Struts Action Hooks

<div class="learn-path-step">
    <p>Upgrading Customization Plugins<br>Step 11 of 11</p>
</div>

In Liferay Portal 6.1 and 6.2, developers could customize the Portal and Portlet
Struts Actions using a Hook and `StrutsAction` wrapper. For example, the
`liferay-hook.xml` file for a hook that overrode the login portlet's login
action had this entry:

```xml
<struts-action>
    <struts-action-path>/login/login</struts-action-path>
    <struts-action-impl>
        com.liferay.sample.hook.action.ExampleStrutsPortletAction
    </struts-action-impl>
</struts-action>
```

The `liferay-hook.xml` contains the Struts mapping and the new class that 
overrides the default login action. 

The wrapper could extend either `BaseStrutsAction` or `BaseStrutsPortletAction`,
depending on whether the Struts Action was a portal or portlet action,
respectively.

Since @product@ 7.0, this mechanism no longer applies for most portlets because
they no longer use Struts Actions, but instead use Liferay `MVCCommand`s.

This tutorial demonstrates how to convert your existing `StrutsAction` wrappers
to `MVCCommand`s. 

## Converting Your Old Wrapper to `MVCCommand`s

Converting `StrutsAction` wrappers to `MVCCommand`s is easier than you may
think.

As a review, legacy `StrutsAction` wrappers implemented all methods, such as
`processAction`, `render`, and `serveResource`, even if only one method was
being customized. Each of these methods can now be customized independently
using different classes, making the logic simpler and easier to maintain.
Depending on the method you customized in your `StrutsAction` wrapper, you need
to use the matching 
[`MVCCommand` interface](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCCommand.html)
shown below:

-   `processAction` &rarr; [`MVCActionCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
-   `render` &rarr; [`MVCRenderCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.html)
-   `serveResource` &rarr; [`MVCResourceCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)

Look at the
[`ExampleStrutsPortletAction` class](/docs/tutorials/6-2/-/knowledge_base/t/overriding-and-adding-struts-actions)
for a `StrutsAction` wrapper example. Depending on the actions overridden, the
user must use different `MVCCommand`s. In this example, the action and render
were overridden, so to migrate to the new pattern, you would create two classes:
an `MVCActionCommand` and `MVCRenderCommand`.

Next you'll determine the mapping the `MVCCommand` uses.

## Mapping Your `MVCCommand` URLs

For most cases, the `MVCCommand` mapping is the same mapping defined in the 
legacy Struts Action.

Using the beginning login example once again, the `struts-action-path` mapping,
`/login/login`, remains the same for the `MVCCommand` mapping in @product-ver@,
but some of the mappings may have changed. It's best to check @product@'s source
code to determine the correct mapping.

Map to your `MVCCommand` URLs using portlet URL tags:

- `MVCRenderCommand` URLs go in `mvcRenderCommandName` parameters. For
  example,

    ```xml
    <portlet:renderURL var="editEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/hello/edit_entry" />
        <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
    </portlet:renderURL>
    ```

- `MVCActionCommand` URLs go in `actionURL` tag `name` attributes or in a
  parameter `ActionRequest.ACTION_NAME`. For example,

    ```xml
    <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />
    ```
 
- `MVCResourceCommand` URLs go in `resourceURL` tag `id` attributes. For
  example,

    ```xml
    <portlet:resourceURL id="/login/captcha" var="captchaURL" />
    ```

Once you have this information, you can override the `MVCCommand` by following
the instructions found in these `MVCCommand` articles:

-   [Adding Logic to MVCCommands](/docs/customization/7-2/-/knowledge_base/c/adding-logic-to-mvc-commands)
-   [Overriding MVCRenderCommands](/docs/customization/7-2/-/knowledge_base/c/overriding-mvcrendercommand)
-   [Overriding MVCActionCommands](/docs/customization/7-2/-/knowledge_base/c/overriding-mvcactioncommand)
-   [Overriding MVCResourceCommands](/docs/customization/7-2/-/knowledge_base/c/overriding-mvcresourcecommand)

Now you know how to convert your `StrutsActionWrapper`s to `MVCCommand`s!
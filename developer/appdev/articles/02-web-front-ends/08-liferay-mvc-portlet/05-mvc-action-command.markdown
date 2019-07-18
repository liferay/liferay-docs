---
header-id: mvc-action-command
---

# MVC Action Command

[TOC levels=1-4]

Liferay's MVC Portlet framework enables you to handle
[MVCPortlet](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet)
[actions](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer#using-action-urls)
in separate classes. This facilitates managing action logic in portlets that
have many actions. Each action URL in your portlet's JSPs invokes an appropriate
action command class. 

Here are the steps:

1.  [Configure your JSPs](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)
    to use action URLs via
    [`<portlet:actionURL>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/actionURL.html)
    tags. For example, the
    [action-command-portlet](https://github.com/liferay/liferay-blade-samples/blob/7.1/gradle/apps/action-command-portlet/src/main/resources/META-INF/resources/view.jsp)
    sample uses this action URL:

    ```jsp
    <liferay-portlet:actionURL name="greet" var="greetURL" />
    ```

    Name the action URL via its `name` attribute. Your `*MVCActionCommand` class
    maps to this name. Assign the `var` attribute a variable name. 

2.  Assign the action URL variable (`var`) to a UI component. Acting on the UI 
    component invokes the action. For  example, the sample's `greetURL` action
    URL variable triggers on submitting this form: 

    ```jsp
    <aui:form action="<%= greetURL %>" method="post" name="fm">
    	<aui:input name="name" type="text" />

    	<aui:button-row>
    		<aui:button type="submit"></aui:button>
    	</aui:button-row>
    </aui:form>
    ```

3.  Create a class that implements the
    [`MVCActionCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
    interface, or that extends the
    [`BaseMVCActionCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html)
    class. The latter may save you time, since it already implements
    `MVCActionCommand`. 

    | **Tip:** Naming your `*MVCActionCommand` class after the action it 
    | performs makes the action mappings more obvious for maintaining the code.
    | For example, if your action class edits some kind of entry, you could name
    | its class `EditEntryMVCActionCommand`. If your application has several MVC
    | command classes, naming them this way helps differentiate them. 

4.  Annotate your class with an
    [`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html)
    annotation, like this one:

    ```java
    @Component(
        property = {
            "javax.portlet.name=your_portlet_name_YourPortlet",
            "mvc.command.name=/your/jsp/action/url"
        },
        service = MVCActionCommand.class
    )
    ```

5.  Set a `javax.portlet.name` property to your portlet's internal ID. 

    Note, you can apply MVC Command classes to multiple portlets by setting
    a `javax.portlet.name` property for each portlet. For example, the
    `javax.portlet.name` properties in this component apply it to three
    specific portlets. 

    ```java
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
        ...
    }
    ```

6.  Set the `mvc.command.name` property to your `<portlet:actionURL>` tag's 
    `name`. This maps your class to the action URL of the same name. 

7.  Register your class as an `MVCActionCommand` service by setting the
    `service` attribute to `MVCActionCommand.class`. 

8.  Implement your action logic by overriding the appropriate method of the 
    class you're implementing or extending. 

    -   [`MVCActionCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)
       implementations override the `processAction` method. 

    -   [`BaseMVCActionCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html)
        extensions override the `doProcessAction` method. 

    Here's an example of overriding `MVCActionCommand`'s `processAction` method.
    This action logic gets the name parameter from the `ActionRequest` and adds
    it to the session messages and to an `ActionRequest` attribute. 

    ```java
    @Override
    public boolean processAction(
    		ActionRequest actionRequest, ActionResponse actionResponse)
    	throws PortletException {

    	_handleActionCommand(actionRequest);

    	return true;
    }

    private void _handleActionCommand(ActionRequest actionRequest) {
    	String name = ParamUtil.get(actionRequest, "name", StringPool.BLANK);

    	if (_log.isInfoEnabled()) {
    		_log.info("Hello " + name);
    	}

    	String greetingMessage = "Hello " + name + "! Welcome to OSGi";

    	actionRequest.setAttribute("GREETER_MESSAGE", greetingMessage);

    	SessionMessages.add(actionRequest, "greetingMessage", greetingMessage);
    }

    private static final Log _log = LogFactoryUtil.getLog(
    	GreeterActionCommand.class);
    ```

Congratulations! You've created an `MVCActionCommand` that handles your portlet
actions. 

## Related Topics

[Creating an MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/creating-an-mvc-portlet)

[Configuring the View Layer](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)

[MVC Render Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-render-command)

[MVC Resource Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-resource-command)

[MVC Command Overrides](/docs/7-2/customization/-/knowledge_base/c/overriding-liferay-mvc-commands)

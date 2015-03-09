# Performing a Custom Action Using a Hook [](id=performing-a-custom-action-using-a-hook)

<!-- The custom-action-hook project can be found here:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/cust/custom-action/end/custom-action-hook
-->

Hooks are useful for triggering custom actions on common portal events, like
user login or system startup. The actions for each of these events are defined
in `portal.properties`, so you need to extend this file to create a custom
action. Hooks make this a simple task. It's time to explore how to perform a
custom action using a hook! 

In this tutorial, you'll create a custom hook that performs a custom action for
user login. You could similarly create a custom action for other portal events
defined in `portal.properties`. It's time to get started! 

1.  Determine the event on which you want to trigger your custom action. Look
    in the [`portal.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
    documentation to find the matching event property. Hint: the event
    properties have `.event` in their name. There are session, startup,
    shutdown, and portal event properties in the following sections of the
    `portal.properties` documentation: 
    - [Session](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Session)
    - [Startup Events](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Startup%20Events)
    - [Shutdown Events](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Shutdown%20Events)
    - [Portal Events](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Portal%20Events)

    Note the property name of the event related to your action. 

2.  In your hook project, create a Java class that extends the
    `com.liferay.portal.kernel.events.Action` class. Override the
    `Action.run(HttpServletRequest, HttpServletResponse)` method.

    For example, here's a class named `LoginAction` that extends `Action` and
    overrides its `run` method:

        package com.liferay.sample.hook;

        import com.liferay.portal.kernel.events.Action;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class LoginAction extends Action {
            public void run(HttpServletRequest req, HttpServletResponse res) {
                System.out.println("## My custom login action");
            }
        }

    +$$$

    **Important:** If your action access the `HttpServletRequest` object, extend
    `com.liferay.portal.kernel.events.Action`; otherwise, extend
    `com.liferay.portal.struts.SimpleAction`. 

    $$$

2.  Create a properties file, `portal.properties`, inside your hook project's
    `docroot/WEB-INF/src` folder. Then add the name of the portal event property
    that corresponds to the event on which you want to perform your action.
    Specify your action class' fully qualified name as the property's value. 

    For example, to perform a class' action just prior to the portal logging in
    a user, you'd specify the `login.events.pre` property with your action class
    as its value. It could look like this property setting. 

        login.events.pre=com.liferay.sample.hook.LoginAction

    +$$$

    **Important:** Since portal properties like `login.events.pre` accept
    *multiple* values, you must append your values to the existing values. You
    can repeatedly modify the properties from additional hooks. 

    Only modify a portal property that accepts a single value from a single
    hook plugin. If you modify a property's value from multiple plugins, Liferay
    won't know which value to use. 

    $$$

3.  Edit your `docroot/WEB-INF/liferay-hook.xml` file and add your hook's portal
    properties file name as the value for the
    `<portal-properties>...</portal-properties>` element within your hook's
    `<hook>...</hook>` element.

    For example, if your hook's properties file name is `portal.properties`,
    you'd specify this element: 

        <portal-properties>portal.properties</portal-properties>

4.  Deploy your hook.

5.  Perform steps to trigger your action and verify that the action was
    executed. 

+$$$

**Warning:** Due to a known bug
([LPS-52754](https://issues.liferay.com/browse/LPS-52754)), a problem can occur
when overriding Struts actions with overlapping paths. Struts action paths
overlap when one path is a substring of another path. The following example
from Liferay's `struts-config.xml` file shows three Struts action paths. Notice
that the first Struts action path is a substring of each of the last two.

    <action path="/document_library/edit_file_entry" ...
    </action>

    <action path="/document_library/edit_file_entry_discussion" ...
    </action>

    <action path="/document_library/edit_file_entry_type" ...
    </action>

Suppose you create a hook plugin to override the
`/document_library/edit_file_entry` path. Due to the bug mentioned above, your
hook's new, custom action is triggered not only when the intended path is
invoked, but also when one of the larger, containing paths (e.g.,
`document_library/edit_file_entry_discussion`) is invoked!

To work around this issue, use the following steps:

1. Find any Struts actions with paths that contain the path of the Struts
action that you are overriding.

2. If any offending paths are found, create a `<struts-action>` for them in
your `liferay-hook.xml`.

3. In the class you create for each Struts action, override only the
`processAction`, `render`, and `serveResource` methods.

4. In each overridden method, simply call the original Struts action's method
(e.g., `originalStrutsPortletAction.processAction`).

$$$

Great! You've created a hook that triggers a custom action on a common portal
event. You now know the basic steps required to perform a custom action using a
hook in Liferay Portal.


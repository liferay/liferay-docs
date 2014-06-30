# Performing a Custom Action Using a Hook [](id=performing-a-custom-action-using-a-hook-lp-6-2-develop-tutorial)

<!-- The custom-action-hook project can be found here:
https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/custom-action/end/custom-action-hook
-->

Hooks are useful for triggering custom actions on common portal events, like
user login or system startup. The actions for each of these events are defined
in `portal.properties`, so you need to extend this file to create a custom
action. Hooks make this a simple task. It's time to explore how to perform a
custom action using a hook! 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** Hooks support customizing a
 specific list of predefined properties.  For a list of portal properties that
 can be overridden via hook, see the
 [liferay-hook_6_2_0.dtd](http://docs.liferay.com/portal/6.2/definitions). In
 addition to defining custom actions, hooks can override portal properties to
 define model listeners, validators, generators, and content sanitizers. If you
 want to customize a property that's not found in this list, you must use an Ext
 plugin (see [Advanced Customization with Ext Plugins](http://www.liferay.com)).
 For more information about the properties themselves, you can view an online
 version of Liferay's [portal
 properties](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
 file.

---

## Triggering a Custom Action [](id=triggering-a-custom-action-lp-6-2-develop-tutorial)

In this tutorial, you'll create a custom hook that performs a custom action for
user login. You could similarly create a custom action for other portal events
defined in `portal.properties`. It's time to get started! 

1.  Determine the event on which you want to trigger your custom action. Look
    in the [`portal.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
    documentation to find the matching event property. Hint: the event properties
    have `.event` in their name. There are session, startup, shutdown, and
    portal event properties in the following sections of the `portal.properties`
    documentation: 
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

    ---

    ![Important](../../images/tip-pen-paper.png) **Important:** If your action
    access the `HttpServletRequest` object, extend
    `com.liferay.portal.kernel.events.Action`; otherwise, extend 
    `com.liferay.portal.struts.SimpleAction`. 

    ---

2.  Create a properties file, `portal.properties`, inside your hook project's
    `docroot/WEB-INF/src` folder. Then add the name of the portal event property
    that corresponds to the event on which you want to perform your action. Specify
    your action class' fully qualified name as the property's value. 

    For example, to perform a class' action just prior to the portal logging in
    a user, you'd specify the `login.events.pre` property with your action class
    as its value. It could look like this property setting: 

        login.events.pre=com.liferay.sample.hook.LoginAction
 
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

---

 ![Important](../../images/tip-pen-paper.png) **Important:** The 
 `login.events.pre` portal property accepts *multiple* values, so your value was 
 appended to the existing `login.events.pre` values. You can repeatedly modify 
 the property from additional hooks because it accepts multiple values. Some 
 portal properties only accept a *single* value, such as the 
 `terms.of.use.required` property, which is either `true` or `false`. Only 
 modify single value properties from a single hook plugin; otherwise Liferay 
 won't know which value to use.

---

Great! You've created a hook that triggers a custom action on a common portal
event. You now know the basic steps required to perform a custom action using a
hook in Liferay Portal.

<!-- This tutorial is missing Next Steps. -Rich -->


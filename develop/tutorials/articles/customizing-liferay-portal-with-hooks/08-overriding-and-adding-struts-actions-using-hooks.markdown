# Overriding and Adding Struts Actions [](id=overriding-and-adding-struts-actions)

<!--
Testing Notes:

On completing the first part of this tutorial, the plugin looks like the hook plugin at ...
liferay-docs/develop/tutorials/tutorials-sdk-6.2-ga3/hooks/struts-actions-overriding-example-hook

On completing the second part of this tutorial, the plugin looks like the hook plugin at ...
liferay-docs/develop/tutorials/tutorials-sdk-6.2-ga3/hooks/struts-actions-adding-example-hook2-hook

Make sure to read their README files. - Jim
-->

Do you want to override Liferay's Struts actions or add a new Struts action to
Liferay? You can do both using Struts action hooks. 

First, consider the interfaces used for Struts actions:

- [`com.liferay.portal.kernel.struts.StrutsAction`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/struts/StrutsAction.html)
- [`com.liferay.portal.kernel.struts.StrutsPortletAction`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/struts/StrutsPortletAction.html)

The `StrutsAction` interface is used with portal Struts actions, like
`/c/portal/update_email_address`. The `StrutsPortletAction` interface is used
with portlet Struts actions. 

Struts actions are classes, and they're specified in a `struts-config.xml` file.
Liferay Portal's
[`struts-config.xml`](https://github.com/liferay/liferay-portal/blob/6.2.2-ga3/portal-web/docroot/WEB-INF/struts-config.xml)
file resides in its `WEB-INF` folder. It links actions to JSPs or tiles. Each
action performs a task and then returns a *forward*, an object containing a name
and path. The forward defines the page the portal sends the user to after the
action completes. When a user submits a form that maps to a Struts action, the
portal loads and executes the action class' method, which returns a forward. 

You can use a Struts action hook plugin to override/wrap existing Struts actions
or to create a new Struts action and path; you'll do both in this tutorial.
You'll override a Struts action to use your own class. Then you'll create a new
Struts action path and navigate to it. It's time to begin! 

## Overriding Struts Actions [](id=overriding-struts-actions)

For the first part of this tutorial, you'll override a portlet's existing Struts
action using a [hook plugin project](/develop/tutorials/-/knowledge_base/6-2/creating-a-hook-project-in-the-plugins-sdk). 


You can view the current actions in your portal's [`struts-config.xml`](https://github.com/liferay/liferay-portal/blob/6.2.2-ga3/portal-web/docroot/WEB-INF/struts-config.xml)
file. For example, here's the default login Struts action:

    <action path="/login/login"
        type="com.liferay.portlet.login.action.LoginAction">

        <forward
            name="portlet.login.login"
            path="portlet.login.login"
        />
        <forward
            name="portlet.login.login_redirect"
            path="portlet.login.login_redirect"
        />
    </action>

You can override a portlet's Struts action like the one above by following these
steps: 

1.  Open the `liferay-hook.xml` file from your hook plugin's `docroot/WEB-INF`
    folder. 

2.  Add a `struts-action` element within the `hook` element.

3.  Add to the `struts-action` element, a `struts-action-path` element with a
    value that specifies the action path you're overriding and add a
    `struts-action-impl` element with a value that specifies your custom action
    class.

    The following Struts action from example hook plugin [example-hook](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/hooks/struts-actions-overriding-example-hook)
    specifies a custom Struts portlet action class called
    `ExampleStrutsPortletAction`. This action class is invoked for the Struts
    action path `/login/login`. The example hook's `struts-action` element looks
    like this: 

        <struts-action>
            <struts-action-path>/login/login</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsPortletAction
            </struts-action-impl>
        </struts-action>

    The DTD for `liferay-hook.xml` is
    [here](https://docs.liferay.com/portal/6.2/definitions/liferay-hook_6_2_0.dtd.html).

4.  In your hook plugin's `docroot/WEB-INF/src` folder, create a Struts portlet
    action class and its package folders. 

    Here's an example Struts portlet action class:

        package com.liferay.sample.hook.action;

        import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
        import com.liferay.portal.kernel.struts.StrutsPortletAction;
        import com.liferay.portal.theme.ThemeDisplay;
        import com.liferay.portal.kernel.util.WebKeys;
        import javax.portlet.ActionRequest;
        import javax.portlet.ActionResponse;
        import javax.portlet.PortletConfig;
        import javax.portlet.RenderRequest;
        import javax.portlet.RenderResponse;
        import javax.portlet.ResourceRequest;
        import javax.portlet.ResourceResponse;

        public class ExampleStrutsPortletAction extends BaseStrutsPortletAction {

            public void processAction(
                    StrutsPortletAction originalStrutsPortletAction,
                    PortletConfig portletConfig, ActionRequest actionRequest,
                    ActionResponse actionResponse)
                throws Exception {
                ThemeDisplay themeDisplay =
                    (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

                Long currentuser = themeDisplay.getUserId();

                if (currentuser != null) {
                    System.out.println("Custom Struts Action 2");

                }
                originalStrutsPortletAction.processAction(
                    originalStrutsPortletAction, portletConfig, actionRequest,
                    actionResponse);
            }

            public String render(
                    StrutsPortletAction originalStrutsPortletAction,
                    PortletConfig portletConfig, RenderRequest renderRequest,
                    RenderResponse renderResponse)
                throws Exception {

                System.out.println("Custom Struts Action");

                return originalStrutsPortletAction.render(
                    null, portletConfig, renderRequest, renderResponse);

            }

            public void serveResource(
                    StrutsPortletAction originalStrutsPortletAction,
                    PortletConfig portletConfig, ResourceRequest resourceRequest,
                    ResourceResponse resourceResponse)
                throws Exception {

                originalStrutsPortletAction.serveResource(
                    originalStrutsPortletAction, portletConfig, resourceRequest,
                    resourceResponse);

            }

        }

Notice that the class extends the base class [`BaseStrutsPortletAction`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/struts/BaseStrutsPortletAction.html).
The `render` method of class above prints to the console the message *Custom
Struts Action* every time a user logs in.

![Figure 1: You can override a Struts action with custom behaviors. This example Struts action hook prints a message to the console every time a user logs in.](../../images/struts-action-console.png)

As a best practice in extending Struts actions, consider invoking the base class
methods that you override. 

That's it for overriding Struts actions! The next section of this tutorial
demonstrates how to add a new Struts action. 

## Adding Struts Actions [](id=adding-struts-actions)

Now that you know how to override a Struts action, you might also be interested
in adding a new Struts action. Here's how you do it:

1.  If you like, create a folder for the new action's JSP. The example hook
    plugin example-hook2-hook on [GitHub](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/hooks/struts-actions-adding-example-hook2-hook),
    stores its custom JSP in a folder `docroot/META-INF/custom_jsps/html/portal`. 

2.  Add a JSP for your new Struts action. For example, the following code from
    the example-hook2-hook plugin's JSP greets the user:

        <%
        String name = (String)request.getAttribute("name");
        %>
        Hello <%= name %>!

3.  Create a new Struts action class. Consider extending [`BaseStrutsPortletAction`](https://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/kernel/struts/BaseStrutsPortletAction.html).
    As an example, here's the code from the example-hook2-hook plugin's Struts
    action class [`ExampleStrutsAction`](https://github.com/liferay/liferay-docs/blob/6.2.x/develop/tutorials/tutorials-sdk-6.2-ga3/hooks/struts-actions-adding-example-hook2-hook/docroot/WEB-INF/src/com/liferay/sample/hook/action/ExampleStrutsAction.java):

        package com.liferay.sample.hook.action;

        import com.liferay.portal.kernel.struts.BaseStrutsAction;
        import com.liferay.portal.kernel.util.ParamUtil;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class ExampleStrutsAction extends BaseStrutsAction {

            public String execute(
                HttpServletRequest request, HttpServletResponse response)
                throws Exception {
    
                String name = ParamUtil.get(request, "name", "World");
    
                request.setAttribute("name", name);
    
                return "/portal/sample.jsp";
            }

        }

    Notice that the `ExampleStrutsAction` class's `execute` method sets the
    request attribute `name` to the current user's name, or to *World* if the
    user's name isn't accessible. 

    This class overrides `BaseStrutsAction`'s `execute(HttpServletRequest,
    HttpServletResponse)` method, but not its `execute(StrutsAction,
    HttpServletRequest, HttpServletResponse)` method. You'd typically override
    both methods. It's fine to ignore overriding that method in this example
    hook. 

4.  Open the `liferay-hook.xml` file from the hook plugin's `docroot/WEB-INF`
    folder. 

5.  If you're using a custom JSP folder, add within the `hook` element a
    `custom-jsp-dir` element that specifies the JSP folder path as its value.
    Within the `hook` element, just after the `custom-jsp-dir` element (if you
    added one), add a `struts-action` element. Within the `struts-action`
    element, add a `struts-action-path` element with a value that specifies the
    action path you're overriding and add a `struts-action-impl` element with a
    value that specifies your custom action class. 

    The following example Struts action from the example-hook2-hook plugin
    specifies Struts action path `/custom/path` for invoking the custom Struts
    portlet action class `ExampleStrutsPortletAction`. 

        <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
        <struts-action>
            <struts-action-path>/custom/path</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsAction
            </struts-action-impl>
        </struts-action>

6.  If you'd like your Struts action path to be public, add it to 
    your portal's list of paths that don't require authentication. Do this by 
    copying your existing [`auth.public.paths`](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Authentication%20Pipeline)
    property assignment from your portal's `portal.properties` into your
    `portal-ext.properties` file. Then add your Struts action path to the end of
    the value list. It looks similar to the assignment below, where you'd replace
    `/custom/path` with your custom Struts action path: 

            auth.public.paths=\
                /asset/get_categories,\
                ...
                /wiki/rss,\
                /custom/path

7.  Restart the portal server and try your new Struts path.

8.  Open your custom Struts action's JSP in your browser. The JSP for example
    hook plugin example-hook2-hook can be accessed from a browser at the path
    you specified: `http://localhost:8080/c/portal/sample`. 

![Figure 2: The example-hook2-hook example hook plugin's new Struts action JSP displays *Hello World!*.](../../images/struts-action-display.png)

Great! Now you know about the interfaces used for Struts actions. You also 
learned how to override an existing action and create a new action using a 
hook. You're ready to strut your stuff! 

## Related Topics [](id=related-topics)

[Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal with Hooks](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

## Overriding and Adding Struts Actions

<!-- The hook that overrides a Struts action and the hook that adds a new Struts
path are two separate hooks:
Override: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/struts-actions/override/example-hook
Add: https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/plat-fws/struts-actions/add/example-hook2-hook
-->

Do you want to add a new Struts action to Liferay or override existing Struts
actions? *Struts action hooks* let you do just that. 

First, consider the interfaces used for Struts actions. There are two:

- `com.liferay.portal.kernel.struts.StrutsAction`
- `com.liferay.portal.kernel.struts.StrutsPortletAction`

The `StrutsAction` interface is for regular Struts actions from the portal, like
`/c/portal/update_email_address`. The `StrutsPortletAction` interface is used
for similar Struts actions, but from portlets. 

Struts actions are defined as classes, and they're all connected in a
`struts-config.xml` file. The `struts-config.xml` for a Liferay Portal instance
running on Apache Tomcat can be found in the
`liferay-portal-[version]/tomcat-[version]/webapps/ROOT/WEB-INF` directory. The
`struts-config.xml` file links actions to specific JSP pages. Each action
performs a specific task and then returns a *forward*, an object containing a
name and path. The forward defines the page the portal sends the user to after
the action completes. When a user submits a form that maps to one of these
actions, the action class is loaded, executed, and returns a forward. 

A Struts action hook can wrap or override existing Struts actions or create a
new Struts path; you'll complete both in this tutorial. You'll override the
Struts actions in the `struts-config.xml` file by using a Struts action hook to
point to a custom class. Then you'll create a new Struts path and navigate to
it. It's time to begin! 

## Overriding Struts Actions

For the first part of this tutorial, you'll override a portlet's existing Struts
action using your custom hook project. 

You can view the current actions in your portal's `struts-config.xml` file. For
example, the following is the default login action:

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

By following the steps below, you can override a portlet's Struts action:

1. Navigate to the `your-hook/docroot/WEB-INF` folder and open
    `liferay-hook.xml`. 

2. Insert the following code between the `<hook>...</hook>` tags:

        <struts-action>
            <struts-action-path>[action_path_to_override]</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsPortletAction
            </struts-action-impl>
        </struts-action>

3. Create a new package `com.liferay.sample.hook.action` in your
   `example-hook/docroot/WEB-INF/src` folder. 

4. In your new package, create a class named *ExampleStrutsPortletAction*, which
   modifies the portlet Struts action. Your class should look similar to the one
   listed below:

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

    Notice the `ExampleStrutsPortletAction` class extends the
    [BaseStrutsPortletAction](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/struts/BaseStrutsPortletAction.java)
    class. The example class prints an example line of text every time the
    specified Struts path is triggered.

When this hook is deployed, you'll observe the printed lines in your console
that your example class generated. For the figure below, the `login/login`
Struts action path was overridden, and prints out custom made lines whenever a
user logs in, or accesses the *Sign In* portlet.

![Figure 1: The overridden Login Struts action displays custom printed lines in the console.](../../images/struts-action-console.png)

For best practice, when overriding an existing Struts action, it's usually best
to override the method that takes the original Struts action handle as a
parameter and execute that original Struts action. Think of the original action
as a servlet filter or aspect. If you override the method that *takes* the
original action handle as a parameter and don't explicitly execute it, the
original action won't be executed. If you override the `execute` method that
*does not take* the original action as a parameter, you are ignoring the
original action and it won't be executed. 

That's it for overriding Struts actions! The next section of this tutorial
demonstrates how to create a new Struts action path. 

## Adding Struts Actions

Now that you know how to override a Struts action, you'll learn how to add a new
Struts action to your custom hook next.

1. Navigate to the `your-hook/docroot/WEB-INF` folder and open
    `liferay-hook.xml`. 

2. Insert the following code between the `<hook>...</hook>` tags:

        <custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
        <struts-action>
            <struts-action-path>/custom/path</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsAction
            </struts-action-impl>
        </struts-action>

3. In your custom hook, create the
   `yourhook/docroot/META-INF/custom_jsps/html/portal` directory (if necessary).

4. Create a `sample.jsp` file in the
   `your-hook/docroot/META-INF/custom_jsps/html/portal` directory and insert
   what you'd like your new Struts path to accomplish. For an example, a simple
   code snippet is listed below, which prints Hello [user_name] to the screen.

        <%
        String name = (String)request.getAttribute("name");
        %>
        Hello <%= name %>!

5. If you'd like your Struts path to be public, add `/custom/path` to your
   portal's list of paths that don't require authentication by copying your
   existing `auth.public.paths` property assignment from your portal's
   `portal.properties` into your `portal-ext.properties` file and adding
   `/custom/path` to the end of the value list. It looks similar to the
   assignment below: 

            auth.public.paths=\
                /asset/get_categories,\
                ...
                /wiki/rss,\
                /custom/path
   
6.  Create a new class named `ExampleStrutsAction` in the
    `com.liferay.sample.hook.action` package. It will implement your new portal
    Struts action. Below is sample code to provide what this might look like:

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

    Notice the `ExampleStrutsAction` class extends
    [BaseStrutsAction](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/struts/BaseStrutsAction.java).
    When the `execute()` method is called, the `name` string is initialized and
    set to the user's name, or *World* if the user's name could not be found.

    The class has overridden the `execute(HttpServletRequest,
    HttpServletResponse)` method of `BaseStrutsAction`, but not the
    `execute(StrutsAction, HttpServletRequest, HttpServletResponse)` method. The
    original Struts action's `execute()` method is ignored. That's fine for this
    example.

7. Restart your portal server and try your new Struts path. The example code
   provided can be accessed from a browser by inserting the custom path URL
   (e.g., `http://localhost:8080/c/portal/sample`). 

    ![Figure 2: The new Struts action displays *Hello World!* in your browser.](../../images/struts-action-display.png)

In this tutorial, you learned about the interfaces used for Struts actions.
Then, you learned how to override an exisiting action and create a new action
using a hook.

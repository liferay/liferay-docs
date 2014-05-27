## Overriding and Adding Struts Actions [](id=override-and-add-struts-actions-with-hook-liferay-portal-6-2-dev-guide-en)

Do you want to add a new Struts action to Liferay or override existing Struts
actions? *Struts action hooks* let you do just that. 

Let's consider the interfaces used for Struts actions. There are two:

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
new Struts path; we'll demonstrate both here. We'll override the Struts actions
in the `struts-config.xml` file by using a Struts action hook to point to a
custom class. Then we'll create a new Struts path: `/c/portal/sample` and
navigate to it. Let's get started! 

First, let's override the Sign In portlet's Struts action using the example-hook
project that we've been using in this chapter. 

Here's the current action in your portal's `struts-config.xml` file:

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

1.  Navigate to your `example-hook/docroot/WEB-INF` folder and open
    `liferay-hook.xml`. 

2.  Insert the following code between the `<hook>...</hook>` tags:

		<portal-properties>portal.properties</portal-properties>
		<custom-jsp-dir>/META-INF/custom_jsps</custom-jsp-dir>
        <struts-action>
            <struts-action-path>/portal/sample</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsAction
            </struts-action-impl>
        </struts-action>
        <struts-action>
            <struts-action-path>/login/login</struts-action-path>
            <struts-action-impl>
            com.liferay.sample.hook.action.ExampleStrutsPortletAction
            </struts-action-impl>
        </struts-action>

3.  Create a new package `com.liferay.sample.hook.action` in your
    `example-hook/docroot/WEB-INF/src` folder. 

4.  In your new package, create a class named *ExampleStrutsPortletAction*, which
    will wrap the login portlet Struts action. Insert the following code:

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
                    System.out.println("Wrapped /login/ action2");

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

                System.out.println("Wrapped /login/ action");

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

5.  Create a new class named `ExampleStrutsAction` in the
    `com.liferay.sample.hook.action` package. It will implement your new portal
    Struts action. Insert the following code:

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

We've overridden the `execute(HttpServletRequest, HttpServletResponse)` method
of `BaseStrutsAction`, but not the `execute(StrutsAction, HttpServletRequest,
HttpServletResponse)` method. The original Struts action's `execute()` method
is ignored. That's fine for our example.

---

 [Note](../../images/tip.png) **Best practice**: When overriding an existing
 Struts action, it's usually best to override the method that takes the original
 Struts action handle as a parameter and execute that original Struts action.
 Think of the original action as a servlet filter or aspect. If you override the
 method that *takes* the original action handle as a parameter and don't
 explicitly execute it, the original action won't be executed. If you override
 the `execute` method that *does not take* the original action as a parameter,
 you are ignoring the original action and it won't be executed.

---

That's it for overriding the Struts actions! Now let's get our new Struts
path working. 

1.  Create a `sample.jsp` file in the
    `example-hook/docroot/META-INF/custom_jsps/html/portal` directory. Insert
    the following code:

        <%
        String name = (String)request.getAttribute("name");
        %>
        Hello <%= name %>!

2.  Add `/portal/sample` to your portal's list of paths that don't require
    authentication by copying your existing `auth.public.paths` property
    assignment from your portal's `portal.properties` into your
    `portal-ext.properties` file and adding `/portal/sample` to the end of the
    value list. It looks similar to the assignment below:

            auth.public.paths=\
                /asset/get_categories,\
                ...
                /wiki/rss,\
                /portal/sample

3.  Restart your portal server. 

Congratulations! Your Struts action hook plugin is complete! Now when you access
the *Sign In* portlet, this message prints to your console:

    Wrapped /login/ action

When you actually log in, this message prints to your console:

    Wrapped /login/ action2
    Wrapped /login/ action

Both custom Struts actions are executed via your Struts action hook! 

Try your new Struts path by accessing it from your browser (e.g.
`http://localhost:8080/c/portal/sample`). 

![Figure 11.4: Your new Struts action displays *Hello World!* in your browser.](../../images/06-hooks-5.png)

Let's continue our hooks expedition by overriding a portal service.

# Performing a Custom Action [](id=perform-a-custom-action-with-hook-liferay-portal-6-2-dev-guide-en)

Hooks are useful for triggering custom actions on common portal events, like
user login or system startup. The actions for each of these events are defined
in `portal.properties` so we need to extend this file to create a custom action.
Hooks make this a simple task.

1.  In your hook project, create the directory
    `docroot/WEB-INF/src/com/liferay/sample/hook` and create a file called
    `LoginAction.java` inside it with this content:

        package com.liferay.sample.hook;

        import com.liferay.portal.kernel.events.Action;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class LoginAction extends Action {
            public void run(HttpServletRequest req, HttpServletResponse res) {
                System.out.println("## My custom login action");
            }
        }

2.  Create a `portal.properties` file inside your hook project's
    `docroot/WEB-INF/src` folder with this content:

        login.events.pre=com.liferay.sample.hook.LoginAction

3.  Edit your `docroot/WEB-INF/liferay-hook.xml` file, adding the following line
    above `<custom-jsp-dir>`:

        <portal-properties>portal.properties</portal-properties>

4.  Redeploy your hook. Once deployment is complete, log out and back in, and
    you should see your custom message, *## My custom login action*, displayed
    in the terminal window running Liferay. 

Custom action hook plugins aren't limited to the login event. You can define
custom actions for other events, too. For actions that require access to the
`HttpServletRequest` object, extend `com.liferay.portal.kernel.events.Action`;
for others, extend `com.liferay.portal.struts.SimpleAction`.

---

 ![Important](../../images/tip-pen-paper.png) **Important:** For better forward
 compatibility, use hooks to customize Struts actions rather than Ext plugins.

---

Extending and overriding portal properties is just as easy, so let's do that
next.

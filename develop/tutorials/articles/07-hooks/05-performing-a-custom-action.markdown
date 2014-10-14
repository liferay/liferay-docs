# Performing a Custom Action [](id=performing-a-custom-action)

Hooks are useful for triggering custom actions on common portal events,
like user log in or system start up. The actions for each of these events are
defined in `portal.properties`, so we'll need to extend this file to create a
custom action. Hooks make this a simple task.

1.  Create the directory
    `example-hook/docroot/WEB-INF/src/com/liferay/sample/hook`, and create the
    file `LoginAction.java` inside it with this content:

        package com.liferay.sample.hook;

        import com.liferay.portal.kernel.events.Action;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

        public class LoginAction extends Action {
            public void run(HttpServletRequest req, HttpServletResponse res) {
                System.out.println("## My custom login action");
            }
        }

2.  Create a `portal.properties` file inside `example-hook/docroot/WEB-INF/src`
    with this content:

        login.events.pre=com.liferay.sample.hook.LoginAction

3.  Edit `liferay-hook.xml` inside `example-hook/docroot/WEB-INF`, adding the
    following line above `<custom-jsp-dir>`:

        <portal-properties>portal.properties</portal-properties>

4.  Redeploy your hook. Once deployment is complete, log out and back in, and
    you should see your custom message, *## My custom login action*, displayed
    in the terminal window running Liferay. 

Custom action hook plugins aren't limited to the log in event. You can define
custom actions for other events, too. For actions that require access to the
`HttpServletRequest`, extend `com.liferay.portal.kernel.events.Action`; for
others, extend `com.liferay.portal.struts.SimpleAction`. 

+$$$

**Important:** For better forward compatibility, use hooks to customize Struts
actions rather than Ext plugins. For more information, see the
[Properties Reference](http://docs.liferay.com/portal/6.1/propertiesdoc/portal.properties.html)
document. 

$$$

Extending and overriding portal properties is just as easy, so let's do that
next.

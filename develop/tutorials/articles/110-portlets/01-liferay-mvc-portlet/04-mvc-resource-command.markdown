# MVC Resource Command [](id=mvc-resource-command)

When using Liferay's MVC framework, you can create resource URLs in your JSPs to 
retrieve images, XML, or any other kind of resource from a Liferay instance. The 
resource URL then invokes the corresponding MVC resource command class 
(`*MVCResourceCommand`) that processes the resource request and response. 

First, [configure your view layer](configuring-the-view-layer#configuring-the-view-layer)
and use the [`<portlet:resourceURL>` tag](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/resourceURL.html)
to create the resource URL in a JSP. For example, the Login Portlet's
`/login-web/src/main/resources/META-INF/resources/navigation/create_account.jsp`
file defines the following resource URL for retrieving a CAPTCHA image during
account creation: 

    <portlet:resourceURL id="/login/captcha" var="captchaURL" />

The `id` attribute declares the resource URL. The `var` attribute declares a
variable to hold the portlet resource URL object. Assign that variable to a UI
component, such as a button or icon. When the user triggers the UI component,
the `*MVCResourceCommand` class that matches the resource URL processes the
resource request and response. You can create this class by implementing the
[`MVCResourceCommand` interface](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)
or extending the [`BaseMVCResourceCommand`
class](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCResourceCommand.html).
The latter may save you time, since it already implements `MVCResourceCommand`. 

Also, it's a good idea to name your `*MVCResourceCommand` class after the
resource it handles and suffix it with `MVCResourceCommand`. For example, the
resource command class matching the preceding CAPTCHA resource URL in the Login
Portlet is
[`CaptchaMVCResourceCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/login/login-web/src/main/java/com/liferay/login/web/internal/portlet/action/CaptchaMVCResourceCommand.java).
In an application with several MVC command classes, this helps differentiate
them.

Your `*MVCResourceCommand` class must also have an `@Component` annotation like
the following example. Set the property `javax.portlet.name` to your portlet's
internal ID, and the property `mvc.command.name` to the value of the `id`
property in your JSP's matching `resourceURL`. To register the component in the
OSGi container as using the `MVCResourceCommand` class, you must set the
`service` property to `MVCResourceCommand.class`: 

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=your_portlet_name_YourPortlet",
            "mvc.command.name=/your/jsp/resource/url"
        },
        service = MVCResourceCommand.class
    )
    public class YourMVCResourceCommand implements MVCResourceCommand {
        // your resource handling code
    }

As a real-world example, consider the Login Portlet's
`CaptchaMVCResourceCommand` class (find this class in the Liferay source code at
`modules/apps/login/login-web/src/main/java/com/liferay/login/web/internal/portlet/action/CaptchaMVCResourceCommand.java`): 

    @Component(
        property = {
            "javax.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
            "javax.portlet.name=" + LoginPortletKeys.LOGIN,
            "mvc.command.name=/login/captcha"
        },
        service = MVCResourceCommand.class
    )
    public class CaptchaMVCResourceCommand implements MVCResourceCommand {

        @Override
        public boolean serveResource(
            ResourceRequest resourceRequest, ResourceResponse resourceResponse) {
            
            try {
                CaptchaUtil.serveImage(resourceRequest, resourceResponse);

                return false;
            }
            catch (Exception e) {
                _log.error(e, e);
                
                return true;
            }
        }

        private static final Log _log = LogFactoryUtil.getLog(
            CaptchaMVCResourceCommand.class);
    }

In the `@Component` annotation, note that `javax.portlet.name` has two different 
settings. This lets multiple portlets use the same component. In this example, 
the portlet IDs are defined as constants in the
[`LoginPortletKeys` class](@app-ref@/foundation/latest/javadocs/com/liferay/login/web/internal/constants/LoginPortletKeys.html).
Also note that the `mvc.command.name` property setting `/login/captcha` matches
the `resourceURL`'s `id` setting shown earlier in this tutorial, and that the
`service` property is set to `MVCResourceCommand.class`. 

The `CaptchaMVCResourceCommand` class implements the `MVCResourceCommand`
interface with only a single method: `serveResource`. This method processes the
resource request and response via the `javax.portlet.ResourceRequest` and
`javax.portlet.ResourceResponse` parameters, respectively. Note that the `try`
block uses the helper class 
[`CaptchaUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/captcha/CaptchaUtil.html)
to serve the CAPTCHA image. Though you don't have to create such a helper
class, doing so often simplifies your code. 

Great! Now you know how to use `MVCResourceCommand` to process resources in your
Liferay MVC portlets. 

## Related Topics [](id=related-topics)

[Creating an MVC Portlet](/develop/tutorials/-/knowledge_base/7-1/creating-an-mvc-portlet)

[MVC Render Command](/develop/tutorials/-/knowledge_base/7-1/mvc-render-command)

[MVC Action Command](/develop/tutorials/-/knowledge_base/7-1/mvc-action-command)

[MVC Command Overrides](/develop/tutorials/-/knowledge_base/7-1/overriding-mvc-commands)

[OSGi Basics for Liferay Development](/develop/tutorials/-/knowledge_base/7-1/osgi-basics-for-liferay-development)

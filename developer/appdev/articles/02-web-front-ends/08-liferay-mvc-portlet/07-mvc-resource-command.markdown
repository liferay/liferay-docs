---
header-id: mvc-resource-command
---

# MVC Resource Command

[TOC levels=1-4]

When using Liferay's [MVCPortlet
framework](/docs/7-2/appdev/-/knowledge_base/a/liferay-mvc-portlet), you can
create resource URLs in your JSPs to retrieve images, XML, or any other kind of
resource from a @product@ instance. The resource URL then invokes the
corresponding MVC resource command class (`*MVCResourceCommand`) that processes
the resource request and response. 

Here how to create your own MVC Resource Command:

1.  [Configure your JSPs](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)
    to generate resource URLs via
    [`<portlet:resourceURL>`](@platform-ref@/7.2-latest/taglibs/util-taglib/portlet/resourceURL.html)
    tags. 

    For example, this
    [resource-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/resource-command-portlet)
    sample resource URL invokes an MVC resource command named `/blade/captcha`.

    ```jsp
    <portlet:resourceURL id="/blade/captcha" var="captchaURL" />
    ```

2.  Name the resource URL via its `id` attribute. 

3.  Assign the resource URL's `var` attribute a variable name to pass to a 
    UI component. 

4.  Assign the resource URL variable (`var`) to a UI component, such as a button
    or icon. When the user triggers the UI component, the `*MVCResourceCommand`
    class that matches the resource URL handles the resource request.

    For example, the sample's resource URL is triggered when the user clicks on
    this
    [`liferay-captcha`](@app-ref@/foundation/latest/taglibdocs/liferay-captcha/captcha.html)
    component:

    ```jsp
    <liferay-captcha:captcha url="<%= captchaURL %>" />
    ```

5.  Create a class that implements the
    [`MVCResourceCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)
    interface, or that extends the
    [`BaseMVCResourceCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCResourceCommand.html)
    class. The latter may save you time, since it already implements
    `MVCResourceCommand`. 

    | **Tip:** Naming your `*MVCResourceCommand` class after the resource it 
    | provides makes the resource mappings more obvious for maintaining the
    | code. For example, if your resource URL serves a captcha, you could name
    | its class `CaptchaMVCResourceCommand`. If your application has several MVC
    | command classes, naming them this way helps differentiate them. 

6.  Annotate your class with an
    [`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html)
    annotation, like this one:

    ```java
    @Component(
        property = {
            "javax.portlet.name=your_portlet_name_YourPortlet",
            "mvc.command.name=/your/jsp/resource/url"
        },
        service = MVCResourceCommand.class
    )
    public class YourMVCResourceCommand extends BaseMVCResourceCommand {
        // your resource handling code
    }
    ```

    1.  Set a `javax.portlet.name` property to your portlet's internal ID. 
 
    2.  Set the `mvc.command.name` property to your `<portlet:resourceURL>` 
        tag's `id`. This maps your class to the resource URL of the same name. 

    3.  Register your class as an `MVCResourceCommand` service by setting the
        `service` attribute to `MVCResourceCommand.class`. 


7.  Implement your resource logic by overriding the appropriate method of the 
    class you're implementing or extending. 

    -   [`MVCResourceCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html)
       implementations override the `serveResource` method. 

    -   [`BaseMVCResourceCommand`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCResourceCommand.html)
        extensions override the `doServeResource` method. 

For example, the
[resource-command-portlet](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/resource-command-portlet)'s
`CaptchaMVCResourceCommand` class implements the `MVCResourceCommand` interface
with only a single method: `serveResource`. 

```java
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_blade_samples_portlet_resourcecommand_CaptchaPortlet",
		"mvc.command.name=/blade/captcha"
	},
	service = MVCResourceCommand.class
)
public class CaptchaMVCResourceCommand implements MVCResourceCommand {

	@Override
	public boolean serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		if (_log.isInfoEnabled()) {
			_log.info("get captcha resource ");
		}

		try {
			CaptchaUtil.serveImage(resourceRequest, resourceResponse);

			return false;
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);

			return true;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CaptchaMVCResourceCommand.class);

}
```

This `serveResource` method processes the resource request and response via the
`javax.portlet.ResourceRequest` and `javax.portlet.ResourceResponse` parameters,
respectively. Note that the `try` block uses the helper class
[`CaptchaUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/captcha/CaptchaUtil.html)
to serve the CAPTCHA image. Though you don't have to create such a helper class,
doing so often simplifies your code. 

Great! Now you know how to use `MVCResourceCommand` to process resources in your
Liferay MVC Portlets. 

## Related Topics

[Creating an MVC Portlet](/docs/7-2/appdev/-/knowledge_base/a/creating-an-mvc-portlet)

[Configuring the View Layer](/docs/7-2/appdev/-/knowledge_base/a/configuring-the-view-layer)

[MVC Render Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-render-command)

[MVC Action Command](/docs/7-2/appdev/-/knowledge_base/a/mvc-action-command)

[MVC Command Overrides](/docs/7-2/customization/-/knowledge_base/c/overriding-liferay-mvc-commands)

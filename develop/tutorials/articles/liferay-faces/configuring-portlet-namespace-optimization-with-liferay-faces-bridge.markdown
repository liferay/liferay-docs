# Configuring Portlet Namespace Optimization with Liferay Faces Bridge

For this tutorial, you'll learn about configuring your portlet's namespace to
abide to the JSR 329 standard. 

---

 ![Note](../../images/tip.png) **Note:** Due to strict namespacing requirements
 introduced in Liferay Portal 6.2, the namespace optimization feature only works
 in Liferay Portal 5.2, 6.0, and 6.1. 

---

The JSR 329 standard requires the bridge implementation to prepend the portlet
namespace to every JSF view component's `id` attribute. This distinguishes the
component when there are multiple JSF portlets on a portal page that contain
similar component hierarchies and naming.  Also, the JSR 329 standard indicates
that the bridge implementation of the `ExternalContext.encodeNamesapce(String)`
method is to prepend the value of `javax.portlet.PortletResponse.getNamespace()`
to the specified `String`. The problem is that since the value returned by
`getNamespace()` can be a lengthy `String`, the size of the rendered HTML portal
page can become unnecessarily large. This can be especially non-performant when
using the `f:ajax` tag in a Facelet view to perform partial-updates to the
browser's DOM. 

Liferay Faces Bridge has a built-in optimization that minimizes the value
returned by the `ExternalContext.encodeNamespace(String)` method, while
still guaranteeing uniqueness. 

If you don't want to leverage the namespace optimization and instead want to
leverage the default behavior specified by JSR 329, you must set this value to
`false` in the `WEB-INF/web.xml` descriptor: 

    <context-param>
        <param-name>com.liferay.faces.bridge.optimizePortletNamespace</param-name>
        <param-value>false</param-value>
    </context-param>

The default value of the following `context-param` is `true`, meaning that
Liferay Faces Bridge will optimize the portlet namespace. Setting the value of
the `context-param` to `false` disables the optimization. 

That's it! Now you have a better understanding for configuring your portlet for
namespace optimization. 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->

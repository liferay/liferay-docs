# Module JSP Override [](id=module-jsp-override)

The Module JSP Override sample conveys Liferay's recommended approach to
override an application's JSP by leveraging OSGi fragment modules. This example
overrides the default `login.jsp` file in the `com.liferay.login.web` bundle by
adding the red text *changed* to the Sign In form.

![Figure 1: The customized Sign In form with the new *changed* text.](../../../images/hook-jsp.png)

You can create your own JSP override by

- Declaring the fragment host.
- Providing the JSP that will override the original one.

To properly declare the fragment host in the `bnd.bnd` file, you must specify
the host module's (where the original JSP is located) Bundle Symbolic Name and
the host module's exact version to which the fragment belongs. In this example,
this is configured like this:

    Fragment-Host: com.liferay.login.web;bundle-version="1.0.0"

Then you must provide the new JSP intended to override the original one. Be sure
to mimic the host module's folder structure when overriding its JAR. For this
example, since the original JSP is in the folder
`/META-INF/resources/login.jsp`, the new JSP file resides in the folder
`src/main/resources/META-INF/resources/login.jsp`.

If needed, you can also target the original JSP following one of the two
possible naming conventions: `original` or `portal`. This pattern looks like

    <liferay-util:include
        page="/login.original.jsp"
        servletContext="<%= application %>"
    />

or

    <liferay-util:include
        page="/login.portal.jsp"
        servletContext="<%= application %>"
    />

This approach can be used to override any application JSP (i.e., JSPs residing
in a module). If you need to override a core JSP, see the `blade.corejsphook`
sample.

For more information on using fragment bundles to override application JSPs, see
the
[Overriding App JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)
tutorial.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/overrides/module-jsp-override)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/overrides/module-jsp-override)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/overrides/module-jsp-override)

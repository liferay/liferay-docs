# Core JSP Override [](id=core-jsp-hook)

The Core JSP Override sample lets you override core/kernel JSPs by adding them
to the module's `META-INF/jsps` folder. This module overrides the `bottom.jsp`
file by inserting the `bottom-ext.jsp` file in the
`META-INF/jsps/html/common/themes` folder. When deploying this sample with no
customizations, sample text is added to the bottom of Liferay's default theme.
For more information on how to customize Liferay's Core using JSP overrides,
visit the
[Overriding Core JSPs](/develop/tutorials/-/knowledge_base/7-0/overriding-core-jsps)
tutorial.

![Figure 1: Deploying a core JSP override overrides core functionality, like @product@'s default theme.](../../../images/blade-core-jsp-hook.png)

**Note:** Using core JSP overrides should be a last resort option only when
there is no other way to customize functionality in your Liferay installation.
It's up to the maintainer of this JSP override to properly maintain and adapt to
changes in the underlying JSP implementation.

You can easily modify this sample by customizing its `BladeCustomJspBag` Java
class or adding additional JSPs in the configured JSP folder. You can modify the
custom JSP folder's path by editing the `BladeCustomJspBag.getCustomJspDir()`
method to return a different folder path.

For more information on customizing the Core JSP Override sample to fit your
needs, see the Javadoc listed in this sample's `BladeCustomJspBag` class.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/overrides/core-jsp-override)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/overrides/core-jsp-override)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/overrides/core-jsp-override)

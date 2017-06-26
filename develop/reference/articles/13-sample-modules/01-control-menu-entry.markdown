# Control Menu Entry [](id=control-menu-entry)

The Control Menu Entry sample provides a customizable button that is added to
Liferay's default Control Menu. When deploying this sample with no
customizations, an additional button is added to the User portion of the Control
Menu. For more information on how to customize Liferay's Control Menu, visit the
[Customizing the Control Menu](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/customizing-the-control-menu)
tutorial.

![Figure 1: The User area of the Control Menu is provided an additional link button when the Control Menu Entry Blade sample is deployed to Liferay.](../../images/controlmenuentry.png)

You can easily modify this sample by customizing its
`BladeProductNavigationControlMenuEntry` Java class. For example, the default
Control Menu Entry sample provides a `link` icon (i.e., a
[Lexicon](http://liferay.github.io/lexicon/content/icons-lexicon/) icon)
pointing to the [http://www.liferay.com/](http://www.liferay.com/) URL. Simply
change the return values of the `getIcon` and `getURL` methods to modify the
icon and URL, respectively. Likewise, to update the Control Menu area that your
sample resides in, update the `@Component` annotation's
`product.navigation.control.menu.category.key` property accordingly. There are
three areas in Liferay's Control Menu to which you can set this property, by
default:

- `ProductNavigationControlMenuCategoryKeys.Sites` (left)
- `ProductNavigationControlMenuCategoryKeys.Tools` (middle)
- `ProductNavigationControlMenuCategoryKeys.User` (right)

For more information on customizing the Control Menu Entry sample to fit your
needs, see the Javadoc listed in this sample's
`BladeProductNavigationControlMenuEntry` class.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/blade.controlmenuentry)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/modules/blade.controlmenuentry)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/blade.controlmenuentry)

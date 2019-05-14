# Customization with Ext [](id=customization-with-ext)

**Customization with Ext projects is deprecated and should only be used if
absolutely necessary.**

There are two types of Ext projects deployable to @product@:

- *Ext plugins*: used to customize @product@'s core functionality. For example,
  overwriting a class in a core artifact like `com.liferay.portal.kernel`.
- *Ext modules*: used to customize OSGi modules. For example, overwriting a JSP
  in the `com.liferay.login.web` module (see the
  [Login Web Ext sample](/docs/7-2/reference/-/knowledge_base/r/login-web-ext)).

Ext projects are powerful tools used to extend @product@'s default core and/or
module projects. They, however, increase the complexity of your @product@
instance because they are a fork of the default project. Ext projects are not
recommended unless there is absolutely no other way to accomplish your task.

@product-ver@ provides many extension points that let you
customize almost every detail of @product@. If there's a way to customize what
you want with an extension point, do it that way instead. See the
[More Extensible, Easier to Maintain](/docs/7-1/tutorials/-/knowledge_base/t/benefits-of-liferay-7-for-liferay-6-developers#more-extensible-easier-to-maintain)
section for more details on the advantages of using @product@'s extension
points.

Before deciding to use an Ext project, weigh the cost. Ext projects let you use
internal APIs and even let you overwrite @product@ core files. This puts
your deployment at risk of being incompatible with security, performance, or
feature updates released by Liferay. When upgrading to a new version of
@product@ (even if it's a maintenance version or a service pack), you have to
review all changes and manually modify your Ext projects to merge your changes
with @product@'s.

Before diving into Ext projects, first consider if an Ext project is even
necessary at all.

## Making the Decision to Use Ext Projects [](id=making-the-decision-to-use-ext-projects)

There are many parts of @product@ that now provide an extension point via OSGi
bundle. You should follow this three step process to decide whether an Ext
project is necessary:

1.  Find the OSGi extension point that you need. You can follow the
    [Finding Extension Points](/docs/7-2/customization/-/knowledge_base/c/finding-extension-points)
    tutorial as a guide.

2.  If an OSGi extension point does not exist, use an Ext project.

3.  Research new extension points after every release of @product@. When a new
    version of @product@ provides the extension point you need, always use the
    extension point to replace the existing Ext project.

So how do you find an OSGi extension point?

Your first step is to examine the custom projects that extend popular @product@
extension points stored in the
[Liferay Blade Samples](https://github.com/liferay/liferay-blade-samples)
repository. For
more information on these sample projects, see the
[Liferay Sample Projects](/docs/7-2/reference/-/knowledge_base/r/liferay-sample-projects)
tutorial. Usable extension points are also documented throughout Liferay's
Developer Network categorized by the @product@ section involved. For example,
[Overriding MVC Commands](/docs/7-2/customization/-/knowledge_base/c/overriding-mvc-commands)
and
[Customizing the Product Menu](/docs/7-2/customization/-/knowledge_base/c/customizing-the-product-menu)
are articles describing how to extend a @product@ extension point. Want to
learn how to
[customize JSPs](/docs/7-2/customization/-/knowledge_base/c/customizing-jsps)?
Those processes are documented too!

You're now equipped to make an informed decision on using Ext projects.

## Licensing and Contributing [](id=licensing-and-contributing)

@product@ is Open Source software licensed under the 
[LGPL 2.1 license](http://www.gnu.org/licenses/lgpl-2.1.html).
If you reuse any code snippet and redistribute it, whether publicly or to a
specific customer, make sure your modifications are compliant with the license.
One common way is to make the source code of your modifications are available to
the community under the same license. Make sure you read the license text
yourself to find the option that best fits your needs. 

If your goal in making changes is fixing a bug or improving @product@, it could
be of interest to a broader audience. Consider contributing it back to the
project. That benefits all users of the product including you, since you won't
have to maintain the changes with each newly released version of @product@. You
can notify Liferay of bugs or improvements at
[issues.liferay.com](http://issues.liferay.com). Check out Liferay's
[Participation](/participate/contribute-a-feature) information to learn all the
ways that you can contribute to Liferay projects. 

In summary, an Ext project is a powerful way to extend @product@. There are no
limits to what you can customize, so use it carefully. Before using
an Ext project, see if you can implement all or part of the desired functionality
through
[Application Display Templates](/docs/7-2/frameworks/-/knowledge_base/f/styling-apps-and-assets)
or extension points, without introducing the complexity that's inherent with Ext
projects. If you need to use an Ext project, make your customization as small as
possible and follow the instructions in this section carefully to avoid issues.

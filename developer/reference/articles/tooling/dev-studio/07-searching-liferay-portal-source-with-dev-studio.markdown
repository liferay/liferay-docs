---
header-id: searching-product-source-in-dev-studio
---

# Searching @product@ Source in Dev Studio

[TOC levels=1-4]

In Liferay Dev Studio, you can search through @product@'s source code to aid in
the development of your project. Liferay provides great resources to help with
development (e.g., official documentation,
[docs.liferay.com](https://docs.liferay.com/),
[sample projects](/docs/7-2/reference/-/knowledge_base/reference/sample-projects),
etc.), but sometimes searching through Liferay's codebase (i.e., platform and
official apps) for patterns is just as useful. For example, if you're creating
an application that extends a class provided in Liferay's `portal-kernel` JAR,
you can inspect that class and research how it's used in other areas of
@product@'s codebase.

To do this, you must be developing in a
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/reference/liferay-workspace).
Liferay Workspace is able to provide this functionality by targeting a specific
@product@ version, which indexes the configured @product@ source code to provide
advanced search. See the
[Managing the Target Platform in Liferay Workspace](/docs/7-2/reference/-/knowledge_base/reference/managing-the-target-platform-for-liferay-workspace)
tutorial for more information on how this works.

In this tutorial, you'll explore three use cases where advanced search would be
useful.

- [Search class hierarchy](#search-class-hierarchy)
- [Search declarations](#search-method-declarations)
- [Search references](#search-annotation-references)

These examples are just a small subset of what you can search in Liferay Dev
Studio. See Eclipse's documentation on
[Java Search](http://help.eclipse.org/oxygen/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fconcept-java-search.htm&resultof=%22%6a%61%76%61%22%20)
for a comprehensive guide.

## Search Class Hierarchy

Inspecting classes that extend a similar superclass can help you find useful
patterns and examples for how you can develop your own app. For example, suppose
your app extends the
[MVCPortlet](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
class. You can search classes that extend that same class in Dev Studio. Complete
the steps below for a simple example:

1.  Right-click the `MVCPortlet` declaration.

2.  Select *Open Type Hierarchy*.

This opens a window that lets you inspect all classes residing in the target
platform that extend `MVCPortlet`.

![Figure 1: Browse the Type Hierarchy window and open the provided classes for examples on how to extend a class.](../../../images/open-type-hierarchy.png)

Great! Now you can search for all extensions and implementations of a
class/interface to aid in your quest for developing the perfect app.

## Search Method Declarations

Sometimes you want a search to be more granular, exploring the declarations of a
specific method provided by a class/interface. Dev Studio's advanced search has
no limits; Liferay Workspace's target platform indexing provides method
exploration too!

Suppose in the
[MVCPortlet](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
class you're extending, you want to search for declarations of its `doView`
method you're overriding. Here's how to do it:

1.  Right-click the `doView` method declaration in your custom app's class.

2.  Select *Declarations* &rarr; *Workspace*.

![Figure 2: All declarations of the method are returned in the Search window.](../../../images/inspect-declared-method.png)

The rendered Search window displays the other occurrences in the target platform
where that method was overridden.

## Search Annotation References

Annotations used in @product@'s source code can sometimes be cryptic. You can
find out how they can be used in your own application by searching for where
these types of annotations exist in Liferay's target platform.

For example, you may find some documentation on using the `@Reference`
annotation in an OSGi module and implement it in your custom app. It could be
useful to reference real world examples in @product@'s apps to check how it was
used elsewhere. You can complete this search like this:

1.  Right-click the `@Reference` annotation in a class.

2.  Select *References* &rarr; *Workspace*.

![Figure 3: All matching annotations are displayed in the Search window.](../../../images/inspect-references-ide.png)

The rendered Search window displays the other occurrences in the target platform
where that annotation was used.

Excellent! You now have the tools to search the configured target platform
specified in your Liferay Workspace!

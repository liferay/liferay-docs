# Searching @product@ Source in Liferay Dev Studio [](id=searching-product-source-in-liferay-ide)

In Liferay Dev Studio, you can search through @product@'s source code to aid in
the development of your project. Liferay provides great resources to help with
development (e.g., official documentation,
[docs.liferay.com](https://docs.liferay.com/),
[sample projects](/develop/reference/-/knowledge_base/7-1/liferay-sample-projects),
etc.), but sometimes, searching through Liferay's codebase (i.e., platform and
official apps) for patterns is just as useful. For example, if you're creating a
custom app that extends a class provided in Liferay's `portal-kernel` JAR, you
can inspect that class and research how it's used in other areas of @product@'s
codebase.

To do this, you must be developing in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).
Liferay Workspace is able to provide this functionality by targeting a specific
@product@ version, which indexes the configured @product@ source code to provide
advanced search. See the
[Managing the Target Platform in Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/managing-the-target-platform-for-liferay-workspace)
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

## Search Class Hierarchy [](id=search-class-hierarchy)

Inspecting classes that extend a similar superclass can help you find useful
patterns and examples for how you can develop your own app. For example, suppose
your app extends the
[MVCPortlet](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
class. You an search classes that extend that same class in Dev Studio by
right-clicking the `MVCPortlet` declaration and selecting *Open Type Hierarchy*.
This opens a window that lets you inspect all classes residing in the target
platform that extend `MVCPortlet`.

![Figure 1: Browse the Type Hierarchy window and open the provided classes for examples on how to extend a class.](../../../images/open-type-hierarchy.png)

Great! Now you can search for all extensions and implementations of a
class/interface to aid in your quest for developing the perfect app.

## Search Method Declarations [](id=search-method-declarations)

Sometimes you want a search to be more granular, exploring the declarations of a
specific method provided by a class/interface. Liferay Dev Studio's advanced
search has no limits; Liferay Workspace's target platform indexing provides
method exploration too!

Suppose in the
[MVCPortlet](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)
class you're extending, you'd like to search for declarations of its `doView`
method you're overriding. You can do this by right-clicking the `doView` method
declaration in your custom app's class and selecting *Declarations* &rarr;
*Workspace*.

![Figure 2: All declarations of the method are returned in the Search window.](../../../images/inspect-declared-method.png)

The rendered Search window displays the other occurrences in the target platform
where that method was overridden.

## Search Annotation References [](id=search-annotation-references)

Annotations used in @product@'s source code can sometimes be cryptic. With the
ability to search where these types of annotations reside in Liferay's target
platform, you can find how they could be used in your own app.

For example, you may find some official documentation on using the `@Reference`
annotation in an OSGi module and implement it in your custom app. It could be
useful to reference real world examples in @product@'s apps to check how it was
used elsewhere. You could search for this by right-clicking the annotation in a
class and selecting *References* &rarr; *Workspace*.

![Figure 3: All matching annotations are displayed in the Search window.](../../../images/inspect-references-ide.png)

The rendered Search window displays the other occurrences in the target platform
where that annotation was used.

Excellent! You now have the tools to search the configured target platform
specified in your Liferay Workspace!

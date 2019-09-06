---
header-id: developing-a-fragment-renderer
---

# Developing a Fragment Renderer

[TOC levels=1-4]

When creating Fragments through @product@'s provided UI, you're given three
front-end languages to leverage: CSS, HTML, and JavaScript. Although you can
harness a lot of power with these languages alone, they do not provide an easy
way to retrieve and process information from the database or third party
systems. A common solution for this issue is creating a full-fledged portlet to
complete common back-end necessities, but this is sometimes overkill for what you
need.

For a lightweight alternative, you can develop a *Fragment Renderer* to use
Liferay's provided Java APIs for back-end tasks related to your Fragment. To do
this, you must
[implement the `FragmentRenderer` interface](#implementing-the-fragmentrenderer-interface).

Optionally, you can

- [Leverage the `FragmentRendererContext`](#leveraging-the-fragmentrenderercontext).

- [Use JSPs for your Fragment's display](#rendering-jsps).

- [Choose when to display the component](#choosing-when-to-display-a-component).

- [Translate the Collection language key](#translating-the-collection-language-key).

You'll explore each step next.

## Implementing the FragmentRenderer Interface

The
[`FragmentRenderer`](@app-ref@/fragment/latest/javadocs/com/liferay/fragment/renderer/FragmentRenderer.html)
interface requires the implementation of two methods:

`getCollectionKey`: returns the unique key for the component's Collection.
Define this key in several components to group them under a collapsible panel in
the Page Editor.

`getLabel`: provides the Fragment name.

The remaining methods are optional, but can be useful in many scenarios:

<!-- Add when Configurable fragments are released for SP1. -Cody
`getConfiguration`: returns the Fragment's configuration JSON code.
-->

`getImagePreviewURL`: returns the URL for previewing the Fragment's image.

`getKey`: returns the Fragment's key.

`getType`: returns the Fragment's type. Type values include
`FragmentConstants.TYPE_COMPONENT` and `FragmentConstants.TYPE_SECTION`.

`isSelectable`: defines whether page authors can select the Fragment
Renderer. You'll learn more about this in the
[Choosing When to Display a Component](#choosing-when-to-display-a-component)
section.

`render` (highly recommended): defines how to render the Fragment Renderer
(e.g., JSP or FreeMarker). You can leverage the `FragmentRendererContext` in
this method to facilitate the rendering process.

Next, you'll learn about leveraging the `FragmentRendererContext`.

## Leveraging the FragmentRendererContext

The `render` method receives a read-only instance of the interface
[`FragmentRendererContext`](@app-ref@/fragment/latest/javadocs/com/liferay/fragment/renderer/FragmentRendererContext.html).
This provides information about the context in which the Fragment is being
rendered. The fields of information that are accessible through it include

**Fragment Entry Link**: The specific instance of the Fragment being rendered.
This information can be used to identify the specific site or page to which the
Fragment was added, when it was added, the user who added it, etc.

**Locale**: The current locale to be used for any multi-locale text.

**Mode**: There are three available modes:

- **VIEW**: The component is being rendered within a page being viewed (not
  edited).
- **ASSET_DISPLAY_PAGE**: The component is being edited on a Display Page.
- **EDIT**: The component is being edited on a Content Page.

There are other fields which should only be necessary for advanced use cases:

**Preview Class PK**: If the Fragment supports displaying content, this field
supports previewing an *In progress* version of the content before
it's ready to publish. In this case, the `render` method returns the content's
primary key. 

**Preview Type**: Represents the preview type you want to show. The accepted
values include

- **TYPE_LATEST_APPROVED**: The latest approved version of the content.
- **TYPE_LATEST**: The latest version of the content.

**Field Values**: Fragments can have editable elements through `<lfr-editable>`
tags; this also applies to those created with `FragmentRenderer`. The
`getFieldValuesOptional()` method retrieves the field values the user may have
introduced in them. This only applies in the context of a Display Page with the
values of the mapped structure.

**Segment Experience IDs**: A list of identifiers for experiences that have been
configured for the current page.

## Rendering JSPs

Usually you'll want to avoid writing HTML in your Java code. Fortunately, you
can use the `render` method to use any templating mechanism of your choice. JSP
integration is provided out-of-the box. 

For example, rendering a JSP for your Fragment Renderer would look like this:

```java
@Override
public void render(
    FragmentRendererContext fragmentRendererContext,
    HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse) throws IOException {

    httpServletRequest.setAttribute(
        "fragmentRendererContext", fragmentRendererContext);

    _jspRenderer.renderJSP(
        httpServletRequest, httpServletResponse, "/my-component.jsp");
}

@Reference
private JSPRenderer _jspRenderer;

@Reference(
    target = "(osgi.web.symbolicname=com.liferay.fragment.renderer.docs)",
    unbind = "-"
)
private ServletContext _servletContext;
```

This sets the
[`FragmentRendererContext`](#leveraging-the-fragmentrenderercontext) in the
HTTP servlet request, which is then used to render the included JSP file (e.g.,
`my-component.jsp`).

To leverage JSPs, you must specify the servlet context for the JSP files. Since
your Fragment Renderer is an OSGi module, your `bnd.bnd` file must define a web
context path:

```markup
Bundle-SymbolicName: com.liferay.fragment.renderer.docs
Web-ContextPath: /my-fragment-renderer
```

Then you must reference the Servlet context using the symbolic name of your
module, as was shown above:

```java
@Reference(
    target = "(osgi.web.symbolicname=com.liferay.fragment.renderer.docs)",
    unbind = "-"
)
private ServletContext _servletContext;
```

| **Note:** To use the JSP Renderer, your module must set the
| `com.liferay.frontend.taglib` dependency in its build file.

Next, you'll learn about controlling when your Fragment Renderer is displayed.

## Choosing When to Display a Component

Sometimes offering Fragment components only makes sense in specific cases. You
can implement the `isSelectable(...)` method to specify under which conditions
the Fragment Renderer is available to page authors.

For example, if you wanted to make your Fragment Renderer only available in
Display Pages, you could implement the `isSelectable` method like this:

```java
@Override
public boolean isSelectable(HttpServletRequest httpServletRequest) {
    Layout layout = (Layout)httpServletRequest.getAttribute(WebKeys.LAYOUT);

    if (Objects.equals(
        layout.getType(), LayoutConstants.TYPE_ASSET_DISPLAY)) {

        return true;
    }

    return false;
}
```

This determines the Fragment Renderer's page type and returns `true` when the
page type is a Display Page or `false` if it's not.

## Translating the Collection Language Key

When setting your Fragment Renderer's collection name via the `getCollectionKey`
method, you should specify it as a language key and then define it in a resource
bundle.

For example, a `getCollectionKey` method could look like this:

```java
@Override
public String getCollectionKey() {
    return "sample-components";
}
```

To specify `sample-components` in a resource bundle, create the
`src/main/resources/content/Language.properties` file within the Fragment
Renderer module and define it using the language key
`fragment.collection.label.{collection-key}`. For example,

```properties
fragment.collection.label.sample-components=Sample Components
```

To learn more about resource bundles, see the
[Localization](/docs/7-2/frameworks/-/knowledge_base/f/localization) section.

Next, you'll step through creating a Fragment Renderer.

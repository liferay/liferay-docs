---
header-id: creating-a-fragment-renderer
---

# Creating a Fragment Renderer

[TOC levels=1-4]

Creating a Fragment Renderer lets you call Liferay's provided Java APIs for
back-end tasks related to your Fragment. In this article, you'll create a sample
Fragment Renderer that displays values stored in the current @product@
instance's database.

1.  [Create a default module project](/docs/7-2/reference/-/knowledge_base/r/creating-a-project)
    in your development environment.

2.  Create a unique package name in the module's `src` directory and create
    a new Java class in that package. To follow naming conventions, give your
    class a unique name followed by `FragmentRenderer` (e.g.,
    `ShowContextFragmentRenderer`).

3.  Configure your new class to implement the
    [`FragmentRenderer`](@app-ref@/fragment/latest/javadocs/com/liferay/fragment/renderer/FragmentRenderer.html)
    interface:

    ```java
    public class ShowContextFragmentRenderer implements FragmentRenderer {
    }
    ```

4.  Insert the following `@Component` annotation above the class declaration:

    ```java
    @Component(service = FragmentRenderer.class)
    ```

    This sets the OSGi service type to `FragmentRenderer`.

5.  Implement the two required `FragmentRenderer` methods:

    ```java
    @Override
    public String getCollectionKey() {
        return "sample-components";
    }
    
    @Override
    public String getLabel(Locale locale) {
        return "Show Context Component";
    }
    ```

    The `getCollectionKey()` method returns a language key, which you'll define
    later. The name displayed for this Fragment is defined as *Show Context
    Component*.

    ![Figure 1: The new Fragment Renderer appears in its defined component collection.](../../../images/show-context-fragment-renderer.png)

6.  Implement the `render` method:

    ```java
    @Override
    public void render(
        FragmentRendererContext fragmentRendererContext,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws IOException {

    		PrintWriter printWriter = httpServletResponse.getWriter();

    		printWriter.write("<h3>Context</h3>");
    		printWriter.write("<ul>");

    		FragmentEntryLink fragmentEntryLink =
            fragmentRendererContext.getFragmentEntryLink();

    		printWriter.write("<li>Added by: " + fragmentEntryLink.getUserName());
    		printWriter.write("<li>Added in: " + fragmentEntryLink.getCreateDate());

    		printWriter.write("<li>Locale: " + fragmentRendererContext.getLocale());
    		printWriter.write("<li>Mode: " + fragmentRendererContext.getMode());
    		printWriter.write("<li>PreviewClassPK: " + fragmentRendererContext.getPreviewClassPK());
    		printWriter.write("<li>PreviewType: " + fragmentRendererContext.getPreviewType());
    		printWriter.write("<li>Segment experiences: " + StringUtil.merge(fragmentRendererContext.getSegmentsExperienceIds(), ", "));
    		printWriter.write("</ul>");
    }
    ```

    This method leverages the
    [`FragmentRendererContext`](/docs/7-2/frameworks/-/knowledge_base/f/developing-a-fragment-renderer#leveraging-the-fragmentrenderercontext),
    which provides the Fragment's context information stored in the database.
    This information is displayed in the Fragment Renderer when it's placed on
    a page.

    ![Figure 2: When adding the new Fragment Renderer to a page, the context information is displayed.](../../../images/show-context-fragment-renderer-page.png)

7.  Define the language key `sample-components` that you used in the
    `getCollectionKey()` method. To do this, create the
    `src/main/resources/content/Language.properties` file and add the following
    language key:

    ```properties
    fragment.collection.label.sample-components=Sample Components
    ```

8.  Provide the appropriate dependencies to compile your Fragment Renderer
    project. For example, the following dependencies are defined for the Show
    Context Component Fragment Renderer sample (Gradle build) deployed to
    Liferay Portal 7.2 GA1:

    ```groovy
    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "4.13.0"
        compileOnly group: "com.liferay", name: "com.liferay.fragment.api", version: "2.7.2"
        compileOnly group: "com.liferay", name: "com.liferay.fragment.service", version: "2.0.10"
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib", version: "4.0.15"
        compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "3.0.0"
        compileOnly group: "javax.portlet", name: "portlet-api", version: "3.0.0"
        compileOnly group: "javax.servlet", name: "javax.servlet-api", version: "3.0.1"
        compileOnly group: "jstl", name: "jstl", version: "1.2"
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }
    ```

    To stay in sync with the appropriate versions of your project's
    dependencies, consider using the
    [Target Platform](/docs/7-2/reference/-/knowledge_base/r/managing-the-target-platform)
    framework.

That's it! You can compile the sample *Show Context Component* Fragment Renderer
and [deploy it](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project)!
It'll be available to add for a Fragment-enabled page under the *Sample
Components* collection.

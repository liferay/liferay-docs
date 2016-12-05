# Adding New Behavior to an Editor [](id=adding-new-behavior-to-an-editor)

With the support of several kinds of WYSIWYG editors, Liferay gives you many
options to support your users' editing needs. Sometimes, however, you can't get
what you want with configuration alone. To help developers in these situations,
Liferay provides a way to programatically access the editor instance to create
the editor experience you want.

This can be done by using the `liferay-util:dynamic-include` JavaScript
extension point. This allows anyone to inject JavaScript code right after the
editor instantiation to configure/change the editor.

In this tutorial, you'll learn how to use the JavaScript extension point in your
Liferay supported WYSIWYG editor.

## Injecting JavaScript into a WYSIWYG Editor [](id=injecting-javascript-into-a-wysiwyg-editor)

The `liferay-util:dynamic-include` extension point is available in the JSP files
of @product@'s configurable editors. This extension point serves as the gateway
for injecting JavaScript into your editor instance. To take advantage of this
extension point, you should follow these steps:

1.  Create a JS file with the JavaScript code you'd like to execute in your
    editor. Create the JS file in a folder that makes sense to reference,
    since you'll need to register the file in your module. Also remember that the
    extension point is configured to inject the JavaScript code into the editor
    immediately following editor initialization.

    Some examples of JS files that are injected into the CKEditor are
    [creole_dialog_definition.js](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/creole_dialog_definition.js),
    [creole_dialog_show.js](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/creole_dialog_show.js),
    and
    [dialog_definition.js](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-editor/frontend-editor-ckeditor-web/src/main/resources/META-INF/resources/_diffs/extension/dialog_definition.js).
    These JS files are used by @product@ to redefine which fields show in
    different dialogs, depending on what the selected language (HTML, BBCode,
    Creole) supports. For example, Creole doesn't support background color in
    table cells, so the table cells are removed from the options displayed to
    the user when running in Creole mode.

2.  Now you'll need to create a module that can register your new JS file and
    inject it into your editor instance. Create a generic OSGi module using your
    favorite third party tool, or use
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

3.  Create a unique package name in the module's `src` directory, and create a
    new Java class in that package. To follow naming conventions, your class name
    should begin with the editor you're modifying, followed by custom attributes,
    and ending with *DynamicInclude* (e.g.,
    `CKEditorCreoleOnEditorCreateDynamicInclude.java`). Your Java class should
    implement the
   [DynamicInclude](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/servlet/taglib/DynamicInclude.java)
   interface.

4.  Directly above the class's declaration, insert the following code:

        @Component(immediate = true, service = DynamicInclude.class)

    This annotation declares the implementation class of the Component, and
    specifies to immediately start the module once deployed to Portal.

5.  If you have not yet inherited the abstract methods from `DynamicInclude`, do
    that now. You'll have two implemented methods to edit: `include(...)` and
    `register(...)`.

6.  In the `include(...)` method, retrieve the bundle where your custom JS file
    resides. Then retrieve the JS file as a URL and inject the contents into the
    editor. You can view some example code below that does this for the
    `creole_dialog_definition.js` file:

        Bundle bundle = _bundleContext.getBundle();

        URL entryURL = bundle.getEntry(
            "/META-INF/resources/html/editors/ckeditor/extension" +
                "/creole_dialog_definition.js");

        StreamUtil.transfer(entryURL.openStream(), response.getOutputStream());

    In the `include(...)` method, you can also retrieve editor configurations
    and choose what JS file to inject based on the configuration selected by
    the user. For example, this would be applicable for the use case that was
    suggested previously dealing with Creole's deficiency with displaying
    background colors in table cells. You can look at how this could be
    done by looking at the `include(...)` method in the
    [CKEditorCreoleOnEditorCreateDynamicInclude](https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-editor/frontend-editor-ckeditor-web/src/main/java/com/liferay/frontend/editor/ckeditor/web/servlet/taglib/CKEditorCreoleOnEditorCreateDynamicInclude.java)
    class.

7.  Make sure you've instantiated your bundle's context so you
    can successfully retrieve your bundle. As a best practice, do this by 
    creating an activation method and then setting the `BundleContext` as a
    private field. Here's an example: 

        @Activate
        protected void activate(BundleContext bundleContext) {
            _bundleContext = bundleContext;
        }

        private BundleContext _bundleContext;

    This method uses the `@Activate` annotation, which specifies that it
    should be invoked once the service component has satisfied its requirements.
    For this default example, the `_bundleContext` was used in the
    `include(...)` method.

8.  Now register the editor you're customizing. For example, if you were
    injecting JS code into the CKEditor's JSP file, the code would look like
    this:

        dynamicIncludeRegistry.register(
            "com.liferay.frontend.editors.web#ckeditor#onEditorCreate");

    This registers the CKEditor into the Dynamic Include registry and specifies
    that JS code will be injected into the editor once it's created.

    Just as you can configure individual JSP pages to use a specific
    implementation of the available WYSIWYG editors, you can use those same
    implementation options for the registration process. Visit the
    [Editors](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Editors)
    section of `portal.properties` for more details. For example, to configure
    the Creole implementation of the CKEditor, you could use the following
    key:

        "com.liferay.frontend.editors.web#ckeditor_creole#onEditorCreate"

That's it! The JS code that you created is now injected into the editor instance
you've specified. You're now able to use JavaScript to add new behavior to your
Liferay supported WYSIWYG editor!

## Related Topics [](id=related-topics)

[Modifying an Editor's Configuration](/develop/tutorials/-/knowledge_base/7-0/modifying-an-editors-configuration)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)

[Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

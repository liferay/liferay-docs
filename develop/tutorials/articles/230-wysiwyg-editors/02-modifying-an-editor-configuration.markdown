# Modifying an Editor's Configuration [](id=modifying-an-editors-configuration)

@product@ supports many different kinds of WYSIWYG editors that can be used in
portlets to edit content. Depending on the content you're editing, you may want
to modify the editor to provide a better configuration for your needs. In this
tutorial, you'll learn how to extend your Liferay supported WYSIWYG editor to
add new or modify existing configurations exactly how you'd like.

## Extending the Editor's Configuration [](id=extending-the-editors-configuration)

To modify the editor's configuration, create a module that has a component that
implements the
[`EditorConfigContributor`](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html)
interface. When you implement this interface, your module will provide a service
that modifies the editors you'd like to change. A simple example of this is
provided below.

1.  Create a generic OSGi module using your favorite third party tool, or use the
    [Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli).

2.  Create a unique package name in the module's `src` directory, and create a
    new Java class in that package. The class should extend the
    [BaseEditorConfigContributor](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/BaseEditorConfigContributor.html)
    class.

3.  Directly above the class's declaration, insert a component annotation:

        @Component(
            property = {

            },

            service = EditorConfigContributor.class
        )

    This annotation declares the implementation class of the Component and
    specifies the Component's properties. You should implement the
    `EditorConfigContributor` interface for this scenario. The `property`
    element is blank in the code snippet above. You need to insert properties
    that distinguish the editor's name, editor's configuration key, and/or the
    portlet name where the editor resides. These three properties can be
    specified independently or in any variation with each other. You can find
    out more about the available properties and how they should be used by
    reading the Javadoc provided in the
    [EditorConfigContributor](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.html)
    interface.

    <!-- Change the Javadoc link above to the Javadoc listed on
    docs.liferay.com, when 7.0 Javadoc is available. -Cody -->

    The following code is a sample of what the `@Component` annotation could
    look like when modifying an editor's configuration:

        @Component(
            property = {
                "editor.config.key=contentEditor", "editor.name=alloyeditor",
                "editor.name=ckeditor", "javax.portlet.name=33",
                "javax.portlet.name=161", "service.ranking:Integer=100"
            },
			
            service = EditorConfigContributor.class
        )

    This annotation declares that the following service will be applied for the
    AlloyEditor and CKEditor identified by their `contentEditor` configuration
    key. Two portlet names are specified, which means that the service applies
    to all editors in those portlets. Lastly, the service ranking is listed,
    which prioritizes this service over others that are currently deployed in
    Portal.

4.  Now that you've specified which editor configurations you want to modify, you
    need to specify what about them you'd like to change. Add the following
    method to your new class:

        @Override
        public void populateConfigJSONObject(
            JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
            ThemeDisplay themeDisplay,
            RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        }

    Currently, this method does nothing. You need to add some logic, which
    you'll do next.

5.  In the `populateConfigJSONObject` method, you need to instantiate a
    [JSONObject](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONObject.html)
    that holds the current configuration of the editor. For instance, you could
    do something like this:

        JSONObject toolbars = jsonObject.getJSONObject("toolbars");

6.  Now that the `JSONObject` holds your editor's configuration, you can modify
    the configuration. For instance, suppose you'd like to add a button to your
    editor's toolbar. To complete this, you'd need to extract the *Add* buttons
    out of your toolbar configuration object as a
    [JSONArray](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONArray.html),
    and then add the button to that `JSONArray`. For example, the following code 
    would add a *Camera* button to the editor's toolbar:

        if (toolbars != null) {
            JSONObject toolbarAdd = toolbars.getJSONObject("add");

            if (toolbarAdd != null) {
                JSONArray addButtons = toolbarAdd.getJSONArray("buttons");

                addButtons.put("camera");
            }
        }

    The configuration JSON object is passed to the editor with the modifications
    you've implemented in the `populateConfigJSONObject` method.

Your Java class is complete! The only thing left to do is generate the module's
JAR file and copy it to your Portal's `osgi/modules` directory. Once the module
is installed and activated in your Portal's service registry, your new editor
configuration is available for use.

@product@ supports several different types of WYSIWYG editors, which include
(among others):

- [AlloyEditor](http://alloyeditor.com/api/)
- [CKEditor](http://docs.ckeditor.com/#!/api/CKEDITOR.config)
- [TinyMCE](http://www.tinymce.com/wiki.php/Configuration)

Make sure to visit each editor's configuration API to learn what each editor
offers for configuration settings.

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-0/adding-new-behavior-to-an-editor)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)

[Developing Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

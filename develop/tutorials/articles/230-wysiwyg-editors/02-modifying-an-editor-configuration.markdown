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

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#creating-a-module).

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

    The following code is a sample of what the `@Component` annotation could
    look like when modifying an editor's configuration:

        @Component(
            property = {
                "editor.config.key=contentEditor", "editor.name=alloyeditor",
                "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
                "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsAdminPortlet", "service.ranking:Integer=100"
            },
			
            service = EditorConfigContributor.class
        )

    This annotation declares that the following service is applied for the
    AlloyEditor identified by the `contentEditor` configuration key. Two portlet
    names are specified (Blogs and Blogs Admin), which means the service applies
    to all editors in those portlets. Lastly, the service ranking is listed,
    which prioritizes this service over others that are currently deployed in
    @product@.

4.  Now that you've specified which editor configurations you want to modify,
    you must specify what about them must change. Add the following method to
    your new class:

        @Override
        public void populateConfigJSONObject(
            JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
            ThemeDisplay themeDisplay,
            RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

        }

    This method updates the original configuration JSON object with a new
    configuration. It can even update or delete the original configuration, or
    any other configuration introduced by another `EditorConfigContributor`.
    The configuration object contains the configuration to be directly used by
    the editor. This means that the configuration object used for this editor
    may differ from other editors used in @product@.
    
    Currently, this method does nothing. You need to add some logic, which
    you'll do next.

5.  In the `populateConfigJSONObject` method, you need to instantiate a
    [JSONObject](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONObject.html)
    that holds the current configuration of the editor. For instance, you could
    do something like this:

        JSONObject toolbars = jsonObject.getJSONObject("toolbars");

    This gets the editor's toolbar.

    +$$$

    **Note:** This toolbar configuration is only applicable for the AlloyEditor.
    If you choose a configuration that is supported by multiple editors, you
    could apply it to them all. To do this, you could specify all the editors
    (e.g., `"editor.name=alloyeditor"`, `"editor.name=ckeditor"`,
    `ckeditor_bbcode` etc.) in the `@Component` annotation  of your
    `EditorConfigContributor` implementation, as you did in step 3. Use the 
    site links provided at the bottom of this tutorial to view each editor's 
    configuration options and requirements.

    $$$

    Now that you've retrieved the toolbar, you can modify it. You'll do this
    next.

6.  You'll modify the editor's toolbar by adding a camera button. To complete
    this, extract the *Add* buttons out of your toolbar configuration object as
    a
    [JSONArray](@platform-ref@/7.0-latest/javadocs/portal-kernel/com/liferay/portal/kernel/json/JSONArray.html),
    and then add the button to that `JSONArray`. The following code adds a
    *Camera* button to the editor's toolbar:

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
JAR file and copy it to your Portal's `deploy` folder. Once the module is
installed and activated in your Portal's service registry, your new editor
configuration is available for use.

@product@ supports several different types of WYSIWYG editors, which include
(among others):

- [AlloyEditor](https://alloyeditor.com/api/1.4.1/)
- [CKEditor](http://docs.ckeditor.com/#!/api/CKEDITOR.config)
- [TinyMCE](http://www.tinymce.com/wiki.php/Configuration)

Make sure to visit each editor's configuration API to learn what each editor
offers for configuration settings.

## Related Topics [](id=related-topics)

[Adding New Behavior to an Editor](/develop/tutorials/-/knowledge_base/7-0/adding-new-behavior-to-an-editor)

[Embedding Portlets in Themes and Layout Templates](/develop/tutorials/-/knowledge_base/7-0/embedding-portlets-in-themes-and-layout-templates)

[Developing Portlets](/develop/tutorials/-/knowledge_base/7-0/portlets)

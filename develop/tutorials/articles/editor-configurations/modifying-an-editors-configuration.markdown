# Modifying an Editor's Configuration

Liferay supports many different kinds of editors that can be used in portlets to
edit content. Depending on the content you're editing, you may want to modify
the editor to provide a better configuration for your needs. In this tutorial,
you'll learn how to extend your Liferay supported editor to add new or modify
existing configurations exactly how you'd like.

## Extending the Editor's Configuration

To modify the editor's configuration, you'll need to create a module that has a
component that implements the
[EditorConfigContributor](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.java)
interface. By implementing this interface in your module, your module will
provide a service to your portal that modifies the editors you'd like to change.
A simple example of this is provided below.

1. Create a generic OSGi module using your favorite third party tool.

    <!-- If we decide to document how to create an OSGi module from scratch, we
    should point to that documentation here. At the current time, there is no
    Liferay "recommended" way of doing this. Therefore, I'm assuming that the
    reader has experience with OSGi development. Pointing to introductory OSGi
    tutorials (once available) would be very helpful here. -Cody -->

2. Create a unique package name in the module's `src` directory, and create a
   new Java class in that package.

3. Directly above the class's declaration, insert the following:

        @Component(
            property = {

            },

            service = EditorConfigContributor.class
        )

    This annotation declares the implementation class of the Component, and
    specifies the Component's properties. You should implement the
    `EditorConfigContributor` interface for this scenario. The `property`
    element is blank in the code snippet above. You'll need to insert properties
    that distinguish the editor's name, editor's configuration key, and/or the
    portlet name where the editor resides. These three properties can be
    specified independently, or in any variation with each other. You can find
    out more about the available properties and how they should be used by
    reading the Javadoc provided in the
    [EditorConfigContributor](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/kernel/editor/configuration/EditorConfigContributor.java)
    interface.

    <!-- Change the Javadoc link above to the Javadoc listed on
    docs.liferay.com, when 7.0 Javadoc is available. -Cody -->

    The following is a sample of what the `@Component` annotation could look
    like when modifying an editor's configuration:

        @Component(
            property = {
                "editor.config.key=contentEditor", "editor.name=alloyeditor",
                "editor.name=ckeditor", "javax.portlet.name=33",
                "javax.portlet.name=161", "service.ranking:Integer=100"
            },
			
            service = EditorConfigContributor.class
        )

    This particular annotation declares that the following service will be
    applied for the AlloyEditor and CKEditor identified by their `contentEditor`
    configuration key. Two portlet names are specified, which means that the
    service applies to all editors in those portlets. Lastly, the service
    ranking is listed, which prioritizes this service over others that are
    currently deployed in Portal.

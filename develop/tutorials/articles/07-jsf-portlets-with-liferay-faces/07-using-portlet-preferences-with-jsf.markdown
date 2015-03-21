# Using Portlet Preferences with JSF [](id=using-portlet-preferences-with-jsf)

JSF portlet developers often must enable the end-user to personalize portlets
in some way. To meet this requirement, the Portlet 2.0 specification lets you
define portlet preferences for each portlet. Preference names and default values
are defined in the `WEB-INF/portlet.xml` descriptor. Portal end-users start
out interacting with the portlet user interface in portlet `VIEW` mode, but
switch to portlet `EDIT` mode in order to select custom preference values. 

Here's an example portlet preference descriptor that sets a date pattern: 

    <portlet-preferences>
        <preference>
            <name>datePattern</name>
            <value>MM/dd/yyyy</value>
        </preference>
    </portlet-preferences>

Additionally, Portlet 2.0 lets you specify support for `EDIT` mode in the
`WEB-INF/portlet.xml` descriptor file. You can add `EDIT` mode by specifying the
element `<portlet-mode>edit</portlet-mode>` in your `portlet.xml` file's
`<supports>` element. The following example snippet specifies `EDIT` mode in
addition to the `VIEW` and `HELP` modes. 

    <supports>
        <mime-type>text/html</mime-type>
        <portlet-mode>view</portlet-mode>
        <portlet-mode>edit</portlet-mode>
        <portlet-mode>help</portlet-mode>
    </supports>

Although support for portlet `EDIT` mode has been specified, the portlet
container doesn't necessarily know which JSF view should be rendered when the
user enters portlet `EDIT` mode. JSF portlet developers must specify the
facelet view in the `WEB-INF/portlet.xml` descriptor that should be displayed
for each supported portlet mode. The following example snippet specifies a
facelet file `/preferences.xhtml` as the view, by mapping it to the
`javax.portlet.faces.defaultViewId.edit` init parameter. 

    <init-param>
        <name>javax.portlet.faces.defaultViewId.edit</name>
        <value>/preferences.xhtml</value>
    </init-param>

Terrific! You're now familiar with specifying and using portlet preferences in
your JSF portlet. 

**Related Topics**

[Localizing JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/localizing-jsf-portlets)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

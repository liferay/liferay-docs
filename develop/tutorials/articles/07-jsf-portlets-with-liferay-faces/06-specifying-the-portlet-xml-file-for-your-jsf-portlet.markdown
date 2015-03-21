# Specifying the portlet.xml File for Your JSF Portlet [](id=specifying-the-portlet-xml-file-for-your-jsf-portlet)

Each portlet project must have a `WEB-INF/portlet.xml` deployment descriptor
file. When creating a JSF portlet using Liferay IDE or the Plugins SDK, this
file is created for you. JSF portlets require a couple deployment descriptors. 

First, using JSF 2.x in a portlet requires specifying
`javax.portlet.faces.GenericFacesPortlet` as the portlet's class. You specify
this class name in the portlet's `<portlet-class>` entity. Notice that the
portlet in the following `portlet.xml` snippet meets this requirement: 

    <portlet-app>
        <portlet>
            <portlet-name>jsf_portlet</portlet-name>
            <display-name>JSF Portlet</display-name>
            <portlet-class>
                javax.portlet.faces.GenericFacesPortlet
            </portlet-class>
            <init-param>
                <name>javax.portlet.faces.defaultViewId.view</name>
                <value>/view.xhtml</value>
            </init-param>
            <init-param>
                <name>javax.portlet.faces.defaultViewId.edit</name>
                <value>/preferences.xhtml</value>
            </init-param>
            <init-param>
                <name>javax.portlet.faces.defaultViewId.help</name>
                <value>/help.xhtml</value>
            </init-param>
            <supports>
                <mime-type>text/html</mime-type>
                <portlet-mode>view</portlet-mode>
                <portlet-mode>edit</portlet-mode>
                <portlet-mode>help</portlet-mode>
            </supports>

            ...

        </portlet>
    </portlet-app>

Second, each portlet must map a facelet to each portlet mode that it supports.
The `portlet.xml` file content above supports the `VIEW`, `EDIT`, and `HELP`
portlet modes, and maps each of those modes to a specific facelet. 

In the example code above, `VIEW` mode support is specified by the
`<portlet-mode>view</portlet-mode>` element. The `VIEW` mode is mapped
to the `/view.xhtml` facelet by the `<init-param>` element:

    <init-param>
        <name>javax.portlet.faces.defaultViewId.view</name>
        <value>/view.xhtml</value>
    </init-param>

You can set up your `portlet.xml` file similarly. Next, you'll start using
portlet preferences. 

**Related Topics**

[Using Portlet Preferences with JSF](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/using-portlet-preferences-with-jsf)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

[Understanding Liferay Faces Bridge](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/understanding-liferay-faces-bridge)

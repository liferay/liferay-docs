# Specifying and Using Portlet Preferences with JSF [](id=specifying-and-using-portlet-preferences-with-jsf)

This tutorial explains how to specify the `portlet.xml` file for your JSF
portlet. Then, this tutorial demonstrates using portlet preferences by defining
them in the `portlet.xml` file. By defining portlet preferences in your JSF
portlet, end-users can personalize the portlet in ways they prefer. To start
off, you'll learn how to specify the `portlet.xml` file for your JSF portlet. 
                                                                        
## Specifying the portlet.xml for Your JSF Portlet

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

## Using Portlet Preferences with JSF

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


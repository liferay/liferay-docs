# Specifying and Using Portlet Preferences with JSF

This tutorial explains how to specify the `portlet.xml` file for your JSF
portlet. Then, this tutorial demonstrates using portlet preferences by defining
them in the `portlet.xml` file. By defining portlet preferences in your JSF
portlet, end users using your portlet can personalize the portlet in ways they
prefer. To start off, you'll learn how to specify the `portlet.xml` file for
your JSF portlet. 

## Specifying the `portlet.xml` for Your JSF Portlet

Each portlet project must have a `WEB-INF/portlet.xml` deployment descriptor
file. When creating a JSF portlet using Liferay IDE or the Plugins SDK, this
file is created for you; but there are a couple unique requirements for JSF
portlets with respect to their deployment descriptors. 

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

Now that we've got `WEB-INF/portlet.xml` set up, let's move on to using portlet
preferences. 

## Using Portlet Preferences with JSF

JSF portlet developers often must enable the end-user to personalize portlets
in some way. To meet this requirement, the Portlet 2.0 specification lets you
define portlet preferences for each portlet. Preference names and default values
are defined in the `WEB-INF/portlet.xml` descriptor. Portal end-users start
out interacting with the portlet user interface in portlet `VIEW` mode, but
switch to portlet `EDIT` mode in order to select custom preference values. 

    <portlet-preferences>
        <preference>
            <name>datePattern</name>
            <value>MM/dd/yyyy</value>
        </preference>
    </portlet-preferences>

Additionally, Portlet 2.0 lets you specify support for `EDIT` mode in the
`WEB-INF/portlet.xml` descriptor. 

    <supports>
        <mime-type>text/html</mime-type>
        <portlet-mode>view</portlet-mode>
        <portlet-mode>edit</portlet-mode>
    </supports>

Although support for portlet `EDIT` mode has been specified, the portlet
container does not necessarily know which JSF view should be rendered when the
user enters portlet `EDIT` mode. JSF portlet developers must specify the
Facelet view in the `WEB-INF/portlet.xml` descriptor that should be displayed
for each supported portlet mode. 

    <init-param>
        <name>javax.portlet.faces.defaultViewId.edit</name>
        <value>/edit.xhtml</value>
    </init-param>

<!-- This example's mapping values are slightly different from that of the
portlet in the previous section. Were they meant to be different? In the
previous example descriptor we mapped EDIT mode to the /preferences.xhtml
facelet, yet in this example we map EDIT mode to the /edit.xhtml facelet. This
seems confusing. - Jim -->

Terrific! You're now familiar with specifying and using portlet preferences in
your JSF portlet. 


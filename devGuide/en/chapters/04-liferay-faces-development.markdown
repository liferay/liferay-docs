# Liferay Faces Reference Documentation [](id=liferay-faces-reference-documentation-liferay-portal-6-2-dev-guide-04-en)

![Liferay Faces logo](../../images/04-liferay-faces-logo.png)

Do you want to develop MVC-based portlets using the Java EE standard? Do you
want use a portlet development framework that provides a UI component model that
makes it easy to develop sophisticated, rich UIs? Or, have you been developing
web apps using JSF that you'd like to use in Liferay Portal? If you answered yes
to any of these questions, you're in luck! *Liferay Faces* provides all of these
capabilities, and more. 

Liferay Faces is an umbrella project that provides support for the
JavaServer&#8482; Faces (JSF) standard within Liferay Portal. It encompasses the
following projects:

- **Liferay Faces Bridge** enables you to deploy JSF web apps as portlets
without writing portlet-specific Java code. It also contains innovative features
that make it possible to leverage the power of JSF 2.x inside a portlet
application. Liferay Faces Bridge implements the JSR 329 Portlet Bridge
Standard. 
- **Liferay Faces Alloy** enables you to use AlloyUI components in way that
is consistent with JSF development. 
- **Liferay Faces Portal** enables you to leverage Liferay-specific utilities
and UI components in JSF portlets. 

If you're new to JSF, you probably want to know its strengths, its weaknesses,
and how it stacks up to developing portlets with CSS/JavaScript. We'll give you
information on JSF and Liferay Faces to help you decide what framework is best
for your needs. 

Here some reasons why to use JSF and Liferay Faces:

- JSF is the Java EE standard for developing web applications that utilize the
Model/View/Controller (MVC) design pattern. As a standard, the specification is
actively maintained by the JCP and the Oracle reference implementation (Mojarra)
has frequent releases. Software Architects often choose standards like JSF
because they are supported by Java EE application server vendors and have a
guaranteed service-life according to Service Level Agreements (SLAs).
- JSF was first introduced in 2003 and is therefore a mature technology for
developing web applications that are (arguably) simpler and easier to maintain.
- JSF Portlet Bridges (like Liferay Faces Bridge) are also standardized by the
JCP and make it possible to deploy JSF web applications as portlets without
writing portlet-specific Java code.
- Support for JSF (via Liferay Faces) is included with Liferay EE support.
- JSF is a unique framework in that it provides a UI component model that makes
it easy to develop sophisticated, rich user interfaces.
- JSF has built-in Ajax functionality that provides automatic updates to the
browser by replacing elements in the DOM.
- JSF is designed with many extension points that make a variety of integrations
possible.
- There are several JSF component suites available including Liferay Faces
Alloy, ICEfaces, Primefaces, and RichFaces. Each of these component suites
fortify JSF with a variety of UI components and complimentary technologies such
as Ajax Push.
- JSF is a good choice for server-side developers that need to build web user
interfaces. This enables server-side developers to focus on their core
competencies rather than being experts in HTML/CSS/JavaScript.
- JSF provides the Facelets templating engine which makes it possible to create
reusable UI components that are encapsulated as markup.
- JSF provides good integration with HTML5 markup
- JSF provides the Faces Flows feature which makes it easy for developers to
create wizard-like applications that flow from view-to-view. 
- JSF has good integration with dependency injection frameworks such as CDI and
Spring that makes it easy for developers to create beans that are placed within
a scope managed by a container: @RequestScoped, @ViewScoped, @SessionScoped,
@FlowScoped
- Since JSF is a stateful technology, the framework encapsulates the
complexities of managing application state so that the developer doesn't have to
write state management code. It is also possible to use JSF in a stateless
manner, but some of the features of application state management become
effectively disabled.

There are some cases of why or when not to use JSF. For example, if you are a
front-end developer who makes heavy use of HTML/CSS/JavaScript, you might find
that JSF UI components render HTML in a manner that gives you less control over
the overall HTML document. So, sticking with JavaScript and leveraging
AlloyUI may be better for you. Or, perhaps standards aren't a major
consideration for you or you may simply prefer developing portlets using your
current framework. 

Whether you develop your next portlet application with JSF with Liferay Faces
or with HTML/CSS/JavaScript is entirely up to you. But you probably want to
learn more about Liferay Faces and try it out for yourself. And you'll do just
those things throughout the sections ahead. 

In the Liferay Faces documentation, we'll cover the following topics:

- JSF Portlet Development
- Liferay Faces Bridge
- Liferay Faces Portal
- Liferay Faces Alloy
- Liferay Faces Versioning Scheme
- How to Migrate Projects from PortletFaces to Liferay Faces
- Building Liferay Faces From Source

We'll guide you through developing portlets with Liferay Faces and introduce you
to each of the Liferay Faces projects: Liferay Faces Bridge, Liferay Faces
Alloy, and the Liferay Faces Portal Projects. We'll explain everything from
choosing the correct Liferay Faces Version for your project, to updating your
project from PortletFaces to Liferay Faces. 

Let's start developing JSF portlets using Liferay Faces. 

## Developing JSF Portlets [](id=develop-jsf-portlets-liferay-portal-6-2-dev-guide-en)

Liferay fully supports developing and deploying JSF portlets on Liferay Portal,
with the help of Liferay Faces Bridge. Liferay Faces Bridge provides the means
for deploying JSF portlets on Liferay Portal. In fact, the bridge supports
deployment of JSF web applications as portlets on any JSR 286 (Portlet 2.0)
compliant portlet containers, like Liferay Portal 5.2, 6.0, 6.1, and 6.2.
Liferay Faces Bridge not only implements the JSR 329 Portlet Bridge Standard,
but it also contains innovative features that make it possible to leverage the
power of JSF 2.x inside a portlet application. Liferay Faces Bridge makes the
JSF portlet development experience as close as possible to JSF web app
development. We'll take you through the portlet development process and show you
how to leverage Liferay Faces Bridge's full potential with your JSF portlets.

In this section, we'll demonstrate how to develop JSF portlets with the standard
features you expect and additional features you'll appreciate for building JSF
portlets that are powerful and easy to maintain. 

Here are the topics we'll cover: 

- Creating a JSF Portlet Project
- Specifying the portlet.xml for Your JSF Portlet
- Utilizing Portlet Preferences
- Accessing the Portlet API
- Internationalizing JSF Portlet Content
- Utilizing IPC with JSF Portlets 
- Leveraging CDI in JSF portlets
- Using Liferay Faces Bridge's JSF Component Tags 
- Dynamically Adding JSF Portlets to Liferay Portal
- Extending Liferay Faces Bridge via Factory Wrappers

Let's get started with simple tutorial on creating and deploying a JSF portlet. 

### Creating a JSF Portlet Project [](id=create-jsf-portlet-project-liferay-portal-6-2-dev-guide-en)

We want to make it easy for you to implement portlets using JSF. And Liferay
IDE, with it's powerful portlet plugin wizard, provides you with a great
environment to do just that. The wizard lets you select a component suite that's
right for your project, including JSF's standard UI component suite, ICEfaces,
Liferay Faces Alloy, PrimeFaces, and RichFaces. Of course, you can use any
development environment you like for building JSF portlets; but Liferay IDE is
hard to beat. 

We'll demonstrate creating a JSF portlet project using Liferay IDE/Developer
Studio, so you can see just how easy it is. 

***In Developer Studio:***

1.  Go to File &rarr; New &rarr; Liferay Plugin Project.  

2.  In the project creation wizard's first window, you'll name your project and
    specify its development and runtime environments. 

    2.1 Fill in the *Project name* and *Display name* with *my-jsf-portlet*
    and *My JSF*, respectively. 

    2.2. Leave the *Use default location* checkbox checked. By default, the
    default location is set to your current workspace. If you'd like to
    change where your plugin project is saved in your file system, uncheck
    the box and specify your alternate location. 

    2.3. Select the *Ant (liferay-plugins-sdk)* option for your build type.
    If you'd like to use *Maven* for your build type, navigate to the
    [Developing Plugins Using
    Maven](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-plugins-using-maven-liferay-portal-6-2-dev-guide-02-en)
    section for details. 

    2.3. Your configured SDK and Liferay Runtime should already be selected.
    If you haven't yet pointed Liferay IDE to a Plugins SDK, click
    *Configure SDKs* to open the *Installed Plugin SDKs* management wizard.
    You can also access the *New Server Runtime Environment* wizard if you
    need to set up your runtime server; just click the *New Liferay Runtime*
    button next to the *Liferay Portal Runtime* dropdown menu. 

    2.4. Select *Portlet* as your Plugin type and click *Next*. 

3.  In the project creation wizard's first window, you'll select the framework
    for your portlet and a UI component suite. 

    3.1 Select the *JSF 2.x* portlet framework. 

    Immediately, the wizard lists the JSF component suites available,
    including the JSF Standard suite, ICEfaces, Liferay Faces Alloy,
    PrimeFaces, and RichFaces. 

    3.2. Select the *PrimeFaces* UI component suite and click *Finish*. 

Great! Your new JSF portlet plugin project is ready for you to develop JSF
portlets. 

![Figure 4.x: Liferay Faces supports using the most popular component suites with your JSF portlets.](../../images/jsf-select-primefaces-comp-suite.png)

Next, let's configure your project to use the Liferay Faces `.jar` files. To
simplify things, we'll set up Ivy to download these artifacts. 

1.  Open your Plugins SDK's `ivy-settings.xml` file. 

2.  Add the following element to the list of `<ibiblio>` elements within the
    `<resolvers>` element. 

        <ibiblio m2compatible="true" name="sonatype-snapshots"
            root="https://oss.sonatype.org/content/repositories/snapshots/" />

3.  Add the following `<resolver>` element within the `<chain>` element and save
    your changes.

        <resolver ref="sonatype-snapshots" />

Your `ivy-settings.xml` file should look like this:

    <ivysettings>
        <settings defaultResolver="default" />

        <resolvers>
            <ibiblio m2compatible="true" name="jboss-nexus"
                root="https://repository.jboss.org/nexus/content/repositories/releases" />
            <ibiblio m2compatible="true" name="jboss-thirdparty"
                root="https://repository.jboss.org/nexus/content/repositories/thirdparty-releases" />
            <ibiblio m2compatible="true" name="maven2"
                root="http://repo1.maven.org/maven2" />
            <ibiblio m2compatible="true" name="ow2-public"
                root="http://repository.ow2.org/nexus/content/repositories/public" />
            <ibiblio m2compatible="true" name="primefaces"
                root="http://repository.primefaces.org" />
            <ibiblio m2compatible="true" name="sonatype-snapshots"
                root="https://oss.sonatype.org/content/repositories/snapshots/" />

            <chain dual="true" name="default">
                <resolver ref="jboss-nexus" />
                <resolver ref="jboss-thirdparty" />
                <resolver ref="maven2" />
                <resolver ref="ow2-public" />
                <resolver ref="primefaces" />
                <resolver ref="sonatype-snapshots" />
            </chain>
        </resolvers>
    </ivysettings>

Now that Plugins SDK can access the Sonatype repository, let's specify the
version of the artifacts for the JSF plugin project to use. 

1.  Open the `ivy.xml` file found in your JSF plugin project's root directory. 

2.  Replace the current revision values of each `liferay-faces-*` dependency
element with `"3.2.4-ga5-SNAPSHOT"`, so that the attribute settings are
`rev="3.2.4-ga5-SNAPSHOT"`. Save your changes. 

Here's what your `ivy.xml` file should look like: 

    <?xml version="1.0"?>

    <ivy-module
        version="2.0"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:noNamespaceSchemaLocation="http://ant.apache.org/ivy/schemas/ivy.xsd"
    >
        <info module="portlet_primefaces_tmpl" organisation="com.liferay" />

        <dependencies defaultconf="default">
            <dependency name="jboss-el" org="org.jboss.el" rev="2.0.1.GA" />
            <dependency name="jsf-api" org="com.sun.faces" rev="2.1.21" />
            <dependency name="jsf-impl" org="com.sun.faces" rev="2.1.21" />
            <dependency name="liferay-faces-bridge-api" org="com.liferay.faces" rev="3.2.4-ga5-SNAPSHOT" />
            <dependency name="liferay-faces-bridge-impl" org="com.liferay.faces" rev="3.2.4-ga5-SNAPSHOT" />
            <dependency name="liferay-faces-portal" org="com.liferay.faces" rev="3.2.4-ga5-SNAPSHOT" />
            <dependency name="liferay-faces-util" org="com.liferay.faces" rev="3.2.4-ga5-SNAPSHOT" />
            <dependency name="primefaces" org="org.primefaces" rev="3.5" />
        </dependencies>
    </ivy-module>

Immediately, Ivy downloads the Liferay Faces artifacts into your Plugins SDK.
Downloading the artifacts for the first time may take a minute or two. 

---

 ![Note](../../images/tip.png) **Warning:** Liferay IDE may
 report an error in your `portlet.xml` file, complaining that the class
 `javax.portlet.faces.GenericFacesPortlet` is not found in the Java Build Path.
 This is a known issue where the IDE is not aware that the Liferay Faces
 artifacts contain this class. To resolve the errors, simply save an edit to the
 `portlet.xml` file (e.g., add a space character, delete it, and save the file).
 Liferay IDE will reparse the descriptors and realize it has the class in the
 Java Build Path. 

---

Let's make the portlet display a calendar. We'll replace the default "hello
world" text output in the portlet's UI, with a PrimeFaces calendar component.
Open the `view.xhtml` facelet file from the portlet project's `docroot/views`
folder and replace the element `<h:outputText
value="#{i18n['my-jsf-hello-world']}" />` with the following lines of code: 

    <h:form>
       <p:calendar></p:calendar>
    </h:form>

Your `view.xhtml` file should look like this:

    <?xml version="1.0"?>
    
    <f:view
        xmlns="http://www.w3.org/1999/xhtml"
        xmlns:c="http://java.sun.com/jsp/jstl/core"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:p="http://primefaces.org/ui"
        xmlns:ui="http://java.sun.com/jsf/facelets"
    >
        <h:head />
        <h:body>
            <h:form>
               <p:calendar  mode="inline" />
            </h:form>
        </h:body>
    </f:view>

It's time to deploy your JSF portlet to the portal and see what it looks like. 

### Deploying the JSF Portlet [](id=deploy-the-jsf-portlet-liferay-portal-6-2-dev-guide-en)

Liferay provides a mechanism called auto-deploy that makes deploying portlets
(and any other plugin types) a breeze. All you need to do is drop the plugin's
`.war` file into the deploy directory, and the portal makes the necessary
changes specific to Liferay and then deploys the plugin to the application
server. This is a method of deployment used throughout this guide.

---

 ![Note](../../images/tip.png) **Note:** Liferay supports a wide
 variety of application servers. Many, such as Tomcat and Jboss, provide a
 simple way to deploy web applications by just copying a file into a folder and
 Liferay's auto-deploy mechanism takes advantage of that ability. You should be
 aware though, that some application servers, such as Websphere or Weblogic,
 require the use of specific tools to deploy web applications; Liferay's
 auto-deploy process won't work for them. 

---

***Deploying in Developer Studio***: Drag your portlet project onto your
server. When deploying your plugin, your server displays messages indicating
that your plugin was read, registered and is now available for use. 

    Reading plugin package for my-jsf-portlet
    Registering portlets for my-jsf-portlet
    1 portlet for my-jsf-portlet is available for use

If at any time you need to redeploy your portlet while in Developer Studio,
right-click your portlet located underneath your server and select *Redeploy*. 

***Deploying in the terminal***: Open a terminal window in your
`portlets/my-jsf-portlet` directory and enter

    ant deploy

A BUILD SUCCESSFUL message indicates your portlet is now being deployed. If you
switch to the terminal window running Liferay, within a few seconds you should
see the message `1 portlet for my-jsf-portlet is available for use`. If
not, double-check your configuration. 

In your web browser, log in to the portal as explained earlier. Click the Add
button, which appears as a *Plus* symbol in the top right hand section of your
browser. Then click *Applications*, find the My JSF portlet in the
*Sample* category, and click *Add*. Your portlet appears in the page. 

![Figure 4.x: Liferay Faces lets you know when a UI component requires a page refresh to render the first time.](../../images/jsf-primefaces-portlet-needs-refresh.png) 

Refresh the page and the portal renders your portlet's calendar component.  

![Figure 4.x: Powerful UI components, like this PrimeFaces calendar, are a snap to include in your portlet UI.](../../images/jsf-primefaces-portlet-with-calendar.png) 

It's just that easy to create and deplpoy JSF portlet plugins! 

Next, let's get familiar with the portlet deployment descriptor file
(`portlet.xml`), and consider the descriptor requirements for JSF portlets.  

### Specifying the portlet.xml for Your JSF Portlet [](id=portlet-xml-file-jsf-portlet-liferay-portal-6-2-dev-guide-en)

Each portlet project must have a `WEB-INF/portlet.xml` deployment descriptor file. As
we demonstrated in the previous section, Liferay IDE and the Plugins SDK create
this file for you. But there are a couple unique requirements for JSF portlets
with respect to their deployment descriptors. 

First, utilizing JSF 2.x in a portlet requires that
`javax.portlet.faces.GenericFacesPortlet` be named as the `<portlet-class>`
entity. Notice that the following `portlet.xml` file meets this requirement. 

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
The `portlet.xml` file content above supports the view, edit, and help portlet
modes and maps each of those modes to a specific facelet. 

For example, view mode support is specified by the
`<portlet-mode>view</portlet-mode>` element and is mapped to the `/view.xhtml`
facelet by the following `<init-param>` element:

    <init-param>
        <name>javax.portlet.faces.defaultViewId.view</name>
        <value>/view.xhtml</value>
    </init-param>

Now that we've got `WEB-INF/portlet.xml` set up, let's move on to portlet
preferences. 

### Utilizing Portlet Preferences With JSF [](id=use-portletpreferences-with-jsf-liferay-portal-6-2-dev-guide-04-en)

JSF portlet developers often have the requirement to provide the end-user with
the ability to personalize the portlet in some way. To meet this requirement,
the Portlet 2.0 specification provides the ability to define portlet preferences
for each portlet. Preference names and default values can be defined in the
`WEB-INF/portlet.xml` descriptor. Portal end-users start out interacting with
the portlet user interface in portlet `VIEW` mode but switch to portlet `EDIT`
mode in order to select custom preference values. 

    <portlet-preferences>
        <preference>
            <name>datePattern</name>
            <value>MM/dd/yyyy</value>
        </preference>
    </portlet-preferences>

Additionally, Portlet 2.0 provides the ability to specify support for `EDIT`
mode in the `WEB-INF/portlet.xml` descriptor. 

    <supports>
        <mime-type>text/html</mime-type>
        <portlet-mode>view</portlet-mode>
        <portlet-mode>edit</portlet-mode>
    </supports>

Although support for portlet `EDIT` mode has been specified, the portlet
container does not necessarily know which JSF view should be rendered when the
user enters portlet `EDIT` mode. JSF portlet developers must specify the
Facelet view, in the `WEB-INF/portlet.xml` descriptor, that is to be displayed
for each supported portlet mode. 

    <init-param>
        <name>javax.portlet.faces.defaultViewId.edit</name>
        <value>/edit.xhtml</value>
    </init-param>

Now that we have dealt with `EDIT` mode, let's learn how to access the portlet
API. 

### Accessing The Portlet API With ExternalContext [](id=access-portlet-api-with-externalcontext-liferay-portal-6-2-dev-guide-en)

Just as JSF *web app* developers rely on `ExternalContext` to access to the
Servlet API, JSF *portlet* developers rely on it to access to the Portlet API. 

As a portlet developer, you'll often need to access an instance of the
`javax.portlet.PortletRequest` or `javax.portlet.PortletResponse` objects. These
instances are accessed in the same way that a
`javax.servlet.http.HttpServletRequest` or
`javax.servlet.http.HttpServletResponse` would be accessed in a servlet
evironment, except that they are cast to the portlet versions of the classes
instead. In the example code below, the request object from
`externalContext.getRequest()` is cast to the `PortletRequest` class and the
response object from `externalContext.getResponse()` is cast to the
`PortletResponse` class. 

    import javax.portlet.PortletRequest;
    import javax.portlet.PortletResponse;
    ....

    public class PortletBackingBean {

        public void submit() {
            FacesContext facesContext =
                FacesContext.getCurrentInstance();

            ExternalContext externalContext =
                facesContext.getExternalContext();

            PortletRequest portletRequest =
                (PortletRequest) externalContext.getRequest();

            PortletResponse portletResponse =
                (PortletResponse) externalContext.getResponse();
        }
    }
            
The code listing above uses the singleton class called `LiferayFacesContext`,
which has methods `getPortletRequest()` and `getPortletResponse()`. This
class comes with Liferay Faces Portal, which we'll cover in detail in later
sections. You can leverage the `LiferayFacesContext` class in your JSF portlets
on Liferay to get easy access to the portlet requests and responses. 

The next section explains how to internationalize JSF Portlets. 

### Internationalizing JSF Portlets [](id=internationalize-jsf-portlets-i18n-liferay-portal-6-2-dev-guide-en)

There are at least two ways to handle internationalization with JSF portlets in
Liferay Portal: 

1. Using the standard JSF mechanism to create your own *i18n* keyword, as shown in the [jsf2-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
   demo.
    - Create a properties file in the classpath like
    [`i18n.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/resources/i18n.properties)
    - Create a `<resource-bundle>` entry in the `<faces-config>` element, as
    demonstrated in
    [`faces-config.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/faces-config.xml)
    - Use your custom *i18n* keyword Expression Language (EL) in your Facelet
    view like [`applicant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/bridge/jsf2-portlet/src/main/webapp/views/applicant.xhtml)

2. Using the built-in *i18n* keyword provided by the [Liferay Faces
   Portal](http://www.liferay.com/community/liferay-projects/liferay-faces/portal)
   project, as shown in the [jsf2-registration-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-registration-portlet)
   demo. This method integrates JSF and Liferay very well, because it allows you
   to "hook" into the thousands of existing internationalized keys that ship
   with Liferay Portal, in addition to the keys you add. 
    - Create a hook inside your portlet plugin, like
    [`liferay-hook.xml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/WEB-INF/liferay-hook.xml)
    - Create internaltionalized Langauge properties files, like
    [`Language_en_US.properties`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/resources/Language_en_US.properties) 
    - Use the built-in *i18n* keyword Expression Language (EL) in your Facelet
    view, like 
    [`registrant.xhtml`](https://github.com/liferay/liferay-faces/blob/master/demos/portal/jsf2-registration-portlet/src/main/webapp/views/registrant.xhtml) 

<!-- This section on JSF portlet i18n needs to be refactored to demonstrate,
with code, how to internationalize a JSF portlet. - Jim -->

Next, we'll learn how to communicate between JSF portlets using IPC. 

### Utilizing IPC With JSF [](id=utilizing-ipc-with-jsf-liferay-portal-6-2-dev-guide-04-en)

Liferay Faces Bridge supports Portlet 2.0 Inter Portlet Communication (IPC)
using the JSR 329 approach for supporting Portlet 2.0 Events and Portlet 2.0
Public Render Parameters. 

---

 ![Note](../../images/tip.png) **Note:** Visit
 <http://www.liferay.com/community/liferay-projects/liferay-faces/demos> for
 demo portlets that demonstrate how to use each of these approaches to IPC. You
 can also find demo portlets that show how to use Ajax Push for IPC using either
 ICEfaces+ICEPush or PrimeFaces+PrimePush. 

---

#### Utilizing Portlet 2.0 Public Render Parameters [](id=portlet-2-0-public-render-parameters-jsf-liferay-portal-6-2-dev-guide-en)

The Public Render Parameters technique provides a way for portlets to share data
by setting public/shared parameter names in a URL controlled by the portal.
While the benefit of this approach is that it is relatively easy to implement,
the drawback is that only small amounts of data can be shared. Typically the
kind of data that is shared is simply the value of a database primary key. As
required by the Portlet 2.0 standard, Public Render Parameters must be declared
in the `WEB-INF/portlet.xml` descriptor. 

This example excerpt from a `WEB-INF/portlet.xml` descriptor demonstrates
setting a public render parameter for a customer ID, shared between a customer
portlet and a bookings portlet: 

    <portlet>
        <portlet-name>customersPortlet</portlet-name>
        <supported-public-render-parameter>selectedCustomerId</supported-public-render-parameter>
    </portlet>
    <portlet>
        <portlet-name>bookingsPortlet</portlet-name>
        <supported-public-render-parameter>selectedCustomerId</supported-public-render-parameter>
    </portlet>
    <public-render-parameter>
        <identifier>selectedCustomerId</identifier>
        <qname xmlns:x="http://liferay.com/pub-render-params">x:selectedCustomerId</qname>
    </public-render-parameter>

Fortunately, the JSR 329 standard defines a mechanism by which developers can
use Portlet 2.0 Public Render Parameters for IPC in a way that is more natural
to JSF development. Section 5.3.2 requires the bridge to inject the public
render parameters into the Model concern of the MVC design pattern (as in JSF
model managed-beans) after the `RESTORE_VIEW` phase completes. This is
accomplished by evaluating the EL expressions found in the
`<model-el>...</model-el>` section of the `WEB-INF/faces-config.xml` descriptor.
The `WEB-INF/faces-config.xml` descriptor excerpt below demonstrates using this
mechanism to the customer and bookings portlet example we used in the previous
code listing: 

    <faces-config>
        <application>
            <application-extension>
                <bridge:public-parameter-mappings>
                    <bridge:public-parameter-mapping>
                        <parameter>customersPortlet:selectedCustomerId</parameter>
                        <model-el>#{customersModelBean.selectedCustomerId}</model-el>
                    </bridge:public-parameter-mapping>
                    <bridge:public-parameter-mapping>
                        <parameter>bookingsPortlet:selectedCustomerId</parameter>
                        <model-el>#{bookingsModelBean.selectedCustomerId}</model-el>
                    </bridge:public-parameter-mapping>
                </bridge:public-parameter-mappings>
            </application-extension>
        </application>
    </faces-config>

Section 5.3.2 of the JSR 329 standard also requires that if a
`bridgePublicRenderParameterHandler` has been registered in the
`WEB-INF/portlet.xml` descriptor, then the handler must be invoked so that it
can perform any processing that might be necessary. Optionally, you can
implement and register a `bridgePublicRenderParameterHandler` for processing
public render parameters. 

For example, a `BridgePublicRenderParameterHandler` for processing public render
params for the currently selected customer for a Bookings portlet could be
stubbed out like the following class code:

    package com.liferay.faces.example.handler;

    import javax.faces.context.FacesContext;

    import com.liferay.faces.bridge.BridgePublicRenderParameterHandler;

    public class CustomerSelectedHandler
    implements BridgePublicRenderParameterHandler {

        public void processUpdates(FacesContext facesContext) {
            // Here is where you would perform any necessary processing of public
            // render parameters
        }

    }

For the `BridgePublicRenderParameterHandler` to be invoked, it would need to be
registered in an `<init-param>` element within the portlet's `<portlet>` element
in the plugin project's `WEB-INF/portlet.xml` descriptor: 

    <init-param>
        <name>javax.portlet.faces.bridgePublicRenderParameterHandler</name>
        <value>com.liferay.faces.example.handler.CustomerSelectedHandler</value>
    </init-param>

---

 ![Note](../../images/tip.png) **Sample:** For a complete example demonstrating
 public render parameters and a `bridgePublicRenderParameterHandler`, see the
 [JSF2 IPC Public Render Parameters Portlet](https://github.com/liferay/liferay-faces/tree/3.1.3-ga4/demos/bridge/jsf2-ipc-pub-render-params-portlet)
 demo on GitHub.
 
---

Now that we've discussed Public Render Parameters for JSF in IPC, let's look at
Events IPC. 

#### Handling Portlet 2.0 Events [](id=portlet-2-0-events-jsf-liferay-portal-6-2-dev-guide-en)

As discussed in the [Utilizing IPC With
JSF](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/utilizing-ipc-with-jsf-liferay-portal-6-2-dev-guide-04-en),
the Server-Side Events technique provides a way for portlets to share data using
an event-listener design. When using this form of IPC, the portlet container
acts as broker and distributes events and payload (data) to portlets. One
requirement of this approach is that the payload must implement the
`java.io.Serializable` interface since it might be sent to a portlet in another
WAR running in a different classloader. As required by the Portlet 2.0 standard,
Events must be declared in the `WEB-INF/portlet.xml` descriptors of the involved
portlets. 

The following example `WEB-INF/portlet.xml` descriptor snippet defines an IPC
event for when a customer is edited.  The `bookingsPortlet` portlet is
registered as a publisher (or sender) for it. The `customersPortlet` portlet, on the
other hand, is registered as a processor (or listener) for that event type. As a
result, when a customer is edited in the `bookingsPortlet` portlet, that
portlet publishes the event and the `customersPortlet` portlet is notified for
processing the event. 

    <portlet>
        <portlet-name>customersPortlet</portlet-name>
        <supported-processing-event>
            <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        </supported-processing-event>
    </portlet>
    <portlet>
        <portlet-name>bookingsPortlet</portlet-name>
        <supported-publishing-event>
            <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        </supported-publishing-event>
    </portlet>

    ....

    <event-definition>
        <qname xmlns:x="http://liferay.com/events">x:ipc.customerEdited</qname>
        <value-type>com.liferay.faces.example.dto.Customer</value-type>
    </event-definition>

Optionally, you can implement a `BridgeEventHandler` for an event and register
the event in the `WEB-INF/portlet.xml` descriptor. If a `BridgeEventHandler` has
been registered in the `WEB-INF/portlet.xml` descriptor, Section 5.2.5 of the
JSR 329 standard requires that the handler must be invoked so that it can
perform any event processing that might be necessary. 

When the customer's details (such as first name
/ last name) are edited in the Bookings portlet, the event 
named `ipc.customerEdited` is sent back to the Customers portlet and is
processed by  the following `CustomerEditedEventHandler` class: 

    ...

    import javax.faces.context.FacesContext;
    import javax.portlet.Event;
    import javax.portlet.faces.BridgeEventHandler;
    import javax.portlet.faces.event.EventNavigationResult;

    ...

    public class CustomerEditedEventHandler implements BridgeEventHandler {

            ....

            public EventNavigationResult handleEvent(FacesContext facesContext, Event event) {
                    EventNavigationResult eventNavigationResult = null;
                    String eventQName = event.getQName().toString();
    
                    if (eventQName.equals("{http://liferay.com/events}ipc.customerEdited")) {
                        ...
                    }
    
                    return eventNavigationResult;
            }

            ....
    }

And here's the descriptor for registering the `CustomerEditedEventHandler` class
as a bridge event handler for the Customers portlet.  The following
`<init-param>` belongs in the Customers portlet's `<portlet>` element, in the
`WEB-INF/portlet.xml` descriptor.  

    <init-param>
        <name>javax.portlet.faces.bridgeEventHandler</name>
        <value>com.liferay.faces.example.event.CustomerEditedEventHandler</value>
    </init-param>

---

 ![Note](../../images/tip.png) **Sample:** 
 For a complete example demonstrating JSF 2 IPC events, see the [JSF2 IPC Events
 -
 Customers](https://github.com/liferay/liferay-faces/tree/3.1.3-ga4/demos/bridge/jsf2-ipc-events-customers-portlet)
 and [JSF2 IPC Events -
 Bookings](https://github.com/liferay/liferay-faces/tree/3.1.3-ga4/demos/bridge/jsf2-ipc-events-bookings-portlet)
 demo portlets on GitHub. 

---

Now that we've discussed some common basic JSF portlet development topics, let's
consider how to use dependency injection in JSF portlets. 

### Developing JSF Portlets with CDI [](id=developing-jsf-portlets-with-cdi-liferay-portal-6-2-dev-guide-en)

<!-- Explain what CDI is, the benefits of using it and any drawbacks to using
it. - Jim -->

In December 2009, [JSR 299](http://jcp.org/en/jsr/detail?id=299) introduced the
Contexts and Dependency Injection (CDI) 1.0 standard into the Java EE 6
platform. In April 2013, [JSR 346](http://jcp.org/en/jsr/detail?id=346) updated
CDI to version 1.1 for Java EE 7. In addition, [JSR
344](http://jcp.org/en/jsr/detail?id=344), the JSF 2.2 specification which is
another component of Java EE 7, introduced a dependency on the CDI API for the
`javax.faces.view.ViewScoped` annotation and for the Faces Flows feature. JBoss
[Weld](http://seamframework.org/Weld) is the Reference Implementation (RI) for
CDI and Apache [OpenWebBeans](http://openwebbeans.apache.org/) is another open
source implementation. 

- Configuring CDI on Liferay Portal
- Configuring the Liferay CDI Portlet Bridge
- Understanding CDI Annotations
- Understanding JSF 2 Annotation

Let's configure Weld CDI on Liferay Portal. 

#### Configuring CDI on Liferay Portal [](id=configure-weld-cdi-with-liferay-portal-6-2-dev-guide-en)

In order to utilize Weld in your Liferay portlets, use one of the following
portal/app server combinations: 

- Liferay Portal 6.1/6.2 (Tomcat)
- Liferay Portal 6.1/6.2 (GlassFish)
    - Apply patch attached to [LPS-35558](http://issues.liferay.com/browse/LPS-35558).
    - [Upgrade Mojarra in GlassFish](http://www.liferay.com/community/wiki/-/wiki/Main/Upgrading+Mojarra+in+Oracle+GlassFish)
      to version 2.1.21 (or higher).
    - [Upgrade Weld in GlassFish](http://www.liferay.com/community/wiki/-/wiki/Main/Upgrading+Weld+in+Oracle+GlassFish)
      to version 1.1.10.Final (or higher).
- Liferay Portal 6.1/6.2 (JBoss AS)
    - Apply patch attached to [LPS-35558](http://issues.liferay.com/browse/LPS-35558)
    - [Upgrade Mojarra in JBoss AS](http://www.liferay.com/community/wiki?p_p_id=36&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1&_36_struts_action=%2Fwiki%2Fedit_page&_36_redirect=http%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FDeveloping%2BJSF%2BPortlets%2Bwith%2BCDI&p_r_p_185834411_nodeId=1071674&p_r_p_185834411_title=%5Bhttp%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FUpgrading%2BMojarra%2Bin%2BJBoss%2BAS)
      to version 2.1.21 (or higher). 
    - [Upgrade Weld in JBoss AS](http://www.liferay.com/community/wiki?p_p_id=36&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1&_36_struts_action=%2Fwiki%2Fedit_page&_36_redirect=http%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FDeveloping%2BJSF%2BPortlets%2Bwith%2BCDI&p_r_p_185834411_nodeId=1071674&p_r_p_185834411_title=%5Bhttp%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FUpgrading%2BWeld%2Bin%2BJBoss%2BAS)
      to version 1.1.10.Final (or higher). 
- Liferay Portal 6.1/6.2 (Resin)

When developing portlets with CDI 1.0, it is necessary to includea 
`WEB-INF/beans.xml` descriptor in the WAR deployment so that the CDI implementation can
detect your classes CDI related annotations when it scans the classpath. In
addition, for JBoss AS 7, it is necessary to include
`WEB-INF/jboss-deployment-structure.xml` descripter in the WAR deployment. 

Here's an example `WEB-INF/beans.xml` descriptor: 

    <beans xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee
        http://java.sun.com/xml/ns/javaee/beans_1_0.xsd">
    </beans>

Here's an example of a `WEB-INF/jboss-deployment-structure.xml` descriptor for
JBoss: 

    <?xml version="1.0"?>
    <jboss-deployment-structure xmlns="urn:jboss:deployment-structure:1.0">
        <deployment>
            <exclusions>
                <module name="javaee.api" />
                <module name="org.apache.log4j" />
            </exclusions>
            <dependencies>
                <module name="com.liferay.portal" />
                <module name="javax.annotation.api" />
                <module name="javax.enterprise.api" />
                <module name="javax.inject.api" />
                <module name="javax.interceptor.api" />
                <module name="javax.validation.api" />
                <module name="javax.mail.api" />
                <module name="org.jboss.modules" />
            </dependencies>
        </deployment>
    </jboss-deployment-structure>

Next, we'll cover Weld configuration on the app server. Their are some different
configuration steps for different app servers. We'll look at the most common
configuration steps first. 

**Weld Configuration**

For most app servers, **excluding Resin**, the `WEB-INF/web.xml` descriptor of the
portlet must include the following markup: 

    <filter>    
        <filter-name>WeldCrossContextFilter</filter-name>    
        <filter-class>org.jboss.weld.servlet.WeldCrossContextFilter</filter-class>
    </filter>
    <filter-mapping>    
        <filter-name>WeldCrossContextFilter</filter-name>    
        <url-pattern>/*</url-pattern>    
        <dispatcher>INCLUDE</dispatcher>    
        <dispatcher>FORWARD</dispatcher>    
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>

If using Resin, then it is not necessary to configure JBoss Weld since Resin
includes the [CanDI](http://www.caucho.com/candi-java-dependency-injection/)
implementation of CDI by default. 

The next section contains information about specifically configuring Tomcat, so
developers running other application servers can skip it. 

**Additional Weld Configuration for Tomcat**

If Weld is running in a Java EE application server like Oracle GlassFish or
JBoss AS, then Weld is automatically included in the global classpath. But on
Tomcat, it is necessary to include the `weld-servlet.jar` dependency in either
the `tomcat/lib` global classpath, or directly in the `WEB-INF/lib` folder of a
portlet: 

    <!-- Required only for Tomcat -->
    <dependency>    
        <groupId>org.jboss.weld.servlet</groupId>    
        <artifactId>weld-servlet</artifactId>    
        <version>1.1.10.Final</version>
    </dependency>

Additionally, the following listener must be added to the `WEB-INF/web.xml`
descriptor: 

    <!-- Required only for Tomcat -->
    <listener>    
        <listener-class>org.jboss.weld.environment.servlet.Listener</listener-class>
    </listener>

Next we'll discuss configuring the Liferay CDI Portlet Bridge. 

#### Configuring the Liferay CDI Portlet Bridge [](id=configure-liferay-cdi-portlet-bridge-liferay-portal-6-2-dev-guide-en)

The Liferay CDI Portlet Bridge makes it possble to use CDI with your JSF
portlets on Liferay. Your JSF portlet projects must include the Liferay CDI
Portlet Bridge as a dependency.

For example, to specify the bridge dependency in a Maven project for Liferay
6.2, you'd add the following `<dependency>` to your POM's `<dependencies>`
element: 

    <dependency>    
        <groupId>com.liferay.cdi</groupId>    
        <artifactId>cdi-portlet-bridge-shared</artifactId>    
        <version>6.2.0.2</version>
    </dependency>

The `WEB-INF/portlet.xml` descriptor of the portlet must include the following
markup: 

    <filter>    
        <filter-name>CDIPortletFilter</filter-name>    
        <filter-class>com.liferay.cdi.portlet.bridge.CDIPortletFilter</filter-class>
        <lifecycle>ACTION_PHASE</lifecycle>
        <lifecycle>EVENT_PHASE</lifecycle>
        <lifecycle>RENDER_PHASE</lifecycle>
        <lifecycle>RESOURCE_PHASE</lifecycle>
    </filter>
    <filter-mapping>
        <filter-name>CDIPortletFilter</filter-name>    
        <portlet-name>my-portlet-name</portlet-name>
    </filter-mapping>

Additionally, the `WEB-INF/web.xml` descriptor of the portlet must include the
following markup: 

    <filter>
        <filter-name>CDICrossContextFilter</filter-name>
        <filter-class>com.liferay.cdi.portlet.bridge.CDICrossContextFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>CDICrossContextFilter</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>INCLUDE</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>ERROR</dispatcher>
    </filter-mapping>
    <listener>
        <listener-class>com.liferay.cdi.portlet.bridge.CDIContextListener</listener-class>
    </listener>

---

 ![Tip](../../images/tip.png) **Tip:** The Liferay Faces Project features the
 [jsf2-cdi-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-cdi-portlet) 
 demo (which is a variant of the
 [jsf2-portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
 demo). It's a good idea download and deploy the jsf2-cdi-portlet demo in your
 development environment in order to verify that CDI functions properly. 

---

Now that everything is configured, you are ready to begin development with CDI.

#### Understanding CDI in JSF Annotations [](id=cdi-jsf-annotations-liferay-portal-6-2-dev-guide-en)

When developing portlets with CDI, it is possible to annotate Java classes as
CDI managed beans with
[`@Named`](http://docs.oracle.com/javaee/6/api/javax/inject/Named.html) with the
following scopes:  

| CDI Annotation | Description |
|----------------|-------------|
| [`@ApplicationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html) | An `@ApplicationScoped` managed bean exists for the entire lifetime of the portlet application. |
| [`@ConversationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ConversationScoped.html) | A `@ConversationScoped` managed bean is created when `Conversation.begin()` is called and is scheduled for garbage collection when `Conversation.end()` is called. |
| [`@FlowScoped`](https://javaserverfaces.java.net/nonav/docs/2.2/javadocs/javax/faces/flow/FlowScoped.html) | A `@FlowScoped` managed bean is created when a JSF 2.2 Flow begins and scheduled for garbage collection when a JSF 2.2 Flow completes. |
| [`@RequestScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/RequestScoped.html) | A `@RequestScoped` managed bean exists during an `ActionRequest`, `RenderRequest`, or `ResourceRequest`. Beans that are created during the `ActionRequest` do not survive into the `RenderRequest`. |
| [`@SessionScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/SessionScoped.html) | A `@SessionScoped` managed bean exists for the duration of the user session. |

In addition to CDI scope annotations, it's important to understand JSF 2
annotations and their equivalency to CDI annotations. 

| JSF Annotation | Equivalent CDI Annotation |
|----------------|---------------------------|
| `javax.faces.ManagedBean` | [`javax.inject.Named`](http://docs.oracle.com/javaee/6/api/javax/inject/Named.html) |
| `javax.faces.ApplicationScoped` | [`javax.enterprise.context.ApplicationScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/ApplicationScoped.html) |
| `javax.faces.RequestScoped` | No such equivalent, since [`javax.enterprise.context.RequestScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/RequestScoped.html) does not span portlet lifecycle phases. 
| `javax.faces.SessionScoped` | [`javax.enterprise.context.SessionScoped`](http://docs.oracle.com/javaee/6/api/javax/enterprise/context/SessionScoped.html) |
| `javax.faces.ManagedProperty` (corresponding setter method required) | [`javax.inject.Inject`](http://docs.oracle.com/javaee/6/api/javax/inject/Inject.html) (corresponding setter method not required) |

Now that we have discussed JSF portlet development with CDI, let's take a
look at some UI component tags included with Liferay Faces Bridge. 

### Using Liferay Faces Bridge's JSF Component Tags [](id=liferay-faces-bridge-jsf-component-tags-liferay-portal-6-2-dev-guide-en)

Although the JSR 329 standard does not define any JSF components that bridge
implementations are required to provide, Liferay Faces Bridge comes with a
handful of components that are helpful to use in JSF portlets. 

Because Liferay Faces has several [active
versions](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)
(targeting different versions of JSF, Liferay Portal, etc.), there are several
versions of the project View Declaration Language (VDL) documentation for these
tags. The VDL documentation can be found at the following addresses: 

- The VDL documentation for Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>. 
- The VDL documentation for Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>.
- The VDL documentation for Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>.
- The VDL documentation for Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>.

<!-- Re-add VDLs for 3.2 through 4.2 when released. - Jim
- The VDL documentation for Liferay Faces 3.2 can be found at <http://docs.liferay.com/faces/3.2/vdldoc/>.
- The VDL documentation for Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

Liferay Faces Bridge provides the following UI component tags under the `bridge`
and `portlet` namespaces for the Bridge and Portlet 2.0 tags respectively. 

#### Bridge UIComponent Tags [](id=liferay-faces-bridge-uicomponent-tags-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge provides the following bridge-specific UIComponent tags as
part of its component suite. 

##### The bridge:inputFile tag [](id=liferay-faces-bridge-inputfile-tag-liferay-portal-6-2-dev-guide-en)

The `bridge:inputFile` tag renders an HTML `<input type="file"/>` tag, providing
file upload capability. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:bridge="http://liferay.com/faces/bridge"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body>
            <h:form>
                <bridge:inputFile fileUploadListener="#{backingBean.handleFileUpload}" multiple="multiple" />
                <h:commandButton value="Submit" />
            </h:form>
        </h:body>
    </f:view>

Here's a code snippet from a class that imports the `FileUploadEvent` class and
implements handling the file upload: 

    import com.liferay.faces.bridge.event.FileUploadEvent;

    ...

    @ManagedBean(name = "backingBean")
    @ViewScoped
    public class ApplicantBackingBean implements Serializable {

            public void handleFileUpload(FileUploadEvent fileUploadEvent)
            throws Exception {
                UploadedFile uploadedFile = fileUploadEvent.getUploadedFile();
                System.err.println("Uploaded file:" + uploadedFile.getName());
            }
        }
    }

---

 ![Note](../../images/tip.png) **Note:** Usage of this tag requires the Apache
 `commons-fileupload` and `commons-io` dependencies. See the [Demo JSF2
 Portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
 for more details.  

---

#### Portlet 2.0 UIComponent Tags [](id=faces-bridge-portlet-2-0-uicomponent-tags-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge provides the following Portlet 2.0 UIComponent tags as
part of its component suite. 

---

 ![Note](../../images/tip.png) **Note:** Although JSP tags are provided by the
 portlet container  implementation, Liferay Faces Bridge provides these tags in
 order to support their usage within Facelets. 

---

The first tag we will look at is `portlet:actionURL`.

##### The portlet:actionURL tag [](id=portlet-actionurl-tag-liferay-portal-6-2-dev-guide-04-en)

If the `var` attribute is present, the `portlet:actionURL` tag introduces an EL
variable that contains a `javax.portlet.ActionURL`, adequate for postbacks.
Otherwise, the URL is written to the response. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:actionURL var="myActionURL" >
                    <portlet:param name="foo" value="1234" />
                </portlet:actionURL>
                <h:outputText var="actionURL=#{myActionURL}" />
            </h:form>
        </h:body>
    </f:view>

Next we will look at an example of the `portlet:namespace` tag.

##### The portlet:namespace tag [](id=faces-bridge-portlet-namespace-tag-liferay-portal-6-2-dev-guide-en)

If the `var` attribute is present, the `portlet:namespace` tag introduces
an EL variable that contains the portlet namespace. Otherwise, the
namespace is written to the response.

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:namespace var="mynamespace" />
                <h:outputText var="namespace=#{mynamespace}" />
            </h:form>
        </h:body>
    </f:view>

The `portlet:param` tag is up next.

##### The portlet:param tag [](id=liferay-faces-bridge-portlet-param-tag-liferay-portal-6-2-dev-guide-en)

The `portlet:param` tag provides the ability to add a request parameter
name=value pair when nested inside `portlet:actionURL`, `portlet:renderURL`, or
`portlet:resourceURL` tags. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:actionURL>
                    <portlet:param name="foo" value="1234" />
                </portlet:actionURL>
            </h:form>
        </h:body>
    </f:view>

The next tag we will look at is the `portlet:renderURL` tag.

##### The portlet:renderURL tag [](id=faces-bridge-portlet-renderurl-tag-liferay-portal-6-2-dev-guide-en)

If the `var` attribute is present, the `portlet:renderURL` tag introduces an EL
variable that contains a `javax.portlet.PortletURL`, adequate for rendering.
Otherwise, the URL is written to the response. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:renderURL var="myRenderURL">
                    <portlet:param name="foo" value="1234" />
                </portlet:renderURL>
                <h:outputText var="actionURL=#{myRenderURL}" />
            </h:form>
        </h:body>
    </f:view>

Finally, we will look at the `portlet:resourceURL` tag.

##### The portlet:resourceURL tag [](id=faces-bridge-portlet-resourceurl-tag-liferay-portal-6-2-dev-guide-en)

If the `var` attribute is present, the `portlet:resourceURL` tag introduces an
EL variable that contains a `javax.portlet.ActionURL`, adequate for obtaining
resources. Otherwise, the URL is written to the response. 

    <?xml version="1.0" encoding="UTF-8"?>
    <f:view xmlns="http://www.w3.org/1999/xhtml"
        xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:portlet="http://java.sun.com/portlet_2_0"
        xmlns:ui="http://java.sun.com/jsf/facelets">
        <h:head />
        <h:body">
            <h:form>
                <portlet:resourceURL var="myResourceURL">
                    <portlet:param name="foo" value="1234" />
                </portlet:resourceURL>
                <h:outputText var="actionURL=#{myResourceURL}" />
            </h:form>
        </h:body>
    </f:view>

Now that we've introduced you to some of Liferay Faces Bridge's UIComponent
tags, let's explore how to dynamically add JSF portlets to portal pages. 

### Dynamically Adding JSF Portlets to Liferay Portal (Runtime Portlets) [](id=dynamically-add-jsf-portlets-to-liferay-portal-6-2-dev-guide-en)

Liferay Portal provides the ability to add portlets dynamically to portal pages
using several approaches:  

- Inside the FreeMarker template or [Velocity
  template](http://www.liferay.com/community/wiki/-/wiki/Main/Embedding+a+portlet+in+the+theme)
  of a theme with `$theme.runtime()` 
- Inside the [layout template](http://www.liferay.com/community/wiki/-/wiki/Main/Add+runtime+portlets+to+a+layout)
  with `$processor.processPortlet()` 
- Inside of a JSP with `<liferay-portlet:runtime />` 

Unfortunately, as described in
[FACES-244](http://issues.liferay.com/browse/FACES-244), dynamically adding  JSF
portlets doesn't work very well. It's actually not limited to JSF portlets --
this problem can happen with any portlet that needs to add JS/CSS resources to
the `<head>`...`</head>` section of the portal  
page. Since JSF portlets require the `jsf.js` resource to perform Ajax requests,
the `jsf.js` resource needs  to be loaded when the portal page is initially
rendered. 

There are two workarounds:

1.  For plain JSF portlets, add a `<link />` element for the `jsf.js` resource
    in the `<head>`...`</head>` section of the `portal_normal.vm` or
    `portal_normal.ftl` file in the theme. The first few lines of `jsf.js`
    prevent double-instantiation in case it gets included multiple times on a
    page. This can occur when a JSF portlet is dynamically included and another
    JSF portlet is added statically. Unfortunately this approach will not work
    for PrimeFaces, since `primefaces.js` does not prevent double-instantiation.

2.  Use an iframe:  

        <div id="${request.portlet-namespace}my_runtime_portlet">
            <script type="text/javascript">
                AUI().use('liferay-portlet-url', 'aui-resize-iframe', function(A) {
                var portletURL = Liferay.PortletURL.createRenderURL();
                portletURL.setPortletId('1_WAR_mypluginportlet');
                portletURL.setPlid(themeDisplay.getPlid());
                var html = '<iframe frameborder="0" id="${request.portlet-namespace}my_runtime_portlet_frame" src="' + portletURL.toString() + '" scrolling="no" width="100%"></iframe>';
                A.one('#${request.portlet-namespace}my_runtime_portlet').append(html);
                A.one('#${request.portlet-namespace}my_runtime_portlet_frame').plug(A.Plugin.ResizeIframe);
                });
            </script>
        </div>
    
In order to avoid the "You do not have the roles required to access this
portlet" error message, add the following to the `WEB-INF/liferay-portlet.xml`
descriptor:  

    <add-default-resource>true</add-default-resource>

Alternatively, you can be place the portlet alone on a hidden portal page and
then use a portlet URL referring to the plid of the hidden portal page. This
approach would be more appropriate for portlets that perform security-sensitive
actions. 

#### JavaScript Concerns [](id=javascript-concerns-liferay-portal-6-2-dev-guide-04-en)

When any JSF 2 portlet dynamically is added to a portal page at runtime by the
end-user, the JSF 2 standard `jsf.js` JavaScript code will not be executed
unless there is a full page refresh.  

As a workaround, Liferay Portal provides configuration parameters that allow the
developer to specify that a full page refresh is required. Specifying this
ensures that JSF 2 is properly initialized. You specify the required
`<render-weight>` and `<ajaxable>` parameter elements in the
`WEB-INF/liferay-portlet.xml` configuration file. 

    <liferay-portlet-app>
        <portlet>
            <portlet-name>my_portlet</portlet-name>
            <instanceable>false</instanceable>
            <render-weight>1</render-weight>
            <ajaxable>false</ajaxable>
        </portlet>
    </liferay-portlet-app>
    
Next we will discuss extension of Liferay Faces Bridge via Factory Wrappers.

### Extending Liferay Faces Bridge via Factory Wrappers [](id=liferay-faces-bridge-factory-wrappers-liferay-portal-6-2-dev-guide-en)

[Liferay Faces
Bridge](http://www.liferay.com/community/liferay-projects/liferay-faces/overview)
has several abstract classes that serve as a contract for defining factories: 

- [BridgeContextFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/context/BridgeContextFactory.java)
- [BridgePhaseFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/BridgePhaseFactory.java)
- [BridgeRequestScopeFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeFactory.java)
- [BridgeRequestScopeFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeFactory.java)
- [BridgeRequestScopeCacheFactory.java](https://github.com/liray/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeCacheFactory.java)
- [BridgeRequestScopeCacheFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeCacheFactory.java)
- [BridgeRequestScopeManagerFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/scope/BridgeRequestScopeManagerFactory.java)
- [BridgeWriteBehindSupportFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/application/view/BridgeWriteBehindSupportFactory.java)
- [BridgeURLFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/context/url/BridgeURLFactory.java)
- [IncongruityContextFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/context/IncongruityContextFactory.java)
- [PortletContainerFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/container/PortletContainerFactory.java)
- [PortletContainerFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/container/PortletContainerFactory.java)
- [UploadedFileFactory.java](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/model/UploadedFileFactory.java)

These factories are defined using the standard JSF `<factory-extension>` element
in `faces-config.xml`. The *default implementations* of these factories are
defined in the [bridge's
`META-INF/faces-config.xml`](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/resources/META-INF/faces-config.xml)
file. 

The bridge features an *extension mechanism* that enables developers to decorate
any of these factories in `META-INF/faces-config.xml` (inside a JAR), or
`WEB-INF/faces-config.xml` (inside a portlet WAR). The benefit of this feature
is that developers can plug in their own factory implementations that decorate
(wrap) the default implementations with a
[`FactoryWrapper`](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/FactoryWrapper.java). 

#### Wrapping the BridgeContextFactory With a Custom BridgeContext [](id=wrap-bridgecontextfactory-liferay-portal-6-2-dev-guide-en)

This *tutorial* for [Liferay Faces
Bridge](http://www.liferay.com/community/liferay-projects/liferay-faces/overview)
shows how to wrap the
[`BridgeContextFactory`](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/context/BridgeContextFactory.java)
so that it returns a custom
[`BridgeContext`](https://github.com/liferay/liferay-faces/blob/master/bridge-impl/src/main/java/com/liferay/faces/bridge/context/BridgeContext.java)
instance, overriding one of the methods to provide custom functionality.  

1.  Create a wrapper class for the `BridgeContext` that overrides the
    `getResponseNamespace()` method: 

        package com.mycompany.myproject;

        public class BridgeContextCustomImpl extends BridgeContextWrapper {

            private BridgeContext wrappedBridgeContext;

            public BridgeContextCustomImpl(BridgeContext bridgeContext) {

                this.wrappedBridgeContext = bridgeContext;
                BridgeContext.setCurrentInstance(this);
            }

            @Override
            public String getResponseNamespace() {
                // return value based on custom algorithm.
            }

            @Override
            public BridgeContext getWrapped() {
                return wrappedBridgeContext;
            }
        }

2.  Create a wrapper class for the `BridgeContextFactory`: 

        package com.mycompany.myproject;

        public class BridgeContextFactoryCustomImpl extends BridgeContextFactory {

            private BridgeContextFactory wrappedBridgeContextFactory;

            public BridgeContextFactoryCustomImpl(
                BridgeContextFactory bridgeContextFactory) {

               this.wrappedBridgeContextFactory =  bridgeContextFactory;
            }

            public BridgeContextFactory getWrapped() {
                return wrappedBridgeContextFactory;
            }

            @Override
            public BridgeContext getBridgeContext(
                BridgeConfig bridgeConfig, BridgeRequestScope bridgeRequestScope,
                PortletConfig portletConfig, PortletContext portletContext,
                PortletRequest portletRequest, PortletResponse portletResponse,
                Bridge.PortletPhase portletPhase, PortletContainer portletContainer,
                IncongruityContext incongruityContext) {

                BridgeContext wrappedBridgeContext =
                wrappedBridgeContextFactory.getBridgeContext(
                    bridgeConfig, bridgeRequestScope, portletConfig, portletContext,
                    portletRequest, portletResponse, portletPhase, portletContainer,
                    incongruityContext);

                BridgeContext bridgeContext =
                    new BridgeContextCustomImpl(wrappedBridgeContext);

                return bridgeContext;
            }
        }

3.  In the portlet's `WEB-INF/faces-config.xml`, specify the custom factory: 

        <faces-config xmlns="http://java.sun.com/xml/ns/javaee"
        xmlns:bridge="http://www.liferay.com/xml/ns/liferay-faces-bridge-2.0-extension"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="2.0"
        xsi:schemaLocation="http://java.sun.com/xml/ns/javaee 
        http://java.sun.com/xml/ns/javaee/web-facesconfig_2_0.xsd">
            <factory>
                <factory-extension>
                    <bridge:bridge-context-factory>
                        com.mycompany.myproject.BridgeContextFactoryCustomImpl
                    </bridge:bridge-context-factory>
                </factory-extension>
            </factory>
        </faces-config>

4.  Rebuild and re-deploy the portlet. 

That's all you need to do to implement and deploy a `BridgeContextFactory`
wrapper. 

Next, we'll look at several detailed aspects of Liferay Faces Bridge, such
as how it satisfies the portlet bridge specifications and some bridge
configuration options. 

## Understanding Liferay Faces Bridge [](id=liferay-faces-bridge-details-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to deploy JSF web applications as portlets
within JSR 286 (Portlet 2.0) compliant portlet containers like Liferay Portal
5.2, 6.0, 6.1, and 6.2. 

The project home page can be found at
<http://www.liferay.com/community/liferay-projects/liferay-faces/bridge>. 

To fully understand Liferay Faces Bridge, you must first understand the
portlet bridge standard. Let's look at that next. 

### Understanding the Portlet Bridge Standard [](id=portlet-bridge-standard-liferay-portal-6-2-dev-guide-en)

Because the Portlet 1.0 and JSF 1.0 specs were being created at essentially the
same time, the Expert Group (EG) for the JSF specification constructed the JSF
framework to be compliant with portlets. For example, the
[ExternalContext.getRequest()](http://download.oracle.com/javaee/6/api/javax/faces/context/ExternalContext.html)
method returns an `Object` instead of an
[javax.servlet.http.HttpServletRequest](http://download.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html).
When this method is used inside of a portal, the `Object` can be cast to a
[javax.portlet.PortletRequest](http://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/PortletRequest.html).
Despite the EG's conciousness of portlet compatibility within the design of JSF,
the gap between the portlet and JSF lifecycles had to be bridged. 

Portlet bridge standards and implementations evolved over time. 

#### Portlet Bridge Standards and Implementations [](id=portlet-bridge-standards-and-impls-liferay-portal-6-2-dev-guide-en)

##### Portlet Bridge 1.0 [](id=portlet-bridge-1-0-liferay-portal-6-2-dev-guide-en)

Starting in 2004, several different JSF portlet bridge implementations were
developed in order to provide JSF developers with the ability to deploy their
JSF web apps as portlets. In 2006, the JCP formed the Portlet Bridge 1.0 ([JSR
301](http://www.jcp.org/en/jsr/detail?id=301)) EG in order to define a standard
bridge API, as well as detailed requirements for bridge implementations. JSR 301
was released in 2010, targeting Portlet 1.0 and JSF 1.2.  

##### Portlet Bridge 2.0 [](id=portlet-bridge-2-0-liferay-portal-6-2-dev-guide-en)

When the Portlet 2.0 ([JSR 286](http://www.jcp.org/en/jsr/detail?id=286))
standard was released in 2008, it became necessary for the JCP to form the
Portlet Bridge 2.0 ([JSR 329](http://www.jcp.org/en/jsr/detail?id=329)) EG. JSR
329 was also released in 2010, targeting Portlet 2.0 and JSF 1.2.  

##### Portlet Bridge 3.0 and Liferay Faces Bridge [](id=portlet-bridge-3-0-liferay-faces-bridge-liferay-portal-6-2-dev-guide-en)

After the [JSR 314](http://www.jcp.org/en/jsr/detail?id=314) EG released JSF 2.0
in 2009 and JSF 2.1 in 2010, it became evident that a Portlet Bridge 3.0
standard would be beneficial. At the time of this writing, the JCP has not
formed such an EG. In the meantime, Liferay developed *Liferay Faces Bridge*,
which targets Portlet 2.0 and JSF 1.2/2.1/2.2.  

<!-- The "At the time of this writing ..." text should probably be reworded.
I'll need to consult Neil as to whether an EG ever formed. - Jim --> 

Liferay Faces Bridge is an implementation of the JSR 329 Portlet Bridge
Standard. It also contains innovative features that make it possible to leverage
the power of JSF 2.x inside a portlet application.  

Now that you're familiar with some of the history of the Porlet Bridge
standards, let's consider the responsibilities it requires of the portlet
bridge. 

#### Portlet Bridge Responsibilities [](id=portlet-bridge-responsibilities-liferay-portal-6-2-dev-guide-en)

A JSF portlet bridge aligns the correct phases of the JSF lifecycle with each
phase of the portlet lifecycle. For instance, if a browser sends an HTTP GET
request to a portal page with a JSF portlet in it, the `RENDER_PHASE` is
perfomed in the portlet's lifecycle. The JSF portlet bridge then initiates the
`RESTORE_VIEW` and `RENDER_RESPONSE` phases in the JSF lifecycle. Likewise, when
an HTTP POST is executed on a portlet and the portlet enters the `ACTION_PHASE`,
then the full JSF lifecycle is initiated by the bridge. 

![Figure 4.1: The different phases of the JSF Lifecycle are executed depending on which phase of the Portlet lifecycle is being executed.](../../images/04-lifecycle-bridge.png)
 
Besides ensuring that the two lifecycles connect correctly, the JSF portlet
bridge also act as a mediator between the portal URL generator and JSF
navigation rules. JSF portlet bridges ensure that URLs which are created by the
portal, comply with JSF navigation rules, so that a JSF portlet is able to
switch to different views. 

With the main aspects of JSF portlet bridges described, let's discuss the
configuration of Liferay Faces Bridge. 

### Configuring Liferay Faces Bridge [](id=configure-liferay-faces-bridge-liferay-portal-6-2-dev-guide-en)

The JSR 329 standard defines several configuration options prefixed with the
`javax.portlet.faces` namespace. Liferay Faces Bridge defines additional
implementation-specific options prefixed with the `com.liferay.faces.bridge`
namespace.  

#### Understanding the Bridge Request Scope [](id=bridge-request-scope-liferay-portal-6-2-dev-guide-04-en)

One of the key requirements in creating a JSF portlet bridge is managing JSF
request-scoped data within the Portlet lifecycle. This is normally referred to
as the *Bridge Request Scope* by JSR 329. The lifespan of the BridgeRequestScope
works like this: 

1.  `ActionRequest`/`EventRequest`: `BridgeRequestScope` begins.

2.  `RenderRequest`: `BridgeRequestScope` is preserved.

3.  Subsequent `RenderRequest`: `BridgeRequestScope` is reused.

4.  Subsequent `ActionRequest`/`EventRequest`: `BridgeRequestScope` ends, and a
    new `BridgeRequestScope` begins.

5.  If the session expires or is invalidated, then similar to the
    `PortletSession` scope, all `BridgeRequestScope` instances associated with
    the session are made available for garbage collection by the JVM.

The main use-case for having the `BridgeRequestScope` preserved in \#2 (above)
is for *re-rendering* of portlets. One example would be when two or more JSF
portlets are placed on a portal page (Portlets X and Y), and those portlets are
**not** using `f:ajax` for form submission. In such a case, if the user were to
submit a form (via full `ActionRequest` postback) in Portlet X, and then submit
a form in Portlet Y, then Portlet X should be re-rendered with its previously
submitted form data. 

With the advent of JSF 2.x and Ajax, there are four drawbacks for supporting
this use-case as the default behavior: 

-   Request-scoped data basically semi-session-scoped in nature, because the
    `BridgeRequestScope` is preserved (even though the user might NEVER click
    the Submit button again). 
-   `BridgeRequestScope` can't be stored in the `PortletSession` because the
    data is request-scoped in nature, and the data stored in the scope isn't
    guaranteed to be `Serializable` for replication. Therefore, it doesn't
    really work well in a clustered deployment. 
-   The developer might have to specify the
    `javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES` `<init-param>` in the
    `WEB-INF/web.xml` descriptor in order to tune the memory settings on the
    server. 

<!-- The first bullet does not make gramatical sense. - Jim -->

Therefore, since Liferay Faces Bridge is designed for JSF 2.x, keeping Ajax in
mind, the bridge makes the following assumptions: 

-   That developers are not primarily concerned about the *re-rendering* of
    portlets use-case mentioned above. 
-   That developers don't want any of the drawbacks mentioned above.  
-   That developers are making heavy use of the `f:ajax` tag and submitting
    forms via Ajax with their modern-day portlets. 
-   That developers want to do as little configuration as possible and don't
    want to be forced to add anything to the `WEB-INF/web.xml` descriptor. 

Consequently, the default behavior of Liferay Faces Bridge is to cause the
`BridgeRequestScope` to end at the end of the `RenderRequest`.

If you prefer the standard behavior over Liferay Faces Bridge's default
behavior, then the following options can be placed in the `WEB-INF/web.xml`
descriptor: 

    <!--
    The default value of the following context-param is false, meaning that
    Liferay Faces Bridge will cause the BridgeRequestScope to end after the
    RENDER_PHASE of the portlet lifecycle. Setting the value to true will cause
    Liferay Faces Bridge to cause the BridgeRequestScope to last until the next
    ACTION_PHASE or EVENT_PHASE of the portlet lifecycle.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.bridgeRequestScopePreserved</param-name>
        <param-value>true</param-value>
    </context-param>

    <!--
    The default value of the following context-param is 100. It defines the
    maximum number of BridgeRequestScope instances to keep in memory on the
    server if the bridgeRequestScopePreserved option is true. 
    -->
    <context-param>
        <param-name>javax.portlet.faces.MAX_MANAGED_REQUEST_SCOPES</param-name>
        <param-value>2000</param-value>
    </context-param>

Alternatively, the `com.liferay.faces.bridge.bridgeRequestScopePreserved` value
can be specified on a portlet-by-portlet basis in the `WEB-INF/portlet.xml`
descriptor. 

#### Using PreDestroy and BridgePreDestroy Annotations [](id=predestroy-bridgepredestroy-annotations-liferay-portal-6-2-dev-guide-en)

When JSF developers want to perform cleanup on managed-beans before they are
destroyed, they typically annotate a method inside the bean with the
`@PreDestroy` annotation. However, section 6.8.1 of the JSR 329 standard
discusses the need for the `@BridgePreDestroy` and
`@BridgeRequestScopeAttributeAdded`  annotations in the bridge API.  

---

 ![Note](../../images/tip.png) **Note:** For an in-depth discussion of this
 issue, please refer to <http://issues.liferay.com/browse/FACES-146>. 

---

In order to explain this requirement, it is necessary to make a distinction
between *local* portals and *remote* portals. Local portals invoke portlets that
are deployed within the same (local) portlet container. Remote portals invoke
portlets that are deployed elsewhere via WSRP (Web Services for Remote
Portlets). The `@BridgePreDestroy` and `@BridgeRequestScopeAttributeAdded`
annotations were introduced into the JSR 329 standard primarily to support WSRP
in remote portals. That being the case, the standard indicates that developers
should always use `@BridgePreDestroy` instead of `@PreDestroy`. Liferay Faces
Bridge however takes a different approach: rather than assuming the remote
portal use-case, Liferay Faces Bridge assumes the local portal use-case. When
developing with a local portal, like Liferay, Liferay Faces Bridge ensures that
the standard `@PreDestroy` annotation works as expected. This means there is no
reason to use the `@BridgeRequestScope` annotation with a local portal when
using Liferay Faces Bridge. Developers must manually configure Liferay Faces
Bridge via the `WEB-INF/web.xml` descriptor in order to leverage the
`@BridgePreDestroy` and `@BridgeRequestScopeAttributeAdded` annotations for
WSRP. 

    <!--
    The default value of the following context-param is false, meaning that
    Liferay Faces Bridge will invoke methods annotated with @PreDestroy over
    those annoated with @BridgePreDestroy. Setting the value of the following
    context-param instructs Liferay Faces Bridge to prefer the @BridgePreDestroy
    annotation over the standard @PreDestroy annotation in order to support a
    WSRP remote portal environment.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.preferPreDestroy</param-name>
        <param-value>false</param-value>
    </context-param>

    <!--
    The following listener is required to support the
    @BridgeRequestScopeAttributeAdded annotation in a WSRP remote portal
    environment. 
    -->
    <listener>
        <listener-class>com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener</listener-class>
    </listener>

Alternatively, the `com.liferay.faces.bridge.preferPreDestroy` value can be
specified on a portlet-by-portlet basis in the `WEB-INF/portlet.xml` descriptor.

#### Configurting the Portlet Container Abilities [](id=configure-portlet-container-abilities-liferay-portal-6-2-dev-guide-en)

Liferay Faces Bridge can be run in a variety of portlet containers (Liferay,
Pluto, etc.) and is aware of some of the abilities (or limitations) of these
containers. Liferay Faces Bridge enables you to configure the abilities of the
portlet container in the `WEB-INF/web.xml` descriptor. 

    <!--
    The default value of the following context-param depends on which portlet
    container the bridge is running in. The value determines whether or not the
    bridge resource handler will attempt to set the status code of downloaded
    resources to values like HttpServletResponse.SC_NOT_MODIFIED.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.containerAbleToSetHttpStatusCode</param-name>
        <param-value>true</param-value>
    </context-param>

By configuring portlet container capabilities, you can take advantage of your
portlet container's specific strengths while using Liferay Faces Bridge. 

#### Configuring Portlet Namespace Optimization [](id=configure-portlet-namespace-optimization-liferay-portal-6-2-dev-guide-en)

The JSR 329 standard requires the bridge implementation to prepend the portlet
namespace to the value of the `id` attribute of every component that is rendered
by a JSF view. This distinguishes the component when there are multiple JSF
portlets on a portal page that contain similar component hierarchies and naming.
Also, the JSR 329 standard indicates that the bridge implementation of the
`ExternalContext.encodeNamesapce(String)` method is to prepend the value of
`javax.portlet.PortletResponse.getNamespace()` to the specified String. The
problem is that since the value returned by `getNamespace()` can be a lengthy
string, the size of the rendered HTML portal page can become unnecessarily
large. This can be especially non-performant when using the `f:ajax` tag in a
Facelet view, in order to perform partial-updates the browser's DOM. 

Liferay Faces Bridge has a built-in optimization that minimizes the value
returned by the the `ExternalContext.encodeNamesapce(String)` method, while
still guaranteeing uniqueness.

If you don't want to leverage the namespace optimization and instead want to
leverage the default behavior specified by JSR 329, you must set this value to
`false` in the `WEB-INF/web.xml` descriptor:  

    <!--
    The default value of the following context-param is true, meaning that
    Liferay Faces Bridge will optimize the portlet namespace. Setting the value
    of the following context-param to false disables the optimization.
    -->
    <context-param>
        <param-name>com.liferay.faces.bridge.optimizePortletNamespace</param-name>
        <param-value>false</param-value>
    </context-param>

---

 ![Note](../../images/tip.png) **Note:** Due to strict namespacing requirements
 introduced in Liferay Portal 6.2, the namespace optimization feature only works
 in Liferay Portal 5.2, 6.0, and 6.1. 

---

#### Resolving XML Entities [](id=resolve-xml-entities-liferay-portal-6-2-dev-guide-en)

<!-- Explain why this is helpful. - Jim -->

Liferay Faces Bridge gives you the option of enabling or disabling XML
validation for all `faces-config.xml` file entities. By default, the validation
is disabled. 

To enable XML validation for all `faces-config.xml` file entities,
you can set the option to `true` in the `WEB-INF/web.xml` descriptor: 

    <!-- The default value of the following context-param is false. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.resolveXMLEntities</param-name>
        <param-value>true</param-value>
    </context-param>

#### Configuring Resource Buffer Size [](id=configure-resource-buffer-size-liferay-portal-6-2-dev-guide-04-en)

Liferay Faces Bridge provides the ability to set the size of the buffer used to
load resources into memory as the file contents are being copied to the
response. The default value of this option is `1024` (1KB). 

<!-- Is the code below from a web.xml? - Jim -->

    <!-- The default value of the following context-param is 1024. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.resourceBufferSize</param-name>
        <param-value>4096</param-value>
    </context-param>

Alternatively, you can specify the `com.liferay.faces.bridge.resourceBufferSize`
value on a portlet-by-portlet basis in the `WEB-INF/portlet.xml` descriptor. 

#### Configuring Distinct Request Scoped Managed Beans [](id=distinct-request-scoped-managed-beans-liferay-portal-6-2-dev-guide-en)

Liferay Portal provides you with the ability to specify whether or not
request attributes are shared among portlets, by specifying the
`<private-request-attributes>` option in the `WEB-INF/liferay-portlet.xml`
descriptor. The default value of this option is `true`, meaning that request
attributes are NOT shared among portlets.  

    <liferay-portlet-app>
        <portlet>

            ...

            <private-request-attributes>false</private-request-attributes>
        </portlet>

        ...

    </liferay-portlet-app>

However, this non-shared feature only works for request attributes that are
present in the request map and that have a non-null value. This can cause a
problem for JSF managed-beans in request scope. Specifically, the problem arises
when a portal page has two or more portlets that have a request scope managed
bean with the same name.

For example, if Portlet X and Portlet Y each have a
class named `BackingBean` annotated with `@RequestScoped` `@ManagedBean`. Then,
when the JSF runtime is asked to resolve an EL-expression `#{backingBean}`,
there is no guarantee that the correct instance will be resolved. In order to
solve this problem, Liferay Faces Bridge provides a configuration option, which
can be specified in `WEB-INF/web.xml`, that causes request-scoped managed beans
to be distinct for each portlet. 

    <!-- The default value of the following context-param is false. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.distinctRequestScopedManagedBeans</param-name>
        <param-value>true</param-value>
    </context-param>

To ensure that `@RequestScoped` managed beans are resolved correctly for each
portlet, set this value to `true`. 

#### Configuring View Parameters [](id=configure-view-parameters-liferay-portal-6-2-dev-guide-en)

In the case of a portlet `RenderRequest`, Section 5.2.6 of the JSR 329 Spec
requires that the bridge must ensure that only the `RESTORE_VIEW` and
`RENDER_RESPONSE` phases of the JSF lifecycle execute. In addition, Section 6.4
requires that a PhaseListener be used to skip the `APPLY_REQUEST_VALUES`,
`PROCESS_VALIDATIONS`, `UPDATE_MODEL_VALUES`, and `INVOKE_APPLICATION` phases.
These requirements are valid for JSF 1.x, but for JSF 2.x *View Parameters*, the
presence of `f:metadata` and `f:viewParam` in a Facelet view makes that the
entire JSF lifecycle be run. 

Liferay Faces Bridge enables support for View Parameters by default, but
provides a configuration option in the `WEB-INF/web.xml` descriptor, that lets
your disable the feature. 

    <!-- The default value of the following context-param is true. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.viewParametersEnabled</param-name>
        <param-value>false</param-value>
    </context-param>

If it is neccessary to utilize the JSF 1.x version of this feature, then this
parameter should be set to false. 

Now that we've discussed JSF portlet bridge standards, Liferay Faces Bridge features, and Liferay Faces Bridge
configuration, let's learn about the Liferay Faces Portal JAR.  

## Leveraging Liferay UI Components and Utilities with Liferay Faces Portal [](id=liferay-faces-portal-liferay-portal-6-2-dev-guide-en)

Liferay Faces Portal is a JAR that you can use to leverage Liferay-specific
utilities and UI components in your JSF portlets.   

The Liferay Faces Portal project home page can be found at
<http://www.liferay.com/community/liferay-projects/liferay-faces/portal>. 

### LiferayFacesContext [](id=liferay-faces-portal-liferayfacescontext-liferay-portal-6-2-dev-guide-en)

`LiferayFacesContext` is an abstract class that extends the JSF
[FacesContext](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html)
abstract class. Because of this, it supplies all the same method signatures. The
`LiferayFacesContext` implements the [delegation design
pattern](http://en.wikipedia.org/wiki/Delegation_pattern) for methods defined by
[FacesContext](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html)
by first calling 
[FacesContext.getCurrentInstance()](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html#getCurrentInstance())
and then delegating to corresponding methods. 

### Liferay Faces Portal UIComponent and Composite Component Tags [](id=liferay-faces-portal-tags-liferay-portal-6-2-dev-guide-en)

Liferay Faces Portal provides a set of Facelet UIComponent and Facelet Composite
Component tags as part of its component suite. 

Because Liferay Faces has several [active versions](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en) (targeting different versions of JSF, Liferay Portal, etc.), there are several versions of the project's View Declaration Language (VDL) documentation for these tags. The VDL documentation can be found at the following addresses: 

- The VDL documentation for Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>.
- The VDL documentation for Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>. 
- The VDL documentation for Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>. 
- The VDL documentation for Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>. 

<!-- Re-add upon release. - Jim
- The VDL documentation for Liferay Faces 3.2 can be found at <http://docs.liferay.com/faces/3.2/vdldoc/>.
- The VDL documentation for Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

Liferay Faces Portal provides the following UIComponent tags under the
`liferay-ui` and `liferay-security` tags. 

#### Liferay Faces Portal UIComponent Tags [](id=liferay-faces-portal-uicomponent-tags-liferay-portal-6-2-dev-guide-en)

<!-- Explain that we'll demonstrate how to use one of the tags as an example.
Eventually we'll add more demonstrations. - Jim -->

##### The liferay-ui:input-editor tag [](id=liferay-ui-input-editor-tag-liferay-portal-6-2-dev-guide-en)

<!-- Note, that this tag is simply an example of one of the tags from liferay-ui
library. - Jim -->

The `liferay-ui:input-editor` tag renders a text area that provides the ability
to enter rich text such as bold, italic, and underline. The renderer relies on
the [CKEditor](http://ckeditor.com/)&#8482; to provide the rich text editing
area. Since Liferay bundles the [CKEditor](http://ckeditor.com/)&#8482;
JavaScript and related images with the portal, the portlet developer does not
need to include it with  the portlet. 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <f:view xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:liferay-ui="xmlns:liferay-ui="http://liferay.com/faces/ui">

        <h:form>
            <liferay-ui:input-editor id="comments" value="#{modelManagedBean.comments}" />
        </h:form>

    </f:view>

---

![Note](../../images/tip.png) **Note:** Prior to Liferay 6.0 SP2 (6.0.12), the
rich text area was rendered as an `<iframe>`. But due to incompatibilities with
IE, the rich text area HTML markup is now  rendered "inline" with the portal
page. Liferay Faces Portal will automatically detect the version of Liferay and
will render the rich text area accordingly. However, if you using Liferay 6.0
(6.0.10) or Liferay 6.0 SP1 (6.0.11) and have received an "inline" patch from
Liferay Support, then you will need to add the following to the portlet's
`WEB-INF/web.xml` descriptor: 

    <context-param>
        <param-name>com.liferay.faces.portal.inlineInputEditor</param-name>
        <param-value>true</param-value>
    </context-param>


If using ICEfaces, then the "inline" version of `liferay-ui:input-editor` will
expose an inefficiency in the Direct2DOM&#8482; (DOM-diff) algorithm. Typing a
single character in the rich text area will cause ICEfaces to detect a DOM-diff,
causing the entire `liferay-ui:input-editor` to be replaced in the browser's DOM
with the form is submitted via Ajax. The workaround for this problem is to use
the JSF 2.x `f:ajax` component to optimize/control which parts of the JSF
component tree are DOM-diffed by ICEfaces. For example:  

    <h:panelGroup id="feedback">
        <h:messages globalOnly="true" layout="table" />
    </h:panelGroup>
    <h:panelGroup id="editor">
        <liferay-ui:input-editor value="#{modelBean.text}" />
    </h:panelGroup>
    <h:commandButton>
        <f:ajax execute="@form" render="feedback" />
    </h:commandButton>
                     
---

Next we will look at the `liferay-ui` prefixed composite component tags.

#### Liferay Faces Portal Composite Component Tags [](id=liferay-faces-portal-composite-tags-liferay-portal-6-2-dev-guide-en)

<!-- Explain that we'll demonstrate how to use several of the tags. - Jim -->

##### The liferay-ui:ice-info-data-paginator tag [](id=liferay-ui-ice-info-data-paginator-liferay-portal-6-2-dev-guide-en)

The `liferay-ui:ice-info-data-paginator` encapsulates an ICEfaces 3.1
[`ice:dataPaginator`](http://www.icefaces.org/docs/v1_8_1/tld/ice/dataPaginator.html)
tag that renders pagination information for an associated
[`ice:dataTable`](http://www.icefaces.org/docs/v1_8_1/tld/ice/dataTable.html).
The navigation information will match the internationalized Liferay
"showing-x-x-of-x-results" message. Since ICEfaces 4.0 has removed support for
ice:dataPaginator, Liferay Faces 4.x no longer includes this feature. 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <f:view xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ice="http://www.icesoft.com/icefaces/component"
        xmlns:liferay-ui="http://liferay.com/faces/ui">

        <liferay-ui:ice-info-data-paginator for="dataTable1" />
        <ice:dataTable id="dataTable1" value="#{modelManagedBean.rows}" var="row">
            ...
        </ice:dataTable>

    </f:view>
    
Next we will look at the `liferay-ui:ice-nav-data-paginator` composite component
tag. 

##### The liferay-ui:ice-nav-data-paginator tag [](id=liferay-ui-ice-nav-data-paginator-tag-liferay-portal-6-2-dev-guide-en)

The `liferay-ui:ice-info-data-paginator` encapsulates an ICEfaces 3.1
[ice:dataPaginator](http://www.icefaces.org/docs/v1_8_1/tld/ice/dataPaginator.html)
tag that renders navigation controls for an associated
[ice:dataTable](http://www.icefaces.org/docs/v1_8_1/tld/ice/dataTable.html). The
icons will match the current Liferay theme. Since ICEfaces 4.0 has removed
support for ice:dataPaginator, Liferay Faces 4.x no longer includes this
feature. 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <f:view xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ice="http://www.icesoft.com/icefaces/component"
        xmlns:liferay-ui="http://liferay.com/faces/ui">

        <liferay-ui:ice-nav-data-paginator for="dataTable1" />
        <ice:dataTable id="dataTable1" value="#{modelManagedBean.rows}" var="row">
            ...
        </ice:dataTable>

    </f:view>

Next we will look at the `liferay-ui:icon` composite component tag.

##### The liferay-ui:icon tag [](id=liferay-ui-icon-tag-liferay-portal-6-2-dev-guide-04-en)

The `liferay-ui:icon` tag encapsulates an HTML `img` tag whose `src` attribute
contains a fully qualified URL to an icon image in the current Liferay theme. 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <f:view xmlns:f="http://java.sun.com/jsf/core"
        xmlns:ice="http://www.icesoft.com/icefaces/component"
        xmlns:liferay-ui="http://liferay.com/faces/ui">

        <liferay-ui:icon alt="#{i18n['delete']}" image="delete" />

    </f:view>

Next we will look at the `liferay-security` prefixed tags.

##### The liferay-security:permissionsURL tag [](id=liferay-security-permissionsurl-tag-liferay-portal-6-2-dev-guide-en)

The `liferay-security:permissionsURL` tag renders an HTML anchor tag (hyperlink)
that the user can click on in order to see the Liferay Permissions screen for
the associated resource. 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
    <f:view xmlns:f="http://java.sun.com/jsf/core"
        xmlns:h="http://java.sun.com/jsf/html"
        xmlns:liferay-security="xmlns:liferay-ui="http://liferay.com/faces/security">

        <h:form>
            <liferay-security:permissionsURL
                modelResource="myproject.model.Book"
                modelResourceDescription="Book"
                resourcePrimKey="#{modelManagedBean.book.bookId} />
        </h:form>

    </f:view>
    
Now that we've discussed Liferay Faces Portal component tags, we will look at
Liferay theme integration with Liferay Faces Portal. 
    
### Liferay Faces Portal Liferay Theme Integration [](id=liferay-faces-portal-theme-integration-liferay-portal-6-2-dev-guide-en)

Liferay Faces Portal offers several features that help integrate JSF portlets
with the current Liferay theme. 

#### ThemeDisplay [](id=liferay-faces-portal-themedisplay-liferay-portal-6-2-dev-guide-en)

Liferay Faces Portal provides the `LiferayFacesContext.getThemeDisplay()` method
at the Java level and the `liferay.themeDisplay` EL variable for getting access
to the Liferay
[`ThemeDisplay`](http://docs.liferay.com/portal/6.2/javadocs/portal-service/com/liferay/portal/theme/ThemeDisplay.html)
object. 

#### Theme Icons [](id=liferay-faces-portal-theme-icons-liferay-portal-6-2-dev-guide-en)

Liferay Faces Portal provides the `liferay-ui:icon` Facelet composite component
tag that encapsulates an HTML `img` tag whose `src` attribute contains a fully
qualified URL to an icon image in the current Liferay theme. Additionally,
Liferay Faces Portal provides the `liferay.themeImagesURL` and
`liferay.themeImageURL` Facelet composite component tags for gaining access to
theme image icons. 

#### Validation Messages (User Feedback) [](id=liferay-faces-portal-validation-messages-liferay-portal-6-2-dev-guide-en)

Most of the standard JSF HTML component tags render themselves as HTML markup
such as `<label />`, `<input />`, `<span />`, etc. and assume the current
Liferay theme thanks to the power of CSS. However, the
[`h:messages`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/messages.html)
and
[`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
tag will not assume the current Liferay theme unless the following JSR 286
standard CSS class names are applied: 

- `portlet-msg-error`
- `portlet-msg-info`
- `portlet-msg-warn`

        <h:messages errorClass="portlet-msg-error" fatalClass="portlet-msg-error" infoClass="portlet-msg-info" warnClass="portlet-msg-warn" /> 
                
<!-- Demonstrate using the liferay-ui:message tag for these message types.  - Jim -->

As a convenience, Liferay Faces Portal provides the
[`liferay-ui:message`](http://docs.liferay.com/faces/4.2/vdldoc/liferay-ui/message.html)
Facelet composite component tag that encapsulates the [`h:message`](http://java.sun.com/javaee/javaserverfaces/1.2/docs/tlddocs/h/message.html)
tag and automatically applies the JSR 286 standard class names as shown above. 

---

 ![Note](../../images/tip.png) **Note:** When running as a portlet, the ICEfaces
 [`ice:messages`](http://www.icefaces.org/docs/latest/tld/ice/messages.html) and
 [`ice:message`](http://www.icefaces.org/docs/latest/tld/ice/message.html)
 component tags automatically apply the JSR 286 standard class names as shown
 above. Additionally the
 [`ice:dataTable`](http://www.icefaces.org/docs/latest/tld/ice/dataTable.html)
 component tag will apply the following JSR 286 standard class names for
 alternating table rows: 

 - `portlet-section-alternate`
 - `portlet-section-body`

---

Next we'll look at integrating Liferay Faces Portal's Language functionality
with JSF Portlets. 

### Liferay Faces Portal Liferay Locale [](id=liferay-faces-portal-liferay-locale-liferay-portal-6-2-dev-guide-en)

By default, the
[Locale](http://java.sun.com/javase/6/docs/api/java/util/Locale.html) that is
normally used to present internationalized JSF views is based on the
web-browser's locale settings. In order to use the portal user's language
preference, the Liferay Faces Portal project automatically registers the
`LiferayLocalePhaseListener`. This phase listener modifies the locale inside the
[UIViewRoot](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/component/UIViewRoot.html)
based on the user's language preference returned by the
[User.getLocale()](http://docs.liferay.com/portal/6.2/javadocs/portal-service/com/liferay/portal/model/User.html#getLocale())
method. 

Now that we have discussed the `LiferayFacesContext`, Liferay Faces Portal
components, Liferay Faces Portal theme integration, and Liferay Language Portlet
Integration, let's move on to the Migration Guide which explains how to migrate
projects which utilize the PortletFaces bridge to utilize the Liferay Faces
bridge. 

In the next section, we will briefly look at Liferay Faces Alloy.

## Liferay Faces Alloy [](id=liferay-faces-alloy-liferay-portal-6-2-dev-guide-04-en)

<!-- This section needs to be expanded. First, the reader needs some insight as
to what AlloyUI is and why they'd want to use it. Then, eventually, we need to
demonstrate using AlloyUI in a JSF portlet. - Jim --> 

Liferay Faces Alloy is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to utilize Alloy UI in a way that is
consistent with JSF development. 

The project home page can be found at
<http://www.liferay.com/community/liferay-projects/liferay-faces/alloy>.  

#### Liferay Faces Alloy Tags [](id=liferay-faces-alloy-tags-liferay-portal-6-2-dev-guide-04-en)

Liferay Faces Alloy provides a set of Facelet UIComponent and Facelet Composite
Component tags as part of its component suite. 

Because Liferay Faces has several [active
versions](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)
(targeting different versions of JSF, Liferay Portal, etc.), there are several
versions of the project's View Declaration Language (VDL) documentation for
these tags. The VDL documentation can be found at the following addresses: 

- The VDL documentation for the Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>.
- The VDL documentation for the Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>.
- The VDL documentation for the Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>.
- The VDL documentation for the Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>.

<!-- Re-add links to VDLs for versions 3.2 through 4.2 when they are released. - Jim
- The VDL documentation for the Liferay Faces 3.2 can be found at <http://docs.liferay.com/faces/3.2/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

## Liferay Faces Version Scheme [](id=liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)

<!-- Revisit this to elaborate. - Jim -->

[Liferay
Faces](http://www.liferay.com/community/liferay-projects/liferay-faces/overview)
follows a `Major1.Major2.Minor-Type` versioning scheme:

`Major1`: 

- `1` = Portlet 1.0 Bridge for JSF 1.2 (JSR 301) *(Note that Liferay Faces
does not support Portlet 1.0)* 
- `2` = Portlet 2.0 Bridge for JSF 1.2 (JSR 329) 
- `3` = Portlet 2.0 Bridge for JSF 2.1 (JSR TBA) 
- `4` = Portlet 2.0 Bridge for JSF 2.2 (JSR TBA) 

`Major2`: 

- `0-legacy` = Liferay `5.2`
- `0` = Liferay `6.0` 
- `1` = Liferay `6.1` 
- `2` = Liferay `6.2` 

`Minor`: 

- May contain bug fixes, improvements, and new features. 

`Type`: 

- GA (General Availability) 
- RC (Release Candidate) 
- BETA (Beta Quality) 
- ALPHA (Alpha Quality)

*Examples*:

- `3.1.0-ga1`: First GA release for JSF 2.0/2.1 for use with Liferay 6.1.x
- `3.1.1-ga2`: Second GA release for JSF 2.0/2.1 for use with Liferay 6.1.x
 
---

 ![Note](../../images/tip.png) **Note:** Some permutations of this
 versioning scheme are not supported, see table below for specific information
 on supported versions of JSF and Liferay Portal. 

---

The following table displays the Liferay Faces version and its compatible
Liferay Portal version and its compatible JSF version:

| Liferay Faces Version | JSF Version (`Major1`) | Liferay Portal Version (`Major2`) |
|-----------------------|------------------------|-----------------------------------|
| `2.1.x`               | `1.2`                  | `6.1`                             |
| `3.0.x-legacy`        | `2.1`                  | `5.2`                             |
| `3.0.x`               | `2.1`                  | `6.0`                             |
| `3.1.x`               | `2.1`                  | `6.1`                             | 
| `3.2.x`               | `2.1`                  | `6.2`                             |
| `4.1.x`               | `2.2`                  | `6.1`                             | 
| `4.2.x` (master)      | `2.2`                  | `6.2`                             |

While Liferay Faces Bridge is theoretically compatible with any portal that
implements the Portlet 2.0 standard, it has been carefully tested for use with
Liferay Portal versions 5.2, 6.0, 6.1, and 6.2 and has several optimizations
that provide increased performance within Liferay. 

## Migration Guide [](id=migrate-to-liferay-faces-liferay-portal-6-2-dev-guide-en)

<!-- Rename, "Migrating From the portletfaces.org to Liferay Faces" - Jim -->

The Liferay Faces project originates from the <http://portletfaces.org>
community website. On April 3, 2012 Liferay announced that it would be assuming
leadership for the portletfaces.org community. Consequently, projects at
portletfaces.org were repackaged under the Liferay Faces umbrella project along
with some name changes: 

- AlloyFaces &rarr; Liferay Faces Alloy 
- PorltetFaces Bridge &rarr; Liferay Faces Bridge 
- LiferayFaces &rarr; Liferay Faces Portal 

#### BridgeRequestAttributeListener [](id=migrate-to-bridgerequestattributelistener-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided a class named
`org.portletfaces.bridge.servlet.BridgeRequestAttributeListener` but Liferay
Faces Bridge uses
`com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener`. In order to
migrate to the new class, you will need to refactor to the new package
namespace, as a deprecated class has not been provided.  

<!--
Mention this is from `WEB-INF/web.xml`.
Modify code to possibly leave out the <web-app> tag.
Clarify that we're replacing the PortletFaces one with Liferay Faces Bridge one.
- Jim
-->

    <!-- PortletFaces Bridge BridgeRequestAttributeListener -->
    <web-app>
        <listener>
            ...
            <listener-class>
                org.portletfaces.bridge.servlet.BridgeRequestAttributeListener
            </listener-class>
            ...
        </listener>
    </web-app>

    <!-- Liferay Faces Bridge GenericFacesPortlet -->
    <web-app>
        <listener>
            ...
            <listener-class>
                com.liferay.faces.bridge.servlet.BridgeRequestAttributeListener
            </listener-class>
            ...
        </listener>
    </web-app>

Next, we'll look at the migration of configuration option names. 

#### Configuration Option Names [](id=liferay-faces-configuration-option-names-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided several configuration options for use within the
`WEB-INF/web.xml` and `WEB-INF/portlet.xml` descriptors. In order to ease
migration, the configuration option names have been reproduced in the Liferay
Faces project. It is recommended that the new configuration option names be
used, as shown in the following listing: 

- `org.portletfaces.bridge.containerAbleToSetHttpStatusCode` &rarr;
  `com.liferay.faces.bridge.containerAbleToSetHttpStatusCode` 

- `org.portletfaces.bridgeRequestScopePreserved` &rarr;
  `com.liferay.faces.bridge.bridgeRequestScopePreserved`

- `org.portletfaces.bridge.optimizePortletNamespace` &rarr;
  `com.liferay.faces.bridge.optimizePortletNamespace`

- `org.portletfaces.bridge.preferPreDestroy` &rarr;
  `com.liferay.faces.bridge.preferPreDestroy`

- `org.portletfaces.bridge.resolveXMLEntities` &rarr;
  `com.liferay.faces.bridge.resolveXMLEntities`

- `org.portletfaces.bridge.resourceBufferSize` &rarr;
  `com.liferay.faces.bridge.resourceBufferSize`

Next, we will see how FileUpload has changed between the PortletFaces Bridge and
the Liferay Faces Bridge. 

#### File Upload [](id=migrate-liferay-faces-file-upload-liferay-portal-6-2-dev-guide-04-en)

PortletFaces Bridge provided classes named
`org.portletfaces.bridge.component.HtmlInputFile` and
`org.portletfaces.bridge.component.UploadedFile`, but Liferay Faces Bridge uses
`com.liferay.faces.bridge.component.HtmlInputFile` and
`com.liferay.faces.bridge.component.UploadedFile`, respectively. In order to
migrate to the new classes, you will need to refactor to the new package
namespace, as deprecated classes have not been provided. 

    // PortletFaces Bridge package name:
    import org.portletfaces.bridge.component.HtmlInputFile;

    // Liferay Faces Bridge package name:
    import com.liferay.faces.bridge.component.HtmlInputFile;

    // PortletFaces Bridge package name:
    import org.portletfaces.bridge.component.UploadedFile;

    // Liferay Faces Bridge package name:
    import com.liferay.faces.bridge.component.UploadedFile;
    
Next we will look at migrating Facelet Tags to Liferay Faces.

#### Facelet Tag Library Namespaces [](id=migrate-facelet-tag-library-namespaces-liferay-portal-6-2-dev-guide-en)

The projects at portletfaces.org provided several UIComponents and Composite
Components for use within Facelet views. The tag library documentation for these
components has been migrated to VDL documentation for each version of the
Liferay Faces Bridge: 

- The VDL documentation for the Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>. 

<!-- Re-add upon release. - Jim
- The VDL documentation for the Liferay Faces 3.2 can be found at <http://docs.liferay.com/faces/3.2/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

Each link for the VDL documentation contains information about all the `aui`,
`aui-cc`, `bridge`, `liferay-ui`, `liferay-util`, and `liferay-security` tags
for that version of the Liferay Faces Bridge, so each VDL documentation link
basically holds the entirety of the PortletFaces tag library documentation (with
the exception of certain tags which are excluded from certain versions of the
Lfieray Faces Bridge). 

#### GenericFacesPortlet [](id=migrate-genericfacesportlet-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided its own
`org.portletfaces.bridge.GenericFacesPortlet` class but the Liferay Faces Bridge
uses the JSR 329 standard `javax.portlet.faces.bridge.GenericFacesPortlet`
class. In order to ease migration, the old class still exists in Liferay Faces
Bridge although it has been deprecated. It is recommended that the standard
class name be used in all `WEB-INF/portlet.xml` `portlet-class` entries. 

<!-- Leave out <portlet-app> elements. - Jim -->

    <!-- PortletFaces Bridge GenericFacesPortlet -->
    <portlet-app>
        <portlet>
            ...
            <portlet-class>
                org.portletfaces.bridge.GenericFacesPortlet
            </portlet-class>
            ...
        </portlet>
    </portlet-app>

    <!-- Liferay Faces Bridge GenericFacesPortlet -->
    <portlet-app>
        <portlet>
            ...
            <portlet-class>
                javax.portlet.faces.GenericFacesPortlet
            </portlet-class>
            ...
        </portlet>
    </portlet-app>

Next we'll look at the new version of the `LiferayFacesContext`.

#### LiferayFacesContext [](id=migrate-liferayfacescontext-liferay-portal-6-2-dev-guide-en)

PortletFaces provided a class named
`org.portletfaces.liferay.faces.context.LiferayFacesContext` class but Liferay
Faces Portal uses the `com.liferay.faces.portal.context.LiferayFacesContext`
class. In order to ease migration, the old class still exists in Liferay Faces
Portal although it has been deprecated. It is recommended that the standard
class name be used instead. 

    // LiferayFaces package name:
    import org.portletfaces.liferay.faces.context.LiferayFacesContext;

    // Liferay Faces Portal package name:
    import com.liferay.faces.portal.context.LiferayFacesContext;
    
The next section explains some of the changes in Logging between ProtletFaces
and Liferay Faces. 

#### Logging [](id=migrate-to-liferay-faces-logging-liferay-portal-6-2-dev-guide-en)

The PortletFaces-Logging project at portletletfaces.org has been moved into the
Liferay Faces Bridge codebase. In order to keep using this logging API in your
portlets, you will need to refactor to the new package namespace, as deprecated
classes have not been provided. 

    // PortletFaces-Logging package names:
    import org.portletfaces.logging.LoggerFactory;
    import org.portletfaces.logging.Logger;

    // Liferay Faces Bridge package names:
    import com.liferay.faces.util.logging.LoggerFactory;
    import com.liferay.faces.util.logging.Logger;

The last migration we will look at is Portlet Preferences.

#### Portlet Preferences [](id=migrate-liferay-faces-portlet-preferences-liferay-portal-6-2-dev-guide-en)

PortletFaces Bridge provided its own
`org.portletfaces.bridge.preference.Preference` class but Liferay Faces Bridge
uses the JSR 329 standard `javax.portlet.faces.preference.Preference` class. In
order to Migrate to the standard class, you will need to refactor to the new
package namespace as deprecated classes have not been provided. 

    // PortletFaces Bridge package name:
    import org.portletfaces.bridge.preference.Preference;

    // Liferay Faces Bridge package name:
    import javax.portlet.faces.preference.Preference;

And those are all the changes necessary to migrate projects from the
PortletFaces Bridge to the Liferay Faces Bridge. 

## Building Liferay Faces From Source [](id=building-liferay-faces-from-source-liferay-portal-6-2-dev-guide-en)

---

 ![Note](../../images/tip.png) **Note:** This procedure assumes that
 you need to use version 4.2.0-m1, but you will need to follow the [Liferay
 Faces Version
 Scheme](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en), in order to find the correct version of Liferay Faces that fits with the version of the Liferay Portal (and mojarra) that you are using. 

---

#### General Instructions [](id=general-liferay-faces-build-instructions-liferay-portal-6-2-dev-guide-en)

1.  If you want to download the code via *Git*, then clone the repository and
    checkout the branch (or tag) that you want to work with. For example, type
    the following in a terminal window for the 4.2.0-m1 tag: 

        git clone https://github.com/liferay/liferay-faces.git
        cd liferay-faces
        git checkout 4.2.0-m1

2.  If you would rather download the code as a *ZIP archive*, then: 

    -   Visit the [Liferay Faces](https://github.com/liferay/liferay-faces) page
        at GitHub. 
    -   Click on the Branch drop-down menu, then click on *Tags*, and select a
        tag version such as 4.2.0-m1.
    -   Click on the *Download Zip* button, which should download a file named
        `liferay-faces-4.2.0-m1.zip`. 
    -   Extract the `liferay-faces-4.2.0-m1.zip` archive. The resulting folder
        should be named `liferay-faces-4.2.0-m1`.  
    -   In a terminal window, type: 

            cd liferay-faces-4.2.0-m1

3.  Ensure that the Maven repository entries listed in this
    [`settings.xml`](https://github.com/liferay/liferay-faces/blob/master/settings.xml)
    file are copied into your local `$HOME/.m2/settings.xml` file. If you do not
    already have a `settings.xml` file in your maven configuration, you can use
    the given `settings.xml` file. 

4.  Build the source with Maven. 

        mvn clean package

5.  Verify that the artifacts have been built by Maven. For example, the
    following JAR artifacts should exist: 

    -   `alloy/target/liferay-faces-alloy-4.2.0-m1.jar`
    -   `bridge-api/target/liferay-faces-bridge-api-4.2.0-m1.jar`
    -   `bridge-impl/target/liferay-faces-bridge-impl-4.2.0-m1.jar`
    -   `portal/target/liferay-faces-portal-4.2.0-m1.jar`
    -   `util/target/liferay-faces-util-4.2.0-m1.jar`

That's it, you have built Liferay Faces from source. 

#### Oracle WebLogic (Optional) [](id=build-liferay-faces-with-oracle-weblogic-liferay-portal-6-2-dev-guide-en)

<!-- Reduce to bold section title? Rename to something like "Building Shared
Libraries for Deploying Portlets on  Oracle WebLogic (Optional)" deployment on
Oracle WebLogic" - Jim --> 

If you are deploying portlets to *Oracle WebLogic*, then it is necessary to
build the *Shared Libraries* from the Liferay Faces source. However, the source
will not build properly until the *WebLogic Injection Provider for Mojarra* is
manually installed into your local `$HOME/.m2/repository` folder. 

1.  Locate Oracle's out-of-the-box `jsf-2.0.war` artifact, typically located in
    `Oracle/Middleware/wlserver/common/deployable-libraries/jsf-2.0.war`. 

2.  Extract the jsf-2.0.war artifact into a temporary folder: 

        cd $MW_HOME/Oracle/Middleware/wlserver/common/deployable-libraries
        mkdir temp
        cd temp
        jar xvf ../jsf-2.0.war

3.  Manually install the WebLogic Injection Provider for Mojarra
    (`wls.jsf.di.jar`) into your local `$HOME/.m2/repository` folder. For
    example, if using Oracle WebLogic version 10.3.6.0, type: 

        mvn install:install-file -Dfile=WEB-INF/lib/wls.jsf.di.jar \
        -DgroupId=com.oracle.weblogic -DartifactId=wls.jsf.di -Dpackaging=jar \
        -DgeneratePom=true -Dversion=10.3.6.0 

4.  Build the Shared Libraries from the Liferay Faces source: 

        cd liferay-faces/support
        mvn -P weblogic clean package

5.  Verify that the Shared Libraries have been built by Maven. For example, the following WAR  artifacts should exist: 

    - `servers/weblogic/jsf-shared-library/target/jsf-shared-library-3.1.3-ga4.war` 
    - `servers/weblogic/jstl-shared-library/target/jstl-shared-library-3.1.3-ga4.war` 
    - `servers/weblogic/richfaces-shared-library/target/richfaces-shared-library-3.1.3-ga4.war` 

---

If you would like to contribute to Liferay Faces, please check out our wiki:
[Contributing to Liferay
Faces](http://www.liferay.com/community/wiki?p_p_id=36&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-2&p_p_col_count=1&_36_struts_action=%2Fwiki%2Fedit_page&_36_redirect=http%3A%2F%2Fwww.liferay.com%2Fcommunity%2Fwiki%2F-%2Fwiki%2FMain%2FBuilding%2BLiferay%2BFaces%2BFrom%2BSource&p_r_p_185834411_nodeId=1071674&p_r_p_185834411_title=wiki%2F-%2Fwiki%2FMain%2FContributing%2Bto%2BLiferay%2BFaces).

<!-- Needs transition to summary. Jim -->

## Summary [](id=liferay-faces-documentation-summary-liferay-portal-6-2-dev-guide-en)

<!-- Add summary - Jim -->

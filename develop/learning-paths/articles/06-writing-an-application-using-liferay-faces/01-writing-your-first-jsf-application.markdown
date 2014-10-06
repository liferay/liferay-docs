# Writing Your First JSF Application

Getting started with a JSF application for Liferay is very easy. This learning
path guides you step-by-step through creating and developing a JSF application.
In particular, you'll develop a guestbook application which users can use to add
messages to a page. When you're finished, the final guestbook application will
look like this: 

![Figure 1: The JSF guestbook can store and display entries for many different guestbooks.](../../images/final-jsf-guestbook.png)

You may be wondering how and why JSF is so easy to deploy to Liferay. Isn't JSF
a separate technology? JSF portlets use *Liferay Faces Bridge*, which supports
deploying JSF web applications as portlets on any JSR 286 (Portlet 2.0)
compliant portlet container, like Liferay Portal 5.2, 6.0, 6.1, and 6.2. You'll
explore more about the bridge later on, but you won't have to worry about it
when creating your JSF application, since it's configured behind the scenes. 

Ready to begin creating a JSF application for Liferay Portal? 

## Creating a JSF Portlet

Since you're configured with Liferay IDE/Developer Studio, you can jump right in
to creating a JSF application. Applications in Liferay Portal are called
*portlets*, so you'll create a JSF portlet project. 

1. Go to *File* &rarr; *New* &rarr; *Liferay JSF Portlet*. Unless you've already
   created a JSF project in your workspace, you'll receive a message stating
   that there are no suitable Liferay plugin projects available for a Liferay
   JSF Portlet. Select *Yes* to open a New Liferay Plugin Project wizard to
   create the JSF plugin project, first. The new plugin project wizard appears. 

    ![Figure 2: Liferay IDE/Developer Studio's new plugin project wizard makes it very easy to create a portlet project.](../../images/lds-new-jsf-plugin-project.png)

2. Fill in the *Project name* and *Display name* as *guestbook-jsf-portlet* and
   *Guestbook*, respectively. 

3. Leave the *Use default location* checkbox checked. By default, the default
   location is set to your current Plugins SDK. Select the *Ant
   (liferay-plugins-sdk)* option for your build type, and the appropriate
   Plugins SDK and Liferay runtime. You also have the option of choosing the
   *Maven* build type, which is also popular with JSF portlets. You can visit
   the [Maven
   tutorials](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/maven)
   to learn how to build plugins using Maven. 

    If you'd like to use Maven to build your guestbook portlet and continue
    following along in this learning path, visit the [Developing Liferay Faces Portlets with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-faces-portlets-with-maven)
    tutorial to update yourself on the key differences between using the Plugins
    SDK and using Maven. 

4. Select *Portlet* for the Plugin type. 

5. Make sure the *Include Sample Code* checkbox is checked and the *Launch New
   Portlet Wizard after project is created* checkbox is **not** checked (if
   necessary), and click *Next*. 

6. Since you'll be using the JSF portlet framework, select the *JSF 2.x* radio
   option and click *Next*. 

7. The next screen in the wizard offers available JSF UI component suites. You
   can read each UI component suite's summary and learn what each can do for
   you. For the guestbook portlet, you'll use the *Liferay Faces Alloy* UI
   component suite, so select that radio option and click *Finish*. 

    ![Figure 3: The list of component suites includes the JSF Standard suite, ICEfaces, Liferay Faces Alloy, PrimeFaces, and RichFaces.](../../images/jsf-ui-component-suite-wizard.png)

Your project can now be found in the Package Explorer on the left side of your
development environment. You've just created a blank JSF Liferay project. Projects
can be filled with portlets. Your next step is to create the portlet that will
live inside your portlet project. You'll do this in the New Liferay JSF Portlet
wizard that is launched after the project is created. 

1. Specify the Portlet class as `javax.portlet.faces.GenericFacesPortlet` (if
   necessary). Then click *Next*. 

2. Enter `guestbook-jsf` for the portlet name. The following fields are edited
   accordingly. 

3. Uncheck the *Create view files* checkbox. You'll create your own view files
   manually. 

4. Click *Finish*. 

Awesome! You just created a JSF portlet! However, the current JSF portlet is
very bare bones and needs some further development so it can serve a purpose.
Just for kicks, deploy the JSF portlet to your Liferay Portal instance to see
what it looks like. 

To deploy this portlet, simply drag the project from the Package Explorer onto
your Liferay server. 

![Figure 4: Drag and drop your project onto the Liferay server to deploy it.](../../images/deploy-jsf-portlet.png)

+$$$

**Note:** Notice in your Console that there are many *DEBUG* messages. By
default, your portlet has been configured to print everything in your console in
debug mode. If you'd like to not run your portlet in debug mode, navigate to
your portlet's `docroot/WEB-INF/faces-config.xml` file and remove the following
code: 

    <lifecycle>
        <phase-listener>com.liferay.faces.util.lifecycle.DebugPhaseListener</phase-listener>
    </lifecycle>

Throughout this learning path, debug mode will be kept on. 

$$$

You can now view the bare bones JSF guestbook by clicking the *Add* button (plus
sign) and choosing *Applications*. Then, from the Sample category drag the JSF
Guestbook onto the page. The portlet only has a default message, which comes
by default when creating a Liferay portlet through IDE/Developer Studio. 

![Figure 5: The JSF Guestbook only displays a default message--for now.](../../images/jsf-guestbook-bare.png)


+$$$

**Note:** Liferay Faces has a specific versioning scheme it follows to keep
releases concise and separate from other releases. A concise versioning scheme
also makes it easier to differentiate between a Liferay Faces version's
compatibility with other platforms. To learn more, visit the [Understanding the
Liferay Faces Version
Scheme](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
tutorial. 

$$$

Remember reading briefly about how the Liferay Faces Bridge is used to keep JSF
portlets compatible with Liferay Portal? You'll dive into the bridge and its
usage next. 

## Understanding Liferay Faces Bridge

The Liferay Faces Bridge enables you to deploy JSF web apps as portlets without
writing portlet-specific code. It also contains innovative features that make it
possible to leverage the power of JSF 2.x inside a portlet application.

Liferay Faces Bridge is a JAR that you can add as a dependency to your portlet
WAR projects, in order to deploy JSF web applications as portlets within JSR 286
(Portlet 2.0) compliant portlet containers, like Liferay Portal 5.2, 6.0, 6.1,
and 6.2. 

The Liferay Faces Bridge project home page can be found
[here](http://www.liferay.com/community/liferay-projects/liferay-faces/bridge). 

To fully understand Liferay Faces Bridge, you must first understand the portlet
bridge standard. Because the Portlet 1.0 and JSF 1.0 specs were being created at
essentially the same time, the Expert Group (EG) for the JSF specification
constructed the JSF framework to be compliant with portlets. For example, the
[ExternalContext.getRequest()](http://docs.oracle.com/javaee/6/api/javax/faces/context/ExternalContext.html#getRequest%28%29)
method returns an `Object` instead of an
[javax.servlet.http.HttpServletRequest](http://download.oracle.com/javaee/6/api/javax/servlet/http/HttpServletRequest.html).
When this method is used in a portal, the `Object` can be cast to a
[javax.portlet.PortletRequest](http://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/PortletRequest.html).
Despite the EG's consciousness of portlet compatibility within the design of
JSF, the gap between the portlet and JSF lifecycles had to be bridged. 

Portlet bridge standards and implementations evolved over time. 

Starting in 2004, several different JSF portlet bridge implementations were
developed in order to provide JSF developers with the ability to deploy their
JSF web apps as portlets. In 2006, the JCP formed the Portlet Bridge 1.0 ([JSR
301](http://www.jcp.org/en/jsr/detail?id=301)) EG in order to define a standard
bridge API, as well as detailed requirements for bridge implementations. JSR 301
was released in 2010, targeting Portlet 1.0 and JSF 1.2. 

When the Portlet 2.0 ([JSR 286](http://www.jcp.org/en/jsr/detail?id=286))
standard was released in 2008, it became necessary for the JCP to form the
Portlet Bridge 2.0 ([JSR 329](http://www.jcp.org/en/jsr/detail?id=329)) EG. JSR
329 was also released in 2010, targeting Portlet 2.0 and JSF 1.2. 

After the [JSR 314](http://www.jcp.org/en/jsr/detail?id=314) EG released JSF 2.0
in 2009 and JSF 2.1 in 2010, it became evident that a Portlet Bridge 3.0
standard would be beneficial. Currently, the JCP has not formed such an EG. In
the meantime, Liferay developed *Liferay Faces Bridge*, which targets Portlet
2.0 and JSF 1.2/2.1/2.2. 

Liferay Faces Bridge is an implementation of the JSR 329 Portlet Bridge
Standard. It also contains innovative features that make it possible to leverage
the power of JSF 2.x inside a portlet application. 

Now that you're familiar with some of the history of the Portlet Bridge
standards, you'll learn about the responsibilities required of the portlet
bridge. 

A JSF portlet bridge aligns the correct phases of the JSF lifecycle with each
phase of the portlet lifecycle. For instance, if a browser sends an HTTP GET
request to a portal page with a JSF portlet in it, the `RENDER_PHASE` is
perfomed in the portlet's lifecycle. The JSF portlet bridge then initiates the
`RESTORE_VIEW` and `RENDER_RESPONSE` phases in the JSF lifecycle. Likewise, when
an HTTP POST is executed on a portlet and the portlet enters the `ACTION_PHASE`,
then the full JSF lifecycle is initiated by the bridge. 

![Figure 6: The different phases of the JSF Lifecycle are executed depending on which phase of the Portlet lifecycle is being executed.](../../images/lifecycle-bridge.png)
 
Besides ensuring that the two lifecycles connect correctly, the JSF portlet
bridge also acts as a mediator between the portal URL generator and JSF
navigation rules. JSF portlet bridges ensure that URLs created by the portal
comply with JSF navigation rules, so that a JSF portlet is able to switch to
different views. 

The JSR 329 standard defines several configuration options prefixed with the
`javax.portlet.faces` namespace. Liferay Faces Bridge defines additional
implementation-specific options prefixed with the `com.liferay.faces.bridge`
namespace. If you're curious of ways to configure Liferay Faces Bridge using
these configuration options, you can run through provided in-depth
[tutorials](/develop/tutorials/-/knowledge_base/6-2/liferay-faces-jsf-portlets). 

With the main aspects of JSF portlet bridges described, you'll now learn how
your JSF guestbook is using a bridge. 

You may have noticed that you specified the
[`GenericFacesPortlet`](http://myfaces.apache.org/portlet-bridge/api/apidocs/javax/portlet/faces/GenericFacesPortlet.html)
class when setting up your JSF portlet. This class handles invocations to your
JSF portlet and makes the portlet relying on the bridge easier to develop by
acting as a turnkey implementation. 

So where is the bridge in our new guestbook portlet?

In your Package Explorer, navigate to the *Ivy* library and you'll discover
there are a list of JARs that are related to JSF, including the bridge JARs. 

![Figure 7: Ivy downloads the required JARs for your JSF portlet, depending on the JSF UI Component Suite you selected.](../../images/jsf-jars-package-explorer.png)

When using a Plugins SDK to build a Liferay portlet,
[Ivy](http://ant.apache.org/ivy/index.html) is used to download dependencies
needed for your intended project. When creating a JSF portlet, the bridge JARs
are automatically downloaded into the portlet project, along with several other
dependency JARs. This is done behind the scenes so you never have to worry about
it, but nonetheless, they are there. Other important JARs that are part of the
Liferay Faces project are *Liferay Faces Alloy* and *Liferay Faces Portal*. 

You'll explore what Liferay Faces Alloy can do for your JSF portlet, first. 

## Understanding Liferay Faces Alloy

If you rewind back to selecting the JSF guestbook portlet's JSF Component Suite
in Liferay IDE/Developer Studio, you may recall the option of choosing between
the *JSF standard*,
[*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf),
[*PrimeFaces*](http://primefaces.org/),
[*RichFaces*](http://richfaces.jboss.org/), and *Liferay Faces Alloy* (which was
selected for the JSF guestbook portlet). 

Liferay Faces Alloy is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to utilize AlloyUI in a way that is
consistent with JSF development. Since you selected the Liferay Faces Alloy JSF
Component Suite during your portlet's setup, the JAR is included in your
portlet project using Ivy, the same way that was described in the previous
section. 

The Liferay Faces Alloy project provides a set of UI components that are JSF
equivalents of the `aui:` JSP tag library provided by Liferay Portal. For
example, a brief list of some of the supported `aui:` tags are listed below: 

- Layout: `aui:panel`, `aui:column`, `aui:button-row`, `aui:form`,
`aui:fieldset`
- UI Components: `aui:list`, `aui:list-item`, `aui:text-box-list`,
`aui:text-box-list-item`
- Composite Components: `aui-cc:button`, `aui-cc:input`, `aui-cc:message`,
`aui-cc:messages`, `aui-cc:select`

<!-- When new Liferay Faces is released, the `aui:` and `aui-cc:` components
will be replaced by `alloy:` components. Update when this is completed. -Cody
-->

If you want to utilize Liferay's AlloyUI technology based on YUI3, you'll need
to include the Liferay Faces Alloy JAR in your JSF portlet project. For your JSF
guestbook, you have Liferay Faces Alloy preconfigured in your project, so you're
automatically able to use AUI tags. Next, you'll explore what Liferay Faces
Portal accomplishes for your JSF portlet. 

<!-- The first sentence of the above paragraph will only be true once the new
version of Liferay Faces is released (very soon). -Cody -->

## Understanding Liferay Faces Portal

Similar to Liferay Faces Bridge, *Liferay Faces Portal* is included in a JSF
portlet project when created using Liferay IDE/Developer Studio. Recall Figure 7
and notice you also have a `liferay-faces-portal-[version].jar` in your Ivy
library. 

Liferay Faces Portal is a JAR that JSF developers can add as a dependency to
their portlet WAR projects in order to utilize Liferay-specific utilities and UI
components. Some of the features included in Liferay Faces Portal are: 

- Utilities: Provides the `LiferayFacesContext` which extends the `FacesContext`
with a variety of Portlet-API and Liferay-specific convenience methods. You can
learn more about the `LiferayFacesContext` by visiting its dedicated
[tutorial](/develop/tutorials/-/knowledge_base/6-2/using-the-liferayfacescontext-with-liferay-faces-portal). 

- Facelet Composite Components: Provides a set of JSF equivalents for
`liferay-ui:captcha`, `liferay-ui:icon`, `liferay-ui:input-editor`, etc. 

<!-- The `liferay-ui` tags are being deprecated on the next release of Liferay
Faces and will be replaced by `portal:` components. Make sure to replaces the
above bullet once it is released. -Cody -->

- Expression Language: Adds a set of EL keywords such as `liferay` for getting
Liferay-specific info, and `i18n` for integration with out-of-the-box Liferay
internationalized messages. 

Now that you have the fundamentals of using JSF portlets in Liferay, you'll move
on to creating your JSF guestbook portlet. First, you'll generate a persistence
layer for the JSF guestbook using Service Builder. 

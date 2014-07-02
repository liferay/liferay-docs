# Creating a JSF Portlet Project

We want to make it easy for you to implement portlets using JSF. And Liferay
IDE, with its powerful portlet plugin wizard, provides you with a great
environment to do just that. The wizard lets you select a component suite that's
right for your project, including JSF's standard UI component suite, ICEfaces,
Liferay Faces Alloy, PrimeFaces, and RichFaces. Of course, you can use any
development environment you like for building JSF portlets, but Liferay IDE is
hard to beat. 

You'll create a JSF portlet project using Liferay IDE/Developer Studio, so you
can see just how easy it is. If you don't have it installed yet, see 
[Chapter 2](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/developing-apps-with-liferay-ide-liferay-portal-6-2-dev-guide-02-en)
of this guide. If you do have it installed, launch it. 

1.  Go to *File* &rarr; *New* &rarr; *Liferay Plugin Project*. 

2.  In the project creation wizard's first window, you'll name your project and
    specify its development and runtime environments. 

    2.1 Fill in the *Project name* and *Display name* with *my-jsf-portlet*
    and *My JSF*, respectively. 

    2.2. Leave the *Use default location* checkbox checked. By default, the
    default location is set to your current Plugins SDK. If you'd like to
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

3.  In this window, you'll select the portlet framework for your portlet and a
    UI component suite. 

    3.1 Select the *JSF 2.x* portlet framework. 

    Immediately, the wizard lists the available JSF component suites in the
    bottom section of the window. The list of component suites includes the
    JSF Standard suite, ICEfaces, Liferay Faces Alloy, PrimeFaces, and
    RichFaces. 

    3.2. Select the *PrimeFaces* UI component suite and click *Finish*. 

   <!-- Why do we pick PrimeFaces, when it's based on JQuery? Shouldn't we
    stick to JSF Standard? -Rich -->

Great! Your new JSF portlet plugin project is ready for you to develop JSF
portlets. 

![Figure 4.1: Liferay Faces supports using the most popular component suites with your JSF portlets.](../../images/jsf-select-primefaces-comp-suite.png)

<!-- As an alternative, we should explain how to download the JARs manually and
install them. - Jim -->

Let's make the portlet display a calendar. We'll replace the portlet's default
"hello world" text output with a PrimeFaces calendar component. 

Open the `view.xhtml` facelet file from the portlet project's `docroot/views`
folder and replace the element `<h:outputText
value="#{i18n['my-jsf-hello-world']}" />` with the following lines of code: 

    <h:form>
       <p:calendar></p:calendar>
    </h:form>

Your `view.xhtml` facelet should look like this:

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

## Next Steps

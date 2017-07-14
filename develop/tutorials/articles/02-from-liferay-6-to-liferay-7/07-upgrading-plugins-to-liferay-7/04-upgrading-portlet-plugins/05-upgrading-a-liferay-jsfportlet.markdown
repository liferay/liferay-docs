# Upgrading a Liferay JSF Portlet

Liferay JSF portlets are easy to upgrade and require very few changes. The ease
of upgrading JSF portlets is caused by the way JSF portlets run in @product@.
Since they leverage the
[Liferay Faces](/develop/reference/-/knowledge_base/7-0/liferay-faces) project,
your JSF portlet does not directly deal with @project@ code, but rather, it
leverages the Faces JARs that map the JSF lifecycle to the @product@ portlet
lifecycle. Because of this, the only modifications you need to make in your
legacy portlet are dependency updates. There are two ways you can find your JSF
portlet's required dependencies for @product-ver@:

- Visit [http://liferayfaces.org/](http://liferayfaces.org/). This site lets you
  generate the necessary dependencies for a JSF portlet based on a chosen
  @product@ version, JSF version, and component suite.
- Reference the [Liferay Faces Version Scheme](/develop/reference/-/knowledge_base/7-0/liferay-faces-version-scheme)
  article to find the project dependencies corresponding to the @product@
  versions.

In this tutorial, you'll see just how easy it is to upgrade a Liferay JSF
portlet by exploring the upgrade process for the sample JSF Applicant portlet.
This portlet provides fields for a job application.

![Figure 1: The JSF Applicant portlet provides a job application for users to submit.](../../../../images/jsf-applicant-6-2.png)

For reference, you can download the
[pre-upgraded portlet code]() and the
[upgrade portlet code](). This sample project uses Maven.

Follow the steps below to upgrade your Liferay JSF portlet.

1.  Open your Liferay JSF portlet's build file (e.g., `pom.xml`, `build.gradle`)
    and navigate to where the dependencies are configured.

2.  Navigate to the [http://liferayfaces.org/](http://liferayfaces.org/) site
    and generate a dependency list by choosing the environment to which you want
    to upgrade your portlet.

    ![Figure 2: The Liferay Faces site gives you options to generate many combinations of dependencies.](../../../../images/jsf-dependency-generation.png)

    Compare the generated dependencies with your portlet's dependencies and make
    any necessary updates. For the sample JSF Applicant portlet, only one
    Liferay Faces dependency requires an update:

        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>3.0.0</version>
        </dependency>

    The Liferay Faces Bridge EXT dependency should be updated to version 5.0.0
    for @product-ver@. Therefore, the updated dependency should look like this:

        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>5.0.0</version>
        </dependency>

That's it! Your Liferay JSF portlet is upgraded and deployable to @product-ver@!

You deploy a Liferay JSF portlet to @product-ver@ the same way you deploy to
Portal 6.x. When the plugin WAR file lands in the `[Liferay_Home]/deploy`
folder, @product@'s Plugin Compatibility Layer converts the WAR to a Web
Application Bundle (WAB) and installs the portlet as a WAB to @product@'s OSGi
runtime.

On deploying an upgraded portlet, the server prints messages that indicate the
following portlet status:

- WAR processing
- WAB startup
- Availability to users

Deploying a Liferay JSF portlet produces messages like these:

    13:41:43,690 INFO ... [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing com.liferay.faces.demo.jsf.applicant.portlet-1.0.war
    ...
    13:42:03,522 INFO  [fileinstall-C:/liferay-ce-portal-7.0-ga4/osgi/war][BundleStartStopLogger:35] STARTED com.liferay.faces.demo.jsf.applicant.portlet-1.0_4.1.0 [503]
    ...
    13:42:05,169 INFO  [fileinstall-C:/liferay-ce-portal-7.0-ga4/osgi/war][PortletHotDeployListener:293] 1 portlet for com.liferay.faces.demo.jsf.applicant.portlet-1.0 is available for use

The portlet is now available on @product@.

![Figure 3: The JSF Applicant portlet displayed in @product-ver@.](../../../../images/jsf-applicant-6-2.png)

You've learned how to upgrade and deploy a Liferay JSF portlet. You resolve
dependencies and deploy the portlet as you always have. It's just that easy!
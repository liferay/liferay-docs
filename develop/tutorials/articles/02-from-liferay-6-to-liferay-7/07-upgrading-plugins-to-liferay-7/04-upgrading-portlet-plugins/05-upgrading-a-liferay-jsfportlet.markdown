# Upgrading a Liferay JSF Portlet [](id=upgrading-a-liferay-jsf-portlet)

Liferay JSF portlets are easy to upgrade and require few changes. They interface
with the [Liferay Faces](/develop/reference/-/knowledge_base/7-0/liferay-faces)
project, which encapsulates @product@'s Java API and JavaScript code. Because
of this, upgrading JSF portlets to @product-ver@ requires only updating
dependencies.

There are two ways to find a JSF portlet's dependencies for @product-ver@:

-   The [http://liferayfaces.org/](http://liferayfaces.org/) home page lets you
    look up the dependencies (Gradle or Maven) by @product@ version, JSF
    version, and component suites. 
-   The [Liferay Faces Version Scheme](/develop/reference/-/knowledge_base/7-0/liferay-faces-version-scheme)
    article's tables list artifacts by @product@ version, JSF version, portlet
    version, and AlloyUI and Metal component suite version. 

In this tutorial, you'll see how easy it is to upgrade a Liferay Portal 6.2 JSF
portlet (JSF 2.2) to @product-ver@ by upgrading the sample JSF Applicant
portlet. This portlet provides a job application users can submit.

![Figure 1: The JSF Applicant portlet provides a job application for users to submit.](../../../../images/jsf-applicant-6-2.png)

For reference, you can download the
[pre-upgraded portlet code](/documents/10184/656312/jsf-applicant-portlet-6.2/b5e38053-229c-98db-a05e-ebccbcc6ce6b)
and the
[upgrade portlet code](/documents/10184/656312/jsf-applicant-portlet-7.0/10e4c0d3-51a0-4605-e7e9-5b306f9701fc).
This sample project uses Maven.

Follow these steps to upgrade your Liferay JSF portlet.

1.  Open your Liferay JSF portlet's build file (e.g., `pom.xml`, `build.gradle`)
    to where the dependencies are configured.

2.  Navigate to the [http://liferayfaces.org/](http://liferayfaces.org/) site
    and generate a dependency list by choosing the environment to which you want
    to upgrade your portlet.

    ![Figure 2: The Liferay Faces site gives you options to generate dependencies for many environments.](../../../../images/jsf-dependency-generation.png)

3.  Compare the generated dependencies with your portlet's dependencies and make
    any necessary updates. For the sample JSF Applicant portlet, only one
    Liferay Faces dependency requires updating:

        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>3.0.0</version>
        </dependency>

    The Liferay Faces Bridge EXT dependency must be updated to version 5.0.0
    for @product-ver@. The updated dependency should look like this:

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

After the portlet deployment is complete, it's available on @product@.

You've learned how to upgrade and deploy a Liferay JSF portlet. You resolved
dependencies and deployed the portlet as you always have. It's just that easy!

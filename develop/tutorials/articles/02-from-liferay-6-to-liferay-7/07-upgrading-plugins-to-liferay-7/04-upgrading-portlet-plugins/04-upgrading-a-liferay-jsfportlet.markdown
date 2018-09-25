# Upgrading a Liferay JSF Portlet [](id=upgrading-a-liferay-jsf-portlet)

Liferay JSF portlets are easy to upgrade and require few changes. They interface
with the [Liferay Faces](/develop/reference/-/knowledge_base/7-1/liferay-faces)
project, which encapsulates @product@'s Java API and JavaScript code. Because
of this, upgrading JSF portlets to @product-ver@ requires only updating
dependencies.

There are two ways to find a JSF portlet's dependencies for @product-ver@:

-   The [http://liferayfaces.org/](http://liferayfaces.org/) home page lets you
    look up the dependencies (Gradle or Maven) by @product@ version, JSF
    version, and component suites. 
-   The [Liferay Faces Version Scheme](/develop/reference/-/knowledge_base/7-1/liferay-faces-version-scheme)
    article's tables list artifacts by @product@ version, JSF version, portlet
    version, and AlloyUI and Metal component suite version. 

In this tutorial, you'll see how easy it is to upgrade a Liferay Portal 6.2 JSF
portlet (JSF 2.2) to @product-ver@ by upgrading the sample JSF Applicant
portlet. This portlet provides a job application users can submit.

![Figure 1: The JSF Applicant portlet provides a job application for users to submit.](../../../../images/jsf-applicant-6-2.png)

For reference, you can download the
[pre-upgraded portlet code](https://dev.liferay.com/documents/10184/656312/jsf-applicant-portlet-6.2/48113118-3372-636f-4b5e-c0b501108a97)
and the
[upgrade portlet code](https://dev.liferay.com/documents/10184/1608802/jsf-applicant-portlet-7.1/7e9d3296-0bf2-72a6-78e8-39f58a048081).
This sample project uses Maven.

Follow these steps to upgrade your Liferay JSF portlet.

1.  Open your Liferay JSF portlet's build file (e.g., `pom.xml`, `build.gradle`)
    to where the dependencies are configured.

2.  Navigate to the [http://liferayfaces.org/](http://liferayfaces.org/) site
    and generate a dependency list by choosing the environment to which you want
    to upgrade your portlet.

    ![Figure 2: The Liferay Faces site gives you options to generate dependencies for many environments.](../../../../images/jsf-dependency-generation.png)

3.  Compare the generated dependencies with your portlet's dependencies and make
    any necessary updates. For the sample JSF Applicant portlet, the Mojarra
    dependency and two Liferay Faces dependencies require updating:

        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.faces</artifactId>
            <version>2.2.13</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>4.0.0</version>
        </dependency>

    Update the dependencies according to the
    [http://liferayfaces.org/](http://liferayfaces.org/) dependency list. For
    example,

        <dependency>
            <groupId>org.glassfish</groupId>
            <artifactId>javax.faces</artifactId>
            <version>2.2.18</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>5.0.3</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>4.1.2</version>
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
    13:42:03,522 INFO  [fileinstall-C:/liferay-ce-portal-7.1-ga1/osgi/war][BundleStartStopLogger:35] STARTED com.liferay.faces.demo.jsf.applicant.portlet-1.0_4.1.0 [503]
    ...
    13:42:05,169 INFO  [fileinstall-C:/liferay-ce-portal-7.1-ga1/osgi/war][PortletHotDeployListener:293] 1 portlet for com.liferay.faces.demo.jsf.applicant.portlet-1.0 is available for use

After the portlet deployment is complete, it's available on @product@.

![Figure 3: You've successfully updated the JSF Applicant portlet for @product-ver@!](../../../../images/jsf-applicant-7-1.png)

You've learned how to upgrade and deploy a Liferay JSF portlet. You resolved
dependencies and deployed the portlet as you always have. It's just that easy!
# Upgrading a Liferay JSF Portlet

<div class="learn-path-step">
    <p>Upgrading Portlets<br>Step 3 of 6</p>
</div>

<!-- Dependency versions/screenshots waiting for Faces artifact release. -->

Liferay JSF portlets are easy to upgrade and require few changes. They interface
with the [Liferay Faces](/docs/reference/7-1/-/knowledge_base/r/liferay-faces)
project, which encapsulates @product@'s Java API and JavaScript code. Because
of this, upgrading JSF portlets to @product-ver@ requires only updating
dependencies.

There are two ways to find a JSF portlet's dependencies for @product-ver@:

- The [http://liferayfaces.org/](http://liferayfaces.org/) home page lets you
  look up the dependencies (Gradle or Maven) by @product@ version, JSF version,
  and component suites. 
- The
  [Liferay Faces Version Scheme](/docs/reference/7-1/-/knowledge_base/r/liferay-faces-version-scheme)
  article's tables list artifacts by @product@ version, JSF version, portlet
  version, and AlloyUI and Metal component suite version. 

In this tutorial, you'll upgrade a Liferay Portal 6.2 JSF portlet's (JSF 2.2)
dependencies to @product-ver@.

1.  Open your Liferay JSF portlet's build file (e.g., `pom.xml`, `build.gradle`)
    to where the dependencies are configured.

2.  Navigate to the [http://liferayfaces.org/](http://liferayfaces.org/) site
    and generate a dependency list by choosing the environment to which you want
    to upgrade your portlet.

    <!--[Figure 2: The Liferay Faces site gives you options to generate dependencies for many environments.](../../../../images/jsf-dependency-generation.png)-->

3.  Compare the generated dependencies with your portlet's dependencies and make
    any necessary updates. For example, in the sample dependencies listed below,
    the Mojarra dependency and two Liferay Faces dependencies require updating:

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

    Using the [http://liferayfaces.org/](http://liferayfaces.org/) dependency
    list as a guide, these dependencies would be updated to

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

Once your Liferay JSF portlet's dependencies are updated, it's deployable to
@product-ver@! Follow the
[Deploying a Project](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project)
article for deployment help.

When the portlet WAR is deployed, @product@'s Plugin Compatibility Layer
converts the WAR to a Web Application Bundle (WAB) and installs the portlet as a
WAB to @product@'s OSGi runtime. The server prints messages that indicate the
following portlet status:

- WAR processing
- WAB startup
- Availability to users

Deploying a Liferay JSF portlet produces messages like these:

```bash
13:41:43,690 INFO ... [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:252] Processing com.liferay.faces.demo.jsf.applicant.portlet-1.0.war
...
13:42:03,522 INFO  [fileinstall-C:/liferay-ce-portal-7.2-ga1/osgi/war][BundleStartStopLogger:35] STARTED com.liferay.faces.demo.jsf.applicant.portlet-1.0_4.1.0 [503]
...
13:42:05,169 INFO  [fileinstall-C:/liferay-ce-portal-7.2-ga1/osgi/war][PortletHotDeployListener:293] 1 portlet for com.liferay.faces.demo.jsf.applicant.portlet-1.0 is available for use
```

After the portlet deployment is complete, it's available on @product@.

You've learned how to upgrade and deploy a Liferay JSF portlet. You resolved
dependencies and deployed the portlet as you always have. It's just that easy!

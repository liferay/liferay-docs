# Third Party Packages Portal Exports [](id=third-party-packages-portal-exports)

@product@'s `com.liferay.portal.bootstrap` module exports many third party Java
packages. If your WAR's Gradle file, for example, uses the `compile` scope for a
dependency that Liferay's OSGi runtime already provides, the dependency JAR will
be included in the WAR's `WEB-INF/lib` and deployed in the resulting WAB, and
two versions of dependency classes will be on the classpath. This can cause
weird errors that are often hard to debug. 

The following files list the packages `com.liferay.portal.bootstrap` exports:

-   `modules/core/portal-bootstrap/system.packages.extra.bnd` file in the
    [GitHub repository](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/core/portal-bootstrap/system.packages.extra.bnd).
    It lists exported packages on separate lines, making them easy to read.
-   `META-INF/system.packages.extra.mf` file in
    `[LIFERAY_HOME]/osgi/core/com.liferay.portal.bootstrap.jar`. The file is
    available in @product@ bundles. It lists exported packages in a paragraph
    wrapped at 70 columns--they're harder to read here than in the
    `system.packages.extra.bnd` file. 

These packages are installed and available in Liferay's OSGi runtime. If your
module or WAR uses one of them, specify the corresponding dependency as being
"provided" (provided by @product@). Here's how to specify a provided dependency. 

Maven: `<scope>provided</scope>`
 
Gradle: `providedCompile`

Now you can safely leverage third party packages @product@ provides! 

## Related topics [](id=related-topics)

[Resolving a Plugin's Dependencies](/develop/reference/-/knowledge_base/7-0/resolving-a-plugins-dependencies)

[Configuring Dependencies](/develop/reference/-/knowledge_base/7-0/configuring-dependencies)

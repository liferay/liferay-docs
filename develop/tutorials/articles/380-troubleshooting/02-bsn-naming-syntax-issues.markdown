# Resolving Bundle-SymbolicName Syntax Issues [](id=resolving-bundle-symbolicname-syntax-issues)

Liferay's OSGi Runtime framework sometimes throws an
`IllegalContextNameException`. Often, this is because an OSGi bundle's
`Bundle-SymbolicName` manifest header has a space in it.

The `Bundle-SymbolicName` uniquely identifies the bundle---along with the
`Bundle-Version` manifest header---and cannot contain spaces. To follow naming
best practices, use a reverse-domain name in your `Bundle-SymbolicName`. For
example, a module with the domain `troubleshooting.liferay.com` would be
reversed to `com.liferay.troubleshooting.`.

There are three ways to specify a bundles `Bundle-SymbolicName`:

1.  `Bundle-SymbolicName` header in a bundle's `bnd.bnd` file.

2.  `Bundle-SymbolicName` header in a plugin WAR's
    `liferay-plugin-package.properties` file.

3.  Plugin WAR file name, if the WAR's `liferay-plugin-package.properties` has
    no `Bundle-SymbolicName` header.

For plugin WARs, specifying the `Bundle-SymbolicName` in the
`liferay-plugin-package.properties` file is preferred. 

For example, if you deploy a plugin WAR that has no `Bundle-SymbolicName` header
in its `liferay-plugin-package.properties`, the
[WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)
uses the WAR's name as the WAB's `Bundle-SymbolicName`. If the WAR's name has a
space in it (e.g., `space-program-theme v1.war`) an
`IllegalContextNameException` occurs on deployment.

    org.apache.catalina.core.ApplicationContext.log The context name 'space-program-theme v1' does not follow Bundle-SymbolicName syntax.
    org.eclipse.equinox.http.servlet.internal.error.IllegalContextNameException: The context name 'space-program-theme v1' does not follow Bundle-SymbolicName syntax.

However you set your a `Bundle-SymbolicName`, refrain from using spaces.

## Related Topics [](id=related-topics)

[Using the WAB Generator](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)

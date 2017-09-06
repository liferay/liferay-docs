# Resolving Bundle-SymbolicName Syntax Issues [](id=resolving-bundle-symbolicname-syntax-issues)

An OSGi bundle's `Bundle-SymbolicName` and  `Bundle-Version` manifest headers
uniquely identify it. You can specify a bundle's `Bundle-SymbolicName` in these
ways:

1.  `Bundle-SymbolicName` header in a bundle's (module's) `bnd.bnd` file.

2.  `Bundle-SymbolicName` header in a plugin WAR's
    `liferay-plugin-package.properties` file.

3.  Plugin WAR file name, if the WAR's `liferay-plugin-package.properties` has
    no `Bundle-SymbolicName` header.

For plugin WARs, specifying the `Bundle-SymbolicName` in the
`liferay-plugin-package.properties` file is preferred. 

**Important**: `Bundle-SymbolicName` values must not contain spaces. On bundle
deployment, Liferay's OSGi Runtime framework throws an
`IllegalContextNameException`  if its `Bundle-SymbolicName` has a space.

For example, if you deploy a plugin WAR that has no `Bundle-SymbolicName` header
in its `liferay-plugin-package.properties`, the
[WAB Generator](/develop/reference/-/knowledge_base/7-0/using-the-wab-generator)
uses the WAR's name as the WAB's `Bundle-SymbolicName`. If the WAR's name has a
space in it (e.g., `space-program-theme v1.war`) an
`IllegalContextNameException` occurs on deployment.

    org.apache.catalina.core.ApplicationContext.log The context name 'space-program-theme v1' does not follow Bundle-SymbolicName syntax.
    org.eclipse.equinox.http.servlet.internal.error.IllegalContextNameException: The context name 'space-program-theme v1' does not follow Bundle-SymbolicName syntax.

To avoid using spaces and to follow naming best practices, you can use a
reverse-domain name in your `Bundle-SymbolicName`.

Here's an example domain name and reverse domain name:

**Module domain**: `troubleshooting.liferay.com`

**Module reverse-domain**: `com.liferay.troubleshooting`

However you set your a `Bundle-SymbolicName`, refrain from using spaces.

## Related Topics [](id=related-topics)

[Using the WAB Generator](/develop/reference/-/knowledge_base/7-0/using-the-wab-generator)

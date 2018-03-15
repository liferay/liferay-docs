# Resolving Common Output Errors Reported by the resolve Task

The `resolve` Gradle task is provided by a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
to
[validates modules](/develop/tutorials/-/knowledge_base/7-1/resolving-your-modules)
This is very useful for finding issues and reporting them as output before
deployment. For more information on running this task from Liferay Workspace,
see the
[Validating Modules Against the Target Platform](/develop/tutorials/-/knowledge_base/7-1/validating-modules-against-the-target-platform)
tutorial section. For general help with OSGi related issues, visit the
[Troubleshooting FAQ](/develop/tutorials/-/knowledge_base/7-1/troubleshooting)
tutorial section.

For help interpreting the `resolve` task's output, see the list below for common
output errors, what they mean, and how to fix them.

## Missing Package Error

This type of error is thrown when your module is referring to a package that is
not available. For example, suppose you have a module `test-service` that
depends on the `com.google.common.base` package. If that package is not
available to reference, the following error is thrown:

    Resolution exception in project 'modules:test-service': Unresolved requirements in root project 'modules:test-service':
        Mandatory:
            [osgi.wiring.package ] com.google.common.base; version=[23.0.0,24.0.0)
            [osgi.identity       ] test.service

To fix this, you must provide the missing third-party package to your module.
See the
[Depending on Third Party Libraries Not Included in @product@](/develop/tutorials/-/knowledge_base/7-1/depending-on-third-party-libraries-not-included-in-product)
section to learn how to do this.

## Missing Service Reference

If your module is referencing a service that does not exist, an error is thrown.
This is very helpful because service reference issues are hard to diagnose
during deployment without diving into the
[Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).

For example, if your module `test-portlet` is referencing a service (e.g.,
`test.api.TestApi`) it does not have access to, the following error is thrown:

    Resolution exception in project 'modules:test-portlet': Unresolved requirements in project 'modules:test-portlet':
        Mandatory:
            [osgi.identity ] test.portlet
            [osgi.service  ] objectClass=test.api.TestApi

To fix this, you must make the service available to your module. If the service
is provided by a customized @product@ core feature or an external @product@
feature that is not included in the default distro JAR, you should
[generate a new custom distro JAR](/develop/reference/-/knowledge_base/7-1/modifying-the-target-platforms-capabilities#depending-on-a-customized-distrobution-of-product)
so the service capability is available to reference. If it's a service provided
by a custom module, you should make that service available to your module.

## Missing Fragment or Incorrect Fragment Version

Referring to a missing fragment or fragment version that does not exist throws
an error. For example, if your `test.login` depends on a fragment named
`com.liferay.login.web` that cannot be referenced, the following error is
thrown:

    Resolution exception in project 'modules:test.login': Unresolved requirements in project 'modules:test-login':
        Mandatory:
            [osgi.identity    ] test.login
            [osgi.wiring.host ] com.liferay.login.web; version=1.0.10

Referencing a fragment in your module is typically done with `Fragment-Host`
header in the `bnd.bnd` file:

    Fragment-Host: com.liferay.login.web;bundle-version="[1.0.0,1.0.1)"

For more information on fragments, see the
[JSP Overrides Using OSGi Fragments](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps)
tutorial.
## Using the Felix Web Console [](id=using-the-felix-web-console)

An alternative way of interacting with Liferay's module framework is via the
Felix Web Console. To access Liferay's Felix Web Console if you're running
Liferay locally, visit
[http://localhost:8080/o/system/console/bundles](http://localhost:8080/o/system/console/bundles).

By default, you'll see a list of all the bundles installed in Liferay's module
framework. You can expand each bundle entry by clicking on the arrow next to the
bundle name to view details about each bundle.

These details include the following information about each bundle (this is not
an exhaustive list):

- its symbolic name
- its version 
- its location on the machine running Liferay
- the date/time it was last modified
- imported and exported packages and their versions
- used and provided services and their IDs

This information can be very useful when developing Liferay bundles. For
example, suppose you get an error like this during development:

    org.osgi.framework.BundleException: Could not resolve module: your.bundle.symbolic.name [230] Unresolved requirement: Import-Package: com.liferay.bookmarks.service; version="[1.0.0,2.0.0)" [Sanitized]

This error means that your bundle could not import a required package. Your
bundle requires a version of the `com.liferay.bookmarks.service` package with a
version greater than or equal to `1.0.0` but less than `2.0.0`. But no bundle
registered in Liferay's module framework exports such a package.

If you know of a bundle that exports the package you require, you can use the
Felix Web Console to check the version of the package that's exported. For
example, the `com.liferay.bookmarks.api` bundle exports the
`com.liferay.bookmarks.service` package. So you could expand the bundle entry
for the Liferay Bookmarks API bundle and check the list of exported packages for
the `com.liferay.bookmarks.service` package and find the version of the package
that's exported. Then you could update your bundle's dependency on the package
to use the correct version of the exported package.

With the Felix Web Console, you can not only view all the installed bundles
in Liferay's module framework but also all the registered services. To view
the services registered on your locally running Liferay instance, visit
[http://localhost:8080/o/system/console](http://localhost:8080/o/system/console).
For each service, you can see the service ID, the service type, and the bundle
providing the service. The service type is the name of the interface the service
implements and under which it's registered. (Services can implement and register
themselves under multiple interfaces.)

You can expand each registered service by clicking on the arrow next to the
service name to view details about each service. These details include the
following information about each service (this is not an exhaustive list):

- The name of the service bean
- The symbolic name of the bundle providing the service
- The version of the bundle providing the service
- The bundles using the service (if any)
- The JSON web service context name (if applicable)
- The JSON web service context path (if applicable)

This information can be very useful when developing Liferay bundles that consume
or provide services.

Liferay's Felix Web Console allows you to manage more than just bundles and
services (although these management tools are probably its most important
features). Explore the Web Console's navigation menu to find additional tools
such as Configuration, Log Service, Licenses, and System Information.

<!-- This was great, Jesse! I think, however, that some screenshots are in
order for the web console, to illustrate what you mention in the text. -Rich -->


# Overriding Liferay's Services

Liferay Portal and its core portlets publish lots of services (registered in
Liferay's module framework as Declarative Services) that you can invoke from
local and remote clients. You can [publish your own services to Liferay's module
framework](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services),
[invoke Liferay's services from your
application](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services),
or modify the behavior of Liferay's services. This tutorial teaches you how to
create a bundle that modifies Liferay's services.

All the functionality provided by Liferay and its portlets is enclosed in a
layer of services that are accessed by the controller layer in its portlets;
this architecture lets you change how a Liferay core portlet behaves without
changing the portlet itself. You might be tempted to extend a service interface
directly. If you are, punch yourself in the face right now. If a fix pack
modifies the interface, your implementation may not account for the changes. As
a result, the patch could break your customization bundle. That's why you should
never extend the service interface directly, but instead extend its wrapper
class. 

Liferay generates dummy wrapper classes for all its service interfaces. For
example, `UserLocalServiceWrapper` is created as a wrapper for
`UserLocalService`, a service interface for adding, removing, and retrieving
user accounts. If you Extend the wrapper class, then alter the service's
behavior, the customization won't break, despite any future patches to the
interface. 

This tutorial shows you how to modify a portal service by creating a bundle
that publishes a `ServiceWrapper` service. 

Steps:

1. Create a project (either a 'service' BLADE project or use the Plguins SDK)
2. Write a JAVA class that publishes a new service: create a class that extends
the service wrapper class, override the methods you want to modify, and
instruct Liferay to use your service class to override those of the default
class
-@Component annotation with correct properties
-Business logic, and can call `super.liferaysMethod()` to leverage the existing logic.
3. Manage dependencies and build configuration (Gradle and bnd.bnd)
4. Build, deploy, test

<!-- Test with both SDK and BLADE approaches -->
<!-- The 6.2 tutorial on hooks linked to the api docs to show what classes there
are -->

## Create a project

For a more detailed description and exposition of the process, refer to the
tutorial on [Creating a Simple
Bundle](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle).
Here's the brief version:

1. Navigate to your Plugins SDK’s `portlets` folder in a terminal or
command prompt. Run the `create.[sh|bat]` script to create a new portlet project
by entering this command:

        ./create.sh service-override "Serivce Override Bundle"

    This creates a project named service-override-portlet.

2. Delete the `-portlet` suffix.

3. Delete your project’s `docroot` folder.

4. Create a new file in your project called bnd.bnd. Add the following
    contents to this file:
<!-- Modify the bnd.bnd to suit our needs -->
    Bundle-Name: Service Override Bundle
    Bundle-SymbolicName: com.liferay.docs.serviceoverride
    Bundle-Version: 1.0.0
    Private-Package: com.liferay.docs.serviceoverride

<!--Bundle-Activator:
com.liferay.docs.serviceoverride.ServiceOverrideActivator 
-->

    Edit the entries to match your project’s name and package structure.  This
    information is used to generate the `MANIFEST.MF` file that the OSGi
    specification requires of bundles.

5. Edit your project’s `ivy.xml` file. Remove the `-portlet` suffix from the
line in which it appears. Replace the default dependencies with this one:

        <dependency name="org.osgi.core" org="org.osgi" rev="5.0.0" />

6. Edit your project’s `build.xml` file. Remove the `-portlet` suffix from the project name. Then replace this import declaration

        <import file="../build-common-portlet.xml" />

    with this one:

        <import file="../../build-common-osgi-plugin.xml" />

7. Run the following command to download the Ivy dependencies required to build your bundle:

    ant clean

Now you're ready to import your project into Eclipse, or your favorite IDE. In
Eclipse, click *File* &rarr; *New* &rarr; *Other* &rarr; *Java Project*.
Uncheck the *Use default location* box and click *Browse*. Navigate to your
project in your Plugins SDK and select it. Click *Finish*. Eclipse generates
`.classpath` and `.project` files based on the contents of your project.

At this point, you have a project, but no Java code yet. Since that's the
fun part, get to it. 

First, create a `src` folder in the root of your project. If you're using
Eclipse, add this folder to your build path.

Next, create a package in the `src` folder (`com.liferay.docs.serviceoverride`).

Create a class that extends a service's `-ServiceWrapper`. Here's an example
class set up to override the methods in `UserLocalService`:

    package com.liferay.docs.serviceoverride;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.ServiceWrapper;
    import com.liferay.portal.service.UserLocalServiceWrapper;

    import java.util.Map;

    import org.osgi.service.component.annotations.Component;
    @Component(service = ServiceWrapper.class)
    public class UserLocalServiceOverride extends UserLocalServiceWrapper {

        public UserLocalServiceOverride() {
            super(null);
        }

        @Override
        public int authenticateByEmailAddress(long companyId, String emailAddress,
                String password, Map<String, String[]> headerMap,
                Map<String, String[]> parameterMap, Map<String, Object> resultsMap)
            throws PortalException {

            System.out.println(
                "Authenticating user by email address " + emailAddress);
            return super.authenticateByEmailAddress(companyId, emailAddress, password,
                headerMap, parameterMap, resultsMap);
        }

    }

There's nothing complicated about it. You're publishing a `ServiceWrapper` type
service, declared in the `@Component` annotation. As mentioned previously,
always extend the service interface wrapper as opposed to the service interface
itself. This particular implementation demonstrates overriding the
`authenticateByEmailAddress()` method. 

<!-- I'm creating blade.tools templates for ServiceWrapper projects. Comlpete this section once merged into blade.tools master
## Creating the Project with Blade Tools

If you're not sure what Blade Tools is, see the tutorial on [creating bundles with Blade Tools]() and the tutorial on [installing Blade Tools](). While the Blade Tools project doesn't have a fully-baked template for overriding services, you can generate most of what you need 

-->

## Related Topics 

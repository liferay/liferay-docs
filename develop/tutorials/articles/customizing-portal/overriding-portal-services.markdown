# Overriding Liferay's Services

All of Liferay's functionality, including the functionality of its core
portlets, is published in a layer of services. By modifying these services, you
can modify Liferay's functionality without modifying the core, or core portlets,
themselves. With Liferay 7, these services are declared as API providers, as
evidenced by the `@ProviderType` annotation used by the service interfaces. To
modify Liferay's services, you might be tempted to simply implement one of these
service interfaces. Don't do it! Instead of implementing the service interfaces
directly, extend the `-ServiceWrapper` class for the interface you want to
modify. If a patch to an interface is made, Liferay engineers will be
responsible for updating all of the necessary API providers, and your bundle, if
it extends a wrapper, will be unaffected by the change. However, if you
implement the interface directly, a patch to the API likely means your
customization bundle will also need to be updated, or it will break.

You can [publish your own services to Liferay's module framework](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services),
[invoke Liferay's services from your application](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services),
or modify the behavior of Liferay's services. This tutorial teaches you how to
create a bundle that modifies Liferay's services.

Liferay generates dummy wrapper classes for all its service interfaces. For
example, `UserLocalServiceWrapper` is created as a wrapper for
`UserLocalService`, a service interface for adding, removing, and retrieving
user accounts. If you extend the wrapper class, then alter the service's
behavior, the customization won't break, despite any future patches to the
interface. 

This tutorial shows you how to modify a portal service by creating a bundle
that publishes a `ServiceWrapper` service. 

1. Create and configure a project.
2. Write a Java class that publishes a service wrapper.
3. Build and deploy your module.

Start by creating a project that can be deployed to Liferay's module framework.

<!-- Test with both SDK and BLADE approaches -->
<!-- The 6.2 tutorial on hooks linked to the api docs to show what classes there
are; add link when available -->

## Creating and Configuring a Project

Before writing any code, you need to have a bundle configured. For a more
detailed description of the process, refer to the tutorial on [Creating a Simple Bundle](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle).
Here's the brief version:

1. Navigate to your Plugins SDK’s `portlets` folder in a terminal or
command prompt. Run the `create.[sh|bat]` script to create a new portlet project
by entering this command:

        ./create.sh service-override-bundle "Serivce Override Bundle"

    This creates a project named service-override-portlet. Modify to suit yoyour
    needs.

2. Delete the `-portlet` suffix.

3. Delete the project’s `docroot` folder.

4. Create a new file in the root of the project called `bnd.bnd`. Add the
    following contents to it:

        Bundle-Name: Service Override Bundle
        Bundle-SymbolicName: com.liferay.docs.serviceoverride
        Bundle-Version: 1.0.0
        Private-Package: com.liferay.docs.serviceoverride

    Edit the entries to match your project’s name and package structure.  This
    information is used to generate the `MANIFEST.MF` file that the OSGi
    specification requires of bundles.

5. Edit your project’s `ivy.xml` file. Remove the `-portlet` suffix from the
`<info module=...` line. Replace the default dependencies with this one:

        <dependency name="org.osgi.core" org="org.osgi" rev="5.0.0" />

6. Edit your project’s `build.xml` file. Remove the `-portlet` suffix from the
project name, then replace this import declaration

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

At this point, you have a project that can be deployed to Liferay, but no Java
code to make it worthwhile.

## Creating a Java Class to Override Liferay's Services

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
service (`service=ServiceWrapper.class`), declared inside the `@Component`
annotation. As mentioned previously, you should always extend the service
interface wrapper as opposed to implementing the service interface itself. Other
than that, simply create a class constructor and begin overriding the methods of
the service. This particular example demonstrates overriding the
`authenticateByEmailAddress()` method, but leverages the existing functionality
of the method by calling `return super.authenticateByEmailAddress()` at the end
of the method. 

## Building and Deploying the Project

To build and deploy the project, make sure that you've configured your Plugins
SDK with the location of your Liferay instance. You need a
`build.[username].properties` file in your Plugins SDK with the following
contents:

    app.server.parent.dir=[Liferay Home]

Replace `[Liferay Home]` with the path to your Liferay bundle.

Deploy the bundle by opening a terminal or command prompt, navigating to the
project's root folder, and running

    ant clean deploy

Liferay's log displays a message like this:

    Module for /home/russell/Documents/code/bundles/master/deploy/com.liferay.docs.serviceoverride.jar copied successfully. Deployment will start in a few seconds.

Use the Felix Gogo shell to verify that your bundle was installed into Liferay's
module framework. If you're running Liferay locally, use

    telnet localhost 11311

to enter Felix Gogo shell. Then, at the prompt, enter `lb`, which stands for
*list bundles*.

    328|Active     |    1|Service Override Bundle (1.0.0)

The above output means the bundle is active.

Since the bundle created by this tutorial modified the
`authenticateByEmailAddress()` method, test its functionality by logging into
the portal. Check Liferay's log and you'll see

    Authenticating user by email address test@liferay.com

Following the steps from this tutorial, you can modify Liferay's behavior quite
extensively. If one of Liferay's portlets doesn't suit your needs at all,
however, and you think you have a better option that can be used inside Liferay
(you want to install and use a different app for blogging, perhaps), check out
our tutorial on [providing portlets to manage requests](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests).

<!-- I'm creating blade.tools templates for ServiceWrapper projects. I'll complete this section once merged into blade.tools master
## Creating the Project with Blade Tools

If you're not sure what Blade Tools is, see the tutorial on [creating bundles with Blade Tools]() and the tutorial on [installing Blade Tools](). While the Blade Tools project doesn't have a fully-baked template for overriding services, you can generate most of what you need 

-->

## Related Topics 

[Publishing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services),

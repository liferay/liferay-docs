# Overriding Liferay's Services

All of Liferay's functionality, including the functionality of its core
portlets, is published in a layer of services. By modifying these services, you
can modify Liferay's functionality without modifying the core, or core portlets,
themselves. With Liferay 7, these services are declared as API providers, as
evidenced by the `@ProviderType` annotation used by the service interfaces.
Liferay generates dummy wrapper classes for all its service interfaces. For
example, `UserLocalServiceWrapper` is created as a wrapper for
`UserLocalService`, a service interface for adding, removing, and retrieving
user accounts.

Instead of implementing the service interfaces directly, extend the
`-ServiceWrapper` class for the interface you want to modify. The reason why
becomes clear if you consider what happens when a patch to the interface is
made. If you implemented the interface, you'd need to update your module to
reflect the changes made by the patch. However, extending the wrapper class
means you only interact with the methods you're overriding, and you don't need
to know anything about the rest of the service. The necessary API providers will
be updated with the patch, and your bundle (if it extends a service wrapper),
will be unaffected by the change. 

You can [publish your own services to Liferay's module framework](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services),
[invoke Liferay's services from your application](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services),
or modify the behavior of Liferay's services. This tutorial teaches you how to
create a module that modifies Liferay's services. The process can be divided
into three steps:

1. Create and configure a module.
2. Write a Java class that publishes a service wrapper.
3. Build and deploy your module.

Start by creating a project that can be deployed to Liferay's module framework.

<!-- The 6.2 tutorial on hooks linked to the api docs to show what classes there
are; add link when available -->

## Creating and Configuring a Project

Before writing any code, you need to have a bundle configured. For a more
detailed description of the process, refer to the tutorial on [Creating a Simple Bundle](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle).

<!-- Jesse is currently reworking the Creating a Simple Bundle Tutorial, so the above link might need to be updated in the near future -->

Blade Tools contains a template for the exact type of module that's needed for
overriding one of Liferay's service interfaces, so once you've [installed Blade
Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-tools), creating
a module to override Liferay's services is trivial. Just enter a `blade create`
command that specifies the proper options, remembering specifically to specify
the fully qualified class name for the Liferay service you want to override:

    blade create -p servicewrapper -c UserLocalServiceOverride -d directory/for/project/ com.liferay.docs.serviceoverride com.liferay.service.UserLocalServiceWrapper  

The above command creates a Gradle module of type `servicewwrapper`. The new
module is created in your local directory `directory/for/project` with a project
name and Java package called `com.liferay.docs.serviceoverride` (the package is
located in the `src/main` directory of the project's root). There's even a
`UserLocalServiceOverride` class that's automatically created in the package.
This particular example overrides Liferay's `UserLocalService` interface, but it
does so by extending the `UserLocalServiceWrapper` class, as discussed above.

## Creating a Java Class to Override Liferay's Services

Your module is ready for development. Open the Java class and you'll see that
it's already set up according to the arguments and options provided in your
`blade create` command:

    package com.liferay.docs.serviceoverride;

    import com.liferay.service.UserLocalServiceWrapper;
    import com.liferay.portal.service.ServiceWrapper;
    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true,
        property = {
        },
        service = ServiceWrapper.class
    )
    public class UserLocalServiceOverride extends UserLocalServiceWrapper {

            public UserLocalServiceOverride() {
                super(null);
            }

    }

There's nothing complicated about it. You're publishing a `ServiceWrapper` type
service (`service=ServiceWrapper.class`). As mentioned previously, you should
always extend the service interface wrapper as opposed to implementing the
service interface itself. Other than that, there's already a class constructor,
so you can just begin overriding the methods of the service. Here's an example:

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

With this example, you're overriding the `authenticateByEmailAddress()` method,
but leveraging the existing functionality of the method by calling `return
super.authenticateByEmailAddress()` at the end of the method. To run this
example, add two import statements:

    import com.liferay.portal.kernel.exception.PortalException;
    java.util.Map;

You could build and deploy this module right now and be on your way. But you've
only seen how easy it is to get a `servicewrapper` type project created for
Liferay. There was some behind the scenes configuration and dependency
management that Blade Tools handled for you. 

## Configuring a Module

In the root of the module, there's a `bnd.bnd` file and `build.gradle` file.
Open the `build.gradle` file first.

    buildscript {
        repositories {
            ivy {
                url 'https://bndtools.ci.cloudbees.com/job/bnd.master/719/artifact/dist/bundles'
                layout 'pattern', {
                    artifact '[module]/[artifact]-[revision].[ext]' /* OSGi repo pattern */
                }
            }
        }
        dependencies {
            classpath 'biz.aQute.bnd:biz.aQute.bnd.gradle:3.0.0'
        }
    }

    apply plugin: 'biz.aQute.bnd.builder'

    repositories {
        mavenCentral()
        maven {
            url 'https://repository.liferay.com/nexus/content/groups/public'
        }
    }

    sourceCompatibility = 1.7
    version = '1.0'

    dependencies {
        compile 'com.liferay.portal:portal-service:7.0.0-SNAPSHOT'
        compile 'org.osgi:org.osgi.compendium:5.0.0'
    }

The repositories and dependencies of the `buildscript` are required for the
building of your module, and some repositories and dependencies upon which your
code depends are also declared. Take special note of the plugin that’s applied:
`biz.aQute.bnd.builder`. This plugin is needed because the Gradle project created
for you uses Bnd to configure the project without using a build environment
based on a Bnd Workspace. 

The information specified in the `bnd.bnd` file is used to generate the
`MANIFEST.MF` file that the OSGi specification requires.

    -dsannotations: *

    Bundle-Version: 1.0.0.${tstamp}

    Import-Package: \
        com.liferay.portal.model;version='[7.0,7.1)',\
        com.liferay.portal.service;version='[7.0,7.1)',\
        *

    Private-Package: com.liferay.docs.serviceoverride

To use the OSGi annotations for Declarative Services, specify `-dsannotations:
*`. Alternatively, specify `Service-Component: *` to use the Bnd annotations.
You're importing any packages the contained package requires to run in the
`Import-Package` declaration, and setting your Java package as a
`Private-Package`. This is an instruction to Bnd that the package listed must be
included in the JAR, but not exported.

## Building and Deploying the Project

To build and deploy the project, you need two commands (navigate to the
project's root folder if you're not already there): 
    
    gradle build

and

    blade deploy [Project Root]/build/libs/[Project Name].jar

You should see a message in your console that looks like this:

    Installed or updated bundle 336

Use the Felix Gogo shell to verify that your bundle was installed into Liferay's
module framework. If you're running Liferay locally, use

    telnet localhost 11311

to enter Felix Gogo shell. Then, at the prompt, enter `lb`, which stands for
*list bundles*.

    336|Active     |    1|com.liferay.docs.serviceoverride (1.0.0.201510151914)

The above output means the bundle is active.

Since the bundle created by this tutorial modified the
`authenticateByEmailAddress()` method, test its functionality by logging into
the portal. Check Liferay's log and you'll see

    Authenticating user by email address test@liferay.com

Following the steps from this tutorial, you can modify Liferay's behavior quite
extensively. If one of Liferay's portlets doesn't suit your needs at all,
however, and you think you have a better option that can be used inside Liferay
(you want to install and use a different app for blogging, perhaps), check out
the tutorial on [providing portlets to manage requests](/develop/tutorials/-/knowledge_base/7-0/providing-portlets-to-manage-requests).

## Related Topics 

[Publishing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services),

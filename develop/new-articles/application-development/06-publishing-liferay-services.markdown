# Publishing Liferay Services

To publish a service to Liferay, the service must be registered in Liferay's
module framework. The easiest way to publish services to Liferay is to create
them as [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
components. When publishing services to Liferay, it's a best practice to package
your service API and service implementation as separate bundles. If you'd like
to create a service client, the client should also be packaged as a separate
bundle. Since Liferay follows this practice, most modularized Liferay
applications consist of at least two or three bundles. In this tutorial, you'll
learn how to create a service API bundle, a service implementation bundle, and a
service client bundle. The service client takes the form of a command which can
be invoked from Liferay's Felix Gogo shell. You can find the code referenced by
this tutorial in complete projects on Github here:
[https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets](https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets). The specific projects referenced by this tutorial are `example-api`, `example-service`, and `example-command`.

## Creating a Service API Bundle

To create a service API bundle, use the following steps. These steps are similar
to the ones explained in the [Creating a Simple Bundle]() tutorial. However, to
create a service API bundle, you don't need to create a bundle activator. 

1. Navigate to your Plugins SDK's `portlets` folder in a terminal or command
   prompt and run the `create.[sh|bat]` script to create a new project. E.g.,
   enter this command:

        ./create.sh example-api "Example API"

2. Since you're not creating a portlet application, delete the `-portlet` suffix
   from your project's name.

3. Delete the `docroot` folder from your project. That folder is a legacy folder
   from the traditional (prior to Liferay 7) way of creating Liferay portlet
   applications.

4. Create a `bnd.bnd` file in your project and the following contents:

        Bundle-Name: Example API
        Bundle-SymbolicName: com.liferay.docs.exampleapi
        Bundle-Version: 1.0.0
        Export-Package: com.liferay.docs.exampleapi

    Of course, edit the entries to match your project's name and package
    structure.

5. Open your project's `ivy.xml` file. Remove the `-portlet` suffix from the
   line in which it appears. Then remove the default dependencies and replace
   them with this one:

        <dependency name="org.osgi.core" org="org.osgi" rev="5.0.0" />

    To create a service API bundle, all you need is the OSGi core.

6. Open your project's `build.xml` file. Remove the `-portlet` suffix from the
   project name. Then replace this import declaration

        <import file="../build-common-portlet.xml" />

    with this one:

        <import file="../../build-common-osgi-plugin />

7. Run the following command to download the dependencies required to build a
   bundle:

        ant clean

8. Now you're ready to import your project into Eclipse. Open Eclipse and click
   *File* &rarr; *New* &rarr; *Other* &rarr; *Java Project*. Uncheck the *Use
   default location* box and click *Browse*. Navigate to your project in your
   Plugins SDK and select it. Eclipse generates `.classpath` and `.project`
   files based on the contents of your project.

9. To create a service API, you only need to create an interface. If your
   project does not have a `src` folder, create one and add it to your Eclipse
   build path. Right-click on your project's `src` folder and select *New*
   &rarr; *Package* and create a new package. E.g., create a new package called
   *com.liferay.docs.exampleapi*. Then right-click on your new package and
   select *New* &rarr; *Interface*. Enter a name for the interface, e.g.,
   *Greeting*. Replace its default contents with the following code:

        package com.liferay.docs.exampleapi;

        public interface Greeting {
                
            public void greet(String name);

        }

    Of course, replace the package declaration with your package.

10. That's it! Make sure your Plugins SDK's `build.[username].properties` file
    is correctly configured with the location of your Liferay instance, then
    deploy your bundle with the following command:

        ant clean deploy

    After deploying your bundle, use Liferay's Felix Gogo shell or Felix Web
    Console to check that your bundle was installed into Liferay's module
    framework. If you have any questions about configuring your Plugins SDK's
    `build.[username].properties` file or about Liferay's Felix Gogo shell or
    Web Console, please refer to the [Creating a Simple Bundle]() tutorial.
    
Since you've only published an interface, there's not much else to test at this
point. Next, you'll implement the interface in a separate bundle to provide some
functionality.

## Creating a Service Implementation Bundle

Now that you've created a service API bundle, it's time to create a service
implementation bundle. This bundle will contain a class that implements the
interface provided by your service API bundle.

1. Navigate to your Plugins SDK's `portlets` folder in a terminal or command
   prompt and run the `create.[sh|bat]` script to create a new project. E.g.,
   enter this command:

        ./create.sh example-service "Example Service"

2. Since you're not creating a portlet application, delete the `-portlet` suffix
   from your project's name.

3. Delete the `docroot` folder from your project. That folder is a legacy folder
   from the traditional (prior to Liferay 7) way of creating Liferay portlet
   applications.

4. Create a `bnd.bnd` file in your project and the following contents:

        Bundle-Name: Example Service
        Bundle-SymbolicName: com.liferay.docs.exampleservice
        Bundle-Version: 1.0.0
        Private-Package: com.liferay.docs.exampleservice

    Of course, edit the entries to match your project's name and package
    structure. Note that you're using a `Private-Package` directive in your
    `bnd.bnd` file and not an `Export-Package` directive. The
    `com.liferay.docs.exampleapi` package needed to be exported (so that your
    service implementation bundle can import it) but the
    `com.liferay.docs.exampleservice` bundle does not need to be exported.

5. Open your project's `ivy.xml` file. Remove the `-portlet` suffix from the
   line in which it appears. Then remove the default dependencies and replace
   them with these ones:

        <dependency name="org.osgi.core" org="org.osgi" rev="5.0.0" />
        <dependency name="org.osgi.compendium" org="org.osgi" rev="5.0.0" />

    To create a service implementation bundle, you need the OSGi core, the OSGi
    compendium, and the service API bundle. The OSGi compendium JAR provides the
    `@Component` annotation that you'll use to publish your service as a
    [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
    component. Since you haven't published your service API bundle to Maven
    Central, you need to add it as a local
    [Ivy](http://ant.apache.org/ivy) dependency. Alternatively, you can simply
    copy your service API JAR file to your project's `lib` folder and add it to
    your Eclipse build path (after you import your project into Eclipse).

6. Open your project's `build.xml` file. Remove the `-portlet` suffix from the
   project name. Then replace this import declaration

        <import file="../build-common-portlet.xml" />

    with this one:

        <import file="../../build-common-osgi-plugin />

7. Run the following command to download the Ivy dependencies required to build
   your bundle:

        ant clean

8. Now you're ready to import your project into Eclipse. Open Eclipse and click
   *File* &rarr; *New* &rarr; *Other* &rarr; *Java Project*. Uncheck the *Use
   default location* box and click *Browse*. Navigate to your project in your
   Plugins SDK and select it. Eclipse generates `.classpath` and `.project`
   files based on the contents of your project.

    Check to see if the service API JAR file that you copied to your project's
    `lib` folder has been added to your Eclipse build path. If it hasn't been,
    add it now.

9. To create a service implementation, you need to implement interface provided
   by the service API. If your project does not have a `src` folder, create one
   and add it to Eclipse's build path. Right-click on your project's `src`
   folder and select *New* &rarr; *Package* and create a new package. E.g.,
   create a new package called *com.liferay.docs.exampleservice*. Then
   right-click on your new package and select *New* &rarr; *Class*. Enter a name
   for the class, e.g., *GreetingImpl*. Replace its default contents with the
   following code:

        package com.liferay.docs.exampleservice;

        import org.osgi.service.component.annotations.Component;

        import com.liferay.docs.exampleapi.Greeting;

        @Component(immediate=true)
        public class GreetingImpl implements Greeting {

                @Override
                public void greet(String name) {
                        System.out.println("Hello " + name + "!");
                }

        }

    Of course, replace the package declaration with your package. The
    `@Component` annotation on your service implementation class marks your
    class as a
    [Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services) 
    component. Your class is registered in Liferay's module framework under the
    name of the interface it implements, i.e. the interface published by your
    service API bundle. Specifying `immediate=true` inside of the `@Component`
    annotation makes Liferay's module framework immediately make the service
    implementation available. By default, Liferay's module framework uses a
    lazy-loading strategy. If this strategy were used, the service
    implementation would not be made available until it was needed.

    Recall that you used a `Private-Package` declaration and not an
    `Import-Package` declaration in your project's `bnd.bnd` file. You might be
    wondering how your service can be invoked if it's not included in an
    exported package. In Liferay's module framework, *packages* must be exported
    in order for other bundles to import them. However, any *service* that's
    registered in Liferay's module framework can be consumed from any bundle. To
    learn how to consume Liferay services, please see the
    [Consuming Liferay Services]() tutorial.

10. That's it! Make sure your Plugins SDK's `build.[username].properties` file
    is correctly configured with the location of your Liferay instance, then
    deploy your bundle with the following command:

        ant clean deploy

    After deploying your bundle, use Liferay's Felix Gogo shell or Felix Web
    Console to check that your bundle was installed into Liferay's module
    framework. If you have any questions about configuring your Plugins SDK's
    `build.[username].properties` file or about Liferay's Felix Gogo shell or
    Web Console, please refer to the [Creating a Simple Bundle]() tutorial.

Now you've implemented the interface published by your service API bundle but
you still need a client to invoke the service. Next, you'll create such a
client.

## Creating a Service Client Bundle

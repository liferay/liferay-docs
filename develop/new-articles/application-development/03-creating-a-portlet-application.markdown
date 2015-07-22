# Creating a Portlet Application

In this tutorial, you'll learn how to create a simple portlet application as a
bundle that can be deployed to Liferay's module framework. You can find the code
referenced by this tutorial in a complete project on Github here:
[https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-portlet](https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/example-portlet)

It's easy to create a portlet application bundle using Liferay's Ant-based
[Plugins SDK](http://www.liferay.com/downloads/liferay-portal/available-releases).
Using the Plugins SDK is not required; you could use
[Maven](https://maven.apache.org), [Gradle](https://gradle.org), or
[Bndtools](http://bndtools.org) instead.

To begin, navigate to your Plugins SDK's `portlets` folder in a terminal or
command prompt. Run the `create.[sh|bat]` script to create a new portlet
project. For example, enter this command

    ./create.sh example Example

to create a project named `example-portlet`. Delete your project's `docroot`
folder. When creating a Liferay portlet as a bundle, you don't need the
`docroot` folder or its contents. We'll specify all the folders and files you
need to create.

Still from the terminal or command line, create a new file in your project
called `bnd.bnd`. Add the following contents to this file:

    Bundle-Name: Example Portlet
    Bundle-SymbolicName: com.liferay.docs.exampleportlet
    Bundle-Version: 1.0.0
    Private-Package: com.liferay.docs.exampleportlet

Liferay's Plugins SDK uses a tool called [bnd](http://www.aqute.biz/Bnd/Bnd) to
read the `bnd.bnd` file and, based on its contents, to generate the
`MANIFEST.MF` file that the OSGi specification requires of bundles. Notice that
you're omitting a `Bundle-Activator` specification in your `bnd.bnd` file. A
[Bundle Activator](http://wiki.osgi.org/wiki/Bundle-Activator) is usually
required when creating a simple OSGi bundle. However, you're going to create
your portlet as a
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
component. A declarative services component can do anything that a bundle
activator can do. Therefore, you don't need to create or specify a
`Bundle-Activator` in your `bnd.bnd` file.

Next, edit your project's `ivy.xml` file. Remove the default dependencies and
replace them with these ones:

    <dependency name="org.osgi.core" org="org.osgi" rev="5.0.0" />
    <dependency name="org.osgi.compendium" org="org.osgi" rev="5.0.0" />
    <dependency name="portlet-api" org="javax.portlet" rev="2.0" />

You don't need the default dependencies to create a simple bundle but you do
need the OSGi core (since you're creating a bundle), the OSGi compendium (since
you're using declarative services), and the portlet API (since you're creating a
portlet).

Finally, edit your project's `build.xml` file. Then replace this import
declaration

    <import file="../build-common-portlet.xml" />

with this one:

    <import file="../../build-common-osgi-plugin.xml" />

Run the following command to download the dependencies required to build a
bundle:

    ant clean

Now you're ready to import your project into Eclipse. Open Eclipse and click
*File* &rarr; *New* &rarr; *Other* &rarr; *Java Project*. Uncheck the *Use
default location* box and click *Browse*. Navigate to your project in your
Plugins SDK and select it. Eclipse generates `.classpath` and `.project` files
based on the contents of your project.

Now it's time to create your portlet class. Right-click on your project's `src`
folder (if this folder doesn't exist, create it and add it to your project's
classpath) and select *New* &rarr; *Package* and create a new package. For
example, suppose you create a new package called
*com.liferay.docs.exampleportlet*. Right-click on your new package and select
*New* &rarr; *Class*. Name the class *ExamplePortlet*. Replace its default
contents with the following code:

    package com.liferay.docs.exampleportlet;

    import java.io.IOException;
    import java.io.PrintWriter;

    import javax.portlet.GenericPortlet;
    import javax.portlet.Portlet;
    import javax.portlet.PortletException;
    import javax.portlet.RenderRequest;
    import javax.portlet.RenderResponse;

    import org.osgi.service.component.annotations.Component;

    @Component(
            immediate = true,
            property = {
                    "com.liferay.portlet.display-category=category.sample",
                    "com.liferay.portlet.instanceable=true",
                    "javax.portlet.display-name=Example Portlet",
                    "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
    )
    public class ExamplePortlet extends GenericPortlet {
            
            @Override
            protected void doView(RenderRequest request, RenderResponse response)
                    throws PortletException, IOException {

                    PrintWriter printWriter = response.getWriter();

                    printWriter.print("Example Portlet is working!");
            }

    }

Of course, replace the package declaration with your package.

First, notice that your portlet class extends `GenericPortlet`. `GenericPortlet`
is an abstract class provided by the `portlet-api.jar` that you added as an Ivy
dependency. You're implementing overriding the dummy `doView` method of
`GenericPortlet` to make your portlet display some text so that you can confirm
that it's working.

Next, notice the `@Component` annotation on your portlet class. This annotation
marks your portlet class as a
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
[Service Component](https://osgi.org/javadoc/r5/cmpn/org/osgi/service/component/annotations/Component.html).

The `immediate = true` property declares that your component must be immediately
activated once its dependencies are satisfied by Liferay's module framework.

The `property` property allows multiple attributes to be specified. Notice that
the properties that you specify here are the ones that you used to specify in
files like `portlet.xml`, `liferay-portlet.xml`, and `web.xml` in a traditional
Liferay plugin. Specifying
`com.liferay.portlet.display-category=category.sample` makes your portlet appear
in the Sample category. Specifying `com.liferay.portlet.instanceable=true` makes
your portlet instanceable. Specifying `javax.portlet.display-name=Example
Portlet` provides a name for your portlet. And specifying
`javax.portlet.security-role-ref=power-user,user` means that the users with the
Power User or User roles have permission to add the portlet to a page.

The `service = Portlet.class` property specifies that your component publishes a
service under the `Portlet` interface. In Liferay's module framework, all
services must be registered under at least one interface name.

Before deploying your portlet, make sure that you've configured your Plugins SDK
with the location of your Liferay instance. To do so, create a
`build.[username].properties` file in your Plugins SDK and add the following
contents to it:

    app.server.dir=[Liferay Home]/tomcat-7.0.62
    auto.deploy.dir=[Liferay Home]/deploy
    liferay.home=[Liferay Home]

Replace `[Liferay Home]` with the path to your Liferay portlet bundle.

Now you can deploy your portlet bundle application by opening a terminal or
command prompt and running the following command from your project folder:

    ant clean deploy

Check Liferay's log for a message like this:

    com.liferay.docs.exampleportlet.jar copied successfully. Deployment will start in a few seconds.

This message means that Liferay's deployment mechanism has detected the JAR file
that you copied to the `deploy` folder. Liferay copies your JAR file to the
`osgi/modules` folder. Once your portlet bundle has been deployed, add your
portlet to a page and look for the following message (or the message that you
configured in your portlet) to be displayed by your portlet.

    Example Portlet is working!

If you see this message, your portlet bundle is working correctly!

Alternative methods of verifying that your portlet bundle was installed into
Liferay's module include using the Felix Gogo shell and the Felix Web Console.
To use the Gogo shell to confirm that your portlet bundle was successfully
installed, connect to Liferay's Gogo shell. If you're running Liferay locally,
use

    telnet localhost 11311

then, at the prompt, enter this command:

    lb

The `lb` command stands for *list bundles*. Verify that your bundle is listed
and its status is active. You should see a result like this:

    257|Active     |    1|Example Portlet (1.0.0)

To use the Web Console to confirm that your portlet bundle was successfully
installed, navigate to
[http://localhost:8080/o/system/console/bundles](http://localhost:8080/o/system/console/bundles)
and confirm that your bundle appears in the list. (It should be the bundle with
the highest ID).

During development, you'll often want to edit and update your bundle. To test
the (very easy) redeployment process, edit your bundle (e.g., change the message
that's displayed by your portlet) and redeploy your bundle by using the
following command from your project's directory:

    ant clean deploy

Confirm that Liferay automatically updates your portlet bundle and that the new
message is displayed. This demonstrates one advantage afforded by Liferay's
module framework: it speeds up development since it's much faster to update a
bundle running in Liferay's module framework than it is to redeploy a
traditional Liferay plugin which must be redeployed to Liferay's application
server.

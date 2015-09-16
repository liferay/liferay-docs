# Creating a Simple Bundle [](id=creating-a-simple-bundle)

In this tutorial, you'll learn how to create a simple bundle that can be
deployed to Liferay's module framework. You can find the code referenced by this
tutorial in a complete project on Github here:
[https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/simple-bundle-app](https://github.com/jrao/liferay-docs/tree/liferay7-development-tutorials/develop/tutorials/code/liferay-plugins-sdk-7.0.0/portlets/simple-bundle-app)

It's easy to create a bundle using Liferay's Ant-based [Plugins SDK](http://www.liferay.com/downloads/liferay-portal/available-releases).
Using the Plugins SDK is not required; you can use
[Maven](https://maven.apache.org), [Gradle](https://gradle.org), or
[Bndtools](http://bndtools.org) instead. Use the following steps to create a
simple bundle using the Plugins SDK.

1.  To begin, navigate to your Plugins SDK's `portlets` folder in a terminal or
    command prompt. Run the `create.[sh|bat]` script to create a new portlet
    project by entering this command:

        ./create.sh simple-bundle "Simple Bundle"

    This creates a project named `simple-bundle-portlet`.

2 . Delete the `-portlet` suffix from your project's name. You don't need the
    `-portlet` suffix since you're just creating a simple bundle, not a portlet. 

3.  Also, delete your project's `docroot` folder. Even if you were creating a
    portlet, you still wouldn't need the `docroot` folder since that folder is a
    legacy folder from the traditional (prior to Liferay 7) way of creating
    Liferay portlet applications.

4.  Create a new file in your project called `bnd.bnd`. Add the following
    contents to this file:

        Bundle-Name: Simple Bundle
        Bundle-SymbolicName: com.liferay.docs.simplebundle
        Bundle-Version: 1.0.0
        Private-Package: com.liferay.docs.simplebundle
        Bundle-Activator: com.liferay.docs.simplebundle.SimpleBundleActivator

    Of course, edit the entries to match your project's name and package
    structure. Liferay's Plugins SDK uses a tool called
    [bnd](http://www.aqute.biz/Bnd/Bnd) to read the `bnd.bnd` file and, based on
    its contents, to generate the `MANIFEST.MF` file that the OSGi specification
    requires of bundles.

5.  Next, edit your project's `ivy.xml` file. Remove the `-portlet` suffix from
    the line in which it appears. Then remove the default dependencies and
    replace them with this one:

        <dependency name="org.osgi.core" org="org.osgi" rev="5.0.0" />

    You don't need the default dependencies to create a simple bundle but you do
    need the OSGi core.

6.  Finally, edit your project's `build.xml` file. Remove the `-portlet` suffix
    from the project name. Then replace this import declaration

        <import file="../build-common-portlet.xml" />

    with this one:

        <import file="../../build-common-osgi-plugin.xml" />

7.  Run the following command to download the Ivy dependencies required to build
    your bundle:

        ant clean

8.  Now you're ready to import your project into Eclipse. Open Eclipse and click
    *File* &rarr; *New* &rarr; *Other* &rarr; *Java Project*. Uncheck the *Use
    default location* box and click *Browse*. Navigate to your project in your
    Plugins SDK and select it. Click *Finish*. Eclipse generates `.classpath`
    and `.project` files based on the contents of your project.

9.  To turn your project into a simple bundle, you'll implement a
    `[BundleActivator](https://osgi.org/javadoc/r5/core/org/osgi/framework/BundleActivator.html)`.
    If your project does not have a `src` folder, create one and add it to your
    Eclipse build path. Right-click on your project's `src` folder and select
    *New* &rarr; *Package* and create a new package. For example, create a new
    package called *com.liferay.docs.simplebundle*. Then right-click on your new
    package and select *New* &rarr; *Class*. Enter a name for the class, e.g.,
    *SimpleBundleActivator*. Replace its default contents with the following
    code:

        package com.liferay.docs.simplebundle;

        import org.osgi.framework.BundleActivator;
        import org.osgi.framework.BundleContext;

        public class SimpleBundleActivator implements BundleActivator {

            @Override
            public void start(BundleContext bundleContext) throws Exception {
                System.out.println("Hello Liferay!");
            }

            @Override
            public void stop(BundleContext bundleContext) throws Exception {
                System.out.println("Goodbye Liferay!");
            }

        }

    Of course, replace the package declaration with your package.

10. Next, make sure that you've configured your Plugins SDK with the location of
    your Liferay instance. To do so, create a `build.[username].properties` file
    in your Plugins SDK and add the following contents to it:

        app.server.dir=[Liferay Home]/tomcat-7.0.62
        auto.deploy.dir=[Liferay Home]/deploy
        liferay.home=[Liferay Home]

    Replace `[Liferay Home]` with the path to your Liferay bundle.

11. Now you can deploy your simple bundle application by opening a terminal or
    command prompt and running the following command from your project folder:

        ant clean deploy

    Check Liferay's log for a message like this:

        com.liferay.docs.simplebundle.jar copied successfully. Deployment will start in a few seconds.

    This message means that Liferay's deployment mechanism has detected the JAR
    file that you copied to the `deploy` folder. Liferay copies your JAR file to
    the `osgi/modules` folder. When your bundle starts, look for the following
    message (or whatever message you configured your bundle to display) in
    Liferay's log:

        Hello Liferay!

    If you see this message, your bundle is working correctly!

12. You can also use the Felix Gogo shell and the Felix Web Console to verify
    that your bundle was installed into Liferay's module framework. To use the
    Gogo shell to confirm that your bundle was successfully installed, connect
    to Liferay's Gogo shell. If you're running Liferay locally, use

        telnet localhost 11311

    then, at the prompt, enter this command:

        lb

    The `lb` command stands for *list bundles*. Verify that your bundle is
    listed and its status is active. You should see a result like this:

        257|Active     |    1|Simple Bundle (1.0.0)

    When you want to disconnect from the shell, type `disconnect` and hit
    *Enter*. 

13. To use the Web Console to confirm that your bundle was successfully
    installed, navigate to
    [http://localhost:8080/o/system/console/bundles](http://localhost:8080/o/system/console/bundles)
    and confirm that your bundle appears in the list. (It should be the bundle
    with the highest ID).

14. During development, you'll often want to edit and update your bundle. To
    test the (very easy) redeployment process, edit your bundle (e.g., change
    the messages that are printed upon the bundle starting and stopping) and
    redeploy your bundle by using the following command from your project's
    directory:

        ant clean deploy

    Confirm that Liferay automatically updates your bundle and that the new
    message is displayed. This demonstrates one advantage afforded by Liferay's
    module framework: it speeds up development since it's much faster to update
    a bundle running in Liferay's module framework than it is to redeploy a
    traditional Liferay plugin. Traditional Liferay plugins must be redeployed
    as web applications to Liferay's application server.

Excellent! You've learned how to create simple bundles that can be deployed to
Liferay's module framework.

## Related Topics [](id=related-topics)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Publishing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/publishing-liferay-services)

# Introduction to Blade Tools [](id=introduction-to-blade-tools)

The [Blade Tools](https://github.com/gamerson/blade.tools) are the easiest way
for Liferay developers to create new Liferay modules. Although the Plugins SDK
is also supported, Blade Tools let you create projects that can be used with any
IDE or development environment. There are two ways to obtain Blade Tools. You
can clone the repository and build the Blade Tools JAR from source or you can
simply download it. Once you've obtained the Blade Tools JAR, you can install
Blade Tools through the [Java Package Manager](http://jpm4j.org) (JPM). 

Blade Tools are packaged using [JPM](http://jpm4j.org/#!/md/install). The exact
installation process depends on your operating system.

You need to install JPM regardless of whether you want to build or download
Blade Tools. 

## Using the Java Package Manager to Download Blade Tools [](id=using-the-java-package-manager-to-download-blade-tools)

If you'd like to clone the repository and build Blade Tools from source, skip to
the next section.

1.  After you have installed JPM, download the `blade.jar` file:
    [https://liferay-test-01.ci.cloudbees.com/job/blade.tools/lastSuccessfulBuild/artifact/com.liferay.blade.cli/generated/com.liferay.blade.cli.jar](https://liferay-test-01.ci.cloudbees.com/job/blade.tools/lastSuccessfulBuild/artifact/com.liferay.blade.cli/generated/com.liferay.blade.cli.jar).

    Blade Tools is still under development. Two tools are currently available:
    the CLI and the migration tool. The Blade CLI tool is included in the
    `blade.jar` file that you downloaded. Other tools, such as a plugin
    migration tool (for migrating plugins from a 6.2 Plugins SDK to a 7.0
    module), are
    [covered in another tutorial](/develop/tutorials/-/knowledge_base/7-0/migrating-legacy-applications-to-new-plugins-sdk).

2.  Next, install the downloaded JAR:

        (sudo) jpm install -fl [Downloads Directory]/blade.jar

Skip over the next section to learn how to verify your installation.

## Installing Blade Tools from the Source Code [](id=installing-blade-tools-from-the-source-code)

Since Blade Tools are still under development, you may want to keep up with the
latest changes. You can clone the Blade Tools repository and build the JAR
locally.

1.  Clone the [Blade Tools](https://github.com/gamerson/blade.tools) repository.

2.  Open your terminal and execute

        gradle build export.blade

    to build the Blade Tools JAR.

3.  Use JPM to install the JAR:

        (sudo) jpm install -fl blade.cli/generated/distributions/executable/blade.jar

Proceed to the next section to learn how to verify your installation.

## Verifying Your Blade Tools Installation [](id=verifying-your-blade-tools-installation)

To check that Blade Tools is installed, make sure that the `blade` executable is
available on your system path. Test its usage by entering `blade` into a
terminal. If Blade Tools is installed correctly, you'll see the *MAIN OPTIONS*
and *Available sub-commands* for the installed tools:

    MAIN OPTIONS

       [ -b, --base <string> ]    -         Specify a new base directory
                                            (default working directory).
       [ -e, --exceptions ]       -         Print exception stack traces when
                                            they occur.
       [ -f, --failok <string;> ] -         Do not return error status for
                                            error that match this given
                                            regular expression.
       [ -k, --key ]              -         Wait for a key press, might be
                                            useful when you want to see the
                                            result before it is overwritten
                                            by a next command
       [ -p, --pedantic ]         -         Be pedantic about all details.
       [ -t, --trace ]            -         Trace on.
       [ -w, --width <int> ]      -         The output width, used for
                                            wrapping diagnostic output

    Available sub-commands: 

      create                      -         Creates a new Liferay module
                                            project from several available
                                            templates. 
      deploy                      -         Deploys a bundle to Liferay
                                            module framework 
      migrate                     -         Run migration tool on project
                                            sources to generate a report of
                                            problems that needs to be fixed 
      open                        -         Opens or imports a file or
                                            project in Liferay IDE

Now that you have Blade Tools installed, you should learn about its two most
commonly used commands: `blade create` and `blade deploy`.

## Using the Blade Create Command [](id=using-the-blade-create-command)

To learn how to use the Blade `create` command, enter *blade create* into a
terminal. At the time of this writing, entering *blade create* into a terminal
produces this output:

      jspportlet                  -         Use mvcportlet with jsps template
                                            for new project 
      portlet                     -         Use basic portlet template for
                                            new project 
      service                     -         Creates a project with a single
                                            service component 
      servicewrapper              -         Creates a project with a single
                                            service wrapper component

Note that in this context, the term 'service' refers to an OSGi service, not to
a Liferay API. To find the correct command syntax for creating a specific type
of project, enter *blade create [project type]*. E.g., entering *blade create
service* produces this output:

    NAME
      service                     - 

    SYNOPSIS
       service [options] <name> <[service]>

    OPTIONS

       [ -c, --classname <string> ] - If a class is generated in the project,
                                    provide the name of the class to be generated.
                                    If not provided defaults to Project name.

For more information about creating specific kinds of Liferay module projects
with Blade Tools, please see the
[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications),
[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components), and
[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)
tutorials.

## Using the Blade Deploy Command [](id=using-the-blade-deploy-command)

To use the Blade `deploy` command, you must first have built a module to deploy.
See the tutorials mentioned above for more information about creating module
projects. Once you've built a module, use the following command to deploy it to
Liferay:

    blade deploy [path to JAR file]

Blade Tools knows how to detect a locally running Liferay instance and
automatically deploy your module to Liferay.

To learn how to create new Liferay module projects, proceed to the
[next](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)
tutorial.

## Related Topics [](id=related-topics)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)

[Using BLADE to Create Modules](/develop/tutorials/-/knowledge_base/7-0/using-blade-to-create-modules)

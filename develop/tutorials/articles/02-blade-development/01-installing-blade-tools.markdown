# Installing Blade Tools [](id=installing-blade-tools)

There are two ways to install Blade Tools. You can clone the repository and
build Blade Tools from source, or you can install them through the 
[Java Package Manager](http://jpm4j.org) (JPM). 

Either way, before downloading or building the Blade Tools, you need to install
[JPM](http://jpm4j.org/#!/md/install). The exact process varies depending on
your OS.

## Using the Java Package Manager to Download Blade Tools [](id=using-the-java-package-manager-to-download-blade-tools)

If you'd rather clone the repository and build the tools from source, skip to
the next section.

After you have JPM installed, download the `blade.jar`
[here](https://liferay-test-01.ci.cloudbees.com/job/blade.tools/lastSuccessfulBuild/artifact/blade.cli/generated/distributions/executable/blade.jar).
At the time of this writing the Blade CLI tool is the only one available. More
tools, such as a plugin migration tool (for migrating plugins from a 6.2 Plugins
SDK to a module for 7.0), will be made available in the future. 

Next, install the downloaded JAR:

    (sudo) jpm install -fl [Downloads Directory]/blade.jar

Now the Blade CLI tool is installed, and the `blade` executable is in your path.
Test its usage by typing `blade` into the terminal. If Blade Tools are
installed correctly and on your path, you'll see the *MAIN OPTIONS* and
*Available sub-commands* for the installed tools:

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
                                            project. 
      deploy                      -         Deploys a bundle to Liferay 7 
      migrate                     -
      open                        -         Opens or imports a file or
                                            project in Liferay IDE 


## Installing Blade Tools from the Source Code [](id=installing-blade-tools-from-the-source-code)

Instead of downloading the Blade Tools from JPM, you can download the repository
and build the JAR locally. You first need to clone the [Blade Tools GitHub Repository](https://github.com/gamerson/blade.tools). 

Once you have the repository cloned down to your local machine, open your
terminal and execute

    gradle build export.blade

Next, use JPM to install the JAR:

    (sudo) jpm install -fl blade.cli/generated/distributions/executable/blade.jar

Now the Blade CLI tool is installed, and the `blade` executable is in your
path. Test its usage by typing `blade` into the terminal. If Blade Tools are
installed correctly and on your path, you'll see the *MAIN OPTIONS*, followed
by the *Available sub-commands* for the installed tools:

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
                                            project. 
      deploy                      -         Deploys a bundle to Liferay 7 
      migrate                     -
      open                        -         Opens or imports a file or

Now you have Blade Tools installed, and you probably want to learn how to
create projects using the tools. We have a [tutorial that shows just that](/develop/tutorials/-/knowledge_base/7-0/blade-tools-bundle-creation).

## Related Topics [](id=related-topics)

[Crerating a Simple bundle](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle)

[Using JSPs in a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/using-jsps-in-a-portlet-application)

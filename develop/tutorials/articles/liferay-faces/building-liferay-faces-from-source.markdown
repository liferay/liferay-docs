# Building Liferay Faces From Source [](id=building-liferay-faces-from-source-liferay-portal-6-2-dev-guide-en)

You may have several reasons for downloading and building Liferay Faces from its
project sourcee code: 
- To try out the latest cutting edge changes
- To investigate a suspected bug
- To learn how Liferay Faces is implemented

Whatever your reasons may be, we're happy to show you how to access the Liferay
Faces source code and build it. 

We'll start with installing the liferay-faces project. 

### Installing the liferay-faces Project [](id=installing-the-liferay-faces-project-liferay-portal-6-2-dev-guide-en)

It's important to install the version of Liferay Faces that you want. So, it's a
good idea to check the [Liferay Faces Version Scheme](http://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)
to confirm the version of Liferay Faces. 

You can either install the project by cloning it from GitHub or by downloading
it as a `.zip` file. We'll demonstrate both options.

**Cloning the project from GitHub**

Cloning the project, requires that you [set up Git](https://help.github.com/articles/set-up-git) 
on your machine. Once you've set up Git, you can download the liferay-faces
project from GitHub and work with a particular branch of the project, following
these instructions: 

1.  Execute the following command from your terminal:

        git clone https://github.com/liferay/liferay-faces.git

2.  Navigate into that directory by executing `cd liferay-faces`.

3.  Checkout the branch (`master` is the default branch) you want to use.

    For example, to use the first milestone release of version 4.2.0, execute
    the following command:

        git checkout 4.2.0-m1

**Downloading the project as a `.zip` file**

To download the liferay-faces project as a `.zip` file, follow these
instructions: 

1.  Visit the Liferay Faces project page,
    [https://github.com/liferay/liferay-faces](https://github.com/liferay/liferay-faces). 

2.  Click on the *branch* drop-down menu and select the branch or tag for the
    version of the liferay-faces project that you'd like to use. 

3.  Click on the *Download Zip* button to download the `[branch/tag name].zip`
    file for that branch or tag. 

4.  Extract the `.zip` file contents to a location on your machine.

5.  In a terminal window, navigate into the liferay-faces project's root
    directory: 

        cd liferay-faces-[version]

Now that you've installed the liferay-faces project, you can configure your
environment for building the project. 

### Building Liferay Faces with Maven [](id=building-liferay-faces-with-maven-liferay-portal-6-2-dev-guide-en)

Maven is required to build the liferay-faces project. You can download Maven
from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi).
We recommend putting your Maven installation's `bin` directory in your system's
`$PATH`, so you can run the Maven executable (`mvn`) easily from your terminal. 

1.  Copy the `externalLiferayFacesRepositories` `<profile>` from
    [`settings.xml`](https://github.com/liferay/liferay-faces/blob/master/settings.xml)
    into your local `$HOME/.m2/settings.xml` file. If you do not already have a
    `settings.xml` file in your Maven configuration, create a `settings.xml`
    file in your `$HOME/.m2` folder and copy the contents of the
    [`settings.xml`](https://github.com/liferay/liferay-faces/blob/master/settings.xml)
    file into it. 

2.  Build the source with Maven by executing the following command: 

        mvn clean package

Maven builds the following Liferay Faces artifacts: 

-   `alloy/target/liferay-faces-alloy-[version].jar`
-   `bridge-api/target/liferay-faces-bridge-api-[version].jar`
-   `bridge-impl/target/liferay-faces-bridge-impl-[version].jar`
-   `portal/target/liferay-faces-portal-[version].jar`
-   `util/target/liferay-faces-util-[version].jar`

That's it; you've built Liferay Faces from source! 

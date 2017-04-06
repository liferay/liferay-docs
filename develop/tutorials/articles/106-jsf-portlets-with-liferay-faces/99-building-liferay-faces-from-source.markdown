# Building Liferay Faces From Source [](id=building-liferay-faces-from-source)

You may have several reasons for downloading and building Liferay Faces from its
project source code: 

- To try out the latest cutting edge changes
- To investigate a suspected bug
- To learn how Liferay Faces is implemented

For this tutorial, you'll learn how to access the Liferay Faces source code and
build it yourself. The Liferay Faces source code is organized into several
different Github repositories:

- [liferay-faces-alloy](https://github.com/liferay/liferay-faces-alloy)
- [liferay-faces-bridge-api](https://github.com/liferay/liferay-faces-bridge-api)
- [liferay-faces-bridge-ext](https://github.com/liferay/liferay-faces-bridge-ext)
- [liferay-faces-bridge-impl](https://github.com/liferay/liferay-faces-bridge-impl)
- [liferay-faces-maven](https://github.com/liferay/liferay-faces-maven)
- [liferay-faces-portal](https://github.com/liferay/liferay-faces-portal)
- [liferay-faces-showcase](https://github.com/liferay/liferay-faces-showcase)
- [liferay-faces-util](https://github.com/liferay/liferay-faces-util)

First, you'll start with installing a Liferay Faces project. 

## Installing a Liferay Faces Project [](id=installing-the-liferay-faces-project)

It's important to install the version of Liferay Faces that you want. So, it's a
good idea to check the
[Liferay Faces Version Scheme](/develop/tutorials/-/knowledge_base/6-2/understanding-the-liferay-faces-version-scheme)
to confirm the version of Liferay Faces. 

You can either install the project by cloning it from GitHub or by downloading
it as a `.zip` file. Both options are demonstrated below. 

**Cloning the project from GitHub**

Cloning the project requires that you [set up Git](https://help.github.com/articles/set-up-git) 
on your machine. Once you've set up Git, you can download a Liferay Faces
project from GitHub and work with a particular branch of the project, following
these instructions: 

1. Execute the following command from your terminal:

        git clone https://github.com/liferay/liferay-faces-[PROJECT]

    Replace the `[PROJECT]` variable with the Liferay Faces project you desire
    (e.g., `liferay-faces-bridge-impl.`).

2. Navigate into that directory by executing `cd liferay-faces-[PROJECT]`.

3. Checkout the branch (`master` is the default branch) you want to use.

    For example, to use the 3.x version of source code, execute the following
    command:

        git checkout 3.x

**Downloading the project as a `.zip` file**

To download the Liferay Faces project as a `.zip` file, follow these
instructions: 

1. Visit the Liferay Faces project's Github page.

2. Click on the *branch* drop-down menu and select the branch or tag for the
   version of the project that you'd like to use. 

3. Click on the *Clone or download* &rarr; *Download ZIP* button on that right
   side of the page to download the `[branch/tag name].zip` file for that branch
   or tag. 

4.  Extract the `.zip` file contents to a location on your machine.

5. In a terminal window, navigate into the Liferay Faces project's root
   directory: 

        cd liferay-faces-[PROJECT]

Now that you've installed the Liferay Faces project, you can configure your
environment for building the project. In the next section of this tutorial,
you'll explore building Liferay Faces with Maven. 

## Building Liferay Faces with Maven [](id=building-liferay-faces-with-maven)

Maven is required to build the Liferay Faces project. You can download Maven
from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi). It
is recommended to place your Maven installation's `bin` directory in your
system's `$PATH`, so you can run the Maven executable (`mvn`) easily from your
terminal. 

1. Copy the `externalLiferayFacesRepositories` `<profile>` from
   [`settings.xml`](https://github.com/liferay/liferay-faces/blob/master/settings.xml)
   into your local `$HOME/.m2/settings.xml` file. If you do not already have a
   `settings.xml` file in your Maven configuration, create a `settings.xml`
   file in your `$HOME/.m2` folder and copy the contents of the
   [`settings.xml`](https://github.com/liferay/liferay-faces/blob/master/settings.xml)
   file into it. 

2. Make sure you're in the liferay-faces directory and, then, build the source with Maven by executing the following command: 

        mvn clean package

Maven builds the Liferay Faces artifacts contained in that project. For example,
running `mvn clean package` in the root folder of the
`liferay-faces-bridge-impl` project produces the
`bridge-impl/target/liferay-faces-bridge-impl-[version].jar`. Artifacts
generated in other Liferay Faces projects follow a similar folder path.

That's it; you've built Liferay Faces from source! 

## Related Topics [](id=related-topics)

[Creating and Deploying JSF Portlets](/develop/tutorials/-/knowledge_base/6-2/creating-and-deploying-jsf-portlets)

[Developing Liferay Faces Portlets with Maven](/develop/tutorials/-/knowledge_base/6-2/developing-liferay-faces-portlets-with-maven)

[Understanding Liferay Faces Bridge](/develop/tutorials/-/knowledge_base/6-2/understanding-liferay-faces-bridge)

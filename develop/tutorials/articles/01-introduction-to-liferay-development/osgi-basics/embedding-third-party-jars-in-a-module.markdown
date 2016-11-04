# Embedding Third Party Libraries in a Module

The OSGi framework is intended for modular development where you can create
modules containing explicit information about their requirements and
capabilities which can then be used to assemble a working system. In a perfect
world, every library you would ever use would contain this information.
Unfortunately, not all libraries are packaged this way, as OSGi modules. So how
do you make use of those in OSGi?

One way to make use of these libraries is to embed them within your module.
Thought this isn't the only way, it's the method which most resembles that of
building WAR files and so it's the one we'll recommend for beginners.

There are a couple different ways to add library JARs to your modules:

- **Expand** the third party JAR directly with the module's classpath.
- **Embed** the third party JAR iwithin a directory in the module referencing
  this path in the module's `Bundle-ClassPath` header (explained a little
  later).

The recommended approach is to expand the JAR in your module. When doing this
the third party classes are positioned next to yours in with your module.

This approach may however suffer from a limitation resulting from having
duplicate paths within the JARs being expanded into your module. This could lead
to files being mistakenly overwritten, resulting in lost information.

Therefore, if the JARs you're embedding have no chance of overwriting each
other's resources in your module, chose to expand then into your module;
otherwise, chose to embed.

You'll learn how to embed library JARs using both ways next.

## Expanding Library JARs in a Module

To add a library JAR in your module so its contents are positioned next to your
module's existing resources, follow these steps:

1.  Open your module's `bnd.bnd` file and add the library JAR by inserting
    the `-includeresource` instruction:

        -includeresource: @shiro-core-[0-9]*.jar

    This declaration sets the `shiro-core-${version}.jar` as an included
    resource in the module. The `@` symbol is required, which specifies that the
    JAR should be expanded when the module is built. The `-includeresource`
    instruction accepts a comma delimited list of resource arguments. Include
    more libraries simply by appending them after a comma.

2.  Open your module's `build.gradle` file and set add the library JAR as a
    dependency so it's properly loaded:

        dependencies {
            ...
            compile group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0', transitive: false
        }

That's it! Your third party library JAR is configured and its contents are
available within your module once it's been built.

## Embedding Library JARs in a Module

To embed a library JAR in your module, follow the steps below:

1.  Open your module's `build.gradle` file and set the JAR you plan to embed as
    a dependency so it's properly loaded:

        dependencies {
            ...
            provided group: "commons-io", name: "commons-io", version: "2.4"
        }

    When you build your module, the JAR is first generated in your module
    project's folder structure since it's set as a dependency. Next, you must
    embed the third party JAR in your module's JAR.

2.  Open your module's `bnd.bnd` file and add the dependency JAR by inserting
    the `-includeresource` declaration:

        -includeresource: lib/commons-io.jar=commons-io-[0-9]*.jar

    This `includeresource` declaration adds the `common-io-[version].jar` file
    residing in the module project's folder structure to the module JAR's `lib`
    folder. The `lib/commons-io.jar` is your module's embedded JAR file.

3.  In the `bnd.bnd`, add the embedded JAR file to your module's classpath:

        Bundle-ClassPath: lib/commons-io.jar

Your dependency JAR is now embedded, and its resources are available to use in
your module JAR.

Excellent! You've learned two easy ways to use non-OSGi dependencies from your
OSGi module.

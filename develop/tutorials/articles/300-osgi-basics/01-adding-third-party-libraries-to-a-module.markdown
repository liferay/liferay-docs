# Adding Third Party Libraries to a Module [](id=adding-third-party-libraries-to-a-module)

The OSGi framework is intended for modular development where you can create
modules containing explicit information about their requirements and
capabilities that can be used to assemble a working system. In a perfect world,
every library would contain this information (i.e., as OSGi modules).
Unfortunately, not all libraries are packaged this way. So how do you use
them in OSGi?

You can add them within your module. This isn't the only way, but it's the
method that most resembles building WAR files, so it's the one we recommend for
beginners.

There are a couple different ways to add libraries to your modules:

- **Expand** the library directly within the module.
- **Embed** the library in a module folder and reference the folder path in the
  module's `Bundle-ClassPath` header.

The recommended approach is to expand the library in your module. This positions
the third party classes with your module's classes.

When using this approach, you may suffer a limitation resulting in duplicate
paths in the libraries being expanded into your module. This could lead
to mistakenly overwritten files, resulting in lost information. For
example, Service Loader descriptors (`META-INF/services/*`), which many
libraries include, could have similar names, but different content.

If the libraries you're adding have **no** chance of overwriting each other's
resources in your module, expand them into your module; otherwise, embed them.

You'll learn how to add libraries using both ways next.

## Expanding Libraries in a Module [](id=expanding-libraries-in-a-module)

To expand a library into your module so its contents are positioned next to your
module's existing resources, follow these steps:

1.  Open your module's `build.gradle` file and add the library as a dependency
    so it's available in the compile classpath:

        dependencies {
            provided group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0', transitive: false
        }

2.  Open your module's `bnd.bnd` file and add the library by inserting the
    `-includeresource` instruction:

        -includeresource: @shiro-core-[0-9]*.jar

    This instruction adds the `shiro-core-[version].jar` as an included resource
    in the module. The `@` symbol specifies that the JAR should be expanded when
    the module is built. The expression `[0-9]*` lets the build tool use the
    version available in the classpath.

    +$$$

    **Note:** The `-includeresource` instruction accepts a comma delimited list
    of resources.

    $$$

That's it! Your third party library is configured and its contents are available
within your module once it's built.

## Embedding Libraries in a Module [](id=embedding-libraries-in-a-module)

To embed a library in your module, follow these steps:

1.  Open your module's `build.gradle` file and add the library as a dependency
    so it's available in the compile classpath:

        dependencies {
            provided group: "org.apache.shiro", name: "shiro-core", version: '1.1.0', transitive: false
        }

2.  Open your module's `bnd.bnd` file and add the library by inserting the
    `-includeresource` instruction:

        -includeresource: META-INF/lib/shiro-core.jar=shiro-core-[0-9]*.jar

    This instruction adds the `shiro-core-[version].jar` file as an included
    resource in the module's `META-INF/lib` folder. The
    `META-INF/lib/shiro-core.jar` is your module's embedded library. The
    expression `[0-9]*` lets the build tool use the version available in the
    classpath.

3.  In the `bnd.bnd`, add the embedded library to your module's classpath:

        Bundle-ClassPath: ., META-INF/lib/shiro-core.jar

    There is a period (`.`) followed by a comma (`,`) before any library paths
    in the `Bundle-ClassPath` header. This ensures that the root path of your
    module is considered the first element of the module's classloader.

    +$$$

    **Note:** The `Bundle-ClassPath` header accepts a comma delimited list of
    libraries.

    $$$

Your library is now embedded and its resources are available to use in your
module.

+$$$

**Note:** If the library you've added as a dependency in your `build.gradle`
file has transitive dependencies, those may also be referenced in the
`-includeresource:` instruction by name without having to add them explicitly to
the list of dependencies.

$$$

Excellent! You've learned two easy ways to use non-OSGi libraries from your OSGi
module.

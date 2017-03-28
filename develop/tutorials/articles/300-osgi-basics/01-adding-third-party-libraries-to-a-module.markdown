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

- **Expand** the libraries directly within the module.
- **Embed** the libraries in a module folder and reference the folder paths in
    the module's `Bundle-ClassPath` header.

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

Expanding libraries into your module positions their resources alongside your
module's existing resources. You can expand some or all of the libraries and
their resources into your module. 

If you're using a Gradle environment, such as a Liferay Workspace project, that
has the Liferay Gradle plugin (i.e., `com.liferay.plugin`), you can expand *all
resources* from *all libraries* in a module by opening the module's
`build.gradle` file and adding this instruction: 

    liferayOSGi {
        expandCompileInclude = true
    }

Alternatively, you can expand a *subset of libraries* or a *subset of resources
from libraries* into your module. 

1.  Open your module's Gradle build file, Maven POM, or Ivy XML file and add
    the libraries as dependencies so they're available in the compile classpath.

    **Gradle:**

        dependencies {
            provided group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0', transitive: false
        }

    **Maven:**

        <dependency>
          <groupId>org.apache.shiro</groupId>
          <artifactId>shiro-core</artifactId>
          <version>1.1.0</version>
          <scope>provided</scope>
        </dependency>

    **Ant/Ivy:**

        <dependency conf="provided" name="shiro-core" org="org.apache.shiro" rev="1.1.0" />

2.  Open your module's `bnd.bnd` file and add the libraries or specific library
    resources by adding them to the `-includeresource` instruction:

        -includeresource: @shiro-core-[0-9]*.jar

    This instruction adds the `shiro-core-[version].jar` as an included resource
    in the module. The `@` symbol specifies that the JAR should be expanded when
    the module is built. The expression `[0-9]*` lets the build tool use the
    version available in the classpath.

    +$$$

    **Note:** The `-includeresource` instruction accepts a comma delimited list
    of resources.

    $$$

That's it! Your third party libraries are configured and their contents are
available within your module once it's built.

## Embedding Libraries in a Module [](id=embedding-libraries-in-a-module)

You can use Gradle, Maven, or Ivy to embed libraries in your module. 

### Embedding Libraries Using Gradle

To embed a library in your module, open your module's `build.gradle` file and
add the library as a dependency so it's available in the compile classpath:

    dependencies {
        compileOnly group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
    }

The `compileOnly` configuration is transitive. It embeds the artifact, and all
of the artifact's dependencies in the module's `META-INF/lib` folder. Also, it
adds the artifact JARs to the `Bundle-ClassPath` header in the module's
manifest. 

### Embedding Libraries Using Maven or Ivy

To embed a library in your module using Maven or Ivy, follow these steps:

1.  Open your module's build file and add the library as a dependency so it's
    available in the compile classpath:

    **Maven:**

        <dependency>
          <groupId>org.apache.shiro</groupId>
          <artifactId>shiro-core</artifactId>
          <version>1.1.0</version>
          <scope>provided</scope>
        </dependency>

    **Ant/Ivy:**

        <dependency conf="provided" name="shiro-core" org="org.apache.shiro" rev="1.1.0" />

2.  Open your module's `bnd.bnd` file and add the library to the
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

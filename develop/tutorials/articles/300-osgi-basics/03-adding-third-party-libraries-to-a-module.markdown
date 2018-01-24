# Adding Third Party Libraries to a Module [](id=adding-third-party-libraries-to-a-module)

The OSGi framework lets you build applications composed of multiple modules. The
modules must specify their requirements and capabilities for the framework to
assemble them into a working system. In a perfect world, every Java library
would specify this information in an OSGi `META-INF/MANIFEST.MF` file, but many
libraries don't. So how do you use them in your OSGi module?

Java libraries that aren't OSGi bundles (modules) must be repackaged for your
module to consume. Here are a few methods available:

-   **Wrap** the third party code inside a stand-alone OSGi module.
-   **Copy** the Java packages you need from the libraries directly into your
    module.
-   **Embed** the libraries wholesale directly into your module.

**Wrapping** the library requires creating and managing OSGi metadata (e.g.,
importing and exporting Java packages) and still leaves you to blacklist any
packages you don't need. Instead of wrapping the library in a new module, a
combination of
[copying packages from libraries and embedding libraries wholesale into your module](#workflow-for-copying-java-packages-from-libraries)
is recommended because it avoids creating another module and configuring 
package imports and exports. The next section describes a workflow for this
recommended approach. 

+$$$

**Note**: Liferay's Gradle plugin `com.liferay.plugin` automates several third
party library configuration steps. The plugin is applied to
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
Gradle module projects created in
[Liferay @ide@](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)
or using
[Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)
automatically.

To leverage the `com.liferay.plugin` plugin outside of Liferay Workspace, add
code like the listing below to your Gradle project:

    buildscript {
        dependencies {
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins", version: "3.2.29"
        }

        repositories {
            maven {
                url "https://cdn.lfrs.sl/repository.liferay.com/nexus/content/groups/public"
            }
        }
    }

    apply plugin: "com.liferay.plugin"

If you use Gradle without the `com.liferay.plugin` plugin, you'll have to
configure your module similar to the way this tutorial demonstrates for Maven
and Ivy.

$$$

## Workflow for Copying Java Packages from Libraries [](id=workflow-for-copying-java-packages-from-libraries)

When you have a dependency on a JAR, much of the time you only need parts of it.
The OSGi container can resolve dependencies faster if you specify only the
packages your module and its transitive dependencies need. Since bnd adds all
packages on the module's classpath to `MANIFEST.MF`'s `Import-Package` header,
to resolve dependencies faster you must manually specify the packages your
module uses. In this way, you avoid having dependencies on bundles you don't
need.

Here's a configuration workflow that minimizes dependencies and package imports:

1.  Add the library as a compile-only dependency (e.g., `compileOnly` in
    Gradle).

2.  Copy only the library packages you need by specifying them in a conditional
    package instruction (`Conditional-Package`) in your `bnd.bnd` file. Here are
    some examples:

    `Conditional-Package: foo.common*` adds packages your module uses such as
    `foo.common`, `foo.common-messages`, `foo.common-web` to your module's class
    path.

    `Conditional-Package: foo.bar.*` adds packages your module uses such as
    `foo.bar` and all its sub-packages (e.g., `foo.bar.baz`, `foo.bar.biz`,
    etc.) to your module's class path.

3.  If your module requires most or all of the library's packages, consider
    [embedding the entire library](#embedding-libraries-in-a-module),
    adding it to your module's classpath.

    **Gradle**: Use the `compileInclude` dependency configuration. See
    [Embedding a Library using Gradle](#embedding-libraries-using-gradle).

    **Maven/Ant**: Specify a dependency in the `provided` scope and add an
    `-includeresource` instruction in the `bnd.bnd` file. See
    [Embedding a Library using Maven or Ivy](embedding-libraries-using-maven-or-ivy).

3.  Lastly, if after embedding a library you get unresolved imports when
    trying to deploy to Liferay, you may need to blacklist some imports: 

    `Import-Package: !foo.bar.baz`

Next you'll explore embedding libraries in a module.

## Embedding Libraries in a Module [](id=embedding-libraries-in-a-module)

You can use Gradle, Maven, or Ivy to embed libraries in your module. Below are
examples for adding [Apache Shiro](https://shiro.apache.org) using all three
build utilities. 

### Embedding a Library Using Gradle [](id=embedding-libraries-using-gradle)

Open your module's `build.gradle` file and add the library as a dependency in
the `compileInclude` configuration:

    dependencies {
        compileInclude group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
    }

The `com.liferay.plugin` plugin's `compileInclude` configuration is transitive.
The `compileInclude` configuration embeds the artifact and all its dependencies
in a `lib` folder in the module's JAR. Also, it adds the artifact JARs to the
module's `Bundle-ClassPath` manifest header. 

**Note**: The `compileInclude` configuration does not download transitive
[optional dependencies](https://maven.apache.org/guides/introduction/introduction-to-optional-and-excludes-dependencies.html).
If your module requires such artifacts, add them as you would another third party library.

+$$$

**Note:** If the library you've added as a dependency in your `build.gradle`
file has transitive dependencies, you can reference them in an
`-includeresource:` instruction by name without having to add them explicitly to
the list of dependencies. See how it's used in the Maven section next. 

$$$

### Embedding a Library Using Maven or Ivy [](id=embedding-libraries-using-maven-or-ivy)

Follow these steps:

1.  Open your module's build file and add the library as a dependency in the
    `provided` scope:

    **Maven:**

        <dependency>
          <groupId>org.apache.shiro</groupId>
          <artifactId>shiro-core</artifactId>
          <version>1.1.0</version>
          <scope>provided</scope>
        </dependency>

    **Ant/Ivy:**

        <dependency conf="provided" name="shiro-core" org="org.apache.shiro" rev="1.1.0" />

2.  Open your module's `bnd.bnd` file and add the library to an
    `-includeresource` instruction: 

        -includeresource: META-INF/lib/shiro-core.jar=shiro-core-[0-9]*.jar;lib:=true

    This instruction adds the `shiro-core-[version].jar` file as an included
    resource in the module's `META-INF/lib` folder. The
    `META-INF/lib/shiro-core.jar` is your module's embedded library. The
    expression `[0-9]*` helps the build tool match the library version 
    to make available on the module's classpath. The `lib:=true` directive adds
    the embedded JAR to the module's classpath via the `Bundle-Classpath`
    manifest header.

Your library is now embedded and its resources are available to use in your
module.

Next, you'll learn how to expand libraries into a module.

## Expanding Libraries into a Module [](id=expanding-libraries-in-a-module)

Expanding libraries into your module positions their resources alongside your
module's existing resources. You can expand some or all of the libraries and
their resources into your module.

If you're using Gradle and have applied Liferay's Gradle plugin
`com.liferay.plugin`, adding the following instruction to your `build.gradle`
file expands all dependencies that are a part of the `compileInclude`
configuration (and their transitive dependencies) into your module:

    liferayOSGi {
        expandCompileInclude = true
    }

Alternatively, you can expand a subset of resources from a library into your module.

1.  Open your module's Gradle build file, Maven POM, or Ivy XML file and add
    the libraries as dependencies so they're available in the compile classpath.

    **Gradle:**

        dependencies {
            compileOnly group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0', transitive: false
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

2.  Open your module's `bnd.bnd` file and use a `-includeresource` instruction
    with regular expressions to specify library resources to expand into the
    module.

        -includeresource: @shiro-core-[0-9]*.jar

    This instruction adds the `shiro-core-[version].jar` as an included resource
    in the module. The `@` symbol specifies that the JAR should be expanded when
    the module is built. The expression `[0-9]*` lets the build tool use the
    version available in the classpath.

    +$$$

    **Note:** The `-includeresource` instruction accepts a comma delimited list
    of resources.

    $$$

+$$$

**Note**: Libraries whose resources and descriptors use *unique* names can be
embedded safely in your module.

If two libraries have resources or descriptors that use the *same* names, those
resources or descriptors can overwrite each other. For example, many libraries
include Service Loader descriptors. If Service Loader descriptors with the same
name are added inside a module's `META-INF/services/` folder, one overwrites the
other. If this happens, expand only parts of each library into your module,
making sure not to add descriptors that use the same name.

$$$

That's it! Your third party library resources are configured and their contents
are available within your module once it's built.

Excellent! You've learned two easy ways to use non-OSGi libraries in your OSGi
module.

## Related Topics [](id=related-topics)

<!--
[Importing Packages](/develop/tutorials/-/knowledge_base/7-0/importing-packages)

[Exporting Packages](/develop/tutorials/-/knowledge_base/7-0/exporting-packages)
-->

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)

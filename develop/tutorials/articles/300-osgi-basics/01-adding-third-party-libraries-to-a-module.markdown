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

-   **Embed** the libraries in a module folder and reference them in the
    module's classpath. 
-   **Expand** specific parts of the libraries directly into the module.

Libraries whose resources and descriptors use unique names can be embedded
safely in your module.

If two libraries have resources or descriptors that use the same names, those
resources or descriptors can overwrite each other. For example, many libraries
include Service Loader descriptors. If Service Loader descriptors with the same
name are added inside a module's `META-INF/services/` folder, one will overwrite
the other. In such a case, you can expand parts of each library into your
module, making sure to not add descriptors that use the same name.

You'll learn how to add libraries using both ways next.

## Embedding Libraries in a Module [](id=embedding-libraries-in-a-module)

You can use Gradle, Maven, or Ivy to embed libraries in your module. 

### Embedding Libraries Using Gradle [](id=embedding-libraries-using-gradle)

Liferay's Gradle plugin `com.liferay.plugin` automates several third party
library configuration steps. The plugin is applied to
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
configure your module similar to the way this this tutorial demonstrates for
Maven and Ivy.  

To embed a library in your module and make it available in the module's
classpath automatically, open your module's `build.gradle` file and add the
library as a dependency in the `compileInclude` configuration:

    dependencies {
        compileInclude group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0'
    }

The `com.liferay.plugin` plugin's `compileInclude` configuration is transitive.
It embeds the artifact, and all of the artifact's dependencies in a `lib` folder
in the module's JAR. Also, it adds the artifact JARs to the `Bundle-ClassPath`
header in the module's manifest. 

### Embedding Libraries Using Maven or Ivy [](id=embedding-libraries-using-maven-or-ivy)

To embed a library in your module using Maven or Ivy, follow these steps:

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
    `-includeresource` instruction. Here's an example instruction:

        -includeresource: META-INF/lib/shiro-core.jar=shiro-core-[0-9]*.jar;lib:=true

    This instruction adds the `shiro-core-[version].jar` file as an included
    resource in the module's `META-INF/lib` folder. The
    `META-INF/lib/shiro-core.jar` is your module's embedded library. The
    expression `[0-9]*` helps the build tool match the version of the library
    JAR available in the module's classpath. The `lib:=true` directive adds the
    embedded JAR to the module's classpath via the `Bundle-Classpath` manifest
    header. 

Your library is now embedded and its resources are available to use in your
module.

+$$$

**Note:** If the library you've added as a dependency in your `build.gradle`
file has transitive dependencies, those may also be referenced in the
`-includeresource:` instruction by name without having to add them explicitly to
the list of dependencies.

$$$

Next, you'll learn how to expand parts of libraries into a module. 

## Expanding Libraries in a Module [](id=expanding-libraries-in-a-module)

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

That's it! Your third party library resources are configured and their contents
are available within your module once it's built.

Excellent! You've learned two easy ways to use non-OSGi libraries from your OSGi
module.

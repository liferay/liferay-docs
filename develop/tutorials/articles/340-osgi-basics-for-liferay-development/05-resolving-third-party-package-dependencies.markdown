# Resolving Third Party Library Package Dependencies [](id=adding-third-party-libraries-to-a-module)

The OSGi framework lets you build applications composed of multiple OSGi bundles
(modules). For the framework to assemble the modules into a working system, the
modules must resolve their Java package dependencies. In a perfect world, every
Java library would be an OSGi module, but many libraries aren't. So how do you
resolve the packages your module needs from non-OSGi third party libraries?

Here is the main workflow for resolving third party Java library packages:

**Step 1 - Find an OSGi module of the library**: Projects, such as [Eclipse Orbit](https://www.eclipse.org/orbit/)
and [ServiceMix Bundles](https://servicemix.apache.org/developers/source/bundles-source.html), 
convert hundreds of traditional Java libraries to OSGi modules. Their artifacts
are available at these locations:

- [Eclipse Orbit](download.eclipse.org/tools/orbit/downloads/drops/R20170919201930/)
- [ServiceMix Bundles](https://mvnrepository.com/artifact/org.apache.servicemix.bundles)

Deploying the module to Liferay's OSGi framework lets you share it on the
system. If you find a module for the library you need,
[deploy](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module)
it. Then add a `compileOnly` dependency for it in your module. When you deploy
your module, the OSGi framework wires the dependency module to your module. If
you don't find an OSGi module based on the Java library, go to Step 2. 

**Step 2 - Resolve the Java packages privately in your module**: You can copy
required library packages into your module or embed them wholesale, if you
must. The rest of the tutorial shows you how to do these things. 

+$$$

**Note**: Liferay's Gradle plugin `com.liferay.plugin` automates several third
party library configuration steps. The plugin is automatically applied to
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
Gradle module projects created using [Liferay @ide@](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)
or [Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli).

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

If you use Gradle without the `com.liferay.plugin` plugin, you must
[embed the third party libraries wholesale](#embedding-libraries-using-gradle).

$$$

The recommended package resolution workflow is next. 

## Library Package Resolution Workflow [](id=library-package-resolution-workflow)

When you depend on a library JAR, much of the time you only need parts of it.
Explicitly specifying only the Java packages you need makes your module more
modular. This also keeps other modules that depend on your module from
incorporating unneeded packages. 

Here's a configuration workflow that minimizes dependencies and Java package
imports:

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

    Deploy your module. If a class your module needs or class its dependencies
    need isn't found, go back to main workflow **Step 1 - Find an OSGi module
    version of the library** to resolve it. 

    **Important**: Resolving packages by using compile-only dependencies and
    conditional package instructions assures you use only the packages you need
    and avoids unnecessary transitive dependencies. It's recommended to use the
    steps up to this point, as much as possible, to resolve required packages.  

3.  If a library package you depend on requires non-class files (e.g., DLLs,
    descriptors) from the library, then you might need to
    [embed the library wholesale in your module](#embedding-libraries-in-a-module).
    This adds the entire library to your module's classpath.

Next you'll learn how to embed libraries in your module. 

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

**Note:** If the library you've added as a dependency in your `build.gradle`
file has transitive dependencies, you can reference them by name in an
`-includeresource:` instruction without having to add them explicitly to the
dependency list. See how it's used in the Maven section next. 

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

Lastly, if after embedding a library you get unresolved imports when trying to
deploy to Liferay, you might need to blacklist some imports: 
    
`Import-Package: !foo.bar.baz`

Congratulations! Resolving all of your module's package dependencies, especially
those from traditional Java libraries, is a quite an accomplishment. 

## Related Topics [](id=related-topics)

[Importing Packages](/develop/tutorials/-/knowledge_base/7-1/importing-packages)

[Exporting Packages](/develop/tutorials/-/knowledge_base/7-1/exporting-packages)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-blade-cli)

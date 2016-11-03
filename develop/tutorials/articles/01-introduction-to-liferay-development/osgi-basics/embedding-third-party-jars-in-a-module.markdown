# Embedding Third Party Dependencies in a Module

The OSGi framework is intended for modular development where you can deploy
modules to export information that can then be imported by other modules. In a
perfect world, every module you create could reference its required dependencies
from other modules. Unfortantely, not all dependencies are packaged in an OSGi
module and registered in the OSGi registry, so how do you make use of those?
Liferay modules can still reference dependencies not registered in the OSGi
registry, but you'll have to declare their JARs within your module.

There are a couple different ways to add the JAR dependency to your module:

- Expand the third party JAR within the module JAR.
- Embed the third party JAR within the module JAR and add it to the module's
  classpath.

The best approach is to declare your dependency JAR so it's expanded in your
module JAR. When doing this, the third party code is mixed in with your module's
existing code.

This approach, however, causes problems in some cases. In Java EE, there is a
[service loader mechanism](http://blog.osgi.org/2013/02/javautilserviceloader-in-osgi.html)
that uses files in both the module's `META-INF/services` folder **and** its
embedded JAR files. Since these two areas could have the same files pointing to
various implementations, an expanded JAR could lead to files being mistakenly
overwritten, resulting in lost information.

Therefore, if the dependencies you're embedding have **no** chance of
overwriting other resources in your module, configure its dependency JARs to
expand within your module; otherwise, the dependency JARs should be embedded and
declared in the module's classpath.

You'll learn how to declare your third party dependencies using both ways next.

## Expanding Dependency JARs in a Module

You'll examine an example module to learn how to declare a dependency JAR
so its classes are integrated with a module's existing classes.

1.  Open your module's `bnd.bnd` file and add the dependency JAR by inserting
    the `-includeresource` declaration:

        -includeresource: @shiro-core-1.1.0.jar

    This declaration sets the `shiro-core-1.1.0.jar` dependency in the module.
    The `@` symbol is required, which specifies that the JAR should be expanded
    when the module is built.

2.  Open your module's `build.gradle` file and set the embedded JAR as a
    dependency so it's properly loaded:

        dependencies {
            ...
            compile group: 'org.apache.shiro', name: 'shiro-core', version: '1.1.0', transitive: false
        }

That's it! Your third party dependency JAR is now available for use within your
module.  

## Adding Dependency JARs in a Module's Classpath

To add a dependency JAR in your module's classpath, follow the steps outlined
below:

1.  Open your module's `bnd.bnd` file and add the dependency JAR by inserting
    the `-includeresource` declaration:

        -includeresource: lib/commons-io.jar=commons-io-[0-9]*.jar

    





# Example: Building an OSGi Module [](id=example-building-an-osgi-module)

The previous sections explained some of the most important concepts for Liferay
Portal 6 developers to understand about OSGi and modularity. Now it's time to
put this knowledge to practice by creating and deploying a module.

The module includes a Java class that implements an OSGi service using
Declarative Services. The project uses Gradle and bnd, and can be built and
deployed from within a [Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace).

Here's the module project's anatomy:

- `bnd.bnd`

- `build.gradle`

- `src/main/java/com/liferay/docs/service/MyService.java`

On building the module JAR, bnd generates the module manifest automatically. 

Here's the Java class:

    package com.liferay.docs.service;

    import org.osgi.service.component.annotations.Activate;
    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true,
        service = MyService.class
    )
    public class MyService {

        @Activate
        void activate() throws Exception {

            System.out.println("Activating " + this.getDescription());
        }

        public String getDescription() {

            return this.getClass().getSimpleName();
        }

    }

It contains these methods:

-  `getDescription` - returns the class's name

-  `activate` - prints the console message *Activating MyService*. The
    `@Activate` annotation signals the OSGi runtime environment to invoke this
    method on component activation.

The `@Component` annotation defines the class as an OSGi service component. The
following properties specify its details:

-  `service=MyService.class` - designates the component to be a service
    component for registering under the type `MyService`. In this example, the
    class implements a service of itself. Note, service components typically
    implement services for interface classes.

-  `immediate=true` - signals the Service Component Runtime to activate the
    component immediately after the component's dependencies are resolved.

The `bnd.bnd` file is next:

    Bundle-SymbolicName: my.service.project
    Bundle-Version: 1.0.0

The `Bundle-SymbolicName` is the arbitrary name for the module. The module's
version value `1.0.0` is appropriate.

bnd generates the module's OSGi manifest to the file `META-INF/MANIFEST.MF`
in the module's JAR. In this project, the JAR is created in the `build/libs`
folder.

The last file to create is the Gradle build file `build.gradle`:

    dependencies {
        compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
    }

Since the `MyService` class uses the `@Component` annotation, the project
depends on the OSGi service component annotations module. The build script is so
simple because Liferay Workspace module projects leverage the Workspace's Gradle
build infrastructure.

Although this module project was created for development in a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/installing-liferay-workspace),
it can easily be modified to use in other build environments. 

Place the project files in a subfolder of your Liferay Workspace's `modules`
folder (e.g., `[Liferay_Workspace]/modules/my.service.project`).

To build the module JAR and deploy it to @product@, execute the `deploy` Gradle
task:

    ../../gradlew deploy

+$$$

**Note**: If Blade is installed (recommended), Gradle can be executed by
entering `blade gw` followed by a task name (e.g., `blade gw deploy`). For
details on Blade commands, see [Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli).

$$$

On deploying the module, the following message is printed to the server console:

`Activating MyService`

Congratulations! You've successfully built and deployed an OSGi module to
@product@. 

Now that you've seen an OSGi module in action, you can appreciate more of the
ways modularity and OSGi improves development on Liferay. They're explained
next. 

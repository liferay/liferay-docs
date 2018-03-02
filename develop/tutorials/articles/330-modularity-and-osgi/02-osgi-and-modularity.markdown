# OSGi and Modularity [](id=osgi-and-modularity)

Modularity makes writing software, especially as a team, fun! Here are some
benefits to modular development on DXP: 

- @product@'s runtime framework is lightweight, fast, and secure. 
- The framework uses the OSGi standard. If you have experience using OSGi with
  other projects, you can apply your existing knowledge to developing on DXP. 
- Modules publish services to and consume services from a service registry.
  Service contracts are loosely coupled from service providers and consumers,
  and the registry manages the contracts automatically. 
- Modules' dependencies are managed automatically by the container, dynamically
  (no restart required). 
- The container manages module life cycles dynamically. Modules can be
  installed, started, updated, stopped, and uninstalled while Liferay is
  running, making deployment a snap. 
- Only a module's classes whose packages are explicitly exported are publicly
  visible; OSGi hides all other classes by default.
- Modules and packages are semantically versioned and declare dependencies on
  specific versions of other packages. This allows two applications that
  depend on different versions of the same packages to each depend on their
  own versions of the packages. 
- Team members can develop, test, and improve modules in parallel.
- You can use your existing developer tools and environment to develop modules. 

There are many benefits to modular software development with OSGi, and we can
only scratch the surface here. Once you start developing modules, you might find
it hard to go back to developing any other way. 

## Modules [](id=modules)

It's time to see what module projects look like and see @product@'s modular
development features in action. To keep things simple, only project code and
structure are shown: you can 
[create modules](/develop/tutorials/-/knowledge_base/7-1/starting-module-development)
like these anytime. 

These modules collectively provide a command that takes a String and uses it in
a greeting. Consider it "Hello World" for modules. 

### API [](id=api)

The API module is first. It defines the contract that a provider implements and
a consumer uses. Here is its structure: 

- `greeting-api`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greeting/api`
                    - `Greeting.java`
    - `bnd.bnd`
    - `build.gradle`

Very simple, right? Beyond the Java source file, there are only two other files:
a Gradle build script (though you can use any build system you want), and a
configuration file called `bnd.bnd`. The `bnd.bnd` file describes and configures the module: 

    Bundle-Name: Greeting API
    Bundle-SymbolicName: com.liferay.docs.greeting.api
    Bundle-Version: 1.0.0
    Export-Package: com.liferay.docs.greeting.api

The module's name is *Greeting API*. Its symbolic name--a name that ensures
uniqueness--is `com.liferay.docs.greeting.api`. Its semantic version is declared
next, and its package is *exported*, which means it's made available to other
modules. This module's package is just an API other modules can implement. 

Finally, there's the Java class, which in this case is an interface: 

    package com.liferay.docs.greeting.api;

    import aQute.bnd.annotation.ProviderType;

    @ProviderType
    public interface Greeting {

            public void greet(String name);

    }

The interface's `@ProviderType` annotation tells the service registry that
anything implementing the interface is a provider. The interface's one method
asks for a `String` and doesn't return anything. 

That's it! As you can see, creating modules is not very different from creating
other Java projects. 

### Provider [](id=provider)

An interface only defines an API; to do something, it must be implemented. This
is what the provider module is for. Here's what a provider module for the
Greeting API looks like: 

- `greeting-impl`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greeting/impl`
                    - `GreetingImpl.java`
    - `bnd.bnd`
    - `build.gradle`

It has the same structure as the API module: a build script, a `bnd.bnd`
configuration file, and an implementation class. The only differences are the
file contents. The `bnd.bnd` file is a little different: 

    Bundle-Name: Greeting Impl
    Bundle-SymbolicName: com.liferay.docs.greeting.impl
    Bundle-Version: 1.0.0

The bundle name, symbolic name, and version are all set similarly to the API. 

Finally, there's no `Export-Package` declaration. A client (which is the third
module you'll create) just wants to use the API: it doesn't care how its
implementation works as long as the API returns what it's supposed to return.
The client, then, only needs to declare a dependency on the API; the service
registry injects the appropriate implementation at runtime. 

Pretty cool, eh? 

All that's left, then, is the class that provides the implementation: 

    package com.liferay.docs.greeting.impl;

    import com.liferay.docs.greeting.api.Greeting;

    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true,
        property = {
        },
        service = Greeting.class
    )
    public class GreetingImpl implements Greeting {

        @Override
        public void greet(String name) {
            System.out.println("Hello " + name + "!");
        }

    }

The implementation is simple. It uses the `String` as a name and prints a hello
message. A better implementation might be to use Liferay's API to collect all
the names of all the users in the system and send each user a greeting
notification, but the point here is to keep things simple. You should
understand, though, that there's nothing stopping you from replacing this
implementation by deploying another module whose Greeting implementation's
`@Component` annotation specifies a higher service ranking property (e.g.,
`"service.ranking:Integer=100"`). 

This `@Component` annotation defines three options: `immediate = true`, an empty
property list, and the service class that it implements. The `immediate = true`
setting means that this module should not be lazy-loaded; the service registry
loads it as soon as it's deployed, instead of when it's first used. Using the
`@Component` annotation declares the class as a Declarative Services component,
which is the most straightforward way to create components for OSGi modules. A
component is a POJO that the runtime creates automatically when the module starts. 

To compile this module, the API it's implementing must be on the classpath. If
you're using Gradle, you'd add the `greetings-api` project to your `dependencies { ... }`
block. In a
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace) 
module, the dependency looks like this: 

    compileOnly project (':modules:greeting-api')

That's all there is to a provider module. 

### Consumer [](id=consumer)

The consumer or client uses the API that the API module defines and the provider
module implements. DXP has many different kinds of consumer modules.
[Portlets](/develop/tutorials/-/knowledge_base/7-1/portlets) 
are the most common consumer module type, but since they are a topic all by
themselves, this example stays simple by creating an command for the Apache
Felix Gogo shell. Note that consumers can, of course, consume many different
APIs to provide functionality. 

A consumer module has the same structure as the other module types: 

- `greeting-command`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greeting/command`
                    - `GreetingCommand.java`
    - `bnd.bnd`
    - `build.gradle`

Again, you have a build script, a `bnd.bnd` file, and a Java class. This
module's `bnd.bnd` file is almost the same as the provider's: 

    Bundle-Name: Greeting Command
    Bundle-SymbolicName: com.liferay.docs.greeting.command
    Bundle-Version: 1.0.0

There's nothing new here: you declare the same things you declared for the
provider. 

Your Java class has a little bit more going on: 

    package com.liferay.docs.greeting.command;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    import com.liferay.docs.greeting.api.Greeting;

    @Component(
        immediate = true,
        property = {
            "osgi.command.scope=greet",
            "osgi.command.function=greet"
        },
        service = Object.class
    )
    public class GreetingCommand {

        public void greet(String name) {
            Greeting greeting = _greeting;

            greeting.greet(name);
        }

        @Reference
        private Greeting _greeting;

    }

The `@Component` annotation declares the same attributes, but specifies
different properties and a different service. As in Java, where every class is a
subclass of `java.lang.Object` (even though you don't need to specify it by
default), in Declarative Services, the runtime needs to know the type of class
to register. Because you're not implementing any particular type, your parent
class is `java.lang.Object`, so you must specify that class as the service.
While Java doesn't require you to specify `Object` as the parent when you're
creating a class that doesn't inherit anything, Declarative Services does. 

The two properties define a command scope and a command function. All commands
have a scope to define their context, as it's common for multiple APIs to have
similar functions, such as `copy` or `delete`. These properties specify you're
creating a command called `greet` in a scope called `greet`. While you get no
points for imagination, this sufficiently defines the command. 

Since you specified `osgi.command.function=greet` in the `@Component`
annotation, your class must have a method named `greet`, and you do. But how
does this `greet` method work? It obtains an instance of the `Greeting` OSGi
service and invokes its `greet` method, passing in the `name` parameter. How is
an instance of the `Greeting` OSGi service obtained? The `GreetingCommand` class
declares a private service bean, `_greeting` of type `Greeting`. This is the
OSGi service type that the provider module registers. The `@Reference`
annotation tells the OSGi runtime to instantiate the service bean with a service
from the service registry. The runtime binds the `Greeting` object of type
`GreetingImpl` to the private field `_greeting`. The `greet` method uses the
`_greeting` field value.

Just like the provider, the consumer needs to have the API on its classpath in
order to compile, but at runtime, since you've declared all the dependencies
appropriately, the container knows about these dependencies, and provides them
automatically. 

If you were to
[deploy these modules to a DXP instance](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#building-and-deploying-a-module),
you'd be able to attach to the
[Gogo Shell](develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
and execute a command like this:

    greet:greet "Captain\ Kirk"

The shell would then return your greeting: 

    Hello Captain Kirk!

This most basic of examples should make it clear that module-based development
is easy and straightforward. The API-Provider-Consumer contract fosters loose
coupling, making your software easy to manage, enhance, and support. 

## A Typical Liferay Application [](id=a-typical-liferay-application)

If you look at a typical application from Liferay's source, you'll generally
find at least four modules: 

- An API module
- A Service (provider) module
- A Test module
- A Web (consumer) module

This is exactly what you'll find for some smaller applications, like the
Mentions application that lets users mention other users with the `@username`
nomenclature in comments, blogs, or other applications. Larger applications like
the Documents and Media library have more modules. In the case of the Documents
and Media library, there are separate modules for different document storage
back-ends. In the case of the Wiki, there are separate modules for different
Wiki engines. 

Encapsulating capability variations as modules facilitates extensibility. If you
have a document storage back-end that Liferay doesn't yet support, you can
implement Liferay's document storage API for your solution by developing a
module for it and thus extend Liferay's Documents and Media library. If there's
a Wiki dialect that you like better than what Liferay's wiki provides, you can
write a module for it and extend Liferay's wiki. 

Are you excited yet? Are you ready to start developing? Here are some resources
for you to learn more. 

## Related Topics [](id=related-topics)

[Liferay IDE](/develop/tutorials/-/knowledge_base/7-1/liferay-ide)

[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)

[Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli)

[Maven](/develop/tutorials/-/knowledge_base/7-1/maven)

[Developing a Web Application](/develop/tutorials/-/knowledge_base/7-1/developing-a-web-application)

[Planning a Plugin Upgrade to Liferay 7](/develop/tutorials/-/knowledge_base/7-1/migrating-existing-code-to-liferay-7)

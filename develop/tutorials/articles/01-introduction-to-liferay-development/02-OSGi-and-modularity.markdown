# OSGi and Modularity [](id=osgi-and-modularity)

<!-- Don't put this here. -->

In the 1970s, personal computers were in their nascent stage of development, and
they were expensive. The core component of a personal computer, the
microprocessor, was perhaps the most complicated and expensive piece of hardware
in the machine, costing $300 or more by itself. An engineer named Chuck Peddle
wanted to change all that, and he did: inventing the 6502 processor in 1976 for
a mere $25. 

This changed everything. A minor explosion of development in personal computers
occurred, because now computer manufacturers could design their systems around
this one important--and now inexpensive--component, and their computers would be
affordable. You’ll recognize the products that followed: the Commodore PET,
VIC-20, and 64, the Apple II, the Atari 600 and 800 and VCS, the Nintendo
Entertainment System, and the BBC Micro computers. All of these products were
based on this one, revolutionary component. 

A few years later and in a similar fashion, IBM developed a PC to compete with
all of these products. In order to create it quickly, they engineered a system
based on off-the-shelf components: an Intel processor, an ISA bus, a standard
video interface. They developed one component of this system themselves: the
Basic Input Output System, or BIOS. Released in 1981, the original IBM PC was
one personal computer in a market crowded with competing products based on the
6502. 

Soon, some engineers realized what IBM had done and knew they could use the same
standard components IBM had used, if only they could create a compatible
implementation of the BIOS. This was done very quickly, and less than a year
later IBM-compatible PCs began to flood the market. All of these computers used
components that were available to everyone and interfaced in the same way. Where
one standard component, the 6502, had created a minor explosion in the nascent
computer industry, a whole system of components--a video interface, a disk
interface, and a bus interface for components to talk to each other--that
inter-operated in a standard way created a huge explosion, creating the computer
industry we have today. 

This is an example of modularity. You can plug any number of available devices
into a computer and they work because they are designed to integrate with the
system in a standard way. It doesn't matter who manufactured the computer,
because they are all made up of inter-operating modules that individually
implement specific functionality, but when put together comprise an entire
working system. 

Modularity is a powerful paradigm for designing any complex system like @product@.
And now Liferay has made a bold move and re-engineered its product to take
advantage of modularity. 

## The Benefits of Modularity [](id=the-benefits-of-modularity)

Modularity makes writing software, especially as a team, fun! Here are some
benefits: 

- The framework is lightweight, fast, and secure. 
- The framework uses the OSGi standard. If you have experience using this with
    another project, you can apply your existing knowledge to Liferay. 
- Modules publish services to and consume services from a service registry.
    Service contracts are loosely coupled from service providers and consumers. 
    <!-- Emphasize the registry. -->
- Modules export code at the package level for use by other modules, enabling
    the registry to manage dependencies automatically. <!-- Automatic management
    of dependencies. --> 
- The container manages module lifecycles dynamically. Modules can be installed,
    started, updated, stopped, and uninstalled while @product@ is running, making
    deployment a snap. 
- <!-- OSGi provides a way to hide internal classes so they cannot be seen. --> Modules hide their internals from other modules, giving developers free reign
    to change the internals whenever needed. 
- Modules are semantically versioned and declare dependencies on specific
    versions of other modules. This allows two applications that depend on
    different versions of the same library to each depend on their own versions
    of the library. 
- You can use your existing developer tools and environment to develop modules. 

There are many benefits to modular software development, and we can only scratch
the surface here. Once you start, you may find it hard to go back to doing
things any other way. 

## Semantic Versioning [](id=semantic-versioning)

In any complex system there are a ton of supporting libraries. Developers find a
library that performs a specific function which they can call to provide
supporting infrastructure for what they are developing. Of course, since this is
a third-party piece of software, its author upgrades and enhances it according
to his or her timeline. If you use version 2.2.1 of this library and its author
a month later releases 2.3.0 and changes a call you made, you cannot upgrade to
the new version unless you first make a change to your software. This is called
"version lock." Similarly, if your software requires version 2.3.0 and another
supporting library requires 2.2.1, you may use one version in one classloader
and another version in a different classloader. This is called "version
promiscuity." 

Either of these situations makes your software more complex. You have to manage
upgrades manually and make sure all necessary versions are accounted for. This
prevents you from safely and easily evolving your project to the next level. 

[Semantic Versioning](http://semver.org) changes that with a system for managing
library versions using a MAJOR.MINOR.PATCH numbering system. This is used in
OSGi to define dependencies for modules. When packages are imported are
exported, the required version is also specified, and this allows the system to
provide the proper versions to the proper packages, if those versions are
installed. 

In this way, dependencies are managed in a much more straightforward way by the
system, freeing you to move forward with your project much more easily. 

<!-- Dependencies are based on versions, and these are rules for maintaining
compatibility. Dependencies are declared on packages, not modules. Add a section
on dependencies. A module exports and imports things. -->

### ProviderType vs. ConsumerType [](id=providertype-vs-consumertype)

<!-- Remove --> 

In a modular, semantically versioned world, then, code is broken up into modules
that have various inter-dependencies. These are managed by the container at
runtime. For the container to be able to manage these dependencies
appropriately, modules that implement an API need to be marked as such. This
allows the container to use semantic versioning to import the proper version of
the package. 

Required versions or version ranges are declared in a module's `bnd.bnd` file,
and this is what the container uses to create the proper configuration. To mark
an implementation as a provider, all a developer needs to do is add the
`@ProviderType` annotation to the interface. Once this is done, the container
can match APIs and implementers by their versions. 

If you don't provide this annotation, the module you're writing is assumed to be
a `@ConsumerType`. This is a module that *consumes* or uses the service. This is
a complex concept that is better explained when you get to the code, so for now
just keep it in the back of your mind, as you'll encounter it later. 

## Extensibility [](id=extensibility)

<!-- Move to after services and components. -->

As you might imagine, the system described above contains all the tools
necessary to make a well designed system that allows developers not only to
create applications based on modules, but also to extend the existing
functionality of the system. Liferay can benefit from this now because the
platform on which it rests is designed for both application development and
customization. 

Components in the OSGi runtime have what's called a *service ranking*. This can
be used by developers to extend or override modules that are already running in
the system. Extending or overriding existing functionality then becomes as easy
as providing your own implementation and then deploying your module with a
ranking higher than the default ranking. This is a simple and natural way to
extend the system, and it's incorporated into the design of the platform from
the ground up. 

## Modules [](id=modules)

Now that you have all the background information you need, it's time to show you
what a project looks like and how all of these concepts work themselves out in
reality. You'll see examples of all the above concepts in action. Only the code
and the structure of the project will be shown: to create a real project, check
out the [tooling](/develop/tutorials/-/knowledge_base/7-0/tooling) tutorials. 

This project is very simple: all it does is print out a string to the console.
This string is called a greeting. 

### API [](id=api)

Your first module is the API module. This module defines the contract that the
provider implements and the consumer uses. Its structure looks like this: 

- `greeting-api`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greetingapi`
                    - `Greeting.java`
    - `bnd.bnd`
    - `build.gradle`

Very simple, right? Beyond the Java source file, there are only two other files:
a Gradle build script (though you can use any build system you want), and a file
called `bnd.bnd`. This is a configuration file that defines settings for your
project: 


    Bundle-Name: Greeting API
    Bundle-SymbolicName: greeting.api
    Bundle-Version: 1.0.0.${tstamp}
    Export-Package: com.liferay.docs.greetingapi

As you can see, it contains data about this module's configuration. Its name is
*Greeting API*. Its symbolic name--a name that ensures uniqueness--is
`greeting.api`. Semantic versioning is declared next, and the package is
*exported*, which means it's made available to other modules. In this case,
since it's just an API, other modules would be able to provide implementations. 

Finally, you have the Java class, which in this case is an interface: 

    package com.liferay.docs.greetingapi;

    import aQute.bnd.annotation.ProviderType;

    @ProviderType
    public interface Greeting {

            public void greet(String name);

    }

Notice that there's no `@Component` annotation on this interface. Your
`Greeting` interface is not implementing an interface, it *is* an interface. It
does, however, have the `@ProviderType` annotation. This tells the service
registry that anything implementing this is a provider. All the code does is
declare one method that asks for a `String` and doesn't return anything. 

That's it! As you can see, creating modules is not very different from creating
other Java projects. 

### Provider [](id=provider)

An interface only defines an API; in order to make it do anything, it must be
implemented. This what the provider module is for. Here's what a provider module
for the Greeting API looks like: 

- `greeting-impl`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greetingimpl`
                    - `GreetingImpl.java`
    - `bnd.bnd`
    - `build.gradle`

It has the same structure as the API module: a build script, a `bnd.bnd`
configuration file, and the implementation class. The only differences are the
contents of the files. Your `bnd.bnd` file is a little different: 

    -dsannotations: *
    Bundle-Name: Greeting Impl
    Bundle-SymbolicName: com.liferay.docs.greetingimpl
    Bundle-Version: 1.0.0.${tstamp}
    Private-Package: com.liferay.docs.greetingimpl

The bundle name, symbolic name, and version are all configured similarly to the
API. `-dsannotations: *` means that you want to use the standard OSGi
annotations (such as `@Component`) to configure your class. This also shows that
you're using OSGi 
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services), which is
probably the most straightforward way to write modules. 

Finally, the `Private-Package` declaration denotes that the listed package isn't
exported like the API was. This sounds confusing until you remember that this
module lives in an ecosystem of modules, and that a service registry handles all
the dependencies. A client just wants to use the API: it doesn't care how its
implementation works as long as the API returns what it's supposed to return.
The client, then, only needs to declare a dependency on the API; the service
registry then takes care of injecting the appropriate, service-ranked
implementation at runtime. 

Pretty cool, eh? 

All that's left, then, is the class that provides the implementation: 

    package com.liferay.docs.greetingimpl;

    import com.liferay.docs.greetingapi.Greeting;

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

This is a very simple implementation. All it does is use the `String` as a name
and print a hello message. A better implementation might be to use Liferay's API
to collect all the users in the system and send them each a greeting
notification, but the point is to keep things simple here. You should
understand, though, that there's nothing stopping you from deploying another
module that implements the same API with a higher service ranking that overrides
this implementation (the service ranking is a property of the `@Service`
annotation). 

This `@Component` annotation is defining three options: `immediate = true`, an
empty property list, and the service that it's implementing. The `immediate =
true` setting means that this module should not be lazy-loaded; the service
registry should load it as soon as it's deployed, instead of when it's first
used. Using the `@Component` annotation declares your class as a Declarative
Services component, which is the most straightforward way to work with OSGi
modules. Components are POJOs that the runtime creates automatically when it
starts the module and all its references are satisfied. 

When you compile this module, the API module it's implementing needs to be on
your classpath, so you would configure your build system to do that. In this
case, you're using Gradle, so you'd add this to your `dependencies { ... }`
block: 

    compile project (':modules:greeting-api')

That's all there is to a provider module. 

### Consumer [](id=consumer)

The consumer uses the API defined by the API module and implemented by the
provider module. You can have many different kinds of consumer modules in
Liferay. Portlets are the most common type of consumer, but since they are a
topic all by themselves, this example stays simple by creating a command for the
Gogo shell. Note that consumers can, of course, consume many different APIs to
provide their functionality, even though only one is shown here. 

A consumer module has the same structure as the other module types: 

- `greeting-command`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greetingcommand`
                    - `GreetingCommand.java`
    - `bnd.bnd`
    - `build.gradle`

Again, you have a build script, a `bnd.bnd` file, and a Java class. This
module's `bnd.bnd` file is almost the same as the provider's: 

    -dsannotations: *
    Bundle-Name: Greeting Command
    Bundle-SymbolicName: com.liferay.docs.greetingcommand
    Bundle-Version: 1.0.0.${tstamp}
    Private-Package: com.liferay.docs.greetingcommand

There's nothing new here: you declare the same things you declared for the
provider, right down to the Declarative Services annotations. 

Your Java class has a little bit more going on: 

    package com.liferay.docs.greetingcommand;

    import org.osgi.service.component.annotations.Component;
    import org.osgi.service.component.annotations.Reference;

    import com.liferay.docs.greetingapi.Greeting;

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
            Greeting greeting = getGreeting();

            greeting.greet(name);
        }

        public Greeting getGreeting() {
            return _greeting;
        }

        @Reference
        public void setGreeting(Greeting greeting) {
            _greeting = greeting;
        }

        private Greeting _greeting;

    }

You have the same settings declared in the `@Component` annotation with
different properties and a different service. Just like in Java, where every
class is a subclass of `java.lang.Object` (even though you don't need to specify
it by default), in Declarative Services, the runtime must know the type of class
it's registering. Because you're not implementing any particular type, your
parent class is `java.lang.Object`, so you must specify that class as the
service. So while Java doesn't require you to specify `Object` as the parent
when you're creating a class that doesn't inherit anything, Declarative Services
does. 

The two properties define a command scope and a command function. All commands
have a scope to define their context, as it's very likely two or more APIs will
have similar functions, such as `copy` or `delete`. In the code above, you've
indicated that you're creating a command called `greet` in a scope called
`greet`. While you get no points for imagination, this is sufficient to define
the command. 

Since you specified `osgi.command.function = greet` in the `@Component`
annotation, your class must have a method named `greet`, and you do. But how
does this `greet` method work? It obtains an instance of the `Greeting` OSGi
service and invokes its `greet` method, passing in the `name` parameter. How is
an instance of the `Greeting` OSGi service obtained? The `GreetingCommand` class
declares a private service bean, `_greeting` of type `Greeting`. This is the
type of the OSGi service registered by the provider module. The
`GreetingCommand` class also provides public getter and setter methods for the
service bean. The setter method is decorated with the `@Reference` annotation
which tells the OSGi runtime to instantiate the service bean with a service
retrieved from the  service registry. The type `GreetingImpl` is used since it's
the only provider registered under the `Greeting` API. The `GreetingCommand`
class's `greet` method calls the public getter method of the private `_greeting`
instance variable.

Just like the provider, the consumer needs to have the API on its classpath in
order to compile, but at runtime, since you've declared all the dependencies
appropriately, the container knows about these dependencies, and can provide
them automatically. 

If you were to deploy this to a running Liferay instance, you'd be able to
attach to the [Gogo Shell](develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) and type something like 

    greet:greet "Captain Kirk"

The shell would then return your greeting: 

    Hello Captain Kirk!

This most basic of examples should make it clear that module-based development
is easy and straightforward. Because of the API-Provider-Consumer contract and
Semantic Versioning, you have a powerful toolset for dependency resolution and
API consistency. This makes your software easy to manage, enhance, and support. 

## A Typical Liferay Application [](id=a-typical-liferay-application)

Liferay applications follow this model closely. If you look at a typical
application from Liferay's source, you'll generally find at least four modules: 

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

All of this is for extensibility. If you have a document storage back-end that
@product@ doesn't yet support, you can implement Liferay's document storage API
for your solution by developing a module for it and thus extend Liferay's
Documents and Media library. If there's a Wiki dialect that you like better than
what Liferay's wiki provides, you can write a module for it and extend Liferay's
wiki. 

Are you excited yet? Are you ready to start developing? Here are some resources
for you to learn more. 

## Related Topics [](id=related-topics)

[BLADE CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli)

[Liferay IDE](/develop/tutorials/-/knowledge_base/7-0/liferay-ide)

[Planning a Plugin Upgrade to Liferay 7](/develop/tutorials/-/knowledge_base/7-0/migrating-existing-code-to-liferay-7)



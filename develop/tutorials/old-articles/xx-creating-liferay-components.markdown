# Creating Liferay Components [](id=creating-liferay-components)

As explained in the
[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)
tutorial, you need to understand two concepts in order to succeed as a Liferay 7
developer: *modules* and *components*. A module is the one and only type of
Liferay 7 plugin. A component is a Java class that's decorated with the
`@Component` annotation that implements a specified interface. Components are
registered in Liferay's module framework. For example, the
[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)
tutorial explains how to create a portlet application as a module. To do so, you
create a module project and create a component class that implements the
`Portlet` interface in that module.

Liferay components are typically created as
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
components. When creating Liferay components, it's a best practice to package
your interface and implementation as separate modules. If you're creating a
client, the client should also be packaged as a separate module. Since Liferay
follows this practice, most modularized Liferay applications consist of at least
two or three modules. For example, the Liferay Polls application consists of the
`polls-api`, `polls-service`, and `polls-web` modules (there's also a
`polls-test` module).

In this tutorial, you'll learn how to create an API module, an implementation
module (a.k.a. service module), and a client module. The client takes the form
of a command that can be invoked from Liferay's Felix Gogo shell. You can find
the code referenced by this tutorial in complete projects on Github here:
[https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules).
The specific projects referenced by this tutorial are
`com.liferay.docs.greetingapi`, `com.liferay.docs.greetingimpl`, and
`com.liferay.docs.greetingimpl`.

## Creating an API Module [](id=creating-an-api-module)

At the time of this writing, Blade Tools does not provide an API module
template. Nevertheless, it's easy enough to adapt the output of the service
module template to create an API module.

To create a module, run a command like this one:

    blade create -t service -s java.lang.Object -p com.liferay.docs.greetingapi -c Greeting greeting-api

Note that the `-s java.lang.Object` argument is required. This argument
specifies the interface implemented by your component class. Since you're
creating an API module, you're not creating a component class at all. Thus, the
value of the argument, `java.lang.Object`, doesn't matter. You'll delete the
`@Component` annotation in which this argument is used.

Running the command above creates a project with the following directory
structure:

- `greeting-api`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greetingapi`
                    - `Greeting.java`
    - `bnd.bnd`
    - `build.gradle`

Since you want to create an API module instead of a service module, you need to
modify the generated `Greeting.java` file and the generated `bnd.bnd` file.

First of all, since you're creating an API, your `Greeting.java` file should be
an interface. Edit it so that it looks like this:

    package com.liferay.docs.greetingapi;

    public interface Greeting {

            public void greet(String name);

    }

Notice that there's no `@Component` annotation on this interface. Your
`Greeting` interface is not implementing an interface, it *is* an interface.

Next, edit your `com.liferay.docs.greetingapi` module's `bnd.bnd` file so that
it looks like this:

    Bundle-Name: Greeting API
    Bundle-SymbolicName: greeting.api
    Bundle-Version: 1.0.0.${tstamp}
    Export-Package: com.liferay.docs.greetingapi

<!--You don't need the `dsannotations: *` line since you're not creating a
component in your module; you're only creating an interface.-->

You don't need the following lines since you're not invoking any Liferay
services:

    Import-Package: \
        com.liferay.portal.service;version="7.0.0",\
        org.osgi.framework;version="[1.7,2)",\
        *

You need the `Export-Package: com.liferay.docs.greetingapi` line since the
package with the `Greeting` interface must be made available to other modules.

To build your project, run `../../gradlew build` from your module's root
directory. Before deploying your module, you'll need to run `blade agent
install` to install a remote agent that facilitates the deploy action. Also,
make sure your Liferay server within your workspace is running. You can run
`blade server start` to run the server defined in your workspace. Once the
remote agent is installed and server is running, run `blade deploy` from your
module's root directory.

Since you've only published an interface, there's not much to test at this
point. Next, you'll implement the `Greeting` interface in a separate module.

## Creating an Implementation Module [](id=creating-an-implementation-module)

Now that you've created an API module, it's time to create an implementation
module. This module will contain a component class that implements the interface
provided by your API module. Implementation modules are sometimes called service
modules since they provide implementations of OSGi services. To create an
implementation module, run a command like this one:

    blade create -t service -s com.liferay.docs.greetingapi.Greeting -p com.liferay.docs.greetingimpl -c GreetingImpl greeting-impl
    
Running this command creates a project with the following directory structure:

- `greeting-impl`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greetingimpl`
                    - `GreetingImpl.java`
    - `bnd.bnd`
    - `build.gradle`

Since you're creating an implementation module (a.k.a. a service module) instead
of an API module, the contents of the generated class is much closer to what you
actually need.

Your `GreetingImpl.java` file is configured and ready to go. The only thing you
should add is the `greet(...)` method you specified in your `Greeting` class.
Add the following method in the body of your `GreetingImpl` class:

    @Override
    public void greet(String name) {
        System.out.println("Hello " + name + "!");
    }

The `@Component` annotation indicates that your class defines a
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
component. The `service = Greeting.class` line indicates that your class will be
registered under the `Greeting` interface.

Next, edit the generated `bnd.bnd` file so that it looks like this:

    -dsannotations: *
    Bundle-Name: Greeting Impl
    Bundle-SymbolicName: com.liferay.docs.greetingimpl
    Bundle-Version: 1.0.0.${tstamp}
    Private-Package: com.liferay.docs.greetingimpl

Your implementation module needs access to your API module in order to compile.
Open your implementation module's `build.gradle` file. Add the following
line to the top of the `dependencies {...}` block:

    compile project (':modules:greeting-api')

The declaration above assumes your `greeting-api` module is in the root of your
`modules` folder in your workspace. To build your project, run `../../gradlew
build` from your project's root directory. To deploy it, run `blade deploy`.

Now you've published an implementation of your `Greeting` interface. To finish,
you must still provide a way for your implementation to be invoked. Next, you'll
create a client that does just that.

## Creating a Client Module [](id=creating-a-client-module)

The service client that you'll create takes the form of a command which can be
invoked from Liferay's Felix Gogo shell. This command invokes the implementation
that you created in the previous section.

To create a client module, run a command like this one:

    blade create -t service -s java.lang.Object -p com.liferay.docs.greetingcommand -c GreetingCommand greeting-web

Running this command creates a project with this directory structure:

- `greeting-web`
    - `src`
        - `main`
            - `java`
                - `com/liferay/docs/greetingcommand`
                    - `GreetingCommand.java`
    - `bnd.bnd`
    - `build.gradle`

The last argument of the command you used to generate your client module was
`java.lang.Object`. This argument specifies the name of the class or interface
under which your component should be registered in Liferay's module framework.
Your `GreetingCommand` class does not need to be registered under a specific
name. Thus, the `java.lang.Object` value is really just a dummy value.

You need to modify the generated `GreetingCommand.java` file so that it obtains
an implementation of the `Greeting` interface from Liferay's module framework
and saves it as an instance variable. You also need to implement a `greet`
method that's invoked when the `greet` command is executed. Edit your
`GreetingCommand.java` file so that it looks like this:

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

The `@Component` annotation on your service implementation class marks your
class as a
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services) 
component. The `osgi.command.scope=greet` and `osgi.command.function=greet`
properties indicate that you're creating a command called `greet` in a scope
called `greet`. In other words, you're creating a command whose full name is
`greet:greet`. In the example above, your `GreetingCommand` class does not
implement an interface but the
[Declarative Services](http://wiki.osgi.org/wiki/Declarative_Services)
framework requires your class to be registered under an interface or class name.
Thus, you set `service = Object.class` in the `@Component` annotation as a dummy
setting to satisfy this requirement.

Since you specified `osgi.command.function=greet` in the `@Component`
annotation, your class must have a method named `greet`. Thus, you have this
method:

    public void greet(String name) {
        Greeting greeting = getGreeting();
        
        greeting.greet(name);
    }

But how does this `greet` method work? It obtains an instance of the `Greeting`
OSGi service and invokes its `greet` method, passing in the `name` parameter.
How is an instance of the `Greeting` OSGi service obtained? The
`GreetingCommand` class declares a private service bean, `_greeting` of type
`Greeting`. This is the type of the OSGi service registered by the
implementation module. The `GreetingCommand` class also provides public getter
and setter methods for the service bean. The setter method is decorated with the
`@Reference` annotation which tells Liferay's module framework to instantiate
the service bean with a service retrieved from the module framework's service
registry. The type `GreetingImpl` is used since it's the only class registered
under the `Greeting` interface. The `GreetingCommand` class's `greet` method
calls the public getter method of the private `_greeting` instance variable.

Note: The explanation above provides an example of how to consume OSGi services
that were published using components. To learn about consuming Liferay services,
please refer to the
[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
tutorial. 

Next, edit the generated `bnd.bnd` file so that it looks like this:

    -dsannotations: *
    Bundle-Name: Greeting Web
    Bundle-SymbolicName: greeting.web
    Bundle-Version: 1.0.0.${tstamp}
    Private-Package: com.liferay.docs.greetingcommand

Your client module also needs access to your API module in order to compile.
Open your implementation module's `build.gradle` file. Add the following
line to the top of the `dependencies {...}` block:

    compile project (':modules:greeting-api')

To build your project, run `../../gradlew build` from your project's root
directory. To deploy it, run `blade deploy`.

Once your service command module has been deployed, you can check that your
modules are deployed to your Liferay instance by accessing your Liferay's Felix
Gogo command shell. Run `blade sh lb` and verify that your modules are deployed
to Liferay. Then enter the `help` command and check that your command is
available. You should see an entry in the list like this:

    greet:greet

Try out the `greet` command by entering `blade sh greet` (or `greet:greet` if
you want use the full name of the command). You should see a result like this:

    >blade sh greet
    ...
    Caused by: java.lang.IllegalArgumentException: Cannot coerce greet() to any of [(String)]

Uh oh. What went wrong? That's right, your greet command needs a string
argument. Try invoking your `greet` command with a string argument. For example,
enter `greet Bob` or `greet "Joe Bloggs"`. You should see a result like this:

    >blade sh greet Bob
    Hello Bob!

or

    >blade sh greet "Joe Bloggs"
    Hello Joe Bloggs!

Great! You've learned how to create API, implementation, and client modules.
You've also learned how to create a specific kind of client module: the kind
that be invoked from Liferay's Felix Gogo shell using Blade.

## Related Topics [](id=related-topics)

[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)

[Using BLADE to Create Modules](/develop/tutorials/-/knowledge_base/7-0/using-blade-to-create-modules)

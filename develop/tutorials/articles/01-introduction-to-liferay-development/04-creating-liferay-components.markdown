# Creating Liferay Components [](id=creating-liferay-components)

As explained in the
[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/introduction-to-liferay-development)
tutorial, you need to understand two concepts in order to succeed as a Liferay 7
developer: *modules* and *components*. A module is the one and only type of
Liferay 7 plugin. A component is a Java class that's decorated with the
`@Component` annotation that implements a specified interface. Components are
registered in Liferay's module framework. For example, the
[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)
tutorial explains how to create a portlet application as a module. To do so, you
create a module project and create a component class that
implements the `Portlet` interface in that module.

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
the code referenced by this tutorial in complete projects on Github here: [https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/osgi/modules).
The specific projects referenced by this tutorial are
`com.liferay.docs.greetingapi`, `com.liferay.docs.greetingimpl`, and
`com.liferay.docs.greetingimpl`.

## Creating an API Module [](id=creating-an-api-module)

At the time of this writing, Blade Tools does not provide an API module
template. Nevertheless, it's easy enough to adapt the output of the service
module template to create an API module.

To create a module, run a command like this one:

    blade create service -c Greeting com.liferay.docs.greetingapi java.lang.Object

Note that the last argument is required. This argument specifies the interface
implemented by your component class. Since you're creating an API module, you're
not creating a component class at all. Thus, the value of the argument,
`java.lang.Object`, doesn't matter. You'll delete the `@Component` annotation in
which this argument is used.

Running the command above creates a project with the following directory
structure:

- `com.liferay.docs.greetingapi`
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
    Bundle-SymbolicName: com.liferay.docs.greetingapi
    Bundle-Version: 1.0.0.${tstamp}
    Export-Package: com.liferay.docs.greetingapi

You don't need the `dsannotations: *` line since you're not creating a component
in your module; you're only creating an interface. You also don't need the
following lines since you're not invoking any Liferay services:

    Import-Package: \
        com.liferay.portal.model;version='[7.0,7.1)',\
        com.liferay.portal.service;version='[7.0,7.1)',\
        *

You need the `Export-Package: com.liferay.docs.greetingapi` line since the
package with the `Greeting` interface must be made available to other modules.

To build your project, run `gradle build` from your project's root directory. To
deploy it, run `blade deploy build/libs/com.liferay.docs.greetingapi-1.0.jar`.

Since you've only published an interface, there's not much to test at this
point. Next, you'll implement the `Greeting` interface in a separate module.

## Creating an Implementation Module [](id=creating-an-implementation-module)

Now that you've created an API module, it's time to create a implementation
module. This module will contain a component class that implements the interface
provided by your API module. Implementation modules are sometimes called service
modules since they provide implementations of OSGi services. To create an
implementation module, run a command like this one:

    blade create service -c GreetingImpl com.liferay.docs.greetingimpl com.liferay.docs.greetingapi.Greeting

Running this command creates a project with the following directory structure:

- `com.liferay.docs.greetingimpl`
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

First, you need to modify the generated `GreetingImpl.java` file so that it
implements the `Greeting` interface. Edit your `GreetingImpl.java` file so that
it looks like this:

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

    compile project (':com.liferay.docs.greetingapi')

To build your project, run `gradle build` from your project's root directory. To
deploy it, run `blade deploy build/libs/com.liferay.docs.greetingimpl-1.0.jar`.

Now you've published an implementation of your `Greeting` interface. To finish,
you must still provide a way for your implementation to be invoked. Next, you'll
create a client that does just that.

## Creating a Client Module [](id=creating-a-client-module)

The service client that you'll create takes the form of a command which can be
invoked from Liferay's Felix Gogo shell. This command invokes the implementation
that you created in the previous section.

To create a client module, run a command like this one:

    blade create service -c GreetingCommand com.liferay.docs.greetingcommand java.lang.Object

Running this command creates a project with this directory structure:

- `com.liferay.docs.greetingcommand`
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
    Bundle-Name: Greeting Command
    Bundle-SymbolicName: com.liferay.docs.greetingcommand
    Bundle-Version: 1.0.0.${tstamp}
    Private-Package: com.liferay.docs.greetingcommand

Your client module also needs access to your API module in order to compile.
Open your implementation module's `build.gradle` file. Add the following
line to the top of the `dependencies {...}` block:

    compile project (':com.liferay.docs.greetingapi')

To build your project, run `gradle build` from your project's root directory. To
deploy it, run `blade deploy
build/libs/com.liferay.docs.greetingcommand-1.0.jar`.

Once your service command module has been deployed, open a new terminal and
access Liferay's Felix Gogo command shell by running `telnet localhost 11311`.
Enter the `help` command and check that your command is available. You should
see an entry in the list like this:

    greet:greet

Try out the `greet` command by entering `greet` (or `greet:greet` if you want
use the full name of the command). You should see a result like this:

    g! greet
    gogo: IllegalArgumentException: Cannot coerce greet() to any of [(String)]

Uh oh. What went wrong? That's right, your greet command needs a string
argument. Try invoking your `greet` command with a string argument. For example,
enter `greet Bob` or `greet "Joe Bloggs"`. You should see a result like this:

    g! greet Bob
    Hello Bob!

or

    g! greet "Joe Bloggs"
    Hello Joe Bloggs!

Great! You've learned how to create API, implementation, and client modules.
You've also learned how to create a specific kind of client module: the kind
that be invoked from Liferay's Felix Gogo shell.

## Related Topics [](id=related-topics)

[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/introduction-to-blade-tools)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)

[Using BLADE to Create Modules](/develop/tutorials/-/knowledge_base/7-0/using-blade-to-create-modules)

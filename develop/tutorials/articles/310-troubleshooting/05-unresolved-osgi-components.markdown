# Detecting Unresolved OSGi Components [](id=detecting-unresolved-osgi-components)

@product@ includes 
[Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) 
commands that come in handy when trying to diagnose a problem due to an 
unresolved OSGi component. The specific tools to use depend on the component 
framework of the unresolved component. Most @product@ components are developed 
using Declarative Services (DS), also known as SCR (Service Component Runtime). 
An exception to this is @product@'s Service Builder services, which are provided 
as Dependency Manager (DM) components. Both 
[Declarative Services](http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html) 
and 
[Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html) 
are Apache Felix projects. 

Start with DS, since most @product@ components, apart from Service Builder 
components, are DS components. Suppose one of your bundles includes a component 
that has a missing service reference. How can you detect this (other than 
observing an error from your bundle)? One way is to invoke the `scr:list` 
command in @product@'s Gogo shell. When you invoke this command, you’ll probably 
see a list of entries like this: 

    [ 507]   org.foo.bar  enabled
        [1701] [satisfied   ]
    [ 507]   org.foo.baz  enabled
        [1702] [satisfied   ]

The left-most (non-indented) lines provide information about the bundles that 
provide components, and the indented lines provided information about the 
components themselves. From left to right, each bundle line lists the bundle's 
ID, name, and status (enabled or disabled). Similarly, each component line lists 
the component's ID and status (satisfied or unsatisfied reference). If you 
invoke the `scr:list` command when some of your bundle's components have 
unsatisfied references, you'll see some entries like this: 

    [ 507]   org.foo.bar  enabled
        [1701] [unsatisfied reference]
    [ 507]   org.foo.baz  enabled
        [1702] [unsatisfied reference]

+$$$

**Tip:** You can use `grep` to find only the components with unsatisfied 
references: `scr:list | grep "unsatisfied reference"`. 

$$$

To view more detailed information about the component with the unsatisfied 
reference, use the command `scr:info <component ID>`. For example, the following 
command does this for the component ID `1701`: 

    g! scr:info 1701
    *** Bundle: org.foo.bar.command (507)
    Component Description:
        Name: org.foo.bar.command
        Implementation Class: org.foo.bar.command.FooBarCommand
        Default State: enabled
        Activation: delayed
        Configuration Policy: optional
        Activate Method: activate
        Deactivate Method: deactivate
        Modified Method: -
        Configuration Pid: [org.foo.bar.command]
        Services:
            org.foo.bar.command.DuckQuackCommand
        Service Scope: singleton
        Reference: Duck
            Interface Name: org.foo.bar.api.Foo
            Cardinality: 1..1
            Policy: static
            Policy option: reluctant
            Reference Scope: bundle
        Component Description Properties:
            osgi.command.function = foo
            osgi.command.scope = bar
        Component Configuration:
            ComponentId: 1701
            State: unsatisfied reference
            UnsatisfiedReference: Foo
            Target: null
            (no target services)
            Component Configuration Properties:
            component.id = 1701
            component.name = org.foo.bar.command
            osgi.command.function = foo
            osgi.command.scope = bar

In the `Component Configuration` section, `UnsatisfiedReference` lists the 
unsatisfied reference's type. This bundle's component isn't working because it's 
missing a `Foo` service. Now you can focus on why `Foo` is unavailable. The 
solution may be as simple as starting or deploying a bundle that provides the 
`Foo` service. 

## Service Builder Components [](id=service-builder-components)

@product@'s Service Builder modules are implemented using Spring. @product@ uses 
[the Apache Felix Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager.html) 
to manage Service Builder modules' OSGi components via the 
[Portal Spring Extender](https://github.com/liferay/com-liferay-portal/tree/master/portal-spring-extender) 
module. When developing a Liferay Service Builder application, you might 
encounter a situation where your application has an unresolved Spring-related 
OSGi component. This could occur, for example, if you update your application's 
database schema but forget to trigger an upgrade (for information on creating 
database upgrade processes for your @product@ applications, see the tutorial 
[Creating an Upgrade Process for Your App](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-an-upgrade-process-for-your-app)). 
To check your Service Builder modules for unresolved Spring components, you can 
use the Dependency Manager's `dm` Gogo shell command, which is explained here:  

- [Dependency Manager - Leveraging the shell](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html)

For example, to get diagnostic information about Service Builder components, use 
the `dependencymanager:dm` command. This command lists all of @product@'s 
Service Builder components, their required services, and whether each required 
service is available. 

## Related Topics [](id=related-topics)

[Calling Non-OSGi Code that Uses OSGi Services](/develop/tutorials/-/knowledge_base/7-0/calling-non-osgi-code-that-uses-osgi-services)

[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/develop/tutorials/-/knowledge_base/7-0/osgi-basics-for-liferay-development)

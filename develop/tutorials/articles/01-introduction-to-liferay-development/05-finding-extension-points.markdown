# Finding Extension Points [](id=finding-extension-points)

@product@ provides many features that help users accomplish their tasks.
Sometimes, however, you may find it necessary to [customize a built-in feature](/develop/tutorials/-/knowledge_base/7-1/customizing).
It's easy to **find** an area you want to customize, but it may seem like a
daunting task to figure out **how** to customize it. @product@ was developed for
easy customization, meaning it has many extension points you can use to add
your own flavor.

There's a process you can follow that makes finding an extension point a breeze.

1.  Locate the bundle (module) that provides the functionality you want to
    change.
3.  Find the components available in the module.
4.  Discover the extension points for the components you want to customize.

This tutorial demonstrates finding an extension point. It steps
through a simple example that locates an extension point for importing LDAP
users. The example includes using @product@'s
[Application Manager](/discover/portal/-/knowledge_base/7-1/managing-and-configuring-apps#using-the-app-manager)
and
[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).

## Locate the Related Module and Component [](id=locate-the-related-module-and-component)

First think of words that describe the application behavior you want to change.
The right keywords can help you easily track down the desired module and its
component. Consider the example for importing LDAP users. Some candidate
keywords for finding the component are *import*, *user*, and *LDAP*.

The easiest way to discover the module responsible for a particular Liferay feature is to use the Application Manager. The Application Manager lists app
suites and their included modules/components in an easy-to-use interface. It
even lists third party apps! You'll use your keywords to target the applicable
component.

1.  Open the App Manager by navigating to *Control Panel* &rarr; *Apps* &rarr;
    *App Manager*. The top level lists app suites, independent apps, and
    independent modules.

2.  Navigate the app suites, apps, and modules, or use Search to find components
    that might provide your desired extension point. Remember to check for your
    keywords in element names and descriptions. The keyword *LDAP* is found in
    the Liferay Foundation app suite's list of apps and features. Select the app
    suite.

    ![Figure 1: The Liferay Foundation app suite contains the LDAP Authentication application.](../../images/ldap-keyword-app-manager.png)

3.  Select the *LDAP* application from the app listing.

4.  The LDAP application only has one module, but typically, applications have 
    more than one module to inspect. Select the *Liferay Portal Security LDAP*
    module.

    ![Figure 2: The App Manager lists the module, package name, version, and status.](../../images/app-manager-breakdown.png)

5.  Search through the components, applying your keywords as a guide. Copy the
    component name you think best fits the functionality you want to customize;
    you'll inspect it later using the Gogo shell.

    ![Figure 3: The component name can be found using the App Manager.](../../images/usermodellistener-component.png)

    +$$$

    **Note:** When using the Gogo shell later, understand that it can take
    several tries to find the component you're looking for; Liferay's naming
    conventions facilitate finding extension points in a manageable time frame.

    $$$

Next, you'll use the Gogo shell to inspect the component for extension points.

## Finding Extension Points in a Component [](id=finding-extension-points-in-a-component)

Once you have the component that relates to the functionality you want to
extend, you can use the Gogo shell's Service Component Runtime (SCR) commands to
inspect it. You can execute SCR commands using
[Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-1/blade-cli) or in
[Gogo shell via telnet](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
This tutorial assumes you're using the Gogo shell via telnet.

Execute the following command:

    scr:info [COMPONENT_NAME]

For the LDAP example component you copied previously, the command would look
like this:

    scr:info com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener

The output includes a lot of information. For this exercise, you're interested
in services the component references. They are extension points. For
example, here's the reference for the service that imports LDAP users:

    ...
    Reference: LdapUserImporter
    Interface Name: com.liferay.portal.security.ldap.exportimport.LDAPUserImporter
    Cardinality: 1..1
    Policy: static
    Policy option: reluctant
    Reference Scope: bundle
    ...

The `LDAPUserImporter` is the extension point for customizing the LDAP user
import process! If none of the references satisfy what you're looking for,
search other components from the App Manager.

If you plan on overriding the referenced service, you'll need to understand the
reference's policy and policy option. If the policy is `static` and the policy
option is `reluctant`, binding a new higher ranking service in place of a bound
service requires reactivating the component or changing the target. For 
information on the other policies and policy options, visit the
[OSGi specification](https://osgi.org/download/r6/osgi.enterprise-6.0.0.pdf), in
particular, sections 112.3.5 and 112.3.6. If you want to learn how to
override a component's service reference, visit the tutorial
[here](/develop/tutorials/-/knowledge_base/7-1/overriding-service-references).

**Important** Not all Liferay extension points are available as referenced
services. Service references are common in Declarative Services (DS) components,
but extension points can be exposed in other ways too. Here's a brief list of
other potential extension points in @product@:

- Instances of `org.osgi.util.tracker.ServiceTracker<S, T>`
- Uses of Liferay's `Registry.getServiceTracker`
- Uses of Liferay's `ServiceTrackerMap` or `ServiceTrackerCollection`
- Any other component framework or whiteboard implementation (e.g., HTTP,
  JAX-RS) that supports tracking services; Blueprint, Apache Dependency Manager,
  etc. could also introduce extension points.

There you have it! In the App Manager, you used keywords to find the module
component whose behavior you wanted to change. Then you used Gogo shell to find
the component extension point for implementing your customization. 

---
header-id: finding-extension-points
---

# Finding Extension Points

[TOC levels=1-4]

@product@ provides many features that help users accomplish their tasks. 
Sometimes, however, you may find it necessary to [customize a built-in feature](/docs/7-2/customization/-/knowledge_base/c/liferay-customization).
It's easy to **find** an area you want to customize, but it may seem like a 
daunting task to figure out **how** to customize it. @product@ was developed for 
easy customization, meaning it has many extension points you can use to add 
your own flavor. 

There's a process you can follow that makes finding an extension point a breeze. 

1.  Locate the bundle (module) that provides the functionality you want to 
    change. 
2.  Find the components available in the module. 
3.  Discover the extension points for the components you want to customize. 

This article demonstrates finding an extension point. It steps through a simple 
example that locates an extension point for importing LDAP users. The example 
includes using @product@'s 
[Application Manager](/docs/7-2/user/-/knowledge_base/u/managing-and-configuring-apps#using-the-app-manager) 
and 
[Felix Gogo Shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell). 

## Locate the Related Module and Component

First think of words that describe the application behavior you want to change. 
The right keywords can help you easily track down the desired module and its 
component. Consider the example for importing LDAP users. Some candidate 
keywords for finding the component are *import*, *user*, *security, *and *LDAP*. 

The easiest way to discover the module responsible for a particular Liferay 
feature is to use the Application Manager. The Application Manager lists apps 
and their included modules/components in an easy-to-use interface. It even lists 
third party apps! You'll use your keywords to target the applicable component. 

1.  Open the App Manager by navigating to *Control Panel* &rarr; *Apps* &rarr; 
    *App Manager*. The top level lists independent apps and independent modules. 

2.  Navigate the apps and modules to find components that might provide your 
    desired extension point. Remember to check for your keywords in element 
    names. The keyword *security* is found in the Liferay CE Portal Security 
    app. Select it.

3.  The Security application has several modules to inspect. Select the 
    *Liferay Portal Security LDAP Implementation* module. 

    ![Figure 1: The module name can be found using the App Manager.](../../images/ldapimplementation-module.png)

4.  Search through the components, applying your keywords as a guide. Copy the 
    component name you think best fits the functionality you want to customize; 
    you'll inspect it later using the Gogo shell. 

    ![Figure 2: The component name can be found using the App Manager.](../../images/usermodellistener-component.png)

    | **Note:** When using the Gogo shell later, understand that it can take 
    | several tries to find the component you're looking for; Liferay's naming 
    | conventions facilitate finding extension points in a manageable time frame. 

Next, you'll use the Gogo shell to inspect the component for extension points. 

## Finding Extension Points in a Component

Once you have the component that relates to the functionality you want to 
extend, you can use the Gogo shell's Service Component Runtime (SCR) commands to 
inspect it. You can execute SCR commands using 
[Liferay Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) or in 
[Gogo shell](/docs/7-2/customization/-/knowledge_base/c/using-the-felix-gogo-shell). 
This article assumes you're using the Gogo shell. 

Execute the following command:

```bash
scr:info [COMPONENT_NAME]
```

For the LDAP example component you copied previously, the command would look 
like this:

```bash
scr:info com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener
```

The output includes a lot of information. For this exercise, you're interested 
in services the component references. They are extension points. For 
example, here's the reference for the service that imports LDAP users:

```bash
- _ldapUserImporter: 
  com.liferay.portal.security.ldap.exportimport.LDAPUserImporter 
  SATISFIED 
  1..1 
  dynamic+greedy
    target=(*) scope=bundle (1 binding):
    * Bound to [7764] from bundle 1754 (com.liferay.portal.security.ldap.impl:2.0.4)
```

The `LDAPUserImporter` is the extension point for customizing the LDAP user 
import process! If none of the references satisfy what you're looking for, 
search other components from the App Manager. 

If you plan on overriding the referenced service, you'll need to understand the 
reference's policy and policy option. In the example, the policy is `dynamic` 
and the policy option is `greedy`. If the policy is `static` and the policy 
option is `reluctant`, binding a new higher ranking service in place of a bound 
service requires reactivating the component or changing the target. For 
information on the other policies and policy options, visit the 
[OSGi specification](https://osgi.org/download/r6/osgi.enterprise-6.0.0.pdf), in 
particular, sections 112.3.5 and 112.3.6. See 
[Overriding OSGi Services](/docs/7-2/customization/-/knowledge_base/c/overriding-osgi-services) 
to learn how to override a component's service reference. 

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

# Overriding a Component's Service Reference

You want to change an app's behavior. One of the app component's referenced
services provides the behavior. You could deploy a higher ranking custom service
to replace it everywhere, but you're not convinced your custom service would be
right for the other apps that use the current service. Your objective: Replace
the service in the one app only, with no app downtime.

No problem. Configuration Admin lets you override services with swift surgical
precision. All you have to do is deploy your custom service and a configuration
file. 

Here are the steps:

1.  Find the component and service details
2.  Create a custom service
3.  Configure the component to use the custom service

Throughout the tutorial an example of replacing a component's LDAP user importer
service with a custom one illustrates the process. 

The first step is finding the component name and service reference's name and
interface. If you already have them, you can skip the next section. 

## Find the component and service reference

You must have the following information to create a custom service and configure
the component to use it.

-   *Component name*: Name of the component whose service to replace.
-   *Reference name*: Name of the component's service reference.
-   *Service interface*: Fully qualified name of the service interface.

Liferay Portal's
[Application Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps#using-the-app-manager)
and
[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) 
helps you find all of these values. You can start with using the Application
Manager (App Manager). 

Searching for the component using keywords or scanning app, module, and
component names and descriptions for keywords, can help you track down the
component. What words describe the application behavior you want to change? Use
those words to help you find the responsible component. 

Consider the LDAP example. The behavior to override is importing LDAP users.
Some candidate keywords for finding the component are "import", "user", and
"LDAP".

Search for the component and service: 

1.  Open the App Manager by selecting *Control Panel &rarr; Apps &rarr; App
    Manager*. The top level lists app suites, independent apps, and independent
    modules.

2.  Navigate the app suites, apps, and modules or use *Search* to find
    components that might provide the service you want to change. Hint: check 
    for your key words in element names and descriptions.

    ![Figure x: For the LDAP example, note the following component's name `com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener` includes the keywords "ldap" and "UserImport". The component is worth examining for the LDAP user input service.](../../images/overriding-bound-osgi-service-module.png)

3.  If a component is a possible match, copy its name for inspecting the
    component in Felix Gogo Shell (Gogo Shell).

    ![Figure x: Each module entry lists its components. This figure shows the matching component for the LDAP example.](../../images/overriding-bound-osgi-service-component.png)

4.  Gogo Shell's Service Component Runtime (SCR) commands help you inspect
    components. The Gogo Shell command `scr:info [componentName]` lists the
    component's attributes, including the services it uses. Execute the command
    using
    [Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli) or in
    [Gogo Shell via telnet](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 

    Here's an example of
    [using Blade CLI to execute the Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
    command:

        blade sh scr:info com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener 
        
    The resulting SCR information includes services the component references.
        
    For example, here's the reference for the service that imports LDAP users:

        ...
        Reference: LdapUserImporter
        Interface Name: com.liferay.portal.security.ldap.exportimport.LDAPUserImporter
        Cardinality: 1..1
        Policy: static
        Policy option: reluctant
        Reference Scope: bundle
        ...

    If none of the references match the service you're looking for, search other
    components. 

5.  Copy the following values from the command results. You'll use them in the
    custom service and service reference configuration you create later.

    -   *Component*
    -   *Reference*
    -   *Interface*

    Here are the values for LDAP example:
    
    -   *Component*:
        `com.liferay.portal.security.ldap.internal.model.listener.UserModelListener`
    -   *Reference*: `LdapUserImporter`
    -   *Interface*:
        `com.liferay.portal.security.ldap.exportimport.LDAPUserImporter`

+$$$

**Note**: All Declarative Services components are configurable via OSGi
Configuration Admin (even if they don't explicitly declare anything about
configuration).

A particularly useful aspect of this is that each ﻿⁠⁠⁠⁠`@Reference`﻿⁠⁠⁠⁠ has a
name property, either *explicitly* set in the reference annotation, or
*implicitly* derived from the name of the member on which the reference
annotation is used.

-   If no reference name property is used, and the `@Reference` is on a field,
    then the reference name is the field name.
-   If the reference is on a method, then heuristics derive the reference name.
    Method name prefixes such as `set`, `add`, and `put` are ignored. If a
    reference is on a method called
    ﻿⁠⁠⁠⁠`setSearchEngine(SearchEngine se)﻿⁠⁠⁠⁠`, for example, then the
    reference name is ﻿`⁠⁠⁠⁠SearchEngine`﻿⁠⁠⁠⁠.

$$$

Once you've found the referenced service you need, you can implement a custom
replacement for it. 

## Create a custom service

It's time to create a custom implementation of the service interface. Refer to
the appropriate [app, app suite](@app-ref@), and
[@product@ module](@platform-ref@/7.0-latest/javadocs/modules)
Javadoc for service interface details.

[Create a module](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/starting-module-development)
and implement your custom service in it. Use the `@Component` annotation to make
the service a Declarative Services component.

For example, the declaration for a `LdapUserImporter` service implementation
might look like this:

    @Component(
    service = LdapUserImporter.class
    )
    public class MyLdapUserImporter implements LdapUserImporter {

        ...
    } 

To register your custom service with the Liferay Portal's OSGi runtime
framework,
[deploy the module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module). 

## Configure the component to use the custom service

You're ready to replace the component's service reference with a reference to
the custom service you deployed. Liferay Portal's Configuraiton Admin lets you
use configuration files to swap in service references on the fly. 

1.  Create a configuration file named after the referencing component's fully
    qualified name. 

    Here's the configuration file name for the LDAP example:

        com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener.config
        
    Note: For deploying to Liferay DE 7.0 Fix Pack 8 or later, use file suffix
    `.config`. For earlier Liferay DE 7.0 Fix Packs or Liferay Portal 7.0 GA3 or
    earlier, use suffix `.cfg`. 

2.  In the configuration file, add a reference target entry that filters on your
    custom service. Follow this format for the entry:

       [reference].target=[filter]﻿⁠

    Replace `[reference]` with name of the reference you're overriding.

    Replace `[filter]` with whatever service properties best filter on your
    custom service component. 
    
    You can use an `objectClass` reference to filter on your custom service
    class. 

    A configuration entry for the LDAP example might look like this:
    
        LdapUserImporter.target=(objectClass=MyLdapUserImporter)

3.  Optionally, you can use a `cardinality.minimum` property to specify the
    number of services the reference can use. Here's the format:

        ﻿[reference].cardinality.minimum=[int]

4.  Deploy the configuration by coping the configuration file into the folder
    `[Liferay_Home]/osgi/configs`. 

Liferay Portal processes the configuration file and injects the service
reference, which in turn binds to your custom service class object!

Congratulations on overriding a Declarative Service component's service!

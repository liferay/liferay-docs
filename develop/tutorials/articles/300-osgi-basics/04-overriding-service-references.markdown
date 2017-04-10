# Overriding Service References [](id=overriding-service-references)

When there's an existing service that you want to customize or implement
differently, you can override the existing one. To do this, you create and
deploy a new, higher-ranked service implementation. But how do you replace a
component's service that's bound by a static and reluctant reference?
Reactivating the component would bind it to the new service but would render the
component temporarily inactive. To replace the service and keep the
component active, you can change the component's service reference to target
your new service.

Here are the steps for overriding a component's service reference:

1.  [Find the component and service details](#find-the-component-and-service-reference)
2.  [Create a custom service](#create-a-custom-service)
3.  [Configure the component to use the custom service](#configure-the-component-to-use-the-custom-service)

Throughout the tutorial, the example of replacing a component's LDAP user
importer service with a custom one is used. 

The first step is finding the name of the component, service reference, and
service interface. If you already have them, you can skip the next section. 

## Find the Component and Service Reference [](id=find-the-component-and-service-reference)

You must have the following information to create a custom service and configure
the component to use it.

-   *Component name*: Name of the component whose service to replace.
-   *Reference name*: Name of the component's service reference.
-   *Service interface*: Fully qualified name of the service interface.

You can [find the component](/develop/tutorials/-/knowledge_base/7-0/finding-extension-points#locate-the-related-module-and-component)
using @product@'s
[Application Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps#using-the-app-manager)
and find the service reference information using
[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 

Gogo Shell's Service Component Runtime (SCR) commands help you inspect
components. The Gogo Shell command `scr:info [componentName]` lists the
component's attributes, including the services it uses. Execute the command
using
[Liferay Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli) or in
[Gogo Shell via telnet](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell). 

Here's an example of executing `scr:info` command in a Gogo Shell telnet
session:

    scr:info com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener 
 
The resulting SCR information includes the component's service references.
 
For example, here's the reference for the service that imports LDAP users:

    ...
    Reference: LdapUserImporter
    Interface Name: com.liferay.portal.security.ldap.exportimport.LDAPUserImporter
    Cardinality: 1..1
    Policy: static
    Policy option: reluctant
    Reference Scope: bundle
    ...

Copy the following values from the command results. You'll use them in the
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

**Note**: OSGi Configuration Admin makes all Declarative Services components
configurable, even if they don't explicitly declare anything about
configuration. Each `@Reference` annotation in the source code has a name
property, either *explicitly* set in the annotation or *implicitly* derived from
the name of the member on which the annotation is used.

-   If no reference name property is used and the `@Reference` is on a field,
    then the reference name is the field name. 
-   If the reference is on a method, then heuristics derive the reference name.
    Method name prefixes such as `set`, `add`, and `put` are ignored. If a
    reference is on a method called`setSearchEngine(SearchEngine se)`, for
    example, then the reference name is `SearchEngine`. 

$$$

Once you've found the referenced service you need, you can implement a
replacement for it. If you've already created one, you can skip this section. 

## Create Your Service [](id=create-a-custom-service)

It's time to create yourimplementation of the service interface. Refer to
the appropriate [app, app suite](@app-ref@), and
[@product@ module](@platform-ref@/7.0-latest/javadocs/modules)
Javadoc for service interface details.

[Create a module](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/starting-module-development)
and implement your service in it. Use the `@Component` annotation to make
the service a Declarative Services component. 

For example, the declaration for an `LdapUserImporter` service implementation
might look like this:

    @Component(
    service = LdapUserImporter.class
    )
    public class MyLdapUserImporter implements LdapUserImporter {
        ...
    } 

To register your service with the @product@'s OSGi runtime framework,
[deploy the module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module). 

## Configure the Component to Use Your Service [](id=configure-the-component-to-use-the-custom-service)

You're ready to change the component's service reference to target your service.
@product@'s Configuration Admin lets you use configuration files to swap in
service references on the fly. 

1.  Create a configuration file named after the referencing component's fully
    qualified name. 

    Here's the configuration file name for the LDAP example:

        com.liferay.portal.security.ldap.internal.messaging.UserImportMessageListener.config
 
    Note: For deploying to Liferay DXP DE 7.0 Fix Pack 8 or later, use file
    suffix `.config`. For earlier Liferay DXP DE 7.0 Fix Packs or Liferay CE
    Portal 7.0 GA3 or earlier, use suffix `.cfg`. 

2.  In the configuration file, add a reference target entry that filters on your
    custom service. Follow this format for the entry:

        [reference].target=[filter]

    Replace `[reference]` with name of the reference you're overriding.

    Replace `[filter]` with service properties that filter on your 
    service component. 

    +$$$

    **Tip**: You can use an `objectClass` reference to filter on your 
    service class. 

    $$$

    A configuration entry for the LDAP example might look like this:
    
        LdapUserImporter.target=(objectClass=MyLdapUserImporter)

3.  Optionally, you can use a `cardinality.minimum` property to specify the
    number of services the reference can use. Here's the format:

        [reference].cardinality.minimum=[int]

4.  Deploy the configuration by coping the configuration file into the folder
    `[Liferay_Home]/osgi/configs`. 

@product@ processes the configuration file and injects the service reference,
which in turn binds to your custom service class object to the component! 

## Related Topics [](id=related-topics)

- [Finding Extension Points](/develop/tutorials/-/knowledge_base/7-0/finding-extension-points)

- [Using Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

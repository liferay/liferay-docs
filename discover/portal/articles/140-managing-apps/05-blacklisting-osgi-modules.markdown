# Blacklisting OSGi Modules and Components [](id=blacklisting-osgi-modules)

Blacklists are used for good and evil. An evil blacklist penalizes unfairly; a
good blacklist protects. @product@'s OSGi module blacklists and component
blacklists are files dropped in a folder to prevent particular modules from
installing and particular components from enabling, saving you from the
uninstalling and disabling them individually with the Application Manager,
Components list, or Gogo shell. 

Using the Control Panel's Bundle Blacklist screen and Component Blacklist
Configuration screen are the best ways to create blacklist files. 

+$$$

**Note**: Blacklisting OSGi modules appears in DXP Digital Enterprise 7.0 Fix 
Pack 30 and Liferay CE Portal 7.0 GA5.

$$$

+$$$

**Note**: Blacklisting OSGi components appears in DXP Digital Enterprise 7.0 Fix
Pack 40 and Liferay CE Portal 7.0 GA6.

$$$

## Blacklisting Modules [](id=blacklisting-steps)

Follow these steps to blacklist modules:

1.  In the Control Panel, navigate to *Configuration* &rarr; *System Settings*
    &rarr; *Bundle Blacklist*. 

2.  In the Bundle Blacklist screen, add the bundle symbolic names (see the table
    below) for the OSGi module JARs, LPKGs, or WARs to uninstall. Click on the
    *Save* button.
 
    ![Figure 1: This blacklist uninstalls the
    `com.liferay.docs.greeting.api` OSGi module, Liferay Marketplace LPKG, and
    `classic-theme` WAR.](../../images/bundle-blacklist-configuration.png)

3.  To export the blacklist to a file, click the Bundle Blacklist module's 
    actions button 
    (![Actions](../../images/icon-actions.png))
    and then click *Export*. The blacklist configuration file
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`
    downloads. Here are contents from an example file:

        blacklistBundleSymbolicNames=["com.liferay.docs.greeting.api","Liferay\ Marketplace","classic-theme"]

4.  Add the bundle symbolic names of any modules not already listed that you
    want to prevent Liferay from installing. 

    **Important**: Configuration values must not contain any extra spaces. Extra
    spaces can short-circuit lists or invalidate the configuration entry.

5.  Copy the configuration file into the 
    `[Liferay_Home]/osgi/configs` folder to deploy it. 

+$$$

**Note**: Blacklisting an LPKG uninstalls all of its internal modules. 

$$$

**Blacklist Bundle Symbolic Names**

Type |  &nbsp;Bundle Symbolic Name | 
:--------- | :--------------|
 Module JAR | `Bundle-SymbolicName` in `bnd.bnd` or `MANIFEST.MF` file |
 LPKG | LPKG file name without the `.lpkg` extension |
 WAR | Servlet context name in `liferay-plugin-package.properties` file or the WAR file name (minus `.war`), if there is no servlet context name property | 

@product@ removes installed modules on the blacklist. Blacklisted modules can't
be installed. The @product@ log reports each module uninstallation.

## Reinstalling Blacklisted Modules [](id=reinstalling-blacklisted-modules)

To reinstall and permit installation of blacklisted modules, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`.

2.  Remove the symbolic names of the modules, LPKGs, or WARs from the
    `blacklistBundleSymbolicNames` list and save the file. 

To reinstall *all* the blacklisted modules execute one of these options:

-   Remove the configuration file.
-   Uninstall the module `com.liferay.portal.bundle.blacklist` using the
    [Application Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps#using-the-app-manager)
    or
    [Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).

+$$$

**Note**: To temporarily reinstall a module that's been blacklisted, you can 
remove its symbolic name from the Bundle Blacklist module in System Settings and
click the *Update* button. If you want the module to install on subsequent
Liferay server startup, make sure to remove the module's symbolic name from any
existing module blacklist configuration file in the
`[Liferay_Home]/osgi/configs` folder.

$$$

The @product@ log reports each module installation. 

## Blacklisting Components [](id=blacklisting-components)

Follow these steps to blacklist components:

1.  In the Control Panel, navigate to *Configuration* &rarr; *System Settings*
    &rarr; *Component Blacklist Configuration*.

2.  Add the names of components to disable and click on the *Update* button. 

    ![Figure 2: This blacklist disables the components 
    `com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth` and 
    `com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet`.](../../images/component-blacklist-configuration.png)

3.  To export the blacklist, click on the Component Blacklist Configuration 
    module's actions button 
    (![Actions](../../images/icon-actions.png))
    and then click *Export*. The blacklist configuration file
    `com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`
    downloads. Here are contents from an example file:

        blacklistComponentNames=["com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth","com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet "]

4.  Add the names of any components not already listed (e.g., components of 
    modules not yet installed) that you want to prevent Liferay from activating.

    **Important**: Configuration values must not contain any extra spaces. Extra
    spaces can short-circuit lists or invalidate the configuration entry.

5.  Copy the configuration file into the `[Liferay_Home]/osgi/configs` folder to
    deploy it. 

## Re-enabling Blacklisted Components [](id=re-enabling-blacklisted-components)

To re-enable and permit enabling of blacklisted components, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`.

2.  Remove the names of the components from the `blacklistComponentNames` list
    and save the file. 

To enable *all* the blacklisted components remove the configuration file.

+$$$

**Note**: To temporarily reactivate a component that's been blacklisted, you can
remove its name from the Component Blacklist Configuration module in System
Settings and click the *Update* button. If you want the component to activate on
subsequent Liferay server startup, make sure to remove the component's name from
any existing component blacklist configuration file in the
`[Liferay_Home]/osgi/configs` folder.

$$$

Now you know how to use the blacklisting features for modules and components.

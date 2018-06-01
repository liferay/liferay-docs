# Blacklisting OSGi Modules and Components [](id=blacklisting-osgi-modules-and-components)

Blacklists are used for good and evil. An evil blacklist penalizes unfairly; a
good blacklist protects. @product@'s OSGi module and component blacklists are 
files that prevent particular modules from installing and particular components 
from enabling. This saves you the trouble of uninstalling and disabling them 
individually with the Application Manager, Components list, or Gogo shell. 

## Blacklisting Modules [](id=blacklisting-steps)

@product@ removes any installed modules on the blacklist. Blacklisted modules 
therefore can't be installed. The log reports each module uninstallation. 

Follow these steps to blacklist modules:

1.  In the Control Panel, navigate to *Configuration* &rarr; *System Settings*
    &rarr; *Module Container*. 

2.  In the Bundle Blacklist screen, add the bundle symbolic names (see the table
    below) for the OSGi module JARs, LPKGs, or WARs to uninstall. Click the 
    *Save* button when you're finished.
 
    ![Figure 1: This blacklist uninstalls the `com.liferay.docs.greeting.api` OSGi module, Liferay Marketplace LPKG, and `classic-theme` WAR.](../../images/bundle-blacklist-configuration.png)

3.  To export the blacklist, click its Actions button 
    (![Actions](../../images/icon-actions.png)) and then click *Export*. The 
    blacklist configuration file then downloads 
    (`com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`). 
    Here are contents from an example file:

        blacklistBundleSymbolicNames=["com.liferay.docs.greeting.api","Liferay\ Marketplace","classic-theme"]

4.  Add the bundle symbolic names of any modules not already listed that you
    want to prevent from installing. 

    **Important**: Configuration values can't contain extra spaces. Extra spaces 
    can short-circuit lists or invalidate the configuration entry. 

5.  To deploy the configuration file, copy it into the folder 
    `[Liferay_Home]/osgi/configs`. The Liferay Home folder is typically the app 
    server's parent folder. 

+$$$

**Note**: Blacklisting an LPKG uninstalls all of its internal modules. 

$$$

**Blacklist Bundle Symbolic Names**

Type |  &nbsp;Bundle Symbolic Name | 
:--------- | :--------------|
 Module JAR | `Bundle-SymbolicName` in `bnd.bnd` or `MANIFEST.MF` file |
 LPKG | `title` in `liferay-marketplace.properties` file |
 WAR | Servlet context name in `liferay-plugin-package.properties` file or the WAR file name (minus `.war`), if there is no servlet context name property | 

## Reinstalling Blacklisted Modules [](id=reinstalling-blacklisted-modules)

To reinstall and permit installation of blacklisted modules, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`.

2.  Remove the symbolic names of the modules, LPKGs, or WARs from the
    `blacklistBundleSymbolicNames` list and save the file. 

To reinstall *all* the blacklisted modules execute one of these options:

-   Remove the configuration file.
-   Uninstall the module `com.liferay.portal.bundle.blacklist` using the
    [Application Manager](/discover/portal/-/knowledge_base/7-1/managing-and-configuring-apps#using-the-app-manager)
    or
    [Felix Gogo Shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).

+$$$

**Note**: To temporarily reinstall a module that's been blacklisted, you can 
remove its symbolic name from the Bundle Blacklist module in System Settings and
click the *Update* button. If you want the module to install on subsequent 
server startup, make sure to remove the module's symbolic name from any existing 
module blacklist configuration file in the `[Liferay_Home]/osgi/configs` folder. 

$$$

The log reports each module installation. 

## Blacklisting Components [](id=blacklisting-components)

Follow these steps to blacklist components:

1.  In the Control Panel, navigate to *Configuration* &rarr; *System Settings*
    &rarr; *Module Container*. 

2.  In the Component Blacklist Configuration screen, add the names of components 
    to disable and click the *Save* button. 

    ![Figure 2: This blacklist disables the components `com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth` and `com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet`.](../../images/component-blacklist-configuration.png)

3.  To export the blacklist, click on the Component Blacklist Configuration 
    module's Actions button 
    (![Actions](../../images/icon-actions.png))
    and then click *Export*. The blacklist configuration file then downloads 
    (`com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`). 
    Here are contents from an example file:

        blacklistComponentNames=["com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth","com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet "]

4.  Add the names of any components not already listed (e.g., components of 
    modules not yet installed) that you want to prevent from activating.

    **Important**: Configuration values can't contain extra spaces. Extra spaces 
    can short-circuit lists or invalidate the configuration entry.

5.  To deploy the configuration file, copy it into the folder 
    `[Liferay_Home]/osgi/configs`. The Liferay Home folder is typically the app 
    server's parent folder. 

## Re-enabling Blacklisted Components [](id=re-enabling-blacklisted-components)

To re-enable and permit enabling of blacklisted components, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`.

2.  Remove the names of the components from the `blacklistComponentNames` list
    and save the file. 

To enable *all* the blacklisted components, remove the configuration file.

+$$$

**Note**: To temporarily reactivate a blacklisted component, remove its name 
from the Component Blacklist Configuration module in System Settings and click 
*Update*. If you want the component to activate on subsequent server startup, 
make sure to remove the component's name from any existing component blacklist 
configuration file in the `[Liferay_Home]/osgi/configs` folder.

$$$

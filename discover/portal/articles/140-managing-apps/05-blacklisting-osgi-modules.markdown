# Blacklisting OSGi Modules and Components [](id=blacklisting-osgi-modules)

Blacklists are used for good and evil. An evil blacklist penalizes unfairly; a
good blacklist protects. @product@'s OSGi module blacklists and component
blacklists are files dropped in a folder to prevent particular modules from
installing and particular components from enabling, saving you from the
uninstalling and disabling them individually with the Application Manager or
Gogo shell. 

+$$$

**Note**: Blacklisting OSGi modules appears in DXP Digital Enterprise Fix 7.0
Pack 30 and Liferay CE Portal 7.0 GA5.

$$$

+$$$

**Note**: Blacklisting OSGi components appears in DXP Digital Enterprise Fix 7.0
Pack 40 and Liferay CE Portal 7.0 GA6.

$$$

## Blacklisting Modules [](id=blacklisting-steps)

Follow these steps to blacklist modules:

1.  Create a configuration file for the component 
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration`:

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

2.  In the configuration file, add `blacklistBundleSymbolicNames=` followed by
    the bundle symbolic names (see the table below) for the OSGi module JARs,
    LPKGs, or WARs to uninstall or to keep from installing. The following
    blacklist uninstalls the `com.liferay.docs.greeting.api` OSGi module,
    Liferay CE Chat LPKG, and `classic-theme` WAR:

        blacklistBundleSymbolicNames=["com.liferay.docs.greeting.api","Liferay\ Marketplace","classic-theme"]
 
    **Important**: Configuration values must not contain any extra spaces. Extra
    spaces can short-circuit lists or invalidate the configuration entry.

3.  Copy the configuration file into the 
    `[Liferay_Home]/osgi/configs` folder to deploy it. 

+$$$

**Note**: Blacklisting an LPKG uninstalls all of its internal modules. 

$$$

**Blacklist Bundle Symbolic Names**

Type |  &nbsp;Bundle Symbolic Name | 
:--------- | :--------------|
 Module JAR | `Bundle-SymbolicName` in `bnd.bnd` or `MANIFEST.MF` file |
 LPKG | `title` in `liferay-marketplace.properties` file |
 WAR | Servlet context name in `liferay-plugin-package.properties` file or the WAR file name (minus `.war`), if there is no servlet context name property | 

@product@ removes installed modules on the blacklist. Blacklisted modules can't
be installed. The @product@ log reports each module uninstallation.

## Reinstalling Blacklisted Modules [](id=reinstalling-blacklisted-modules)

To reinstall and permit installation of blacklisted modules, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config`.

2.  Remove the symbolic names of the modules, LPKGs, or WARs from the
    `blacklistBundleSymbolicNames` list.

To reinstall *all* the blacklisted modules execute one of these options:

-   Remove the configuration file.
-   Uninstall the module `com.liferay.portal.bundle.blacklist` using the
    [Application Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps#using-the-app-manager)
    or
    [Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).

The @product@ log reports each module installation. 

## Blacklisting Components [](id=blacklisting-components)

Follow these steps to blacklist components:

1.  Create the configuration file 
    `com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`.

2.  In the configuration file, add `blacklistComponentNames=` followed by the
    names of components to disable or to keep from enabling. The following
    blacklist disables the components 
    `com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth` and 
    `com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet`:

        blacklistComponentNames=["com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth","com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet "]
 
    **Important**: Configuration values must not contain any extra spaces. Extra
    spaces can short-circuit lists or invalidate the configuration entry.

3.  Copy the configuration file into the `[Liferay_Home]/osgi/configs` folder to
    deploy it. 

## Re-enabling Blacklisted Components [](id=re-enabling-blacklisted-components)

To re-enable and permit enabling of blacklisted components, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`.

2.  Remove the names of the components from the `blacklistComponentNames` list.

To enable *all* the blacklisted components remove the configuration file.

Now you know how to use the blacklisting features for modules and components.

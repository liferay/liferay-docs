# Blacklisting OSGi Modules

In society, the term "blacklist" carries a negative connotation. People on
blacklists are usually mistreated. Regarding OSGi modules, a blacklist is a
convenience. It's a file that lets you uninstall one or more specific modules.
Rather than uninstalling them one at a time using the Application Manager or
Apache Felix Gogo Shell, you can uninstall them simultaneously by dropping a
blacklist file into a folder. 

+$$$

**Note**: The blacklisting capability was introduced in DXP Digital Enterprise
Fix 7.0 Pack 30 and Liferay CE Portal 7.0 GA5.

$$$  

Follow these steps to blacklist a module:

1.  Create a configuration file for the component 
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration`,
    if you haven't already created one.

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

2.  In the configuration file, add `blacklistBundleSymbolicNames=` followed by
    the symbolic names of OSGi modules to uninstall or to keep from installing.
    The modules can be from LPKG, JAR, WAR, or WAR wrapper files. 

3.  To deploy the configuration file, copy it into the 
    `[Liferay_Home]/osgi/configs` folder. 

Installed modules on the blacklist are uninstalled. Blacklisted modules that
have not yet been installed remain uninstalled. The @product@ log reports each
module uninstallation. 

To reinstall specific blacklisted modules, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration`.

2.  Remove the symbolic names of the modules from the
    `blacklistBundleSymbolicNames` list.

To reinstall *all* the blacklisted modules execute one of these options:

-   Remove the configuration file.
-   Uninstall the module `com.liferay.portal.bundle.blacklist` using the
    [Application Manager](/discover/portal/-/knowledge_base/7-0/managing-and-configuring-apps#using-the-app-manager)
    or
    [Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell).

The @product@ log reports each module installation. 

Now you know how to use the blacklisting feature to uninstall and reinstall
modules.

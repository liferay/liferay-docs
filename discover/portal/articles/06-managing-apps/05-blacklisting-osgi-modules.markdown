# Blacklisting OSGi Modules

In society, the term "blacklist" typically carries a negative connotation.
People on blacklists are usually treated unfavorably. Regarding OSGi modules, a
blacklist is a convenience. It's a file that lets you uninstall multiple
modules. Rather than uninstalling them one at a time using the Application
Manager or Apache Felix Gogo Shell, you can uninstall them by dropping the
blacklist file into a directory. 

+$$$

**Note**: The blacklisting capability was introduced in DXP Digital Enterprise
Fix 7.0 Pack 30 and Liferay CE Portal 7.0 GA5.

$$$  

Follow these steps to blacklist a module:

1.  Create a configuration file for the component 
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration`, if you haven't already created one.

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config

2.  In the configuration file, add `blacklistBundleSymbolicNames=` followed by
    the symbolic names of OSGi modules to uninstall or to keep from installing.
    The modules can be from LPKG, JAR, WAR, or WAR wrapper files. 

3.  To deploy the configuration file, copy it into the 
    `[Liferay_Home]/osgi/configs` folder. 

Blacklisted modules currently installed on the Portal instance are uninstalled.
Blacklisted modules that have not yet been installed remain uninstalled. The
@product@ log reports each module uninstallation. 

When you're ready to reinstall the modules, you have two options: 

1.  In the configuration file, remove the module's symbolic name from the list 
    of `blacklistBundleSymbolicNames` values.

2.  Use the Application Manager or Felix Gogo Shell to uninstall the blacklist 
    module `com.liferay.portal.bundle.blacklist`.

The @product@ log reports each module installation. 

Now you know how helpful blacklisting is for uninstalling multiple modules.

# Blacklisting OSGi Modules [](id=blacklisting-osgi-modules)

Blacklists are used for good and evil. An evil blacklist penalizes unfairly; a
good blacklist protects. @product@'s OSGi module blacklist is a file that's
dropped in a folder to prevent particular modules from installing, saving you
from the uninstalling them individually with the Application Manager or Gogo
shell. 

+$$$

**Note**: Blacklisting appears in DXP Digital Enterprise Fix 7.0 Pack 30 and
Liferay CE Portal 7.0 GA5.

$$$

Follow these steps to blacklist a module:

1.  Create a configuration file for the component 
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration`:

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.cfg

2.  In the configuration file, add `blacklistBundleSymbolicNames=` followed by
    the symbolic names of OSGi modules to uninstall or to keep from installing.
    The modules can be from LPKG, JAR, WAR, or WAR wrapper files. (Be very careful that there are no spaces or anything after any line.)

3.  Copy the configuration file into the 
    `[Liferay_Home]/osgi/configs` folder to deploy it. 

@product@ removes installed modules on the blacklist. Blacklisted modules can't
be installed. The @product@ log reports each module uninstallation. 

To reinstall blacklisted modules, follow these steps:

1.  Open the configuration file
    `com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.cfg`.

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

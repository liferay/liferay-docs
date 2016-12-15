# Installing patches on the @product-ver@ WAR [](id=installing-patches-on-the-liferay-de-war)

Because of your app server choice, you may not be able to patch the @product@
instance that's installed, because the files aren't available on the file
system. Instead, you must patch the @product@ WAR file and then re-deploy it.
This tutorial shows you how to do that. 

## Prerequisites [](id=prerequisites)

Download the necessary artifacts from the 
[Customer Portal:](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise)

- @product@ WAR (liferay-dxp-digital-enterprise-[version].war)
- OSGi dependencies (liferay-dxp-digital-enterprise-dependencies-[version].zip)
- Additional dependencies (liferay-dxp-digital-enterprise-osgi-[version].zip)
- Latest Patching Tool

## How to Install a Fix Pack on the @product@ WAR [](id=how-to-install-a-fix-pack-on-the-liferay-war)

1.  Create a folder and unzip the dependency artifacts and the Patching Tool
    into it. Content of this folder should look like this:	

    ![Figure 1: Use a simple folder structure for patching.](../../../../images-dxp/patch-war-file-folder-structure.png)

2.  Create the default profile configuration file in the Patching Tool's home
    folder: `liferay-de-home/patching-tool/default.properties`. The contents
    should look like this: 
	
        patching.mode=binary
        war.path=/liferay-de-home/liferay-dxp-digital-enterprise-[version].war
        global.lib.path=/liferay-de-home/liferay-dxp-digital-enterprise-dependencies-[version]/
        liferay.home=/liferay-de-home/

    If you have different OSGi folder structure, you can specify them as
    described in the documentation [Patching Tool Advanced
    Configuration](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/patching-tool-advanced-configuration): 
	
        module.framework.core.path=d:/liferay-de-home/osgi/core
        module.framework.marketplace.path=d:/liferay-de-home/osgi/marketplace
        module.framework.modules.path=d:/liferay-de-home/osgi/modules
        module.framework.portal.path=d:/liferay-de-home/osgi/portal
        module.framework.static.path=d:/liferay-de-home/osgi/static	

3.  Download the patch (fix pack or hotfix) that you'd like to install and place
    it to the Patching Tool's `patches` folder

4.  Now the Patching Tool info command shows the following output:

        /liferay-de-home/patching-tool>patching-tool.bat info

        Loading product and patch information...
        Product information:
          * installation type: binary
          * build number: 7010
          * service pack version:
            - available SP version: Not available
            - installable SP version: 1
          * patching-tool version: 2.0.5
          * time: 2016-12-05 13:29Z
          * plugins: no plugins detected

        Currently installed patches: -

        Available patches: de-8-7010

        Detailed patch list:
          [ I] de-8-7010 :: Currently not installed; Will be installed.

5.  Install the downloaded patch. 

        /liferay-de-home/patching-tool>patching-tool.sh install
        One patch is ready to be installed. Applying de-8...
        Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
        Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
        The installation was successful. One patch is installed on the system.

Great! You have successfully patched the artifacts, and they are ready to be
deployed on any supported Application Server.

## Related Topics [](id=related-topics)

[Patching Tool Advanced Configuration](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/patching-tool-advanced-configuration)

[Deploying @product@](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/deploying-liferay-dxp)

[Installing @product@ on WebLogic 12c R2](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/installing-liferay-dxp-on-weblogic-12c-r2)

[Installing @product@ on WebSphere 8.5.5](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/installing-liferay-dxp-on-websphere-8-5-5)


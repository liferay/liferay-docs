#Installing patches on the Liferay DE WAR

## Prerequisites
Download the necessary artifacts from the [Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/digital-enterprise)

- Digital Enterprise WAR (liferay-dxp-digital-enterprise-7.0-sp1-20161027112321352.war)
- OSGi dependencies (liferay-dxp-digital-enterprise-dependencies-7.0-sp1-20161027112321352.zip)
- Additional dependencies (liferay-dxp-digital-enterprise-osgi-7.0-sp1-20161031224040737.zip)
- Latest Patching Tool

## How to install fix pack on the Liferay WAR

1) Unzip the dependency artifacts and the Patching Tool into a common folder,
called e.g. `d:/liferay-de-home/`

Content of this folder should look like this:	

~~~~
1. liferay-dxp-digital-enterprise-dependencies-7.0-sp1\ <- Unzipped DE Dependencies
2. osgi\ <- Unzipped OSGi dependencies
3. patching-tool\ <- Unzipped Patching Tool
4. liferay-dxp-digital-enterprise-7.0.war <- Liferay DE WAR file
~~~~


2) Create the default profile configuration file in the Patching Tool's home
folder
* Create a new file in the Patching Tool's home folder: `D:/liferay-de-home/patching-tool/default.properties`
	
~~~~
patching.mode=binary
war.path=D:/liferay-de-home/liferay-dxp-digital-enterprise-7.0-sp1-20161027112321352.war
global.lib.path=D:/liferay-de-home/liferay-dxp-digital-enterprise-dependencies-7.0-sp1/
liferay.home=d:/liferay-de-home/
~~~~

If you have different OSGi directory structure, you can specify them as 
described in the documentation: [Patching Tool Advanced Configuration](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/patching-tool-advanced-configuration)
	
~~~~
module.framework.core.path=d:/liferay-de-home/osgi/core
module.framework.marketplace.path=d:/liferay-de-home/osgi/marketplace
module.framework.modules.path=d:/liferay-de-home/osgi/modules
module.framework.portal.path=d:/liferay-de-home/osgi/portal
module.framework.static.path=d:/liferay-de-home/osgi/static	
~~~~


3) Download the patch (fix pack or hotfix) that you'd like to install and place 
it to the Patching Tool's `patches` folder

4) Now the Patching Tool info command shows the following output:

~~~~
d:/liferay-de-home/patching-tool>patching-tool.bat info

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
~~~~

5) Install the downloaded patch

~~~~
d:/liferay-de-home/patching-tool>patching-tool.bat install
One patch is ready to be installed. Applying de-8...
Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
The installation was successful. One patch is installed on the system.
~~~~

+$$$

**Checkpoint:** Great, you have successfully patched the artifacts and they are ready to be deployed on any supported Application Server.

$$$

* [Deploying Liferay DXP](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/deploying-liferay-dxp)
* [Installing Liferay DXP on WebLogic 12c R2](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/installing-liferay-dxp-on-weblogic-12c-r2)
* [Installing Liferay DXP on WebSphere 8.5.5](https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/installing-liferay-dxp-on-websphere-8-5-5)

### Reference: 

* https://customer.liferay.com/documentation/7.0/deploy/-/official_documentation/deployment/patching-tool-advanced-configuration
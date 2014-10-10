# Reference [](id=reference)

This page describes and provides access to Liferay's reference documentation.
You can refer to this documentation as you configure portals, invoke Liferay
services, leverage tag libraries, and package apps for Liferay Marketplace. 

## APIs

Liferay describes the Liferay Portal Java API, JavaScript tag libraries, and JSF
tags. 

- [Public Java API](http://docs.liferay.com/portal/6.2/javadocs/)
describes Liferay packages and classes for you to use in your Liferay plugins. 

- [Internal Java API](http://docs.liferay.com/portal/6.2/javadocs-all/)
includes all the classes in the Liferay core, and is primarily intended for
understanding Liferay or contributing to the Liferay core. Many of the classes
referenced in this documentation should not be referenced directly from Liferay
plugins. Please refer to the
[public API documentation](http://docs.liferay.com/portal/6.2/javadocs/) when
developing either of these. 

- [TagLibs](http://docs.liferay.com/portal/6.2/taglibs/) describes tag libraries
for [AlloyUI](http://alloyui.com/), Liferay UI, Liferay themes, Liferay
portlets, Liferay security, and standard portlets.  

- [Liferay Faces VDL Docs](http://docs.liferay.com/faces/3.1/vdldoc/) provides
the latest version of Liferay Faces JSF tag documentation in View Declaration
Language (VDL) format. VDL docs for all versions of Liferay Faces are available
at <http://docs.liferay.com/faces/>. 

## Definitions

Liferay provides definitions for its configuration files and deployment
descriptors. 

- [DTDs](http://docs.liferay.com/portal/6.2/definitions/) describes the XML
files used in configuring Liferay apps, Liferay plugins, and Liferay Portal. 

- [liferay-plugin-package_6_2_0.properties](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
defines properties used for configuring deployment of Liferay plugins. It's
properties describe the general characteristics of the Liferay plugin, declare
its resources, and specify its security related parameters.  

## Configuration Defaults

Liferay sets default values in particular property files.

- [portal.properties](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html)
describes the configuration defaults for Liferay Portal. 

- [system.properties](http://docs.liferay.com/portal/6.2/propertiesdoc/system.properties.html)
describes the system configuration defaults for Liferay Portal. 

## Liferay's Deprecation Policy

Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major and minor releases of
Liferay. A change in the first or second digits of consecutive Liferay releases
indicates a major or minor release, respectively. For example, the release of
Liferay Portal 6.2.0 after 5.2.0 was a major release; whereas the release of
6.2.0 after 6.1.30 was a minor release. Major and minor releases can have API
deprecations. 

APIs should not be deprecated between maintenance releases. Maintenance releases
are signified by a change in the third digit of the release number. For example,
the release of Liferay Portal 6.1.30 after 6.1.20 was a maintenance release and
therefore should have no API deprecations. 

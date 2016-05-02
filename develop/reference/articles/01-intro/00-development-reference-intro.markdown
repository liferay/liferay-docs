# Development Reference [](id=development-reference)

Here you'll find reference documentation for Liferay Portal CE, Liferay Screens,
Liferay Faces, and technologies related to you as a third-party developer.

The different types of reference docs you'll find in this section of the Liferay
Developer Network are as follows: 

- Descriptions of Java and JavaScript APIs, CSS, tags and tag libraries, and
XML DTDs
- Writeups on the latest Screenlets for Liferay Screens
- Breaking changes
- Cheat sheets and tips on
    - Plugin anatomy
    - Design patterns
    - Tools
    - Adapting to new APIs

Liferay's reference docs are your fingertips.

## Java APIs [](id=java-apis)

**Javadoc (HTML)** for Liferay Portal 7.0 CE modules at the time of the
latest GA release:

- [portal-kernel](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/index.html):
for developing applications on the Liferay platform.

- [util-bridges](https://docs.liferay.com/portal/7.0/javadocs/util-bridges/index.html):
for using various non-proprietary computing languages, frameworks, and utilities
on the Liferay platform.

- [util-java](https://docs.liferay.com/portal/7.0/javadocs/util-java/index.html): for
using various Java-related frameworks and utilities on the Liferay platform.

- [util-slf4j](https://docs.liferay.com/portal/7.0/javadocs/util-slf4j/index.html): for
using the Simple Logging Facade for Java (SLF4J).

- [portal-impl](https://docs.liferay.com/portal/7.0/javadocs/portal-impl/index.html):
refer to this only if you are an advanced Liferay developer that needs a deeper
understanding of Liferay Portal's implementation in order to contribute to it.

As an alternative to online Javadoc,
[download](https://www.liferay.com/downloads) the reference doc ZIP file and
browse locally.

For help finding specific Liferay API modules, see [Liferay API
Modules](/develop/reference/-/knowledge_base/7-0/finding-liferay-api-modules).

<!--
Module APIs (Javadoc JARs): Javadoc for *all* versions of CE modules are
available in JARs here. 
-->

## Taglibs [](id=taglibs)

[**Taglib doc (HTML)**](https://docs.liferay.com/portal/7.0/taglibs/) for Liferay Portal 7.0
CE tag libraries at the time of the latest GA release. As an alternative to online taglib docs, you can
[download](https://www.liferay.com/downloads) the reference doc ZIP file and
browse locally.

**Taglib doc (JARs)** for *all* versions of the CE tag libraries are listed here:

- [com.liferay.alloy.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.alloy.taglib/)

- [com.liferay.application.list.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.application.list.taglib/)

- [com.liferay.asset.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.asset.taglib/)

- [com.liferay.dynamic.data.mapping.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.dynamic.data.mapping.taglib/)

- [com.liferay.flags.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.flags.taglib/)

- [com.liferay.frontend.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.frontend.taglib/)

- [com.liferay.item.selector.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.item.selector.taglib/)

- [com.liferay.layout.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.layout.taglib/)

- [com.liferay.map.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.map.taglib/)

- [com.liferay.product.navigation.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.product.navigation.taglib/)

- [com.liferay.site.navigation.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.site.navigation.taglib/)

- [com.liferay.staging.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.staging.taglib/)

- [com.liferay.trash.taglib](https://repository.liferay.com/nexus/content/repositories/liferay-releases-ce/com/liferay/com.liferay.trash.taglib/)

[**Faces Taglibs**](https://docs.liferay.com/faces/3.2/vdldoc/): for the latest
version of Liferay Faces JSF tag docs in View Declaration Language (VDL) format.
VDL docs for all versions of Liferay Faces are available
[here](http://docs.liferay.com/faces/).

## JavaScript and CSS [](id=javascript-and-css)

[**Lexicon**](http://liferay.github.io/lexicon/): The Liferay Experience
Language is a system for building applications in and outside of Liferay,
designed to be fluid and extensible, as well as provide a consistent and
documented API.

[**Bootstrap**](http://getbootstrap.com/): The base CSS library onto which
Lexicon is added. Liferay uses Bootstrap natively and all of its CSS classes are
available within portlets, templates, and themes.

[**AlloyUI**](http://alloyui.com): Liferay includes AlloyUI and all of its
JavaScript APIs are available within portlets, templates and themes.

## Descriptor Definitions [](id=descriptor-definitions)

[**DTDs**](http://docs.liferay.com/portal/7.0/definitions/): Describes the XML
files used in configuring Liferay apps, Liferay plugins, and Liferay Portal.

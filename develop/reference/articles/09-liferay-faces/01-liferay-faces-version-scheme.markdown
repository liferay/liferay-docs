# Liferay Faces Version Scheme [](id=liferay-faces-version-scheme)

In this article, you'll explore the Liferay Faces versioning scheme, and what
each component of a version means. Once you have the versioning scheme mastered,
you can view several example configurations.

## Liferay Faces Alloy [](id=liferay-faces-alloy)

Provides a suite of JSF components that utilize [AlloyUI](http://alloyui.com/).

|Branch|Example Artifact|AlloyUI|JSF API|Additional Info|
|------|----------------|-------|-------|---------------|
|[master (3.0.x)](https://github.com/liferay/liferay-faces-alloy/tree/master)|com.liferay.faces.alloy-3.0.0.jar|3.0.x|2.2+|*AlloyUI 3.0.x is the version that comes bundled with Liferay Portal 7.0.*|
|[2.0.x](https://github.com/liferay/liferay-faces-alloy/tree/2.0.x)|com.liferay.faces.alloy-2.0.0.jar|2.0.x|2.1+|*AlloyUI 2.0.x is the version that comes bundled with Liferay Portal 6.2.*|
|[1.0.x](https://github.com/liferay/liferay-faces-alloy/tree/1.0.x)|com.liferay.faces.alloy-1.0.0.jar|2.0.x|1.2|*AlloyUI 2.0.x is the version that comes bundled with Liferay Portal 6.2.*|

## Liferay Faces Bridge [](id=liferay-faces-bridge)

Provides the ability to deploy JSF web applications as portlets within
[Apache Pluto](https://portals.apache.org/pluto/), the reference implementation
for JSR 286 (Portlet 2.0) and JSR 362 (Portlet 3.0).

|Branch|Example Artifacts|Portlet API|JSF API|JCP Specification|Additional Info|
|------|-----------------|:-----------:|:-------:|:-----------------:|---------------|
|API: [master (5.0.x)](https://github.com/liferay/liferay-faces-bridge-api/tree/master)<br/>IMPL: [5.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/master)|com.liferay.faces.bridge.api-5.0.0.jar<br/>com.liferay.faces.bridge.impl-5.0.0.jar|3.0|2.2|[JSR 378](https://www.jcp.org/en/jsr/detail?id=378)|*The Expert Group began work in September 2015 and the Specification is currently under development.*|
|API: [4.0.x](https://github.com/liferay/liferay-faces-bridge-api/tree/4.0.x)<br/>IMPL: [4.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/4.0.x)|com.liferay.faces.bridge.api-4.0.0.jar<br/>com.liferay.faces.bridge.impl-4.0.0.jar|2.0|2.2|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329)|*Includes non-standard bridge extensions for JSF 2.2.*|
|API: [3.0.x](https://github.com/liferay/liferay-faces-bridge-api/tree/3.0.x)<br/>IMPL: [3.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/3.0.x)|com.liferay.faces.bridge.api-3.0.0.jar<br/>com.liferay.faces.bridge.impl-3.0.0.jar|2.0|2.1|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329)|*Includes non-standard bridge extensions for JSF 2.1.*|
|API: [2.0.x](https://github.com/liferay/liferay-faces-bridge-api/tree/2.0.x)<br/>IMPL: [2.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/2.0.x)|com.liferay.faces.bridge.api-2.0.0.jar<br/>com.liferay.faces.bridge.impl-2.0.0.jar|2.0|1.2|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329) (MR1)|*Includes support for Maintenance Release 1 (MR1).*|
|1.0.x|N/A|1.0|1.2|[JSR 301](https://www.jcp.org/en/jsr/detail?id=301)|*N/A (Not Applicable) since Liferay Faces Bridge has never implemented JSR 301.*|

## Liferay Faces Bridge Ext [](id=liferay-faces-bridge-ext)

Extension to Liferay Faces Bridge that provides compatibility with
[Liferay Portal](http://www.liferay.com/community/liferay-projects/liferay-portal/overview)
and also takes advantage of Liferay-specific features such as friendly URLs.

|Branch           |Example Artifact                  |&nbsp;&nbsp;Liferay Portal API&nbsp;&nbsp;|&nbsp;&nbsp;Bridge API&nbsp;&nbsp;|&nbsp;&nbsp;Portlet API&nbsp;&nbsp;|JSF API|
|-----------------|------------------------------------|:--------------:|:----------:|:-----------:|:-------:|
|*6.0.x (planned)*|*com.liferay.faces.bridge.ext-6.0.0.jar*|*Future*         |*5.0.x*     |*3.0*        |*2.2*    |
|[master (5.0.x)](https://github.com/liferay/liferay-faces-bridge-ext/tree/master)|com.liferay.faces.bridge.ext-5.0.0.jar|7.0.x|4.0.x|2.0|2.2|
|[4.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/4.0.x)|RESERVED|N/A|N/A|N/A|N/A|
|[3.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/3.0.x)|com.liferay.faces.bridge.ext-3.0.0.jar|6.2.x|4.0.x|2.0|2.2|
|[2.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/2.0.x)|com.liferay.faces.bridge.ext-2.0.0.jar|6.2.x|3.0.x|2.0|2.1|
|[1.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/1.0.x)|com.liferay.faces.bridge.ext-1.0.0.jar|6.2.x|2.0.x|2.0|1.2|

## Liferay Faces Metal [](id=liferay-faces-metal)

Provides a suite of JSF components that utilize
[metal.js](http://http://metaljs.com/).

|Branch|Example Artifact|&nbsp;&nbsp;MetalJS&nbsp;&nbsp;|&nbsp;&nbsp;JSF API&nbsp;&nbsp;|Additional Info|
|------|----------------|:---------:|:-------:|---------------|
|[master (1.0.x)](https://github.com/liferay/liferay-faces-metal/tree/master)|com.liferay.faces.metal-1.0.0.jar|1.0.x|2.2+|*Metal.js 1.0.x is the version that comes bundled with Liferay Portal 7.0.*|

## Liferay Faces Portal [](id=liferay-faces-portal)

Provides a suite of JSF components that are based on the JSP tags provided by
[Liferay Portal](http://www.liferay.com/community/liferay-projects/liferay-portal/overview).

|Branch|Example Artifact|Liferay Portal API&nbsp;&nbsp;|&nbsp;&nbsp;JSF API|
|------|----------------|:------------------:|:-------:|
|[master (3.0.x)](https://github.com/liferay/liferay-faces-portal/tree/master)|com.liferay.faces.portal-3.0.0.jar|7.0.x|2.2+|
|[2.0.x](https://github.com/liferay/liferay-faces-portal/tree/2.0.x)|com.liferay.faces.portal-2.0.0.jar|6.2.x|2.1+|
|[1.0.x](https://github.com/liferay/liferay-faces-portal/tree/1.0.x)|com.liferay.faces.portal-1.0.0.jar|6.2.x|1.2|

## Liferay Faces Util [](id=liferay-faces-util)

Library that contains general purpose JSF utilities to support many of the
sub-projects that comprise Liferay Faces.

|Branch|Example Artifact|&nbsp;&nbsp;JSF API|
|------|----------------|:-------:|
|[master (3.0.x)](https://github.com/liferay/liferay-faces-util/tree/master)|com.liferay.faces.util-3.0.0.jar|2.2|
|[2.0.x](https://github.com/liferay/liferay-faces-util/tree/2.0.x)|com.liferay.faces.util-2.0.0.jar|2.1|
|[1.0.x](https://github.com/liferay/liferay-faces-util/tree/1.0.x)|com.liferay.faces.util-1.0.0.jar|1.2|

Now that you know all about the Liferay Faces versioning scheme, you may be
curious as to how these components interact with each other. Refer to the
following figure to view the Liferay Faces dependency diagram.

![Figure 1: The Liferay Faces dependency diagram helps visualize how components interact and depend on each other.](../../images/liferay-faces-dependency-diagram.png)

Next, you can view some example configurations to see the new versioning scheme
in action.

## Example Configurations [](id=example-configurations)

### Liferay Portal 7.0: AlloyUI 3.0 / Metal 1.0 / Portlet 2.0 / JSF 2.2 [](id=liferay-portal-7-0-alloyui-3-0--metal-1-0--portlet-2-0--jsf-2-2)

    <dependencies>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.alloy</artifactId>
            <version>3.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>4.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.ext</artifactId>
            <version>5.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.metal</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.portal</artifactId>
            <version>3.0.0</version>
        </dependency>
    </dependencies>

### Apache Pluto 3.0: Portlet 3.0 / JSF 2.2 [](id=apache-pluto-3-0-portlet-3-0--jsf-2-2)

    <dependencies>
        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>com.liferay.faces.bridge.impl</artifactId>
            <version>5.0.0</version>
        </dependency>
    </dependencies>

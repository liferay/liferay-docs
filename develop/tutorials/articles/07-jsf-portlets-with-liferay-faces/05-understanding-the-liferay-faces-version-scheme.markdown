# Understanding the Liferay Faces Version Scheme [](id=understanding-the-liferay-faces-version-scheme)

In this tutorial, you'll explore the Liferay Faces versioning scheme, and what
each component of a version means. For Liferay Portal 6.2, there are two
supported version schemes. The first "older" version scheme is supported from
previous Liferay versions up to Liferay Portal 6.2. This version scheme is
supported by all Liferay Faces releases up to Liferay Faces GA6.

The newer version scheme is supported for Liferay 6.2 and all future releases of
Liferay Portal. Any Liferay Faces release after Liferay Faces GA6 will follow
the newer version scheme.

You'll learn about the version scheme supported for Liferay Faces releases GA6
and prior first.

## Liferay Faces Version Scheme for Releases Up to Liferay Faces GA6 [](id=liferay-faces-version-scheme-for-releases-up-to-liferay-faces-ga6)

[Liferay
Faces](http://www.liferay.com/community/liferay-projects/liferay-faces/overview)
follows a `Major1.Major2.Minor-Type` versioning scheme: 

`Major1`: 

- `1` = Portlet 1.0 Bridge for JSF 1.2 (JSR 301) *(Note that Liferay Faces
does not support Portlet 1.0)* 
- `2` = Portlet 2.0 Bridge for JSF 1.2 (JSR 329) 
- `3` = Portlet 2.0 Bridge for JSF 2.1 (JSR TBA) 
- `4` = Portlet 2.0 Bridge for JSF 2.2 (JSR TBA) 

`Major2`: 

- `0-legacy` = Liferay `5.2` 
- `0` = Liferay `6.0` 
- `1` = Liferay `6.1` 
- `2` = Liferay `6.2` 

`Minor`: 

- May contain bug fixes, improvements, and new features. 

`Type`: 

- GA (General Availability) 
- RC (Release Candidate) 
- BETA (Beta Quality) 
- ALPHA (Alpha Quality) 

*Examples*: 

- `3.1.0-ga1`: First GA release for JSF 2.0/2.1 for use with Liferay 6.1.x 
- `3.1.1-ga2`: Second GA release for JSF 2.0/2.1 for use with Liferay 6.1.x 
 
+$$$

**Note:** Some permutations of this versioning scheme are not supported, see
table below for specific information on supported versions of JSF and Liferay
Portal. 

$$$

The following table displays the Liferay Faces version and its compatible
Liferay Portal and JSF versions: 

| Liferay Faces Version&nbsp;&nbsp;&nbsp; | JSF Version (`Major1`)&nbsp;&nbsp;&nbsp; | Liferay Portal Version (`Major2`) |
|-----------------------|------------------------|-----------------------------------|
| `2.1.x`               | `1.2`                  | `6.1`                             |
| `3.0.x-legacy`        | `2.1`                  | `5.2`                             |
| `3.0.x`               | `2.1`                  | `6.0`                             |
| `3.1.x`               | `2.1`                  | `6.1`                             | 
| `3.2.x`               | `2.1`                  | `6.2`                             |
| `4.2.x` (master)      | `2.2`                  | `6.2`                             |

While Liferay Faces Bridge is theoretically compatible with any portal that
implements the Portlet 2.0 standard, it has been carefully tested for use with
Liferay Portal versions 5.2, 6.0, 6.1, and 6.2 and has several optimizations
that provide increased performance on Liferay. 

If you've developed portlets that use the
[PortletFaces](http://www.portletfaces.org/en) Bridge, you'll need to migrate
them to Liferay Faces in order to deploy them using the Liferay Faces
Bridge--don't worry, it's very straightforward. 

<!-- You can learn how to do this in the Liferay Faces Migrating tutorials
starting with the [Migrating BridgeRequestAttributeListener to Liferay Faces]()
tutorial. -->

With the knowledge of the Liferay Faces versioning scheme under your belt, you
can now recognize what each version supports. Next, you'll learn about the
version scheme supported by Liferay Faces after the GA6 release.

## Liferay Faces Version Scheme for Releases After Liferay Faces GA6 [](id=liferay-faces-version-scheme-for-releases-after-liferay-faces-ga6)

In this section, you'll learn about the versioning scheme for each component of
Liferay Faces for releases following Liferay Faces GA6. Once you have the
versioning scheme mastered, you can view several example configurations.

### Liferay Faces Alloy [](id=liferay-faces-alloy)

*Provides a suite of JSF components that utilize [AlloyUI](http://alloyui.com/).*

|Branch|Example Artifact|AlloyUI|JSF API|Additional Info|
|------|----------------|-------|-------|---------------|
|[master (3.0.x)](https://github.com/liferay/liferay-faces-alloy/tree/master)|liferay-faces-alloy-3.0.0.jar|3.0.x|2.1+|*AlloyUI 3.0.x is the version that comes bundled with Liferay Portal 7.0.*|
|[2.0.x](https://github.com/liferay/liferay-faces-alloy/tree/2.0.x)|liferay-faces-alloy-2.0.0.jar|2.0.x|2.1+|*AlloyUI 2.0.x is the version that comes bundled with Liferay Portal 6.2.*|
|[1.0.x](https://github.com/liferay/liferay-faces-alloy/tree/1.0.x)|liferay-faces-alloy-1.0.0.jar|2.0.x|1.2|*AlloyUI 2.0.x is the version that comes bundled with Liferay Portal 6.2.*|

### Liferay Faces Bridge [](id=liferay-faces-bridge)

*Provides the ability to deploy JSF web applications as portlets within [Apache Pluto](https://portals.apache.org/pluto/), the reference implementation for JSR 286 (Portlet 2.0) and JSR 362 (Portlet 3.0).*

|Branch|Example Artifacts|Portlet API|JSF API|JCP Specification|Additional Info|
|------|-----------------|:-----------:|:-------:|:-----------------:|---------------|
|API: [master (5.0.x)](https://github.com/liferay/liferay-faces-bridge-api/tree/master)<br/>IMPL: [5.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/master)|liferay-faces-bridge-api-5.0.0.jar<br/>liferay-faces-bridge-impl-5.0.0.jar|3.0|2.2|[JSR 378](https://www.jcp.org/en/jsr/detail?id=378)|*The Expert Group began work in September 2015 and the Specification is currently under development.*|
|API: [4.0.x](https://github.com/liferay/liferay-faces-bridge-api/tree/4.0.x)<br/>IMPL: [4.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/4.0.x)|liferay-faces-bridge-api-4.0.0.jar<br/>liferay-faces-bridge-impl-4.0.0.jar|2.0|2.2|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329)|*Includes non-standard bridge extensions for JSF 2.2.*|
|API: [3.0.x](https://github.com/liferay/liferay-faces-bridge-api/tree/3.0.x)<br/>IMPL: [3.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/3.0.x)|liferay-faces-bridge-api-3.0.0.jar<br/>liferay-faces-bridge-impl-3.0.0.jar|2.0|2.1|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329)|*Includes non-standard bridge extensions for JSF 2.1.*|
|API: [2.0.x](https://github.com/liferay/liferay-faces-bridge-api/tree/2.0.x)<br/>IMPL: [2.0.x](https://github.com/liferay/liferay-faces-bridge-impl/tree/2.0.x)|liferay-faces-bridge-api-2.0.0.jar<br/>liferay-faces-bridge-impl-2.0.0.jar|2.0|1.2|[JSR 329](https://www.jcp.org/en/jsr/detail?id=329) (MR1)|*Includes support for Maintenance Release 1 (MR1).*|
|1.0.x|N/A|1.0|1.2|[JSR 301](https://www.jcp.org/en/jsr/detail?id=301)|*N/A (Not Applicable) since Liferay Faces Bridge has never implemented JSR 301.*|

### Liferay Faces Bridge Ext [](id=liferay-faces-bridge-ext)

*Extension to Liferay Faces Bridge that provides compatibility with [Liferay Portal](http://www.liferay.com/community/liferay-projects/liferay-portal/overview) and also takes advantage of Liferay-specific features such as friendly URLs.*

|Branch           |Example Artifact                  |&nbsp;&nbsp;Liferay Portal API&nbsp;&nbsp;|&nbsp;&nbsp;Bridge API&nbsp;&nbsp;|&nbsp;&nbsp;Portlet API&nbsp;&nbsp;|JSF API|
|-----------------|------------------------------------|:--------------:|:----------:|:-----------:|:-------:|
|*6.0.x (planned)*|*liferay-faces-bridge-ext-6.0.0.jar*|*7.1.x*         |*5.0.x*     |*3.0*        |*2.2*    |
|[master (5.0.x)](https://github.com/liferay/liferay-faces-bridge-ext/tree/master)|liferay-faces-bridge-ext-5.0.0.jar|7.0.x|4.0.x|2.0|2.2|
|[4.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/4.0.x)|liferay-faces-bridge-ext-4.0.0.jar|7.0.x|3.0.x|2.0|2.1|
|[3.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/3.0.x)|liferay-faces-bridge-ext-3.0.0.jar|6.2.x|4.0.x|2.0|2.2|
|[2.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/2.0.x)|liferay-faces-bridge-ext-2.0.0.jar|6.2.x|3.0.x|2.0|2.1|
|[1.0.x](https://github.com/liferay/liferay-faces-bridge-ext/tree/1.0.x)|liferay-faces-bridge-ext-1.0.0.jar|6.2.x|2.0.x|2.0|1.2|

### Liferay Faces Crystal [](id=liferay-faces-crystal)

*Provides a suite of JSF components that utilize [metal.js and CrystalUI](http://http://metaljs.com/).*

|Branch|Example Artifact|&nbsp;&nbsp;CrystalUI&nbsp;&nbsp;|&nbsp;&nbsp;JSF API&nbsp;&nbsp;|Additional Info|
|------|----------------|:---------:|:-------:|---------------|
|[master (1.0.x)](https://github.com/liferay/liferay-faces-crystal/tree/master)|liferay-faces-crystal-1.0.0.jar|1.0.x|2.1+|*CrystalUI 1.0.x is the version that comes bundled with Liferay Portal 7.0.*|

### Liferay Faces Portal [](id=liferay-faces-portal)

*Provides a suite of JSF components that are based on the JSP tags provided by [Liferay Portal](http://www.liferay.com/community/liferay-projects/liferay-portal/overview).*

|Branch|Example Artifact|Liferay Portal API&nbsp;&nbsp;|&nbsp;&nbsp;JSF API|
|------|----------------|:------------------:|:-------:|
|[master (3.0.x)](https://github.com/liferay/liferay-faces-portal/tree/master)|liferay-faces-portal-3.0.0.jar|7.0.x|2.1+|
|[2.0.x](https://github.com/liferay/liferay-faces-portal/tree/2.0.x)|liferay-faces-portal-2.0.0.jar|6.2.x|2.1+|
|[1.0.x](https://github.com/liferay/liferay-faces-portal/tree/1.0.x)|liferay-faces-portal-1.0.0.jar|6.2.x|1.2|

### Liferay Faces Util [](id=liferay-faces-util)

*Library that contains general purpose JSF utilities to support many of the sub-projects that comprise Liferay Faces.*

|Branch|Example Artifact|&nbsp;&nbsp;JSF API|
|------|----------------|:-------:|
|[master (3.0.x)](https://github.com/liferay/liferay-faces-util/tree/master)|liferay-faces-util-3.0.0.jar|2.2|
|[2.0.x](https://github.com/liferay/liferay-faces-util/tree/2.0.x)|liferay-faces-util-2.0.0.jar|2.1|
|[1.0.x](https://github.com/liferay/liferay-faces-util/tree/1.0.x)|liferay-faces-util-1.0.0.jar|1.2|

Now that you know all about the Liferay Faces versioning scheme, you may be
curious as to how these components interact with each other. Refer to the
following figure to view the Liferay Faces dependency diagram.

![Figure 1: The Liferay Faces dependency diagram helps visualize how components interact and depend on each other.](../../images/liferay-faces-dependency-diagram.png)

Next, you can view some example configurations to see the new versioning scheme
in action.

## Example Configurations [](id=example-configurations)

### Liferay Portal 7.0: AlloyUI 3.0 / CrystalUI 1.0 / Portlet 2.0 / JSF 2.2 [](id=liferay-portal-7-0-alloyui-3-0--crystalui-1-0--portlet-2-0--jsf-2-2)

	<dependencies>
		<dependency>
			<groupId>com.liferay.faces.alloy</groupId>
			<artifactId>liferay-faces-alloy</artifactId>
			<version>3.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-impl</artifactId>
			<version>4.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-ext</artifactId>
			<version>2.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.crystal</groupId>
			<artifactId>liferay-faces-crystal</artifactId>
			<version>1.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.portal</groupId>
			<artifactId>liferay-faces-portal</artifactId>
			<version>2.0.0</version>
		</dependency>
	</dependencies>

### Liferay Portal 6.2: AlloyUI 2.0 / Portlet 2.0 / JSF 2.2 [](id=liferay-portal-6-2-alloyui-2-0--portlet-2-0--jsf-2-2)

	<dependencies>
		<dependency>
			<groupId>com.liferay.faces.alloy</groupId>
			<artifactId>liferay-faces-alloy</artifactId>
			<version>2.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-impl</artifactId>
			<version>4.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-ext</artifactId>
			<version>1.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.portal</groupId>
			<artifactId>liferay-faces-portal</artifactId>
			<version>1.0.0</version>
		</dependency>
	</dependencies>

### Liferay Portal 6.2: AlloyUI 2.0 / Portlet 2.0 / JSF 2.1 [](id=liferay-portal-6-2-alloyui-2-0--portlet-2-0--jsf-2-1)

	<dependencies>
		<dependency>
			<groupId>com.liferay.faces.alloy</groupId>
			<artifactId>liferay-faces-alloy</artifactId>
			<version>2.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-impl</artifactId>
			<version>3.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-ext</artifactId>
			<version>1.0.0</version>
		</dependency>
		<dependency>
			<groupId>com.liferay.faces.portal</groupId>
			<artifactId>liferay-faces-portal</artifactId>
			<version>1.0.0</version>
		</dependency>
	</dependencies>

### Apache Pluto 3.0: Portlet 3.0 / JSF 2.2 [](id=apache-pluto-3-0-portlet-3-0--jsf-2-2)

	<dependencies>
		<dependency>
			<groupId>com.liferay.faces.bridge</groupId>
			<artifactId>liferay-faces-bridge-impl</artifactId>
			<version>5.0.0</version>
		</dependency>
	</dependencies>

## Related Topics [](id=related-topics)

[Creating and Deploying JSF Portlets](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/creating-and-deploying-jsf-portlets)

[Developing Liferay Faces Portlets with Maven](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/developing-liferay-faces-portlets-with-maven)

[Using Portlet Preferences with JSF](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/using-portlet-preferences-with-jsf)

[Liferay Faces Alloy UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-alloy-ui-components)

[Liferay Faces Bridge UI Components](/develop/tutorials-jsf-test/-/knowledge_base/tutorials-test-jsf/liferay-faces-bridge-ui-components)

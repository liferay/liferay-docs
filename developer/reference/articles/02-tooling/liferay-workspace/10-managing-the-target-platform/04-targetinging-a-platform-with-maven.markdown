---
header-id: targeting-a-platform-with-maven
---

# Targeting a Platform with Maven

[TOC levels=1-4]

Although a Maven-based Liferay Workspace does not offer a configurable property
to set the target platform, you can still leverage the Target Platform framework
by adding a few dependencies to your project.

1.  Open your workspace's root `pom.xml` file and add the following
    dependencies:

    ```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.liferay.portal</groupId>
                <artifactId>release.portal.bom</artifactId>
                <version>7.2.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.liferay.portal</groupId>
                <artifactId>release.portal.bom.compile.only</artifactId>
                <version>7.2.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>com.liferay.portal</groupId>
                <artifactId>release.portal.bom.third.party</artifactId>
                <version>7.2.0</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    ```

    These dependencies are described below:

    `com.liferay.ce.portal.bom`: provides all the artifacts included in
    @product@.

    `com.liferay.ce.portal.compile.only`: provides artifacts that are not
    included in @product@, but are necessary to reference during the build
    (e.g., `org.osgi.core`).

    `release.portal.bom.third.party`: provides all third party artifacts that
    make up the Liferay Portal bundle.

    Liferay DXP users must replace the artifact names and versions:

    - `release.portal.bom` &rarr; `release.dxp.bom`
    - `release.portal.bom.compile.only` &rarr; `release.dxp.bom.compile.only`
    - `release.portal.bom.third.party`
    - `7.2.0` &rarr; `7.2.10`

2.  Go through the remaining POMs in your workspace and remove `<version>` tags
    for all Liferay-specific artifacts. These versions are now being provided
    by the Target Platform framework.

Great! You can now target a platform in your Maven-based workspace.

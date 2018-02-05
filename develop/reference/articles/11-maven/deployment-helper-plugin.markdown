# Deployment Helper Plugin [](id=deployment-helper-plugin)

The Deployment Helper plugin lets you create a cluster deployable WAR from your
OSGi artifacts.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.deployment.helper</artifactId>
                <version>1.0.4</version>
                <configuration>
                </configuration>
            </plugin>
        ...
        </plugins>
    </build>

You can view an example POM containing the Deployment Helper configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/deployment-helper/samples/pom.xml).

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`deployment-helper:build` | Builds a WAR which contains one or more files that are copied once the WAR is deployed.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`deploymentFileNames` | `String` | `null` | The files or directories to include in the WAR and copy once the WAR is deployed. If a directory is added to this collection, all the JAR files contained in the directory are included in the WAR.
`deploymentPath` | `String` | `null` | The directory to which the included files are copied.
`outputFileName` | `String` | `null` | The WAR file to build.

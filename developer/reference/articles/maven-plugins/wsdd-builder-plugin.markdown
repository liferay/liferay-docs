# WSDD Builder Plugin [](id=wsdd-builder-plugin)

The WSDD Builder plugin lets you generate the
[Apache Axis](http://axis.apache.org/axis/) Web Service Deployment Descriptor
(WSDD) files from a
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/what-is-service-builder)
`service.xml` file.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.wsdd.builder</artifactId>
                <version>1.0.10</version>
                <configuration>
                </configuration>
            </plugin>
        ...
        </plugins>
    </build>

You can view an example POM containing the WSDD Builder configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/portal-tools-wsdd-builder/samples/pom.xml).

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`wsdd-builder:build` | Runs the Liferay WSDD Builder to generate the WSDD files.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`classPath` | `String` | `null` | The classpath that the Liferay WSDD Builder uses to generate WSDD files.
`inputFileName` | `String` | `"service.xml"` | The file from which to generate the WSDD files.
`outputDirName` | `String` | `"src"` | The directory where the `*_deploy.wsdd` and `*_undeploy.wsdd` files are generated.
`serverConfigFileName` | `String` | `"server-config.wsdd"` | The file to generate.
`serviceNamespace` | `String` | `"Plugin"` | The namespace for the WSDD Service.

# Bundle Support Plugin

The Bundle Support plugin lets you use
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace)
as a Maven project. For more information on how a Maven Workspace works and the
features it provides, see the
[Maven Workspace](/develop/tutorials/-/knowledge_base/7-0/maven-workspace)
tutorial.

## Usage

To use the plugin, include it in your `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.bundle.support</artifactId>
                <version>3.2.1</version>
                <executions>
                    <execution>
                        <id>clean</id>
                        <goals>
                            <goal>clean</goal>
                        </goals>
                        <phase>clean</phase>
                        <configuration>
                        </configuration>
                    </execution>
                    <execution>
                        <id>deploy</id>
                        <goals>
                            <goal>deploy</goal>
                        </goals>
                        <phase>pre-integration-test</phase>
                        <configuration>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            ...
        </plugins>
    </build>

## Goals

The plugin adds five Maven goals to your project:

Name | Description
---- | -----------
`bundle-support:clean` |  
`bundle-support:create-token` | 
`bundle-support:deploy` | 
`bundle-support:dist` | 
`bundle-support:init` | 

## clean Goal's Available Parameters

You can set the following parameters in the `clean` execution's
`<configuration>` section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`_liferayHome` | `String` | `bundles` |  The directory where your @product@ instance resides.
`fileName` | `String` | `${project.artifactId}.${project.packaging}` | 

## create-token Goal's Available Parameters

You can change the default parameter values of the `create-token` goal by
creating an `<execution>` section for it. Within the `<execution>` section,
define a `<configuration>` section. For example,

    <execution>
        <id>create-token</id>
        <goals>
            <goal>create-token</goal>
        </goals>
        <configuration>
        </configuration>
    </execution>

The `create-token` goal's parameters are listed below.

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`emailAddress` | `String` |  |  
`force` | `boolean` |  | 
`password` | `String` |  | 
`passwordFile` | `File` |  | 
`tokenFile` | `File` | `${user.home}/.liferay/token` | 
`tokenUrl` | `URL` | `https://cdn.lfrs.sl/releases.liferay.com/portal/7.0.4-ga5/liferay-ce-portal-tomcat-7.0-ga5-20171018150113838.zip` | 

## deploy Goal's Available Parameters

You can set the following parameters in the `deploy` execution's
`<configuration>` section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`_liferayHome` | `String` | `bundles` |  
`deployFile` | `File` | `${project.build.directory}/${project.build.finalName}.${project.packaging}` | 
`outputFileName` | `String` | `${project.artifactId}.${project.packaging}` | 

## dist Goal's Available Parameters

You can change the default parameter values of the `dist` goal by creating an
`<execution>` section for it. Within the `<execution>` section, define a
`<configuration>` section. For example,

    <execution>
        <id>dist</id>
        <goals>
            <goal>dist</goal>
        </goals>
        <configuration>
        </configuration>
    </execution>

The `dist` goal's parameters are listed below.

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`_liferayHome` | `String` | `bundles` |  
`archiveFileName` | `String` |  | 
`cacheDir`  | `File` | `${user.home}/.liferay/bundles` | 
`configs`  | `String` | `configs` | 
`deployFile` | `File` |` ${project.build.directory}/${project.build.finalName}.${project.packaging}` | 
`environment`  | `String` | `${liferay.workspace.environment}` | 
`format`  | `String` | `zip` | 
`includeFolder` | `boolean` | `true` | 
`outputFileName` | `String` | `${project.artifactId}.${project.packaging}` | 
`password` | `String` |  | 
`stripComponents` | `int` | `1` | 
`token` | `boolean` |  | 
`tokenFile` | `File` | `${user.home}/.liferay/token` | 
`url` | `URL` | `${liferay.workspace.bundle.url}` | 
`userName` | `String` |  | 

## init Goal's Available Parameters

You can change the default parameter values of the `create-token` goal by
creating an `<execution>` section for it. Within the `<execution>` section,
define a `<configuration>` section. For example,

    <execution>
        <id>init</id>
        <goals>
            <goal>init</goal>
        </goals>
        <configuration>
        </configuration>
    </execution>

The `init` goal's parameters are listed below.

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`_liferayHome` |  | `bundles` | 
`cacheDir` |  | `${user.home}/.liferay/bundles` | 
`configs` |  | `configs` | 
`environment` |  | `${liferay.workspace.environment}` | 
`password` |  |  | 
`stripComponents` |  | `1` |
`token` |  |  | 
`tokenFile` |  | `${user.home}/.liferay/token` |
`url` |  | `${liferay.workspace.bundle.url}` | 
`userName` |  |  | 

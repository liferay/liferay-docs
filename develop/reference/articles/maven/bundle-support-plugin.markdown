# Bundle Support Plugin [](id=bundle-support-plugin)

The Bundle Support plugin lets you use
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
as a Maven project. For more information on how a Maven Workspace works and the
features it provides, see the
[Maven Workspace](/develop/tutorials/-/knowledge_base/7-1/maven-workspace)
tutorial.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

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

## Goals [](id=goals)

The plugin adds five Maven goals to your project:

Name | Description
---- | -----------
[bundle-support:clean](#clean-goals-available-parameters) |  Deletes a file from the `deploy` directory of a Liferay bundle.
[bundle-support:create-token](#create-token-goals-available-parameters) | Creates a token used to validate your user credentials when downloading a DXP bundle.
[bundle-support:deploy](#deploy-goals-available-parameters) | Deploys the Maven project to the specified @product@ bundle.
[bundle-support:dist](#dist-goals-available-parameters) | Creates a distributable @product@ bundle archive file (e.g., ZIP).
[bundle-support:init](#init-goals-available-parameters) | Downloads and installs the specified @product@ version.

## clean Goal's Available Parameters [](id=clean-goals-available-parameters)

You can set the following parameters in the `clean` execution's
`<configuration>` section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`liferayHome` | `String` | `bundles` |  The directory where your @product@ instance resides. This can be specified from the command line as `-DliferayHome=`.
`fileName` | `String` | `${project.artifactId}.${project.packaging}` | The name of the file to delete from your bundle.

## create-token Goal's Available Parameters [](id=create-token-goals-available-parameters)

You can change the default parameter values of the `create-token` goal by
creating an `<execution>` section containing `<configuration>` tags. For
example,

    <execution>
        <id>create-token</id>
        <goals>
            <goal>create-token</goal>
        </goals>
        <configuration>
        </configuration>
    </execution>

You can set the following parameters in the `create-token` execution's
`<configuration>` section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`emailAddress` | `String` | `null` | The email address to use when downloading a DXP bundle. This email address must match the one registered for your DXP subscription.
`force` | `boolean` | `false` | Whether to override the existing token with a newly generated one.
`password` | `String` | `null` | The password to use when downloading a DXP bundle. This password must match the one registered for your DXP subscription.
`passwordFile` | `File` | `null` | The file to hold your password used when downloading a DXP bundle.
`tokenFile` | `File` | `${user.home}/.liferay/token` | The file to hold the Liferay bundle authentication token.
`tokenUrl` | `URL` | `https://cdn.lfrs.sl/releases.liferay.com/portal/7.1.0-a1/liferay-ce-portal-tomcat-7.1-a1-20180410105031513.zip` | The URL pointing to the bundle Zip to download.

After executing the `create-token` goal, you're prompted for your email address
and password, both of which are used to generate your token. It's recommended to
configure your email and password from the command line rather than specifying
them in your POM file.

## deploy Goal's Available Parameters [](id=deploy-goals-available-parameters)

You can set the following parameters in the `deploy` execution's
`<configuration>` section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`liferayHome` | `String` | `bundles` | The directory where your @product@ instance resides. This can be specified from the command line as `-DliferayHome=`.
`deployFile` | `File` | `${project.build.directory}/${project.build.finalName}.${project.packaging}` | The packaged file (e.g., JAR) to deploy to the Liferay bundle.
`outputFileName` | `String` | `${project.artifactId}.${project.packaging}` | The name of the output file.

## dist Goal's Available Parameters [](id=dist-goals-available-parameters)

You can change the default parameter values of the `dist` goal by creating an
`<execution>` section containing `<configuration>` tags. For example,

    <execution>
        <id>dist</id>
        <goals>
            <goal>dist</goal>
        </goals>
        <configuration>
        </configuration>
    </execution>

You can set the following parameters in the `dist` execution's `<configuration>`
section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`liferayHome` | `String` | `bundles` | The directory where your @product@ instance resides. This can be specified from the command line as `-DliferayHome=`.
`archiveFileName` | `String` | `null` | The name for the generated archive file.
`cacheDir`  | `File` | `${user.home}/.liferay/bundles` | The directory where the downloaded bundle Zip files are stored.
`configs`  | `String` | `configs` | The directory that contains the configuration files.
`deployFile` | `File` |` ${project.build.directory}/${project.build.finalName}.${project.packaging}` | The packaged file (e.g., JAR) to deploy to the Liferay bundle.
`environment`  | `String` | `${liferay.workspace.environment}` | The environment of your Liferay home deployment. (e.g., `common`, `dev`, `local`, `prod`, and `uat`).
`format`  | `String` | `zip` | The format type to use when packaging the Liferay bundle as an archive.
`includeFolder` | `boolean` | `true` | Whether to add a parent folder to the archive.
`outputFileName` | `String` | `${project.artifactId}.${project.packaging}` | The path to the archive file.
`password` | `String` | `null` | The password if your Liferay bundle's URL requires authentication.
`stripComponents` | `int` | `1` | The number of directories to strip when expanding your bundle.
`token` | `boolean` | `false` | Whether to use a token to download a @product@ bundle. This should be set to `true` when downloading a DXP bundle.
`tokenFile` | `File` | `${user.home}/.liferay/token` | The file to hold the Liferay bundle authentication token.
`url` | `URL` | `${liferay.workspace.bundle.url}` | The URL of the Liferay bundle to expand.
`userName` | `String` | `null` | The user name if your Liferay bundle's URL requires authentication.

## init Goal's Available Parameters [](id=init-goals-available-parameters)

You can change the default parameter values of the `init` goal by creating an
`<execution>` section containing `<configuration>` tags. For example,

    <execution>
        <id>init</id>
        <goals>
            <goal>init</goal>
        </goals>
        <configuration>
        </configuration>
    </execution>

You can set the following parameters in the `init` execution's `<configuration>`
section of the POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`liferayHome` | `String` | `bundles` | The directory where your @product@ instance resides. This can be specified from the command line as `-DliferayHome=`.
`cacheDir` | `File` | `${user.home}/.liferay/bundles` | The directory where the downloaded bundle Zip files are stored.
`configs` | `String` | `configs` | The directory that contains the configuration files.
`environment` | `String` | `${liferay.workspace.environment}` | The environment with the settings appropriate for current development (e.g., `common`, `dev`, `local`, `prod`, and `uat`).
`password` | `String` | `null` | The password if your Liferay bundle's URL requires authentication.
`stripComponents` | `int` | `1` | The number of directories to strip when expanding your bundle.
`token` | `boolean` | `false` | Whether to use a token to download a @product@ bundle. This should be set to `true` when downloading a DXP bundle.
`tokenFile` | `File` | `${user.home}/.liferay/token` | The file to hold the Liferay bundle authentication token.
`url` | `URL` | `${liferay.workspace.bundle.url}` | The URL of the Liferay bundle to expand.
`userName` | `String` | `null` | The user name if your Liferay bundle's URL requires authentication.

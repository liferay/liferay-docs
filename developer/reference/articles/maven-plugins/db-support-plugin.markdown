# DB Support Plugin [](id=db-support-plugin)

The DB Support plugin lets you run the Liferay DB Support tool to execute
certain actions on a local @product@ database. The following actions are
available:

- Cleans the Liferay database from the Service Builder tables and rows of a
  module.

## Usage [](id=usage)

To use the plugin, include it in your project's `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.db.support</artifactId>
                <version>1.0.6</version>
                <configuration>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>org.hsqldb</groupId>
                        <artifactId>hsqldb</artifactId>
                        <version>2.4.0</version>
                    </dependency>
                </dependencies>
            </plugin>
        ...
        </plugins>
    </build>

Also notice the configured plugin dependency. You must configure the JDBC driver
used by your @product@ bundle so the DB Support plugin can properly manage your
database. Replace the HSQLDB driver listed above with your custom database's
JDBC driver.

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`db-support:clean-service-builder` |  Cleans the @product@ database from the Service Builder tables and rows of a module.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`password` | `String` | `jdbc.default.password` | The user password for connecting to the @product@ database.
`propertiesFile` | `File` | `null` | The `portal-ext.properties` file which contains the JDBC settings for connecting to the @product@ database.
`serviceXmlFile` | `File` | `null` | The `service.xml` file of the module.
`servletContextName` | `String` | `null` | The servlet context name (usually the value of the `Bundle-Symbolic-Name` manifest header) of the module.
`url` | `String` | `jdbc.default.url` | The JDBC URL for connecting to the @product@ database.
`userName` | `String` | `jdbc.default.username` | The user name for connecting to the @product@ database.

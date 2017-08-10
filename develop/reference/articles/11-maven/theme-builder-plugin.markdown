# Theme Builder Plugin

The Theme Builder plugin lets you build Liferay theme files in your project.
Visit the
[Building Themes in a Maven Project](/develop/tutorials/-/knowledge_base/7-0/building-themes-in-a-maven-project)
tutorial to learn more about applying Theme Builder to your Maven project.

## Usage

To use the plugin, include it in your `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.portal.tools.theme.builder</artifactId>
                <version>1.1.4</version>
                <executions>
                    <execution>
                        <phase>generate-resources</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                        <configuration>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            ...
        </plugins>
    </build>

## Goals

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`theme-builder:build` |  Builds the theme files.

## Available Parameters

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`diffsDir` |  |  | 
`name` |  |  | 
`outputDir` |  |  | 
`parentDir` |  |  | 
`parentName` |  |  | 
`templateExtension` |  |  | 
`unstyledDir` |  |  | 







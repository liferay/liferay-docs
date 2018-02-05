# Javadoc Formatter Plugin [](id=javadoc-formatter-plugin)

The Javadoc Formatter plugin lets you format project Javadoc comments. The tool
lets you generate:

- Default [`@author`](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html#@author)
  tags to all classes.
- Comment stubs to classes, fields, and methods.
- Missing [`@Override`](https://docs.oracle.com/javase/8/docs/api/java/lang/Override.html)
  annotations.
- An XML representation of the Javadoc comments, which can be used by tools in
  order to index the Javadocs of the project.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.javadoc.formatter</artifactId>
                <version>1.0.26</version>
                <configuration>
                </configuration>
            </plugin>
        ...
        </plugins>
    </build>

You can view an example POM containing the Javadoc Formatter configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/javadoc-formatter/samples/pom.xml).

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`javadoc-formatter:format` | Runs the Liferay Javadoc Formatter to format files.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`author` | `String` | `"Brian Wing Shun Chan"` | The value of the `@author` tag to add at class level if missing.
`generateXml` | `boolean` | `false` | Whether to generate a XML representation of the Javadoc comments. The XML files are generated in the `src/main/resources` directory only if the Java files are contained in `src/main/java`.
`initializeMissingJavadocs` | `boolean` | `false` | Whether to add comment stubs at the class, field, and method levels. If `false`, only the class-level `@author` is added.
`inputDirName` | `String` | `"./"` | The root directory to begin searching for Java files to format.
`limits` | `String[]` | `[]` | The Java file name patterns, relative to the working directory, to include when formatting Javadoc comments. The patterns must be specified without the `.java` file type suffix. If empty, all Java files are formatted.
`outputFilePrefix` | `String` | `"javadocs"` | The file name prefix of the XML representation of the Javadoc comments. If `generateXML` is `false`, this property is not used.
`updateJavadocs` | `boolean` | `false` | Whether to fix existing comment blocks by adding missing tags.

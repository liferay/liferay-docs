# Source Formatter Plugin [](id=source-formatter-plugin)

The Source Formatter plugin lets you format project files using the
[Liferay Source Formatter](https://github.com/liferay/liferay-portal/blob/master/modules/util/source-formatter)
tool.

## Usage [](id=usage)

To use the plugin, include it in your `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.source.formatter</artifactId>
                <version>1.0.432</version>
                <executions>
                    <execution>
                        <phase>process-sources</phase>
                        <goals>
                            <goal>format</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                </configuration>
            </plugin
        ...
        </plugins>
    </build>

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`source-formatter:format` |  Runs the Liferay Source Formatter to format source formatting errors.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`autoFix` | `boolean` | `true` | Whether to automatically fix source formatting errors.
`baseDir` | `String` | `"./"` | The Source Formatter base directory. *(Read-only)*
`fileNames` | `String[]` | `null` | The file names to format, relative to the project directory. If `null`, all files contained in `baseDir` will be formatted.
`formatCurrentBranch` | `boolean` | `false` | Whether to format only the files contained in `baseDir` that are added or modified in the current Git branch.
`formatLatestAuthor` | `boolean` | `false` | Whether to format only the files contained in `baseDir` that are added or modified in the latest Git commits of the same author.
`formatLocalChanges` | `boolean` | `false` | Whether to format only the unstaged files contained in `baseDir`.
`gitWorkingBranchName` | `String` | `"master"` | The Git working branch name.
`includeSubrepositories` | `boolean` | `false` | Whether to format files that are in read-only subrepositories.
`maxLineLength` | `int` | `80` | The maximum number of characters allowed in Java files.
`printErrors` | `boolean` | `true` | Whether to print formatting errors on the Standard Output stream.
`processorThreadCount` | `int` | `5` | The number of threads used by Source Formatter.
`showDocumentation` | `boolean` | `false` | Whether to show the documentation for the source formatting issues, if present.
`throwException` | `boolean` | `false` | Whether to fail the build if formatting errors are found.

# CSS Builder Plugin [](id=css-builder-plugin)

The CSS Builder plugin lets you compile [Sass](http://sass-lang.com/) files in
your project.

## Usage [](id=usage)

To use the plugin, include it in your project's root `pom.xml` file:

    <build>
        <plugins>
        ...
            <plugin>
                <groupId>com.liferay</groupId>
                <artifactId>com.liferay.css.builder</artifactId>
                <version>2.1.0</version>
                <executions>
                    <execution>
                        <id>default-build</id>
                        <phase>compile</phase>
                        <goals>
                            <goal>build</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                </configuration>
            </plugin>
        ...
        </plugins>
    </build>

You can view an example POM containing the CSS Builder configuration
[here](https://github.com/liferay/liferay-portal/blob/master/modules/util/css-builder/samples/pom.xml).

## Goals [](id=goals)

The plugin adds one Maven goal to your project:

Name | Description
---- | -----------
`css-builder:build` | Compiles the Sass files in the project.

## Available Parameters [](id=available-parameters)

You can set the following parameters in the `<configuration>` section of the
POM:

Parameter Name | Type | Default Value | Description
------------- | ---- | ------------- | -----------
`appendCssImportTimestamps` | `boolean` | `true` | Whether to append the current timestamp to the URLs in the `@import` CSS at-rules.
<a name="basedir"></a>`baseDir` | `File` | `"src/META-INF/resources"` | The base directory that contains the SCSS files to compile.
`dirNames` | `List<String>` | `["/"]` | The name of the directories, relative to [`baseDir`](#basedir), which contain the SCSS files to compile.
`generateSourceMap` | `boolean` | `false` | Whether to generate [source maps](https://developers.google.com/web/tools/chrome-devtools/debug/readability/source-maps) for easier debugging.
`importDir` | `File` | `null` | The `META-INF/resources` directory of the [Liferay Frontend Common CSS](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-css/frontend-css-common) artifact. This is required in order to make [Bourbon](http://bourbon.io) and other CSS libraries available to the compilation.
`outputDirName` | `String` | `".sass-cache/"` | The name of the sub-directories where the SCSS files are compiled to. For each directory that contains SCSS files, a sub-directory with this name is created.
`precision` | `int` | `9` | The numeric precision of numbers in Sass.
`rtlExcludedPathRegexps` | `List<String>` |  | The SCSS file patterns to exclude when converting for right-to-left (RTL) support.
`sassCompilerClassName` | `String` | `"jni"` | The type of Sass compiler to use. Supported values are `"jni"` and `"ruby"`. The Ruby Sass compiler requires `com.liferay.sass.compiler.ruby.jar`, `com.liferay.ruby.gems.jar`, and `jruby-complete.jar` to be added to the classpath.
 
